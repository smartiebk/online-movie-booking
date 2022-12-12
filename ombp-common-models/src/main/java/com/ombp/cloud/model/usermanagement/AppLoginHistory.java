package com.ombp.cloud.model.usermanagement;

import java.util.Date;

import com.ombp.cloud.model.BaseEntity;

public class AppLoginHistory extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1837420712525753318L;
	
	private String username;
	
	private String clientId;
	
	private String accessTokenValue;
	
	private Date accessTokenExpiration;
	
	private String refreshTokenValue;
	
	private Date refreshTokenExpiration;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String grantType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAccessTokenValue() {
		return accessTokenValue;
	}

	public void setAccessTokenValue(String accessTokenValue) {
		this.accessTokenValue = accessTokenValue;
	}

	public String getRefreshTokenValue() {
		return refreshTokenValue;
	}

	public void setRefreshTokenValue(String refreshTokenValue) {
		this.refreshTokenValue = refreshTokenValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public Date getAccessTokenExpiration() {
		return accessTokenExpiration;
	}

	public void setAccessTokenExpiration(Date accessTokenExpiration) {
		this.accessTokenExpiration = accessTokenExpiration;
	}

	public Date getRefreshTokenExpiration() {
		return refreshTokenExpiration;
	}

	public void setRefreshTokenExpiration(Date refreshTokenExpiration) {
		this.refreshTokenExpiration = refreshTokenExpiration;
	}

	public AppLoginHistory(String username, String clientId, String accessTokenValue, Date accessTokenExpiration,
			String refreshTokenValue, Date refreshTokenExpiration, Date createTime, Date updateTime, String grantType) {
		super();
		this.username = username;
		this.clientId = clientId;
		this.accessTokenValue = accessTokenValue;
		this.accessTokenExpiration = accessTokenExpiration;
		this.refreshTokenValue = refreshTokenValue;
		this.refreshTokenExpiration = refreshTokenExpiration;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.grantType = grantType;
	}

	public AppLoginHistory() {
		super();
	}
	
}
