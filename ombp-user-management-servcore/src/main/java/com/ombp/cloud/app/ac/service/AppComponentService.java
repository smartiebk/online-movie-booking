package com.ombp.cloud.app.ac.service;

import com.ombp.cloud.model.appcomponents.RealClientOrgPreference;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

public interface AppComponentService {
	
	RealClientOrgPreference fetchClientOrgPref(AuthenticatedUser authenticatedUser, String realOrgClientId);
	
}
