package com.ombp.cloud.app.startup;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ombp.cloud.app.common.dao.AppInitializerDao;
import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.context.LocalApplicationContext;

@Component
@Order(0)
public class AppInitializer implements ApplicationListener<ApplicationReadyEvent>, AppConstant {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppInitializerDao appInitializerDao;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		initializeMethodSecurityACL();
		
	}

	private void initializeMethodSecurityACL() {
		logger.info("Initializing method security ACLs for application....");
		
		Map<String, Set<String>> methodAcls = appInitializerDao.fetchMethodSecurityACLs(SERVICE_NAME_AUTH);
		
		LocalApplicationContext.getMethodSecurityACLs().putAll(methodAcls);
		
		logger.info("Initialized method security ACLs for application.");
	}

}
