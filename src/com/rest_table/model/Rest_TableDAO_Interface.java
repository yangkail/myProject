package com.rest_table.model;

import java.util.List;
import java.util.Set;

import com.user_table.model.User_TableVO;


public interface Rest_TableDAO_Interface {
	public String insert(Rest_TableVO rest_TableVO);
	public String getRs_id(String cp_contact_email);
	public String getRs_check_yn(String cp_contact_email);
	public List<Rest_TableVO> getOne();
	public Rest_TableVO getOneAll(String rs_id);
	public Rest_TableVO getOneAllOnlyPic(String rs_id);
	public void updateAuthorityToApplication(String rs_id);
	public void updatePicAndView(Rest_TableVO rest_TableVO);
	public List<Rest_TableVO> getAll(String sql);
	public byte[] getOnePic(String sql,String pic);
	public void updateAuthorityIsOk(String rs_id);
	public void deleteRest(String rs_id);
	public List<Rest_TableVO> getAll();     //熱門餐廳
	public List<Rest_TableVO> getAllNew();	//新進餐廳
	public  List<Rest_TableVO> findByRsName(String rs_type);

	public List<Rest_TableVO> findByRsAddress(String rs_address);
	public List<Rest_TableVO> getAllRestForSomeCondition(String sql);
}
