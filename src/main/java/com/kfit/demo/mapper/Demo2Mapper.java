package com.kfit.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kfit.demo.bean.Demo2;

public interface Demo2Mapper {
	
	@Insert("insert into demo2(name) values(#{name})")
	public int save(Demo2 demo2);
	
	@Select("select *from demo2")
	public List<Demo2> selectAll();
	
}
