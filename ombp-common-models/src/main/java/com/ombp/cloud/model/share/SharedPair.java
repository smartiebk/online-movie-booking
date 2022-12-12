/**
 * 
 */
package com.ombp.cloud.model.share;

import java.util.Date;

import com.ombp.cloud.model.BaseEntity;

/**
 * @author PranayKathade
 *
 */
public class SharedPair extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2762396128948421294L;

	private Integer sharedType;
	
	private String entityName;
	
	private String entityDescription;
	
	private Date validTillDate;

	public Integer getSharedType() {
		return sharedType;
	}

	public void setSharedType(Integer sharedType) {
		this.sharedType = sharedType;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityDescription() {
		return entityDescription;
	}

	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}

	public Date getValidTillDate() {
		return validTillDate;
	}

	public void setValidTillDate(Date validTillDate) {
		this.validTillDate = validTillDate;
	}

}
