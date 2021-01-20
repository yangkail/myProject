package com.ad_table.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Ad_TableJNDIDAO implements Ad_TableDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context cntxt = new InitialContext();
			ds = (DataSource) cntxt.lookup("java:como/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	private static final String INSERT_STMT = "INSERT INTO AD_TABLE(AD_SERIAL,AD_TOP_YN,RS_ID,AD_PRICE,AD_TOP_TIME1,AD_TOP_TIME2,AD_TOP_TIME3,AD_TIME,AD_SHOWTIME,AD_CANCEL_TIME,AD_STATUS,AD_PIC_QUEUE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT = "SELECT ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_TableVo";

	private static final String GET_ONE_STMT = "SELECT  ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_TableVo where  ad_serial = ?";

	private static final String DELETE_AD = "DELETE FROM Ad_Table where ad_serial = ?";

	private static final String UPDATE = "UPDATE Ad_TableVo  set ad_top_yn=?, rs_id=?, ad_price=?,ad_top_time1=?,ad_top_time2=?,ad_top_time3=?,ad_time=?,ad_showtime=?,ad_cancel_time=?,ad_status=?,ad_pic_queue=? where ad_serial = ?";

	@Override
	public void insert(Ad_TableVO ad_TableVO) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Ad_TableVO ad_TableVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String ad_Serial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ad_TableVO findByPrimaryKey(String ad_Serial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ad_TableVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ad_TableVO findByRsId(String rs_id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Ad_TableVO findByRsName(String rs_type) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}