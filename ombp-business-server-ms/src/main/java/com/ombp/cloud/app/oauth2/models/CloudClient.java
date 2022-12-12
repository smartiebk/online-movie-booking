package com.ombp.cloud.app.oauth2.models;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import com.ombp.cloud.model.usermanagement.Authority;
import com.ombp.cloud.model.usermanagement.Client;

public class CloudClient implements ClientDetails {

	private Client client;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3618668539008124012L;

	@Override
	public String getClientId() {
		return getClient().getClientId();
	}

	@Override
	public Set<String> getResourceIds() {
		return getCollectionFromString(getClient().getResourceIdsStr());
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return getClient().getClientSecret();
	}

	@Override
	public boolean isScoped() {
		return false;
	}

	@Override
	public Set<String> getScope() {
		return getCollectionFromString(getClient().getScopeStr());
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return getCollectionFromString(getClient().getGrantTypesStr());
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return getCollectionFromString(getClient().getRedirectUris());
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		List<Authority> allowedActions = getClient().getAllowedActions();
		for(Authority autho : allowedActions) 
		{
			authorities.add(new CloudAuthority(autho));
		}
		return authorities;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return getClient().getAccessTokenValiditySeconds();
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return getClient().getRefreshTokenValiditySeconds();
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAdditionalInformation() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = Collections.emptyMap();
		if (getClient().getAdditionalInformationStr() != null && getClient().getAdditionalInformationStr().length() != 0) {
			try {
				map = mapper.readValue(getClient().getAdditionalInformationStr(), Map.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public CloudClient(Client client) {
		super();
		this.client = client;
	}
	
	private Set<String> getCollectionFromString(String css) 
	{
		Set<String> setOfValues = Collections.emptySet();

		if (css != null && css.length() != 0) {
			setOfValues = StringUtils.commaDelimitedListToSet(css);
		}
		return setOfValues;
	}

}
