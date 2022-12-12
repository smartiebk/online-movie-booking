/**
 * 
 */
package com.ombp.cloud.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author PranayKathade
 *
 */
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2538728790069222345L;

	private Integer id;
	
	private String pkId;
	
	private int isDeleted;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer userId;
	
	private Integer craetedByUserId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public BaseEntity() {
		super();
	}

	public Integer getCraetedByUserId() {
		return craetedByUserId;
	}

	public void setCraetedByUserId(Integer craetedByUserId) {
		this.craetedByUserId = craetedByUserId;
	}
}
