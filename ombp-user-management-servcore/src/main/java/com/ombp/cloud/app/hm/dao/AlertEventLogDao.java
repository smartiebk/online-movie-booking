package com.ombp.cloud.app.hm.dao;

import com.ombp.cloud.model.alerts.EmailLog;
import com.ombp.cloud.model.usermanagement.SMSLog;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface AlertEventLogDao extends GenericDaoContract<SMSLog, Integer>{

	void insertEmailLog(EmailLog emailLog);

}
