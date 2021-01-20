package com.feedback_table.model;

import java.sql.Date;

public class Feedback_TableVO implements java.io.Serializable {
	private String order_id; 		// 訂單編號
	private String rs_id; 			// 餐廳編號
	private String common; 			// 調查表評論
	private Date common_time; 		// 調查表評論時間
	private Integer push_yn; 		// 是否推薦給朋友
	private Integer order_star; 	// 調查表星星數
	private Date common_cancel_time;// 調查表刪除時間
	private Integer common_status;  // 調查表刪除狀態
	
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getRs_id() {
		return rs_id;
	}
	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public Date getCommon_time() {
		return common_time;
	}
	public void setCommon_time(Date common_time) {
		this.common_time = common_time;
	}
	public Integer getPush_yn() {
		return push_yn;
	}
	public void setPush_yn(Integer push_yn) {
		this.push_yn = push_yn;
	}
	public Integer getOrder_star() {
		return order_star;
	}
	public void setOrder_star(Integer order_star) {
		this.order_star = order_star;
	}
	public Date getCommon_cancel_time() {
		return common_cancel_time;
	}
	public void setCommon_cancel_time(Date common_cancel_time) {
		this.common_cancel_time = common_cancel_time;
	}
	public Integer getCommon_status() {
		return common_status;
	}
	public void setCommon_status(Integer common_status) {
		this.common_status = common_status;
	}

}
