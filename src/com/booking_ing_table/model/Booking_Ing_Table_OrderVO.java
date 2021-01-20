package com.booking_ing_table.model;

import java.sql.Date;

public class Booking_Ing_Table_OrderVO extends Booking_Ing_TableVO {
	private String rs_table_number;
	private String rs_seat_sieral;
	private String rs_id;
	private Integer rs_table_seat;
	
	public String getRs_table_number() {
		return rs_table_number;
	}

	public void setRs_table_number(String rs_table_number) {
		this.rs_table_number = rs_table_number;
	}



	public Booking_Ing_Table_OrderVO() {
	
	}

	@Override
	public String toString() {
		return "Booking_Ing_Table_OrderVO [rs_id=" + rs_id 
				+", rs_table_number="+ rs_table_number
				+  ", rs_seat_sieral=" + rs_seat_sieral
				+ ", rs_table_seat=" 
				+ rs_table_seat
				+",order_id=" 
				+ getOrder_id()
				+",rs_status="
				+getRs_status()+
				",gs_select_time="+getGs_select_time()
				+",gs_select_date="+getGs_select_date()+
				"]";
	}

	public String getRs_seat_sieral() {
		return rs_seat_sieral;
	}

	public void setRs_seat_sieral(String rs_seat_sieral) {
		this.rs_seat_sieral = rs_seat_sieral;
	}

	public String getRs_id() {
		return rs_id;
	}

	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}

	public Booking_Ing_Table_OrderVO(String order_id, Integer rs_status, String gs_select_time, Date gs_select_date,
			String rs_id, Integer rs_table_seat,String rs_seat_sieral,String rs_table_number) {
		super(order_id, rs_status, gs_select_time, gs_select_date);
		this.rs_id = rs_id;
		this.rs_table_seat = rs_table_seat;
		this.rs_seat_sieral = rs_seat_sieral;
		this.rs_table_number =rs_table_number;
	}


	public Integer getRs_table_seat() {
		return rs_table_seat;
	}

	public void setRs_table_seat(Integer rs_table_seat) {
		this.rs_table_seat = rs_table_seat;
	}

	public Booking_Ing_Table_OrderVO(Booking_Ing_TableVO booking_Ing_TableVO, String rs_id, Integer rs_table_seat,String rs_seat_sieral,String rs_table_number) {
		this(booking_Ing_TableVO.getOrder_id(), booking_Ing_TableVO.getRs_status(),
				booking_Ing_TableVO.getGs_select_time(), booking_Ing_TableVO.getGs_select_date(),rs_id, rs_table_seat,rs_seat_sieral,rs_table_number);

	}

}
