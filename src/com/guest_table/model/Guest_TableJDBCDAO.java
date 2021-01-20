package com.guest_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Guest_TableJDBCDAO implements Guest_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO guest_table (GS_EMAIL ,GS_PWD,GS_NAME,GS_BIRTH,GS_SEX,"
			+ "GS_PHONE,GS_ADDRESS,GS_CREDIT,GS_REG_TIME,GS_BIG_PIC,"
			+ "AUTHORITY) "
			
			+ "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
		private static final String GET_ALL_STMT = 
			"SELECT GS_EMAIL,GS_PWD,GS_NAME,to_char(GS_BIRTH,'yyyy-mm-dd') GS_BIRTH,GS_SEX,GS_PHONE,GS_ADDRESS,GS_CREDIT,to_char(GS_REG_TIME,'yyyy-mm-dd') GS_REG_TIME,GS_BIG_PIC, AUTHORITY FROM guest_table ";
		private static final String GET_ONE_STMT = 
				"SELECT GS_EMAIL,GS_PWD,GS_NAME,to_char(GS_BIRTH,'yyyy-mm-dd') GS_BIRTH,GS_SEX,GS_PHONE,GS_ADDRESS,GS_CREDIT,to_char(GS_REG_TIME,'yyyy-mm-dd') GS_REG_TIME,GS_BIG_PIC, AUTHORITY FROM guest_table where GS_EMAIL = ?";
		private static final String DELETE = 
			"DELETE FROM guest_table where GS_EMAIL = ?";
		private static final String UPDATE = 
			"UPDATE guest_table set  GS_PWD=?, GS_NAME=?, GS_BIRTH=?, GS_SEX=?, GS_PHONE=? ,GS_ADDRESS=?,GS_CREDIT=?,GS_REG_TIME=?,AUTHORITY=DEFAULT   where GS_EMAIL = ?";
		private static final String LOGIN =
				"SELECT GS_EMAIL,GS_PWD FROM guest_table where GS_EMAIL = ?";
		private static final String TOTAL_STAR =
				"SELECT AVG(ORDER_STAR) FROM FEEDBACK_TABLE";
		private static final String UPDATE_CREDIT = 
				"UPDATE guest_table set  GS_CREDIT=? where GS_EMAIL = ?";
		private static final String UPDATE_PWD = 
				"UPDATE guest_table set  GS_PWD=? where GS_EMAIL = ?";
			
		@Override
		public Integer totalUsePoint(String sql2, String gs_email) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Integer sum=null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, gs_email);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					sum=rs.getInt(1);
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
			return sum;
		}
		

		@Override
		public Integer totalNoUsePoint(String sql1, String gs_email) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Integer sum=null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, gs_email);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					sum=rs.getInt(1);
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
			return sum;
		}

		public void insert(Guest_TableVO guest_TableVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				

				pstmt.setString(1, guest_TableVO.getGs_email());
				pstmt.setString(2, guest_TableVO.getGs_pwd());
				pstmt.setString(3, guest_TableVO.getGs_name());
				pstmt.setDate(4, guest_TableVO.getGs_birth());
				pstmt.setInt(5, guest_TableVO.getGs_sex());
				pstmt.setString(6, guest_TableVO.getGs_phone());
				pstmt.setString(7, guest_TableVO.getGs_address());
				pstmt.setString(8, guest_TableVO.getGs_credit());
				pstmt.setDate(9, guest_TableVO.getGs_reg_time());
				pstmt.setBytes(10, guest_TableVO.getGs_big_pic());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		
		   public void update(Guest_TableVO guest_TableVO) {
			   Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(UPDATE);

					
					pstmt.setString(1, guest_TableVO.getGs_pwd());
					pstmt.setString(2, guest_TableVO.getGs_name());
					pstmt.setDate(3, guest_TableVO.getGs_birth());
					pstmt.setInt(4, guest_TableVO.getGs_sex());
					pstmt.setString(5, guest_TableVO.getGs_phone());
					pstmt.setString(6, guest_TableVO.getGs_address());
					pstmt.setString(7, guest_TableVO.getGs_credit());
					pstmt.setDate(8, guest_TableVO.getGs_reg_time());
//					pstmt.setBytes(9, guest_TableVO.getGs_big_pic());
					
					pstmt.setString(9, guest_TableVO.getGs_email());
					
					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
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
		   
		   public void updateCredit(Guest_TableVO guest_TableVO) {
			   Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(UPDATE_CREDIT);

					
				
					pstmt.setString(1, guest_TableVO.getGs_credit());
					
					pstmt.setString(2, guest_TableVO.getGs_email());
					
					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
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
		   
		   public void updatePwd(Guest_TableVO guest_TableVO) {
			   Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(UPDATE_PWD);

					
				
					pstmt.setString(1, guest_TableVO.getGs_pwd());
					
					pstmt.setString(2, guest_TableVO.getGs_email());
					
					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
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
		   
		   
		   
		   
		   public void delete(String gs_email) {
			   Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(DELETE);

					pstmt.setString(1, gs_email);

					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
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
		   
		   public Guest_TableVO findByPrimaryKey(String gs_email) {
			   Guest_TableVO guest_TableVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setString(1, gs_email);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo 也稱為 Domain objects
						guest_TableVO = new Guest_TableVO();
						guest_TableVO.setGs_email(rs.getString("gs_email"));
						guest_TableVO.setGs_pwd(rs.getString("gs_pwd"));
						guest_TableVO.setGs_name(rs.getString("gs_name"));
						guest_TableVO.setGs_birth(rs.getDate("gs_birth"));
						guest_TableVO.setGs_sex(rs.getInt("gs_sex"));
						guest_TableVO.setGs_phone(rs.getString("gs_phone"));
						guest_TableVO.setGs_address(rs.getString("gs_address"));
						guest_TableVO.setGs_credit(rs.getString("gs_credit"));
						guest_TableVO.setGs_reg_time(rs.getDate("gs_reg_time"));
						guest_TableVO.setGs_big_pic(rs.getBytes("gs_big_pic"));
						guest_TableVO.setAuthority(rs.getInt("authority"));
					}

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVO 也稱為 Domain objects
						
						guest_TableVO = new Guest_TableVO();
						guest_TableVO.setGs_email(rs.getString("gs_email"));
						guest_TableVO.setGs_pwd(rs.getString("gs_pwd"));
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

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
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
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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
		   
		   
		   

		   public Integer avg_star() {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Integer avg = null;
				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(TOTAL_STAR);
					rs = pstmt.executeQuery();
					while (rs.next()) {
					 avg=rs.getInt(1); 
					
					}
					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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
				return avg; 
		   } 
		
		@Override
		public void updateGsBigPic(String sql, byte[] gs_big_pic) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(sql);

				pstmt.setBytes(1, gs_big_pic);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				se.printStackTrace();
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
		

			public static void main(String[] args) {

				Guest_TableJDBCDAO dao = new Guest_TableJDBCDAO();
				
				dao.avg_star();

				// 新增
//				Guest_TableVO guest_TableVO1 = new Guest_TableVO();
//				guest_TableVO1.setGs_email("F@gmail.com");;
//				guest_TableVO1.setGs_pwd("66666");
//				guest_TableVO1.setGs_name("F");
//				guest_TableVO1.setGs_birth(java.sql.Date.valueOf("2020-12-02"));
//				guest_TableVO1.setGs_sex(1);
//				guest_TableVO1.setGs_phone("0936123666");
//				guest_TableVO1.setGs_address("台北市中山區");
//				guest_TableVO1.setGs_credit("6666 6666 6666 6666");
//				guest_TableVO1.setGs_reg_time(java.sql.Date.valueOf("2020-12-02"));
//				guest_TableVO1.setGs_big_pic(null);
//				guest_TableVO1.setAuthority(3);
//				dao.insert(guest_TableVO1);

////				// 修改
//				Guest_TableVO guest_TableVO2 = new Guest_TableVO();
//				guest_TableVO2.setGs_email("Alpha@gmail.com");;
//				guest_TableVO2.setGs_pwd("22222");
//				guest_TableVO2.setGs_name("Beta");
//				guest_TableVO2.setGs_birth(java.sql.Date.valueOf("1999-12-31"));
//				guest_TableVO2.setGs_sex(1);
//				guest_TableVO2.setGs_phone("0936123222");
//				guest_TableVO2.setGs_address("台北市士林區");
//				guest_TableVO2.setGs_credit("2222 2222 2222 2222");
//				guest_TableVO2.setGs_reg_time(java.sql.Date.valueOf("2020-11-28"));
//				guest_TableVO2.setGs_big_pic(null);
////				guest_TableVO2.setAuthority(3);
//				dao.update(guest_TableVO2);
////
//				// 刪除
//				dao.delete("Beta@gmail.com");
////
////				// 查詢
				Guest_TableVO guest_TableVO3 = dao.findByPrimaryKey("Alpha@gmail.com");
				System.out.print(guest_TableVO3.getGs_email() + ",");
				System.out.print(guest_TableVO3.getGs_pwd() + ",");
				System.out.print(guest_TableVO3.getGs_name() + ",");
				System.out.print(guest_TableVO3.getGs_birth() + ",");
				System.out.print(guest_TableVO3.getGs_sex() + ",");
				System.out.print(guest_TableVO3.getGs_phone() + ",");
				System.out.print(guest_TableVO3.getGs_address() + ",");
				System.out.print(guest_TableVO3.getGs_credit() + ",");
				System.out.print(guest_TableVO3.getGs_reg_time() + ",");
				System.out.print(guest_TableVO3.getGs_big_pic() + ",");
				System.out.print(guest_TableVO3.getAuthority() + ",");
				
				System.out.println("---------------------");
//
////				// 查詢
//				List<Guest_TableVO> list = dao.getAll();
//				for (Guest_TableVO aGuest_TableVO : list) {
//					System.out.print(aGuest_TableVO.getGs_email() + ",");
//					System.out.print(aGuest_TableVO.getGs_pwd() + ",");
//					System.out.print(aGuest_TableVO.getGs_name() + ",");
//					System.out.print(aGuest_TableVO.getGs_birth() + ",");
//					System.out.print(aGuest_TableVO.getGs_sex() + ",");
//					System.out.print(aGuest_TableVO.getGs_phone() + ",");
//					System.out.print(aGuest_TableVO.getGs_address()+ ",");
//					System.out.print(aGuest_TableVO.getGs_credit()+ ",");
//					System.out.print(aGuest_TableVO.getGs_reg_time()+ ",");
//					System.out.print(aGuest_TableVO.getGs_big_pic()+ ",");
//					System.out.print(aGuest_TableVO.getAuthority()+ ",");
//					System.out.println();
//				}
			}
		   
}
