package com.ombp.cloud.model.usermanagement;

import java.util.Collections;
import java.util.List;

import com.ombp.cloud.model.BaseEntity;

public class Client extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7796120325386896288L;

	private String clientId;

	private String resourceIdsStr;

	private String clientSecret;

	private String scopeStr;

	private String grantTypesStr;

	private String redirectUris;

	private String authoritiesStr;

	private String autoApproveScopeStr;

	private Integer accessTokenValiditySeconds;
	
	private Integer refreshTokenValiditySeconds;
	
	private String additionalInformationStr;
	
	private List<Authority> allowedActions;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResourceIdsStr() {
		return resourceIdsStr;
	}
	public void setResourceIdsStr(String resourceIdsStr) {
		this.resourceIdsStr = resourceIdsStr;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getScopeStr() {
		return scopeStr;
	}
	public void setScopeStr(String scopeStr) {
		this.scopeStr = scopeStr;
	}
	public String getGrantTypesStr() {
		return grantTypesStr;
	}
	public void setGrantTypesStr(String grantTypesStr) {
		this.grantTypesStr = grantTypesStr;
	}
	public String getRedirectUris() {
		return redirectUris;
	}
	public void setRedirectUris(String redirectUris) {
		this.redirectUris = redirectUris;
	}
	public String getAuthoritiesStr() {
		return authoritiesStr;
	}
	public void setAuthoritiesStr(String authoritiesStr) {
		this.authoritiesStr = authoritiesStr;
	}
	public String getAutoApproveScopeStr() {
		return autoApproveScopeStr;
	}
	public void setAutoApproveScopeStr(String autoApproveScopeStr) {
		this.autoApproveScopeStr = autoApproveScopeStr;
	}
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}
	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}
	public String getAdditionalInformationStr() {
		return additionalInformationStr;
	}
	public void setAdditionalInformationStr(String additionalInformationStr) {
		this.additionalInformationStr = additionalInformationStr;
	}
	public List<Authority> getAllowedActions() {
		return allowedActions == null ? Collections.emptyList() : allowedActions;
	}
	public void setAllowedActions(List<Authority> allowedActions) {
		this.allowedActions = allowedActions;
	}
}
