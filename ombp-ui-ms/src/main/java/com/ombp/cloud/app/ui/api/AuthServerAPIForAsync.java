package com.ombp.cloud.app.ui.api;

import java.io.IOException;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ombp.cloud.constants.EndPointConstants;

@FeignClient(contextId = "bearerContextIdForAuthServerAsync", value = "bearerValueForAuthServerAsync", url = "${authservice.hostname}")
public interface AuthServerAPIForAsync extends EndPointConstants {

	@RequestMapping(value = UPDATE_EXPIRED_LOCAL_SCANS_STATUS_IN_IMAGING, method = RequestMethod.POST, consumes = "application/json")
	public void updateScanFileStatusAfterDeletion(@RequestHeader("Authorization") String token, @RequestBody List<Integer> ids) throws IOException;

	
}
