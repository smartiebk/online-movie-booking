package com.ombp.cloud.app.endpoint.um;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.um.service.UserService;
import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.constants.ResponseConstants;
import com.ombp.cloud.context.ThreadLocalContext;
import com.ombp.cloud.model.RequestConvertor;
import com.ombp.cloud.model.ResponseConvertor;
import com.ombp.cloud.model.paging.Page;
import com.ombp.cloud.model.paging.PagingRequest;
import com.ombp.cloud.model.ui.ResponseData;
import com.ombp.cloud.model.usermanagement.AppUser;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.model.usermanagement.UserGroup;

@RestController
public class UserEndPoint implements EndPointConstants, ResponseConstants {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder userPasswordEncoder;
	
	@RequestMapping(value = LIST_USER, method = RequestMethod.POST, consumes = "application/json")
	public Page<AppUser> list(HttpServletRequest request,  @RequestBody PagingRequest pagingRequest) 
	{
		Map<String, Object> searchParams = new HashMap<>();
		
		searchParams.put(AppConstant.PAGINATION_PARAM_KEY, pagingRequest);
		
		Page<AppUser> users = userService.fetchAllUsers(searchParams);
		
		return new ResponseConvertor<Page<AppUser>>(users).getObject();
	}
	
	@RequestMapping(value = SAVE_USER, method = RequestMethod.POST, consumes = "application/json")
	public AppUser saveUser(HttpServletRequest request, @RequestBody AppUser appUser) throws Exception 
	{
		AppUser user = new RequestConvertor<AppUser>(appUser).getObject();
		
		if(user.getId()==null) {
			appUser.setPassword(userPasswordEncoder.encode(appUser.getPassword()));
		}
		userService.saveUser(user);
		
		return new ResponseConvertor<AppUser>(user).getObject();
	}
	
	@RequestMapping(value = SAVE_USER_INFO_FROM_PROFILE, method = RequestMethod.POST, consumes = "application/json")
	public AppUser saveUserInfoFromProfile(HttpServletRequest request, @RequestBody AppUser appUser) throws Exception 
	{
		userService.saveUserInfoFromProfile(appUser);
		
		return appUser;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = DELETE_USER, method = RequestMethod.POST)
	public String deleteRoles(HttpServletRequest request, @RequestBody String[] pkIds)
	 {
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) ThreadLocalContext.getCurrentThreadMap()
				.get(AppConstant.THREAD_PREFIX_CURRENT_USER + String.valueOf(Thread.currentThread().getId()));

		List<Integer> selectedIds = null;
		try {
			selectedIds = new RequestConvertor().getConvertedIntegerList(Arrays.asList(pkIds));
		} catch (NumberFormatException | InvalidKeyException | InvalidAlgorithmParameterException
				| NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException | IOException e) {
			LOG.error("", e);
			return null;
		}
		if(selectedIds!=null) 
		{
			userService.deleteSelected(selectedIds, authenticatedUser);	
		}

		return SUCCESS;
	}
	
	@RequestMapping(value = FETCH_USER, method = RequestMethod.GET)
	public AppUser fetchUser(HttpServletRequest request, @PathVariable(value="id") String id) throws Exception 
	{
		Integer realId = null;
		try {
			realId = Integer.valueOf(id);
		}
		catch(Exception exception) 
		{
			realId = Integer.valueOf(new RequestConvertor<String>(id).getObject());
		}
		
		AppUser user = userService.fetchUserById(Integer.valueOf(realId));
		
		return new ResponseConvertor<AppUser>(user).getObject();
	}
	
	@RequestMapping(value = FETCH_USER_DETAILS, method = RequestMethod.GET)
	public ResponseData fetchUserDetails(HttpServletRequest request, @PathVariable(value="id") String pkId) throws Exception 
	{
		Integer realId = null;
		try {
			realId = Integer.valueOf(pkId);
		}
		catch(Exception exception) 
		{
			realId = Integer.valueOf(new RequestConvertor<String>(pkId).getObject());
		}
				
		List<UserGroup> existingUserGroups = userService.getAllUserGroupsByUserId(realId);
		
		Collections.sort(existingUserGroups, new Comparator<UserGroup>() {
			@Override
			public int compare(UserGroup o1, UserGroup o2) {
				return o2.getName().compareTo(o1.getName());
			}
		});
		
		AppUser appUser = userService.fetchUserById(realId);
		
		Map<String, Object> data = new HashMap<>();
		data.put("existingUserGroups", existingUserGroups);
		data.put("user", appUser);
		
		ResponseData responseData = new ResponseData(SUCCESS);
		responseData.setData(data);
		
		return responseData;
	}
	
}
