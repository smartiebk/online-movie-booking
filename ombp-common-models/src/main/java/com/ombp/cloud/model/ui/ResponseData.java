package com.ombp.cloud.model.ui;

import java.io.Serializable;
import java.util.Map;

public class ResponseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9024027497341143708L;

	private String statusMsg;
	
	private Map<String, Object> data;

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public ResponseData(String statusMsg) {
		super();
		this.statusMsg = statusMsg;
	}

	public ResponseData() {
		super();
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
