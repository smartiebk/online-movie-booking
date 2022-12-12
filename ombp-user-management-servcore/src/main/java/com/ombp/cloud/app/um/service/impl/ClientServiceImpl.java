package com.ombp.cloud.app.um.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ombp.cloud.app.um.dao.ClientDao;
import com.ombp.cloud.app.um.service.ClientService;
import com.ombp.cloud.model.usermanagement.Client;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public Client fetchClientByClientId(String clientId) {
		Client client = clientDao.fetchClientByClientId(clientId);
		return client;
	}

}
