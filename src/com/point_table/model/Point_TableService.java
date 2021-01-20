package com.point_table.model;

import java.sql.Timestamp;
import java.util.List;

public class Point_TableService {

	private Point_TableDAO_interface dao;

	public Point_TableService() {
		dao = new Point_TableJDBCDAO();
	}

	public Point_TableVO addPoint_Table(String point_sieral, String order_id, String gs_email, Integer order_get_point,
			Integer order_use_point, Timestamp point_update_time) {

		Point_TableVO point_tableVO = new Point_TableVO();

		point_tableVO.setPoint_sieral(point_sieral);
		point_tableVO.setOrder_id(order_id);
		point_tableVO.setGs_email(gs_email);
		point_tableVO.setOrder_get_point(order_get_point);
		point_tableVO.setOrder_use_point(order_use_point);
		point_tableVO.setPoint_update_time(point_update_time);
		dao.insert(point_tableVO);

		return point_tableVO;
	}

	// 預留給 Struts 2 用的
	public void addPoint_Table(Point_TableVO point_tableVO) {
		dao.insert(point_tableVO);
	}

	public Point_TableVO updatePoint_Table(String point_sieral, String order_id, String gs_email,
			Integer order_get_point, Integer order_use_point, Timestamp point_update_time) {

		Point_TableVO point_tableVO = new Point_TableVO();

		point_tableVO.setPoint_sieral(point_sieral);
		point_tableVO.setOrder_id(order_id);
		point_tableVO.setGs_email(gs_email);
		point_tableVO.setOrder_get_point(order_get_point);
		point_tableVO.setOrder_use_point(order_use_point);
		point_tableVO.setPoint_update_time(point_update_time);
		dao.update(point_tableVO);
		
		return point_tableVO;

	}

	// 預留給 Struts 2 用的
	public void updatePoint_Table(Point_TableVO point_tableVO) {
		dao.update(point_tableVO);
	}

	public void deletePoint_Table(String point_sieral) {
		dao.delete(point_sieral);
	}

	public Point_TableVO getOnePoint_Table(String point_sieral) {
		return dao.findByPrimaryKey(point_sieral);
	}
	
	
	public List<Point_TableVO> getAll() {
		return dao.getAll();
	}
	
	//查詢前台會員積分用
	public List<Point_Order_TableVO> getAllPoint(String gs_email){
		return dao.getAllPoint(gs_email);
	}
	
}
