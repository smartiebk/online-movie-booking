package com.ombp.cloud.app.hm.dao;

import java.util.List;

import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface TheatreDao extends GenericDaoContract<Theatre, Integer>{

	List<Theatre> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria);

}
