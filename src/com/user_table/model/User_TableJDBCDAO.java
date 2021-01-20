package com.user_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class User_TableJDBCDAO implements User_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";
	//新增管理者		
	private static final String INSERT_STMT = "INSERT INTO user_table(user_id,user_account,user_password,user_job,user_depart) VALUES ('UR' || lpad(USER_TABLE_SEQ.Nextval,5,'0'),?,?,?,?)";
	//查詢全部管理者		
	private static final String GET_ALL_STMT = "SELECT user_id,user_account,user_password,user_job,user_depart,authority from user_table order by user_ID ";
	//查詢單筆管理者
	private static final String GET_ONE_STMT = "SELECT user_id,user_account,user_password,user_job,user_depart,authority from user_table where user_ID=?";
	// findByPrimaryKey刪除單一管理者
	private static final String DELETE = "DELETE FROM user_table WHERE user_ID=?";
	//更新管理者資料
	private static final String UPDATE = "UPDATE user_table set user_account=?,user_password=?,user_job=?,user_depart=?,authority=? where user_ID=?";
	//只顯示不重複資料select bar
	private static final String DEPART = "select distinct user_depart from user_table";
	//管理者登入
	private static final String LOGIN = "select user_account, user_password, authority from user_table where user_account=? ";

	@Override //新增管理者	
	public void insert(User_TableVO user_tableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, user_tableVO.getUser_account());
			pstmt.setString(2, user_tableVO.getUser_password());
			pstmt.setString(3, user_tableVO.getUser_job());
			pstmt.setString(4, user_tableVO.getUser_depart());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
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

	@Override  //更新管理者資料
	public void update(User_TableVO user_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, user_tableVO.getUser_account());
			pstmt.setString(2, user_tableVO.getUser_password());
			pstmt.setString(3, user_tableVO.getUser_job());
			pstmt.setString(4, user_tableVO.getUser_depart());
			pstmt.setInt(5, user_tableVO.getAuthority());
			pstmt.setString(6, user_tableVO.getUser_id());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
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

	@Override  	// findByPrimaryKey刪除單一管理者
	public void delete(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
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

	@Override   //只顯示不重複資料selectbar
	public List<User_TableVO> getDepart() { 
		List<User_TableVO> listD = new ArrayList<User_TableVO>();
		User_TableVO user_tableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DEPART);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user_tableVO = new User_TableVO();
				user_tableVO.setUser_depart(rs.getString("user_depart"));
				listD.add(user_tableVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
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
		return listD;
	}

	@Override  	//查詢單筆管理者
	public User_TableVO findByPrimaryKey(String user_id) {
		User_TableVO user_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user_tableVO = new User_TableVO();
				user_tableVO.setUser_id(rs.getString("user_id"));
				user_tableVO.setUser_account(rs.getString("user_account"));
				user_tableVO.setUser_password(rs.getString("user_password"));
				user_tableVO.setUser_job(rs.getString("user_job"));
				user_tableVO.setUser_depart(rs.getString("user_depart"));
				user_tableVO.setAuthority(rs.getInt("authority"));
			}
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
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
		return user_tableVO;
	}
	
	@Override  //管理者登入
	public User_TableVO getLog(String user_account) {
		User_TableVO user_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, user_account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user_tableVO = new User_TableVO();
				user_tableVO.setUser_account(rs.getString("user_account"));
				user_tableVO.setUser_password(rs.getString("user_password"));
				user_tableVO.setAuthority(rs.getInt("authority"));
				 // Store the row in the list
			}
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
		return user_tableVO;
	}

	@Override   //查詢全部管理者	
	public List<User_TableVO> getAll() {
		List<User_TableVO> list = new ArrayList<User_TableVO>();
		User_TableVO user_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				user_tableVO = new User_TableVO();
				user_tableVO.setUser_id(rs.getString("user_id"));
				user_tableVO.setUser_account(rs.getString("user_account"));
				user_tableVO.setUser_password(rs.getString("user_password"));
				user_tableVO.setUser_job(rs.getString("user_job"));
				user_tableVO.setUser_depart(rs.getString("user_depart"));

				user_tableVO.setAuthority(rs.getInt("authority"));
				list.add(user_tableVO); // Store the row in the list
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

	public static void main(String[] args) {

		User_TableJDBCDAO dao = new User_TableJDBCDAO();
	
		//登入
//		User_TableVO user_tableVO = dao.getLog("MORRY");
//		System.out.println(user_tableVO.getUser_account());
//		System.out.println(user_tableVO.getUser_password());
//		System.out.println(user_tableVO.getAuthority());
////		System.out.println("=================");
//		String act = user_tableVO.getUser_account();
//		String pwd = user_tableVO.getUser_password();
//		System.out.println(act);
//		System.out.println(pwd);
		
		// 新增
//		User_TableVO user_tableVO1 = new User_TableVO();
//		user_tableVO1.setUser_account("TERRY");
//		user_tableVO1.setUser_password("T123456");
//		user_tableVO1.setUser_depart("測試部");
//		user_tableVO1.setUser_job("組長");
//		user_tableVO1.setAuthority(1);
//		dao.insert(user_tableVO1);
//		System.out.println("新增成功");
//
//		// 修改
//		User_TableVO user_tableVO2 = new User_TableVO();
//		user_tableVO2.setUser_account("KAIIII");
//		user_tableVO2.setUser_password("9990000");
//		user_tableVO2.setUser_job("0000000");
//		user_tableVO2.setUser_depart("KAIIII");
//		user_tableVO2.setAuthority(1);
//		user_tableVO2.setUser_id("UR00107");
//		dao.update(user_tableVO2);
//		System.out.println("修改成功");
//
//		// 刪除
//		dao.delete("KAIIII");
//		System.out.println("刪除成功");
//
//		// 查詢
//		User_TableVO user_tableVO3 = dao.findByPrimaryKey("UR00101");
//		System.out.print(user_tableVO3.getUser_id() + ",");
//		System.out.print(user_tableVO3.getUser_account() + ",");
//		System.out.print(user_tableVO3.getUser_password() + ",");
//		System.out.print(user_tableVO3.getUser_job() + ",");
//		System.out.print(user_tableVO3.getUser_depart() + ",");
//		System.out.println(user_tableVO3.getAuthority() + ",");
//		System.out.println("查詢成功");

		// 查詢
//		List<User_TableVO> list = dao.getAll();
//		for (User_TableVO aEmp : list) {
//		System.out.println(aEmp.getUser_id() + ",");
//		System.out.println(aEmp.getUser_account() + ",");
//		System.out.println(aEmp.getUser_password() + ",");
//		System.out.println(aEmp.getUser_depart() + ",");
//		System.out.println(aEmp.getUser_job() + ",");
//		System.out.println(aEmp.getAuthority() + ",");
//		System.out.println("==========");
//
//		}

		List<User_TableVO> listD = dao.getDepart();
		for (User_TableVO part : listD) {
			System.out.println(part.getUser_depart());

		
//		}
		}
	}
	}


