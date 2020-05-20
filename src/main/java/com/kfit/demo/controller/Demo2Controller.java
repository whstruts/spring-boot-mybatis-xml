package com.kfit.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.kfit.demo.bean.Demo2;
import com.kfit.demo.service.Demo2Service;

@Controller
@RequestMapping("demo2")
public class Demo2Controller {
	
	@Autowired
	private Demo2Service demo2Service;
	
	@RequestMapping("/save")
	public Demo2 save(Demo2 demo2){
		demo2Service.save(demo2);
		return demo2;
	}
	
	
	@RequestMapping("/selectAll")
	public List<Demo2> selectAll(int pageNum){
		PageHelper.startPage(pageNum, 2);
		return demo2Service.selectAll();
	}


}