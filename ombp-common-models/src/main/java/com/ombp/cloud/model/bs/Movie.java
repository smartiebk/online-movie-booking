package com.ombp.cloud.model.bs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ombp.cloud.model.BaseEntity;

public class Movie extends BaseEntity {

	private static final long serialVersionUID = -7087876408489145807L;

	private String movieName;
	
	private Date releaseDate;
	
	private List<MovieDetails> movieDetails;
	
	private List<Theatre> theatres;
	
	private Map<String, List<MovieDetails>> movieDetailsMap;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<MovieDetails> getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(List<MovieDetails> movieDetails) {
		this.movieDetails = movieDetails;
	}

	public Map<String, List<MovieDetails>> getMovieDetailsMap() {
		
		Map<String, List<MovieDetails>> map = null;
		
		if(this.movieDetails!=null && !this.movieDetails.isEmpty()) {
			map = new HashMap<>();
			
			for(MovieDetails details : this.movieDetails) 
			{
				if(map.containsKey(details.getMovieDetailsAttributes().getAttributeConstant())) {
					List<MovieDetails> li = map.get(details.getMovieDetailsAttributes().getAttributeConstant());
					li.add(details);
				}
				else {
					List<MovieDetails> li = new ArrayList<>();
					li.add(details);
					map.put(details.getMovieDetailsAttributes().getAttributeConstant(), li);
				}
			}
		}
		
		this.movieDetailsMap = map;
		
		return movieDetailsMap;
	}

	public List<Theatre> getTheatres() {
		return theatres;
	}

	public void setTheatres(List<Theatre> theatres) {
		this.theatres = theatres;
	}

	public void setMovieDetailsMap(Map<String, List<MovieDetails>> movieDetailsMap) {
		this.movieDetailsMap = movieDetailsMap;
	}
	
}