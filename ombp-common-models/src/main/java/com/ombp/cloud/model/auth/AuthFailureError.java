package com.ombp.cloud.model.auth;

public class AuthFailureError {
	
	private Integer status;
	
	private String error;
	
	private String description;


	public AuthFailureError(Integer status, String error, String description) {
		super();
		this.status = status;
		this.error = error;
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
