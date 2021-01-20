package com.order_table.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;



public class Order_TableService {
	
	private Order_TableDAO_interface dao;
	
	public Order_TableService() {
		dao = new Order_TableJDBCDAO();
	}
	
	public Order_TableVO addOrder_Table(String order_id,String rs_id,String gs_email,Integer order_status,
			Timestamp order_time, Timestamp order_cancel_time, String gs_order_remark, 
			Integer gs_people, String gs_select_time, Integer order_deposit, byte[] order_qrcode, 
			Integer rs_table_status ) 	{
			
		
		Order_TableVO order_tableVO = new Order_TableVO();
		
		order_tableVO.setOrder_id(order_id);
		order_tableVO.setRs_id(rs_id);
		order_tableVO.setGs_email(gs_email);
		order_tableVO.setOrder_status(order_status);
		order_tableVO.setOrder_time(order_time);
		order_tableVO.setOrder_cancel_time(order_cancel_time);
		order_tableVO.setGs_order_remark(gs_order_remark);
		order_tableVO.setGs_people(gs_people);
		order_tableVO.setGs_select_time(gs_select_time);
		order_tableVO.setOrder_deposit(order_deposit);
		order_tableVO.setOrder_qrcode(order_qrcode);
		order_tableVO.setRs_table_status(rs_table_status);
		dao.insert(order_tableVO);
		
		return order_tableVO;
	}
	
	//預留給 Struts 2 用的
	public void addOrder_Table(Order_TableVO order_tableVO) {
		dao.insert(order_tableVO);
	}
	
	public Order_TableVO updateOrder_Table(String order_id,String rs_id,String gs_email,Integer order_status,
			Timestamp order_time, Timestamp order_cancel_time, String gs_order_remark, 
			Integer gs_people, String gs_select_time, Integer order_deposit, byte[] order_qrcode, 
			Integer rs_table_status ) {
		
		Order_TableVO order_tableVO = new Order_TableVO();
		
		order_tableVO.setOrder_id(order_id);
		order_tableVO.setRs_id(rs_id);
		order_tableVO.setGs_email(gs_email);
		order_tableVO.setOrder_status(order_status);
		order_tableVO.setOrder_time(order_time);
		order_tableVO.setOrder_cancel_time(order_cancel_time);
		order_tableVO.setGs_order_remark(gs_order_remark);
		order_tableVO.setGs_people(gs_people);
		order_tableVO.setGs_select_time(gs_select_time);
		order_tableVO.setOrder_deposit(order_deposit);
		order_tableVO.setOrder_qrcode(order_qrcode);
		order_tableVO.setRs_table_status(rs_table_status);
		dao.update(order_tableVO);
		
		return order_tableVO;

	}
	//預留給 Struts 2 用的
	public void updateOrder_Table(Order_TableVO order_tableVO) {
		dao.update(order_tableVO);
	}
	
	public void deleteOrder_Table(String order_id) {
		dao.delete(order_id);
	}
	
	public Order_TableVO getOneOrder_Table(String order_id) {
		return dao.findByPrimaryKey(order_id);
	}
	
	public List<Order_TableVO> getAllRs_id(String rs_id) {
		return dao.getAllRs_id(rs_id);
	}
	
	public List<Order_TableVO> getAllGs_email(String gs_email) {
		return dao.getAllGs_email(gs_email);
	}
	
	public List<Order_TableVO> getAllOrder_id(String order_id) {
		return dao.getAllOrder_id(order_id);
	}
	
	public List<Order_TableVO> getAll() {
		return dao.getAll();
	}
	
	public void update2(Order_TableVO order_tableVO) {
		dao.update2(order_tableVO);
	}
	
	public boolean update2(String order_id) {
		return dao.update2(order_id);
	}
	
	public boolean update0(String order_id) {
		return dao.update0(order_id);
	}
	
	public List<Order_TableVO> getAllComplete_Order(String rs_id) {
		return dao.getAllComplete_Order(rs_id);
	}
	
	public List<Order_TableVO> getAllCancel_Order(String rs_id) {
		return dao.getAllCancel_Order(rs_id);
	}
	public List<Order_Rest_TableVO> getAllConsumer_Complete(String gs_email) {
		return dao.getAllConsumer_Complete(gs_email);
	}
	
	public List<Order_Rest_TableVO> getAllConsumer_Cancel(String gs_email) {
		return dao.getAllConsumer_Cancel(gs_email);
	}
	
	public List<Order_Rest_TableVO> get_ORDER_REST(String gs_email) {
		return dao.get_ORDER_REST(gs_email);
	}
	
}