package com.kfit.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfit.demo.bean.Demo;

public interface DemoMapper {
	
	public int save(Demo demo);
	
	//注意：方法名和我们的demo.xml文件的id要一样.
	public int update(Demo demo);
	public int update2(Demo demo);
	
	public int delete(int id);
	
	public List<Demo> selectAll();
	
	public Demo selectById(int id);
	
	//arg0 -- param1
	public List<Demo> select1(@Param("name")String name,@Param("email")String email);
	
	public List<Demo> select3(@Param("name")String name,@Param("email")String email);
	
	
	public List<Demo> select5(List<Integer> list);

}
