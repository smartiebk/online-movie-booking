package com.ombp.cloud.app.um.service;

import com.ombp.cloud.model.usermanagement.AppLoginHistory;

public interface LoginHistoryService {

	AppLoginHistory fetchAppLoginRecord(AppLoginHistory appLoginHistory);
	
	void insertAppLoginHistory(AppLoginHistory appLoginHistory);
	
	void updateAppLoginHistory(AppLoginHistory appLoginHistory);
}
