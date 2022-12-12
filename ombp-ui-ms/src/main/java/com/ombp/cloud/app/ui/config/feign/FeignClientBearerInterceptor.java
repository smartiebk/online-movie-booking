package com.ombp.cloud.app.ui.config.feign;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ombp.cloud.app.ui.auth.util.CookieTokenUtil;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.exception.CookieMalformedException;
import com.ombp.cloud.app.ui.util.SpringContextHelper;
import com.ombp.cloud.model.usermanagement.AccessToken;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignClientBearerInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER="Authorization";
	private static final String TOKEN_TYPE = "Bearer";
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public void apply(RequestTemplate requestTemplate) {
		
		if("openAuthServer".equalsIgnoreCase(requestTemplate.feignTarget().name())) 
		{
			//Do nothing
		}
		else 
		{
			String token = null;
			
			try {
				token = getTokenFromCookie(requestTemplate);
			} catch (CookieMalformedException e) {
				LOG.error("Malformed cookie was processed...", e);
			}
			if(token!=null) 
			{
				requestTemplate.removeHeader(AUTHORIZATION_HEADER);
				
				requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, token));
			}	
		}
	}
	
	private String getTokenFromCookie(RequestTemplate requestTemplate) throws CookieMalformedException 
	{
		String finalToken = "";
		
		String token = "";
		
		HttpServletRequest request = SpringContextHelper.getRequest();
		
		if(request!=null) 
		{
			Object loginAuth = request.getAttribute(UIConstants.ACCESS_TOKEN_REQ_ATTR);
			
			if(loginAuth!=null) {
				return ((AccessToken)loginAuth).getAccess_token();
			}
			
			if(SpringContextHelper.getAccessTokenForAuthenticatedUser()!=null) 
			{
				return SpringContextHelper.getAccessTokenForAuthenticatedUser().getAccess_token();
			}
			
			/*Object loginAuthInSession = request.getSession(false).getAttribute(UIConstants.ACCESS_TOKEN_SESSION_ATTR);
			
			if(loginAuthInSession!=null) {
				request.getSession(false).removeAttribute(UIConstants.ACCESS_TOKEN_SESSION_ATTR);
				return ((AccessToken)loginAuthInSession).getAccess_token();
			}*/
			
	        Cookie sessionCookie = CookieTokenUtil.getValidTokenCookie(request.getCookies());
	        
	        if( sessionCookie == null || StringUtils.isEmpty( sessionCookie.getValue() ) ) {
	        	LOG.debug("No cookies are present!");
	        	throw new SessionAuthenticationException("No cookie for app-session-id found after login...");
	        }
	        
	        token = sessionCookie.getValue();
	        
	        AccessToken accessToken = CookieTokenUtil.extractTokens(token);
	        
	        finalToken = accessToken.getAccess_token();
		}
		else 
		{
			boolean headerAlreadyPresent = requestTemplate.headers()!=null && requestTemplate.headers().containsKey(AUTHORIZATION_HEADER);
			
			if(headerAlreadyPresent) 
			{
				finalToken = requestTemplate.headers().get(AUTHORIZATION_HEADER).iterator().next();
			}
			
		}
		return finalToken;
	}
}