package com.guest_table.model;

import java.util.List;

import org.apache.tomcat.util.descriptor.web.LoginConfig;


public interface Guest_TableDAO_interface {
	
	public void insert(Guest_TableVO guest_TableVO);
    public void update(Guest_TableVO guest_TableVO);
    public void delete(String gs_email);
    public Guest_TableVO findByPrimaryKey(String gs_email);
    public List<Guest_TableVO> getAll();
	public Guest_TableVO login(String gs_email);
	public Integer avg_star();
	public void updateGsBigPic(String sql,byte[] gs_big_pic);
	public void updateCredit(Guest_TableVO guest_TableVO);
	public void updatePwd(Guest_TableVO guest_TableVO);
	public Integer totalNoUsePoint(String sql1,String gs_email);
	public Integer totalUsePoint(String sql2,String gs_email);
		
	
}
