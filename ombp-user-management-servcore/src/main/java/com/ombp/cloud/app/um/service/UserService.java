package com.ombp.cloud.app.um.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ombp.cloud.model.paging.Page;
import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.model.usermanagement.UserGroup;

public interface UserService {
	
	AppUser fetchUserByUsername(String username);
	
	AppUser fetchUserById(Integer id);
	
	boolean isUserExistsWithSameParams(AppUser appUser);
	
	AppUser saveUser(AppUser appUser);
	
	boolean deleteUser(AppUser appUser);
	
	Page<AppUser> fetchAllUsers(Map<String, Object> params);

	boolean containsAnyUnAuthorizedEntityId(List<Integer> list);

	void deleteSelected(List<Integer> selectedIds, AuthenticatedUser authenticatedUser);

	List<UserGroup> getAllUserGroupsByUserId(Integer realId);

	void removeUserGroupFromUser(Integer userId, Integer userGroupId);

	void saveUserGroupToUser(Integer ugIdInt, Set convertedIntegerSet);
	
	List<Authority> getAuthoritiesForUser(Integer userId);
	
	AppUser updateUserPassword(AppUser appUser);

	void saveUserInfoFromProfile(AppUser appUser);
}
