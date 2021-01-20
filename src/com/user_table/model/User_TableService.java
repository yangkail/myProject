package com.user_table.model;

import java.util.*;

import com.user_table.model.User_TableVO;;

public class User_TableService {

	private User_TableDAO_interface dao;

	public User_TableService() {
		dao = new User_TableJDBCDAO();
	}

	public User_TableVO addUser(String user_account, String user_password, String user_job, String user_depart
			) {

		User_TableVO user_tableVO = new User_TableVO();
		user_tableVO.setUser_account(user_account);
		user_tableVO.setUser_password(user_password);
		user_tableVO.setUser_job(user_job);
		user_tableVO.setUser_depart(user_depart);
//		user_TableVO.setAuthority(authority);
		dao.insert(user_tableVO);
		return user_tableVO;
	}

	public User_TableVO updateUser(String user_id, String user_account, String user_password, String user_job,
			String user_depart, Integer authority) {

		User_TableVO user_tableVO = new User_TableVO();
		user_tableVO.setUser_id(user_id);
		user_tableVO.setUser_account(user_account);
		user_tableVO.setUser_password(user_password);
		user_tableVO.setUser_job(user_job);
		user_tableVO.setUser_depart(user_depart);
		user_tableVO.setAuthority(authority);
		dao.update(user_tableVO);
		return user_tableVO;
	}

	public void deleteUser(String user_id) {
		dao.delete(user_id);
	}

	public User_TableVO getOneUser(String user_id) {
		return dao.findByPrimaryKey(user_id);
	}

	public List<User_TableVO> getAll() {
		return dao.getAll();
	}

	public List<User_TableVO> getDepart() {
		return dao.getDepart();
	}
	
	public User_TableVO getLog(String user_account){
		return dao.getLog(user_account);
	}

}
