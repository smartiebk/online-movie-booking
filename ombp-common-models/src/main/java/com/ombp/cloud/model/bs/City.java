package com.ombp.cloud.model.bs;

import com.ombp.cloud.model.BaseEntity;

public class City extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752817051925665646L;
	
	private String cityName;
	
	private String stateName;
	
	private String country;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
