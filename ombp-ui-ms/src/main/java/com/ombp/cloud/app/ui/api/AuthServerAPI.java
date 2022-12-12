package com.ombp.cloud.app.ui.api;

import java.io.IOException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ombp.cloud.app.ui.config.feign.FeignClientBearerConfig;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.model.usermanagement.LogoutInfo;

@FeignClient(contextId = "bearerContextIdForAuthServer", value = "bearerValueForAuthServer", url = "${authservice.hostname}", configuration = FeignClientBearerConfig.class)
public interface AuthServerAPI extends EndPointConstants {

	@GetMapping(EndPointConstants.USER_INFO)
	AuthenticatedUser getUserInfo() throws IOException;

	@GetMapping(EndPointConstants.LOGOUT)
	LogoutInfo remoteLogout() throws IOException;

}
