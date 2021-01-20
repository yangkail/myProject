package com.rest_table.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Rest_TableDAOjdbc implements Rest_TableDAO_Interface {
	private static Object newStr;
	String driver = "oracle.jdbc.driver.OracleDriver"; // 驅動程式
	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL
	String userid = "TEA102G1";// 帳號
	String passwd = "123456";// 密碼

	// 業者新增餐廳用
	private static final String INSERT_STMT = "INSERT INTO REST_TABLE VALUES('RS' || lpad(REST_TABLE_SEQ.Nextval,5,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	// 只找餐廳表格中指定的rs_id(用外鍵email)
	private static final String FIND_RS_ID = "SELECT RS_ID FROM REST_TABLE WHERE CP_CONTACT_EMAIL=?";

	// 只找餐廳表格中指定的rs_check_yn(用外鍵email)
	private static final String FIND_RS_CHECK_YN = "SELECT RS_CHECK_YN FROM REST_TABLE WHERE CP_CONTACT_EMAIL=?";

	// 前台判斷資料用,只找表格中全部的資料中的三筆資料
	private static final String GET_ALL_STMT_ONLY_THREE_DATA = "SELECT RS_NAME, RS_ADDRESS, RS_PHONE FROM REST_TABLE";

	// 前後台查詢指定表格中其中一筆資料
	private static final String GET_ONE_STMT = "SELECT RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS, RS_PHONE, RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, TO_CHAR(RS_REG_DATE,'YYYY-MM-DD')RS_REG_DATE, AUTHORITY FROM REST_TABLE WHERE RS_ID=?";

	// 更改權限值(4是沒問題可上架,10是下架,11是填完資料尚未上傳圖片,12是填完資料並上傳圖片,13是填完上傳完所有資料按下申請審核)
	private static final String UPDATE_AUTHORITY = "UPDATE REST_TABLE SET AUTHORITY=? WHERE RS_ID=?";

	private static final String UPDATE_AUTHORITY_AND_CHECK = "UPDATE REST_TABLE SET RS_CHECK_YN=?, AUTHORITY=? WHERE RS_ID=?";

	// 業者上傳餐廳的5張圖片，並更改權限值
	private static final String UPDATE_SIX_PIC = "UPDATE REST_TABLE SET RS_VIEW1=?, RS_VIEW2=?, RS_VIEW3=?, RS_VIEW4=?, RS_VIEW5=?, AUTHORITY=? WHERE RS_ID=?";

	// 給首頁熱門餐廳使用
	private static final String GET_HITO ="SELECT rs_id,rs_name,rs_address,rs_phone,rs_intro,rs_type from rest_table order by dbms_random.value()";
	
	//給首頁新進餐廳使用
	private static final String GET_FORNEW ="SELECT rs_id,rs_name,rs_address,rs_phone,rs_intro,rs_type from rest_table order by rs_id DESC";
	
	
	private static final String Get_RS_TYPE = "SELECT * FROM REST_TABLE WHERE RS_TYPE LIKE ?";
	private static final String Get_RS_ADDRESS = "SELECT * FROM REST_TABLE WHERE RS_ADDRESS LIKE ?";

	@Override
	public List<Rest_TableVO> findByRsName(String rs_type) {
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		Rest_TableVO rest_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_RS_TYPE);

			pstmt.setString(1, rs_type);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_tableVO = new Rest_TableVO();
				rest_tableVO.setRs_id(rs.getString("rs_id"));
				rest_tableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_tableVO.setRs_name(rs.getString("rs_name"));
				rest_tableVO.setRs_address(rs.getString("rs_address"));
				rest_tableVO.setRs_phone(rs.getString("rs_phone"));
				rest_tableVO.setRs_intro(rs.getString("rs_intro"));
				rest_tableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_tableVO.setRs_type(rs.getString("rs_type"));
				rest_tableVO.setRs_big_pic(rs.getBytes("rs_bigpic"));
				rest_tableVO.setRs_pic(rs.getBytes("rs_pic"));
				rest_tableVO.setRs_view1(rs.getBytes("rs_view1"));
				rest_tableVO.setRs_view2(rs.getBytes("rs_view2"));
				rest_tableVO.setRs_view3(rs.getBytes("rs_view3"));
				rest_tableVO.setRs_view4(rs.getBytes("rs_view4"));
				rest_tableVO.setRs_view5(rs.getBytes("rs_view5"));
				rest_tableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_tableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_tableVO.setAuthority(rs.getInt("authority"));

				list.add(rest_tableVO);

			}

			// Handle any driver errors
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
		return list;
	}
	
	@Override
	public List<Rest_TableVO> findByRsAddress(String rs_address) {
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		Rest_TableVO rest_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_RS_ADDRESS);

			pstmt.setString(1, rs_address);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_tableVO = new Rest_TableVO();
				rest_tableVO.setRs_id(rs.getString("rs_id"));
				rest_tableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_tableVO.setRs_name(rs.getString("rs_name"));
				rest_tableVO.setRs_address(rs.getString("rs_address"));
				rest_tableVO.setRs_phone(rs.getString("rs_phone"));
				rest_tableVO.setRs_intro(rs.getString("rs_intro"));
				rest_tableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_tableVO.setRs_type(rs.getString("rs_type"));
				rest_tableVO.setRs_big_pic(rs.getBytes("rs_bigpic"));
				rest_tableVO.setRs_pic(rs.getBytes("rs_pic"));
				rest_tableVO.setRs_view1(rs.getBytes("rs_view1"));
				rest_tableVO.setRs_view2(rs.getBytes("rs_view2"));
				rest_tableVO.setRs_view3(rs.getBytes("rs_view3"));
				rest_tableVO.setRs_view4(rs.getBytes("rs_view4"));
				rest_tableVO.setRs_view5(rs.getBytes("rs_view5"));
				rest_tableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_tableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_tableVO.setAuthority(rs.getInt("authority"));

			}
			// Handle any driver errors
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
		return list;
	}
	
	
	//給首頁新進餐廳使用
	@Override 
	public List<Rest_TableVO> getAllNew() {
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FORNEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("Rs_id"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));

				list.add(rest_TableVO); // Store the row in the list
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
		return list;
	}
	
	// 給首頁使用熱門餐廳
	@Override 
	public List<Rest_TableVO> getAll() {
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HITO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("Rs_id"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));

				list.add(rest_TableVO); // Store the row in the list
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
		return list;
	}

	// 業者新增餐廳用
	@Override
	public String insert(Rest_TableVO rest_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rs_id = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			File file = new File("C:/TEA101_WebApp/eclipse_WTP_workspace1/TEA102G1/WebContent/images/no_img.jpg");
			byte[] pic = compyPicToBytes(file);

			con.setAutoCommit(false);
			String[] cols = { "RS_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, rest_TableVO.getCp_contact_email());
			pstmt.setString(2, rest_TableVO.getRs_name());
			pstmt.setString(3, rest_TableVO.getRs_address());
			pstmt.setString(4, rest_TableVO.getRs_phone());
			pstmt.setString(5, rest_TableVO.getRs_intro());
			pstmt.setInt(6, 2);
			pstmt.setString(7, rest_TableVO.getRs_type());
			pstmt.setBytes(8, rest_TableVO.getRs_big_pic());
			pstmt.setBytes(9, pic);
			pstmt.setBytes(10, pic);
			pstmt.setBytes(11, pic);
			pstmt.setBytes(12, pic);
			pstmt.setBytes(13, pic);
			pstmt.setBytes(14, pic);
			pstmt.setString(15, rest_TableVO.getRs_open_time());
			pstmt.setDate(16, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setInt(17, 11);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();

			while (rs.next()) {
				rs_id = rs.getString(1);
			}
			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rs_id;
	}

	// 撈取資料庫中,餐廳的RS_ID
	@Override
	public String getRs_id(String cp_contact_email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rs_id = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_RS_ID);
			pstmt.setString(1, cp_contact_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rs_id = rs.getString("RS_ID");
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rs_id;
	}

	// 業者登入後取餐廳審核狀態值
	@Override
	public String getRs_check_yn(String cp_contact_email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rs_check_yn = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_RS_CHECK_YN);
			pstmt.setString(1, cp_contact_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rs_check_yn = rs.getInt("RS_CHECK_YN") + "";
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rs_check_yn;
	}

	// 撈資料庫中,RS表格中的全部資料(只撈三個欄位)
	@Override
	public List<Rest_TableVO> getOne() {
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_ONLY_THREE_DATA);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				list.add(rest_TableVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 前台查詢指定表格中其中一筆資料
	@Override
	public Rest_TableVO getOneAll(String rs_id) {
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rs_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("rs_id"));
				rest_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));
				rest_TableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_TableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_TableVO.setAuthority(rs.getInt("authority"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return rest_TableVO;
	}

	// 取得指定資料的全部照片
	@Override
	public Rest_TableVO getOneAllOnlyPic(String rs_id) {
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rs_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_big_pic(rs.getBytes("rs_bigpic"));
				rest_TableVO.setRs_pic(rs.getBytes("rs_pic"));
				rest_TableVO.setRs_view1(rs.getBytes("rs_view1"));
				rest_TableVO.setRs_view2(rs.getBytes("rs_view2"));
				rest_TableVO.setRs_view3(rs.getBytes("rs_view3"));
				rest_TableVO.setRs_view4(rs.getBytes("rs_view4"));
				rest_TableVO.setRs_view5(rs.getBytes("rs_view5"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rest_TableVO;
	}

	// 業者按下"審核申請"按鈕
	@Override
	public void updateAuthorityToApplication(String rs_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTHORITY);

			con.setAutoCommit(false);
			pstmt.setInt(1, 13);
			pstmt.setString(2, rs_id);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 業者上傳餐廳的6張圖片
	@Override
	public void updatePicAndView(Rest_TableVO rest_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_SIX_PIC);

			con.setAutoCommit(false);
			pstmt.setBytes(1, rest_TableVO.getRs_view1());
			pstmt.setBytes(2, rest_TableVO.getRs_view2());
			pstmt.setBytes(3, rest_TableVO.getRs_view3());
			pstmt.setBytes(4, rest_TableVO.getRs_view4());
			pstmt.setBytes(5, rest_TableVO.getRs_view5());
			pstmt.setInt(6, 12);
			pstmt.setString(7, rest_TableVO.getRs_id());
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 後臺餐廳審核撈資料
	@Override
	public List<Rest_TableVO> getAll(String sql) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Rest_TableVO rest_TableVO = null;
		ResultSet rs = null;
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("rs_id"));
				rest_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));
				rest_TableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_TableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_TableVO.setAuthority(rs.getInt("authority"));
				list.add(rest_TableVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 後臺找單一欄位照片
	@Override
	public byte[] getOnePic(String sql, String pic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] pics = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pics = rs.getBytes(pic);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pics;
	}

	// 餐廳審核完畢更改權限值13==>4,及審核狀態值為2==>1
	@Override
	public void updateAuthorityIsOk(String rs_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTHORITY_AND_CHECK);

			con.setAutoCommit(false);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 4);
			pstmt.setString(3, rs_id);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 後台下架餐廳 或 公司刪除
	@Override
	public void deleteRest(String rs_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTHORITY_AND_CHECK);

			con.setAutoCommit(false);
			pstmt.setInt(1, 2);
			pstmt.setInt(2, 10);
			pstmt.setString(3, rs_id);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 照片轉碼用
	private byte[] compyPicToBytes(File file) {
		byte[] pic = null;
		try (InputStream in = new FileInputStream(file)) {
			pic = new byte[in.available()];
			in.read(pic);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return pic;
	}

	@Override
	public List<Rest_TableVO> getAllRestForSomeCondition(String sql) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Rest_TableVO rest_TableVO = null;
		ResultSet rs = null;
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("rs_id"));
				rest_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));
				rest_TableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_TableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_TableVO.setAuthority(rs.getInt("authority"));
				list.add(rest_TableVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}    
