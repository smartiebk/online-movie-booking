package com.ombp.cloud.app.ui.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.ombp.cloud.model.ui.ResponseData;

public class GeneralUtil {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	
	public static ResponseData validResponse(Object obj) 
	{
		return new ResponseData(obj!=null ? SUCCESS : FAILED);
	}
	
	public static String getDateInStringWithoutFormat(Date date) 
	{
		Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    String year = String.valueOf(calendar.get(Calendar.YEAR));
	    String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
	    String day = String.valueOf(calendar.get(Calendar.DATE));
	    String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	    String minuite = String.valueOf(calendar.get(Calendar.MINUTE));
	    
	    return "" + day + "-" + month + "-" +  year + "-" + hour +  "-" + minuite;
	}
	
	public static ResponseData customResponse(Object obj) 
	{
		ResponseData data =new ResponseData();
		Map<String, Object> d = new HashMap<>();
		d.put("savedObj", obj);
		data.setData(d);
		return data;
	}
	
}
