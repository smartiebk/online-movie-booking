package com.ombp.cloud.app.openendpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ombp.cloud.app.hm.service.MovieShowService;
import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;

@RestController
public class MovieShowEndPoint implements EndPointConstants {
	
	@Autowired
	private MovieShowService movieShowService;
	
	@RequestMapping(value = "/open/save/movieshow", method = RequestMethod.POST)
	public MovieShow saveMovieShow(@RequestBody MovieShow movieShow) throws Exception {
		movieShowService.saveMovieShow(movieShow);
		return movieShow;
	}
	
	@RequestMapping(value = "/open/delete/movieshow", method = RequestMethod.POST)
	public MovieShow deleteMovieShow(@RequestBody MovieShow movieShow) throws Exception {
		movieShowService.deleteMovieShow(movieShow);
		return movieShow;
	}
	
	@RequestMapping(value = "/open/fetch/moviebyname/{movieName}", method = RequestMethod.GET)
	public Movie fetchMovieByMovieName(@PathVariable("movieName") String movieName) throws Exception {
		Movie m = movieShowService.fetchMovieByMovieName(movieName);
		return m;
	}
	
	@RequestMapping(value = "/open/fetch/screens/{theatreId}", method = RequestMethod.GET)
	public List<Screen> fetchAllScreensByTheatreId(@PathVariable("theatreId") Integer theatreId) throws Exception {
		List<Screen> li = movieShowService.fetchAllScreensByTheatreId(theatreId);
		return li;
	}
	
	@RequestMapping(value = "/open/fetch/shows/{theatreId}", method = RequestMethod.GET)
	public List<MovieShow> fetchAllShowsByTheatreId(@PathVariable("theatreId") Integer theatreId) throws Exception {
		List<MovieShow> li = movieShowService.fetchAllShowsByTheatreId(theatreId);
		return li;
	}
	
}