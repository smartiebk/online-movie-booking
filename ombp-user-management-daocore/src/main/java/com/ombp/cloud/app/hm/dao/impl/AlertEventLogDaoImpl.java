package com.ombp.cloud.app.hm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.common.dao.impl.GenericDaoImpl;
import com.ombp.cloud.app.hm.dao.AlertEventLogDao;
import com.ombp.cloud.model.alerts.EmailLog;
import com.ombp.cloud.model.usermanagement.SMSLog;

@Repository
public class AlertEventLogDaoImpl extends GenericDaoImpl<SMSLog, Integer> implements AlertEventLogDao   {

	@Override
	public void insertEmailLog(EmailLog emailLog) {
		getSession().insert("insertEmailLog", emailLog);
	}

}
