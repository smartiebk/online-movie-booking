package com.ombp.cloud.app.um.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.common.dao.impl.GenericDaoImpl;
import com.ombp.cloud.app.um.dao.UserDao;
import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.AppUserDetails;
import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.model.usermanagement.UserGroup;

@Repository
public class UserDaoImpl extends GenericDaoImpl<AppUser, Integer> implements UserDao   {

	
	private final String NAMESPACE = this.getClass().getName();

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSession() {
		return this.sqlSession;
	}

	public String getStatementIdWithNamespace(String statementId) {
		return NAMESPACE + "." + statementId;
	}
	
	public UserDaoImpl() {
		super();
	}

	@Override
	public AppUser fetchUserByUsername(String username) {
		return getSession().selectOne(getStatementIdWithNamespace("fetchUserByUsername"), username);
	}

	@Override
	public List<Authority> getAuthoritiesForUser(Integer userId) {
		return getSession().selectList(getStatementIdWithNamespace("loadUserAuthorities"), userId);
	}

	@Override
	public List<UserGroup> getAllUserGroupsByUserId(Integer realId) {
		return getSession().selectList(getStatementIdWithNamespace("getAllUserGroupsByUserId"), realId);
	}

	@Override
	public void removeUserGroupFromUser(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		 getSession().update(getStatementIdWithNamespace("removeUserGroupFromUser"), params);
	}

	@Override
	public void insertUserDetails(AppUserDetails appUserDetails) {
		getSession().insert(getStatementIdWithNamespace("insertUserDetails"), appUserDetails);
	}

	@Override
	public void updateUserDetails(AppUserDetails appUserDetails) {
		getSession().update(getStatementIdWithNamespace("updateUserDetails"), appUserDetails);
	}

	@Override
	public void updateUserPassword(AppUser appUser) {
		getSession().update(getStatementIdWithNamespace("updateUserPassword"), appUser);
	}

	@Override
	public void auditAppUserBeforePasswordChange(Integer id) {
		getSession().insert(getStatementIdWithNamespace("auditAppUserBeforePasswordChange"), id);
	}

	@Override
	public void saveUserInfoFromProfile(Map<String, Object> params) {
		params.putAll(getCurrrentUserParameterMap());
		getSession().update(getStatementIdWithNamespace("saveUserInfoFromProfile"), params);
	}


}
