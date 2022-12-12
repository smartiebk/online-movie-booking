package com.ombp.cloud.model.alerts;

import com.ombp.cloud.model.BaseEntity;

public class EmailLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8033995745002014809L;

	private String sentTo;
	
	private String sentCc;
	
	private String sentBcc;
	
	private String emailSubject;
	
	private String emailContent;
	
	private String emailType;
	
	private String emailFrom;
	
	private String isAttchmentPresent;
	
	private String attachmentFileNames;
	
	private String status;
	
	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getSentCc() {
		return sentCc;
	}

	public void setSentCc(String sentCc) {
		this.sentCc = sentCc;
	}

	public String getSentBcc() {
		return sentBcc;
	}

	public void setSentBcc(String sentBcc) {
		this.sentBcc = sentBcc;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getIsAttchmentPresent() {
		return isAttchmentPresent;
	}

	public void setIsAttchmentPresent(String isAttchmentPresent) {
		this.isAttchmentPresent = isAttchmentPresent;
	}

	public String getAttachmentFileNames() {
		return attachmentFileNames;
	}

	public void setAttachmentFileNames(String attachmentFileNames) {
		this.attachmentFileNames = attachmentFileNames;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
