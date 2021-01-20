package com.user_table.model;

import java.io.Serializable;

public class User_TableVO implements Serializable{            //管理員表格
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String user_account;              //管理者帳號      
	private String user_password;             //管理者密碼
	private String user_depart;
	private String user_job;
	private Integer authority;                //管理者代號1
	
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_depart() {
		return user_depart;
	}

	public void setUser_depart(String user_depart) {
		this.user_depart = user_depart;
	}

	public String getUser_job() {
		return user_job;
	}

	public void setUser_job(String user_job) {
		this.user_job = user_job;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public User_TableVO() {
		super();
	}
}
