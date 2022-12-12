package com.ombp.cloud.app.ac.dao;

import java.util.Map;

import com.ombp.cloud.model.appcomponents.RealClientOrgPreference;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface AppComponentDao extends GenericDaoContract<Integer, Integer> {
	
	RealClientOrgPreference fetchClientOrgPref(Map<String, Object> params	);

}
