package com.ombp.cloud.app.openendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.hm.service.AlertEventLogService;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.alerts.EmailLog;
import com.ombp.cloud.model.usermanagement.SMSLog;

@RestController
public class AlertLogEndPoint implements EndPointConstants {
	
	@Autowired
	private AlertEventLogService smsLogService;
	
	@RequestMapping(value = "/log/sms/save", method = RequestMethod.POST)
	public SMSLog logSMS(@RequestBody SMSLog smsLog) throws Exception {
		smsLogService.save(smsLog);
		return smsLog;
	}
	
	@RequestMapping(value = "/log/email/save", method = RequestMethod.POST)
	public EmailLog logEmail(@RequestBody EmailLog emailLog) throws Exception {
		smsLogService.saveEmailLog(emailLog);
		return emailLog;
	}
}