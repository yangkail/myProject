package com.booking_ing_table.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

import com.order_table.model.Order_TableVO;

public interface Booking_Ing_TableDAO_interface {
	public void insert(Booking_Ing_TableVO booking_ing_tablevo);

	public void update(Booking_Ing_TableVO booking_ing_tablevo);

	public void update_status(Booking_Ing_TableVO booking_ing_tablevo);

	public Booking_Ing_TableVO findByPrimaryKey(String order_id);

	public List<Booking_Ing_TableVO> getAll();

	public List<Booking_Ing_TableVO> findByPrimaryKey_Status(Integer rs_status);

	public List<Booking_Ing_Table_OrderVO> get_all_table_seat(String rs_id, Integer rs_table_seat, Integer rs_status,
			String rs_open_time, Date rs_reg_date);

	public Booking_Ing_TableVO findByOrderID(String rs_sieral);

	public void insertWithOrder(Booking_Ing_TableVO booking_Ing_TableVO, List<Order_TableVO> order_TableList,
			Booking_Ing_TableVO updateBooking_Ing_TableVO);

	public void update_Order_Status(Booking_Ing_TableVO updateBooking_Ing_TableVO, Connection con);

	public void delete(String order_id);



}
