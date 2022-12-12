package com.ombp.cloud.app.ui.controllers.on;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.ui.api.OpenAuthServerAPI;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.constants.UIURLConstants;
import com.ombp.cloud.app.ui.exception.ServiceDownIOException;
import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;

@RestController
public class TheatreController implements UIURLConstants, UIConstants {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Autowired
	private OpenAuthServerAPI businessServerAPI;

	@RequestMapping(value = LIST_ALL_THEATRES_URI, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Theatre> fetchAllTheatreBySearchCriteria(@RequestBody SearchCriteria searchCriteria) throws ServiceDownIOException {
		
		List<Theatre> theatres = null;
		
		try {
			theatres = businessServerAPI.fetchAllTheatreBySearchCriteria(searchCriteria);
		} catch (IOException e) {
			LOG.error("", e);
			throw new ServiceDownIOException(e.getMessage());
		}
		
		return theatres;
	}
	
}
