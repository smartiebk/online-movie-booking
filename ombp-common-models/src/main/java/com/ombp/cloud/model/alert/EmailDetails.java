package com.ombp.cloud.model.alert;

import java.util.List;

public class EmailDetails {
	
	private String username; 
	
	private String fromEmailAddress; 
	
	private String password;
	
	private List<String> to; 
	
	private List<String> cc;
	
	private List<String> bcc;
	
	private String subject;
	
	private String message;
	
	private String attachmentFilePath;
	
	private String attachmentFileNameInEmail;
	
	private boolean isAttachmentPresent;
	
	private boolean status;
	
	private boolean onlyToNoCCBCC;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}

	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}

	public boolean isAttachmentPresent() {
		return isAttachmentPresent;
	}

	public void setAttachmentPresent(boolean isAttachmentPresent) {
		this.isAttachmentPresent = isAttachmentPresent;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAttachmentFileNameInEmail() {
		return attachmentFileNameInEmail;
	}

	public void setAttachmentFileNameInEmail(String attachmentFileNameInEmail) {
		this.attachmentFileNameInEmail = attachmentFileNameInEmail;
	}

	public boolean isOnlyToNoCCBCC() {
		return onlyToNoCCBCC;
	}

	public void setOnlyToNoCCBCC(boolean onlyToNoCCBCC) {
		this.onlyToNoCCBCC = onlyToNoCCBCC;
	}
	
}
