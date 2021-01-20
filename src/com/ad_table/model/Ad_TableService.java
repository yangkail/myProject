package com.ad_table.model;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Ad_TableService {

	private Ad_TableJDBCDAO dao;

	public  Ad_TableService() {
		dao = new Ad_TableJDBCDAO();
	}
	public Ad_TableVO addAd_Table(Integer ad_top_yn,String rs_id, Integer ad_price,Integer ad_top_time1,Integer ad_top_time2,Integer ad_top_time3,Date ad_time,Date ad_showtime,Date ad_cancel_time,Integer ad_status,byte[] ad_pic_queue) {
		Ad_TableVO ad_TableVO = new Ad_TableVO();
//		ad_TableVO.setAd_serial(ad_serial);
		ad_TableVO.setAd_top_yn(ad_top_yn);
		ad_TableVO.setRs_id(rs_id);
		ad_TableVO.setAd_price(ad_price);
		ad_TableVO.setAd_top_time1(ad_top_time1);
		ad_TableVO.setAd_top_time2(ad_top_time2);
		ad_TableVO.setAd_top_time3(ad_top_time3);
		ad_TableVO.setAd_time(ad_time);
		ad_TableVO.setAd_showtime(ad_showtime);
		ad_TableVO.setAd_cancel_time(ad_cancel_time);
		ad_TableVO.setAd_status(ad_status);
		ad_TableVO.setAd_pic_queue(ad_pic_queue);
		try {
			dao.insert(ad_TableVO);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return ad_TableVO;
	}

	
	public List<Ad_TableVO> getAll() {
		return dao.getAll();
	}

	public Ad_TableVO updateAd_Table(String ad_serial,Integer ad_top_yn,String rs_id, Integer ad_price,Integer ad_top_time1,Integer ad_top_time2,Integer ad_top_time3,Date ad_time,Date ad_showtime,Date ad_cancel_time,Integer ad_status,byte[] ad_pic_queue) {
		Ad_TableVO ad_TableVO = new Ad_TableVO();
		ad_TableVO.setAd_serial(ad_serial);
		ad_TableVO.setAd_top_yn(ad_top_yn);
		ad_TableVO.setRs_id(rs_id);
		ad_TableVO.setAd_price(ad_price);
		ad_TableVO.setAd_top_time1(ad_top_time1);
		ad_TableVO.setAd_top_time2(ad_top_time2);
		ad_TableVO.setAd_top_time3(ad_top_time3);
		ad_TableVO.setAd_time(ad_time);
		ad_TableVO.setAd_showtime(ad_showtime);
		ad_TableVO.setAd_cancel_time(ad_cancel_time);
		ad_TableVO.setAd_status(ad_status);
		ad_TableVO.setAd_pic_queue(ad_pic_queue);
		dao.update(ad_TableVO);
		
		return ad_TableVO;
	}

	
	public Ad_TableVO getOneAd(String ad_serial) {
		return dao.findByPrimaryKey(ad_serial);
	}

	public void deleteAd_Table(String ad_serial) {
		dao.delete(ad_serial);
	}
	
	public Ad_TableVO getOneRs(String rs_id) {
		return dao.findByRsId(rs_id);

	}  
	
    public void updatepic(byte[] ad_pic_queue) {
    	
    }
}