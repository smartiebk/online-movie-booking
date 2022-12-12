package com.ombp.cloud.app.endpoint.um;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.oauth2.models.CloudUser;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

@RestController
public class UserInfoEndPoint implements EndPointConstants {

	@RequestMapping(value = USER_INFO, method = RequestMethod.GET)
	public AuthenticatedUser getUserInfo(OAuth2Authentication authentication) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CloudUser cuser = null;

		if (auth.getPrincipal() instanceof CloudUser) {
			cuser = (CloudUser) auth.getPrincipal();
		}

		return 
				cuser.getAuthenticatedUser();
	}

}
