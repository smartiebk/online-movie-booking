package com.ombp.cloud.app.ui.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.ombp.cloud.app.ui.api.OpenAuthServerAPI;
import com.ombp.cloud.app.ui.constants.AlertEventConstants;
import com.ombp.cloud.model.alerts.AlertEvent;
import com.ombp.cloud.model.usermanagement.SMSLog;

@Service
public class SendSMSService implements AlertEventConstants {

	@Value("${sms.provider.url}")
	private String providerUrl;
	
	@Value("${sms.auth.key}")
	private String authKey;	
	
	@Autowired
	private OpenAuthServerAPI openAuthServerAPI;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public boolean sendSMS(Map<String, String> data, String sendTo, String smsType, boolean addSenderText) 
	{
		String sms = prepareSMS(data, null, false);
		
		boolean	result = send(sms, sendTo, smsType);
		
		return result;
	}
	
	private String prepareSMS(Map<String, String> data, AlertEvent alertEvent, boolean addSenderText) {
		
		if(addSenderText) {
			data.put("senderText", getSenderText(alertEvent.getSenderText()));
		}
		
		List<String> placeHolders = new ArrayList<>();
		
		for(String key : data.keySet()) {
			placeHolders.add(data.get(key));
		}
		
		String sms = MessageFormat.format(alertEvent.getTemplate(), placeHolders.toArray());
		
		return sms;
	}

	private String getSenderText(String senderText) {
		if(senderText!=null && senderText.trim().length()!=0) {
			return senderText;
		}
		return "";
	}
	
	private boolean send(String sms, String sendTo, String smsType) {
		boolean result = false;
		
		try {
		URI uri = null;
		
		sms = sms.replaceAll("\\\\n", "\n");
		
		String url = providerUrl + "?authorization=" + authKey + "&route=v3&sender_id=TXTIND&message="
				+ UriUtils.encode(sms, "UTF-8") + "&language=english&flash=0&numbers=" + sendTo;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			LOG.error("", e);
		}
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri, String.class); 
		
		if(response.contains("SMS sent successfully")) 
		{
			result = true;
		}
		
		SMSLog smsLog = new SMSLog();
		smsLog.setApiUrl(url);
		smsLog.setSms(sms);
		smsLog.setSentTo(sendTo);
		smsLog.setSmsType(smsType);
		smsLog.setStatus(String.valueOf(result));
		
			try {
				openAuthServerAPI.logSMS(smsLog);
			} catch (IOException e) {
				LOG.error("", e);
			}
		} catch (Exception exception) {
			LOG.error("error in sending SMS", exception);
		}
		return result;
	}
	
}
