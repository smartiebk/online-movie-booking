package com.ombp.cloud.app.um.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import com.ombp.cloud.model.share.MapperSql;
import com.ombp.cloud.model.share.MybatisMapper;

public class MybatisMapperParser {
	
	private final static Logger LOG = LoggerFactory.getLogger(MybatisMapperParser.class);
	
	static
	{
		System.setProperty("javax.xml.accessExternalDTD", "all");
	}
	
	private static Map<String, String> sqlMap = null;

	private static void populateSQLs() throws FileNotFoundException 
	{
		MybatisMapper mapperObj = null;
		
		ClassPathResource classPathResource = new ClassPathResource("dbmappers/share/excludeFromSharePage.xml");
		
		BufferedReader br = null;
		try{
			URL url = classPathResource.getURL();
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			
		} catch (IOException e) {
			LOG.error("file error " + e.getMessage());
		}
		
		//File xmlFile = ResourceUtils.getFile("classpath:/dbmappers/share/excludeFromSharePage.xml");
		 
		JAXBContext jaxbContext;
		try
		{
		    jaxbContext = JAXBContext.newInstance(MybatisMapper.class);              
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    mapperObj = (MybatisMapper) jaxbUnmarshaller.unmarshal(br);
		     
		}
		catch (JAXBException e) 
		{
			LOG.error("", e);
		}
		
		if(mapperObj!=null && mapperObj.getSqls()!=null && mapperObj.getSqls().size()!=0) 
		{
			sqlMap = new HashMap<String, String>();
			
			for(MapperSql msql : mapperObj.getSqls())
			{
				sqlMap.put(msql.getId(), msql.getSql());
			}
		}
	}
	
	public static String getSQLWithIdFromMapper(String id) 
	{
		String sql = null;
		
		if(sqlMap == null) 
		{
			try {
				populateSQLs();
			} catch (FileNotFoundException e) {
				LOG.error("", e);
			}
		}
		
		sql = sqlMap.get(id);
		
		return sql; 
	} 
	
}
