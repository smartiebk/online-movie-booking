package com.ombp.cloud.model.paging;

import java.io.Serializable;

public class TableLangFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3392149790400967151L;

	private String content;
	
	private String locale;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public TableLangFile(String content, String locale) {
		super();
		this.content = content;
		this.locale = locale;
	}

	public TableLangFile() {
		super();
	}
	

}
