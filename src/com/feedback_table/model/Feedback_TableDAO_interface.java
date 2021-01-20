package com.feedback_table.model;

import java.util.List;


public interface Feedback_TableDAO_interface {
	public void insert(Feedback_TableVO feedback_TableVO);
    public void update(Feedback_TableVO feedback_TableVO);
    public void delete(String order_id);
    public Feedback_TableVO findByPrimaryKey(String order_id);
    public List<Feedback_TableVO> getAll();
    public Integer avg_star();
    public Integer rs_avg_star(String rs_id);
    public List<Feedback_TableVO> getRs_star(String rs_id);
}
