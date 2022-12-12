package com.ombp.cloud.app.ui.auth.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.constants.UIURLConstants;
import com.ombp.cloud.app.ui.exception.CookieMalformedException;
import com.ombp.cloud.model.usermanagement.AccessToken;
import com.ombp.cloud.utils.RandomStringCryptoUtil;

public class CookieTokenUtil {
	
	private final static Logger LOG = LoggerFactory.getLogger(CookieTokenUtil.class);

	public static String getCookieString(AccessToken accessToken) {
		String cookieToken = RandomStringCryptoUtil.getRandomAlphaNumericString(4)
				+ accessToken.getAccess_token().length() + "_45_" + accessToken.getAccess_token() +

				RandomStringCryptoUtil.getRandomAlphaNumericString(7) + accessToken.getRefresh_token().length()
				+ "_667_" + accessToken.getRefresh_token() + System.nanoTime();

		return cookieToken;
	}

	public static AccessToken extractTokens(String cookieToken) throws CookieMalformedException {
		
		AccessToken accessToken = new AccessToken();

		try {
		
			String accessTokenStr = cookieToken.substring(4, cookieToken.length());
			String[] tempArr1 = accessTokenStr.split("_45_");
	
			int accessTokenLength = Integer.valueOf(tempArr1[0]);
			String actualAccessToken = tempArr1[1].substring(0, accessTokenLength);
	
			String refreshToken1 = tempArr1[1].substring(accessTokenLength + 7, tempArr1[1].length());
	
			String[] tempArr2 = refreshToken1.split("_667_");
	
			int refreshTokenLength = Integer.valueOf(tempArr2[0]);
	
			String actualRefreshToken = tempArr2[1].substring(0, refreshTokenLength);
			
			accessToken.setAccess_token(actualAccessToken);
	
			accessToken.setRefresh_token(actualRefreshToken);
		}
		catch(Exception e) 
		{
			LOG.error("Could not extract tokens. Cookie may be malformed", e);
			
			throw new CookieMalformedException();
		}
		return accessToken;

	}
	
	public static void writeTokenCookieToResponse(HttpServletResponse response, AccessToken accessToken) 
	{
		String token = null;
		
		int maxAge = 0;
		
		if(accessToken!=null) 
		{
			token = CookieTokenUtil.getCookieString(accessToken);
			maxAge = 60 * 60 * 24 * 365 * 10;
		}
		
		Cookie sessionCookie = new Cookie(UIConstants.AUTH_COOKIE_NAME, token);
		sessionCookie.setMaxAge(maxAge);
		sessionCookie.setHttpOnly(true);
		sessionCookie.setPath(UIURLConstants.BASE_URL);
		response.addCookie(sessionCookie);
	}
	
	public static void writeCookieInBase64ToResponse(HttpServletResponse response, String cookieName,
			String cookieValue) throws UnsupportedEncodingException {
		int maxAge = 60 * 60 * 24 * 365 * 10;

		Cookie sessionCookie = new Cookie(cookieName,
				Base64.getEncoder().encodeToString(cookieValue.getBytes("UTF-8")));
		sessionCookie.setMaxAge(maxAge);
		sessionCookie.setPath(UIURLConstants.LANDING_URL);
		response.addCookie(sessionCookie);
	}
	
	/**
	 * <b>This method picks the latest/most recent valid cookie from the request</b>
	 * @param cookies
	 * @return
	 */
	public static Cookie getValidTokenCookie(Cookie[] cookies) 
	{
        if( cookies == null || cookies.length < 1 ) {
            LOG.debug("No cookies are present!");
            return null;
        }

        List<Cookie> sessionCookies = new ArrayList<>();
        for( Cookie cookie : cookies ) {
            if( (UIConstants. AUTH_COOKIE_NAME).equals( cookie.getName() ) ) {
            	sessionCookies.add(cookie);
            }
        }
        
        Cookie sessionCookie = null;
        
        if(sessionCookies.size() >= 1) 
        {
        	Collections.sort(sessionCookies,new Comparator<Cookie>() {
                @SuppressWarnings("deprecation")
				@Override
                public int compare(Cookie a, Cookie b) {
                	Date ad = new Date();
                	ad.setSeconds(a.getMaxAge());
                	Date bd = new Date();
                	bd.setSeconds(b.getMaxAge());
                    return ad.compareTo(bd);
                }
            });
        	
        	sessionCookie = sessionCookies.get(0);
        }
        
		return sessionCookie;
	}
	

}
