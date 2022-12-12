package com.ombp.cloud.app.ui.controllers.on;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.ui.api.OpenAuthServerAPI;
import com.ombp.cloud.app.ui.constants.UIConstants;
import com.ombp.cloud.app.ui.constants.UIURLConstants;
import com.ombp.cloud.app.ui.exception.ServiceDownIOException;
import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;
import com.ombp.cloud.model.ui.ResponseData;

@RestController
public class MovieShowController implements UIURLConstants, UIConstants {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Autowired
	private OpenAuthServerAPI businessServerAPI;

	
	@RequestMapping(value = SAVE_MOVIE_SHOW, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData saveMovieShow(@RequestBody MovieShow movieShow) throws ServiceDownIOException {
		try {
			movieShow = businessServerAPI.saveMovieShow(movieShow);
		} catch (IOException e) {
			LOG.error("", e);
			throw new ServiceDownIOException(e.getMessage());
		}
		ResponseData r = new ResponseData("SUCCESS");
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("movieShow", movieShow);
		r.setData(responseData);
		
		return r;
	}
	
	@RequestMapping(value = DELETE_MOVIE_SHOW, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData deleteMovieShow(@RequestBody MovieShow movieShow) throws ServiceDownIOException {
		try {
			movieShow = businessServerAPI.deleteMovieShow(movieShow);
		} catch (IOException e) {
			LOG.error("", e);
			throw new ServiceDownIOException(e.getMessage());
		}
		ResponseData r = new ResponseData("Delete SUCCESS");
		
		return r;
	}
	
	@RequestMapping(value = MOVIE_BY_NAME_URI, method = RequestMethod.GET)
	public Movie fetchMovieByMovieName(@PathVariable("movieName") String movieName) throws ServiceDownIOException {
		
		Movie movie = null;
		
		try {
			movie = businessServerAPI.fetchMovieByMovieName(movieName);
		} catch (IOException e) {
			LOG.error("", e);
			throw new ServiceDownIOException(e.getMessage());
		}
		
		return movie;
	}
	
	@RequestMapping(value = LIST_ALL_SCREENS_URI, method = RequestMethod.GET)
	public List<Screen> fetchAllScreensByTheatreId(@PathVariable("theatreId") Integer theatreId) throws ServiceDownIOException {
		
		List<Screen> screens = null;
		
		try {
			screens = businessServerAPI.fetchAllScreensByTheatreId(theatreId);
		} catch (IOException e) {
			LOG.error("", e);
			throw new ServiceDownIOException(e.getMessage());
		}
		
		return screens;
	}

	
	@RequestMapping(value = LIST_ALL_MOVIE_SHOWS_URI, method = RequestMethod.GET)
	public List<MovieShow> fetchAllShowsByTheatreId(@PathVariable("theatreId") Integer theatreId) throws ServiceDownIOException {
		
		List<MovieShow> movieShows = null;
		
		try {
			movieShows = businessServerAPI.fetchAllShowsByTheatreId(theatreId);
		} catch (IOException e) {
			LOG.error("", e);
			throw new ServiceDownIOException(e.getMessage());
		}
		
		return movieShows;
	}
	
}
