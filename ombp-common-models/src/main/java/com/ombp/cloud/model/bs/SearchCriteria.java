package com.ombp.cloud.model.bs;

import java.util.Date;

import com.ombp.cloud.model.BaseEntity;

public class SearchCriteria extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7383795662075673718L;
	
	private Date showStartTiming;
	
	private Date showDate;
	
	private String city;
	
	private String movieName;
	
	private String language;

	public Date getShowStartTiming() {
		return showStartTiming;
	}

	public void setShowStartTiming(Date showStartTiming) {
		this.showStartTiming = showStartTiming;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
}