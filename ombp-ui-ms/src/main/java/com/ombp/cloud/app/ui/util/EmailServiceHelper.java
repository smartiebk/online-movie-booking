package com.ombp.cloud.app.ui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.ui.api.AuthServerAPI;

@Service
public class EmailServiceHelper {
	
	private static Logger LOG = LoggerFactory.getLogger(EmailServiceHelper.class);
	
	@Autowired
	private AuthServerAPI authServerAPI;
	
	private String EMAIL_SUBJECT_PLACEHOLDERS = "SUBJECT_PLACEHOLDERS";
	
	
}
