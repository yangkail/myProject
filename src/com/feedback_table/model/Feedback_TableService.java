package com.feedback_table.model;

import java.util.List;

public class Feedback_TableService {
	private Feedback_TableDAO_interface dao;

	public Feedback_TableService() {

		dao = new Feedback_TableJDBCDAO();
	}

	public Feedback_TableVO addFeedback(String order_id, String rs_id, String common, java.sql.Date common_time,
			Integer push_yn, Integer order_star, java.sql.Date common_cancel_time, Integer common_status) {

		Feedback_TableVO feedback_TableVO = new Feedback_TableVO();

		feedback_TableVO.setOrder_id(order_id);
		feedback_TableVO.setRs_id(rs_id);
		feedback_TableVO.setCommon(common);
		feedback_TableVO.setCommon_time(common_time);
		feedback_TableVO.setPush_yn(push_yn);
		feedback_TableVO.setOrder_star(order_star);
		feedback_TableVO.setCommon_cancel_time(common_cancel_time);
		feedback_TableVO.setCommon_status(common_status);

		dao.insert(feedback_TableVO);

		return feedback_TableVO;
	}

	public Feedback_TableVO updateFeedback(String order_id, String rs_id, String common, java.sql.Date common_time,
			Integer push_yn, Integer order_star, java.sql.Date common_cancel_time, Integer common_status) {

		Feedback_TableVO feedback_TableVO = new Feedback_TableVO();

		feedback_TableVO.setOrder_id(order_id);
		feedback_TableVO.setRs_id(rs_id);
		feedback_TableVO.setCommon(common);
		feedback_TableVO.setCommon_time(common_time);
		feedback_TableVO.setPush_yn(push_yn);
		feedback_TableVO.setOrder_star(order_star);
		feedback_TableVO.setCommon_cancel_time(common_cancel_time);
		feedback_TableVO.setCommon_status(common_status);
		dao.update(feedback_TableVO);

		return dao.findByPrimaryKey(order_id);
	}

	public List<Feedback_TableVO> getAll() {
		return dao.getAll();
	}

	public Feedback_TableVO getOneFeedback(String order_id) {
		return dao.findByPrimaryKey(order_id);
	}

	public Integer avg_star() {
		return dao.avg_star();
	}

	public Integer rs_avg_star(String rs_id) {
		return dao.rs_avg_star(rs_id);
	}
	 public List<Feedback_TableVO> getRs_star(String rs_id){
		 return dao.getRs_star(rs_id);
	 }
	
	
}
