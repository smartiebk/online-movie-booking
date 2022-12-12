package com.ombp.cloud.model.bs;

import com.ombp.cloud.model.BaseEntity;

public class MovieDetails extends BaseEntity {

	private static final long serialVersionUID = 7056496975894078005L;

	private Integer movieId;
	
	private Integer movieDetailsAttributeId;
	
	private MovieDetailsAttributes movieDetailsAttributes;
	
	private String keyText;
	
	private String valueText;

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getMovieDetailsAttributeId() {
		return movieDetailsAttributeId;
	}

	public void setMovieDetailsAttributeId(Integer movieDetailsAttributeId) {
		this.movieDetailsAttributeId = movieDetailsAttributeId;
	}

	public MovieDetailsAttributes getMovieDetailsAttributes() {
		return movieDetailsAttributes;
	}

	public void setMovieDetailsAttributes(MovieDetailsAttributes movieDetailsAttributes) {
		this.movieDetailsAttributes = movieDetailsAttributes;
	}

	public String getKeyText() {
		return keyText;
	}

	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

	public String getValueText() {
		return valueText;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
	
}