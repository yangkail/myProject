package com.point_table.model;

import java.sql.Timestamp;

public class Point_TableVO implements java.io.Serializable{
	private String  point_sieral;	 		//積分流水號
	private String  order_id;		 		//訂單流水號
	private String gs_email; 		 		//消費者信箱
	private Integer order_get_point;	 	//訂單新增積分
	private Integer order_use_point;  		//訂單使用積分
	private Timestamp point_update_time;	//積分更新時間
	
	
	public String getPoint_sieral() {
		return point_sieral;
	}
	public void setPoint_sieral(String point_sieral) {
		this.point_sieral = point_sieral;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGs_email() {
		return gs_email;
	}
	public void setGs_email(String gs_email) {
		this.gs_email = gs_email;
	}
	public Integer getOrder_get_point() {
		return order_get_point;
	}
	public void setOrder_get_point(Integer order_get_point) {
		this.order_get_point = order_get_point;
	}
	public Integer getOrder_use_point() {
		return order_use_point;
	}
	public void setOrder_use_point(Integer order_use_point) {
		this.order_use_point = order_use_point;
	}
	public Timestamp getPoint_update_time() {
		return point_update_time;
	}
	public void setPoint_update_time(Timestamp point_update_time) {
		this.point_update_time = point_update_time;
	}
	@Override
	public String toString() {
		return "Point_TableVO [point_sieral=" + point_sieral + ", order_id=" + order_id + ", gs_email=" + gs_email
				+ ", order_get_point=" + order_get_point + ", order_use_point=" + order_use_point
				+ ", point_update_time=" + point_update_time + "]";
	}
	
}
