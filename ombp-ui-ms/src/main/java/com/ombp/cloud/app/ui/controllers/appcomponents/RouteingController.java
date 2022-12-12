package com.ombp.cloud.app.ui.controllers.appcomponents;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ombp.cloud.app.ui.api.AuthServerAPI;
import com.ombp.cloud.app.ui.api.OpenAuthServerAPI;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.constants.UIURLConstants;
import com.ombp.cloud.constants.PermissionConstants;

/**
 * @author PranayKathade
 * This class is used for routing main pages which loads with new URLs.
 */
@Controller
public class RouteingController extends BaseController implements UIURLConstants, UIConstants, PermissionConstants {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AuthServerAPI authServerAPI;
	
	@Autowired
	private OpenAuthServerAPI openAuthServerAPI;
	
	
	@ResponseBody
	@RequestMapping(CHANGE_LANGUAGE)
	public void changeLanguage(HttpServletRequest httpRequest) {
	}
}
