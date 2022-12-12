/**
 * 
 */
package com.ombp.cloud.app.um.dao.impl;

import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.common.dao.impl.GenericDaoImpl;
import com.ombp.cloud.app.um.dao.ClientDao;
import com.ombp.cloud.model.usermanagement.Client;

/**
 * @author PranayKathade
 *
 */
@Repository
public class ClientDaoImpl extends GenericDaoImpl<Client, String> implements ClientDao {
	
	public ClientDaoImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.ombp.cloud.servcore.um.dao.ClientDao#fetchClientByClientId(java.lang.String)
	 */
	@Override
	public Client fetchClientByClientId(String clientId) {
		return super.fetchById(clientId);
	}

}
