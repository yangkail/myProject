package com.guest_table.model;

import java.util.List;

public class Guest_TableService {
	
	private Guest_TableDAO_interface dao;
	
	public Guest_TableService() {
		dao = new Guest_TableJDBCDAO();
	}

	public Integer totalPoint(String gs_email) {
		String sql1="SELECT SUM(ORDER_GET_POINT) FROM POINT_TABLE WHERE GS_EMAIL=?";
		Integer noUse=dao.totalNoUsePoint(sql1,gs_email);
		String sql2="SELECT SUM(ORDER_USE_POINT) FROM POINT_TABLE WHERE GS_EMAIL=?";
		Integer use=dao.totalUsePoint(sql2,gs_email);
		Integer point = noUse-use;
		return point;
	}
	
	public Guest_TableVO addGuest_Table(String gs_email, String gs_pwd,String gs_name, java.sql.Date gs_birth,
			Integer gs_sex, String gs_phone, String gs_address, String gs_credit, 
			java.sql.Date gs_reg_time, byte[] gs_big_pic) {

		Guest_TableVO guest_TableVO = new Guest_TableVO();

		guest_TableVO.setGs_email(gs_email);
		guest_TableVO.setGs_pwd(gs_pwd);
		guest_TableVO.setGs_name(gs_name);
		guest_TableVO.setGs_birth(gs_birth);
		guest_TableVO.setGs_sex(gs_sex);
		guest_TableVO.setGs_phone(gs_phone);
		guest_TableVO.setGs_address(gs_address);
		guest_TableVO.setGs_credit(gs_credit);
		guest_TableVO.setGs_reg_time(gs_reg_time);
		guest_TableVO.setGs_big_pic(gs_big_pic);
		dao.insert(guest_TableVO);

		return guest_TableVO;
	}
	
	public Guest_TableVO updateGuest(String gs_email, String gs_pwd,String gs_name, java.sql.Date gs_birth,
			Integer gs_sex, String gs_phone, String gs_address, String gs_credit, 
			java.sql.Date gs_reg_time)  {

		Guest_TableVO guest_TableVO = new Guest_TableVO();
		
		guest_TableVO.setGs_email(gs_email);
		guest_TableVO.setGs_pwd(gs_pwd);
		guest_TableVO.setGs_name(gs_name);
		guest_TableVO.setGs_birth(gs_birth);
		guest_TableVO.setGs_sex(gs_sex);
		guest_TableVO.setGs_phone(gs_phone);
		guest_TableVO.setGs_address(gs_address);
		guest_TableVO.setGs_credit(gs_credit);
		guest_TableVO.setGs_reg_time(gs_reg_time);
//		guest_TableVO.setGs_big_pic(gs_big_pic);
		dao.update(guest_TableVO);

		return dao.findByPrimaryKey(gs_email);
	}
	
	
	
	
	public List<Guest_TableVO> getAll() {
		return dao.getAll();
	}

	public Guest_TableVO getOneGuest(String gs_email) {
		return dao.findByPrimaryKey(gs_email);
	}

	public Guest_TableVO login(String gs_email) {
		return dao.login(gs_email);
	}
	
	public Integer avg_star() {
		return dao.avg_star();
	}

	public void updateGsBigPic(String gs_email,byte[] gs_big_pic) {
		String sql="UPDATE GUEST_TABLE SET GS_BIG_PIC=? WHERE GS_EMAIL='"+gs_email+"'";
		dao.updateGsBigPic(sql,gs_big_pic);
	}
	
	
	public Guest_TableVO updateCredit(String gs_email, String gs_credit)  {

		Guest_TableVO guest_TableVO = new Guest_TableVO();
		
		guest_TableVO.setGs_email(gs_email);
		guest_TableVO.setGs_credit(gs_credit);
		dao.updateCredit(guest_TableVO);

		return dao.findByPrimaryKey(gs_email);
	}
	
	
	public Guest_TableVO updatePwd(String gs_email, String gs_pwd)  {

		Guest_TableVO guest_TableVO = new Guest_TableVO();
		
		guest_TableVO.setGs_email(gs_email);
		guest_TableVO.setGs_pwd(gs_pwd);
		dao.updatePwd(guest_TableVO);

		return dao.findByPrimaryKey(gs_email);
	}
	
	
	
	
	
}
