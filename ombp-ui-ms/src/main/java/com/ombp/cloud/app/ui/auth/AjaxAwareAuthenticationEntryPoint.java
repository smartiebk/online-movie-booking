package com.ombp.cloud.app.ui.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private static final  String HTTP_AJAX_HEADER_NAME = "X-Requested-With";
	private static final String HTTP_AJAX_HEADER_VALUE = "XMLHttpRequest";
	private static final String CONTENT_TYPE_JSON = "application/json";
	
	public AjaxAwareAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	  @Override
	  public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException)
	      throws IOException, ServletException {

	    if (isAjax(request)) {
	    	response.setContentType(CONTENT_TYPE_JSON);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
	    } else {
	      super.commence(request, response, authException);
	    }
	  }
	  
	  private boolean isAjax(HttpServletRequest request) {
			String header = request.getHeader(HTTP_AJAX_HEADER_NAME);
			return header != null && HTTP_AJAX_HEADER_VALUE.equals(header);
		}
}
