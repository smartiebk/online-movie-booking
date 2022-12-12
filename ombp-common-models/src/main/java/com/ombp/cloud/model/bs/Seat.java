package com.ombp.cloud.model.bs;

import com.ombp.cloud.model.BaseEntity;

public class Seat extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -206953574925921667L;

	private String seatName;
	
	private SeatCategory seatCategory;
	
	private Integer seatCategoryId;

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public SeatCategory getSeatCategory() {
		return seatCategory;
	}

	public void setSeatCategory(SeatCategory seatCategory) {
		this.seatCategory = seatCategory;
	}

	public Integer getSeatCategoryId() {
		return seatCategoryId;
	}

	public void setSeatCategoryId(Integer seatCategoryId) {
		this.seatCategoryId = seatCategoryId;
	}	
}