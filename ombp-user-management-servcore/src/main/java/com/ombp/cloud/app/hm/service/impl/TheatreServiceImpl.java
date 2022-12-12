package com.ombp.cloud.app.hm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.hm.dao.TheatreDao;
import com.ombp.cloud.app.hm.service.TheatreService;
import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;

@Service
public class TheatreServiceImpl implements TheatreService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TheatreDao theatreDao;
	
	@Override
	public List<Theatre> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria) {
		return theatreDao.fetchAllTheatreBySearchCriteria(searchCriteria);
	}

}
