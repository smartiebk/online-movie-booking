package com.ombp.cloud.app.um.service;

import com.ombp.cloud.model.usermanagement.Client;

public interface ClientService {

	Client fetchClientByClientId(String clientId);
}
