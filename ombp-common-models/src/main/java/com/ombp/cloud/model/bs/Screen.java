package com.ombp.cloud.model.bs;

import java.util.List;

import com.ombp.cloud.model.BaseEntity;

public class Screen extends BaseEntity {
	
	private static final long serialVersionUID = -1637070504548851061L;

	private String screenName;
	
	private Theatre theatre;
	
	private Integer theatreId;
	
	private List<SeatCategory> seatCategories;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Integer getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}

	public List<SeatCategory> getSeatCategories() {
		return seatCategories;
	}

	public void setSeatCategories(List<SeatCategory> seatCategories) {
		this.seatCategories = seatCategories;
	}
	
}