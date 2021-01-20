package com.feedback_table.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.guest_table.model.Guest_TableVO;

public abstract class Feedback_TableDAO implements Feedback_TableDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Feedback_Table (ORDER_ID ,RS_ID, COMMON, COMMON_TIME, PUSH_YN, ORDER_STAR, COMMON_CANCEL_TIME, COMMON_STATUS) "
			+ "VALUES (?,?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_ID,RS_ID, COMMON, to_char(COMMON_TIME,'yyyy-mm-dd'), PUSH_YN, ORDER_STAR,to_char(COMMON_CANCEL_TIME,'yyyy-mm-dd') COMMON_STATUS  FROM guest_table ";
	private static final String GET_ONE_STMT = "SELECT RS_ID, COMMON, to_char(COMMON_TIME,'yyyy-mm-dd'), PUSH_YN, ORDER_STAR,to_char(COMMON_CANCEL_TIME,'yyyy-mm-dd') COMMON_STATUS FROM guest_table where ORDER_ID = ?";
	private static final String DELETE = "DELETE FROM Feedback_Table where ORDER_ID = ?";
	private static final String UPDATE = "UPDATE Feedback_Table set  RS_ID=?, COMMON=?, COMMON_TIME=?, PUSH_YN=?, ORDER_STAR=? ,COMMON_CANCEL_TIME=?,COMMON_STATUS=?  where ORDER_ID = ?";

	public void insert(Feedback_TableVO feedback_TableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			pstmt.setString(1, feedback_TableVO.getOrder_id());
			pstmt.setString(2, feedback_TableVO.getRs_id());
			pstmt.setString(3, feedback_TableVO.getCommon());
			pstmt.setDate(4, feedback_TableVO.getCommon_time());
			pstmt.setInt(5, feedback_TableVO.getPush_yn());
			pstmt.setInt(6, feedback_TableVO.getOrder_star());
			pstmt.setDate(7, feedback_TableVO.getCommon_cancel_time());
			pstmt.setInt(8, feedback_TableVO.getCommon_status());

			pstmt.executeUpdate();
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

	public void update(Feedback_TableVO feedback_TableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			pstmt.setString(1, feedback_TableVO.getOrder_id());
			pstmt.setString(2, feedback_TableVO.getRs_id());
			pstmt.setString(3, feedback_TableVO.getCommon());
			pstmt.setDate(4, feedback_TableVO.getCommon_time());
			pstmt.setInt(5, feedback_TableVO.getPush_yn());
			pstmt.setInt(6, feedback_TableVO.getOrder_star());
			pstmt.setDate(7, feedback_TableVO.getCommon_cancel_time());
			pstmt.setInt(8, feedback_TableVO.getCommon_status());

			pstmt.executeUpdate();
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

	public void delete(String order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_id);

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

	public Feedback_TableVO findByPrimaryKey(String order_id) {
		Feedback_TableVO feedback_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				feedback_TableVO = new Feedback_TableVO();
				feedback_TableVO.setOrder_id(rs.getString("order_id"));
				feedback_TableVO.setRs_id(rs.getString("rs_id"));
				feedback_TableVO.setCommon(rs.getString("common"));
				feedback_TableVO.setCommon_time(rs.getDate("common_time"));
				feedback_TableVO.setPush_yn(rs.getInt("push_yn"));
				feedback_TableVO.setOrder_star(rs.getInt("order_star"));
				feedback_TableVO.setCommon_status(rs.getInt("common_status"));
			}
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
		return feedback_TableVO;
	}

	public List<Feedback_TableVO> getAll() {
		List<Feedback_TableVO> list = new ArrayList<Feedback_TableVO>();
		Feedback_TableVO feedback_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				feedback_TableVO = new Feedback_TableVO();
				feedback_TableVO.setOrder_id(rs.getString("order_id"));
				feedback_TableVO.setRs_id(rs.getString("rs_id"));
				feedback_TableVO.setCommon(rs.getString("common"));
				feedback_TableVO.setCommon_time(rs.getDate("common_time"));
				feedback_TableVO.setPush_yn(rs.getInt("push_yn"));
				feedback_TableVO.setOrder_star(rs.getInt("order_star"));
				feedback_TableVO.setCommon_status(rs.getInt("common_status"));
				list.add(feedback_TableVO); // Store the row in the list
			}

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
