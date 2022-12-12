package com.ombp.cloud.app.endpoint.um;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.cache.TLCCache;
import com.ombp.cloud.app.config.CloudJdbcTokenStore;
import com.ombp.cloud.app.events.LogLoginInfoEvent;
import com.ombp.cloud.app.oauth2.models.CloudUser;
import com.ombp.cloud.app.util.EventPublisher;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.usermanagement.AppLoginHistory;
import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.LogoutInfo;

@RestController
public class LogoutEndPoint implements EndPointConstants {
	
	@Autowired
	private CloudJdbcTokenStore jdbcTokenStore;
	
	private final String LOGOUT_ACTION = "logout";
	
	@Autowired
	private TLCCache cache;
	
	@RequestMapping(value = LOGOUT, method = RequestMethod.GET)
	public Object logout(OAuth2Authentication authentication) {
		
		if(authentication.getPrincipal() instanceof CloudUser) 
		{
			CloudUser cuser = (CloudUser) authentication.getPrincipal();
			AppUser user = cuser.getAppUser();
			
			OAuth2AccessToken existingAccessToken = jdbcTokenStore.getAccessToken(authentication);
			if(existingAccessToken!=null) {
				jdbcTokenStore.removeRefreshToken(existingAccessToken.getRefreshToken());
				jdbcTokenStore.removeAccessToken(existingAccessToken);
			}
			
			AppLoginHistory appLoginHistory = new AppLoginHistory(((CloudUser) authentication.getPrincipal()).getUsername(),
					authentication.getOAuth2Request().getClientId(), existingAccessToken.getValue(), existingAccessToken.getExpiration(),
					existingAccessToken.getRefreshToken().getValue(),
					((DefaultExpiringOAuth2RefreshToken) existingAccessToken.getRefreshToken()).getExpiration(), new Date(), new Date(),
					LOGOUT_ACTION);
			
			cache.removeUser(user.getUsername());
			
			EventPublisher.publishEvent(new LogLoginInfoEvent(appLoginHistory));
			
			return new LogoutInfo(user.getUsername(), "Success", new Date());
		}
		return null;
	}
	
}
