package com.ombp.cloud.app.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ombp.cloud.app.oauth2.models.CloudUser;
import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.constants.AspectConstants;
import com.ombp.cloud.context.ThreadLocalContext;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

@Aspect
@Configuration
public class AuthenticationInsertionAspect {

	private final String expression = AspectConstants.ASPECT_PREFIX + AspectConstants.PACKAGE_AS_END_POINT
			+ AspectConstants.ASPECT_SUFFIX;

	@Before(expression)
	public void before(JoinPoint joinPoint) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		CloudUser cuser = null;
		
		AuthenticatedUser authenticatedUser = null;
		
		if(auth.getPrincipal() instanceof CloudUser) 
		{
			cuser = (CloudUser) auth.getPrincipal();
			
			authenticatedUser = cuser.getAuthenticatedUser();
		}
		ThreadLocalContext.getCurrentThreadMap().put(AppConstant.THREAD_PREFIX_CURRENT_USER + String.valueOf(Thread.currentThread().getId()),
				authenticatedUser);
	}
	
	
	@After(expression)
	public void after(JoinPoint joinPoint) {
		
		ThreadLocalContext.flush();
		
	}
}