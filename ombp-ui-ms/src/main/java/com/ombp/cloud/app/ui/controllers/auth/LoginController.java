package com.ombp.cloud.app.ui.controllers.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ombp.cloud.app.ui.api.AuthServerAPI;
import com.ombp.cloud.app.ui.constants.UIURLConstants;

@Controller
public class LoginController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuthServerAPI authServerAPI;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest httpServletRequest) {
		
		ModelAndView modelAndView = new ModelAndView("pages/login");
		
		HttpSession httpSession = httpServletRequest.getSession(false);
		
		if (httpSession != null) {
			Object exception = httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

			if (exception != null && exception instanceof UsernameNotFoundException) {
				modelAndView.addObject("autherror", messageSource.getMessage("auth.error.usernmae.not.exists", null,
						LocaleContextHolder.getLocale()));
				httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			}
			else if(exception != null && exception instanceof BadCredentialsException)
			{
				modelAndView.addObject("autherror", messageSource.getMessage("auth.error.credentials.mismatch", null,
						LocaleContextHolder.getLocale()));
				httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			}
			else if(exception != null && exception instanceof ProviderNotFoundException)
			{
				modelAndView.addObject("autherror", messageSource.getMessage("auth.error.service.down", null,
						LocaleContextHolder.getLocale()));
				httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			}
		}
		
		String msg = httpServletRequest.getParameter("alogin");
		
		if(msg!=null && msg.length()!=0 && Boolean.valueOf(msg)) 
		{
			modelAndView.addObject("autherror", messageSource.getMessage("auth.error.logout.due.to.another.login", null,
					LocaleContextHolder.getLocale()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = UIURLConstants.BASE_URL + "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest httpServletRequest) {
		
		try {
			authServerAPI.remoteLogout();
		} catch (IOException e) {
			LOG.error("", e);
		}
		
		return "redirect:/logout";
	}
	
	/*@RequestMapping(value = "/app2", method = RequestMethod.GET)
	public ModelAndView login1(HttpServletRequest request, HttpServletResponse response) throws ServiceDownIOException {
		AuthenticatedUser user = null;
		ModelAndView modelAndView = new ModelAndView("pages/index");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		TLCAuthenticationToken sauth = (TLCAuthenticationToken) auth;
		
		user = sauth.getAuthenticatedUser();
		
		try {
			user = authServerAPI.getUserInfo();
		} 
		catch (IOException e) 
		{
			throw new ServiceDownIOException(e.getMessage());
		}
		
		System.out.println(user);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/app/{tenant}", method = RequestMethod.GET)
	public ModelAndView loginTenant(HttpServletRequest request, HttpServletResponse response, @PathVariable("tenant") String tenant) {
		return PageRouteingHelper.getFullPageSpecs(null, null);
	}*/
}
