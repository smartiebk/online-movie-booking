package com.ombp.cloud.app.um.dao;

import java.util.List;
import java.util.Map;

import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.AppUserDetails;
import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.model.usermanagement.UserGroup;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface UserDao extends GenericDaoContract<AppUser, Integer>{
	
    AppUser fetchUserByUsername(String username);
	
	List<Authority> getAuthoritiesForUser(Integer userId);

	List<UserGroup> getAllUserGroupsByUserId(Integer realId);

	void removeUserGroupFromUser(Map<String, Object> params);

	void insertUserDetails(AppUserDetails appUserDetails);

	void updateUserDetails(AppUserDetails appUserDetails);

	void updateUserPassword(AppUser appUser);

	void auditAppUserBeforePasswordChange(Integer id);

	void saveUserInfoFromProfile(Map<String, Object> params);

}
