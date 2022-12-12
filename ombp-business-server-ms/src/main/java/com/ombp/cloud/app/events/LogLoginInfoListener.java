package com.ombp.cloud.app.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.um.service.LoginHistoryService;

@Service
public class LogLoginInfoListener {

	@Autowired
	private LoginHistoryService loginHistoryService;
	
	@Async
	@EventListener
	public void processLogLoginInfoEvent(LogLoginInfoEvent event) {
		loginHistoryService.insertAppLoginHistory(event.getAppLoginHistory());
	}

}
