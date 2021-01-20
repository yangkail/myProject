package com.order_table.model;

import java.sql.Timestamp;

import com.rest_table.model.Rest_TableVO;

public class Order_Rest_TableVO extends Rest_TableVO {

	private String order_id;  				//訂單流水號
	private String rs_id; 					//餐廳編號
	private String gs_email; 				//消費者信箱
	private Integer order_status; 			//訂單狀態
	private Timestamp order_time;			//訂單成立時間
	private Timestamp order_cancel_time; 	//訂單取消時間
	private String gs_order_remark; 		//訂單備註
	private Integer gs_people; 				//用餐人數
	private String gs_select_time; 			//消費者用餐時段
	private Integer order_deposit; 			//使用訂金
	private byte[] order_qrcode; 			//訂單QR條碼
	private Integer rs_table_status;		//餐廳座位狀態
	private String rs_name;					//餐聽名稱	
	
	
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
	
	public String getGs_email() {
		return gs_email;
	}
	
	public void setGs_email(String gs_email) {
		this.gs_email = gs_email;
	}
	
	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	
	public Timestamp getOrder_time() {
		return order_time;
	}
	
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	
	public Timestamp getOrder_cancel_time() {
		return order_cancel_time;
	}
	
	public void setOrder_cancel_time(Timestamp order_cancel_time) {
		this.order_cancel_time = order_cancel_time;
	}
	
	public String getGs_order_remark() {
		return gs_order_remark;
	}
	
	public void setGs_order_remark(String gs_order_remark) {
		this.gs_order_remark = gs_order_remark;
	}

	public Integer getGs_people() {
		return gs_people;
	}
	
	public void setGs_people(Integer gs_people) {
		this.gs_people = gs_people;
	}
	
	public String getGs_select_time() {
		return gs_select_time;
	}

	public void setGs_select_time(String gs_select_time) {
		this.gs_select_time = gs_select_time;
	}
	
	public Integer getOrder_deposit() {
		return order_deposit;
	}
	
	public void setOrder_deposit(Integer order_deposit) {
		this.order_deposit = order_deposit;
	}
	
	public byte[] getOrder_qrcode() {
		return order_qrcode;
	}
	
	public void setOrder_qrcode(byte[] order_qrcode) {
		this.order_qrcode = order_qrcode;
	}
	
	public Integer getRs_table_status() {
		return rs_table_status;
	}

	public void setRs_table_status(Integer rs_table_status) {
		this.rs_table_status = rs_table_status;
	}
	
	public String getRs_name() {
		return rs_name;
	}
	
	public void setRs_name(String rs_name) {
		this.rs_name = rs_name;
	}
	
	
}
