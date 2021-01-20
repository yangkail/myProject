package com.compy_table.model;

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
import java.util.List;

import javax.servlet.http.Part;

import org.apache.naming.java.javaURLContextFactory;



public class Compy_TableDAOjdbc implements Compy_TableDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 驅動程式
	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL
	String userid = "TEA102G1";// 帳號
	String passwd = "123456";// 密碼

	// 業者前台登入會員驗證,取帳號及密碼
	private static final String GET_ACCOUNT_PWD_LOGIN_USE = "SELECT CP_NAME, CP_CONTACT_EMAIL, CP_ACCOUNT, CP_PWD, AUTHORITY FROM COMPY_TABLE WHERE CP_ACCOUNT=?";

	// 新增 不含圖片
	private static final String INSERT_STMT = "INSERT INTO COMPY_TABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,null,?,?,?)";

	// 前端 業者更改大頭貼
	private static final String FRONT_UPDATE_BIGPIC = "UPDATE COMPY_TABLE SET CP_BIGPIC=? WHERE CP_CONTACT_EMAIL=?";

	// 前端 業者更改LOGO
	private static final String FRONT_UPDATE_LOGO = "UPDATE COMPY_TABLE SET CP_LOGO=? WHERE CP_CONTACT_EMAIL=?";
	
	// 前端 業者更改營業登記證
	private static final String FRONT_UPDATE_BUSINESS = "UPDATE COMPY_TABLE SET CP_BUSINESS=? WHERE CP_CONTACT_EMAIL=?";

	// 查詢全部
	private static final String GET_ALL_STMT = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE";

	// 查詢全部(用PK鍵 CP_CONTACT_EMAIL)
	private static final String GET_ONE_STMT_PK = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE WHERE CP_CONTACT_EMAIL=?";

	// 查詢全部(用帳號 CP_ACCOUNT)
	private static final String GET_ONE_STMT_ACCOUNT = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE WHERE CP_ACCOUNT=?";

	// 查詢全部(用公司名稱 CP_NAME)
	private static final String GET_ONE_STMT_NAME = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE WHERE CP_NAME=?";

	// 更改權限(2代表沒問題，10代表已被刪除，11代表註冊未驗證，12代表以驗證但未點選EMAIL)
	private static final String UPDATE_AUTHORITY = "UPDATE COMPY_TABLE SET AUTHORITY=?,CP_UPDATE_TIME=? WHERE CP_CONTACT_EMAIL=?";
	
	// 更改全部(目前沒用到)
	private static final String UPDATE = "UPDATE COMPY_TABLE SET CP_PHONE=?, CP_NAME=?, CP_ID=?, CP_ADDRESS=?, CP_BOSS=?, CP_CONTACT_MAN=?, CP_PWD=?, CP_CREDIT=?, CP_BUSINESS=?, CP_LOGO=?, CP_BIGPIC=?, CP_UPDATE_TIME=?  WHERE CP_CONTACT_EMAIL=?";

	// 業者上傳圖片(註冊完後 或 業者想新增或更改圖片)
	private static final String UPDATE_PIC = "UPDATE COMPY_TABLE SET CP_BUSINESS=?, CP_LOGO=?, CP_BIGPIC=? WHERE CP_CONTACT_EMAIL=?";

	// 查詢除了指定主鍵外的其他全部資料(除了圖片)
	private static final String GET_OTHER_COMP = "SELECT CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_PWD, CP_CREDIT FROM COMPY_TABLE WHERE CP_CONTACT_EMAIL NOT IN(?)";

	// 後臺更改指定業者的全部資料
	private static final String UPDATE_COMPY_INFO = "UPDATE COMPY_TABLE SET CP_PHONE=?, CP_NAME=?, CP_ID=?, CP_ADDRESS=?, CP_BOSS=?, CP_CONTACT_MAN=?, CP_PWD=?, CP_CREDIT=?, CP_BUSINESS=?, CP_LOGO=?, CP_BIGPIC=?, CP_UPDATE_TIME=?  WHERE CP_CONTACT_EMAIL=?";

	// 用主鍵取得照片
	private static final String GET_ONE_COMPY_PICS = "SELECT CP_BUSINESS, CP_LOGO, CP_BIGPIC FROM COMPY_TABLE WHERE CP_CONTACT_EMAIL=?";
	
	// 業者更改密碼
	private static final String UPDATE_PWD = "UPDATE COMPY_TABLE SET CP_PWD=? WHERE CP_CONTACT_EMAIL=?";

	// 業者前台登入會員驗證,取帳號及密碼
	@Override
	public Compy_TableVO findCompyAccountPwd_LoginUse(String cp_account) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACCOUNT_PWD_LOGIN_USE);
			pstmt.setString(1, cp_account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return compy_TableVO;
	}

	// 新增
	@Override
	public void insert(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			File file=new File("C:/TEA101_WebApp/eclipse_WTP_workspace1/TEA102G1/WebContent/images/no_img.jpg");
			byte[] pic=compyPicToBytes(file);
			
			pstmt.setString(1, compy_TableVO.getCp_contact_email());
			pstmt.setString(2, compy_TableVO.getCp_phone());
			pstmt.setString(3, compy_TableVO.getCp_name());
			pstmt.setString(4, compy_TableVO.getCp_id());
			pstmt.setString(5, compy_TableVO.getCp_address());
			pstmt.setString(6, compy_TableVO.getCp_boss());
			pstmt.setString(7, compy_TableVO.getCp_contact_man());
			pstmt.setString(8, compy_TableVO.getCp_account());
			pstmt.setString(9, compy_TableVO.getCp_pwd());
			pstmt.setBytes(10, pic);
			pstmt.setBytes(11, pic);
			pstmt.setBytes(12, pic);
			pstmt.setDate(13, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setDate(14, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setInt(15, 11);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	// 前端 業者更改大頭貼
	@Override
	public void frontChangeBigPic(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FRONT_UPDATE_BIGPIC);

			pstmt.setBytes(1, compy_TableVO.getCp_bigpic());
			pstmt.setString(2, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
	
	// 前端 業者更改LOGO
	@Override
	public void frontChangeLogo(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FRONT_UPDATE_LOGO);

			pstmt.setBytes(1, compy_TableVO.getCp_logo());
			pstmt.setString(2, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
	
	// 前端 業者更改營業登記證
	@Override
	public void frontChangeBusiness(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FRONT_UPDATE_BUSINESS);

			pstmt.setBytes(1, compy_TableVO.getCp_business());
			pstmt.setString(2, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	// 更改
	@Override
	public void update(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, compy_TableVO.getCp_phone());
			pstmt.setString(2, compy_TableVO.getCp_name());
			pstmt.setString(3, compy_TableVO.getCp_id());
			pstmt.setString(4, compy_TableVO.getCp_address());
			pstmt.setString(5, compy_TableVO.getCp_boss());
			pstmt.setString(6, compy_TableVO.getCp_contact_man());
			pstmt.setString(7, compy_TableVO.getCp_pwd());
			pstmt.setString(8, compy_TableVO.getCp_credit());
			pstmt.setBytes(9, compy_TableVO.getCp_business());
			pstmt.setBytes(10, compy_TableVO.getCp_logo());
			pstmt.setBytes(11, compy_TableVO.getCp_bigpic());
			pstmt.setDate(12, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(13, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	// 更改業者表格照片
	@Override
	public void updateCompyPic(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PIC);

			pstmt.setBytes(1, compy_TableVO.getCp_business());
			pstmt.setBytes(2, compy_TableVO.getCp_logo());
			pstmt.setBytes(3, compy_TableVO.getCp_bigpic());
			pstmt.setString(4, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	// 後臺查詢表格資料-排除指定資料
	@Override
	public List<Compy_TableVO> findOtherCompanies(String cp_contact_email) {
		List<Compy_TableVO> list = new ArrayList<Compy_TableVO>();
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OTHER_COMP);
			pstmt.setString(1, cp_contact_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				list.add(compy_TableVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

		return list;
	}

	// 後臺確認業者更改資料
	@Override
	public void updateCompyInfo(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_COMPY_INFO);

			pstmt.setString(1, compy_TableVO.getCp_phone());
			pstmt.setString(2, compy_TableVO.getCp_name());
			pstmt.setString(3, compy_TableVO.getCp_id());
			pstmt.setString(4, compy_TableVO.getCp_address());
			pstmt.setString(5, compy_TableVO.getCp_boss());
			pstmt.setString(6, compy_TableVO.getCp_contact_man());
			pstmt.setString(7, compy_TableVO.getCp_pwd());
			pstmt.setString(8, compy_TableVO.getCp_credit());
			pstmt.setBytes(9, compy_TableVO.getCp_business());
			pstmt.setBytes(10, compy_TableVO.getCp_logo());
			pstmt.setBytes(11, compy_TableVO.getCp_bigpic());
			pstmt.setDate(12, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(13, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	// 後台確認刪除資料
	@Override
	public void delete(String cp_contact_email) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTHORITY);

			pstmt.setInt(1, 10);
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(3, cp_contact_email);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
	
	//後臺點選確認驗證後
	@Override
	public void updateFromBack(String cp_contact_email) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTHORITY);

			pstmt.setInt(1, 12);
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(3, cp_contact_email);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	//業者點選email確認驗證後
	@Override
	public void updateFromEMAILBack(String cp_contact_email) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTHORITY);

			pstmt.setInt(1, 2);
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(3, cp_contact_email);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

	// 用主鍵找表格資料
	@Override
	public Compy_TableVO findByPrimaryKey(String cp_contact_email) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_PK);

			pstmt.setString(1, cp_contact_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

		return compy_TableVO;
	}

	// 用業者帳號找表格資料
	@Override
	public Compy_TableVO findByCpAccount(String cp_account) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_ACCOUNT);

			pstmt.setString(1, cp_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

		return compy_TableVO;
	}

	// 用業者公司名稱找表格資料
	@Override
	public Compy_TableVO findByCpName(String cp_name) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_NAME);

			pstmt.setString(1, cp_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return compy_TableVO;
	}

	// 找表格全部
	@Override
	public List<Compy_TableVO> getAll() {
		List<Compy_TableVO> list = new ArrayList<Compy_TableVO>();
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
				list.add(compy_TableVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

		return list;
	}

	// 用主鍵只找業者圖片
	@Override
	public Compy_TableVO findCompyPic(String cp_contact_email) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_COMPY_PICS);
			pstmt.setString(1, cp_contact_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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
		return compy_TableVO;
	}
	
	//業者更改密碼
	@Override
	public void updatePwd(String cp_contact_email, String cp_pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PWD);
			
			pstmt.setString(1, cp_pwd);
			pstmt.setString(2, cp_contact_email);
			pstmt.executeUpdate();
			

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

//===========================以下為測試=====================================================================================================================
	public static void main(String[] args) {

		// =====================insert 新增==============================
//		Compy_TableVO compy_TableVO = new Compy_TableVO();
//		compy_TableVO.setCp_contact_email("abc@gmail.com");
//		compy_TableVO.setCp_phone("0900123456");
//		compy_TableVO.setCp_name("你好食品公司");
//		compy_TableVO.setCp_id("12345678");
//		compy_TableVO.setCp_address("台北市中正區");
//		compy_TableVO.setCp_boss("阿土伯");
//		compy_TableVO.setCp_contact_man("孫小美");
//		compy_TableVO.setCp_account("111111");
//		compy_TableVO.setCp_pwd("333333");
//		compy_TableVO.setCp_reg_time(java.sql.Date.valueOf("2020-11-27"));
//
//		Compy_TableDAOjdbc compy_TableDAOjdbc = new Compy_TableDAOjdbc();
//		compy_TableDAOjdbc.insert(compy_TableVO);
//		System.out.println("COMPY_TABLE 資料\"新增\"成功");

		// =====================update 更新==============================
//		Compy_TableVO compy_TableVO = new Compy_TableVO();
//		compy_TableVO.setCp_contact_email("abc@gmail.com");
//		compy_TableVO.setCp_phone("0900123456");
//		compy_TableVO.setCp_name("你好食品公司");
//		compy_TableVO.setCp_id("12345678");
//		compy_TableVO.setCp_address("台北市中正區");
//		compy_TableVO.setCp_boss("阿土伯");
//		compy_TableVO.setCp_contact_man("孫小美");
//		compy_TableVO.setCp_account("111111");
//		compy_TableVO.setCp_pwd("333333");
//		compy_TableVO.setCp_reg_time(java.sql.Date.valueOf("1999-10-11"));
//		
//
//		Compy_TableDAOjdbc compy_TableDAOjdbc = new Compy_TableDAOjdbc();
//		compy_TableDAOjdbc.update(compy_TableVO);
//		System.out.println("COMPY_TABLE 資料\"更新\"成功");

		// =====================delete 刪除表============================
//		Compy_TableDAOjdbc compy_TableDAOjdbc=new Compy_TableDAOjdbc();
//		compy_TableDAOjdbc.delete("abc@gmail.com");
//		System.out.println("COMPY_TABLE 資料\"刪除\"成功");

		// =====================get one all 查詢指定欄位全部=========================
//		Compy_TableVO compy_TableVO=new Compy_TableDAOjdbc().findByPrimaryKey("abc@gmail.com");
//		System.out.println(compy_TableVO.getCp_contact_email());
//		System.out.println(compy_TableVO.getCp_phone());
//		System.out.println(compy_TableVO.getCp_name());
//		System.out.println(compy_TableVO.getCp_id());
//		System.out.println(compy_TableVO.getCp_address());
//		System.out.println(compy_TableVO.getCp_boss());
//		System.out.println(compy_TableVO.getCp_contact_man());
//		System.out.println(compy_TableVO.getCp_account());
//		System.out.println(compy_TableVO.getCp_pwd());
//		System.out.println(compy_TableVO.getCp_business());
//		System.out.println(compy_TableVO.getCp_logo());
//		System.out.println(compy_TableVO.getCp_bigpic());
//		System.out.println(compy_TableVO.getCp_credit());
//		System.out.println(compy_TableVO.getCp_reg_time());
//		System.out.println(compy_TableVO.getCp_update_time());
//		System.out.println(compy_TableVO.getAuthority());

		// =====================get all 查詢指定table全部=========================
//		List<Compy_TableVO> list=new Compy_TableDAOjdbc().getAll();
//		for(Compy_TableVO compy_TableVO:list) {
//			System.out.println(compy_TableVO.getCp_contact_email());
//			System.out.println(compy_TableVO.getCp_phone());
//			System.out.println(compy_TableVO.getCp_name());
//			System.out.println(compy_TableVO.getCp_id());
//			System.out.println(compy_TableVO.getCp_address());
//			System.out.println(compy_TableVO.getCp_boss());
//			System.out.println(compy_TableVO.getCp_contact_man());
//			System.out.println(compy_TableVO.getCp_account());
//			System.out.println(compy_TableVO.getCp_pwd());
//			System.out.println(compy_TableVO.getCp_business());
//			System.out.println(compy_TableVO.getCp_logo());
//			System.out.println(compy_TableVO.getCp_bigpic());
//			System.out.println(compy_TableVO.getCp_credit());
//			System.out.println(compy_TableVO.getCp_reg_time());
//			System.out.println(compy_TableVO.getCp_update_time());
//			System.out.println(compy_TableVO.getAuthority());
//		}

		// =====================get all 排除指定資料=========================
//		List<Compy_TableVO> list=new Compy_TableDAOjdbc().findOtherCompanies("asd0000fg@hotmail.com");
//		for(Compy_TableVO compy_TableVO:list) {
//			System.out.println(compy_TableVO.getCp_contact_email());
//			System.out.println(compy_TableVO.getCp_phone());
//			System.out.println(compy_TableVO.getCp_name());
//			System.out.println(compy_TableVO.getCp_id());
//			System.out.println(compy_TableVO.getCp_address());
//			System.out.println(compy_TableVO.getCp_boss());
//			System.out.println(compy_TableVO.getCp_contact_man());
//			System.out.println(compy_TableVO.getCp_account());
//			System.out.println(compy_TableVO.getCp_pwd());
//			System.out.println(compy_TableVO.getCp_business());
//			System.out.println(compy_TableVO.getCp_logo());
//			System.out.println(compy_TableVO.getCp_bigpic());
//			System.out.println(compy_TableVO.getCp_credit());
//			System.out.println(compy_TableVO.getCp_reg_time());
//			System.out.println(compy_TableVO.getCp_update_time());
//			System.out.println(compy_TableVO.getAuthority());
//			System.out.println("================================");
//		}
	}
}
