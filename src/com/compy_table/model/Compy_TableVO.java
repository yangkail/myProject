package com.compy_table.model;

import java.io.Serializable;
import java.sql.Date;

public class Compy_TableVO implements Serializable{
	private String cp_contact_email; //公司EMAIL not null
	private String cp_phone; //公司電話 not null
	private String cp_name; //公司名稱 not null
	private String cp_id; //公司統編 not null
	private String cp_address; //公司地址 not null
	private String cp_boss; //公司負責人 not null
	private String cp_contact_man;//公司聯絡人 not null
	private String cp_account; //公司設置帳號 not null
	private String cp_pwd;//公司設置密碼 not null
	private byte[] cp_business;//公司營業登記
	private byte[] cp_logo;//公司LOGO
	private byte[] cp_bigpic;//公司大頭照(放在平台中的)
	private String cp_credit;//公司信用卡號碼 not null
	private Date cp_reg_time;//註冊時間
	private Date cp_update_time;//資料更新時間
	private Integer authority;
	public String getCp_contact_email() {
		return cp_contact_email;
	}
	public void setCp_contact_email(String cp_contact_email) {
		this.cp_contact_email = cp_contact_email;
	}
	public String getCp_phone() {
		return cp_phone;
	}
	public void setCp_phone(String cp_phone) {
		this.cp_phone = cp_phone;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public String getCp_id() {
		return cp_id;
	}
	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}
	public String getCp_address() {
		return cp_address;
	}
	public void setCp_address(String cp_address) {
		this.cp_address = cp_address;
	}
	public String getCp_boss() {
		return cp_boss;
	}
	public void setCp_boss(String cp_boss) {
		this.cp_boss = cp_boss;
	}
	public String getCp_contact_man() {
		return cp_contact_man;
	}
	public void setCp_contact_man(String cp_contact_man) {
		this.cp_contact_man = cp_contact_man;
	}
	public String getCp_account() {
		return cp_account;
	}
	public void setCp_account(String cp_account) {
		this.cp_account = cp_account;
	}
	public String getCp_pwd() {
		return cp_pwd;
	}
	public void setCp_pwd(String cp_pwd) {
		this.cp_pwd = cp_pwd;
	}
	public byte[] getCp_business() {
		return cp_business;
	}
	public void setCp_business(byte[] cp_business) {
		this.cp_business = cp_business;
	}
	public byte[] getCp_logo() {
		return cp_logo;
	}
	public void setCp_logo(byte[] cp_logo) {
		this.cp_logo = cp_logo;
	}
	public byte[] getCp_bigpic() {
		return cp_bigpic;
	}
	public void setCp_bigpic(byte[] cp_bigpic) {
		this.cp_bigpic = cp_bigpic;
	}
	public String getCp_credit() {
		return cp_credit;
	}
	public void setCp_credit(String cp_credit) {
		this.cp_credit = cp_credit;
	}
	public Date getCp_reg_time() {
		return cp_reg_time;
	}
	public void setCp_reg_time(Date cp_reg_time) {
		this.cp_reg_time = cp_reg_time;
	}
	public Date getCp_update_time() {
		return cp_update_time;
	}
	public void setCp_update_time(Date cp_update_time) {
		this.cp_update_time = cp_update_time;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer autority) {
		this.authority = autority;
	}
	
	
}
