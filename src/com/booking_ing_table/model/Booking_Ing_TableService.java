package com.booking_ing_table.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.order_table.model.Order_TableVO;

public class Booking_Ing_TableService {
	private Booking_Ing_TableDAO_interface dao;

	public Booking_Ing_TableService() {
		dao = new Booking_Ing_Table_JdbcDAO();
	}

	public Booking_Ing_TableVO addBooking_Ing_Table(Integer rs_status,String rs_sieral,String gs_select_time) {
		  Booking_Ing_TableVO  booking_Ing_TableVO1 = new Booking_Ing_TableVO();
		  booking_Ing_TableVO1.setRs_status(rs_status);
		  booking_Ing_TableVO1.setRs_sieral(rs_sieral);
		  booking_Ing_TableVO1.setGs_select_time(gs_select_time);
		  dao.insert(booking_Ing_TableVO1);
		  
		  return booking_Ing_TableVO1;
	}

	public Booking_Ing_TableVO updateBooking_Ing_Table(String rs_sieral, Integer rs_status, String order_id,String gs_select_time) {
		 Booking_Ing_TableVO booking_Ing_TableVO2= new Booking_Ing_TableVO();
		 booking_Ing_TableVO2.setRs_sieral(rs_sieral);
		 booking_Ing_TableVO2.setRs_status(rs_status);
		 booking_Ing_TableVO2.setOrder_id(order_id);
		 booking_Ing_TableVO2.setGs_select_time(gs_select_time);
		 dao.update(booking_Ing_TableVO2);
		 
		 return booking_Ing_TableVO2;
	}

	public Booking_Ing_TableVO getOneBooking_Ing_Table(String order_id) {
		return dao.findByPrimaryKey(order_id);
		
	}

	public List<Booking_Ing_TableVO> getAll() {
		return dao.getAll();
	}
	 public List<Booking_Ing_TableVO> getAll_Status(Integer rs_status){
		 return dao.findByPrimaryKey_Status(rs_status);
	 }
	 public Booking_Ing_TableVO updateBooking_Ing_Status(Integer rs_status,String order_id,String gs_select_time) {
		 Booking_Ing_TableVO booking_Ing_TableVO3 = new Booking_Ing_TableVO();
		 booking_Ing_TableVO3.setRs_status(rs_status);
		 booking_Ing_TableVO3.setOrder_id(order_id);
		 booking_Ing_TableVO3.setGs_select_time(gs_select_time);
		 dao.update_status(booking_Ing_TableVO3);
		 
		 return booking_Ing_TableVO3;
	}
	 public List<Booking_Ing_Table_OrderVO> get_all_table_seat(String rs_id, Integer rs_table_seat, Integer rs_status,
				String gs_select_time, Date gs_select_date){
					
		 
	return dao.get_all_table_seat(rs_id, rs_table_seat, rs_status, gs_select_time, gs_select_date);
		 
		 
	 }
	 
	 public Booking_Ing_TableVO findByOrderID(String rs_sieral) {
		 return dao.findByOrderID(rs_sieral);
	 }
	 
	 public List<Order_TableVO> insertWithOrder(Integer rs_status,String rs_sieral,String gs_select_time,Date gs_select_date,List<Order_TableVO> order_TableList,
			 Integer update_rs_status ,String update_order_id) {
		 Booking_Ing_TableVO booking_Ing_TableVO = new Booking_Ing_TableVO();
		 Booking_Ing_TableVO updateBooking_Ing_TableVO2 = new Booking_Ing_TableVO();
		 booking_Ing_TableVO.setRs_sieral(rs_sieral);
		 booking_Ing_TableVO.setRs_status(rs_status);
		 booking_Ing_TableVO.setGs_select_time(gs_select_time);
		 booking_Ing_TableVO.setGs_select_date(gs_select_date);
		 //修改狀態的訂單
		 updateBooking_Ing_TableVO2.setRs_status(update_rs_status);
		 updateBooking_Ing_TableVO2.setOrder_id(update_order_id);
		 dao.insertWithOrder(booking_Ing_TableVO, order_TableList, updateBooking_Ing_TableVO2);;
		return order_TableList;
		 
	 }
	 public void delete(String order_id) {
		 dao.delete(order_id);
	 }
	 
}
