package com.kfit.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kfit.demo.bean.Demo2;
import com.kfit.demo.mapper.Demo2Mapper;

@Service
public class Demo2Service {
	
	@Autowired
	private Demo2Mapper demo2Mapper;
	
	@Transactional
	public int save(Demo2 demo2){
		return demo2Mapper.save(demo2);
	}
	
	public List<Demo2> selectAll(){
		return demo2Mapper.selectAll();
	}
}
