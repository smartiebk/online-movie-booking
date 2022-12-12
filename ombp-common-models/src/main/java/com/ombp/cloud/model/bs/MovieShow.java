package com.ombp.cloud.model.bs;

import java.util.Date;

import com.ombp.cloud.model.BaseEntity;

public class MovieShow extends BaseEntity {

	private static final long serialVersionUID = -3682667177697870971L;

	private Movie movie;
	
	private Integer movieId;
	
	private Integer screenId;
	
	private Screen screen;
	
	private Date showDate;
	
	private Date showTimingFrom;
	
	private Date showTimingTo;
	
	private Date showStartDate;
	
	private Date showEndDate;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Date getShowTimingFrom() {
		return showTimingFrom;
	}

	public void setShowTimingFrom(Date showTimingFrom) {
		this.showTimingFrom = showTimingFrom;
	}

	public Date getShowTimingTo() {
		return showTimingTo;
	}

	public void setShowTimingTo(Date showTimingTo) {
		this.showTimingTo = showTimingTo;
	}

	public Date getShowStartDate() {
		return showStartDate;
	}

	public void setShowStartDate(Date showStartDate) {
		this.showStartDate = showStartDate;
	}

	public Date getShowEndDate() {
		return showEndDate;
	}

	public void setShowEndDate(Date showEndDate) {
		this.showEndDate = showEndDate;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	
}