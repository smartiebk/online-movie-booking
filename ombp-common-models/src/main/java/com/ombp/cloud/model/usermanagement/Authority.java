package com.ombp.cloud.model.usermanagement;

import java.io.Serializable;

import com.ombp.cloud.model.BaseEntity;

public class Authority extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8490234493475520382L;
	private String authority;

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return authority;
	}	

	
	
}