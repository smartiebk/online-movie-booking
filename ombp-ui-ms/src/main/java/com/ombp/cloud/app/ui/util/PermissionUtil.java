package com.ombp.cloud.app.ui.util;

import com.ombp.cloud.constants.PermissionConstants;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

public class PermissionUtil implements PermissionConstants {

	public static boolean hasAuthority(String action) {
		try {
			return SpringContextHelper.getAuthenticatedUser().isSuperAdmin() ? true :
					SpringContextHelper.getAuthenticatedUser().getAllowedAuthorities().contains(action);
		} catch (Exception e) {
			
		}
		return false;
	}

	public static boolean hasAuthority(AuthenticatedUser authenticatedUser, String action) {
		try {
			return authenticatedUser.isSuperAdmin() ? true :
				authenticatedUser.getAllowedAuthorities().contains(action);
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public static boolean canEdit(String entity) {
		try {
			return SpringContextHelper.getAuthenticatedUser().isSuperAdmin() ? true :
					SpringContextHelper.getAuthenticatedUser().getAllowedAuthorities().contains(ENTITY+_US_+entity+_US_+EDIT);
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public static boolean canDelete(String entity) {
		try {
			return SpringContextHelper.getAuthenticatedUser().isSuperAdmin() ? true :
					SpringContextHelper.getAuthenticatedUser().getAllowedAuthorities().contains(ENTITY+_US_+entity+_US_+DELETE);
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public static boolean canCreate(String entity) {
		try {
			return SpringContextHelper.getAuthenticatedUser().isSuperAdmin() ? true :
					SpringContextHelper.getAuthenticatedUser().getAllowedAuthorities().contains(ENTITY+_US_+entity+_US_+CREATE);
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public static boolean canConfigure(String entity) {
		try {
			return SpringContextHelper.getAuthenticatedUser().isSuperAdmin() ? true :
					SpringContextHelper.getAuthenticatedUser().getAllowedAuthorities().contains(ENTITY+_US_+entity+_US_+CONFIGURE);
		} catch (Exception e) {
			
		}
		return false;
	}
	
}
