package com.ombp.cloud.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
@EnableZuulProxy
@EnableDiscoveryClient
public class EdgeRoutingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeRoutingServerApplication.class, args);
	}

}
