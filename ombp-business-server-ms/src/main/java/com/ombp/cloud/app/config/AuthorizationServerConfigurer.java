/**
 * 
 */
package com.ombp.cloud.app.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.ombp.cloud.app.um.service.LoginHistoryService;

/**
 * @author PranayKathade
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private ClientDetailsService clientDetailsService; 
	
	@Autowired
	protected DataSource dataSource;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private NoOpPasswordEncoder noOpPasswordEncoder;
	
	@Autowired
	private LoginHistoryService loginHistoryService;
	
	@Bean
    public TokenStore tokenStore() {
        return new CloudJdbcTokenStore(dataSource);
    }
	
	@Bean
	public ApprovalStore approvalStore() {
		return new JdbcApprovalStore(dataSource);
	}
	
	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenServices(customTokenServices()).approvalStore(approvalStore()).authorizationCodeServices(authorizationCodeServices())
				.tokenStore(tokenStore()).userApprovalHandler(userApprovalHandler(tokenStore()))
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()").allowFormAuthenticationForClients()
        .checkTokenAccess("isAuthenticated()").passwordEncoder(noOpPasswordEncoder);
    }
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(6);
    }
	
	@Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }
	
	public AuthorizationServerTokenServices customTokenServices(){
		  CloudTokenServices tokenServices = new CloudTokenServices();
		  tokenServices.setReuseRefreshToken(true);
		  tokenServices.setSupportRefreshToken(true);
		  tokenServices.setAuthenticationManager(createPreAuthProvider());
		  tokenServices.setTokenStore(tokenStore());
		  tokenServices.setClientDetailsService(clientDetailsService);
		  tokenServices.setDataSource(dataSource);
		  tokenServices.setLogLoginHistoryService(loginHistoryService);
		  return tokenServices;
		}
	
	private ProviderManager createPreAuthProvider() {
	    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
	    provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
	    return new ProviderManager(Arrays.asList(provider));
	}

}
