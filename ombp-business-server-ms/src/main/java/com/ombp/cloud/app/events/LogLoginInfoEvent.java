package com.ombp.cloud.app.events;

import com.ombp.cloud.model.usermanagement.AppLoginHistory;

public class LogLoginInfoEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4627302238276027448L;

	private AppLoginHistory appLoginHistory;

	public AppLoginHistory getAppLoginHistory() {
		return appLoginHistory;
	}

	public void setAppLoginHistory(AppLoginHistory appLoginHistory) {
		this.appLoginHistory = appLoginHistory;
	}

	public LogLoginInfoEvent(AppLoginHistory appLoginHistory) {
		super();
		this.appLoginHistory = appLoginHistory;
	}
	
}
