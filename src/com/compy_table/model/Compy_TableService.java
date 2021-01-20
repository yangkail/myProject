package com.compy_table.model;

import java.sql.Date;
import java.util.List;


public class Compy_TableService {
	private Compy_TableDAO_Interface compy_TableDAO_Interface;

	public Compy_TableService() {
		compy_TableDAO_Interface=new Compy_TableDAOjdbc();
	}
	
	// 業者前台登入會員驗證,取帳號及密碼
	public Compy_TableVO findCompyAccountPwd_LoginUse(String cp_account) {
		return compy_TableDAO_Interface.findCompyAccountPwd_LoginUse(cp_account);
	}
	
	//新增
	public Compy_TableVO addCompy_Table
	(String cp_contact_email,String cp_phone,String cp_name,String cp_id,String cp_address,String cp_boss,
			String cp_contact_man,String cp_account,String cp_pwd) {
		Compy_TableVO compy_TableVO=new Compy_TableVO();
		
		compy_TableVO.setCp_contact_email(cp_contact_email);
		compy_TableVO.setCp_phone(cp_phone);
		compy_TableVO.setCp_name(cp_name);
		compy_TableVO.setCp_id(cp_id);
		compy_TableVO.setCp_address(cp_address);
		compy_TableVO.setCp_boss(cp_boss);
		compy_TableVO.setCp_contact_man(cp_contact_man);
		compy_TableVO.setCp_account(cp_account);
		compy_TableVO.setCp_pwd(cp_pwd);
		compy_TableDAO_Interface.insert(compy_TableVO);
		
		return compy_TableVO;
		
	}
	
	//前端 業者更改大頭照
	public Compy_TableVO frontChangeBigPic(String cp_contact_email,byte[] cp_bigpic) {
		Compy_TableVO compy_TableVO=new Compy_TableVO();
		compy_TableVO.setCp_contact_email(cp_contact_email);
		compy_TableVO.setCp_bigpic(cp_bigpic);
		compy_TableDAO_Interface.frontChangeBigPic(compy_TableVO);
		return compy_TableVO;
	}
	
	//前端 業者更改大頭照
	public Compy_TableVO frontChangeLogo(String cp_contact_email,byte[] cp_logo) {
		Compy_TableVO compy_TableVO=new Compy_TableVO();
		compy_TableVO.setCp_contact_email(cp_contact_email);
		compy_TableVO.setCp_logo(cp_logo);
		compy_TableDAO_Interface.frontChangeLogo(compy_TableVO);
		return compy_TableVO;
	}
	
	public Compy_TableVO frontChangeBusiness(String cp_contact_email,byte[] cp_business) {
		Compy_TableVO compy_TableVO=new Compy_TableVO();
		compy_TableVO.setCp_contact_email(cp_contact_email);
		compy_TableVO.setCp_business(cp_business);
		compy_TableDAO_Interface.frontChangeBusiness(compy_TableVO);
		return compy_TableVO;
	}
	
	//更改業者表格中照片
	public Compy_TableVO updateCompyPic(String cp_contact_email,byte[] cp_business,byte[] cp_logo,byte[] cp_bigpic) {
		Compy_TableVO compy_TableVO=new Compy_TableVO();
		
		compy_TableVO.setCp_contact_email(cp_contact_email);
		compy_TableVO.setCp_business(cp_business);
		compy_TableVO.setCp_logo(cp_logo);
		compy_TableVO.setCp_bigpic(cp_bigpic);
		compy_TableDAO_Interface.updateCompyPic(compy_TableVO);
		
		return compy_TableVO;
		
	}
	
	//後臺查詢表格資料-排除指定資料
	public List<Compy_TableVO> findOtherCompanies(String cp_contact_email){
		return compy_TableDAO_Interface.findOtherCompanies(cp_contact_email);
	}
	
	//後臺確認業者更改後資料傳送
	public Compy_TableVO updateCompyInfo(
			String cp_phone,String cp_name,String cp_address,
			String cp_id,String cp_boss,String cp_contact_man,
			String cp_pwd,String cp_credit,byte[] cp_business,
			byte[] cp_logo,byte[] cp_bigpic,String cp_contact_email) {
		Compy_TableVO compy_TableVO=new Compy_TableVO();
		compy_TableVO.setCp_contact_email(cp_contact_email);
		compy_TableVO.setCp_phone(cp_phone);
		compy_TableVO.setCp_name(cp_name);
		compy_TableVO.setCp_address(cp_address);
		compy_TableVO.setCp_id(cp_id);
		compy_TableVO.setCp_boss(cp_boss);
		compy_TableVO.setCp_contact_man(cp_contact_man);
		compy_TableVO.setCp_pwd(cp_pwd);
		compy_TableVO.setCp_credit(cp_credit);
		compy_TableVO.setCp_business(cp_business);
		compy_TableVO.setCp_logo(cp_logo);
		compy_TableVO.setCp_bigpic(cp_bigpic);
		compy_TableDAO_Interface.updateCompyInfo(compy_TableVO);
		return compy_TableVO;
	}
	
	//後臺確定刪除資料
	public void delete(String cp_contact_email) {
		compy_TableDAO_Interface.delete(cp_contact_email);
	}
	
	//後臺點選確認驗證後
	public void updateFromBack(String cp_contact_email) {
		compy_TableDAO_Interface.updateFromBack(cp_contact_email);
	}
	
	//業者點選email確認驗證後
	public void updateFromEMAILBack(String cp_contact_email) {
		compy_TableDAO_Interface.updateFromEMAILBack(cp_contact_email);
	}
	
	//用主鍵找表格資料
	public Compy_TableVO findByPrimaryKey(String cp_contact_email) {
		return compy_TableDAO_Interface.findByPrimaryKey(cp_contact_email);
	}
	
	//用業者帳號找表格資料
	public Compy_TableVO findByCpAccount(String cp_account) {
		return compy_TableDAO_Interface.findByCpAccount(cp_account);
	}
	
	//用業者公司名稱找表格資料
	public Compy_TableVO findByCpName(String cp_name) {
		return compy_TableDAO_Interface.findByCpName(cp_name);
	}
	
	//找表格全部	
	public List<Compy_TableVO> getAll(){
		return compy_TableDAO_Interface.getAll();
	}
	
	//用主鍵只找業者圖片
	public Compy_TableVO findCompyPic(String cp_contact_email) {
		return compy_TableDAO_Interface.findCompyPic(cp_contact_email);
	}
	
	//業者更改密碼
	public void updatePwd(String cp_contact_email,String cp_pwd) {
		compy_TableDAO_Interface.updatePwd(cp_contact_email,cp_pwd);
	}
}
