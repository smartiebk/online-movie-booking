package com.ombp.cloud.app.ui.auth.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ombp.cloud.app.ui.auth.service.RemoteAuthService;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.exception.ServiceDownIOException;
import com.ombp.cloud.model.usermanagement.AccessToken;
import com.ombp.cloud.model.usermanagement.CheckedTokenInfo;

@Service
public class RemoteAuthServiceImpl implements RemoteAuthService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	
	@Value("${authservice.clientid}")
	private String clientId;
	
	@Value("${authservice.clientsecret}")
	private String clientSecret;
	
	@Value("${authservice.token.endpoint}")
	private String accessTokenUsingPasswordGrantUrl;
	
	@Value("${authservice.reissue.token.endpoint}")
	private String accessTokenUsingRefreshGrantUrl;
	
	@Value("${authservice.check.token.endpoint}")
	private String checkTokenUrl;
	
	@Override
	public AccessToken fetchAccessToken(String username, String password) throws ServiceDownIOException {
		
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.set("grant_type", "password");
		form.set("username", username);
		form.set("password", password);

		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add(HttpHeaders.AUTHORIZATION, String.format("Basic %s",
					new String(Base64.getEncoder().encode(String.format("%s:%s", clientId, clientSecret).getBytes("UTF-8")), "UTF-8")));
		} catch (UnsupportedEncodingException e1) {
			LOG.error("",e1);
		}
		 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form,
				headers);
		//org.apache.http.ssl.SSLContexts.custom().
		//SSLContext sslContext = SSLContexts.custom().useTLS().build();
		//SSLConnectionSocketFactory f = new SSLConnectionSocketFactory( sslContext, new String[]{"TLSv1", "TLSv1.2"}, null, null);
		//HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
		//HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		LOG.error("Posting for access token with password grant...");
		AccessToken accessToken = null;
		try {
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> responseEntity;
			try {
				responseEntity = restTemplate.exchange(accessTokenUsingPasswordGrantUrl, HttpMethod.POST, request, String.class);
				HttpStatus statusCode = responseEntity.getStatusCode();
				LOG.error("Posting for access token completed code - " + statusCode);

				if (statusCode == HttpStatus.OK) {

					ObjectMapper mapper = new ObjectMapper();
					mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

					try {
						accessToken = mapper.readValue(responseEntity.getBody(), AccessToken.class);
					} catch (IOException e) {
						LOG.error("", e);
					}
				}
			} catch (HttpStatusCodeException exception) {
				String exMsg = exception.getResponseBodyAsString();
				
				if (exMsg != null && exMsg.contains(UIConstants.WRONG_PASSWORD_MESSAGE)) {
					accessToken = new AccessToken();
					accessToken.setErrorMessage(UIConstants.WRONG_PASSWORD_MESSAGE);
				}
				
				if(exMsg.length()==0 && exception.getResponseHeaders()!=null) 
				{
					exMsg = exception.getResponseHeaders().get("WWW-Authenticate")!=null ?
							exception.getResponseHeaders().get("WWW-Authenticate").get(0) : "";
							
							accessToken = new AccessToken();
							
					if (exMsg.length() != 0 && exMsg.contains(UIConstants.USER_NOT_FOUND_MESSAGE)) {
						accessToken.setErrorMessage(UIConstants.USER_NOT_FOUND_MESSAGE);
					}
				}
				
				LOG.error("Requested Authorization service is down....", exception);
			}
		} catch (Exception e) {
			if (e instanceof ResourceAccessException) {
				LOG.error("Requested Authorization service is down....", e);
				throw new ServiceDownIOException(e.getMessage());
			} 
			LOG.error("Exception in RemoteAuthServiceImpl....", e);
		}
		
		return accessToken;
	}

	@Override
	public AccessToken fetchAccessToken(String refreshToken) {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add(HttpHeaders.AUTHORIZATION, String.format("Basic %s",
					new String(Base64.getEncoder().encode(String.format("%s:%s", clientId, clientSecret).getBytes("UTF-8")), "UTF-8")));
		} catch (UnsupportedEncodingException e1) {
			LOG.error("",e1);
		}

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form,
				headers);
		//org.apache.http.ssl.SSLContexts.custom().
		//SSLContext sslContext = SSLContexts.custom().useTLS().build();
		//SSLConnectionSocketFactory f = new SSLConnectionSocketFactory( sslContext, new String[]{"TLSv1", "TLSv1.2"}, null, null);
		//HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
		//HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		LOG.error("Posting for access token with refresh token grant...");
		AccessToken accessToken = null;
		ResponseEntity<String> responseEntity;
		try {
			RestTemplate restTemplate = new RestTemplate();		
			responseEntity = restTemplate.exchange(accessTokenUsingRefreshGrantUrl+refreshToken, HttpMethod.POST, request, String.class); 
			HttpStatus statusCode = responseEntity.getStatusCode();
		LOG.error("Posting for access token completed code - " + statusCode);
		
		if (statusCode == HttpStatus.OK) {
			
			ObjectMapper mapper =new ObjectMapper(); 
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			try {
				accessToken = mapper.readValue(responseEntity.getBody(), AccessToken.class);
			} catch (IOException e) {
				LOG.error("",e);
			}
		}
		}
		catch(Exception e) 
		{
			LOG.error("",e);
		}
		
		
		return accessToken;
	}

	@Override
	public CheckedTokenInfo checkToken(String accessToken) {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add(HttpHeaders.AUTHORIZATION, String.format("Basic %s",
					new String(Base64.getEncoder().encode(String.format("%s:%s", clientId, clientSecret).getBytes("UTF-8")), "UTF-8")));
		} catch (UnsupportedEncodingException e1) {
			LOG.error("",e1);
		}

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form,
				headers);
		//org.apache.http.ssl.SSLContexts.custom().
		//SSLContext sslContext = SSLContexts.custom().useTLS().build();
		//SSLConnectionSocketFactory f = new SSLConnectionSocketFactory( sslContext, new String[]{"TLSv1", "TLSv1.2"}, null, null);
		//HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
		//HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		LOG.error("Posting for access token with refresh token grant...");
		
		RestTemplate restTemplate = new RestTemplate();		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(checkTokenUrl+accessToken, request, String.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		LOG.error("Posting for access token completed code - " + statusCode);
		CheckedTokenInfo tokenInfo = null;

		if (statusCode == HttpStatus.OK) {
			
			ObjectMapper mapper =new ObjectMapper(); 
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			try {
				tokenInfo = mapper.readValue(responseEntity.getBody(), CheckedTokenInfo.class);
			} catch (IOException e) {
				LOG.error("",e);
			}
		}
		
		return tokenInfo;
	}

}
