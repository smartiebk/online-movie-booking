package com.ombp.cloud.app.ui.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.util.StringUtils;

import com.ombp.cloud.app.ui.auth.util.CookieTokenUtil;
import com.ombp.cloud.app.ui.exception.CookieMalformedException;
import com.ombp.cloud.model.usermanagement.AccessToken;

public class AuthTokenUtil {

	private final static Logger LOG = LoggerFactory.getLogger(CookieTokenUtil.class);

	public static AccessToken getAccessToken(HttpServletRequest request) throws CookieMalformedException {
		String token = "";

		Cookie sessionCookie = CookieTokenUtil.getValidTokenCookie(request.getCookies());

		if (sessionCookie == null || StringUtils.isEmpty(sessionCookie.getValue())) {
			LOG.debug("No cookies are present!");
			throw new SessionAuthenticationException("No cookie for app-session-id found after login...");
		}

		token = sessionCookie.getValue();

		AccessToken accessToken = CookieTokenUtil.extractTokens(token);

		return accessToken;
	}
}
