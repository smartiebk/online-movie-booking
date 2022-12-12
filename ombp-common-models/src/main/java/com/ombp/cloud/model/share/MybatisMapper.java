package com.ombp.cloud.model.share;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mapper")
public class MybatisMapper {


    private List<MapperSql> sqls;

    @XmlElement(name="sql")
	public List<MapperSql> getSqls() {
		return sqls;
	}

	public void setSqls(List<MapperSql> sqls) {
		this.sqls = sqls;
	}
	
}