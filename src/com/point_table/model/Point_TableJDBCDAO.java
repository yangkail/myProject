package com.point_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order_table.model.Order_TableVO;

public class Point_TableJDBCDAO implements Point_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO point_table VALUES ('PT' || lpad(POINT_TABLE_SEQ.Nextval,5,'0'), ?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "SELECT point_sieral, order_id, gs_email, order_get_point, order_use_point, point_update_time FROM point_table order by point_sieral";
	
	private static final String GET_ONE_STMT = "SELECT point_sieral, order_id, gs_email, order_get_point, order_use_point, point_update_time FROM point_table where point_sieral = ?";
	
	private static final String DELETE = "DELETE FROM point_table where point_sieral = ?";
	
	private static final String UPDATE = "UPDATE point_table set order_id=?, gs_email=?, order_get_point=?, order_use_point=?, point_update_time=? where point_sieral = ?";
	
	private static final String GET_ALL_POINT = "SELECT POINT_TABLE.point_sieral, POINT_TABLE.order_id, " + 
			"POINT_TABLE.gs_email, POINT_TABLE.order_get_point, POINT_TABLE.order_use_point," + 
			"POINT_TABLE.point_update_time, " + 
			"ORDER_TABLE.order_id, ORDER_TABLE.rs_id, ORDER_TABLE.order_time "
			+ "FROM POINT_TABLE JOIN ORDER_TABLE ON(ORDER_TABLE.order_id = POINT_TABLE.order_id) where ORDER_TABLE.gs_email = ?";
	
	@Override
	public void insert(Point_TableVO point_tableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, point_tableVO.getPoint_sieral());
			pstmt.setString(1, point_tableVO.getOrder_id());
			pstmt.setString(2, point_tableVO.getGs_email());
			pstmt.setInt(3, point_tableVO.getOrder_get_point());
			pstmt.setInt(4, point_tableVO.getOrder_use_point());
			pstmt.setTimestamp(5, point_tableVO.getPoint_update_time());

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
	public void update(Point_TableVO point_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, point_tableVO.getOrder_id());
			pstmt.setString(2, point_tableVO.getGs_email());
			pstmt.setInt(3, point_tableVO.getOrder_get_point());
			pstmt.setInt(4, point_tableVO.getOrder_use_point());
			pstmt.setTimestamp(5, point_tableVO.getPoint_update_time());
			pstmt.setString(6, point_tableVO.getPoint_sieral());

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
	public void delete(String point_sieral) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, point_sieral);

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
	public Point_TableVO findByPrimaryKey(String point_sieral) {
		Point_TableVO point_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, point_sieral);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				point_tableVO = new Point_TableVO();
				point_tableVO.setPoint_sieral(rs.getString("point_sieral"));
				point_tableVO.setOrder_id(rs.getString("order_id"));
				point_tableVO.setGs_email(rs.getString("gs_email"));
				point_tableVO.setOrder_get_point(rs.getInt("order_get_point"));
				point_tableVO.setOrder_use_point(rs.getInt("order_use_point"));
				point_tableVO.setPoint_update_time(rs.getTimestamp("point_update_time"));
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
		return point_tableVO;
	}

	@Override
	public List<Point_TableVO> getAll() {
		List<Point_TableVO> list = new ArrayList<Point_TableVO>();
		Point_TableVO point_tableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				point_tableVO = new Point_TableVO();
				point_tableVO.setPoint_sieral(rs.getString("point_sieral"));
				point_tableVO.setOrder_id(rs.getString("order_id"));
				point_tableVO.setGs_email(rs.getString("gs_email"));
				point_tableVO.setOrder_get_point(rs.getInt("order_get_point"));
				point_tableVO.setOrder_use_point(rs.getInt("order_use_point"));
				point_tableVO.setPoint_update_time(rs.getTimestamp("point_update_time"));
				list.add(point_tableVO);
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


	
	//前台查詢會員積分用
public List<Point_Order_TableVO> getAllPoint(String gs_email)  {
		
		List<Point_Order_TableVO> list = new ArrayList<Point_Order_TableVO>();
		
		Point_Order_TableVO point_order_tableVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_POINT);
			pstmt.setString(1, gs_email);
			rs = pstmt.executeQuery();

//			System.out.println("連線成功");
			while (rs.next()) {
				point_order_tableVO = new Point_Order_TableVO();
				point_order_tableVO.setPoint_sieral(rs.getString("point_sieral"));
				point_order_tableVO.setOrder_id(rs.getString("order_id"));
				point_order_tableVO.setGs_email(rs.getString("gs_email"));
				point_order_tableVO.setOrder_get_point(rs.getInt("order_get_point"));
				point_order_tableVO.setOrder_use_point(rs.getInt("order_use_point"));
				point_order_tableVO.setPoint_update_time(rs.getTimestamp("point_update_time"));		
				point_order_tableVO.setRs_id(rs.getString("rs_id"));
				point_order_tableVO.setOrder_time(rs.getTimestamp("order_time"));		
				list.add(point_order_tableVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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

		Point_TableJDBCDAO dao = new Point_TableJDBCDAO();

		// 新增
//		Point_TableVO point_tableVO1 = new Point_TableVO();
//		point_tableVO1.setPoint_sieral("PT00203");
//		point_tableVO1.setOrder_id("OD00104");
//		point_tableVO1.setGs_email("Delta@gmail.com");
//		point_tableVO1.setOrder_get_point(20);
//		point_tableVO1.setOrder_use_point(10);
//		point_tableVO1.setPoint_update_time(java.sql.Timestamp.valueOf("1999-12-04 12:12:12"));
//		dao.insert(point_tableVO1);

		// 修改
//		Point_TableVO point_tableVO2 = new Point_TableVO();
//		point_tableVO2.setPoint_sieral("PT00207");
//		point_tableVO2.setOrder_id("OD00104");
//		point_tableVO2.setGs_email("Alpha@gmail.com");
//		point_tableVO2.setOrder_get_point(10);
//		point_tableVO2.setOrder_use_point(10);
//		point_tableVO2.setPoint_update_time(java.sql.Timestamp.valueOf("1995-01-11"));
//		dao.update(point_tableVO2);
//		System.out.println("===========");

//		 刪除
// 			dao.delete("PT00200");

		// 查詢
//		Point_TableVO point_tableVO3 = dao.findByPrimaryKey("PT00200");
//		System.out.print(point_tableVO3.getPoint_sieral() + ",");
//		System.out.print(point_tableVO3.getOrder_id() + ",");
//		System.out.print(point_tableVO3.getGs_email() + ",");
//		System.out.print(point_tableVO3.getOrder_get_point() + ",");
//		System.out.print(point_tableVO3.getOrder_use_point() + ",");
//		System.out.print(point_tableVO3.getPoint_update_time() + ",");
//		System.out.println();

		// 查詢
//		List<Point_TableVO> list = dao.getAll();
//		for (Point_TableVO Point_TableVO4 : list) {
//			System.out.print(Point_TableVO4.getPoint_sieral() + ",");
//			System.out.print(Point_TableVO4.getOrder_id() + ",");
//			System.out.print(Point_TableVO4.getGs_email() + ",");
//			System.out.print(Point_TableVO4.getOrder_get_point() + ",");
//			System.out.print(Point_TableVO4.getOrder_use_point() + ",");
//			System.out.print(Point_TableVO4.getPoint_update_time() + ",");
//			System.out.println();
//		}
	}
}