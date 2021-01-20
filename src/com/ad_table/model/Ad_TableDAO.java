//package com.ad_table.model;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class Ad_TableDAO implements Ad_TableDAO_interface {
//
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = "INSERT INTO AD_TABLE(AD_SERIAL,AD_TOP_YN,RS_ID,AD_PRICE,AD_TOP_TIME1,AD_TOP_TIME2,AD_TOP_TIME3,AD_TIME,AD_SHOWTIME,AD_CANCEL_TIME,AD_STATUS,AD_PIC_QUEUE) VALUES ('AD' || lpad(AD_TABLE_SEQ.Nextval,5,'0'),?,?,?,?,?,?,?,?,?,?,?)";
//
//	private static final String GET_ALL_STMT = "SELECT ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_Table";
//
//	private static final String GET_ONE_STMT = "SELECT  ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_Table where  ad_serial = ?";
//
//	private static final String DELETE_AD = "DELETE FROM Ad_Table where ad_serial = ?";
//
//	private static final String UPDATE = "UPDATE Ad_Table set ad_top_yn=?, rs_id=?, ad_price=?,ad_top_time1=?,ad_top_time2=?,ad_top_time3=?,ad_time=?,ad_showtime=?,ad_cancel_time=?,ad_status=?, ad_pic_queue=? where ad_serial = ?";
//    
//	private static final String GET_ONE_RSID = "SELECT  ad_serial , ad_top_yn, rs_id, ad_price,ad_top_time1,ad_top_time2,ad_top_time3,ad_time,ad_showtime,ad_cancel_time,ad_status,ad_pic_queue FROM Ad_Table  where  RS_ID = ?";
//	
////	private static final String Get_RS_TYPE = "SELECT * FROM REST_TABLE WHERE RS_TYPE IN ('日','泰','美','韓','越','新馬')";
//	
//	@Override
//	public void insert(Ad_TableVO ad_TableVO)  {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		byte[] ad_pic_queue = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, ad_TableVO.getAd_top_yn());
//			pstmt.setString(2, ad_TableVO.getRs_id());
//			pstmt.setInt(3, ad_TableVO.getAd_price());
//			pstmt.setInt(4, ad_TableVO.getAd_top_time1());
//			pstmt.setInt(5, ad_TableVO.getAd_top_time2());
//			pstmt.setInt(6, ad_TableVO.getAd_top_time3());
//			pstmt.setDate(7, ad_TableVO.getAd_time());
//			pstmt.setDate(8, ad_TableVO.getAd_showtime());
//			pstmt.setDate(9, ad_TableVO.getAd_cancel_time());
//			pstmt.setInt(10, ad_TableVO.getAd_status());
//			pstmt.setBytes(11, ad_TableVO.getAd_pic_queue());
//
//			try {
//				ad_pic_queue = getAd_pic_queue("items/pic/20201207.jpg");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			pstmt.setBytes(11, ad_pic_queue);
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	public static byte[] getAd_pic_queue(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}
//
//	@Override
//	public void update(Ad_TableVO ad_TableVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, ad_TableVO.getAd_top_yn());
////			pstmt.setString(2, ad_TableVO.getRs_id());
//			pstmt.setInt(2, ad_TableVO.getAd_price());
//			pstmt.setInt(3, ad_TableVO.getAd_top_time1());
//			pstmt.setInt(4, ad_TableVO.getAd_top_time2());
//			pstmt.setInt(5, ad_TableVO.getAd_top_time3());
//			pstmt.setDate(6, ad_TableVO.getAd_time());
//			pstmt.setDate(7, ad_TableVO.getAd_showtime());
//			pstmt.setDate(8, ad_TableVO.getAd_cancel_time());
//			pstmt.setInt(9, ad_TableVO.getAd_status());
//			pstmt.setBytes(10, ad_TableVO.getAd_pic_queue());
//			pstmt.setString(11, ad_TableVO.getAd_serial());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	public void delete(String ad_Serial) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE_AD);
//
//			// 1●設定於 pstm.executeUpdate()之前
//			con.setAutoCommit(false);
//
//			pstmt = con.prepareStatement(DELETE_AD);
//			pstmt.setString(1, ad_Serial);
//			pstmt.executeUpdate();
//
//			// 2●設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除編號" + ad_Serial);
//			// Handle any driver errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
////	@Override
//	public Ad_TableVO findByPrimaryKey(String ad_Serial)  {
//		Ad_TableVO ad_TableVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, ad_Serial);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				ad_TableVO = new Ad_TableVO();
//				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
//				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
//				ad_TableVO.setRs_id(rs.getString("rs_id"));
//				ad_TableVO.setAd_price(rs.getInt("ad_price"));
//				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
//				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
//				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
//				ad_TableVO.setAd_time(rs.getDate("ad_time"));
//				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
//				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
//				ad_TableVO.setAd_status(rs.getInt("ad_status"));
//				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));
//
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return ad_TableVO;
//	}
//
//	@Override
//	public List<Ad_TableVO> getAll()  {
//		List<Ad_TableVO> list = new ArrayList<Ad_TableVO>();
//		Ad_TableVO ad_TableVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				ad_TableVO = new Ad_TableVO();
//				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
//				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
//				ad_TableVO.setRs_id(rs.getString("rs_id"));
//				ad_TableVO.setAd_price(rs.getInt("ad_price"));
//				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
//				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
//				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
//				ad_TableVO.setAd_time(rs.getDate("ad_time"));
//				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
//				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
//				ad_TableVO.setAd_status(rs.getInt("ad_status"));
//				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));
//
//				list.add(ad_TableVO);
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public Ad_TableVO findByRsId(String rs_id) {
//		Ad_TableVO ad_TableVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_RSID);
//			rs = pstmt.executeQuery();
//
//			pstmt.setString(1, rs_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				ad_TableVO = new Ad_TableVO();
//				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
//				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
//				ad_TableVO.setRs_id(rs.getString("rs_id"));
//				ad_TableVO.setAd_price(rs.getInt("ad_price"));
//				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
//				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
//				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
//				ad_TableVO.setAd_time(rs.getDate("ad_time"));
//				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
//				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
//				ad_TableVO.setAd_status(rs.getInt("ad_status"));
//				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));
//
//			}
//
//			// Handle any driver errors
//			while (rs.next()) {
//				ad_TableVO = new Ad_TableVO();
//				ad_TableVO.setAd_serial(rs.getString("ad_serial"));
//				ad_TableVO.setAd_top_yn(rs.getInt("ad_top_yn"));
//				ad_TableVO.setRs_id(rs.getString("rs_id"));
//				ad_TableVO.setAd_price(rs.getInt("ad_price"));
//				ad_TableVO.setAd_top_time1(rs.getInt("ad_top_time1"));
//				ad_TableVO.setAd_top_time2(rs.getInt("ad_top_time2"));
//				ad_TableVO.setAd_top_time3(rs.getInt("ad_top_time3"));
//				ad_TableVO.setAd_time(rs.getDate("ad_time"));
//				ad_TableVO.setAd_showtime(rs.getDate("ad_showtime"));
//				ad_TableVO.setAd_cancel_time(rs.getDate("ad_cancel_time"));
//				ad_TableVO.setAd_status(rs.getInt("ad_status"));
//				ad_TableVO.setAd_pic_queue(rs.getBytes("ad_pic_queue"));
//
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return ad_TableVO;
//	}
//
//
//}
