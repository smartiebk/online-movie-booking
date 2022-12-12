/**
 * 
 */
package com.ombp.cloud.model.usermanagement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ombp.cloud.model.BaseEntity;

/**
 * @author PranayKathade
 *
 */
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8422369653795790198L;

	private String name;

	private String details;

	private Set<Authority> authorities;

	private List<String> authorityIds;

	private Integer roleTypeId;
	
	private RoleType roleType;
	
	private String roleTypeStr;

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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<String> getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(List<String> authorityIds) {
		this.authorityIds = authorityIds;
	}

	public Integer getRoleTypeId() {
		return roleTypeId;
	}

	public void setRoleTypeId(Integer roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	public Set<Integer> getAuthorityIdsi() {
		
		return authorityIds!=null ? new HashSet<>(authorityIds.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList())) : null;
	}

	public Role(String name, String details, Set<Authority> authorities, List<String> authorityIds,
			Integer roleTypeId) {
		super();
		this.name = name;
		this.details = details;
		this.authorities = authorities;
		this.authorityIds = authorityIds;
		this.roleTypeId = roleTypeId;
	}

	public Role() {
		super();
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public String getRoleTypeStr() {
		return roleTypeStr;
	}

	public void setRoleTypeStr(String roleTypeStr) {
		this.roleTypeStr = roleTypeStr;
	}

	
}
