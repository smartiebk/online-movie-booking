package com.ombp.cloud.app.ui.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.ombp.cloud.app.ui.api.AuthServerAPI;
import com.ombp.cloud.app.ui.auth.util.CookieTokenUtil;
import com.ombp.cloud.app.ui.util.StaticDataHolder;

@Component
public class AuthLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		StaticDataHolder.clearData(((TLCAuthenticationToken)authentication).getAuthenticatedUser());
		//Clear cookie containing refreshToken and accessToken
		CookieTokenUtil.writeTokenCookieToResponse(response, null);
		// custom logic to logout from
		super.setDefaultTargetUrl("/login");
		super.onLogoutSuccess(request, response, authentication);
	}
}