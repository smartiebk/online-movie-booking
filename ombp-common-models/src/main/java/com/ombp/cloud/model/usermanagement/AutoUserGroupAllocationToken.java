package com.ombp.cloud.model.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.ombp.cloud.model.BaseEntity;
import com.ombp.cloud.utils.RandomStringCryptoUtil;

public class AutoUserGroupAllocationToken extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4254420584165001225L;

	
	private Integer userGroupId;
	
	private String token;
	
	private Integer tokenUsed;
	
	private Date tokenExpiry;

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getTokenUsed() {
		return tokenUsed;
	}

	public void setTokenUsed(Integer tokenUsed) {
		this.tokenUsed = tokenUsed;
	}

	public Date getTokenExpiry() {
		return tokenExpiry;
	}

	public void setTokenExpiry(Date tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}

	public AutoUserGroupAllocationToken(Integer userGroupId) {
		super();
		this.userGroupId = userGroupId;
		this.setToken(RandomStringCryptoUtil.getRandomAlphaNumericString(45));
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 7);
	    this.setTokenExpiry(cal.getTime());
		this.setTokenUsed(0);
		this.setCreateTime(new Date());
		this.setUpdateTime(new Date());
	}
	
}