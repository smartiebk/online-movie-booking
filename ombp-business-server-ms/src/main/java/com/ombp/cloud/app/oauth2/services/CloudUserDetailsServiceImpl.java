package com.ombp.cloud.app.oauth2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.cache.TLCCache;
import com.ombp.cloud.app.oauth2.models.CloudUser;
import com.ombp.cloud.app.um.service.UserService;
import com.ombp.cloud.model.usermanagement.AppUser;

@Service
@Primary
public class CloudUserDetailsServiceImpl implements UserDetailsService 
{

	@Autowired
	private UserService userService;
	
	@Autowired
	private TLCCache cache;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CloudUser cacheUser = cache.getUser(username);
		
		if(cacheUser == null) 
		{
			AppUser appuser = userService.fetchUserByUsername(username);
			
			if(appuser!=null) {
			cacheUser = new CloudUser(appuser);
			
			cache.putUser(username, cacheUser);
			
			}
		}
		
		return cacheUser;
	}

}
