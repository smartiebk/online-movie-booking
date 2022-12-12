package com.ombp.cloud.app.um.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ombp.cloud.app.um.dao.LoginHistoryDao;
import com.ombp.cloud.app.um.service.LoginHistoryService;
import com.ombp.cloud.model.usermanagement.AppLoginHistory;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LoginHistoryServiceImpl implements LoginHistoryService {
	
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	
	@Override
	public AppLoginHistory fetchAppLoginRecord(AppLoginHistory appLoginHistory) {
		return loginHistoryDao.fetchAppLoginRecord(appLoginHistory);
	}

	@Override
	public void insertAppLoginHistory(AppLoginHistory appLoginHistory) {
		loginHistoryDao.insertAppLoginHistory(appLoginHistory);
	}

	@Override
	public void updateAppLoginHistory(AppLoginHistory appLoginHistory) {
		// TODO Auto-generated method stub
		
	}



}
