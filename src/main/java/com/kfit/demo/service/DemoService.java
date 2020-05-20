package com.kfit.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kfit.demo.bean.Demo;
import com.kfit.demo.mapper.DemoMapper;

@Service
public class DemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	//添加事务.
	@Transactional
	public int save(Demo demo){
		return demoMapper.save(demo);
	}
	
	
	@Transactional
	public int update(Demo demo){
		return demoMapper.update(demo);
	}
	
	
	@Transactional
	public int update2(Demo demo){
		return demoMapper.update2(demo);
	}
	
	@Transactional
	public int delete(int id){
		return demoMapper.delete(id);
	}
	
	public List<Demo> selectAll(){
		return demoMapper.selectAll();
	}
	
	public Demo selectById(int id){
		return demoMapper.selectById(id);
	}
}
