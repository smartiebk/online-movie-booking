package com.ombp.cloud.app.hm.service;

import java.util.List;

import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;

public interface TheatreService {

	List<Theatre> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria);
}
