package com.ombp.cloud.app.oauth2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.cache.TLCCache;
import com.ombp.cloud.app.oauth2.models.CloudClient;
import com.ombp.cloud.app.um.service.ClientService;
import com.ombp.cloud.model.usermanagement.Client;

@Service
@Primary
public class CloudClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientService clientService;

	@Autowired
	private TLCCache cache;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		CloudClient cacheObj = cache.getClient(clientId);

		if (cacheObj == null) {

			Client client = clientService.fetchClientByClientId(clientId);
			
			cacheObj = new CloudClient(client);
			
			cache.putClient(clientId, cacheObj);
		}
		
		return cacheObj;
	}
	
}
