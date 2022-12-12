package com.ombp.cloud.app.ui.config.feign;

import org.springframework.context.annotation.Bean;

import feign.Retryer;

public class FeignClientBearerConfig {

	@Bean
    public FeignClientBearerInterceptor feignClientBearerInterceptor() {
        return new FeignClientBearerInterceptor();
    }
	
	@Bean
	public Retryer retryer() {
	    return new Retryer.Default();
	}
	
}
