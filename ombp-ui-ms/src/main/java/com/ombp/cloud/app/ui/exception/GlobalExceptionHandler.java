package com.ombp.cloud.app.ui.exception;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ombp.cloud.app.ui.auth.ForceLogoutHelper;
import com.ombp.cloud.app.ui.constants.UIConstants;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TokenExpiredException.class)
	public ModelAndView handleTokenExpiredException(TokenExpiredException mex) throws ServletException {
        return new ForceLogoutHelper().forceLogout();
    }
	
	@ExceptionHandler({ServiceDownIOException.class, CookieMalformedException.class})
	public ModelAndView handleServiceDownException(ServiceDownIOException mex) throws ServletException {
		
		if(mex!=null && mex.getMessage()!=null &&  mex.getMessage().equalsIgnoreCase(UIConstants.OTHER_INSTANCE_LOGIN_INDUCE_LOGOUT)) 
		{
			return new ForceLogoutHelper().forceLogout();
		}
		
        return new ModelAndView("pages/error");
    }
	
}
