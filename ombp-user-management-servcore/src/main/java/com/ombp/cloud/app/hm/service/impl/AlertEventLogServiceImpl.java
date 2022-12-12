package com.ombp.cloud.app.hm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.hm.dao.AlertEventLogDao;
import com.ombp.cloud.app.hm.service.AlertEventLogService;
import com.ombp.cloud.model.alerts.EmailLog;
import com.ombp.cloud.model.usermanagement.SMSLog;
@Service
public class AlertEventLogServiceImpl implements AlertEventLogService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AlertEventLogDao alertEventLogDao;

	@Override
	public SMSLog save(SMSLog smsLog) {
		alertEventLogDao.insert(smsLog);
		return smsLog;
	}

	@Override
	public EmailLog saveEmailLog(EmailLog emailLog) {
		alertEventLogDao.insertEmailLog(emailLog);
		return emailLog;
	}

}
