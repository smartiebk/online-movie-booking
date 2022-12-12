package com.ombp.cloud.model.paging;

public class Column {

    private String data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private Search search;
    private String width;
    private String header;
    private boolean ifDataAsFunction;
    private boolean ifHeaderSpecial;
    private String linkType;
    private String linkProperty;
    private String overridenHeader;
    
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSearchable() {
		return searchable;
	}
	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}
	public Boolean getOrderable() {
		return orderable;
	}
	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public Column() {
		super();
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public boolean isIfDataAsFunction() {
		return ifDataAsFunction;
	}
	public void setIfDataAsFunction(boolean ifDataAsFunction) {
		this.ifDataAsFunction = ifDataAsFunction;
	}
	public boolean isIfHeaderSpecial() {
		return ifHeaderSpecial;
	}
	public void setIfHeaderSpecial(boolean ifHeaderSpecial) {
		this.ifHeaderSpecial = ifHeaderSpecial;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getLinkProperty() {
		return linkProperty;
	}
	public void setLinkProperty(String linkProperty) {
		this.linkProperty = linkProperty;
	}
	public String getOverridenHeader() {
		return overridenHeader;
	}
	public void setOverridenHeader(String overridenHeader) {
		this.overridenHeader = overridenHeader;
	}
}
