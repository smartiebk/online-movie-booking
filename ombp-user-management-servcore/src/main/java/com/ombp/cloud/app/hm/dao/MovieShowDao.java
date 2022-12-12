package com.ombp.cloud.app.hm.dao;

import java.util.List;

import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;
import com.ombp.cloud.util.dbcontract.GenericDaoContract;

public interface MovieShowDao extends GenericDaoContract<MovieShow, Integer>{

	MovieShow insertMovieShow(MovieShow movieShow);
	
	MovieShow updateMovieShow(MovieShow movieShow);

	Movie fetchMovieByMovieName(String movieName);

	List<Screen> fetchAllScreensByTheatreId(Integer theatreId);

	List<MovieShow> fetchAllShowsByTheatreId(Integer theatreId);

}
