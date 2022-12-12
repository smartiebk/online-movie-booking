package com.ombp.cloud.app.hm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.common.dao.impl.GenericDaoImpl;
import com.ombp.cloud.app.hm.dao.MovieShowDao;
import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;

@Repository
public class MovieShowDaoImpl extends GenericDaoImpl<MovieShow, Integer> implements MovieShowDao {

	@Override
	public MovieShow insertMovieShow(MovieShow movieShow) {
		getSession().insert("insertMovieShow", movieShow);
		return movieShow;
	}

	@Override
	public MovieShow updateMovieShow(MovieShow movieShow) {
		getSession().insert("updateMovieShow", movieShow);
		return movieShow;
	}

	@Override
	public Movie fetchMovieByMovieName(String movieName) {
		return getSession().selectOne("fetchMovieByMovieName", movieName);
	}

	@Override
	public List<Screen> fetchAllScreensByTheatreId(Integer theatreId) {
		return getSession().selectList("fetchAllScreensByTheatreId", theatreId);
	}

	@Override
	public List<MovieShow> fetchAllShowsByTheatreId(Integer theatreId) {
		return getSession().selectList("fetchAllShowsByTheatreId", theatreId);
	}

}