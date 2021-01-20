package com.ad_table.model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Ad_TableJDBCDAO implements Ad_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO AD_TABLE(AD_SERIAL,AD_TOP_YN,RS_ID,AD_PRICE,AD_TOP_TIME1,AD_TOP_TIME2,AD_TOP_TIME3,AD_TIME,AD_SHOWTIME,AD_CANCEL_TIME,AD_STATUS,AD_PIC_QUEUE) VALUES ('AD' || lpad(AD_TABLE_SEQ.Nextval,5,'0'),?,?,?,?,?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT = "SELECT ad_serial,ad_top_yn,rs_id,ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_Table order by ad_serial";

	private static final String GET_ONE_STMT = "SELECT  ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_Table where  ad_serial = ?";

	private static final String DELETE_AD = "DELETE FROM Ad_Table where ad_serial = ?";

	private static final String UPDATE = "UPDATE Ad_Table set ad_top_yn=?, rs_id=?, ad_price=?,ad_top_time1=?,ad_top_time2=?,ad_top_time3=?,ad_time=?,ad_showtime=?,ad_cancel_time=?,ad_status=?, ad_pic_queue=? where ad_serial = ?";
	
	private static final String GET_ONE_RSID = "SELECT  ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_Table where  RS_ID = ?";

	@Override
	public void insert(Ad_TableVO ad_TableVO) throws IOException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ad_TableVO.getAd_top_yn());
			pstmt.setString(2, ad_TableVO.getRs_id());
			pstmt.setInt(3, ad_TableVO.getAd_price());
			pstmt.setInt(4, ad_TableVO.getAd_top_time1());
			pstmt.setInt(5, ad_TableVO.getAd_top_time2());
			pstmt.setInt(6, ad_TableVO.getAd_top_time3());
			pstmt.setDate(7, ad_TableVO.getAd_time());
			pstmt.setDate(8, ad_TableVO.getAd_showtime());
			pstmt.setDate(9, ad_TableVO.getAd_cancel_time());
			pstmt.setInt(10, ad_TableVO.getAd_status());
			pstmt.setBytes(11, ad_TableVO.getAd_pic_queue());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Ad_TableVO ad_TableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ad_TableVO.getAd_top_yn());
			pstmt.setString(2, ad_TableVO.getRs_id());
			pstmt.setInt(3, ad_TableVO.getAd_price());
			pstmt.setInt(4, ad_TableVO.getAd_top_time1());
			pstmt.setInt(5, ad_TableVO.getAd_top_time2());
			pstmt.setInt(6, ad_TableVO.getAd_top_time3());
			pstmt.setDate(7, ad_TableVO.getAd_time());
			pstmt.setDate(8, ad_TableVO.getAd_showtime());
			pstmt.setDate(9, ad_TableVO.getAd_cancel_time());
			pstmt.setInt(10, ad_TableVO.getAd_status());
			pstmt.setBytes(11, ad_TableVO.getAd_pic_queue());
			pstmt.setString(12, ad_TableVO.getAd_serial());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String ad_Serial) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_AD);
			pstmt.setString(1, ad_Serial);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除編號" + ad_Serial + "時,共有" + "被刪除");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Ad_TableVO findByPrimaryKey(String ad_Serial) {
		Ad_TableVO ad_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ad_Serial);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ad_TableVO = new Ad_TableVO();
				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
				ad_TableVO.setRs_id(rs.getString("rs_id"));
				ad_TableVO.setAd_price(rs.getInt("ad_price"));
				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
				ad_TableVO.setAd_time(rs.getDate("ad_time"));
				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
				ad_TableVO.setAd_status(rs.getInt("ad_status"));
				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return ad_TableVO;
	}

	@Override
	public List<Ad_TableVO> getAll() {
		List<Ad_TableVO> list = new ArrayList<Ad_TableVO>();
		Ad_TableVO ad_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ad_TableVO = new Ad_TableVO();
				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
				ad_TableVO.setRs_id(rs.getString("rs_id"));
				ad_TableVO.setAd_price(rs.getInt("ad_price"));
				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
				ad_TableVO.setAd_time(rs.getDate("ad_time"));
				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
				ad_TableVO.setAd_status(rs.getInt("ad_status"));
				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));

				list.add(ad_TableVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public Ad_TableVO findByRsId(String rs_id) {
		Ad_TableVO ad_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_RSID);

			pstmt.setString(1, rs_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ad_TableVO = new Ad_TableVO();
				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
				ad_TableVO.setRs_id(rs.getString("rs_id"));
				ad_TableVO.setAd_price(rs.getInt("ad_price"));
				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
				ad_TableVO.setAd_time(rs.getDate("ad_time"));
				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
				ad_TableVO.setAd_status(rs.getInt("ad_status"));
				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return ad_TableVO;
	}


	public static byte[] getPic(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public static void main(String[] args) throws IOException {
		Ad_TableJDBCDAO dao = new Ad_TableJDBCDAO();
		byte[] pic = getPic("images/20201207.jpg");
		// 新增
//		Ad_TableVO ad_TableVO = new Ad_TableVO();
//		ad_TableVO.setAd_top_yn(1);
//		ad_TableVO.setRs_id("RS10003");
//		ad_TableVO.setAd_price(1);
//		ad_TableVO.setAd_top_time1(1);
//		ad_TableVO.setAd_top_time2(0);
//		ad_TableVO.setAd_top_time3(0);
//		ad_TableVO.setAd_time(java.sql.Date.valueOf("2005-01-01"));
//		ad_TableVO.setAd_showtime(java.sql.Date.valueOf("2005-01-01"));
//		ad_TableVO.setAd_cancel_time(null);
//		ad_TableVO.setAd_status(1);
//		ad_TableVO.setAd_pic_queue(pic);
//		dao.insert(ad_TableVO);
//		System.out.println("新增成功");
//		System.out.println("=========");

//		// 修改
//		Ad_TableVO ad_TableVO2 = new Ad_TableVO();
//		ad_TableVO2.setAd_serial("AD00102");
//		ad_TableVO2.setAd_top_yn(0);
//		ad_TableVO2.setRs_id("RS10001");
//		ad_TableVO2.setAd_price(1);
//		ad_TableVO2.setAd_top_time1(1);
//		ad_TableVO2.setAd_top_time2(0);
//		ad_TableVO2.setAd_top_time3(0);
//		ad_TableVO2.setAd_time(java.sql.Date.valueOf("2002-01-01"));
//		ad_TableVO2.setAd_showtime(java.sql.Date.valueOf("2001-01-01"));
//		ad_TableVO2.setAd_cancel_time(java.sql.Date.valueOf("2022-01-01"));
//		ad_TableVO2.setAd_status(0);
//		ad_TableVO2.setAd_pic_queue(pic);
//		dao.update(ad_TableVO2);
//		System.out.println("=========");
//		System.out.println("修改成功");
//		// 刪除
//		dao.delete("105");
//		System.out.println("=========");
//		//廣告查詢
//		Ad_TableVO ad_TableVO3 = dao.findByPrimaryKey("AD00101");
//		System.out.print(ad_TableVO3.getAd_serial() + ",");
//		System.out.print(ad_TableVO3.getAd_top_yn() + ",");
//		System.out.print(ad_TableVO3.getRs_id() + ",");
//		System.out.print(ad_TableVO3.getAd_price() + ",");
//		System.out.print(ad_TableVO3.getAd_top_time1() + ",");
//		System.out.print(ad_TableVO3.getAd_top_time2() + ",");
//		System.out.print(ad_TableVO3.getAd_top_time3() + ",");
//		System.out.print(ad_TableVO3.getAd_showtime() + ",");
//		System.out.print(ad_TableVO3.getAd_cancel_time() + ",");
//		System.out.print(ad_TableVO3.getAd_status() + ",");
//		System.out.print(ad_TableVO3.getAd_pic_queue() + ",");
//		System.out.println("=========");
		
//
//		List<Ad_TableVO> list = dao.getAll();
//		for (Ad_TableVO ad_TableVO4 : list) {
//			System.out.print(ad_TableVO4.getAd_serial() + ",");
//			System.out.print(ad_TableVO4.getAd_top_yn() + ",");
//			System.out.print(ad_TableVO4.getRs_id() + ",");
//			System.out.print(ad_TableVO4.getAd_price() + ",");
//			System.out.print(ad_TableVO4.getAd_top_time1() + ",");
//			System.out.print(ad_TableVO4.getAd_top_time2() + ",");
//			System.out.print(ad_TableVO4.getAd_top_time3() + ",");
//			System.out.print(ad_TableVO4.getAd_showtime() + ",");
//			System.out.print(ad_TableVO4.getAd_cancel_time() + ",");
//			System.out.print(ad_TableVO4.getAd_status() + ",");
//			System.out.print(ad_TableVO4.getAd_pic_queue() + ",");
//			System.out.println("=========");
//		}
		
		//餐廳查詢
		Ad_TableVO ad_TableVO5 = dao.findByRsId("RS10003");
		System.out.print(ad_TableVO5.getAd_serial() + ",");
		System.out.print(ad_TableVO5.getAd_top_yn() + ",");
		System.out.print(ad_TableVO5.getRs_id() + ",");
		System.out.print(ad_TableVO5.getAd_price() + ",");
		System.out.print(ad_TableVO5.getAd_top_time1() + ",");
		System.out.print(ad_TableVO5.getAd_top_time2() + ",");
		System.out.print(ad_TableVO5.getAd_top_time3() + ",");
		System.out.print(ad_TableVO5.getAd_showtime() + ",");
		System.out.print(ad_TableVO5.getAd_cancel_time() + ",");
		System.out.print(ad_TableVO5.getAd_status() + ",");
		System.out.print(ad_TableVO5.getAd_pic_queue() + ",");
		System.out.println("=========");

	}

	
}