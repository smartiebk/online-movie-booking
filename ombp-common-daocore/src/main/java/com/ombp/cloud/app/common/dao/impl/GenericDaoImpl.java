package com.ombp.cloud.app.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ombp.cloud.constants.AppConstant;
import com.ombp.cloud.context.ThreadLocalContext;
import com.ombp.cloud.model.BaseEntity;
import com.ombp.cloud.model.paging.Page;
import com.ombp.cloud.model.paging.PagingRequest;
import com.ombp.cloud.model.share.SharedPair;
import com.ombp.cloud.model.share.SharedWith;
import com.ombp.cloud.model.usermanagement.AuthenticatedUser;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

/**
 *
 * @param <T> the generic type
 * @param <PK> the generic type
 */
public  class GenericDaoImpl<T, PK extends Serializable> implements GenericDaoContract<T, PK> {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	/** The namespace. */
	private final String NAMESPACE = this.getClass().getName();

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = "fetchById";
	
	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID_WITH_MAP = "fetchByIdWithMap";
	
	private static final String SHARE_AND_SAVE = "shareAndSave";
	
	private static final String REMOVE_SHARING = "removeSharing";
	
	private static final String FETCH_SHARED_WITH = "fetchSharedWith";

	/** The Constant INSERT. */
	private static final String INSERT = "insert";

	/** The Constant UPDATE. */
	private static final String UPDATE = "update";

	/** The Constant DELETE. */
	private static final String DELETE = "delete";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "fetchAll";

	/** The Constant COUNT. */
	private static final String COUNT = "count";
	
	private static final String CHECK_VALIDITY = "containsAnyUnAuthorizedEntityId";

	/** The Constant LOGICALDELETE. */
	private static final String LOGICALDELETE = "logicalDelete";
	
	/** The Constant LOGICALDELETE. */
	private static final String LOGICALDELETE_SELECTED = "logicalDeleteSelected";

	/** The Constant EXISTCHILDENTITY. */
	private static final String EXISTCHILDENTITY = "existChildEntity";
	
	private static final String EXISTS_WITH_PARAMS = "existsWithParameters";

	/** The sql session. */
	@Autowired
	private SqlSession sqlSession;

	/**
	 * The Constructor.
	 */
	public GenericDaoImpl() {

	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected SqlSession getSession() {
		return this.sqlSession;
	}

	/**
	 * Gets the statement id with namespace.
	 *
	 * @param statementId the statement id
	 * @return the statement id with namespace
	 */
	protected String getStatementIdWithNamespace(String statementId) {
		return NAMESPACE + "." + statementId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#logicalDelete(java.lang.Object)
	 */
	@Override
	public Integer logicalDelete(T o) {
		
		if(o instanceof BaseEntity) 
		{
			((BaseEntity) o).setUserId((Integer)getCurrrentUserParameterMap().get("currentUserId"));
		}
		
		return getSession().update(getStatementIdWithNamespace(LOGICALDELETE), o);
	}

	@Override
	public Integer logicalDeleteSelected(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		return getSession().update(getStatementIdWithNamespace(LOGICALDELETE_SELECTED), params);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#exists(java.io.Serializable)
	 */
	@Override
	public Integer exists(PK id) {
		return 0;
	}

	@Override
	public Integer existsWithParameters(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		return getSession().selectOne(getStatementIdWithNamespace(EXISTS_WITH_PARAMS), params);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#fetchById(java.io.Serializable)
	 */
	@Override
	public T fetchById(PK id) {
		Map<String, Object> params = getCurrrentUserParameterMap();
		params.put("id", id);

		return getSession().selectOne(getStatementIdWithNamespace(FETCH_BY_ID), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#insert(java.lang.Object)
	 */
	@Override
	public T insert(T o) {
		if(o instanceof BaseEntity) 
		{
			((BaseEntity) o).setUserId((Integer)getCurrrentUserParameterMap().get("currentUserId"));
		}
		getSession().insert(getStatementIdWithNamespace(INSERT), o);
		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public Integer update(T o) {
		if(o instanceof BaseEntity) 
		{
			((BaseEntity) o).setUserId((Integer)getCurrrentUserParameterMap().get("currentUserId"));
		}
		return getSession().update(getStatementIdWithNamespace(UPDATE), o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public Integer delete(T o) {
		if(o instanceof BaseEntity) 
		{
			((BaseEntity) o).setUserId((Integer)getCurrrentUserParameterMap().get("currentUserId"));
		}
		return getSession().delete(getStatementIdWithNamespace(DELETE), o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#fetchAll(java.util.Map)
	 */
	@Override
	public List<T> fetchAll(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		return getSession().selectList(getStatementIdWithNamespace(FETCH_ALL), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.heartbeat.framework.dao.GenericDao#fetchWithPageSearchSort(java.util.Map)
	 */
	@Override
	public Page<T> fetchWithPageSearchSort(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		List<T> items = getSession().selectList(getStatementIdWithNamespace("fetchWithPageSearchSort"), params);
		
		for(T obj : items) 
		{
			sanitizeForHTML(obj);
		}
		
		Integer count = count(params);
		Page<T> page = new Page<>(items);
		page.setRecordsFiltered((int) count);
		page.setRecordsTotal((int) count);
		
		if(params!=null && params.get("pagingRequest")!=null) {
			page.setDraw(((PagingRequest)params.get("pagingRequest")).getDraw());
		}
		
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#count(java.util.Map)
	 */
	@Override
	public Integer count(Map<String, Object> params) {
		
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		Integer count = getSession().selectOne(getStatementIdWithNamespace(COUNT), params);
		return count == null ? 0 : count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heartbeat.framework.dao.GenericDao#existChildEntity(java.util.Map)
	 */
	@Override
	public Integer existChildEntity(Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		return getSession().selectOne(getStatementIdWithNamespace(EXISTCHILDENTITY), params);
	}
	
	@Override
	public boolean containsAnyUnAuthorizedEntityId(List<Integer> ids) {
		
		Map<String, Object> params = getCurrrentUserParameterMap();
		
		params.put(AppConstant.IDS_COLLECTION, ids);
		
		Integer count = getSession().selectOne(getStatementIdWithNamespace(CHECK_VALIDITY), params);
		
		if(count==null) 
		{
			count = 0;
		}
		
		if(ids.size() == count) 
		{
			return false;
		}		
		return true;
	}

	@Override
	public T fetchById(PK id, Map<String, Object> params) {
		if(params!=null) 
		{
			params.putAll(getCurrrentUserParameterMap());	
		}
		params.put("id", id);
		return getSession().selectOne(getStatementIdWithNamespace(FETCH_BY_ID_WITH_MAP), params);
	}

	@Override
	public Map<String, Object> getCurrrentUserParameterMap() {
		
		AuthenticatedUser user = (AuthenticatedUser) ThreadLocalContext.getCurrentThreadMap()
				.get(AppConstant.THREAD_PREFIX_CURRENT_USER + String.valueOf(Thread.currentThread().getId()));
		
		Map<String, Object> params = DataParameterMap.getUserParams(user);
		return params;
	}

	@Override
	public void shareAndSave(SharedWith sharedWith) {
		
		Map<String, Object> params = getCurrrentUserParameterMap();
		
		params.put("sharedWithTargetIds", sharedWith.getIds());
		
		params.put("sharedWithIds", sharedWith.getSharedPairs());
		
		getSession().insert(getStatementIdWithNamespace(SHARE_AND_SAVE), params);
	}

	@Override
	public List<SharedPair> fetchSharedWith(SharedWith sharedWith) {
		Map<String, Object> params = getCurrrentUserParameterMap();
		
		params.put("sharedWith", sharedWith);
		
		List<SharedPair> pairs = getSession().selectList(getStatementIdWithNamespace(FETCH_SHARED_WITH), params);
		
		return pairs;
	}

	@Override
	public void removeSharing(SharedWith sharedWith) {
		
		Map<String, Object> params = getCurrrentUserParameterMap();
		
		params.put("sharedWithTargetIds", sharedWith.getIds());
		
		params.put("sharedWithIds", sharedWith.getSharedPairs());
		
		getSession().insert(getStatementIdWithNamespace(REMOVE_SHARING), params);
	}
	
	protected void sanitizeForHTML(T object)
	{
		try
		{
			Class<?> targetClass = object.getClass();
			Field[] actionProperties = targetClass.getDeclaredFields();
			for (Field field : actionProperties)
			{
				field.setAccessible(true);
				try
				{
						if(field.getType().equals("".getClass())) 
						{
							field.set(object, StringEscapeUtils.escapeHtml4((String) field.get(object)));
						}
				}
				catch (Exception e)
				{
					LOG.error("Error Occured while setting field value to field "+field.getName()+" of class:" + this.getClass());
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Error Occured while setting field value of class:" + this.getClass());
		}
	}
}
