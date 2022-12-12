package com.ombp.cloud.app.hm.service;

import java.util.List;

import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;

public interface MovieShowService {

	MovieShow saveMovieShow(MovieShow movieShow);

	Movie fetchMovieByMovieName(String movieName);

	List<Screen> fetchAllScreensByTheatreId(Integer theatreId);

	List<MovieShow> fetchAllShowsByTheatreId(Integer theatreId);
	
	void deleteMovieShow(MovieShow movieShow);

}
