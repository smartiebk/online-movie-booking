package com.ombp.cloud.app.um.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ombp.cloud.app.um.dao.UserDao;
import com.ombp.cloud.app.um.service.UserService;
import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.context.ThreadLocalContext;
import com.ombp.cloud.model.paging.Page;
import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.model.usermanagement.UserGroup;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public AppUser fetchUserByUsername(String username) {
		
		AppUser appUser = userDao.fetchUserByUsername(username);
		
		List<com.ombp.cloud.model.usermanagement.Authority> authorities = new ArrayList<>();
		
		if(appUser!=null) 
		{
			authorities = userDao.getAuthoritiesForUser(appUser.getId());
			
			appUser.setAllowedActions(new HashSet<>(authorities));
		}
		return appUser;
	}

	@Override
	public boolean isUserExistsWithSameParams(AppUser appUser) {
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("username", appUser.getUsername());
		params.put("mobile", appUser.getMobileNumber());
		params.put("email", appUser.getEmail());
		
		int noOfUsersWithSameParams = userDao.existsWithParameters(params);
		return noOfUsersWithSameParams > 0 ? true : false;
	}

	@Override
	public AppUser saveUser(AppUser appUser) {
		Date currentTime = new Date();
		appUser.setUpdateTime(currentTime);
		
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) ThreadLocalContext.getCurrentThreadMap()
				.get(AppConstant.THREAD_PREFIX_CURRENT_USER + String.valueOf(Thread.currentThread().getId()));
		
		appUser.setUserId(authenticatedUser.getId());
		
		if(appUser!=null && appUser.getId()==null) 
		{
			appUser.setCreateTime(currentTime);
			userDao.insert(appUser);
			appUser.getAppUserDetails().setAppUserId(appUser.getId());
			userDao.insertUserDetails(appUser.getAppUserDetails());
			/*if(appUser.getUserGroupMappingSignUpCode()!=null) 
			{
				saveUserToUserGroupMapping(appUser.getId(), null, appUser.getUserGroupMappingSignUpCode());
			}*/
		}
		else
		{
			userDao.update(appUser);
			userDao.updateUserDetails(appUser.getAppUserDetails());
		}
		return appUser;
	}

	@Override
	public boolean deleteUser(AppUser appUser) {
		appUser.setIsDeleted(2);
		int updated = userDao.update(appUser);
		return updated == 0 ? false : true;
	}

	@Override
	public Page<AppUser> fetchAllUsers(Map<String, Object> params) {
		Page<AppUser> allUsers= userDao.fetchWithPageSearchSort(params);
		return allUsers;
	}

	@Override
	public AppUser fetchUserById(Integer id) {
		return userDao.fetchById(id);
	}

	@Override
	public boolean containsAnyUnAuthorizedEntityId(List<Integer> list) {
		return userDao.containsAnyUnAuthorizedEntityId(list);
	}

	@Override
	public void deleteSelected(List<Integer> selectedIds, AuthenticatedUser authenticatedUser) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("selectedIds", selectedIds);
		userDao.logicalDeleteSelected(params);	
	}

	@Override
	public List<UserGroup> getAllUserGroupsByUserId(Integer realId) {
		return userDao.getAllUserGroupsByUserId(realId);
	}

	@Override
	public void removeUserGroupFromUser(Integer userId, Integer userGroupId) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("userGroupId", userGroupId);
		userDao.removeUserGroupFromUser(params);
	}

	@Override
	public void saveUserGroupToUser(Integer ugIdInt, Set convertedIntegerSet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Authority> getAuthoritiesForUser(Integer userId) {
		return userDao.getAuthoritiesForUser(userId);
	}

	@Override
	public AppUser updateUserPassword(AppUser appUser) {
		userDao.auditAppUserBeforePasswordChange(appUser.getId());
		userDao.updateUserPassword(appUser);
		return appUser;
	}

	@Override
	public void saveUserInfoFromProfile(AppUser appUser) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("email", appUser.getEmail());
		params.put("mobileNumber", appUser.getMobileNumber());
		
		if(appUser.getAppUserDetails()!=null) 
		{
			params.put("alternateMobileNumber", appUser.getAppUserDetails().getAltMobile());
			params.put("firstName", appUser.getAppUserDetails().getFirstName());
			params.put("lastName", appUser.getAppUserDetails().getLastName());
			params.put("address", appUser.getAppUserDetails().getAddress());
			params.put("city", appUser.getAppUserDetails().getCity());
			params.put("state", appUser.getAppUserDetails().getState());
			params.put("dob", appUser.getAppUserDetails().getDateOfBirth());
		}
				
		userDao.saveUserInfoFromProfile(params);
	}
	
}
