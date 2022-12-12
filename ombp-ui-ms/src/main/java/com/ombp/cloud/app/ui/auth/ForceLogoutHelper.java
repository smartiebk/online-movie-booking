package com.ombp.cloud.app.ui.auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ombp.cloud.app.ui.auth.util.CookieTokenUtil;
import com.ombp.cloud.app.ui.util.SpringContextHelper;
import com.ombp.cloud.app.ui.util.StaticDataHolder;

public class ForceLogoutHelper {

	public ModelAndView forceLogout() throws ServletException 
	{
		StaticDataHolder.clearData();
		
		HttpServletRequest request = SpringContextHelper.getRequest();
		request.logout();
		
		HttpServletResponse originalResponse = SpringContextHelper.getResponse();
    	// Clear session cookie
		CookieTokenUtil.writeTokenCookieToResponse(originalResponse, null);
		
		ModelAndView modelAndView =  new ModelAndView("redirect:/login?alogin=true");
		modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
		return modelAndView;
	}
	
}
