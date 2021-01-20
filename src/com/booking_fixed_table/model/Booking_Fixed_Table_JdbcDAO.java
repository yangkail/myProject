package com.booking_fixed_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking_ing_table.model.Booking_Ing_TableVO;
import com.booking_ing_table.model.Booking_Ing_Table_JdbcDAO;
import com.booking_ing_table.model.Booking_Ing_Table_OrderVO;
import com.order_table.model.Order_TableJDBCDAO;
import com.order_table.model.Order_TableVO;

public class Booking_Fixed_Table_JdbcDAO implements Booking_Fixed_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO BOOKING_FIXED_TABLE (RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL) values ('RSFIX' || lpad(BOOKING_FIXED_TABLE_SEQ.Nextval,5,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL FROM BOOKING_FIXED_TABLE order by RS_SIERAL";
	private static final String GET_ONE_STMT = "SELECT RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL FROM BOOKING_FIXED_TABLE where RS_SIERAL = ?";
	private static final String DELETE = "DELETE FROM BOOKING_FIXED_TABLE where RS_SIERAL = ?";
	private static final String UPDATE = "UPDATE BOOKING_FIXED_TABLE set RS_TABLE_NUMBER=?, RS_TABLE_SEAT=?, RS_SEAT_SIERAL=? where RS_SIERAL=?";
	private static final String SELECT_GET_RS_ID_ALL = "Select * From BOOKING_FIXED_TABLE Inner join REST_SEAT_COORDINATE_TABLE on  \r\n"
			+ "REST_SEAT_COORDINATE_TABLE.RS_SEAT_SIERAL  =\r\n"
			+ "BOOKING_FIXED_TABLE.RS_SEAT_SIERAL Where REST_SEAT_COORDINATE_TABLE.RS_ID = ?";
	private static final String GET_ONE_RS_SEAT_SIERAL = "SELECT RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL FROM BOOKING_FIXED_TABLE where RS_SEAT_SIERAL = ?";

	@Override
	public void insert(Booking_Fixed_TableVO booking_fixed_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, booking_fixed_tableVO.getRs_table_number());
			pstmt.setInt(2, booking_fixed_tableVO.getRs_table_seat());
			pstmt.setString(3, booking_fixed_tableVO.getRs_seat_sieral());

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
	public void update(Booking_Fixed_TableVO booking_fixed_tableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, booking_fixed_tableVO.getRs_table_number());
			pstmt.setInt(2, booking_fixed_tableVO.getRs_table_seat());
			pstmt.setString(3, booking_fixed_tableVO.getRs_seat_sieral());
//			System.out.println("update1");
			pstmt.setString(4, booking_fixed_tableVO.getRs_sieral());
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
	public void delete(String rs_sieral) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rs_sieral);

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
	public Booking_Fixed_TableVO findByPrimaryKey(String rs_sieral) {
		Booking_Fixed_TableVO booking_fixed_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rs_sieral);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// booking_fixed_tableVO 銋迂� Domain objects
				booking_fixed_tableVO = new Booking_Fixed_TableVO();
				booking_fixed_tableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_fixed_tableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_fixed_tableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_fixed_tableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
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
	public List<Booking_Fixed_TableVO> getAll() {
		List<Booking_Fixed_TableVO> list = new ArrayList<Booking_Fixed_TableVO>();
		Booking_Fixed_TableVO booking_Fixed_TableVO = null;

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
				booking_Fixed_TableVO = new Booking_Fixed_TableVO();
				booking_Fixed_TableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_Fixed_TableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_Fixed_TableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_Fixed_TableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
				list.add(booking_Fixed_TableVO); // Store the row in the list
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
	public List<Booking_Fixed_TableVO> select_get_rs_id_all(String rs_id) {
		List<Booking_Fixed_TableVO> list = new ArrayList<Booking_Fixed_TableVO>();
		Booking_Fixed_TableVO booking_Fixed_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_GET_RS_ID_ALL);
			pstmt.setString(1, rs_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Fixed_TableVO = new Booking_Fixed_TableVO();
				booking_Fixed_TableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_Fixed_TableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_Fixed_TableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_Fixed_TableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
				list.add(booking_Fixed_TableVO); // Store the row in the list
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
	public Booking_Fixed_TableVO findByRS_SEAT_SIERAL(String rs_seat_sieral) {
		Booking_Fixed_TableVO booking_fixed_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_RS_SEAT_SIERAL);

			pstmt.setString(1, rs_seat_sieral);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// booking_fixed_tableVO 銋迂� Domain objects
				booking_fixed_tableVO = new Booking_Fixed_TableVO();
				booking_fixed_tableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_fixed_tableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_fixed_tableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_fixed_tableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
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

	public static void main(String[] args) {

		Booking_Fixed_Table_JdbcDAO dao = new Booking_Fixed_Table_JdbcDAO();
		// setHiredate(java.sql.Date.valueOf("2005-01-01"));
		// // 新增
//			Booking_Fixed_TableVO booking_Fixed_VO1 = new Booking_Fixed_TableVO();
//			booking_Fixed_VO1.setRs_table_number("1");
//			booking_Fixed_VO1.setRs_table_seat(1);
//			booking_Fixed_VO1.setRs_seat_sieral("RSCOD001005");
//			
//			dao.insert(booking_Fixed_VO1);
		//// // 修改
		// Booking_Fixed_TableVO booking_Fixed_VO2 = new Booking_Fixed_TableVO();
		// booking_Fixed_VO2.setRs_table_number("11111");
		// booking_Fixed_VO2.setRs_table_seat(666);
		// booking_Fixed_VO2.setRs_seat_sieral("RSCOD001001");
		// booking_Fixed_VO2.setRs_sieral("RSFIX01001");
		// dao.update(booking_Fixed_VO2);
		//// // 查詢單筆
		// Booking_Fixed_TableVO booking_Fixed_VO3 = dao.findByPrimaryKey("RSFIX01003");
		// System.out.print(booking_Fixed_VO3.getRs_sieral() + ",");
		// System.out.print(booking_Fixed_VO3.getRs_table_number() + ",");
		// System.out.print(booking_Fixed_VO3.getRs_table_seat() + ",");
		// System.out.println(booking_Fixed_VO3.getRs_seat_sieral());
		// System.out.println("---------------------");
		//// // 查詢全部
		// List<Booking_Fixed_TableVO> list = dao.getAll();
		// for (Booking_Fixed_TableVO aBooking_Fixed : list) {
		// System.out.print(aBooking_Fixed.getRs_sieral() + ",");
		// System.out.print(aBooking_Fixed.getRs_table_number() + ",");
		// System.out.print(aBooking_Fixed.getRs_table_seat() + ",");
		// System.out.print(aBooking_Fixed.getRs_seat_sieral());
		// System.out.println();
		// }
		// 查詢餐廳編號底下的Booking_Fixed_TableVO資訊
		// List<Booking_Fixed_TableVO> list2 = dao.select_get_rs_id_all("RS10001");
		// for (Booking_Fixed_TableVO aBooking_Fixed : list2) {
		// System.out.print(aBooking_Fixed.getRs_sieral() + ",");
		// System.out.print(aBooking_Fixed.getRs_table_number() + ",");
		// System.out.print(aBooking_Fixed.getRs_table_seat() + ",");
		// System.out.print(aBooking_Fixed.getRs_seat_sieral());
		// System.out.println();
		// }
		// 刪除一筆資料
		// dao.delete("RSFIX01203");
		// 使用餐廳編號來查詢可以訂位的人數
		// List<Booking_Fixed_TableVO> list3 = dao.get_all_table_seat("RS10001", 2, 0);
		// for (Booking_Fixed_TableVO aBooking_Fixed : list3) {
		// System.out.print(aBooking_Fixed.getRs_sieral() + ",");
		// System.out.print(aBooking_Fixed.getRs_table_number() + ",");
		// System.out.print(aBooking_Fixed.getRs_table_seat() + ",");
		// System.out.print(aBooking_Fixed.getRs_seat_sieral());
		// System.out.println();
		// }
		//
//			Booking_Fixed_TableVO booking_Fixed_VO3 = dao.findByRS_SEAT_SIERAL("RSCOD001005");
//			System.out.print(booking_Fixed_VO3.getRs_sieral() + ",");
//			System.out.print(booking_Fixed_VO3.getRs_table_number() + ",");
//			System.out.print(booking_Fixed_VO3.getRs_table_seat() + ",");
//			System.out.println(booking_Fixed_VO3.getRs_seat_sieral());
//			System.out.println("---------------------");		
//			

		// 新增兩筆
		Booking_Fixed_TableVO booking_Fixed_TableVO = new Booking_Fixed_TableVO();
		booking_Fixed_TableVO.setRs_table_number("1");
		booking_Fixed_TableVO.setRs_table_seat(1);
		booking_Fixed_TableVO.setRs_seat_sieral("RSCOD001005");

		List<Booking_Ing_TableVO> booking_Ing_TableList = new ArrayList<Booking_Ing_TableVO>(); // 準備置入員工數人
		Booking_Ing_TableVO booking_Ing_TableXX = new Booking_Ing_TableVO(); // 員工POJO1
		booking_Ing_TableXX.setRs_status(1);
//			booking_Ing_TableXX.setRs_sieral("RSFIX01001");
		booking_Ing_TableXX.setGs_select_time("2");
		booking_Ing_TableXX.setGs_select_date(java.sql.Date.valueOf("2021-10-09"));

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
		booking_Ing_TableList.add(booking_Ing_TableXX);
//			order_TableList.add(order_TableYY);
//			
		dao.insertwithorder(booking_Fixed_TableVO, booking_Ing_TableList);

	}

	@Override
	public void insertwithorder(Booking_Fixed_TableVO booking_fixed_tableVO,
			List<Booking_Ing_TableVO> booking_Ing_TableList) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

		
			// 先新增部門
			String cols[] = {"RS_SIERAL"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, booking_fixed_tableVO.getRs_table_number());
			pstmt.setInt(2, booking_fixed_tableVO.getRs_table_seat());
			pstmt.setString(3, booking_fixed_tableVO.getRs_seat_sieral());
			pstmt.executeUpdate();
			// 掘取對應的自增主鍵值
			String next_rs_sieral = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_rs_sieral = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rs_sieral + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單內容
			Booking_Ing_Table_JdbcDAO dao = new Booking_Ing_Table_JdbcDAO();
			System.out.println("list.size()-A=" + booking_Ing_TableList.size());
			for (Booking_Ing_TableVO aBooking_Ing_TableVO : booking_Ing_TableList) {
				aBooking_Ing_TableVO.setRs_sieral(next_rs_sieral);
				dao.insertWithBookFixed(aBooking_Ing_TableVO, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + booking_Ing_TableList.size());
			System.out.println("新增訂單編號" + next_rs_sieral + "時,共有多少" + booking_Ing_TableList.size() + "訂單同時被新增");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

}
