package com.ombp.cloud.app.ui.auth;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.ombp.cloud.model.usermanagement.AccessToken;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

public class TLCAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1771017139195338157L;
	
	private AccessToken accessToken;
	
	private AuthenticatedUser authenticatedUser;

	public TLCAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public TLCAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, AccessToken accessToken, AuthenticatedUser authenticatedUser) {
		super(principal, credentials, authorities);
		this.accessToken = accessToken;
		this.authenticatedUser = authenticatedUser;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public AuthenticatedUser getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(AuthenticatedUser authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	
}
