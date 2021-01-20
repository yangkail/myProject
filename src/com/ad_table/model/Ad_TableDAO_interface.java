package com.ad_table.model;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Ad_TableDAO_interface {

	public void insert(Ad_TableVO ad_TableVO) throws IOException;

	public void update(Ad_TableVO ad_TableVO);

	public void delete(String ad_Serial);

	public Ad_TableVO findByPrimaryKey(String ad_Serial);

	public List<Ad_TableVO> getAll();
	
	public Ad_TableVO findByRsId(String rs_id);
	
//	public  Ad_TableVO findByRsName(String rs_type);
    
}
