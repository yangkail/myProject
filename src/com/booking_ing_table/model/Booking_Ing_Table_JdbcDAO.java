package com.booking_ing_table.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking_fixed_table.model.Booking_Fixed_TableVO;

import com.order_table.model.Order_TableJDBCDAO;
import com.order_table.model.Order_TableVO;

public class Booking_Ing_Table_JdbcDAO implements Booking_Ing_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO  BOOKING_ING_TABLE (ORDER_ID,RS_STATUS,RS_SIERAL,GS_SELECT_TIME,GS_SELECT_DATE) values ('OD' || lpad(ORDER_TABLE_SEQ.Nextval,5,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL,GS_SELECT_TIME,to_char(GS_SELECT_DATE,'yyyy-mm-dd')GS_SELECT_DATE FROM BOOKING_ING_TABLE order by ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL,GS_SELECT_TIME,to_char(GS_SELECT_DATE,'yyyy-mm-dd')GS_SELECT_DATE FROM BOOKING_ING_TABLE where ORDER_ID = ?";
	private static final String UPDATE = "UPDATE BOOKING_ING_TABLE set RS_STATUS=?, RS_SIERAL=?,GS_SELECT_TIME=?,GS_SELECT_DATE=? where ORDER_ID=?";
	private static final String GET_STATUS = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL,GS_SELECT_TIME,to_char(GS_SELECT_DATE,'yyyy-mm-dd')GS_SELECT_DATE FROM BOOKING_ING_TABLE where RS_STATUS = ?";
	private static final String UPDATE_STATUS = "UPDATE BOOKING_ING_TABLE set RS_STATUS=? ,GS_SELECT_TIME=?,GS_SELECT_DATE=? where ORDER_ID=?";
//	private static final String GET_ALL_TABLE_SEAT = "Select *  From BOOKING_FIXED_TABLE Inner join REST_SEAT_COORDINATE_TABLE  on  \r\n"
//			+ "REST_SEAT_COORDINATE_TABLE.RS_SEAT_SIERAL  = BOOKING_FIXED_TABLE.RS_SEAT_SIERAL \r\n"
//			+ "join BOOKING_ING_TABLE on BOOKING_FIXED_TABLE.RS_SIERAL =  BOOKING_ING_TABLE.RS_SIERAL\r\n"
//			+ "Where REST_SEAT_COORDINATE_TABLE.RS_ID = ? AND BOOKING_FIXED_TABLE.RS_TABLE_SEAT >= ? \r\n"
//			+ "AND BOOKING_ING_TABLE.RS_STATUS != ? AND BOOKING_ING_TABLE.GS_SELECT_TIME != ?  \r\n"
//			+ "AND booking_ing_table.gs_select_date != ?";
	private static final String GET_ALL_TABLE_SEAT ="Select *  From   REST_SEAT_COORDINATE_TABLE Inner \r\n" + 
			"join \r\n" + 
			"REST_TABLE \r\n" + 
			"on \r\n" + 
			"REST_TABLE.RS_ID = REST_SEAT_COORDINATE_TABLE.RS_ID \r\n" + 
			"join\r\n" + 
			"BOOKING_FIXED_TABLE \r\n" + 
			"on\r\n" + 
			"REST_SEAT_COORDINATE_TABLE.RS_SEAT_SIERAL =  BOOKING_FIXED_TABLE.RS_SEAT_SIERAL \r\n" + 
			"join \r\n" + 
			"BOOKING_ING_TABLE \r\n" + 
			"on \r\n" + 
			"BOOKING_FIXED_TABLE.RS_SIERAL =  BOOKING_ING_TABLE.RS_SIERAL\r\n" + 
			"\r\n" + 
			"Where REST_SEAT_COORDINATE_TABLE.RS_ID = ?\r\n" + 
			"AND BOOKING_FIXED_TABLE.RS_TABLE_SEAT >= ?\r\n" + 
			"AND BOOKING_ING_TABLE.RS_STATUS != ? \r\n" + 
			"AND REST_TABLE.RS_OPEN_TIME != ?  \r\n" + 
			"AND REST_TABLE.RS_REG_DATE != ?";
	private static final String GET_ONE_BKuseOrderID = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL,GS_SELECT_TIME,to_char(GS_SELECT_DATE,'yyyy-mm-dd')GS_SELECT_DATE FROM BOOKING_ING_TABLE where RS_SIERAL = ?";
	private static final String UPDATE_ORDER_STATUS = "UPDATE BOOKING_ING_TABLE set RS_STATUS=?  where ORDER_ID=?";
	private static final String DELETE = "DELETE FROM BOOKING_ING_TABLE where ORDER_ID = ?";
	@Override
	public void insert(Booking_Ing_TableVO booking_ing_tablevo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, booking_ing_tablevo.getRs_status());
			pstmt.setString(2, booking_ing_tablevo.getRs_sieral());
			pstmt.setString(3, booking_ing_tablevo.getGs_select_time());
			pstmt.setDate(4, booking_ing_tablevo.getGs_select_date());
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
	public void update(Booking_Ing_TableVO booking_ing_tablevo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, booking_ing_tablevo.getRs_status());
			pstmt.setString(2, booking_ing_tablevo.getRs_sieral());
			pstmt.setString(3, booking_ing_tablevo.getGs_select_time());
			pstmt.setDate(4, booking_ing_tablevo.getGs_select_date());
			pstmt.setString(5, booking_ing_tablevo.getOrder_id());
//			System.out.println("update1");
//			System.out.println("update2");
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
	public Booking_Ing_TableVO findByPrimaryKey(String order_id) {
		Booking_Ing_TableVO booking_fixed_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// booking_fixed_tableVO 銋迂� Domain objects
				booking_fixed_tableVO = new Booking_Ing_TableVO();
				booking_fixed_tableVO.setRs_sieral(rs.getString("rs_sieral"));
				booking_fixed_tableVO.setOrder_id(rs.getString("order_id"));
				booking_fixed_tableVO.setRs_status(rs.getInt("rs_status"));
				booking_fixed_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				booking_fixed_tableVO.setGs_select_date(rs.getDate("gs_select_date"));

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
		return booking_fixed_tableVO;
	}

	@Override
	public List<Booking_Ing_TableVO> getAll() {

		List<Booking_Ing_TableVO> list = new ArrayList<Booking_Ing_TableVO>();
		Booking_Ing_TableVO booking_Ing_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Ing_TableVO = new Booking_Ing_TableVO();
				booking_Ing_TableVO.setOrder_id(rs.getString("order_id"));
				booking_Ing_TableVO.setRs_status(rs.getInt("rs_status"));
				booking_Ing_TableVO.setRs_sieral(rs.getString("rs_sieral"));
				booking_Ing_TableVO.setGs_select_date(rs.getDate("gs_select_date"));
				booking_Ing_TableVO.setGs_select_time(rs.getString("gs_select_time"));
				list.add(booking_Ing_TableVO); // Store the row in the list
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

	public List<Booking_Ing_TableVO> findByPrimaryKey_Status(Integer rs_status) {
		List<Booking_Ing_TableVO> list = new ArrayList<Booking_Ing_TableVO>();
		Booking_Ing_TableVO booking_Ing_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STATUS);
			pstmt.setInt(1, rs_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Ing_TableVO = new Booking_Ing_TableVO();
				booking_Ing_TableVO.setOrder_id(rs.getString("order_id"));
				booking_Ing_TableVO.setRs_status(rs.getInt("rs_status"));
				booking_Ing_TableVO.setRs_sieral(rs.getString("rs_sieral"));
				booking_Ing_TableVO.setGs_select_time(rs.getString("gs_select_time"));
				booking_Ing_TableVO.setGs_select_date(rs.getDate("gs_select_date"));
				list.add(booking_Ing_TableVO); // Store the row in the list
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

	@Override
	public void update_status(Booking_Ing_TableVO booking_ing_tablevo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, booking_ing_tablevo.getRs_status());
			pstmt.setString(2, booking_ing_tablevo.getGs_select_time());
			pstmt.setDate(3, booking_ing_tablevo.getGs_select_date());
			pstmt.setString(4, booking_ing_tablevo.getOrder_id());
//			System.out.println("update1");
//			System.out.println("update2");
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
	//使用假資料來選位
//	@Override
//	public List<Booking_Ing_Table_OrderVO> get_all_table_seat(String rs_id, Integer rs_table_seat, Integer rs_status,
//			String gs_select_time, Date gs_select_date) {
//		List<Booking_Ing_Table_OrderVO> list = new ArrayList<Booking_Ing_Table_OrderVO>();
//		Booking_Ing_Table_OrderVO booking_Ing_Table_OrderVO = null;
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//	
//		try {
//	
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_TABLE_SEAT);
//			pstmt.setString(1, rs_id);
//			pstmt.setInt(2, rs_table_seat);
//			pstmt.setInt(3, rs_status);
//			pstmt.setString(4, gs_select_time);
//			pstmt.setDate(5, gs_select_date);
//	
//			rs = pstmt.executeQuery();
//	
//			while (rs.next()) {
//				// empVO 銋迂� Domain objects
//				booking_Ing_Table_OrderVO = new Booking_Ing_Table_OrderVO();
//				booking_Ing_Table_OrderVO.setRs_id(rs.getString("RS_ID"));
//				booking_Ing_Table_OrderVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
//				booking_Ing_Table_OrderVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
//				booking_Ing_Table_OrderVO.setRs_status(rs.getInt("RS_STATUS"));
//				booking_Ing_Table_OrderVO.setGs_select_time(rs.getString("GS_SELECT_TIME"));
//				booking_Ing_Table_OrderVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
//				booking_Ing_Table_OrderVO.setGs_select_date(rs.getDate("GS_SELECT_DATE"));
//	
//				list.add(booking_Ing_Table_OrderVO); // Store the row in the list
//			}
//	
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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
//		return list;
//	}
	
	//使用餐廳資訊來選位
	@Override
	public List<Booking_Ing_Table_OrderVO> get_all_table_seat(String rs_id, Integer rs_table_seat, Integer rs_status,
			String rs_open_time, Date rs_reg_date) {
		List<Booking_Ing_Table_OrderVO> list = new ArrayList<Booking_Ing_Table_OrderVO>();
		Booking_Ing_Table_OrderVO booking_Ing_Table_OrderVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TABLE_SEAT);
			pstmt.setString(1, rs_id);
			pstmt.setInt(2, rs_table_seat);
			pstmt.setInt(3, rs_status);
			pstmt.setString(4, rs_open_time);
			pstmt.setDate(5, rs_reg_date);
	
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Ing_Table_OrderVO = new Booking_Ing_Table_OrderVO();
				booking_Ing_Table_OrderVO.setRs_id(rs.getString("RS_ID"));
				booking_Ing_Table_OrderVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_Ing_Table_OrderVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
				booking_Ing_Table_OrderVO.setRs_status(rs.getInt("RS_STATUS"));
				booking_Ing_Table_OrderVO.setRs_open_time(rs.getString("RS_OPEN_TIME"));
				booking_Ing_Table_OrderVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_Ing_Table_OrderVO.setRs_reg_date(rs.getDate("RS_REG_DATE"));
	
				list.add(booking_Ing_Table_OrderVO); // Store the row in the list
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
	@Override
	public void insertWithOrder(Booking_Ing_TableVO  booking_Ing_TableVO, List<Order_TableVO> order_TableList, Booking_Ing_TableVO updateBooking_Ing_TableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, booking_Ing_TableVO.getRs_status());
			pstmt.setString(2, booking_Ing_TableVO.getRs_sieral());
			pstmt.setString(3, booking_Ing_TableVO.getGs_select_time());
			pstmt.setDate(4, booking_Ing_TableVO.getGs_select_date());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_order_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_order_id +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//修改此選位的狀態
			update_Order_Status(updateBooking_Ing_TableVO, con);
			
			// 再同時新增訂單內容
			Order_TableJDBCDAO dao = new Order_TableJDBCDAO();
			System.out.println("list.size()-A="+order_TableList.size());
			for (Order_TableVO aOrder_TableVO : order_TableList) {
				aOrder_TableVO.setOrder_id(next_order_id) ;
				dao.insertWithBooking(aOrder_TableVO,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+order_TableList.size());
			System.out.println("新增訂單編號" + next_order_id + "時,共有多少" + order_TableList.size()
					+ "訂單同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Booking_Ing_TableVO findByOrderID(String rs_sieral) {
		Booking_Ing_TableVO booking_fixed_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BKuseOrderID);

			pstmt.setString(1, rs_sieral);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// booking_fixed_tableVO 銋迂� Domain objects
				booking_fixed_tableVO = new Booking_Ing_TableVO();
				booking_fixed_tableVO.setRs_sieral(rs.getString("rs_sieral"));
				booking_fixed_tableVO.setOrder_id(rs.getString("order_id"));
				booking_fixed_tableVO.setRs_status(rs.getInt("rs_status"));
				booking_fixed_tableVO.setGs_select_time(rs.getString("gs_select_time"));
				booking_fixed_tableVO.setGs_select_date(rs.getDate("gs_select_date"));

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
		return booking_fixed_tableVO;
	}
	@Override
	public void update_Order_Status(Booking_Ing_TableVO updateBooking_Ing_TableVO,Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(UPDATE_ORDER_STATUS);

			pstmt.setInt(1, updateBooking_Ing_TableVO.getRs_status());
			pstmt.setString(2, updateBooking_Ing_TableVO.getOrder_id());
//			System.out.println("update1");
//			System.out.println("update2");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-update");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insertWithBookFixed(Booking_Ing_TableVO booking_Ing_TableVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, booking_Ing_TableVO.getRs_status());
			pstmt.setString(2, booking_Ing_TableVO.getRs_sieral());
			pstmt.setString(3, booking_Ing_TableVO.getGs_select_time());
			pstmt.setDate(4, booking_Ing_TableVO.getGs_select_date());
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

	public static void main(String[] args) {
	
			Booking_Ing_Table_JdbcDAO dao = new Booking_Ing_Table_JdbcDAO();
	//		setHiredate(java.sql.Date.valueOf("2005-01-01"));
			// 新增
//			Booking_Ing_TableVO booking_ing_tablevo1 = new Booking_Ing_TableVO();
//			booking_ing_tablevo1.setRs_status(0);
//			booking_ing_tablevo1.setRs_sieral("RSFIX01001");
//			booking_ing_tablevo1.setGs_select_time("1");
//			booking_ing_tablevo1.setGs_select_date(java.sql.Date.valueOf("1999-10-09"));
//			dao.insert(booking_ing_tablevo1);
	////		 修改
	//		Booking_Ing_TableVO booking_ing_tablevo2 = new Booking_Ing_TableVO();
	//		booking_ing_tablevo2.setOrder_id("OD00105");
	//		booking_ing_tablevo2.setRs_status(1);
	//		booking_ing_tablevo2.setRs_sieral("RSFIX01005");
	//		booking_ing_tablevo2.setGs_select_time("5");
	//		booking_ing_tablevo2.setGs_select_date(java.sql.Date.valueOf("1999-10-09"));
	//		dao.update(booking_ing_tablevo2);
	////		
	//		//// 修改訂位狀態
	//		Booking_Ing_TableVO booking_ing_tablevo3 = new Booking_Ing_TableVO();
	//		booking_ing_tablevo3.setRs_status(0);
	//		booking_ing_tablevo3.setOrder_id("OD00101");
	//		booking_ing_tablevo3.setGs_select_time("1");
	//		booking_ing_tablevo3.setGs_select_date(java.sql.Date.valueOf("1999-10-09"));
	//		dao.update_status(booking_ing_tablevo3);
	//////
	////////		//單筆查詢
	//		Booking_Ing_TableVO booking_Ing_TableVO3 = dao.findByPrimaryKey("OD00103");
	//		System.out.print(booking_Ing_TableVO3.getOrder_id() + ",");
	//		System.out.print(booking_Ing_TableVO3.getRs_status() + ",");
	//		System.out.print(booking_Ing_TableVO3.getRs_sieral() + ",");
	//		System.out.print(booking_Ing_TableVO3.getGs_select_time() +",");
	//		System.out.println(booking_Ing_TableVO3.getGs_select_date());
	//		System.out.println("---------------------");
	//////////		//查詢全部
	//		List<Booking_Ing_TableVO> list = dao.getAll();
	//		for (Booking_Ing_TableVO aBooking_Ing : list) {
	//			System.out.print(aBooking_Ing.getOrder_id() + ",");
	//			System.out.print(aBooking_Ing.getRs_status() + ",");
	//			System.out.print(aBooking_Ing.getRs_sieral() + ",");
	//			System.out.print(aBooking_Ing.getGs_select_time()+",");
	//			System.out.print(aBooking_Ing.getGs_select_date());
	//			System.out.println();
	//		}
	//		System.out.println("---------------------");
	//////	//查詢訂位狀態
//			List<Booking_Ing_TableVO> list2 = dao.findByPrimaryKey_Status(0);
//			for (Booking_Ing_TableVO aBooking_Ing : list2) {
//				System.out.print(aBooking_Ing.getOrder_id() + ",");
//				System.out.print(aBooking_Ing.getRs_status() + ",");
//				System.out.print(aBooking_Ing.getRs_sieral() + ",");
//				System.out.print(aBooking_Ing.getGs_select_time()+ ",");
//				System.out.print(aBooking_Ing.getGs_select_date());
//				System.out.println();
//			}
			// 使用餐廳編號來查詢可以訂位的人數
			List<Booking_Ing_Table_OrderVO> list3 = dao.get_all_table_seat("RS10001", 1, 0, "2",java.sql.Date.valueOf("2020-12-30"));
			System.out.println(list3.size());
			for (Booking_Ing_Table_OrderVO aBooking_Fixed : list3) {
				System.out.print("Rs_id="+aBooking_Fixed.getRs_id()+ ",");
				System.out.print("Rs_table_number="+aBooking_Fixed.getRs_table_number()+ ",");
				System.out.print("Rs_seat_sieral="+aBooking_Fixed.getRs_seat_sieral()+ ",");
				System.out.print("Rs_status="+aBooking_Fixed.getRs_status()+",");
				System.out.print("Rs_table_seat="+aBooking_Fixed.getRs_table_seat() + ",");
				System.out.print("Rs_open_time="+aBooking_Fixed.getRs_open_time()+",");
				System.out.print("Rs_reg_date="+aBooking_Fixed.getRs_reg_date());
				System.out.println();
			}
//			
			//交易同時新增兩個Table
//			Booking_Ing_TableVO booking_Ing_TableVO5 = new Booking_Ing_TableVO();
//			booking_Ing_TableVO5.setRs_status(1);
//			booking_Ing_TableVO5.setRs_sieral("RSFIX01001");
//			booking_Ing_TableVO5.setGs_select_time("2");
//			booking_Ing_TableVO5.setGs_select_date(java.sql.Date.valueOf("2021-10-09"));
//			
//			List<Order_TableVO> order_TableList = new ArrayList<Order_TableVO>(); // 準備置入員工數人
//			Order_TableVO order_TableXX = new Order_TableVO();   // 員工POJO1
//			order_TableXX.setRs_id("RS10001");
//			order_TableXX.setGs_email("Echo@gmail.com");
//			order_TableXX.setOrder_status(1);
//			order_TableXX.setOrder_time(java.sql.Date.valueOf("2020-12-01"));
//			order_TableXX.setOrder_cancel_time(java.sql.Date.valueOf("2020-12-02"));
//			order_TableXX.setGs_order_remark("不要角落謝謝");
//			order_TableXX.setGs_people(4);
//			order_TableXX.setGs_select_time("1");
//			order_TableXX.setOrder_deposit(new Double(40));
//			order_TableXX.setOrder_qrcode(null);
//			order_TableXX.setRs_table_status(1);

//			Order_TableVO order_TableYY = new Order_TableVO();   // 員工POJO2
//			order_TableYY.setRs_id("RS10002");
//			order_TableYY.setGs_email("EchoXXXXXXXXX@gmail.com");
//			order_TableYY.setOrder_status(1);
//			order_TableYY.setOrder_time(java.sql.Date.valueOf("2020-12-01"));
//			order_TableYY.setOrder_cancel_time(java.sql.Date.valueOf("2020-12-02"));
//			order_TableYY.setGs_order_remark("~~~~~~謝謝");
//			order_TableYY.setGs_people(4);
//			order_TableYY.setGs_select_time("1");
//			order_TableYY.setOrder_deposit(new Double(40));
//			order_TableYY.setOrder_qrcode(null);
//			order_TableYY.setRs_table_status(1);
//
//			order_TableList.add(order_TableXX);
//			order_TableList.add(order_TableYY);
//			
//			dao.insertWithOrder(booking_Ing_TableVO5 , order_TableList);
			
			//單筆查詢(利用外來鍵)
//			Booking_Ing_TableVO booking_Ing_TableVO3 = dao.findByOrderID("RSFIX01005");
//			System.out.print(booking_Ing_TableVO3.getOrder_id() + ",");
//			System.out.print(booking_Ing_TableVO3.getRs_status() + ",");
//			System.out.print(booking_Ing_TableVO3.getRs_sieral() + ",");
//			System.out.print(booking_Ing_TableVO3.getGs_select_time() +",");
//			System.out.println(booking_Ing_TableVO3.getGs_select_date());
//			System.out.println("---------------------");
//			Connection con = null;
//			//修改本來被選著的位子狀態
//			Booking_Ing_TableVO booking_ing_tablevo2 = new Booking_Ing_TableVO();
//			booking_ing_tablevo2.setOrder_id("OD00105");
//			booking_ing_tablevo2.setRs_status(1);
//			dao.update_Order_Status(booking_ing_tablevo2,con);
			
			
//			//刪除
//			dao.delete("OD00105");
			
		}

}
