package com.order_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public abstract class Order_TableDAO implements Order_TableDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
		private static final String INSERT_STMT = 
			"INSERT INTO order_table VALUES (order_table_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		private static final String GET_ALL_STMT = 
			"SELECT order_id, rs_id, gs_email, order_status, to_char(order_time,'yyyy-mm-dd') order_time, to_char(order_cancel_time,'yyyy-mm-dd') order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table order by order_id";
		
		private static final String GET_ONE_STMT = 
			"SELECT order_id, rs_id, gs_email, order_status, to_char(order_time,'yyyy-mm-dd') order_time, to_char(order_cancel_time,'yyyy-mm-dd') order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table where order_id = ?";
		
		private static final String GET_ALL_COMPLETE_ORDER="SELECT * FROM ORDER_TABLE WHERE RS_ID=? AND ORDER_STATUS=1";
		
		private static final String GET_RS_ID = 
			"SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table where by rs_id = ?";
		
		private static final String GET_GS_EMAIL = 
			"SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table WHERE gs_email = ?";
		
		private static final String GET_ALL_ORDER_ID = 
			"SELECT order_id, rs_id, gs_email, order_status,  order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, order_qrcode, rs_table_status FROM order_table where order_id = ?";
		
		private static final String DELETE = 
			"DELETE FROM order_table where order_id = ?";
		
		private static final String UPDATE = 
			"UPDATE order_table set rs_id=?, gs_email=?, order_status=?,order_time=?, order_cancel_time=?, gs_order_remark=?, gs_people=?, gs_select_time=?, order_deposit=?, order_qrcode=?, rs_table_status=? where order_id = ?";
		
		private static final String UPDATE2 = 
			"UPDATE ORDER_TABLE SET ORDER_STATUS = 2 WHERE ORDER_ID = ?";
		
		@Override
		public void insert(Order_TableVO order_tableVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
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
				// Handle any SQL errors
			} catch (SQLException se) {
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
		
	// Ajax專用
	@Override
	public boolean update2(String order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean OK = false;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setString(1, order_id);

			pstmt.executeUpdate();
			OK = true;

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
		return OK;
	}
			
		@Override
		public void update(Order_TableVO order_tableVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
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
				
				// Handle any driver errors
			} catch (SQLException se) {
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
		public Order_TableVO findByPrimaryKey(String order_id) {
			Order_TableVO order_tableVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, order_id);

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
				}
				
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<Order_TableVO> getAllRs_id(String rs_id)  {
			
			List<Order_TableVO> list = new ArrayList<Order_TableVO>();	
			Order_TableVO order_tableVO = null;		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_RS_ID);

			
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
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<Order_TableVO> getAllGs_email(String gs_email)  {
			
			List<Order_TableVO> list = new ArrayList<Order_TableVO>();	
			Order_TableVO order_tableVO = null;		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_GS_EMAIL);

			
				rs = pstmt.executeQuery();
				
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
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<Order_TableVO> getAllOrder_id(String order_id)  {
			
			List<Order_TableVO> list = new ArrayList<Order_TableVO>();	
			Order_TableVO order_tableVO = null;		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_ORDER_ID);

			
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
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<Order_TableVO> getAll() {
			List<Order_TableVO> list = new ArrayList<Order_TableVO>();
			Order_TableVO order_tableVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
		try {
			
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				order_tableVO = new Order_TableVO();
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
				order_tableVO.setRs_id(rs.getString("rs_id"));
				order_tableVO.setRs_table_status(rs.getInt("rs_table_status"));			
				list.add(order_tableVO);
			}
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_COMPLETE_ORDER);

			
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
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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


