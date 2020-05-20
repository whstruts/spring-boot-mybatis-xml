package com.kfit.demo.bean;

/**
 * 测试实体类
 * @author Angel -- 守护天使
 * @version v.0.1
 * @date 2017年8月27日
 */
public class Demo {
	
	private int id;//主键-->自增长.
	private String name;//名称.
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
