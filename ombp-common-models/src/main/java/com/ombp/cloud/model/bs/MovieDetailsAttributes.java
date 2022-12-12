package com.ombp.cloud.model.bs;

import com.ombp.cloud.model.BaseEntity;

public class MovieDetailsAttributes extends BaseEntity {

	private static final long serialVersionUID = 2674103264436766155L;

	private String attributeName;
	
	private String attributeConstant;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeConstant() {
		return attributeConstant;
	}

	public void setAttributeConstant(String attributeConstant) {
		this.attributeConstant = attributeConstant;
	}
}