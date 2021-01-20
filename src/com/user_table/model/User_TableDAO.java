//package com.user_table.model;
//
//import java.sql.*;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class User_TableDAO implements User_TableDAO_interface {
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
//	private static final String INSERT_STMT = "INSERT INTO user_table VALUES (?,?,?)";
//
//	private static final String GET_ALL_STMT = "SELECT user_account,user_password,authority from user_table order by user_account ";
//
//	private static final String GET_ONE_STMT = "SELECT user_account,user_password,authority from user_table where user_account=?";
//	// findByPrimaryKey
//	private static final String DELETE = "DELETE FROM user_table WHERE user_account=?";
//
//	private static final String UPDATE = "UPDATE user_table set user_password=?,authority=? where user_account=?";
//
//	@Override
//	public void insert(User_TableVO user_tableVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, user_tableVO.getUser_account());
//			pstmt.setString(2, user_tableVO.getUser_password());
//			pstmt.setString(3, user_tableVO.getUser_depart());
//			pstmt.setString(4, user_tableVO.getUser_job());
//			pstmt.setInt(5, user_tableVO.getAuthority());
//
//			pstmt.executeUpdate();
//
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
//	}
//
//	@Override
//	public void update(User_TableVO user_tableVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, user_tableVO.getUser_account());
//			pstmt.setString(2, user_tableVO.getUser_password());
//			pstmt.setString(3, user_tableVO.getUser_depart());
//			pstmt.setString(4, user_tableVO.getUser_job());
//			pstmt.setInt(5, user_tableVO.getAuthority());
//			pstmt.setString(6, user_tableVO.getUser_id());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//
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
//	}
//
//	@Override
//	public void delete(String user_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, user_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//
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
//	@Override
//	public User_TableVO findByPrimaryKey(String user_id) {
//		User_TableVO user_tableVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			pstmt.setString(1, user_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				user_tableVO = new User_TableVO();
//
//				user_tableVO.setUser_id(rs.getString("user_id"));
//				user_tableVO.setUser_account(rs.getString("user_account"));
//				user_tableVO.setUser_password(rs.getString("user_password"));
//				user_tableVO.setUser_depart(rs.getString("user_depart"));
//				user_tableVO.setUser_job(rs.getString("user_job"));
//				user_tableVO.setAuthority(rs.getInt("authority"));
//
//			}
//
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
//		return user_tableVO;
//	}
//
//	@Override
//	public List<User_TableVO> getAll() {
//		List<User_TableVO> list = new ArrayList<User_TableVO>();
//		User_TableVO user_tableVO = null;
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
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				user_tableVO = new User_TableVO();
//				user_tableVO.setUser_id(rs.getString("user_id"));
//				user_tableVO.setUser_account(rs.getString("user_account"));
//				user_tableVO.setUser_password(rs.getString("user_password"));
//				user_tableVO.setUser_depart(rs.getString("user_depart"));
//				user_tableVO.setUser_job(rs.getString("user_job"));
//				user_tableVO.setAuthority(rs.getInt("authority"));
//
//				list.add(user_tableVO); // Store the row in the list
//			}
//
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
//}
