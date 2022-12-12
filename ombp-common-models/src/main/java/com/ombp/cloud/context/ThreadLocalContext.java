package com.ombp.cloud.context;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalContext {

	private static ThreadLocal<Map<String, Object>> map = new ThreadLocal<>();
	
	private static Map<String, Object> localMap = new HashMap<String, Object>();
	
	static
	{
		map.set(localMap);
	}
	
	public static Map<String, Object> getCurrentThreadMap() 
	{
		localMap = map.get();
		if (localMap == null)
		{
			localMap = new HashMap<>();
			map.set(localMap);
		}
		
		return map.get();
	}
	
	public static void flush() 
	{
		map.get().clear();
	}
	
}
