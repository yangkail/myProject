package com.booking_fixed_table.model;

import java.io.Serializable;

import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO;



//RS_SIERAL 	     VARCHAR2(100) NOT NULL,
//RS_TABLE_NUMBER      VARCHAR2(20) NOT NULL,
//RS_TABLE_SEAT         NUMBER NOT NULL,
//RS_SEAT_SIERAL    VARCHAR2(100)NOT NULL,
public class Booking_Fixed_TableVO extends Rest_Seat_Coordinate_TableVO{
	private String rs_sieral;
	private String rs_table_number;
	private Integer rs_table_seat;
	private String rs_seat_sieral;
	private Integer rs_sort;
	public Integer getRs_sort(int j) {
		return rs_sort;
	}
	public void setRs_sort(Integer rs_sort) {
		this.rs_sort = rs_sort;
	}
	public String getRs_sieral() {
		return rs_sieral;
	}
	public void setRs_sieral(String rs_sieral) {
		this.rs_sieral = rs_sieral;
	}
	public String getRs_table_number() {
		return rs_table_number;
	}
	public void setRs_table_number(String rs_table_number) {
		this.rs_table_number = rs_table_number;
	}
	public Integer getRs_table_seat() {
		return rs_table_seat;
	}
	public void setRs_table_seat(Integer rs_table_seat) {
		this.rs_table_seat = rs_table_seat;
	}
	public String getRs_seat_sieral() {
		return rs_seat_sieral;
	}
	public void setRs_seat_sieral(String rs_seat_sieral) {
		this.rs_seat_sieral = rs_seat_sieral;
	}
	public Booking_Fixed_TableVO(String rs_sieral, Integer rs_table_seat
			) {
		this.rs_sieral = rs_sieral;
		this.rs_table_seat = rs_table_seat;
		
	}
	public Booking_Fixed_TableVO(String rs_sieral, Integer rs_table_seat,String rs_table_number,
			String rs_seat_sieral,Integer rs_sort
			) {
		super();
		this.rs_sieral = rs_sieral;
		this.rs_table_seat = rs_table_seat;
		this.rs_seat_sieral =rs_seat_sieral;
		this.rs_sort = rs_sort;
		this.rs_table_number =rs_table_number;
		
	}
	public Booking_Fixed_TableVO() {
		super();
	    
		
	}
	




}


