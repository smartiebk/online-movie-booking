package com.ombp.cloud.app.ui.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;

import com.ombp.cloud.model.usermanagement.AuthenticatedUser;

public class StaticDataHolder {

	private static Map<String, Object> data;
	
	private static Map<String, String> tableLangFilesContent;
	
	private static JSONObject paginatedTableConfiguration;
	
	private static JSONObject paginatedTableCustomConfiguration;
	
	private static JSONObject staticDataValueFormConfiguration;
	
	private static String TIME_WHEN_VALUE_POPULATED = "_timeWhenPopulated";
	
	private static Set<String> latestCookieString;
	
	static
	{
		data = new LinkedHashMap<>();
		tableLangFilesContent = new HashMap<String, String>();
	}

	public static Object getData(String key) {
		AuthenticatedUser user = SpringContextHelper.getAuthenticatedUser();
		String prefix = user.getId() + "_" + user.getUsername();
		
		String dataKey = prefix+"_"+key;
		
		if(data.containsKey(prefix+TIME_WHEN_VALUE_POPULATED)) 
		{
			Date timeWhenValuesPopulated = data.get(prefix+TIME_WHEN_VALUE_POPULATED)!=null ?
					(Date) data.get(prefix+TIME_WHEN_VALUE_POPULATED) : null;
			
			if(timeWhenValuesPopulated!=null && new Date().after(DateUtils.addMinutes(timeWhenValuesPopulated, 180))) 
			{
				clearData();
			}
		}
		
		return data.get(dataKey);
	}
	
	public static void putData(String key, Object obj) {
		AuthenticatedUser user = SpringContextHelper.getAuthenticatedUser();
		
		String prefix = user.getId() + "_" + user.getUsername();
		
		String dataKey = prefix+"_"+key;
		
		if(!data.containsKey(prefix+TIME_WHEN_VALUE_POPULATED)) 
		{
			data.put(prefix+ TIME_WHEN_VALUE_POPULATED, new Date());
		}		

		data.put(dataKey, obj);
	}
	
	public static void clearData(AuthenticatedUser user) 
	{
		
		String prefix = user.getId() + "_" + user.getUsername();
		
		Set<String> keySet =  data.keySet();
		
		Iterator<String> iterator = keySet.iterator();
		
		while(iterator.hasNext()) 
		{
			String inKey = iterator.next();
			
			if(inKey.startsWith(prefix)) 
			{
				iterator.remove();
			}
		}
	}
	
	public static void clearData() 
	{
		AuthenticatedUser user = SpringContextHelper.getAuthenticatedUser();
		clearData(user);
	}

	public static Map<String, String> getTableLangFilesContent() {
		return tableLangFilesContent;
	}

	public static void setTableLangFilesContent(Map<String, String> tableLangFilesContent) {
		StaticDataHolder.tableLangFilesContent = tableLangFilesContent;
	}

	public static Set<String> getLatestCookieString() {
		return latestCookieString;
	}

	public static void setLatestCookieString(Set<String> latestCookieString) {
		StaticDataHolder.latestCookieString = latestCookieString;
	}

	public static JSONObject getPaginatedTableConfiguration() {
		return paginatedTableConfiguration;
	}

	public static void setPaginatedTableConfiguration(JSONObject paginatedTableConfiguration) {
		StaticDataHolder.paginatedTableConfiguration = paginatedTableConfiguration;
	}

	public static JSONObject getPaginatedTableCustomConfiguration() {
		return paginatedTableCustomConfiguration;
	}

	public static void setPaginatedTableCustomConfiguration(JSONObject paginatedTableCustomConfiguration) {
		StaticDataHolder.paginatedTableCustomConfiguration = paginatedTableCustomConfiguration;
	}

	public static JSONObject getStaticDataValueFormConfiguration() {
		return staticDataValueFormConfiguration;
	}

	public static void setStaticDataValueFormConfiguration(JSONObject staticDataValueFormConfiguration) {
		StaticDataHolder.staticDataValueFormConfiguration = staticDataValueFormConfiguration;
	}

}
