package com.ombp.cloud.app.ui.auth;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ombp.cloud.app.ui.api.AuthServerAPI;
import com.ombp.cloud.app.ui.auth.service.RemoteAuthService;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.exception.ServiceDownIOException;
import com.ombp.cloud.app.ui.util.SpringContextHelper;
import com.ombp.cloud.model.usermanagement.AccessToken;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

@Component
public class TLCAuthenticationProvider implements AuthenticationProvider {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RemoteAuthService remoteAuthService;
	
	@Autowired
	private AuthServerAPI authServerAPI;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        AccessToken accessToken;
		try {
			accessToken = remoteAuthService.fetchAccessToken(user, password);
		} catch (ServiceDownIOException e) {
			LOG.error("Requested Authorization service is down....",e);
			return null;
		}
        
        if (accessToken!=null && accessToken.getAccess_token()!=null && accessToken.getAccess_token().length()!=0) {  
        	AuthenticatedUser authenticatedUser = null;
        	try {
        		HttpServletRequest request = SpringContextHelper.getRequest();
        		request.setAttribute(UIConstants.ACCESS_TOKEN_REQ_ATTR, accessToken);
				authenticatedUser = authServerAPI.getUserInfo();
			} catch (IOException e) {
				LOG.error("Requested Authorization service is down....",e);
				return null;
			}
			return new TLCAuthenticationToken(user, password, Collections.emptyList(), accessToken, authenticatedUser);
		} else if (accessToken != null && accessToken.getErrorMessage().length() != 0
				&& accessToken.getErrorMessage().equalsIgnoreCase(UIConstants.USER_NOT_FOUND_MESSAGE)) {
			throw new UsernameNotFoundException("External system authentication failed");
		} else if (accessToken != null && accessToken.getErrorMessage().length() != 0
				&& accessToken.getErrorMessage().equalsIgnoreCase(UIConstants.WRONG_PASSWORD_MESSAGE)) {
			throw new BadCredentialsException("External system authentication failed");
		} 
        
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}