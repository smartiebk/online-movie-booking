/**
 * 
 */
package com.ombp.cloud.model.usermanagement;

import java.util.Set;

import com.ombp.cloud.model.BaseEntity;

/**
 * @author PranayKathade
 *
 */
public class UserGroup extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2425349076039357474L;

	private String name;
	
	private String details;
	
	private Set<Role> roles;
	
	private Integer tenantId;
	
	private boolean adminUserGroup;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public boolean isAdminUserGroup() {
		return adminUserGroup;
	}

	public void setAdminUserGroup(boolean adminUserGroup) {
		this.adminUserGroup = adminUserGroup;
	}
	
}
