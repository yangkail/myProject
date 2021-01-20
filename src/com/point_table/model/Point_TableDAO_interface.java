package com.point_table.model;

import java.util.List;

import com.order_table.model.Order_TableVO;

public interface Point_TableDAO_interface {
	public void insert(Point_TableVO point_tableVO);
    public void update(Point_TableVO point_tableVO);
    public void delete(String point_sieral);
    public Point_TableVO findByPrimaryKey(String point_sieral);
    public List<Point_TableVO> getAll();
    public List<Point_Order_TableVO> getAllPoint(String gs_email);
}
