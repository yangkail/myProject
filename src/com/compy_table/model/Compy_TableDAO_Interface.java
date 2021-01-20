package com.compy_table.model;

import java.util.List;
import java.util.Set;


public interface Compy_TableDAO_Interface {
	public Compy_TableVO findCompyAccountPwd_LoginUse(String cp_account);
	public void insert(Compy_TableVO compy_TableVO);
	public void frontChangeBigPic(Compy_TableVO compy_TableVO);
	public void frontChangeLogo(Compy_TableVO compy_TableVO);
	public void frontChangeBusiness(Compy_TableVO compy_TableVO);
	public void update(Compy_TableVO compy_TableVO);
	public void updateCompyPic(Compy_TableVO compy_TableVO);
	public List<Compy_TableVO> findOtherCompanies(String cp_contact_email);
	public void updateCompyInfo(Compy_TableVO compy_TableVO);
	public void delete(String cp_contact_email);
	public void updateFromBack(String cp_contact_email);
	public void updateFromEMAILBack(String cp_contact_email);
	public Compy_TableVO findByPrimaryKey(String cp_contact_email);
	public Compy_TableVO findByCpAccount(String cp_account);
	public Compy_TableVO findByCpName(String cp_name);
	public List<Compy_TableVO> getAll();
	public Compy_TableVO findCompyPic(String cp_contact_email);
	public void updatePwd(String cp_contact_email,String cp_pwd);
}
