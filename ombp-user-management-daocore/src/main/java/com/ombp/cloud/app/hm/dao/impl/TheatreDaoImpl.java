package com.ombp.cloud.app.hm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.common.dao.impl.GenericDaoImpl;
import com.ombp.cloud.app.hm.dao.TheatreDao;
import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;

@Repository
public class TheatreDaoImpl extends GenericDaoImpl<Theatre, Integer> implements TheatreDao {

	@Override
	public List<Theatre> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria) {
		return getSession().selectList("fetchAllTheatreBySearchCriteria", searchCriteria);
	}
}