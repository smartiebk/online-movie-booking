package com.ombp.cloud.app.um.dao;

import com.ombp.cloud.model.usermanagement.Client;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface ClientDao extends GenericDaoContract<Client, String>{

	public Client fetchClientByClientId(String clientId);
	
}
