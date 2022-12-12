package com.ombp.cloud.app.oauth2.models;

import org.springframework.security.core.GrantedAuthority;

import com.ombp.cloud.model.usermanagement.Authority;

public class CloudAuthority implements GrantedAuthority {

	private Authority allowedAction;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4883436021148737020L;

	@Override
	public String getAuthority() {
		return getAllowedAction().getAuthority();
	}

	public Authority getAllowedAction() {
		return allowedAction;
	}

	public void setAllowedAction(Authority allowedAction) {
		this.allowedAction = allowedAction;
	}

	public CloudAuthority(Authority allowedAction) {
		super();
		this.allowedAction = allowedAction;
	}

}
