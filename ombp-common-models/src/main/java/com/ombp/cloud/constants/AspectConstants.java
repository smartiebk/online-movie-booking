package com.ombp.cloud.constants;

public class AspectConstants {

	public static final String ASPECT_PREFIX = "execution(* ";
	
	public static final String ASPECT_SUFFIX = ".*.*(..))";
	
	/**
	 * Constant with PACKAGE_* : Distinct Name of package names for micro-service
	 * */
	public static final String PACKAGE_UM = "com.ombp.cloud.app.um.service";	
	
	public static final String PACKAGE_AS_END_POINT = "com.ombp.cloud.app.endpoint.";	
	
}


