package com.ombp.cloud.model.paging;

import java.util.List;


public class PagingRequest {

    private int start;
    private int length;
    private int draw;
    private List<Order> order;
    private List<Column> columns;
    private Search search;
    private int offset;
    private String entityType;
    
    private String currentSortProperty;
    private String currentSortOrder;
    
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
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
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public int getOffset() {
		this.offset= this.start;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getCurrentSortProperty() {
		currentSortProperty = currentSortProperty == null ? this.order.size()==0 ? null : this.columns.get(this.order.get(0).getColumn()).getName() :currentSortProperty;
		return currentSortProperty;
	}
	public void setCurrentSortProperty(String currentSortProperty) {
		this.currentSortProperty = currentSortProperty;
	}
	public String getCurrentSortOrder() {
		currentSortOrder = currentSortOrder==null ? this.order.size()==0 ? null : this.order.get(0).getDir().name() : currentSortOrder;
		return currentSortOrder;
	}
	public void setCurrentSortOrder(String currentSortOrder) {
		this.currentSortOrder = currentSortOrder;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

}
