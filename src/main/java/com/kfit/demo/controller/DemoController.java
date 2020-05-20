package com.kfit.demo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.*;
import com.kfit.demo.service.SpzlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kfit.demo.service.DemoService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private SpzlService spzlService;

	@RequestMapping("/save")
	public Demo save(Demo demo){
		int rs = demoService.save(demo);
		System.out.println("rs-->"+rs);
		return demo;
	}

	@RequestMapping("/getyywdd")
	public void getyywdd(){

//		String yywdata;
//
//		yywdata = ExportExcelUtils.batchSnycTestceshi();
//	//	SpzlService ss = new SpzlService();
//		JSONObject obj= JSONObject.parseObject(yywdata);
//		obj = obj.getJSONObject("data");
//		JSONArray orderList = obj.getJSONArray("orderList");
//		for (int i = 0;i<orderList.size();i++)
//		{
//			JSONObject objorder = (JSONObject) orderList.get(i);
//			yywddhz hz = JSONObject.toJavaObject(objorder,yywddhz.class);
//			spzlService.InToDDHZ(hz);
//			JSONArray order_detailList = objorder.getJSONArray("order_detailList");
//			for (int j= 0;j<order_detailList.size();j++)
//			{
//				JSONObject objorder_detail = (JSONObject) order_detailList.get(j);
//				yywddmx mx = JSONObject.toJavaObject(objorder_detail,yywddmx.class);
//				spzlService.InToDDMX(mx);
//			}
//		}
//		spzlService.YYW_AddHZ();

	}

    @RequestMapping("/getKC")
	public List<Spzl> getKC(){
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		//	PageHelper.startPage(1, 2);
		return spzlService.getKC();
	}
	@RequestMapping("/getspb")
	public List<Spb> getspb() {
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		//	PageHelper.startPage(1, 2);
		return spzlService.getspb();
	}
	@RequestMapping("/getkhb")
	public List<Khzl> getkhb() {
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		//	PageHelper.startPage(1, 2);
		return spzlService.getkhb();
	}
	@RequestMapping("/getkcb")
	public List<kcb> getkcb(){
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
		//	PageHelper.startPage(1, 2);
		return spzlService.getkcb();
	}
	@RequestMapping(value = "/getUserByName",produces = "application/json; charset=utf-8")
	public @ResponseBody
	User getUserByName(HttpServletRequest request) throws IOException {
		StringBuffer str = new StringBuffer();
		try {
			BufferedInputStream in = new BufferedInputStream(request.getInputStream());
			int i;
			char c;
			while ((i=in.read())!=-1) {
				c=(char)i;
				str.append(c);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		JSONArray json  = JSONArray.parseArray(str.toString());
//		JSONObject obj= JSONObject.parseObject(str.toString());
		for (int i = 0; i < json.size(); i++)
		{
			JSONObject object = (JSONObject) json.get(i);
			User user= new User();
			user.setName(object.get("name").toString());
			spzlService.ItoUsers(user);
			System.out.println(object.get("name"));

		}
	//	System.out.println(obj.get("name"));
		User user= new User();
		user.setName("老王");
		return user;
	}
}

