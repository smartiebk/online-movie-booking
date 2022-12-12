package com.ombp.cloud.app.um.dao;

import java.util.List;
import java.util.Map;

import com.ombp.cloud.model.usermanagement.AutoUserGroupAllocationToken;
import com.ombp.cloud.model.usermanagement.Role;
import com.ombp.cloud.model.usermanagement.UserGroup;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface UserGroupDao extends GenericDaoContract<UserGroup, Integer>{

	void saveUserGroupRoleMapping(Map<String, Object> params);
	
	void deleteUserGroupRoleMapping(Map<String, Object> params);

	void createAutoUserAddToUserGroupToken(AutoUserGroupAllocationToken token);

	Integer fetchUserGroupIdBySignUpCode(String signUpCode);

	void markSignUpCodeAsUsed(Integer userId, String signUpCode);

	void saveUserToUserGroupMapping(Integer userId, Map<String, Object> params);

	List<Role> getAllRolesByUGId(Integer realId);

	void removeRoleFromUserGroup(Map<String, Object> params);

	void removeMRoleFromUserGroup(Map<String, Object> params);
	
}
