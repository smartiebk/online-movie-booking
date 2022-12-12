package com.ombp.cloud.app.um.dao;

import com.ombp.cloud.model.usermanagement.AppLoginHistory;

public interface LoginHistoryDao {

	AppLoginHistory fetchAppLoginRecord(AppLoginHistory appLoginHistory);

	void insertAppLoginHistory(AppLoginHistory appLoginHistory);

	void updateAppLoginHistory(AppLoginHistory appLoginHistory);

}
