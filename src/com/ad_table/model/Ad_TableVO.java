package com.ad_table.model;

import java.sql.Date;

public class Ad_TableVO implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String ad_serial;    // 廣告流水編號 
	private Integer ad_top_yn;    // 廣告是否至頂
	private String rs_id;         // 餐廳編號
	private Integer ad_price;     // 廣告時段費用
	private Integer ad_top_time1; // 購買廣告時段1
	private Integer ad_top_time2; // 購買廣告時段2
	private Integer ad_top_time3; // 購買廣告時段3
	private Date ad_time;         // 廣告成立時間
	private Date ad_showtime;     // 廣告到期時間
	private Date ad_cancel_time;  // 廣告取消時間
	private Integer ad_status;    // 廣告狀態
	private byte[] ad_pic_queue;  // 首頁廣告輪播
	
	public String getAd_serial() {
		return ad_serial;
	}
	public void setAd_serial(String ad_serial) {
		this.ad_serial = ad_serial;
	}
	public Integer getAd_top_yn() {
		return ad_top_yn;
	}
	public void setAd_top_yn(Integer ad_top_yn) {
		this.ad_top_yn = ad_top_yn;
	}
	public String getRs_id() {
		return rs_id;
	}
	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}
	public Integer getAd_price() {
		return ad_price;
	}
	public void setAd_price(Integer ad_price) {
		this.ad_price = ad_price;
	}
	public Integer getAd_top_time1() {
		return ad_top_time1;
	}
	public void setAd_top_time1(Integer ad_top_time1) {
		this.ad_top_time1 = ad_top_time1;
	}
	public Integer getAd_top_time2() {
		return ad_top_time2;
	}
	public void setAd_top_time2(Integer ad_top_time2) {
		this.ad_top_time2 = ad_top_time2;
	}
	public Integer getAd_top_time3() {
		return ad_top_time3;
	}
	public void setAd_top_time3(Integer ad_top_time3) {
		this.ad_top_time3 = ad_top_time3;
	}
	public Date getAd_time() {
		return ad_time;
	}
	public void setAd_time(Date ad_time) {
		this.ad_time = ad_time;
	}
	public Date getAd_showtime() {
		return ad_showtime;
	}
	public void setAd_showtime(Date ad_showtime) {
		this.ad_showtime = ad_showtime;
	}
	public Date getAd_cancel_time() {
		return ad_cancel_time;
	}
	public void setAd_cancel_time(Date ad_cancel_time) {
		this.ad_cancel_time = ad_cancel_time;
	}
	public Integer getAd_status() {
		return ad_status;
	}
	public void setAd_status(Integer ad_status) {
		this.ad_status = ad_status;
	}
	public byte[] getAd_pic_queue() {
		return ad_pic_queue;
	}
	public void setAd_pic_queue(byte[] ad_pic_queue) {
		this.ad_pic_queue = ad_pic_queue;
	}

	
}
