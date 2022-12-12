package com.ombp.cloud.app.ui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.ombp.cloud.app.ui.auth.AjaxAwareAuthenticationEntryPoint;
import com.ombp.cloud.app.ui.auth.AuthLoginSuccessHandler;
import com.ombp.cloud.app.ui.auth.AuthLogoutSuccessHandler;
import com.ombp.cloud.app.ui.auth.TLCAuthenticationProvider;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	/*@Autowired
    private TLCLogoutHandler logoutHandler;
	*/
	@Autowired
	private AuthLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
    private AuthLoginSuccessHandler loginSuccessHandler;
	
	@Autowired
    SimpleUrlAuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	private TLCAuthenticationProvider authenticationProvider;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Commented items <====> "/info/**","/ws_report/**", "/adiona/**", "/tdc/**", "/ngd/**",
		
		String[] allowedPatterns = new String[] { "/", "/login", "/home/**", "/user/forgotPassword/**", "/error", "/di/**","/rp/**","/oa/**", };	

	http.csrf().disable().authorizeRequests().antMatchers(allowedPatterns).permitAll() // (3)
	//	http.authorizeRequests().antMatchers("/**").permitAll() // (3)
				.anyRequest()
				.authenticated() // (4)
				.and()
				//.addFilterAfter(getFilter(), UsernamePasswordAuthenticationFilter.class)
				.formLogin().defaultSuccessUrl("/app", true)
                .failureHandler( authFailureHandler )// (5)
				.loginPage("/login")
				.successHandler( loginSuccessHandler )
                .and()
	            .logout()
	            //.logoutUrl("/logout")
	            //.addLogoutHandler(logoutHandler)
	            .logoutSuccessHandler(logoutSuccessHandler)
	            //.logoutSuccessUrl("/login")
				.permitAll()
				.and()
				.authenticationProvider(authenticationProvider).exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
	
	http.authorizeRequests();
	
	http.headers().frameOptions().sameOrigin();
	
	}

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		String[] allowedPatterns = new String[] { "/resources/**", "/static/**", "/vendor/**", "/js/**", "/css/**", "/di/**", 
				"/dist/**", "/less/**", "/data/**", "/**.json", "/pages/**", "/**.js", "/images/**", "/userfiles/**","/user/forgotPassword/**",
				"/sw.js", "/.well-known/assetlinks.json", };
		
		web.ignoring().antMatchers(allowedPatterns);
	}

	
	@Bean
	SimpleUrlAuthenticationFailureHandler getAuthFailureHandler() {

		SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/login");
		handler.setDefaultFailureUrl("/login");
		
		// handler.setUseForward( true );

		return handler;

	}
	
	
	@Bean
	TLCAuthenticationProvider getAuthenticationProvider() 
	{
		return new TLCAuthenticationProvider();
	}

	/*@Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
    auth.authenticationProvider( new TLCAuthentication() );
    }*/
	
	/*@Bean
	public TLCLogoutSuccessHandler logoutSuccessHandler() {
	    return new TLCLogoutSuccessHandler();
	}*/
	
	@Bean
    public AjaxAwareAuthenticationEntryPoint authenticationEntryPoint(){
		AjaxAwareAuthenticationEntryPoint entryPoint = 
          new AjaxAwareAuthenticationEntryPoint("/login");
        return entryPoint;
    }
	
	@Bean
	public FilterRegistrationBean xssPreventFilter() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();

	    registrationBean.setFilter(new XSSFilter());
	    registrationBean.addUrlPatterns("/*");

	    return registrationBean;
	}
}
