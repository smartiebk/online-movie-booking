package com.ombp.cloud.app.ui.cloudutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProperties {

	private static String fromEmailId;
	
	private static String fromEmailPassword;
	
	private static String ccEmailId;

	public static String getFromEmailId() {
		return fromEmailId;
	}
	
	public static String getFromEmailPassword() {
		return fromEmailPassword;
	}
	
	public static String getCcEmailId() {
		return ccEmailId;
	}

	@Value("${org.email.from.emailid}")
	public void setFromEmailId(String fromEmailId) {
		EmailProperties.fromEmailId = fromEmailId;
	}
	
	@Value("${org.email.from.emailpassword}")
	public void setFromEmailPassword(String fromEmailPassword) {
		EmailProperties.fromEmailPassword = fromEmailPassword;
	}

	@Value("${org.email.cc}")
	public void setCcEmailId(String ccEmailId) {
		EmailProperties.ccEmailId = ccEmailId;
	}
	
}
