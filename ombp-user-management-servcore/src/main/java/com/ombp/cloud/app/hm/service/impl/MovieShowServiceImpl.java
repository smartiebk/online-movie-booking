package com.ombp.cloud.app.hm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ombp.cloud.app.hm.dao.MovieShowDao;
import com.ombp.cloud.app.hm.service.MovieShowService;
import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;

@Service
public class MovieShowServiceImpl implements MovieShowService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MovieShowDao movieShowDao;
	
	@Override
	public MovieShow saveMovieShow(MovieShow movieShow) {
		if(movieShow.getId()==null) 
		{
			movieShowDao.insertMovieShow(movieShow);
		}
		else 
		{
			movieShowDao.updateMovieShow(movieShow);
		}
		return movieShow;
	}

	@Override
	public Movie fetchMovieByMovieName(String movieName) {
		return movieShowDao.fetchMovieByMovieName(movieName);
	}

	@Override
	public List<Screen> fetchAllScreensByTheatreId(Integer theatreId) {
		return movieShowDao.fetchAllScreensByTheatreId(theatreId);
	}

	@Override
	public List<MovieShow> fetchAllShowsByTheatreId(Integer theatreId) {
		return movieShowDao.fetchAllShowsByTheatreId(theatreId);
	}

	@Override
	public void deleteMovieShow(MovieShow movieShow) {
		movieShowDao.logicalDelete(movieShow);
	}

}
