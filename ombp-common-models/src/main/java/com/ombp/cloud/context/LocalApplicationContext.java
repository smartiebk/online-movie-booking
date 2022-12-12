package com.ombp.cloud.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalApplicationContext {

	private static Map<String, Set<String>> mehtodSecurityACL;
	
	static
	{
		mehtodSecurityACL = new HashMap<>();
	}
	
	public static Map<String, Set<String>> getMethodSecurityACLs() 
	{
		return mehtodSecurityACL;
	}
}
