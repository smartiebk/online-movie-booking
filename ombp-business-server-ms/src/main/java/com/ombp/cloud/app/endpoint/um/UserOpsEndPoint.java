package com.ombp.cloud.app.endpoint.um;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.um.service.UserService;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.constants.ResponseConstants;
import com.ombp.cloud.model.ui.ResponseData;
import com.ombp.cloud.model.usermanagement.AppUser;

@RestController
public class UserOpsEndPoint implements EndPointConstants, ResponseConstants {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder userPasswordEncoder;
	

	@RequestMapping(value = SIGN_UP_BY_LOGGED_IN_USER, method = RequestMethod.POST, consumes = "application/json")
	public Object saveSignedUpUser(HttpServletRequest request, @RequestBody AppUser appUser) 
	{
		//if user is logged in and has permission to create user then only allow user creation without tenant_id
		
		if(userService.isUserExistsWithSameParams(appUser)) 
		{
			return new ResponseData("User with same username or email or mobile already exists.");
		}
		appUser.setPassword(userPasswordEncoder.encode(appUser.getPassword()));
		userService.saveUser(appUser);
		
		return appUser;
	}
	
	
}
