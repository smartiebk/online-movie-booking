package com.ombp.cloud.app.common.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

public class DataParameterMap {

	public static Map<String, Object> getUserParams(AuthenticatedUser authenticatedUser)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currentUserId", authenticatedUser!=null ? authenticatedUser.getId() : -1);
		params.put("isSuperAdmin", authenticatedUser!=null ? authenticatedUser.isSuperAdmin() : false);
		
		return params;
	}
}
