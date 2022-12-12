package com.ombp.cloud.app.ui.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.ombp.cloud.app.ui.auth.util.CookieTokenUtil;
import com.ombp.cloud.model.usermanagement.AccessToken;

@Component
public class AuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		if (!(authentication instanceof TLCAuthenticationToken)) {
			return;
		}

		TLCAuthenticationToken auth = (TLCAuthenticationToken) authentication;
		AccessToken accessToken = auth.getAccessToken();
		
		if(accessToken!=null) 
		{
			// Add a session cookie
			CookieTokenUtil.writeTokenCookieToResponse(response, accessToken);

			//request.getSession(false).setAttribute(UIConstants.ACCESS_TOKEN_SESSION_ATTR, accessToken);
			
			// clearAuthenticationAttributes(request);
			
			HttpSession session = request.getSession(false);

			boolean isNotAppRequest = false;
			
			if (session != null) {
				
				SavedRequest savedRequest = session.getAttribute(SAVED_REQUEST)!=null ? (SavedRequest) session.getAttribute(SAVED_REQUEST) : null;
				
				if(savedRequest!=null) 
				{
					isNotAppRequest = ((DefaultSavedRequest)savedRequest).getRequestURI().equalsIgnoreCase("/");
				}
				else 
				{
					isNotAppRequest = true;
				}
			}
			else 
			{
				isNotAppRequest = true;
			}

			if(isNotAppRequest) 
			{
				String tenant = "";
				
				super.setDefaultTargetUrl("/app"+tenant);
				super.setAlwaysUseDefaultTargetUrl(true);
			}
			
			// call the original impl
			super.onAuthenticationSuccess(request, response, authentication);
		}
		else 
		{
			response.sendRedirect("/error");
		}
	}

}
