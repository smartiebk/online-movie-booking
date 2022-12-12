package com.ombp.cloud.app.ui.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ombp.cloud.app.ui.auth.TLCAuthenticationToken;
import com.ombp.cloud.model.usermanagement.AccessToken;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

public class SpringContextHelper {

	public static HttpServletResponse getResponse() 
	{
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    	HttpServletResponse originalResponse = ((ServletRequestAttributes)requestAttributes).getResponse();
    	
    	return originalResponse;
	}
	
	public static HttpServletRequest getRequest() 
	{
		HttpServletRequest request =null;
		
		if(RequestContextHolder.getRequestAttributes()!=null) 
		{
			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
	                .getRequest();
		}
		return request;
	}
	
	public static AuthenticatedUser getAuthenticatedUser() 
	{
		AuthenticatedUser user = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		TLCAuthenticationToken sauth = (TLCAuthenticationToken) auth;
		
		user = sauth.getAuthenticatedUser();
		
		return user;
	}
	
	public static AccessToken getAccessTokenForAuthenticatedUser() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		TLCAuthenticationToken sauth = (TLCAuthenticationToken) auth;
		
		return sauth.getAccessToken();
	}
	
	public static void setAccessTokenForAuthenticatedUser(AccessToken accessToken) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		TLCAuthenticationToken sauth = (TLCAuthenticationToken) auth;
		
		sauth.setAccessToken(accessToken);
	}
	
}
