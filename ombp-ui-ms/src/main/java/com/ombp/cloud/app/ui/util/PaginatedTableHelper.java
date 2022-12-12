package com.ombp.cloud.app.ui.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.model.paging.Column;
import com.ombp.cloud.model.paging.Direction;
import com.ombp.cloud.model.paging.Order;
import com.ombp.cloud.model.paging.TableLangFile;
import com.ombp.cloud.model.paging.TableMetadata;

public class PaginatedTableHelper implements UIConstants {

	private static final Logger LOG = LoggerFactory.getLogger(PaginatedTableHelper.class);

	public static TableMetadata getMetadataForPaginatedTables(String entityType, String allConfiguredLocale,
			String paginatedTableLangFileName, MessageSource messageSource, Map<String, Object> extraData) {
		if(extraData==null) 
		{
			extraData = new HashMap<String, Object>();
		}
		TableMetadata tableMetadata = new TableMetadata();
		tableMetadata.setEntityType(entityType);
		try {
			
			populateConfigurations();
			
			JSONObject tableConfig = null;
			if(extraData.get(CUSTOM_KEY_FOR_PAGINATED_TABLE)!=null) 
			{
				String customEntityKey = (String) extraData.get(CUSTOM_KEY_FOR_PAGINATED_TABLE);
				try {
					tableConfig = StaticDataHolder.getPaginatedTableCustomConfiguration().getJSONObject(customEntityKey);
				}catch(Exception exception) {
					//
				}
			}
			
			if(tableConfig==null) 
			{
				try {
					tableConfig = StaticDataHolder.getPaginatedTableConfiguration().getJSONObject(entityType);
				}catch(Exception exception) {
					//
				}
			}
			
			tableMetadata.setMessageLabelKey(getStringFromJSON(tableConfig, PAGINATED_TABLE_MSG_RSC_KEY));
			tableMetadata.setPageLength(getStringFromJSON(tableConfig, PAGINATED_TABLE_PAGE_LENGTH_KEY));
			tableMetadata.setLengthChange(getBooleanFromJSON(tableConfig, PAGINATED_TABLE_LENGTH_CHANGE_KEY));
			
			String url = getStringFromJSON(tableConfig, PAGINATED_TABLE_DATA_URL);
			
			if(extraData.get(UIConstants.P_TABLE_URL_DYNAMIC_ID)!=null) 
			{
				url = url.replace((String) extraData.get(UIConstants.P_TABLE_URL_DYNAMIC_FIELD_NAME_IN_URL),
						(String) extraData.get(UIConstants.P_TABLE_URL_DYNAMIC_ID));
			}
			
			if(extraData.get(UIConstants.P_TABLE_DATA_URL_PARAMETERS)!=null) 
			{
				Map<String, Object> urlParams = (Map<String, Object>) extraData.get(UIConstants.P_TABLE_DATA_URL_PARAMETERS);
				
				for(String key : urlParams.keySet()) 
				{
					url = url.replace(key, String.valueOf(urlParams.get(key)));
				}
			}
			
			tableMetadata.setDataURL(url);

			List<Column> columns = new ArrayList<>();

			List<String> permissions = new ArrayList<>(); 
					
			if(extraData.get(UIConstants.P_TABLE_ACTION_PERMISSIONS)!=null) 
			{
				permissions.addAll((List<String>) extraData.get(UIConstants.P_TABLE_ACTION_PERMISSIONS));
			}
			
			String primaryKey = "pkId";
			
			if(extraData.containsKey(UIConstants.P_TABLE_DATA_PRIMARY_KEY) && !extraData.containsKey(UIConstants.P_TABLE_DATA_PRIMARY_KEY_ADDITIONAL)) 
			{
				primaryKey = (String) extraData.get(UIConstants.P_TABLE_DATA_PRIMARY_KEY);
			}
			
			if(extraData.containsKey(UIConstants.P_TABLE_DATA_PRIMARY_KEY_ADDITIONAL)) 
			{
				primaryKey = (String) extraData.get(UIConstants.P_TABLE_DATA_PRIMARY_KEY_ADDITIONAL);
			}
			
			if (permissions.contains(SELECT_NEEDED)) {
				String select = tableConfig.getJSONObject(PAGINATED_TABLE_OTHER_COLUMNS).getString(SELECT_NEEDED);
				select = select.replaceAll("R_ID", "+data['"+primaryKey+"']+");
				Column column = new Column();
				column.setHeader("<input type='checkbox' data-toggle='tooltip' title='"+messageSource.getMessage("app.tables.select.tt", null, LocaleContextHolder.getLocale())+"' name='" + entityType + "' id='selectAll_" + entityType + "'>");
				column.setData("function(data){return " + select + "}");
				column.setOrderable(false);
				column.setIfDataAsFunction(true);
				column.setWidth("4%");
				column.setIfHeaderSpecial(true);
				columns.add(column);
			}

			permissions.remove(SELECT_NEEDED);

			JSONArray columnsConf = tableConfig.getJSONArray(PAGINATED_TABLE_COLUMNS);

			for (int i = 0; i < columnsConf.length(); i++) {
				Column column = new ObjectMapper().readValue(columnsConf.get(i).toString(), Column.class);
				
				if (column.isIfDataAsFunction() && CAN_CONFIGURE_ENTITY.equalsIgnoreCase(column.getLinkType())
						&& !permissions.contains(CAN_CONFIGURE_ENTITY)) {
					column.setData(column.getLinkProperty());
					column.setIfDataAsFunction(false);
				}
				
				columns.add(column);
			}
			String action = "";
			for (String permissionKey : permissions) {
				String select = ""; 
				try {
					select = tableConfig.getJSONObject(PAGINATED_TABLE_OTHER_COLUMNS).getString(permissionKey);
				}
				catch(Exception e) 
				{
					
				}
				if(select.length()!=0) 
				{
					select = select.replaceAll("R_ID", "+ data['"+primaryKey+"'] + ");
					action = action + select + "+";	
				}
			}
			if (action.length() != 0) {
				action = action.substring(0, action.length() - 1);
			}
			Column actionColumn = new Column();
			actionColumn.setData("function(data){return " + action + "}");
			actionColumn.setOrderable(false);
			actionColumn.setHeader("app.tables.header.actions");
			actionColumn.setIfDataAsFunction(true);
			actionColumn.setWidth("6%");
			if (action.length() != 0) {
				columns.add(actionColumn);
			}

			tableMetadata.setColumns(columns);

			List<Order> orderLi = new ArrayList<>();
			Order order = new Order();
			try {
				if (tableConfig.has(PAGINATED_TABLE_ORDER) && tableConfig.getJSONArray(PAGINATED_TABLE_ORDER) != null) {
					order.setColumn(tableConfig.getJSONArray(PAGINATED_TABLE_ORDER).getJSONObject(0)
							.getInt(PAGINATED_TABLE_COL_ORDER));
					order.setDir(Direction.valueOf(tableConfig.getJSONArray(PAGINATED_TABLE_ORDER).getJSONObject(0)
							.getString(PAGINATED_TABLE_SORT)));
					orderLi.add(order);
				}
			} catch (Exception e) {
				LOG.error("", e);
			}
			tableMetadata.setOrder(orderLi);
			
		} catch (IOException e) {
			LOG.error("", e);
		}

		tableMetadata.setTableLangFiles(getTableLangFiles(tableMetadata.getMessageLabelKey(), allConfiguredLocale, paginatedTableLangFileName, messageSource));
		
		String jsonEquivalant = "";
		try {
			jsonEquivalant = new ObjectMapper().writeValueAsString(tableMetadata);
		} catch (JsonProcessingException e) {
			LOG.error("", e);
		}
		
		try {
			tableMetadata.setJsonEquivalant(Base64.getEncoder().encodeToString(jsonEquivalant.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			LOG.error("", e);
		}
		
		return tableMetadata;
	}

	private static void populateConfigurations() {
		boolean customConfigCached = StaticDataHolder.getPaginatedTableCustomConfiguration() != null;
		
		if(!customConfigCached) {
			String customConfigContent = CommonFileUtil.getFileContentAsString(PAGINATED_TABLE_CUSTOM_CONFIG_FILE);
			JSONObject customConfigObj = new JSONObject(customConfigContent);
			StaticDataHolder.setPaginatedTableCustomConfiguration(customConfigObj);
		}
		
		boolean configCached = StaticDataHolder.getPaginatedTableConfiguration() != null;
				
		if (!configCached) {
			String configContent = CommonFileUtil.getFileContentAsString(PAGINATED_TABLE_CONFIG_FILE);
			JSONObject configObj = new JSONObject(configContent);
			StaticDataHolder.setPaginatedTableConfiguration(configObj);
		}
	}

	/**
	 * This method populates language specific paginated tables lang files
	 * @param entityKeyLablelKey
	 * @param messageSource 
	 * @param paginatedTableLangFileName 
	 * @param allConfiguredLocale 
	 * @return
	 */
	private static List<TableLangFile> getTableLangFiles(String entityKeyLablelKey, String allConfiguredLocale,
			String paginatedTableLangFileName, MessageSource messageSource) {
		
		List<String> allAvailableLocales = new ArrayList<>();
		
		List<TableLangFile> tableLangFiles = new ArrayList<>();
		
		if(allConfiguredLocale!=null && allConfiguredLocale.length()!=0) 
		{
			allAvailableLocales = Arrays.asList(allConfiguredLocale.split(","));
		}
		
		for(String locale : allAvailableLocales) 
		{
			try {
				String content = StaticDataHolder.getTableLangFilesContent().get(locale) != null
						? StaticDataHolder.getTableLangFilesContent().get(locale)
						: FileUtils.readFileToString(
						ResourceUtils.getFile(
								"classpath:static/jsons/" + paginatedTableLangFileName + "_" + locale + ".json"),
						StandardCharsets.UTF_8.toString());
				
				if(!StaticDataHolder.getTableLangFilesContent().containsKey(locale)) 
				{
					StaticDataHolder.getTableLangFilesContent().put(locale, content);
				}		
						
				content = content.replaceAll(ENTITY_NAME_TABLE_LANG_FILE_CONST,
						messageSource.getMessage(entityKeyLablelKey, null, new Locale(locale)));
				
				tableLangFiles.add(new TableLangFile(content, locale));
				
			} catch (IOException e) {
				//LOG.error("", e);
			}
		}
		return tableLangFiles;
	}
	
	private static String getStringFromJSON(JSONObject jsonObject, String key) 
	{
		String value = "";
		
		try {
			value = jsonObject.getString(key);
		}
		catch(Exception e) 
		{
			//LOG.error("", e);
		}
		return value;
	}
	
	private static Boolean getBooleanFromJSON(JSONObject jsonObject, String key) 
	{
		Boolean value = null;
		
		try {
			value = jsonObject.getBoolean(key);
		}
		catch(Exception e) 
		{
			//LOG.error("", e);
		}
		return value;
	}
}
