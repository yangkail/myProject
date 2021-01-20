package com.rest_table.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Rest_TableService {
	private Rest_TableDAO_Interface rest_TableDAO_Interface;

	public Rest_TableService() {
		rest_TableDAO_Interface = new Rest_TableDAOjdbc();
	}

	// 業者新增餐廳
	public String insert(String cp_contact_email, String rs_name, String rs_address, String rs_phone, String rs_intro,
			String rs_type, byte[] rs_big_pic, String rs_open_time) {
		Rest_TableVO rest_TableVO = new Rest_TableVO();

		rest_TableVO.setCp_contact_email(cp_contact_email);
		rest_TableVO.setRs_name(rs_name);
		rest_TableVO.setRs_address(rs_address);
		rest_TableVO.setRs_phone(rs_phone);
		rest_TableVO.setRs_intro(rs_intro);
		rest_TableVO.setRs_type(rs_type);
		rest_TableVO.setRs_big_pic(rs_big_pic);
		rest_TableVO.setRs_open_time(rs_open_time);
		String rs_id = rest_TableDAO_Interface.insert(rest_TableVO);

		return rs_id;
	}

	// 業者登入後取餐廳的RS_ID
	public String getRs_id(String cp_contact_email) {
		String rs_id = rest_TableDAO_Interface.getRs_id(cp_contact_email);
		return rs_id;
	}

	// 業者登入後取餐廳審核狀態值
	public String getRs_check_yn(String cp_contact_email) {
		String rs_check_yn = rest_TableDAO_Interface.getRs_check_yn(cp_contact_email);
		return rs_check_yn;
	}

	// 撈資料庫中,RS表格中的全部資料(只撈三個欄位)
	public List<Rest_TableVO> getOne() {
		return rest_TableDAO_Interface.getOne();
	}

	// 前台查詢指定表格中其中一筆資料
	public Rest_TableVO getOneAll(String rs_id) {
		return rest_TableDAO_Interface.getOneAll(rs_id);
	}

	// 取得指定資料的全部照片
	public Rest_TableVO getOneAllOnlyPic(String rs_id) {
		return rest_TableDAO_Interface.getOneAllOnlyPic(rs_id);
	}

	// 業者上傳餐廳的6張圖片
	public void updatePicAndView(Rest_TableVO rest_TableVO) {
		rest_TableDAO_Interface.updatePicAndView(rest_TableVO);
	}

	// 業者按下審核申請按鈕
	public void updateAuthorityToApplication(String rs_id) {
		rest_TableDAO_Interface.updateAuthorityToApplication(rs_id);
	}

	// 後臺查詢餐廳審核狀態-複合查詢
	public List<Rest_TableVO> selectRest(Map<String, String[]> map) {
		StringBuffer sd = new StringBuffer();
		sd.append("SELECT * FROM REST_TABLE ");
		String sql = null;
		Set<String> keys = map.keySet();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (!"method".equals(key)) {
				if (value != null && value.trim().length() != 0) {
					count++;
					String content = getContent(key, value.trim());

					if (count == 1) {
						sd.append(" WHERE " + content);
					} else {
						sd.append(" AND " + content);
					}
				}
			}
		}
		sd.append(" ORDER BY RS_REG_DATE DESC");
		sql = sd.toString();
		return rest_TableDAO_Interface.getAll(sql);
	}

	// 後臺查詢餐廳審核狀態-複合查詢-字串拼接用
	private String getContent(String key, String value) {
		String content = null;
		if ("rs_id".equals(key) || "cp_contact_email".equals(key) || "rs_name".equals(key)) {
			content = key + "='" + value + "'";
		}
		return content;
	}

	// 後臺顯示照片
	public byte[] getOnePic(String which, String rs_id) {
		String sql = "SELECT " + which + " FROM REST_TABLE WHERE RS_ID='" + rs_id + "'";
		return rest_TableDAO_Interface.getOnePic(sql, which);
	}

	// 後臺驗證完畢後更改權限+審核狀態
	public void updateAuthorityIsOk(String rs_id) {
		rest_TableDAO_Interface.updateAuthorityIsOk(rs_id);
	}

	// 後臺下架餐廳
	public void deleteRest(String rs_id) {
		rest_TableDAO_Interface.deleteRest(rs_id);
	}

	// 首頁熱門餐廳取得資料
	public List<Rest_TableVO> getAll() {
		return rest_TableDAO_Interface.getAll();
	}

	// 給首頁新進餐廳使用
	public List<Rest_TableVO> getAllNew() {
		return rest_TableDAO_Interface.getAllNew();
	}

	public List<Rest_TableVO> getRsName(String rs_type) {
		return rest_TableDAO_Interface.findByRsName(rs_type);
	}

	public List<Rest_TableVO> getRsAddress(String rs_address) {
		return rest_TableDAO_Interface.findByRsAddress(rs_address);
	}

	public List<Rest_TableVO> getAllRestForSomeCondition(Map<String, String[]> map) {
		StringBuffer sd = new StringBuffer();
		sd.append("SELECT * FROM REST_TABLE ");
		String sql = null;
		Set<String> keys = map.keySet();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (!"method".equals(key)&&value != null && value.trim().length() != 0) {
				if("search_rest_name".equals(key)) {
					sd.append("WHERE RS_NAME like '%"+value+"%' ");
					count++;
				}
			}
		}
		for (String key : keys) {
			String value = map.get(key)[0];
			if (!"method".equals(key)&&value != null && value.trim().length() != 0&&!"分類".equals(value)) {
				if("search_rest_type".equals(key)) {
					if(count==0) {
						sd.append("WHERE RS_TYPE='"+value+"' ");
					}else {
						sd.append("AND RS_TYPE='"+value+"' ");
					}
				}
			}
		}
		sql=sd.toString();
		return rest_TableDAO_Interface.getAllRestForSomeCondition(sql);
	}
}
