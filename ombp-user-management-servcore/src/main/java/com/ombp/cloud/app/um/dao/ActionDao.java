package com.ombp.cloud.app.um.dao;

import java.util.List;

import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface ActionDao extends GenericDaoContract<Authority, Integer>{
	
	List<Authority> listActionsForSuperAdmin(AuthenticatedUser authenticatedUser);
	
	List<Authority> listActionsForUser(AuthenticatedUser authenticatedUser);

}
