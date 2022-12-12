package com.ombp.cloud.app.common.dao;

import java.util.Map;
import java.util.Set;

public interface AppInitializerDao {

	Map<String, Set<String>> fetchMethodSecurityACLs(String serviceName);
	
}
