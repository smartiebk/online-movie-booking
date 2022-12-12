package com.ombp.cloud.app.common.dao.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

/**
 * @author 
 */
@Configuration
public class MybatisConfig {

	@Autowired
	protected DataSource dataSource;
	
	@Autowired
    private ResourceLoader resourceLoader;

	@Bean
	protected SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setConfigLocation(ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath:/config/*-mybatis-config.xml")[0]);
		factory.setMapperLocations(ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath:/dbmappers/**/*.xml"));
		return factory.getObject();
	}

	@Bean
	protected SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
