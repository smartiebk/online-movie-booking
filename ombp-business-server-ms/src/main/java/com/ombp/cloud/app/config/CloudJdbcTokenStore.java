package com.ombp.cloud.app.config;

import javax.sql.DataSource;

import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;
@Component
public class CloudJdbcTokenStore extends JdbcTokenStore {

	public CloudJdbcTokenStore(DataSource dataSource) {
		super(dataSource);
	}

}
