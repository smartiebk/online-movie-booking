package com.ombp.cloud.app.ui.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.ui.api.OpenAuthServerAPI;
import com.ombp.cloud.app.ui.cloudutils.NotifyEvents;
import com.ombp.cloud.app.ui.constants.AlertEventConstants;
import com.ombp.cloud.model.alert.EmailDetails;
import com.ombp.cloud.model.alerts.AlertEvent;
import com.ombp.cloud.model.alerts.EmailLog;

@Service
public class SendEmailService implements AlertEventConstants {

	@Autowired
	private OpenAuthServerAPI openAuthServerAPI;

	@Value("${email.provider.username}")
	private String emailProviderUsername;
	
	@Value("${email.provider.password}")
	private String emailProviderPassword;
	
	@Value("${email.provider.default.to.address}")
	private String emailProviderDefaultToAddress;
	
	@Value("${email.provider.default.from.address}")
	private String emailProviderDefaultFromAddress;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public boolean sendEmail(Map<String, Object> data, String emailType, EmailDetails emailDetails) 
	{
		boolean result = NotifyEvents.trySendingEmail(3, emailDetails);
		
		logEmail(emailDetails, emailType, result);
		
		return result;
	}

	private String prepareEmailSubject(Map<String, Object> data, AlertEvent alertEvent) {
		
		Object[] placeHolderValues = (Object[]) data.get("SUBJECT_PLACEHOLDERS");
		
		return MessageFormat.format(alertEvent.getSubject(), placeHolderValues);
	}

	private void logEmail(EmailDetails emailDetails, String emailType, boolean result) {
		EmailLog emailLog = new EmailLog();
		emailLog.setSentTo(emailDetails.getTo()!=null ? String.join(";", emailDetails.getTo()) : "");
		emailLog.setSentCc(emailDetails.getCc()!=null ? String.join(";", emailDetails.getCc()) : "");
		emailLog.setSentBcc(emailDetails.getBcc()!=null ? String.join(";", emailDetails.getBcc()) : "");
		emailLog.setEmailSubject(emailDetails.getSubject());
		emailLog.setEmailContent(emailDetails.getMessage());
		emailLog.setEmailType(emailType);
		emailLog.setEmailFrom(emailDetails.getFromEmailAddress());
		emailLog.setStatus(String.valueOf(result));
		emailLog.setIsAttchmentPresent(String.valueOf(emailDetails.getAttachmentFileNameInEmail()!=null && emailDetails.getAttachmentFileNameInEmail().trim().length()!=0));
		emailLog.setAttachmentFileNames(emailDetails.getAttachmentFileNameInEmail());
		
			try {
				openAuthServerAPI.logEmail(emailLog);
			} catch (IOException e) {
				LOG.error("", e);
			}
	}
	
}
