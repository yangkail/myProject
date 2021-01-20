package com.booking_fixed_table.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.booking_fixed_table.model.Booking_Fixed_TableService;
import com.booking_fixed_table.model.Booking_Fixed_TableVO;
import com.booking_ing_table.model.Booking_Ing_TableService;
import com.booking_ing_table.model.Booking_Ing_TableVO;
import com.booking_ing_table.model.Booking_Ing_Table_JdbcDAO;
import com.order_table.model.Order_TableVO;

@WebServlet(urlPatterns = { "/Booking_Fixed_Table_Add_Ajax.do" })
public class Booking_Fixed_Table_Add_Ajax extends HttpServlet {
	int i = 0;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		// 產生錯誤訊息list
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String rs_id = (String) session.getAttribute("rs_id");
		// 請求進來由字串處理

		String json = getJsonString(req);
		System.out.println(json);
		try {
		JSONObject jsonObject = new JSONObject(json);

		System.out.println("rs_seat_sieral:" + jsonObject.getString("rs_seat_sieral"));
		System.out.println("RS_TABLE_SEAT:" + jsonObject.getString("rs_table_seat"));
		//新增booking_Ing_TableVO 資料
		List<Booking_Ing_TableVO> booking_Ing_TableList = new ArrayList<Booking_Ing_TableVO>();
		Booking_Ing_TableVO booking_Ing_TableVO =new Booking_Ing_TableVO();
		booking_Ing_TableVO.setRs_status(1);
		booking_Ing_TableVO.setGs_select_date(new java.sql.Date(System.currentTimeMillis()));
		booking_Ing_TableList.add(booking_Ing_TableVO);
		
		String rs_seat_sieral = jsonObject.getString("rs_seat_sieral");
		String rs_table_number = jsonObject.getString("rs_table_number");
		Integer rs_table_seat = new Integer(jsonObject.getString("rs_table_seat"));
		Booking_Fixed_TableService booking_FixedSvc = new Booking_Fixed_TableService();
		Booking_Fixed_TableVO booking_Fixed_TableVO = booking_FixedSvc.insertwithorder(rs_table_number, rs_table_seat, rs_seat_sieral, booking_Ing_TableList);
//		Booking_Fixed_TableVO booking_Fixed_TableVO = booking_FixedSvc.addbooking_fixed_table(rs_table_number, rs_table_seat, rs_seat_sieral);
		
		// 取出所有資料
		List<Booking_Fixed_TableVO> list = booking_FixedSvc.getAll();
		// 取出最後一筆
		Booking_Fixed_TableVO list2 = list.get(list.size() - 1);
		// 新增到json裡
		jsonObject.put("rs_id", rs_id);
		jsonObject.put("rs_sieral", list2.getRs_sieral());
		jsonObject.put("sort", new Integer(i++));

		out.write(jsonObject.toString());
		out.flush();
		out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getJsonString(HttpServletRequest req) {

		StringBuffer json = new StringBuffer();
		try {
			String line = null;
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
