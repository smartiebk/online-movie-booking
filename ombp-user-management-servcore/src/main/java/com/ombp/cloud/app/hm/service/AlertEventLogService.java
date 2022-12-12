package com.ombp.cloud.app.hm.service;

import com.ombp.cloud.model.alerts.EmailLog;
import com.ombp.cloud.model.usermanagement.SMSLog;

public interface AlertEventLogService {

	SMSLog save(SMSLog smsLog);
	
	EmailLog saveEmailLog(EmailLog emailLog);
	
}

