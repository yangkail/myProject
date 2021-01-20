package com.order_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class Order_TableJDBCDAO implements Order_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO order_table VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table order by order_id";

	private static final String GET_ONE_STMT = "SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table where order_id = ?";

	private static final String GET_RS_ID = "SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table WHERE rs_id = ?";

	private static final String GET_GS_EMAIL = "SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table WHERE gs_email = ?";

	private static final String GET_ALL_ORDER_ID = "SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table where order_id = ?";

	private static final String DELETE = "DELETE FROM order_table where order_id = ?";

	private static final String UPDATE = "UPDATE order_table set rs_id=?, gs_email=?, order_status=?,order_time=?, order_cancel_time=?, gs_order_remark=?, gs_people=?, gs_select_time=?, order_deposit=?, order_qrcode=?, rs_table_status=? where order_id = ?";

	private static final String UPDATE2 = "UPDATE ORDER_TABLE SET ORDER_STATUS = 2 WHERE ORDER_ID = ?";

	private static final String UPDATE0 = "UPDATE ORDER_TABLE SET ORDER_STATUS = 0 WHERE ORDER_ID = ?";

	private static final String INSERT_WITH_BOOKING = "INSERT INTO order_table VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// 前台RS_complete_order_page.jsp 訂單狀態:成立訂單 使用
	private static final String GET_ALL_COMPLETE_ORDER = "SELECT * FROM ORDER_TABLE WHERE RS_ID=? AND ORDER_STATUS=1";

	// 前台RS_cancel_order_page.jsp 訂單狀態:取消訂單 使用
	private static final String GET_ALL_CANCEL_ORDER = "SELECT * FROM ORDER_TABLE WHERE RS_ID=? AND ORDER_STATUS=0";

	// 前台Consumer_mail_complete_page.jsp 訂單狀態:成立訂單 使用
	private static final String GET_ALL_CONSUMER_COMPLETE = "SELECT ORDER_TABLE.order_id, ORDER_TABLE.rs_id, ORDER_TABLE.gs_email, "
			+ "ORDER_TABLE.order_status, ORDER_TABLE.order_time,ORDER_TABLE.order_cancel_time, " 
			+ "ORDER_TABLE.gs_order_remark , ORDER_TABLE.gs_people, ORDER_TABLE.gs_select_time, "
			+ "ORDER_TABLE.order_deposit , ORDER_TABLE.order_qrcode , ORDER_TABLE.rs_table_status,"
			+ "REST_TABLE.rs_name FROM ORDER_TABLE JOIN REST_TABLE ON(ORDER_TABLE.rs_id = REST_TABLE.rs_id) where ORDER_TABLE.gs_email = ? AND ORDER_STATUS=1";

	// 前台Consumer_mail_cancel_page.jsp 訂單狀態:取消訂單 使用
	private static final String GET_ALL_CONSUMER_CANCEL = "SELECT ORDER_TABLE.order_id, ORDER_TABLE.rs_id, ORDER_TABLE.gs_email, "
			+"ORDER_TABLE.order_status, ORDER_TABLE.order_time,ORDER_TABLE.order_cancel_time, " 
			+"ORDER_TABLE.gs_order_remark , ORDER_TABLE.gs_people, ORDER_TABLE.gs_select_time, " 
			+"ORDER_TABLE.order_deposit , ORDER_TABLE.order_qrcode , ORDER_TABLE.rs_table_status," 
			+"REST_TABLE.rs_name FROM ORDER_TABLE JOIN REST_TABLE ON(ORDER_TABLE.rs_id = REST_TABLE.rs_id) where ORDER_TABLE.gs_email = ? AND ORDER_STATUS=0";

	// ORDER JOIN REST
	private static final String GET_ORDER_REST = "SELECT ORDER_TABLE.order_id, ORDER_TABLE.rs_id, ORDER_TABLE.gs_email, "
			+ "ORDER_TABLE.order_status, ORDER_TABLE.order_time,ORDER_TABLE.order_cancel_time, "
			+ "ORDER_TABLE.gs_order_remark , ORDER_TABLE.gs_people, ORDER_TABLE.gs_select_time, "
			+ "ORDER_TABLE.order_deposit , ORDER_TABLE.order_qrcode , ORDER_TABLE.rs_table_status,"
			+ "REST_TABLE.rs_name FROM ORDER_TABLE JOIN REST_TABLE ON(ORDER_TABLE.rs_id = REST_TABLE.rs_id) where ORDER_TABLE.gs_email = ?";
//		"INSERT INTO order_table VALUES (order_table_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		"INSERT INTO order_table (order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status) VALUES (order_table_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public List<Order_Rest_TableVO> getAllConsumer_Complete(String gs_email) {

		List<Order_Rest_TableVO> list = new ArrayList<Order_Rest_TableVO>();

		Order_Rest_TableVO order_rest_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_CONSUMER_COMPLETE);
			pstmt.setString(1, gs_email);
			rs = pstmt.executeQuery();
			System.out.println(GET_ALL_CONSUMER_COMPLETE);
			System.out.println("連線成功");
			while (rs.next()) {
				order_rest_tableVO = new Order_Rest_TableVO();
				order_rest_tableVO.setRs_id(rs.getString("rs_id"));
				order_rest_tableVO.setOrder_id(rs.getString("order_id"));
				order_rest_tableVO.setGs_email(rs.getString("gs_email"));
				order_rest_tableVO.setOrder_status(rs.getInt("order_status"));
				order_rest_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_rest_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_rest_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_rest_tableVO.setGs_people(rs.getInt("gs_people"));
				order_rest_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_rest_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_rest_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_rest_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				order_rest_tableVO.setRs_name(rs.getString("rs_name"));
				list.add(order_rest_tableVO);
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
		return list;
	}

	@Override
	public List<Order_Rest_TableVO> getAllConsumer_Cancel(String gs_email) {

		List<Order_Rest_TableVO> list = new ArrayList<Order_Rest_TableVO>();

		Order_Rest_TableVO order_rest_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_CONSUMER_CANCEL);
			pstmt.setString(1, gs_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_rest_tableVO = new Order_Rest_TableVO();
				order_rest_tableVO.setRs_id(rs.getString("rs_id"));
				order_rest_tableVO.setOrder_id(rs.getString("order_id"));
				order_rest_tableVO.setGs_email(rs.getString("gs_email"));
				order_rest_tableVO.setOrder_status(rs.getInt("order_status"));
				order_rest_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_rest_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_rest_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_rest_tableVO.setGs_people(rs.getInt("gs_people"));
				order_rest_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_rest_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_rest_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_rest_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				order_rest_tableVO.setRs_name(rs.getString("rs_name"));
				list.add(order_rest_tableVO);
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
		return list;
	}

	@Override
	public List<Order_TableVO> getAllCancel_Order(String rs_id) {

		List<Order_TableVO> list = new ArrayList<Order_TableVO>();

		Order_TableVO order_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_CANCEL_ORDER);
			pstmt.setString(1, rs_id);
			rs = pstmt.executeQuery();

			// System.out.println("連線成功");
			while (rs.next()) {
				order_tableVO = new Order_TableVO();
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				list.add(order_tableVO);
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
		return list;
	}

	public void insertWithBooking(Order_TableVO order_TableVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_WITH_BOOKING);
			pstmt.setString(1, order_TableVO.getOrder_id());
			pstmt.setString(2, order_TableVO.getRs_id());
			pstmt.setString(3, order_TableVO.getGs_email());
			pstmt.setInt(4, order_TableVO.getOrder_status());
			pstmt.setTimestamp(5, order_TableVO.getOrder_time());
			pstmt.setTimestamp(6, order_TableVO.getOrder_cancel_time());
			pstmt.setString(7, order_TableVO.getGs_order_remark());
			pstmt.setInt(8, order_TableVO.getGs_people());
			pstmt.setString(9, order_TableVO.getGs_select_time());
			pstmt.setDouble(10, order_TableVO.getOrder_deposit());
			pstmt.setBytes(11, order_TableVO.getOrder_qrcode());
			pstmt.setInt(12, order_TableVO.getRs_table_status());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}

	// Ajax餐廳業者專用
	@Override
	public boolean update2(String order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean OK = false;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setString(1, order_id);

			pstmt.executeUpdate();
			OK = true;

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

		return OK;
	}

	// Ajax餐廳業者專用
	@Override
	public boolean update0(String order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean OK = false;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE0);

			pstmt.setString(1, order_id);

			pstmt.executeUpdate();
			OK = true;

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

		return OK;
	}

	@Override
	public void insert(Order_TableVO order_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_tableVO.getOrder_id());
			pstmt.setString(2, order_tableVO.getRs_id());
			pstmt.setString(3, order_tableVO.getGs_email());
			pstmt.setInt(4, order_tableVO.getOrder_status());
			pstmt.setTimestamp(5, order_tableVO.getOrder_time());
			pstmt.setTimestamp(6, order_tableVO.getOrder_cancel_time());
			pstmt.setString(7, order_tableVO.getGs_order_remark());
			pstmt.setInt(8, order_tableVO.getGs_people());
			pstmt.setString(9, order_tableVO.getGs_select_time());
			pstmt.setDouble(10, order_tableVO.getOrder_deposit());
			pstmt.setBytes(11, order_tableVO.getOrder_qrcode());
			pstmt.setInt(12, order_tableVO.getRs_table_status());

			pstmt.executeUpdate();

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
	public void update(Order_TableVO order_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, order_tableVO.getRs_id());
			pstmt.setString(2, order_tableVO.getGs_email());
			pstmt.setInt(3, order_tableVO.getOrder_status());
			pstmt.setTimestamp(4, order_tableVO.getOrder_time());
			pstmt.setTimestamp(5, order_tableVO.getOrder_cancel_time());
			pstmt.setString(6, order_tableVO.getGs_order_remark());
			pstmt.setInt(7, order_tableVO.getGs_people());
			pstmt.setString(8, order_tableVO.getGs_select_time());
			pstmt.setDouble(9, order_tableVO.getOrder_deposit());
			pstmt.setBytes(10, order_tableVO.getOrder_qrcode());
			pstmt.setInt(11, order_tableVO.getRs_table_status());
			pstmt.setString(12, order_tableVO.getOrder_id());

			pstmt.executeUpdate();

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
	public void update2(Order_TableVO order_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setString(1, order_tableVO.getOrder_id());

			pstmt.executeUpdate();

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
	public void delete(String order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_id);

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
	public List<Order_TableVO> getAllRs_id(String rs_id) {

		List<Order_TableVO> list = new ArrayList<Order_TableVO>();

		Order_TableVO order_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RS_ID);
			pstmt.setString(1, rs_id);
			rs = pstmt.executeQuery();

			System.out.println("連線成功");
			while (rs.next()) {
				order_tableVO = new Order_TableVO();
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				list.add(order_tableVO);
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
		return list;
	}

	@Override
	public List<Order_TableVO> getAllGs_email(String gs_email) {

		List<Order_TableVO> list = new ArrayList<Order_TableVO>();

		Order_TableVO order_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GS_EMAIL);
			pstmt.setString(1, gs_email);
			rs = pstmt.executeQuery();

			System.out.println("連線成功");
			while (rs.next()) {
				order_tableVO = new Order_TableVO();
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				list.add(order_tableVO);
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
		return list;
	}

	@Override
	public List<Order_TableVO> getAllOrder_id(String order_id) {

		List<Order_TableVO> list = new ArrayList<Order_TableVO>();

		Order_TableVO order_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ORDER_ID);
			pstmt.setString(1, order_id);
			rs = pstmt.executeQuery();

			System.out.println("連線成功");
			while (rs.next()) {
				order_tableVO = new Order_TableVO();
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				list.add(order_tableVO);
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
		return list;
	}

	@Override
	public List<Order_TableVO> getAllComplete_Order(String rs_id) {

		List<Order_TableVO> list = new ArrayList<Order_TableVO>();

		Order_TableVO order_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_COMPLETE_ORDER);
			pstmt.setString(1, rs_id);
			rs = pstmt.executeQuery();

			System.out.println("連線成功");
			while (rs.next()) {
				order_tableVO = new Order_TableVO();
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				list.add(order_tableVO);
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
		return list;
	}

	@Override
	public Order_TableVO findByPrimaryKey(String order_id) {

		Order_TableVO order_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			System.out.println("連線成功");
			pstmt.setString(1, order_id);
			rs = pstmt.executeQuery();

			System.out.println("連線成功");
			while (rs.next()) {

				order_tableVO = new Order_TableVO();
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
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
		return order_tableVO;
	}

	@Override
	public List<Order_TableVO> getAll() {
		List<Order_TableVO> list = new ArrayList<Order_TableVO>();
		Order_TableVO order_tableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_tableVO = new Order_TableVO();
				order_tableVO.setOrder_id(rs.getString("order_id"));
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setGs_email(rs.getString("gs_email"));
				order_tableVO.setOrder_status(rs.getInt("order_status"));
				order_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_tableVO.setGs_people(rs.getInt("gs_people"));
				order_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				list.add(order_tableVO);
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
		return list;
	}

	public List<Order_Rest_TableVO> get_ORDER_REST(String gs_email) {

		List<Order_Rest_TableVO> list = new ArrayList<Order_Rest_TableVO>();

		Order_Rest_TableVO order_rest_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ORDER_REST);
			pstmt.setString(1, gs_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_rest_tableVO = new Order_Rest_TableVO();
				order_rest_tableVO.setOrder_id(rs.getString("order_id"));
				order_rest_tableVO.setRs_id(rs.getString("rs_id"));
				order_rest_tableVO.setGs_email(rs.getString("gs_email"));
				order_rest_tableVO.setOrder_status(rs.getInt("order_status"));
				order_rest_tableVO.setOrder_time(rs.getTimestamp("order_time"));
				order_rest_tableVO.setOrder_cancel_time(rs.getTimestamp("order_cancel_time"));
				order_rest_tableVO.setGs_order_remark(rs.getString("gs_order_remark"));
				order_rest_tableVO.setGs_people(rs.getInt("gs_people"));
				order_rest_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				order_rest_tableVO.setOrder_deposit(rs.getInt("order_deposit"));
				order_rest_tableVO.setOrder_qrcode(rs.getBytes("order_qrcode"));
				order_rest_tableVO.setRs_table_status(rs.getInt("rs_table_status"));
				order_rest_tableVO.setRs_name(rs.getString("rs_name"));
				list.add(order_rest_tableVO);
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
		return list;
	}

	public static void main(String[] args) {

		Order_TableJDBCDAO dao = new Order_TableJDBCDAO();

//			//新增
//			Order_TableVO order_tableVO1 = new Order_TableVO();
//			order_tableVO1.setOrder_id("OD00105");
//			order_tableVO1.setRs_id("RS10005");
//			order_tableVO1.setGs_email("Echo@gmail.com");
//			order_tableVO1.setOrder_status(1);
//			order_tableVO1.setOrder_time(java.sql.Timestamp.valueOf("2020-12-01"));
//			order_tableVO1.setOrder_cancel_time(java.sql.Timestamp.valueOf("2020-12-02"));
//			order_tableVO1.setGs_order_remark("不要角落謝謝");
//			order_tableVO1.setGs_people(4);
//			order_tableVO1.setGs_select_time("1");
//			order_tableVO1.setOrder_deposit(40);
//			order_tableVO1.setOrder_qrcode(null);
//			order_tableVO1.setRs_table_status(1);
//			dao.insert(order_tableVO1);

		// 修改
//			Order_TableVO order_tableVO2 = new Order_TableVO();
//			order_tableVO2.setOrder_id("OD00105");
//			order_tableVO2.setRs_id("RS10005");
//			order_tableVO2.setGs_email("Echo@gmail.com");
//			order_tableVO2.setOrder_status(2);
//			order_tableVO2.setOrder_time(java.sql.Timestamp.valueOf("2010-11-11"));
////			System.out.println(order_tableVO2.getOrder_time());
//			order_tableVO2.setOrder_cancel_time(java.sql.Timestamp.valueOf("2010-01-11"));
//			order_tableVO2.setGs_order_remark("謝謝");
//			order_tableVO2.setGs_people(4);
//			order_tableVO2.setGs_select_time("2");
//			order_tableVO2.setOrder_deposit(30);
//			order_tableVO2.setOrder_qrcode(null);
//			order_tableVO2.setRs_table_status(3);
//			dao.update(order_tableVO2);
//	
//
//			
//			//刪除
//			dao.delete("OD00105");
//
////			//查詢
//			Order_TableVO order_tableVO3 = dao.findByPrimaryKey("OD00101");
//			System.out.print(order_tableVO3.getOrder_id() + ",");
//			System.out.print(order_tableVO3.getRs_id() + ",");
//			System.out.print(order_tableVO3.getGs_email() + ",");
//			System.out.print(order_tableVO3.getOrder_status() + ",");
//			System.out.print(order_tableVO3.getOrder_time() + ",");
//			System.out.print(order_tableVO3.getOrder_cancel_time() + ",");
//			System.out.print(order_tableVO3.getGs_order_remark() + ",");
//			System.out.print(order_tableVO3.getGs_people() + ",");
//			System.out.print(order_tableVO3.getGs_select_time() + ",");
//			System.out.print(order_tableVO3.getOrder_deposit() + ",");
//			System.out.print(order_tableVO3.getOrder_qrcode() + ",");
//			System.out.print(order_tableVO3.getRs_table_status());			
//			System.out.println();
//		
////
////			//查詢
//			List<Order_TableVO> list = dao.getAll();
//			for (Order_TableVO Order_Table1: list) {
//				System.out.print(Order_Table1.getOrder_id() + ",");
//				System.out.print(Order_Table1.getRs_id() + ",");
//				System.out.print(Order_Table1.getGs_email() + ",");
//				System.out.print(Order_Table1.getOrder_status() + ",");
//				System.out.print(Order_Table1.getOrder_time() + ",");
//				System.out.print(Order_Table1.getOrder_cancel_time() + ",");
//				System.out.print(Order_Table1.getGs_order_remark() + ",");
//				System.out.print(Order_Table1.getGs_people() + ",");
//				System.out.print(Order_Table1.getGs_select_time() + ",");
//				System.out.print(Order_Table1.getOrder_deposit()+ ",");
//				System.out.print(Order_Table1.getOrder_qrcode()+ ",");
//				System.out.print(Order_Table1.getRs_table_status());
//				System.out.println();
//			}
	}
}
