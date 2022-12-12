package com.ombp.cloud.app.ui.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.ombp.cloud.app.ui.constants.UIConstants;

public class PageRouteingHelper {
	
	private final static Logger LOG = LoggerFactory.getLogger(PageRouteingHelper.class);
	
	private static Properties prop = null;
     
	private static List<String> jsMsg;
	
	static 
	{
		jsMsg = new ArrayList<String>();
		
		InputStream is = null;
        try {
            prop = new Properties();
            is = PageRouteingHelper.class.getResourceAsStream("/messages.properties");
            prop.load(is);
        } catch (FileNotFoundException e) {
        	LOG.error("", e);
        } catch (IOException e) {
        	LOG.error("", e);
        }
        
        for(Object keyObj : prop.keySet()) 
        {
        	String key = (String) keyObj;
        	if(key.endsWith(".ijaw")) 
        	{
        		jsMsg.add(key);
        	}
        }
	}
	
	public static ModelAndView getFullPageSpecs(String pageId, Map<String, Object> objects) 
	{
		LOG.debug("Routing to page with page-id as :::: "+pageId);
		
		ModelAndView modelAndView = new ModelAndView("pages/index");
		
		modelAndView.addObject("pageName", pageId);
		
		if(objects!=null) 
		{
			for(String key : objects.keySet()) 
			{
				modelAndView.addObject(key, objects.get(key));
			}
		}
		
		modelAndView.addObject("jsMsg", jsMsg);
		
		addLoggedInUserSpecs(modelAndView);
		
		return modelAndView;
	}
	
	public static ModelAndView getFragmentPageSpecs(String page, Map<String, Object> params) 
	{
		LOG.debug("Loading fragment page with page as :::: "+ page);
		
		ModelAndView modelAndView = new ModelAndView(page);
		
		if(params!=null) 
		{
			for(String key : params.keySet()) 
			{
				modelAndView.addObject(key, params.get(key));
			}
		}
		
		addLoggedInUserSpecs(modelAndView);
		
		return modelAndView;
	}
	
	private static void addLoggedInUserSpecs(ModelAndView modelAndView) 
	{
		modelAndView.addObject(UIConstants.PAGE_ATTR_CURRENT_USER, SpringContextHelper.getAuthenticatedUser());
		
		//To be used in thymleaf expression ${#sets.contains(permissions, 'ALLOWED_ACTION')}
		//Like th:if="${#sets.contains(permissions, 'ENTITY_ACTION_CREATE')} or ${isSuperAdmin}" 
		modelAndView.addObject(UIConstants.PAGE_ATTR_CURRENT_USER_PERMISSIONS,
				SpringContextHelper.getAuthenticatedUser().getAllowedAuthorities());
		
		modelAndView.addObject(UIConstants.PAGE_ATTR_CURRENT_USER_SUPERADMIN,
				SpringContextHelper.getAuthenticatedUser().isSuperAdmin());
	}

}
