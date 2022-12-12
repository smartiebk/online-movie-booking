package com.ombp.cloud.app.ui.config.feign;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ombp.cloud.app.ui.auth.service.RemoteAuthService;
import com.ombp.cloud.app.ui.auth.util.CookieTokenUtil;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.exception.CookieMalformedException;
import com.ombp.cloud.app.ui.exception.TokenExpiredException;
import com.ombp.cloud.app.ui.util.SpringContextHelper;
import com.ombp.cloud.model.usermanagement.AccessToken;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private static final  String HTTP_AJAX_HEADER_NAME = "X-Requested-With";
	private static final String HTTP_AJAX_HEADER_VALUE = "XMLHttpRequest";
	
	@Autowired
	private RemoteAuthService remoteAuthService;
	
    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String s, Response response) {

        Exception exception = errorDecoder.decode(s, response);
        
        LOG.error("Some error while calling remote service....", exception.getMessage());

        if(response.status() == 401){
        	
        	if(exception.getMessage()!=null && exception.getMessage().contains(UIConstants.TAMPERED_COOKIE_RESPONSE)) 
        	{
        		return new CookieMalformedException();
        	}
        	
        	HttpServletRequest request = SpringContextHelper.getRequest();
        	
        	Cookie sessionCookie = null;
        	
        	AccessToken existingAccessToken = null;
        	
        	AccessToken newlyObtainedAccessTokenFromRefreshToken = null;
        	
        	Object et = request.getAttribute(UIConstants.ACCESS_TOKEN_REQ_ATTR);
        	
        	if(et!=null) 
        	{
        		existingAccessToken = (AccessToken) et;
        	}
        	/*else if(SpringContextHelper.getAccessTokenForAuthenticatedUser()!=null) 
        	{
        		existingAccessToken = SpringContextHelper.getAccessTokenForAuthenticatedUser();
        	}*/
        	else 
        	{
        		sessionCookie = CookieTokenUtil.getValidTokenCookie(request.getCookies());

                if( sessionCookie == null || StringUtils.isEmpty( sessionCookie.getValue() ) ) {
                	return new TokenExpiredException(UIConstants.OTHER_INSTANCE_LOGIN_INDUCE_LOGOUT);
                }
                
    			try {
    				existingAccessToken = CookieTokenUtil.extractTokens(sessionCookie.getValue());
    			} catch (CookieMalformedException e) {
    				LOG.error("Malformed cookie was processed....", e);
    			} 
        	}
        	
        	newlyObtainedAccessTokenFromRefreshToken = remoteAuthService.fetchAccessToken(existingAccessToken.getRefresh_token());
        	
        	HttpServletResponse originalResponse = SpringContextHelper.getResponse();
        	
        	if(newlyObtainedAccessTokenFromRefreshToken!=null) {
        	
        		if(sessionCookie!=null) 
        		{
        			sessionCookie.setValue(CookieTokenUtil.getCookieString(newlyObtainedAccessTokenFromRefreshToken));	
        		}
        		// Add a session cookie
        		CookieTokenUtil.writeTokenCookieToResponse(originalResponse, newlyObtainedAccessTokenFromRefreshToken);
        		
        		SpringContextHelper.setAccessTokenForAuthenticatedUser(newlyObtainedAccessTokenFromRefreshToken);
        		
				return new RetryableException(401, "401 error", response.request().httpMethod(),
						new Date(System.currentTimeMillis() + 1000L), response.request());
        	}
        	else 
        	{
        		// Add a session cookie
        		CookieTokenUtil.writeTokenCookieToResponse(originalResponse, newlyObtainedAccessTokenFromRefreshToken);
        		
        		if(isAjax(SpringContextHelper.getRequest())) 
        		{
        			return new SessionAuthenticationException("");
        		}
        		
        		return new TokenExpiredException(UIConstants.OTHER_INSTANCE_LOGIN_INDUCE_LOGOUT);
        	}
        	
        }
        
        if(exception instanceof RetryableException){
            return exception;
        }

        return exception;
    }

    private boolean isAjax(HttpServletRequest request) {
		String header = request.getHeader(HTTP_AJAX_HEADER_NAME);
		return header != null && HTTP_AJAX_HEADER_VALUE.equals(header);
	}
}
