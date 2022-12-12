package com.ombp.cloud.model.paging;

import java.io.Serializable;

public class Order implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4391593065469566607L;
	private Integer column;
    private Direction dir;
	public Integer getColumn() {
		return column;
	}
	public void setColumn(Integer column) {
		this.column = column;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
    
}
