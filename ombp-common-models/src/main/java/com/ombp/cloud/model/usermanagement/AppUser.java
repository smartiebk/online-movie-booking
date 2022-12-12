package com.ombp.cloud.model.usermanagement;

import java.util.Collections;
import java.util.Set;

import com.ombp.cloud.model.BaseEntity;

public class AppUser extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244312494823824720L;
	private String username;
	private String email;
	private String mobileNumber;
	private String password;
	private int enabled;
	private int accountExpired;
	private int accountLocked;
	private int credentialsExpired;
	private Set<Authority> allowedActions;
	private String userGroupMappingSignUpCode;
	
	private AppUserDetails appUserDetails;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(int accountExpired) {
		this.accountExpired = accountExpired;
	}
	public int getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(int accountLocked) {
		this.accountLocked = accountLocked;
	}
	public int getCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(int credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public Set<Authority> getAllowedActions() {
		return allowedActions == null ? Collections.emptySet() : allowedActions;
	}
	public void setAllowedActions(Set<Authority> allowedActions) {
		this.allowedActions = allowedActions;
	}
	public AppUserDetails getAppUserDetails() {
		return appUserDetails;
	}
	public void setAppUserDetails(AppUserDetails appUserDetails) {
		this.appUserDetails = appUserDetails;
	}
	public String getUserGroupMappingSignUpCode() {
		return userGroupMappingSignUpCode;
	}
	public void setUserGroupMappingSignUpCode(String userGroupMappingSignUpCode) {
		this.userGroupMappingSignUpCode = userGroupMappingSignUpCode;
	}
	
}
