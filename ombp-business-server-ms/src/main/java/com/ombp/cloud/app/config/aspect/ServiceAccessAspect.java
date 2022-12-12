package com.ombp.cloud.app.config.aspect;

import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;

import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.constants.AspectConstants;
import com.ombp.cloud.context.LocalApplicationContext;
import com.ombp.cloud.context.ThreadLocalContext;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

@Aspect
@Configuration
public class ServiceAccessAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String expression = AspectConstants.ASPECT_PREFIX + AspectConstants.PACKAGE_UM + AspectConstants.ASPECT_SUFFIX;

	@Before(expression)
	public void before(JoinPoint joinPoint) {
		
		AuthenticatedUser user = (AuthenticatedUser) ThreadLocalContext.getCurrentThreadMap()
				.get(AppConstant.THREAD_PREFIX_CURRENT_USER + String.valueOf(Thread.currentThread().getId()));
		
		Set<String> macl = LocalApplicationContext.getMethodSecurityACLs().get(joinPoint.getSignature());
		if (macl != null) {
			if (user != null) {
				if (user.getAllowedAuthorities().contains(AppConstant.PERMISSION_SUPER_ADMIN)
						|| user.getAllowedAuthorities().containsAll(macl)) {
					logger.info("User {0} is allowed to access {1}", user.getUsername(), joinPoint.getSignature());
				} else {
					throw new AccessDeniedException("User " + user.getUsername()
							+ " does not have access to method :::: " + joinPoint.getSignature());
				}
			}
		}
	}
}