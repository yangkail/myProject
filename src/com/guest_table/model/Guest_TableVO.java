package com.guest_table.model;

import java.sql.Date;

import org.apache.naming.java.javaURLContextFactory;

public class Guest_TableVO implements java.io.Serializable {
	private String gs_email; 	// 會員電子信箱
	private String gs_pwd; 		// 會員密碼
	private String gs_name; 	// 會員姓名
	private Date gs_birth; 	// 會員生日
	private Integer gs_sex;		// 會員性別
	private String gs_phone; 	// 會員手機號碼
	private String gs_address; 	// 會員住址
	private String gs_credit; 	// 會員信用卡資訊
	private Date gs_reg_time; 	// 會員註冊時間
	private byte[] gs_big_pic; 	// 會員大頭貼
	private Integer authority; 	// 權限

	public String getGs_email() {
		return gs_email;
	}

	public void setGs_email(String gs_email) {
		this.gs_email = gs_email;
	}

	public String getGs_pwd() {
		return gs_pwd;
	}

	public void setGs_pwd(String gs_pwd) {
		this.gs_pwd = gs_pwd;
	}

	public String getGs_name() {
		return gs_name;
	}

	public void setGs_name(String gs_name) {
		this.gs_name = gs_name;
	}

	public Date getGs_birth() {
		return gs_birth;
	}

	public void setGs_birth(Date gs_birth) {
		this.gs_birth = gs_birth;
	}

	public Integer getGs_sex() {
		return gs_sex;
	}

	public void setGs_sex(Integer gs_sex) {
		this.gs_sex = gs_sex;
	}

	public String getGs_phone() {
		return gs_phone;
	}

	public void setGs_phone(String gs_phone) {
		this.gs_phone = gs_phone;
	}

	public String getGs_address() {
		return gs_address;
	}

	public void setGs_address(String gs_address) {
		this.gs_address = gs_address;
	}

	public String getGs_credit() {
		return gs_credit;
	}

	public void setGs_credit(String gs_credit) {
		this.gs_credit = gs_credit;
	}

	public Date getGs_reg_time() {
		return gs_reg_time;
	}

	public void setGs_reg_time(Date gs_reg_time) {
		this.gs_reg_time = gs_reg_time;
	}

	public byte[] getGs_big_pic() {
		return gs_big_pic;
	}

	public void setGs_big_pic(byte[] gs_big_pic) {
		this.gs_big_pic = gs_big_pic;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

}
