package com.ombp.cloud.app.openendpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.hm.service.TheatreService;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;

@RestController
public class TheatreEndPoint implements EndPointConstants {
	
	@Autowired
	private TheatreService theatreService;
	
	@RequestMapping(value = "/open/search/theatres", method = RequestMethod.POST)
	public List<Theatre> fetchAllTheatreBySearchCriteria(@RequestBody SearchCriteria searchCriteria) throws Exception {
		List<Theatre> li = theatreService.fetchAllTheatreBySearchCriteria(searchCriteria);
		return li;
	}
}