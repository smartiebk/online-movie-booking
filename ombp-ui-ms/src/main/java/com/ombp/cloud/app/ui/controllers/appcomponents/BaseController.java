package com.ombp.cloud.app.ui.controllers.appcomponents;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.constants.UIURLConstants;
import com.ombp.cloud.app.ui.exception.ServiceDownIOException;
import com.ombp.cloud.app.ui.util.PageRouteingHelper;
import com.ombp.cloud.app.ui.util.PermissionUtil;
import com.ombp.cloud.app.ui.util.SpringContextHelper;
import com.ombp.cloud.constants.PermissionConstants;

@Component
public class BaseController implements UIURLConstants, UIConstants, PermissionConstants {

	
	public void getUserPermissionsForActionsOnEntity(List<String> permissions, String entity) {
		if(PermissionUtil.canEdit(entity)) 
		{
			permissions.add(CAN_EDIT_ENTITY);
		}
		if(PermissionUtil.canDelete(entity)) 
		{
			permissions.add(CAN_DELETE_ENTITY);
		}
		if(PermissionUtil.canConfigure(entity)) 
		{
			permissions.add(CAN_CONFIGURE_ENTITY);
		}	
	}
	
	/**
	 * Use this method for writing full page response with different URL so that all items get included while sending response.
	 * */
	protected ModelAndView constructResponseWithCommonComponents(String pageId, Map<String, Object> params) 
			throws ServiceDownIOException 
	{
		params.put(LOGGED_IN_USER_INFO, SpringContextHelper.getAuthenticatedUser());
		
		return PageRouteingHelper.getFullPageSpecs(pageId, params);
	}
}
