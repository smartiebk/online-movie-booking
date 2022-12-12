package com.ombp.cloud.app.ac.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.ac.dao.AppComponentDao;
import com.ombp.cloud.app.ac.service.AppComponentService;
import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.model.appcomponents.RealClientOrgPreference;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

@Service
public class AppComponentServiceImpl implements AppComponentService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppComponentDao appComponentDao;
	
	
	@Override
	public RealClientOrgPreference fetchClientOrgPref(AuthenticatedUser authenticatedUser, String realOrgClientId) {
		Map<String, Object> params = new HashMap<>();
		params.put(AppConstant.PARAM_USER_ID, authenticatedUser.getId());	
		params.put("realOrgClientId", realOrgClientId);
		return appComponentDao.fetchClientOrgPref(params);
	}


}
