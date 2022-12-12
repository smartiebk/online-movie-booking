package com.ombp.cloud.app.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Component;

import com.ombp.cloud.app.oauth2.models.CloudClient;
import com.ombp.cloud.app.oauth2.models.CloudUser;

@Component
public class TLCCache {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CacheManager cacheManager;
	
	private Cache userCache;
	
	private Cache clientCache;
	
	private Cache getUserCache() 
	{
		userCache = cacheManager.getCache("UsersCache");
		
		return userCache;
	}
	
	private Cache getClientCache() 
	{
		clientCache = cacheManager.getCache("ClientCache");
		
		return clientCache;
	}
	
	public CloudUser getUser(String key) 
	{
		CloudUser cacheObj = null;
		
		ValueWrapper value = getUserCache().get(key);
		
		if(value!=null) 
		{
			cacheObj = (CloudUser) value.get();
		}
		
		logger.debug("Getting user from cache....");
		
		return cacheObj;
	}
	
	public void putUser(String key, CloudUser user) 
	{
		getUserCache().put(key, user);
	}
	
	public void removeUser(String key) 
	{
		getUserCache().evict(key);
	}
	
	public CloudClient getClient(String key) 
	{
		CloudClient cacheObj = null;
		
		ValueWrapper value = getClientCache().get(key);
		
		if(value!=null) 
		{
			cacheObj = (CloudClient) value.get();
		}
		
		logger.debug("Getting client from cache....");
		
		return cacheObj;
	}
	
	public void putClient(String key, CloudClient client) 
	{
		getClientCache().put(key, client);
	}
	

}
