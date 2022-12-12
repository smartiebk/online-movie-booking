package com.ombp.cloud.model.paging;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TableMetadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8556170330570973400L;

	private List<TableLangFile> tableLangFiles;
	
	private String entityType;
	
	private String messageLabelKey;
	
	private String dataURL;
	
	private List<Order> order;
	
    private List<Column> columns;
    
    private Map<String, String> otherColumns;
    
    private String jsonEquivalant;
    
    private String pageLength;
    
    private Boolean lengthChange;
    
	public List<TableLangFile> getTableLangFiles() {
		return tableLangFiles;
	}

	public void setTableLangFiles(List<TableLangFile> tableLangFiles) {
		this.tableLangFiles = tableLangFiles;
	}

	public String getDataURL() {
		return dataURL;
	}

	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getMessageLabelKey() {
		return messageLabelKey;
	}

	public void setMessageLabelKey(String messageLabelKey) {
		this.messageLabelKey = messageLabelKey;
	}

	public Map<String, String> getOtherColumns() {
		return otherColumns;
	}

	public void setOtherColumns(Map<String, String> otherColumns) {
		this.otherColumns = otherColumns;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getJsonEquivalant() {
		return jsonEquivalant;
	}

	public void setJsonEquivalant(String jsonEquivalant) {
		this.jsonEquivalant = jsonEquivalant;
	}

	public String getPageLength() {
		return pageLength;
	}

	public void setPageLength(String pageLength) {
		this.pageLength = pageLength;
	}

	public Boolean getLengthChange() {
		return lengthChange;
	}

	public void setLengthChange(Boolean lengthChange) {
		this.lengthChange = lengthChange;
	}

}