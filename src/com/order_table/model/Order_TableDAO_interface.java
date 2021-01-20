package com.order_table.model;

import java.sql.Connection;
import java.util.*;


public interface Order_TableDAO_interface {
	 public void insert(Order_TableVO order_tableVO);
     public void update(Order_TableVO order_tableVO);
     public void delete(String order_id);
     public Order_TableVO findByPrimaryKey(String order_id);
//     public Order_TableVO findByRs_id(String rs_id);
     public List<Order_TableVO> getAll();
     public List<Order_TableVO> getAllRs_id(String rs_id);
     public List<Order_TableVO> getAllGs_email(String gs_email);
     public List<Order_TableVO> getAllOrder_id(String order_id);
//     public List<Order_TableVO> getAllComplete_Order(String rs_id);
     public void update2(Order_TableVO order_tableVO);
     public void insertWithBooking(Order_TableVO order_TableVO, Connection con);
     //Ajax餐廳業者專用
     public boolean update2(String order_id);

	 //Ajax消費者專用
	 public boolean update0(String order_id);
	
	 //前台RS_complete_order_page.jsp 訂單狀態:成立訂單 使用
	 public List<Order_TableVO> getAllComplete_Order(String rs_id);
	 
	//前台RS_cancel_order_page.jsp 訂單狀態:取消訂單 使用
	 public List<Order_TableVO> getAllCancel_Order(String rs_id);
	 
	//前台Consumer_complete_page.jsp 訂單狀態:成立訂單 使用
	 public List<Order_Rest_TableVO> getAllConsumer_Complete(String gs_email);
	 
	//前台Consumer_cancel_page.jsp 訂單狀態:成立訂單 使用
	 public List<Order_Rest_TableVO> getAllConsumer_Cancel(String gs_email);
	 
	
	 public List<Order_Rest_TableVO> get_ORDER_REST(String gs_email);
	
}