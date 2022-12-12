package com.ombp.cloud.model.usermanagement;

import java.io.Serializable;
import java.util.Date;

public class LogoutInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6741853112787476194L;

	private String username;
	
	private String message;
	
	private Date logoutTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public LogoutInfo(String username, String message, Date logoutTime) {
		super();
		this.username = username;
		this.message = message;
		this.logoutTime = logoutTime;
	}

	public LogoutInfo() {
		super();
	}
	
}
