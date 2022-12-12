package com.ombp.cloud.app.ac.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.ac.dao.AppComponentDao;
import com.ombp.cloud.app.common.dao.impl.GenericDaoImpl;
import com.ombp.cloud.model.appcomponents.RealClientOrgPreference;

@Repository
public class AppComponentDaoImpl extends GenericDaoImpl<Integer, Integer> implements AppComponentDao   {

	@Override
	public RealClientOrgPreference fetchClientOrgPref(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		return getSession().selectOne(getStatementIdWithNamespace("fetchClientOrgPref"), params);
	}


}
