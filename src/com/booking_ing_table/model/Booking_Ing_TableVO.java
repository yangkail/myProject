package com.booking_ing_table.model;

import java.io.Serializable;
import java.sql.Date;

import com.booking_fixed_table.model.Booking_Fixed_TableVO;

//ORDER_ID	    VARCHAR2(100) NOT NULL,
//RS_STATUS     		NUMBER  NOT NULL,
//RS_SIERAL 	    VARCHAR2(100) NOT NULL,
//PRIMARY KEY (ORDER_ID),
public class Booking_Ing_TableVO extends Booking_Fixed_TableVO {
	private String order_id;
	private Integer rs_status;
	private String rs_sieral;
	private String gs_select_time;
	private Date gs_select_date;
	public Booking_Ing_TableVO() {
		
	}

	public Booking_Ing_TableVO(String order_id, Integer rs_status, String gs_select_time, Date gs_select_date) {
		super();
		this.order_id = order_id;
		this.rs_status = rs_status;
		this.gs_select_time = gs_select_time;
		this.gs_select_date = gs_select_date;
	}





	public Date getGs_select_date() {
		return gs_select_date;
	}

	public void setGs_select_date(Date gs_select_date) {
		this.gs_select_date = gs_select_date;
	}

	public String getGs_select_time() {
		return gs_select_time;
	}

	public void setGs_select_time(String gs_select_time) {
		this.gs_select_time = gs_select_time;
	}

	public String getRs_sieral() {
		return rs_sieral;
	}

	public void setRs_sieral(String rs_sieral) {
		this.rs_sieral = rs_sieral;
	}

	public String getOrder_id() {
		return order_id;
	}

	@Override
	public String toString() {
		return "Booking_Ing_TableVO [order_id=" + order_id + ", rs_status=" + rs_status + ", rs_sieral=" + rs_sieral
				+ ", gs_select_time=" + gs_select_time + ", gs_select_date=" + gs_select_date + "]";
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Integer getRs_status() {
		return rs_status;
	}

	public void setRs_status(Integer rs_status) {
		this.rs_status = rs_status;
	}

}
