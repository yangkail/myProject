package com.rest_table.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.rest_table.model.Rest_TableDAO_Interface;
import com.rest_table.model.Rest_TableService;
import com.rest_table.model.Rest_TableVO;
import com.sun.mail.imap.protocol.ID;

@WebServlet("/rest_table/Rest_TableServlet_front")
@MultipartConfig
public class Rest_TableServlet_front extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String method = request.getParameter("method");

//業者新增餐廳=====================================================================================================================
//================================================================================================================================
		if ("insert_new_rest".equals(method)) {
			Rest_TableService rest_TableService=new Rest_TableService();
			List<Rest_TableVO> rsList=rest_TableService.getOne();//取地目前有註冊的餐廳三資訊
			
			
			HttpSession session = request.getSession();
			Map<String, String> map = (Map) session.getAttribute("authority_compy");
			String cp_contact_email = map.get("cp_contact_email");// 業者EMAIL主鍵

			String rs_name = request.getParameter("rs_name").trim();// 餐廳名稱
			String address = request.getParameter("rs_address").trim();// 餐廳全部地址(須轉)
			String city = request.getParameter("cities").trim();// 餐廳所在城市(須轉)
			String area = request.getParameter("areas").trim();// 餐廳所在地區(須轉)
			String rs_phone=request.getParameter("rs_phone").trim();
			String[] type = request.getParameterValues("rs_type");// 餐廳類別(須轉)
			String[] open_time = request.getParameterValues("rs_open_time");// 餐廳營業時間(須轉)
			String rs_intro = request.getParameter("rs_intro").trim();// 餐廳簡介
			Part part = request.getPart("rs_big_pic");// 餐廳照片

			Rest_TableVO rest_TableVO=new Rest_TableVO();
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				// 判斷餐廳姓名是否為空
				if (rs_name == null || (rs_name.trim()).length() == 0) {
					errorMsgs.put("rs_name", "請輸入餐廳姓名");
				}else{
					for(Rest_TableVO list:rsList) {
						if(rs_name.equals(list.getRs_name())) {
							errorMsgs.put("rs_name", "餐廳名稱已有註冊,請重新輸入");
						}
					}
				}
				
				//判斷地址是否為空
				String rs_address=null;
				if ((address.length()) == (city.length() + area.length())) {
					errorMsgs.put("rs_address", "請輸入地址");
				}else {
					StringBuffer sd=new StringBuffer();
					sd.append(city+",");
					sd.append(area+",");
					sd.append(address);
					rs_address=sd.toString();
					for(Rest_TableVO list:rsList) {
						if(rs_address.equals(list.getRs_address())) {
							errorMsgs.put("rs_address", "地址已有註冊,請重新輸入");
						}
					}
				}
				
				
				//判斷電話是否為空
				if(rs_phone==null||(rs_phone.trim()).length()==0) {
					errorMsgs.put("rs_phone","請輸入電話");
				}else {
					for(Rest_TableVO list:rsList) {
						if(rs_address.equals(list.getRs_phone())) {
							errorMsgs.put("rs_phone", "電話已有註冊,請重新輸入");
						}
					}
				}
				
				//判斷有沒有點選類別
				String rs_type=null;
				if(type==null) {
					errorMsgs.put("rs_type","請點選餐廳類別");
				}else {
					rs_type=addStringIn(type);
				}
				
				//判斷有沒有點選營業時間
				String rs_open_time=null;
				if(open_time==null) {
					errorMsgs.put("rs_open_time","請點選營業時間");
				}else {
					if(open_time.length==3) {
						rs_open_time="4";
					}else {
						rs_open_time=addStringIn(open_time);
					}
				}
				
				//判斷簡介
				if(rs_intro==null||(rs_intro.trim()).length()==0) {
					errorMsgs.put("rs_intro","請輸入餐廳介紹");
				}else if(rs_intro.length()<20) {
					errorMsgs.put("rs_intro","輸入字數少於20字,請確認");
				}else if(rs_intro.length()>100){
					errorMsgs.put("rs_intro","輸入字數多於100字,請確認");
				}
				
				//判斷餐廳LOGO
				byte[] rs_big_pic=null;
				try(InputStream in=part.getInputStream()){
					if(in.available()==0) {
						errorMsgs.put("rs_big_pic","請上傳圖片");
					}else {
						rs_big_pic=picToBrainy(part);
					}
				}
				rest_TableVO.setRs_name(rs_name);
				rest_TableVO.setRs_address(address);
				rest_TableVO.setRs_phone(rs_phone);
				rest_TableVO.setRs_type(rs_type);
				rest_TableVO.setRs_open_time(rs_open_time);
				rest_TableVO.setRs_intro(rs_intro);
				
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("rest_TableVO",rest_TableVO);
					errorMsgs.put("rs_big_pic","請上傳圖片");
					String url = "/front-end/rest_table/front_CompyAddRest_info.jsp";
					RequestDispatcher error = request.getRequestDispatcher(url);
					error.forward(request, response);
					return;
				}
				
				rest_TableVO.setRs_name(rs_name);
				rest_TableVO.setRs_address(rs_address);
				rest_TableVO.setRs_phone(rs_phone);
				rest_TableVO.setRs_type(rs_type);
				rest_TableVO.setRs_open_time(rs_open_time);
				rest_TableVO.setRs_intro(rs_intro);
				rest_TableVO.setRs_big_pic(rs_big_pic);
				
				String rs_id=rest_TableService.insert(cp_contact_email, rs_name, rs_address, rs_phone, rs_intro, rs_type, rs_big_pic, rs_open_time);
				
				session.setAttribute("rs_id", rs_id);
				
				response.sendRedirect(request.getContextPath()+"/front-end/rest_table/front_CompyAddRest_info.jsp?display=5");
				
				
			} catch (Exception e) {
				errorMsgs.put("error",e.getMessage());
				String url = "/front-end/rest_table/front_CompyAddRest_info.jsp";
				RequestDispatcher error = request.getRequestDispatcher(url);
				error.forward(request, response);
			}
		}
		
//業者按下"審核申請"按鈕=====================================================================================================================
//=======================================================================================================================================
		if("update_pic_and_view".equals(method)) {
			HttpSession session=request.getSession();
			String rs_id=(String) session.getAttribute("rs_id");
			
			Part part1=request.getPart("rs_view1");
			byte[] rs_view1=picToBrainy(part1);
			
			Part part2=request.getPart("rs_view2");
			byte[] rs_view2=picToBrainy(part2);
			
			Part part3=request.getPart("rs_view3");
			byte[] rs_view3=picToBrainy(part3);
			
			Part part4=request.getPart("rs_view4");
			byte[] rs_view4=picToBrainy(part4);
			
			Part part5=request.getPart("rs_view5");
			byte[] rs_view5=picToBrainy(part5);
			
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				if(rs_view1.length==0) {
					errorMsgs.put("error", "錯誤,需上傳所有圖片,請您重新確認");
				}
				if(rs_view2.length==0) {
					errorMsgs.put("error", "錯誤,需上傳所有圖片,請您重新確認");
				}
				if(rs_view3.length==0) {
					errorMsgs.put("error", "錯誤,需上傳所有圖片,請您重新確認");
				}
				if(rs_view4.length==0) {
					errorMsgs.put("error", "錯誤,需上傳所有圖片,請您重新確認");
				}
				if(rs_view5.length==0) {
					errorMsgs.put("error", "錯誤,需上傳所有圖片,請您重新確認");
				}
				
				Rest_TableVO rest_TableVO=new Rest_TableVO();
				rest_TableVO.setRs_id(rs_id);
				rest_TableVO.setRs_view1(rs_view1);
				rest_TableVO.setRs_view2(rs_view2);
				rest_TableVO.setRs_view3(rs_view3);
				rest_TableVO.setRs_view4(rs_view4);
				rest_TableVO.setRs_view5(rs_view5);
				
				if(!errorMsgs.isEmpty()) {
					String url = "/front-end/rest_table/front_CompyAddPic.jsp";
					RequestDispatcher error = request.getRequestDispatcher(url);
					error.forward(request, response);
					return;
				}
				
				Rest_TableService rest_TableService=new Rest_TableService();
				rest_TableService.updatePicAndView(rest_TableVO);
				
				response.sendRedirect(request.getContextPath()+"/front-end/rest_table/front_CompyAddpic_Seat.jsp");
				
			}catch (Exception e) {
				errorMsgs.put("error","錯誤,伺服器忙線中");
				String url = "/front-end/rest_table/front_CompyAddPic.jsp";
				RequestDispatcher error = request.getRequestDispatcher(url);
				error.forward(request, response);
			}
		}
		
//業者按下"審核申請"按鈕=====================================================================================================================
//=======================================================================================================================================
		if("review_application".equals(method)) {
			HttpSession session=request.getSession();
			String rs_id=(String) session.getAttribute("rs_id");
			
			Rest_TableService rest_TableService=new Rest_TableService();
			rest_TableService.updateAuthorityToApplication(rs_id);
			
			response.sendRedirect(request.getContextPath()+"/front-end/rest_table/front_CompyAddRest_info.jsp");
		}
		
		
//我在最底層,照片顯示=====================================================================================================================
//=======================================================================================================================================
		if("get_the_pic".equals(method)) {
			String rs_id=request.getParameter("id");
			Rest_TableService rest_TableService=new Rest_TableService();
			Rest_TableVO rest_TableVO=rest_TableService.getOneAllOnlyPic(rs_id);
			OutputStream out=response.getOutputStream();
			String which=request.getParameter("which");
			
			switch (which) {
				case "rs_bigpic":
					response.setContentLength(rest_TableVO.getRs_big_pic().length);
					out.write(rest_TableVO.getRs_big_pic());
					break;
				case "rs_pic":
					response.setContentLength(rest_TableVO.getRs_pic().length);
					out.write(rest_TableVO.getRs_pic());
					break;
				case "rs_view1":
					response.setContentLength(rest_TableVO.getRs_view1().length);
					out.write(rest_TableVO.getRs_view1());
					break;
				case "rs_view2":
					response.setContentLength(rest_TableVO.getRs_view2().length);
					out.write(rest_TableVO.getRs_view2());
					break;
				case "rs_view3":
					response.setContentLength(rest_TableVO.getRs_view3().length);
					out.write(rest_TableVO.getRs_view3());
					break;
				case "rs_view4":
					response.setContentLength(rest_TableVO.getRs_view4().length);
					out.write(rest_TableVO.getRs_view4());
					break;
				case "rs_view5":
					response.setContentLength(rest_TableVO.getRs_view5().length);
					out.write(rest_TableVO.getRs_view5());
					break;
			}
		}
	}


	//拿來字串串接用
	private String addStringIn(String[] strings) {
		StringBuffer sb = new StringBuffer();
		for (int count = 0; count < strings.length; count++) {
			sb.append(strings[count] + (count != (strings.length - 1) ? "," : ""));
		}
		return sb.toString();
	}

	//照片轉為陣列用
	private byte[] picToBrainy(Part part) {
		byte[] content = null;
		try (InputStream in = part.getInputStream()) {
			content = new byte[in.available()];
			in.read(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

}
