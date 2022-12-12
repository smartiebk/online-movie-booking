package com.ombp.cloud.util.dbcontract;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ombp.cloud.model.paging.Page;
import com.ombp.cloud.model.share.SharedPair;
import com.ombp.cloud.model.share.SharedWith;

public interface GenericDaoContract <T, PK extends Serializable> {

	Integer exists(PK id);
	
	Integer existsWithParameters(Map<String, Object> params);

	Integer existChildEntity(Map<String, Object> params);
	
	T fetchById(PK id);
	
	T fetchById(PK id, Map<String, Object> params);

	T insert(T o);

	Integer update(T o);

	Integer delete(T o);
	
	Integer logicalDelete(T o);
	
	Integer logicalDeleteSelected(Map<String, Object> params);

	List<T> fetchAll(Map<String, Object> params);
	
	Integer count(Map<String, Object> params);

	Page<T> fetchWithPageSearchSort(Map<String, Object> params);
	
	boolean containsAnyUnAuthorizedEntityId(List<Integer> ids);
	
	Map<String, Object> getCurrrentUserParameterMap();
	
	void shareAndSave(SharedWith sharedWith);
	
	List<SharedPair> fetchSharedWith(SharedWith sharedWith);
	
	void removeSharing(SharedWith sharedWith);

}