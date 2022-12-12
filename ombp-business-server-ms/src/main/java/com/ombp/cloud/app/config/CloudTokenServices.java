package com.ombp.cloud.app.config;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.xml.OAuth2ClientContextFactoryBean;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.ombp.cloud.app.events.LogLoginInfoEvent;
import com.ombp.cloud.app.oauth2.models.CloudUser;
import com.ombp.cloud.app.um.service.LoginHistoryService;
import com.ombp.cloud.app.util.EventPublisher;
import com.ombp.cloud.model.usermanagement.AppLoginHistory;

public class CloudTokenServices extends DefaultTokenServices {

	private DataSource dataSource;
	
	private LoginHistoryService loginHistoryService;
	
	private final String GRANT_TYPE_PASSWORD = "password";
	
	private final String GRANT_TYPE_REFESH_TOKEN = "refresh_token";
	
	@Override
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {

		if(GRANT_TYPE_PASSWORD.equalsIgnoreCase(authentication.getOAuth2Request().getGrantType())) {
			CloudJdbcTokenStore jdbcTokenStore = new CloudJdbcTokenStore(dataSource);
			//For every login using password grant_type, issue new refresh token so that it would have longer validity
			OAuth2AccessToken existingAccessToken = jdbcTokenStore.getAccessToken(authentication);
			if(existingAccessToken!=null) {
				jdbcTokenStore.removeRefreshToken(existingAccessToken.getRefreshToken());
				jdbcTokenStore.removeAccessToken(existingAccessToken);
			}
		}
		OAuth2AccessToken token = super.createAccessToken(authentication);
		
		AppLoginHistory appLoginHistory = new AppLoginHistory(((CloudUser) authentication.getPrincipal()).getUsername(),
				authentication.getOAuth2Request().getClientId(), token.getValue(), token.getExpiration(),
				token.getRefreshToken().getValue(),
				((DefaultExpiringOAuth2RefreshToken) token.getRefreshToken()).getExpiration(), new Date(), new Date(),
				authentication.getOAuth2Request().getGrantType());
		
		EventPublisher.publishEvent(new LogLoginInfoEvent(appLoginHistory));
		
		return token;
	}

	
	@Override
	public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest)
			throws AuthenticationException {
		//fetch login request to get the exact time
		//if refresh token will expire in next 4 hours and user is logging in after long time like 12 hours then only
		AppLoginHistory appLoginHistory = new AppLoginHistory();
		appLoginHistory.setRefreshTokenValue(refreshTokenValue);
		AppLoginHistory loggedData = loginHistoryService.fetchAppLoginRecord(appLoginHistory);
		
		long timeSinceLastRequestInMins = (new Date().getTime() - loggedData.getUpdateTime().getTime())/(60 * 1000) % 60;
		long timeToExpireRefreshToken = (loggedData.getRefreshTokenExpiration().getTime()-new Date().getTime())/(60 * 1000) % 60;
		
		if(GRANT_TYPE_REFESH_TOKEN.equalsIgnoreCase(tokenRequest.getGrantType()) && timeSinceLastRequestInMins >= 720 && timeToExpireRefreshToken <= 240) 
		{
			super.setReuseRefreshToken(false);
		}
		OAuth2AccessToken token = super.refreshAccessToken(refreshTokenValue, tokenRequest);
		super.setReuseRefreshToken(true);
		appLoginHistory.setUsername(loggedData.getUsername());
		appLoginHistory.setClientId(tokenRequest.getClientId());
		appLoginHistory.setAccessTokenValue(token.getValue());
		appLoginHistory.setAccessTokenExpiration(token.getExpiration()); 
		appLoginHistory.setRefreshTokenValue(token.getRefreshToken().getValue());
		appLoginHistory.setRefreshTokenExpiration(((DefaultExpiringOAuth2RefreshToken) token.getRefreshToken()).getExpiration());
		appLoginHistory.setCreateTime(new Date());
		appLoginHistory.setUpdateTime(new Date());
		appLoginHistory.setGrantType(tokenRequest.getGrantType());
		
		EventPublisher.publishEvent(new LogLoginInfoEvent(appLoginHistory));
		return token;
	}


	@Override
	public void setTokenStore(TokenStore tokenStore) {
		super.setTokenStore(tokenStore);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setLogLoginHistoryService(LoginHistoryService loginHistoryService) {
		this.loginHistoryService = loginHistoryService;
	}

}
