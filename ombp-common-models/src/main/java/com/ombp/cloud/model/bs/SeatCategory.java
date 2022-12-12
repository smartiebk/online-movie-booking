package com.ombp.cloud.model.bs;

import java.util.List;

import com.ombp.cloud.model.BaseEntity;

public class SeatCategory extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7235559988122026501L;

	private String seatCategoryName;
	
	private Screen screen;
	
	private Integer screenId;
	
	private List<Seat> seats;

	public String getSeatCategoryName() {
		return seatCategoryName;
	}

	public void setSeatCategoryName(String seatCategoryName) {
		this.seatCategoryName = seatCategoryName;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
}