package com.ombp.cloud.app.oauth2.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.model.usermanagement.Authority;

public class CloudUser implements UserDetails {
	
	private AppUser appUser;
	
	private AuthenticatedUser authenticatedUser;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7531637873028654341L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<CloudAuthority> authorities = new HashSet<>();
		Set<Authority> allowedActions = getAppUser().getAllowedActions();
		for(Authority autho : allowedActions) 
		{
			authorities.add(new CloudAuthority(autho));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return getAppUser().getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		return getAppUser().getAccountExpired() == 0 ? true : false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return getAppUser().getAccountLocked() == 0 ? true : false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return getAppUser().getCredentialsExpired() == 0 ? true : false;
	}

	@Override
	public boolean isEnabled() {
		return getAppUser().getEnabled() == 0 ? true : false;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public String getUsername() {
		return getAppUser().getUsername();
	}

	public CloudUser(AppUser appUser) {
		super();
		this.appUser = appUser;
	}

	public AuthenticatedUser getAuthenticatedUser() {
		Set<String> allowedActions = new HashSet<>();
		if(this.getAppUser().getAllowedActions()!=null && !this.getAppUser().getAllowedActions().isEmpty()) 
		{
			for(Authority a : this.getAppUser().getAllowedActions()) 
			{
				allowedActions.add(a.getAuthority());
			}
		}
		
		authenticatedUser = new AuthenticatedUser(this.getAppUser().getId(), this.getAppUser().getUsername(),
				this.getAppUser().getEmail(), this.getAppUser().getMobileNumber(), true, allowedActions, this.getAppUser().getAppUserDetails());
		
		
		return authenticatedUser;
	}
	
}
