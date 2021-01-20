package com.guest_table.model;

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



public class Guest_TableDAO implements Guest_TableDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = 
			"INSERT INTO guest_table (GS_EMAIL ,GS_PWD,GS_NAME,GS_BIRTH,GS_SEX,"
			+ "GS_PHONE,GS_ADDRESS,GS_CREDIT,GS_REG_TIME,GS_BIG_PIC,"
			+ "AUTHORITY) "
			
			+ "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT GS_EMAIL,GS_PWD,GS_NAME,to_char(GS_BIRTH_TIME,'yyyy-mm-dd') GS_BIRTH,GS_SEX,GS_PHONE,GS_ADDRESS,GS_CREDIT,to_char(GS_REG_TIME,'yyyy-mm-dd') GS_REG_TIME,GS_BIG_PIC, AUTHORITY FROM guest_table ";
		private static final String GET_ONE_STMT = 
				"SELECT GS_PWD,GS_NAME,to_char(GS_BIRTH_TIME,'yyyy-mm-dd') GS_BIRTH,GS_SEX,GS_PHONE,GS_ADDRESS,GS_CREDIT,to_char(GS_REG_TIME,'yyyy-mm-dd') GS_REG_TIME,GS_BIG_PIC, AUTHORITY FROM guest_table where GS_EMAIL = ?";
		private static final String DELETE = 
			"DELETE FROM guest_table where GS_EMAIL = ?";
		private static final String UPDATE = 
			"UPDATE guest_table set  GS_PWD=?, GS_NAME=?, GS_BIRTH=?, GS_SEX=?, GS_PHONE=? ,GS_ADDRESS=?,GS_CREDIT=?,GS_REG_TIME=?,GS_BIG_PIC=?,AUTHORITY=?   where GS_EMAIL = ?";
		
		private static final String LOGIN =
				"SELECT GS_EMAIL,GS_PWD FROM guest_table where GS_EMAIL = ?";
		
		public void insert(Guest_TableVO guest_TableVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
		
		pstmt.setString(1, guest_TableVO.getGs_email());
		pstmt.setString(2, guest_TableVO.getGs_name());
		pstmt.setDate(3, guest_TableVO.getGs_birth());
		pstmt.setInt(4, guest_TableVO.getGs_sex());
		pstmt.setString(5, guest_TableVO.getGs_phone());
		pstmt.setString(6, guest_TableVO.getGs_address());
		pstmt.setString(7, guest_TableVO.getGs_credit());
		pstmt.setDate(8, guest_TableVO.getGs_reg_time());
		pstmt.setBytes(9, guest_TableVO.getGs_big_pic());
		pstmt.setInt(10, guest_TableVO.getAuthority());

		pstmt.executeUpdate();
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
		public void update(Guest_TableVO guest_TableVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
		
		
		
		pstmt.setString(1, guest_TableVO.getGs_name());
		pstmt.setDate(2, guest_TableVO.getGs_birth());
		pstmt.setInt(3, guest_TableVO.getGs_sex());
		pstmt.setString(4, guest_TableVO.getGs_phone());
		pstmt.setString(5, guest_TableVO.getGs_address());
		pstmt.setString(6, guest_TableVO.getGs_credit());
		pstmt.setDate(7, guest_TableVO.getGs_reg_time());
		pstmt.setBytes(8, guest_TableVO.getGs_big_pic());
		pstmt.setInt(9, guest_TableVO.getAuthority());
		pstmt.setString(10, guest_TableVO.getGs_email());
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
		public void delete(String gs_email) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
		
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, gs_email);

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

		public Guest_TableVO findByPrimaryKey(String gs_email) {
			   Guest_TableVO guest_TableVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setString(1, gs_email);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo 也稱為 Domain objects
						guest_TableVO = new Guest_TableVO();
						guest_TableVO.setGs_email(rs.getString("gs_mail"));
						guest_TableVO.setGs_name(rs.getString("gs_name"));
						guest_TableVO.setGs_birth(rs.getDate("gs_birth"));
						guest_TableVO.setGs_sex(rs.getInt("gs_sex"));
						guest_TableVO.setGs_phone(rs.getString("gs_phone"));
						guest_TableVO.setGs_address(rs.getString("gs_address"));
						guest_TableVO.setGs_credit(rs.getString("Gs_credit"));
						guest_TableVO.setGs_reg_time(rs.getDate("Gs_reg_time"));
						guest_TableVO.setGs_big_pic(rs.getBytes("Gs_big_pic"));
						guest_TableVO.setAuthority(rs.getInt("Authority"));
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
				return guest_TableVO;
			}
				
		  public List<Guest_TableVO> getAll(){
			   List<Guest_TableVO> list = new ArrayList<Guest_TableVO>();
			   Guest_TableVO guest_TableVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						// empVO 也稱為 Domain objects
						guest_TableVO = new Guest_TableVO();
						guest_TableVO.setGs_email(rs.getString("gs_mail"));
						guest_TableVO.setGs_name(rs.getString("gs_name"));
						guest_TableVO.setGs_birth(rs.getDate("gs_birth"));
						guest_TableVO.setGs_sex(rs.getInt("gs_sex"));
						guest_TableVO.setGs_phone(rs.getString("gs_phone"));
						guest_TableVO.setGs_address(rs.getString("gs_address"));
						guest_TableVO.setGs_credit(rs.getString("Gs_credit"));
						guest_TableVO.setGs_reg_time(rs.getDate("Gs_reg_time"));
						guest_TableVO.setGs_big_pic(rs.getBytes("Gs_big_pic"));
						guest_TableVO.setAuthority(rs.getInt("Authority"));
						list.add(guest_TableVO); // Store the row in the list
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
		  
		  public Guest_TableVO login(String gs_email) {
			   Guest_TableVO guest_TableVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(LOGIN);

					pstmt.setString(1, gs_email);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo 也稱為 Domain objects
						guest_TableVO = new Guest_TableVO();
						guest_TableVO.setGs_email(rs.getString("gs_email"));
						guest_TableVO.setGs_pwd(rs.getString("gs_pwd"));
						
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
				return guest_TableVO;
		   }
		@Override
		public Integer avg_star() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void updateGsBigPic(String sql, byte[] gs_big_pic) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void updateCredit(Guest_TableVO guest_TableVO) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void updatePwd(Guest_TableVO guest_TableVO) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Integer totalNoUsePoint(String sql1, String gs_email) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Integer totalUsePoint(String sql2, String gs_email) {
			// TODO Auto-generated method stub
			return null;
		}
		   
		
}
