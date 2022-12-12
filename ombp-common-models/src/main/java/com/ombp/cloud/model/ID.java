package com.ombp.cloud.model;

public class ID {

	private String pkId;
	
	private Integer id;

	private Integer idFromPkId;
	
	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public Integer getId() throws Exception {
		this.id = getIdFromPkId();
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdFromPkId() throws Exception 
	{
		if(this.pkId!=null && this.pkId.trim().length()!=0) 
		{
			this.idFromPkId = Integer.valueOf(new RequestConvertor<String>(this.pkId).getObject());	
		}
		else 
		{
			this.idFromPkId = this.id;
		}
		return idFromPkId;
	}
	
}
