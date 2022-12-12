package com.ombp.cloud.model.bs;

import java.util.List;

import com.ombp.cloud.model.BaseEntity;

public class Theatre extends BaseEntity {
	
	private static final long serialVersionUID = -4891309351912664108L;

	private String theatreName;
	
	private Address address;
	
	private Integer addressId;
	
	private List<Screen> screens;
	
	private List<MovieShow> movieShows;

	public List<MovieShow> getMovieShows() {
		return movieShows;
	}

	public void setMovieShows(List<MovieShow> movieShows) {
		this.movieShows = movieShows;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}
	
}