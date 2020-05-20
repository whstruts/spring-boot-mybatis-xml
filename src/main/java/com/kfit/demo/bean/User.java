package com.kfit.demo.bean;

public class User {
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	private String Name;

	private String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public float getKyje() {
		return kyje;
	}

	public void setKyje(float kyje) {
		this.kyje = kyje;
	}

	private String user_id;
	private  float kyje;

	public String getIsLogin() {
		return IsLogin;
	}

	public void setIsLogin(String isLogin) {
		IsLogin = isLogin;
	}

	private String IsLogin;

}