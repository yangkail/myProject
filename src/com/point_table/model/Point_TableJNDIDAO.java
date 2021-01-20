package com.point_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_table.model.Order_TableVO;

public class Point_TableJNDIDAO implements Point_TableDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO point_table VALUES ('PT' || lpad(POINT_TABLE_SEQ.Nextval,5,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT point_sieral, order_id, gs_email, order_get_point, order_use_point, point_update_time FROM point_table order by point_sieral";
	private static final String GET_ONE_STMT = "SELECT point_sieral, order_id, gs_email, order_get_point, order_use_point, point_update_time FROM point_table where point_sieral = ?";
	private static final String DELETE = "DELETE FROM point_table where point_sieral = ?";
	private static final String UPDATE = "UPDATE point_table set order_id=?, gs_email=?, order_get_point=?, order_use_point=?, point_update_time=? where point_sieral = ?";
	private static final String GET_ALL_POINT = "SELECT POINT_TABLE.point_sieral, POINT_TABLE.gs_email, " + 
			"POINT_TABLE.order_id, POINT_TABLE.order_get_point, POINT_TABLE.order_use_point," + 
			"POINT_TABLE.point_update_time, " + 
			"ORDER_TABLE.order_id, ORDER_TABLE.rs_id, ORDER_TABLE.order_time "
			+ "FROM POINT_TABLE JOIN ORDER_TABLE ON(ORDER_TABLE.order_id = POINT_TABLE.order_id) where point_sieral = ?";
	
	@Override
	public void insert(Point_TableVO point_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//					pstmt.setString(1, point_tableVO.getPoint_sieral());
			pstmt.setString(1, point_tableVO.getOrder_id());
			pstmt.setString(2, point_tableVO.getGs_email());
			pstmt.setInt(3, point_tableVO.getOrder_get_point());
			pstmt.setInt(4, point_tableVO.getOrder_use_point());
			pstmt.setTimestamp(5, point_tableVO.getPoint_update_time());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, point_tableVO.getOrder_id());
			pstmt.setString(2, point_tableVO.getGs_email());
			pstmt.setInt(3, point_tableVO.getOrder_get_point());
			pstmt.setInt(4, point_tableVO.getOrder_get_point());
			pstmt.setTimestamp(5, point_tableVO.getPoint_update_time());
			pstmt.setString(6, point_tableVO.getPoint_sieral());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, point_sieral);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
		public List<Point_Order_TableVO> getAllPoint(String point_sieral)  {
		
		List<Point_Order_TableVO> list = new ArrayList<Point_Order_TableVO>();
		
		Point_Order_TableVO point_order_tableVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_POINT);
			pstmt.setString(1, point_sieral);
			rs = pstmt.executeQuery();

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
}
