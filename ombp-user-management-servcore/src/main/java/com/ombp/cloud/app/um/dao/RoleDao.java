package com.ombp.cloud.app.um.dao;

import java.util.List;
import java.util.Map;

import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.model.usermanagement.Role;
import com.ombp.cloud.model.usermanagement.RoleType;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface RoleDao extends GenericDaoContract<Role, Integer>{

	List<Authority> getAllAuthoritiesByRoleId(Integer roleId);

	void saveRoleAuthorities(Map<String, Object> params);
	
	List<RoleType> fetchRoleTypes();
	
	void deleteRoleAuthorities(Map<String, Object> params);

	Role fetchRoleByRoleType(String roleTypeTAdmin);

	void removeActionFromRole(Map<String, Object> params);
	
	public void removeMActionFromRole(Map<String, Object> params);

}
