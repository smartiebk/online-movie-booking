/**
 * 
 */
package com.ombp.cloud.model.share;

import java.util.List;

import com.ombp.cloud.model.BaseEntity;

/**
 * @author PranayKathade
 *
 */
public class SharedWith extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715580441244075928L;
	
	private Class typeOfSharedEntity;
	
	private List<String> pkIds;
	
	private List<Integer> ids;
	
	private List<SharedPair> sharedPairs;

	public Class getTypeOfSharedEntity() {
		return typeOfSharedEntity;
	}

	public void setTypeOfSharedEntity(Class typeOfSharedEntity) {
		this.typeOfSharedEntity = typeOfSharedEntity;
	}

	public List<String> getPkIds() {
		return pkIds;
	}

	public void setPkIds(List<String> pkIds) {
		this.pkIds = pkIds;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<SharedPair> getSharedPairs() {
		return sharedPairs;
	}

	public void setSharedPairs(List<SharedPair> sharedPairs) {
		this.sharedPairs = sharedPairs;
	}
	
}
