package com.ombp.cloud.model.alerts;

import com.ombp.cloud.model.BaseEntity;

public class AlertEvent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7607336521171204059L;
	
	private String alertType;
	
	private String eventTypeConstant;
	
	private String dbLable;
	
	private String keyLable;
	
	private String subject;
	
	private String template;
	
	private String isUserSpecific;
	
	private String isSubScribedByCurrentUser;
	
	private String senderText;
	
	public String getIsSubScribedByCurrentUser() {
		return isSubScribedByCurrentUser;
	}

	public void setIsSubScribedByCurrentUser(String isSubScribedByCurrentUser) {
		this.isSubScribedByCurrentUser = isSubScribedByCurrentUser;
	}

	public String getIsUserSpecific() {
		return isUserSpecific;
	}

	public void setIsUserSpecific(String isUserSpecific) {
		this.isUserSpecific = isUserSpecific;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getEventTypeConstant() {
		return eventTypeConstant;
	}

	public void setEventTypeConstant(String eventTypeConstant) {
		this.eventTypeConstant = eventTypeConstant;
	}

	public String getDbLable() {
		return dbLable;
	}

	public void setDbLable(String dbLable) {
		this.dbLable = dbLable;
	}

	public String getKeyLable() {
		return keyLable;
	}

	public void setKeyLable(String keyLable) {
		this.keyLable = keyLable;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSenderText() {
		return senderText;
	}

	public void setSenderText(String senderText) {
		this.senderText = senderText;
	}
}
