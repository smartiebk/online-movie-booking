package com.ombp.cloud.model.bs;

import com.ombp.cloud.model.BaseEntity;

public class Address extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -327740040799135928L;

	private String type;
	
	private City city;
	
	private Integer cityId;
	
	private String area;
	
	private String pincode;
	
	private String landmark;
	
	private String addressLines;
	
	private boolean primary;
	
	private String primaryPhone;
	
	private String secondaryPhone;
	
	private String alternatePhone;
	
	private String emailAddressCS;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getAddressLines() {
		return addressLines;
	}

	public void setAddressLines(String addressLines) {
		this.addressLines = addressLines;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getEmailAddressCS() {
		return emailAddressCS;
	}

	public void setEmailAddressCS(String emailAddressCS) {
		this.emailAddressCS = emailAddressCS;
	}
	
}