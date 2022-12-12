package com.ombp.cloud.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
//@Order(1)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder userPasswordEncoder;
	
	@Autowired
	private CloudAuthenticationEntryPoint unauthorizedHandler;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
       
		http.authorizeRequests().antMatchers("/*/signup", "/resetpasswd/**", "/log/**", "/open/**", "/sharedlink/**").permitAll()
        .anyRequest().authenticated().and().formLogin()
        .permitAll().and().logout().permitAll();
		
		/*http
        .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler);*/
		
		http.csrf().disable();
		
		http.exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler);
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/resetpasswd/**", "/logsms/**", "/sharedlink/**");
    }
	
	@Bean
	public FilterRegistrationBean xssPreventFilter() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();

	    registrationBean.setFilter(new XSSFilter());
	    registrationBean.addUrlPatterns("/*");

	    return registrationBean;
	}
}
