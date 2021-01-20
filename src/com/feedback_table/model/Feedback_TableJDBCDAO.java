package com.feedback_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.guest_table.model.Guest_TableJDBCDAO;

public class Feedback_TableJDBCDAO implements Feedback_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Feedback_Table (ORDER_ID ,RS_ID, COMMON, COMMON_TIME, PUSH_YN, ORDER_STAR, COMMON_CANCEL_TIME, COMMON_STATUS) "
			+ "VALUES (?,?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT ORDER_ID,RS_ID, COMMON, to_char(COMMON_TIME,'yyyy-mm-dd')COMMON_TIME, PUSH_YN, ORDER_STAR,to_char(COMMON_CANCEL_TIME,'yyyy-mm-dd')COMMON_CANCEL_TIME, COMMON_STATUS  FROM FEEDBACK_TABLE ";
		private static final String GET_ONE_STMT = 
				"SELECT ORDER_ID, RS_ID, COMMON, to_char(COMMON_TIME,'yyyy-mm-dd')COMMON_TIME, PUSH_YN, ORDER_STAR,to_char(COMMON_CANCEL_TIME,'yyyy-mm-dd')COMMON_CANCEL_TIME, COMMON_STATUS FROM FEEDBACK_TABLE where ORDER_ID = ?";
		private static final String DELETE = 
			"DELETE FROM Feedback_Table where ORDER_ID = ?";
		private static final String UPDATE = 
			"UPDATE Feedback_Table set  RS_ID=?, COMMON=?, COMMON_TIME=?, PUSH_YN=?, ORDER_STAR=? ,COMMON_CANCEL_TIME=?,COMMON_STATUS=?  where ORDER_ID = ?";

		private static final String TOTAL_STAR = 
			"SELECT AVG(ORDER_STAR) from FEEDBACK_TABLE";	
		
		private static final String RS_TOTAL_STAR =
			"SELECT AVG(ORDER_STAR) from FEEDBACK_TABLE WHERE RS_ID = ?";
		
		private static final String GET_RS_STAR= 
				"SELECT ORDER_STAR, COMMON from FEEDBACK_TABLE WHERE RS_ID = ?" ;
		
		
		public void insert(Feedback_TableVO feedback_TableVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, feedback_TableVO.getOrder_id());
				pstmt.setString(2, feedback_TableVO.getRs_id());
				pstmt.setString(3, feedback_TableVO.getCommon());
				pstmt.setDate(4, feedback_TableVO.getCommon_time());
				pstmt.setInt(5, feedback_TableVO.getPush_yn());
				pstmt.setInt(6, feedback_TableVO.getOrder_star());
				pstmt.setDate(7, feedback_TableVO.getCommon_cancel_time());
				pstmt.setInt(8, feedback_TableVO.getCommon_status());
				

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
	
	
		
		   public void update(Feedback_TableVO feedback_TableVO) {
			   Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(UPDATE);

					
					pstmt.setString(1, feedback_TableVO.getRs_id());
					pstmt.setString(2, feedback_TableVO.getCommon());
					pstmt.setDate(3, feedback_TableVO.getCommon_time());
					pstmt.setInt(4, feedback_TableVO.getPush_yn());
					pstmt.setInt(5, feedback_TableVO.getOrder_star());
					pstmt.setDate(6, feedback_TableVO.getCommon_cancel_time());
					pstmt.setInt(7, feedback_TableVO.getCommon_status());
					pstmt.setString(8, feedback_TableVO.getOrder_id());
					
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
		   public Feedback_TableVO findByPrimaryKey(String order_id) {
			   Feedback_TableVO feedback_TableVO = null;
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
						// empVo �]�٬� Domain objects
						feedback_TableVO = new Feedback_TableVO();
						feedback_TableVO.setOrder_id(rs.getString("order_id"));
						feedback_TableVO.setRs_id(rs.getString("rs_id"));
						feedback_TableVO.setCommon(rs.getString("common"));
						feedback_TableVO.setCommon_time(rs.getDate("common_time"));
						feedback_TableVO.setPush_yn(rs.getInt("push_yn"));
						feedback_TableVO.setOrder_star(rs.getInt("order_star"));
						feedback_TableVO.setCommon_cancel_time(rs.getDate("common_cancel_time"));
						feedback_TableVO.setCommon_status(rs.getInt("common_status"));
						
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
				return feedback_TableVO;
		   }
		   
		   
		   public List<Feedback_TableVO> getAll(){
			   List<Feedback_TableVO> list = new ArrayList<Feedback_TableVO>();
			   Feedback_TableVO feedback_TableVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
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
						feedback_TableVO.setCommon_cancel_time(rs.getDate("common_cancel_time"));
						feedback_TableVO.setCommon_status(rs.getInt("common_status"));
						list.add(feedback_TableVO); // Store the row in the list
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
			   
			   
		   
		   
		   
		   public Integer rs_avg_star(String rs_id) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Integer rs_avg = null;
				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(RS_TOTAL_STAR);
					pstmt.setString(1, rs_id);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
					 rs_avg=rs.getInt(1);
					
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
				return rs_avg; 
		   }
			   
		   
		   
		   public List<Feedback_TableVO> getRs_star(String rs_id){
			   List<Feedback_TableVO> list = new ArrayList<Feedback_TableVO>();
			   Feedback_TableVO feedback_TableVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(GET_RS_STAR);
					pstmt.setString(1, rs_id);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						
						feedback_TableVO = new Feedback_TableVO();
						feedback_TableVO.setOrder_star(rs.getInt("order_star"));
						feedback_TableVO.setCommon(rs.getString("common"));
						list.add(feedback_TableVO); // Store the row in the list
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
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
			public static void main(String[] args) {

				Feedback_TableJDBCDAO dao = new Feedback_TableJDBCDAO();
				System.out.println(dao.avg_star()); 
				System.out.println(dao.rs_avg_star("RS10005")); 

				// �s�W
//				Feedback_TableVO feedback_TableVO1 = new Feedback_TableVO();
//				feedback_TableVO1.setOrder_id("OD00105");;
//				feedback_TableVO1.setRs_id("RS10005");
//				feedback_TableVO1.setCommon("good");
//				feedback_TableVO1.setCommon_time(java.sql.Date.valueOf("2020-12-02"));
//				feedback_TableVO1.setPush_yn(1);
//				feedback_TableVO1.setOrder_star(5);
//				feedback_TableVO1.setCommon_cancel_time(java.sql.Date.valueOf("2020-12-02"));
//				feedback_TableVO1.setCommon_status(0);
//				
//				dao.insert(feedback_TableVO1);

//				// �ק�
//				Feedback_TableVO feedback_TableVO2 = new Feedback_TableVO();
//				feedback_TableVO2.setOrder_id("OD00105");;
//				feedback_TableVO2.setRs_id("RS10005");
//				feedback_TableVO2.setCommon("good");
//				feedback_TableVO2.setCommon_time(java.sql.Date.valueOf("2020-12-01"));
//				feedback_TableVO2.setPush_yn(1);
//				feedback_TableVO2.setOrder_star(1);
//				feedback_TableVO2.setCommon_cancel_time(java.sql.Date.valueOf("2020-12-01"));
//				feedback_TableVO2.setCommon_status(0);
//				dao.update(feedback_TableVO2);
//
//				// �R��
//				dao.delete("OD00105");
//
//				// �d��
				Feedback_TableVO feedback_TableVO3 = dao.findByPrimaryKey("OD00104");
				System.out.print(feedback_TableVO3.getOrder_id() + ",");
				System.out.print(feedback_TableVO3.getRs_id() + ",");
				System.out.print(feedback_TableVO3.getCommon() + ",");
				System.out.print(feedback_TableVO3.getCommon_time() + ",");
				System.out.print(feedback_TableVO3.getPush_yn() + ",");
				System.out.print(feedback_TableVO3.getOrder_star() + ",");
				System.out.println(feedback_TableVO3.getCommon_status());
				System.out.println("---------------------");
//
//				// �d��
//				List<Feedback_TableVO> list = dao.getAll();
//				for (Feedback_TableVO aFeedback_TableVO : list) {
//					System.out.print(aFeedback_TableVO.getOrder_id() + ",");
//					System.out.print(aFeedback_TableVO.getRs_id() + ",");
//					System.out.print(aFeedback_TableVO.getCommon() + ",");
//					System.out.print(aFeedback_TableVO.getCommon_time() + ",");
//					System.out.print(aFeedback_TableVO.getPush_yn() + ",");
//					System.out.print(aFeedback_TableVO.getOrder_star() + ",");
//					System.out.print(aFeedback_TableVO.getCommon_cancel_time()+ ",");
//					System.out.print(aFeedback_TableVO.getCommon_status()+ ",");
//					
//					System.out.println();
//				}
			}
}
