package com.rest_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Rest_TableDAOjndi implements Rest_TableDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA102g1");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static final String INSERT_STMT = "INSERT INTO REST_TABLE (RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS,RS_PHONE , RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, RS_REG_DATE, AUTHORITY) VALUES(REST_TABLE_SEQ.Nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)";
	private static final String GET_ALL_STMT = "SELECT RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS, RS_PHONE, RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, TO_CHAR(RS_REG_DATE,'YYYY-MM-DD')RS_REG_DATE, AUTHORITY FROM REST_TABLE";
	private static final String GET_ONE_STMT = "SELECT RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS, RS_PHONE, RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, TO_CHAR(RS_REG_DATE,'YYYY-MM-DD')RS_REG_DATE, AUTHORITY FROM REST_TABLE WHERE RS_ID=?";
	private static final String DELETE = "DELETE FROM REST_TABLE WHERE RS_ID=?";
	private static final String UPDATE = "UPDATE REST_TABLE SET RS_NAME=?, RS_ADDRESS=?, RS_PHONE=?, RS_INTRO=?, RS_CHECK_YN=?, RS_TYPE=?, RS_BIGPIC=?, RS_PIC=?, RS_VIEW1=?, RS_VIEW2=?, RS_VIEW3=?, RS_VIEW4=?, RS_VIEW5=?, RS_OPEN_TIME=?, RS_REG_DATE=? WHERE RS_ID=?";
	@Override
	public String insert(Rest_TableVO rest_TableVO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Rest_TableVO> getOne() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRs_id(String cp_contact_email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Rest_TableVO getOneAll(String rs_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Rest_TableVO getOneAllOnlyPic(String rs_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateAuthorityToApplication(String rs_id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePicAndView(Rest_TableVO rest_TableVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Rest_TableVO> getAll(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public byte[] getOnePic(String sql, String pic) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateAuthorityIsOk(String rs_id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getRs_check_yn(String cp_contact_email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteRest(String rs_id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Rest_TableVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Rest_TableVO> getAllNew() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Rest_TableVO> findByRsName(String rs_type) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Rest_TableVO> findByRsAddress(String rs_address) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Rest_TableVO> getAllRestForSomeCondition(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

}