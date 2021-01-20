package com.compy_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Compy_TableDAOjndi implements Compy_TableDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA102g1");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static final String INSERT_STMT = "INSERT INTO COMPY_TABLE VALUES(?,?,?,?,?,?,?,?,?,null,null,null,null,?,null,DEFAULT)";
	private static final String GET_ALL_STMT = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE";
	private static final String GET_ONE_STMT_PK = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE WHERE CP_CONTACT_EMAIL=?";
	private static final String GET_ONE_STMT_ACCOUNT = "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE WHERE CP_ACCOUNT=?";
	private static final String GET_ONE_STMT_NAME= "SELECT CP_CONTACT_EMAIL, CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_ACCOUNT, CP_PWD, CP_BUSINESS, CP_LOGO, CP_BIGPIC, CP_CREDIT, TO_CHAR(CP_REG_TIME,'YYYY-MM-DD')CP_REG_TIME, TO_CHAR(CP_UPDATE_TIME,'YYYY-MM-DD')CP_UPDATE_TIME, AUTHORITY FROM COMPY_TABLE WHERE CP_NAME=?";
	private static final String DELETE = "DELETE FROM COMPY_TABLE WHERE CP_CONTACT_EMAIL=?";
	private static final String UPDATE = "UPDATE COMPY_TABLE SET CP_PHONE=?, CP_NAME=?, CP_ID=?, CP_ADDRESS=?, CP_BOSS=?, CP_CONTACT_MAN=?, CP_UPDATE_TIME=?  WHERE CP_CONTACT_EMAIL=?";
	private static final String UPDATE_PIC="UPDATE COMPY_TABLE SET CP_BUSINESS=?, CP_LOGO=?, CP_BIGPIC=? WHERE CP_CONTACT_EMAIL=?";
	private static final String GET_OTHER_COMP="SELECT CP_PHONE, CP_NAME, CP_ID, CP_ADDRESS, CP_BOSS, CP_CONTACT_MAN, CP_PWD, CP_CREDIT FROM COMPY_TABLE WHERE CP_CONTACT_EMAIL NOT IN(?)";


	//新增
	@Override
	public void insert(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, compy_TableVO.getCp_phone());
			pstmt.setString(2, compy_TableVO.getCp_name());
			pstmt.setString(3, compy_TableVO.getCp_id());
			pstmt.setString(4, compy_TableVO.getCp_address());
			pstmt.setString(5, compy_TableVO.getCp_boss());
			pstmt.setString(6, compy_TableVO.getCp_contact_man());
			pstmt.setString(7, compy_TableVO.getCp_account());
			pstmt.setString(8, compy_TableVO.getCp_pwd());
			pstmt.setDate(9, compy_TableVO.getCp_reg_time());
			pstmt.setDate(10, compy_TableVO.getCp_update_time());
			pstmt.setString(11, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//更改
	@Override
	public void update(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, compy_TableVO.getCp_phone());
			pstmt.setString(2, compy_TableVO.getCp_name());
			pstmt.setString(3, compy_TableVO.getCp_id());
			pstmt.setString(4, compy_TableVO.getCp_address());
			pstmt.setString(5, compy_TableVO.getCp_boss());
			pstmt.setString(6, compy_TableVO.getCp_contact_man());
			pstmt.setDate(7, compy_TableVO.getCp_update_time());
			pstmt.setString(8, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//更改業者圖片
	@Override
	public void updateCompyPic(Compy_TableVO compy_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PIC);

			pstmt.setBytes(1, compy_TableVO.getCp_business());
			pstmt.setBytes(2, compy_TableVO.getCp_logo());
			pstmt.setBytes(3, compy_TableVO.getCp_bigpic());
			pstmt.setString(4, compy_TableVO.getCp_contact_email());
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//後臺查詢表格資料-排除指定資料
	@Override
	public List<Compy_TableVO> findOtherCompanies(String cp_contact_email) {
		List<Compy_TableVO> list=new ArrayList<Compy_TableVO>();
		Compy_TableVO compy_TableVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OTHER_COMP);
			pstmt.setString(1, cp_contact_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				list.add(compy_TableVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	//刪除全部
	@Override
	public void delete(String cp_contact_email) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cp_contact_email);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	//用主鍵找表格資料
	@Override
	public Compy_TableVO findByPrimaryKey(String cp_contact_email) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_PK);

			pstmt.setString(1, cp_contact_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return compy_TableVO;
	}

	//用業者帳號找表格資料
	@Override
	public Compy_TableVO findByCpAccount(String cp_account) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ACCOUNT);

			pstmt.setString(1, cp_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return compy_TableVO;
	}
	
	//用業者公司名稱找表格資料
	@Override
	public Compy_TableVO findByCpName(String cp_name) {
		Compy_TableVO compy_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_NAME);

			pstmt.setString(1, cp_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return compy_TableVO;
	}

	
	//找表格全部
	@Override
	public List<Compy_TableVO> getAll() {
		List<Compy_TableVO> list=new ArrayList<Compy_TableVO>();
		Compy_TableVO compy_TableVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				compy_TableVO = new Compy_TableVO();
				compy_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				compy_TableVO.setCp_phone(rs.getString("cp_phone"));
				compy_TableVO.setCp_name(rs.getString("cp_name"));
				compy_TableVO.setCp_id(rs.getString("cp_id"));
				compy_TableVO.setCp_address(rs.getString("cp_address"));
				compy_TableVO.setCp_boss(rs.getString("cp_boss"));
				compy_TableVO.setCp_contact_man(rs.getString("cp_contact_man"));
				compy_TableVO.setCp_account(rs.getString("cp_account"));
				compy_TableVO.setCp_pwd(rs.getString("cp_pwd"));
				compy_TableVO.setCp_business(rs.getBytes("cp_business"));
				compy_TableVO.setCp_logo(rs.getBytes("cp_logo"));
				compy_TableVO.setCp_bigpic(rs.getBytes("cp_bigpic"));
				compy_TableVO.setCp_credit(rs.getString("cp_credit"));
				compy_TableVO.setCp_reg_time(rs.getDate("cp_reg_time"));
				compy_TableVO.setCp_update_time(rs.getDate("cp_update_time"));
				compy_TableVO.setAuthority(rs.getInt("authority"));
				list.add(compy_TableVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	
	
//以下還沒處理=======================================================================================================
	@Override
	public void updateCompyInfo(Compy_TableVO compy_TableVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compy_TableVO findCompyPic(String cp_contact_email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compy_TableVO findCompyAccountPwd_LoginUse(String cp_account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void frontChangeBigPic(Compy_TableVO compy_TableVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frontChangeLogo(Compy_TableVO compy_TableVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePwd(String cp_contact_email, String cp_pwd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFromBack(String cp_contact_email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFromEMAILBack(String cp_contact_email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frontChangeBusiness(Compy_TableVO compy_TableVO) {
		// TODO Auto-generated method stub
		
	}
}
