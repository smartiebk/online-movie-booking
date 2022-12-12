package com.ombp.cloud.app.ui.auth.service;

import com.ombp.cloud.app.ui.exception.ServiceDownIOException;
import com.ombp.cloud.model.usermanagement.AccessToken;
import com.ombp.cloud.model.usermanagement.CheckedTokenInfo;

public interface RemoteAuthService {

	AccessToken fetchAccessToken(String username, String password) throws ServiceDownIOException;
	
	AccessToken fetchAccessToken(String refreshToken);
	
	CheckedTokenInfo checkToken(String accessToken);
	
}
