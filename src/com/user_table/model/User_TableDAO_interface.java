package com.user_table.model;

import java.util.*;

public interface User_TableDAO_interface {

	public void insert(User_TableVO user_tableVO);
	public void update(User_TableVO user_tableVO);
	public void delete(String user_id);
	public User_TableVO findByPrimaryKey(String user_id);
	public List<User_TableVO> getAll();
	public List<User_TableVO> getDepart();
	public User_TableVO getLog(String user_account);
	}

// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
