/**
 * 
 */
package com.ombp.cloud.model.usermanagement;

import java.util.Set;

import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.model.BaseEntity;

/**
 * @author PranayKathade
 *
 */
public class AuthenticatedUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -836374959648175605L;
	private String username;
	private String email;
	private String mobileNumber;
	private boolean superAdmin;
	
	private Set<String> allowedAuthorities;
	
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

	public boolean isSuperAdmin() {
		return this.getAllowedAuthorities().contains(AppConstant.PERMISSION_SUPER_ADMIN);
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	public Set<String> getAllowedAuthorities() {
		return allowedAuthorities;
	}

	public void setAllowedAuthorities(Set<String> allowedAuthorities) {
		this.allowedAuthorities = allowedAuthorities;
	}

	public AuthenticatedUser(Integer userId, String username, String email, String mobileNumber, boolean superAdmin,
			Set<String> allowedAuthorities, AppUserDetails appUserDetails) {
		super();
		super.setId(userId);
		this.username = username;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.superAdmin = superAdmin;
		this.allowedAuthorities = allowedAuthorities;
		this.appUserDetails = appUserDetails;
	}

	public AuthenticatedUser() {
		super();
	}

	public AppUserDetails getAppUserDetails() {
		return appUserDetails;
	}

	public void setAppUserDetails(AppUserDetails appUserDetails) {
		this.appUserDetails = appUserDetails;
	}

}
