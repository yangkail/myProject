package com.order_table.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.order_table.model.*;


@WebServlet("/order_table/Order_TableServlet")
@MultipartConfig
public class Order_TableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String action1 = request.getParameter("action1");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		
//		System.out.println("------------------------------------");
//		System.out.println("轉到ActAplController");
//		System.out.println("這裡是Controller的開頭");
//		System.out.println("action  = " + action);
//		System.out.println("req.getServletPaht() = " + request.getServletPath());
//		System.out.println("req.getContextPath() = " + request.getContextPath());
//		System.out.println("------------------------------------");
		

		
		if("display_pic".equals(action1)) {
//			System.out.println("hello world");
			Order_TableService order_TableService = new Order_TableService();
			String order_id = request.getParameter("order_id");
			Order_TableVO order_TableVO = order_TableService.getOneOrder_Table(order_id);
			byte [] pic = order_TableVO.getOrder_qrcode();
			OutputStream opStream = response.getOutputStream();
			opStream.write(pic);
			opStream.close();
			
		}
		
		
		// 查詢全部訂單資訊
		if ("getAll".equals(action)) {
			
			Order_TableJDBCDAO dao = new Order_TableJDBCDAO();
			List<Order_TableVO> list = dao.getAll();

			HttpSession session =request.getSession();
			session.setAttribute("list", list);    
			// Send the Success view
			String url = "/back-end/order_table/back_listAllOrder_Table.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}
		
		// // 查詢單筆訂單資訊(後臺)
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			System.out.println("======");

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("order_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單流水號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/order_table/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				String order_id = null;
				try {
					order_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單流水號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/order_table/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				Order_TableService order_tableSvc = new Order_TableService();

				Order_TableVO order_tableVO = order_tableSvc.getOneOrder_Table(order_id);
				if (order_tableVO == null) {
					errorMsgs.add("查無資料");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/order_table/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("order_tableVO", order_tableVO);// 資料庫取出的order_tableVO物件,存入req
				String url = "/back-end/order_table/back_listOneOrder_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneOrder_Table.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/order_table/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		//查詢相同餐廳編號資訊(後臺)
		if ("getOne_For_Display1".equals(action)) { // 來自select_page.jsp的請求
			
			Order_TableJDBCDAO dao = new Order_TableJDBCDAO();
			HttpSession session =request.getSession();
			
//		String rs_id=(String) session.getAttribute("rs_id"); //正式用	
			String rs_id="RS10001"; //測試用
			List<Order_TableVO> list = dao.getAllRs_id(rs_id);
			
			
			session.setAttribute("list", list);    
			// Send the Success view
			String url = "/back-end/order_table/back_listAllRs_id.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}	
		
		//查詢相同mail資訊(後臺)
		if ("getOne_For_Display2".equals(action)) { // 來自select_page.jsp的請求
			
			Order_TableJDBCDAO dao = new Order_TableJDBCDAO();
			HttpSession session =request.getSession();
			
//			String gs_email=(String) session.getAttribute("gs_email");//正式用
			String gs_email="Alpha@gmail.com"; //測試用
			
			List<Order_TableVO> list = dao.getAllGs_email(gs_email);
			
			session.setAttribute("list", list);    
			// Send the Success view
			String url = "/back-end/order_table/back_listAllGs_email.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}	
		
		
		if ("getOne_For_Update".equals(action)) {	

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String order_id = new String(request.getParameter("order_id"));
				
				/***************************2.開始查詢資料****************************************/
				Order_TableService order_tableSvc = new Order_TableService();
				Order_TableVO order_tableVO =order_tableSvc.getOneOrder_Table(order_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("order_tableVO", order_tableVO);         // 資料庫取出的order_tableVO物件,存入req
				String url = "/back-end/order_table/update_order_table_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_order_table_input.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage()); 
				
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/order_table/back_listAllOrder_Table.jsp");
				failureView.forward(request, response);
			}
		}
		
		// 更新訂單資訊
		if ("update".equals(action)) { // 來自update_order_table_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			Order_TableVO order_tableVO = new Order_TableVO();
				
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String order_id = new String(request.getParameter("order_id").trim());
				if (order_id == null || order_id.trim().length() == 0) {
					errorMsgs.add("請輸入正確訂單流水號格式");
				}	
				
				
				String rs_id = request.getParameter("rs_id");

				String rs_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (rs_id == null || rs_id.trim().length() == 0) {
					errorMsgs.add("餐廳編號: 請勿空白");
				} else if(!rs_id.trim().matches(rs_idReg)) { 
					errorMsgs.add("餐廳編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				String gs_email = request.getParameter("gs_email");
				if (gs_email == null || gs_email.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}	

				
				Integer order_status = null;
				try {
					order_status = new Integer(request.getParameter("order_status").trim());
				} catch (NumberFormatException e) {
					order_status = 0;
					errorMsgs.add("請輸入正確訂單狀態格式");
				}

				java.sql.Timestamp order_time = null;
				try {
					if(request.getParameter("order_time") == null || request.getParameter("order_time").length() == 0) {		
					throw new IllegalArgumentException();
					} 
					order_time = new java.sql.Timestamp(sdf.parse(request.getParameter("order_time")).getTime());
				} catch (IllegalArgumentException e) {
					order_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確訂單成立時間格式");
				}

				java.sql.Timestamp order_cancel_time = null;
				try {
					if(request.getParameter("order_cancel_time") == null || request.getParameter("order_cancel_time").length() == 0) {		
					throw new IllegalArgumentException();
					} 
					order_cancel_time = new java.sql.Timestamp(sdf.parse(request.getParameter("order_cancel_time")).getTime());
				} catch (IllegalArgumentException e) {
					order_cancel_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確訂單取消時間格式");
				}

				String gs_order_remark = request.getParameter("gs_order_remark");
				if (gs_order_remark == null || gs_order_remark.trim().length() == 0) {
					errorMsgs.add("請輸入正確訂單備註格式");
				}	
				

				Integer gs_people = null;
				try {
					gs_people = new Integer(request.getParameter("gs_people"));
				} catch (NumberFormatException e) {
					gs_people = 0;
					errorMsgs.add("請輸入正確用餐人數");
				}

				String gs_select_time = request.getParameter("gs_select_time");
				if (gs_select_time == null || gs_select_time.trim().length() == 0) {
					errorMsgs.add("請輸入正確用餐時段");
				}	

				Integer order_deposit = null;
				try {
					order_deposit = new Integer(request.getParameter("order_deposit"));
				} catch (NumberFormatException e) {
					order_deposit = 0;
					errorMsgs.add("請輸入正確訂金格式");
				}

				Order_TableService order_tableSvc = new Order_TableService();
				Part order_qrcode = request.getPart("order_qrcode");

				InputStream in = order_qrcode.getInputStream();
				byte[] pic = null;
				if(in.available()!=0) {
					pic = new byte[in.available()];
				in.read(pic);
				in.close();
				}  else {
					pic = order_tableSvc.getOneOrder_Table(order_id).getOrder_qrcode();
				}

				

				Integer rs_table_status = null;
				try {
					rs_table_status = new Integer(request.getParameter("rs_table_status").trim());
				} catch (NumberFormatException e) {
					rs_table_status = 0;
					errorMsgs.add("請輸入正確餐廳座位狀態格式");
				}
				

				
				order_tableVO.setOrder_id(order_id);
				order_tableVO.setRs_id(rs_id);
				order_tableVO.setGs_email(gs_email);
				order_tableVO.setOrder_status(order_status);
				order_tableVO.setOrder_time(order_time);
				order_tableVO.setOrder_cancel_time(order_cancel_time);
				order_tableVO.setGs_order_remark(gs_order_remark);
				order_tableVO.setGs_people(gs_people);
				order_tableVO.setGs_select_time(gs_select_time);
				order_tableVO.setOrder_deposit(order_deposit);
				order_tableVO.setOrder_qrcode(pic);
				order_tableVO.setRs_table_status(rs_table_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("order_tableVO", order_tableVO); // 含有輸入格式錯誤的order_tableVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/order_table/update_order_table_input.jsp");
					System.out.println("xxxxxxxxxx");
					failureView.forward(request, response);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
//				Order_TableService order_tableSvc = new Order_TableService();
				order_tableVO = order_tableSvc.updateOrder_Table(order_id,rs_id, gs_email, order_status, order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, pic, rs_table_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("order_tableVO", order_tableVO); 
				String url = "/back-end/order_table/back_listAllOrder_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				request.setAttribute("order_tableVO", order_tableVO);
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/order_table/update_order_table_input.jsp");
				failureView.forward(request, response);
			}
		}

		
		// 新增訂單資訊
//        if ("insert".equals(action)) { // 來自addOrder_Table.jsp的請求 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			request.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String order_id = request.getParameter("order_id");
//				if ( order_id == null ||  order_id.trim().length() == 0) {
//					errorMsgs.add("訂單流水號請勿空白");
//				}
//
//				String rs_id = request.getParameter("rs_id");
//				String rs_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (rs_id == null || rs_id.trim().length() == 0) {
//					errorMsgs.add("餐廳編號: 請勿空白");
//				} else if(!rs_id.trim().matches(rs_idReg)) { 
//					errorMsgs.add("餐廳編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//
//				
//				String gs_email = request.getParameter("gs_email").trim();
//				if (gs_email == null || gs_email.trim().length() == 0) {
//					errorMsgs.add("信箱請勿空白");
//				}
//
//				Integer order_status = new Integer(request.getParameter("order_status").trim());
//
//				java.sql.Timestamp order_time = null;
//				try {
//					if(request.getParameter("order_time") == null || request.getParameter("order_time").length() == 0) {		
//					throw new IllegalArgumentException();
//					} 
//					order_time = new java.sql.Timestamp(sdf.parse(request.getParameter("order_time")).getTime());
//				} catch (IllegalArgumentException e) {
//					order_time = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("訂單成立時間");
//				}
//				
//				java.sql.Timestamp order_cancel_time = null;
//				try {
//					if(request.getParameter("order_cancel_time") == null || request.getParameter("order_cancel_time").length() == 0) {		
//					throw new IllegalArgumentException();
//					} 
//					order_cancel_time = new java.sql.Timestamp(sdf.parse(request.getParameter("order_cancel_time")).getTime());
//				} catch (IllegalArgumentException e) {
//					order_cancel_time = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("訂單取消時間");
//				}
//
//				String gs_order_remark = request.getParameter("gs_order_remark").trim();
//				if (gs_order_remark == null || gs_order_remark.trim().length() == 0) {
//					errorMsgs.add("請輸入訂單備註");
//				}	
//
//				Integer gs_people = new Integer(request.getParameter("gs_people"));
//
//				String gs_select_time = request.getParameter("gs_select_time").trim();
//				if (gs_select_time == null || gs_select_time.trim().length() == 0) {
//					errorMsgs.add("請輸入用餐人數");
//				}	
//
//				Integer order_deposit = null;
//				try {
//					order_deposit = new Integer(request.getParameter("order_deposit").trim());
//				} catch (NumberFormatException e) {
//					order_deposit = 0;
//					errorMsgs.add("請輸入使用訂金");
//				}
//				
//				Order_TableService order_tableSvc = new Order_TableService();
//				Part order_qrcode = request.getPart("order_qrcode");
//				InputStream in = order_qrcode.getInputStream();
//				byte[] pic = null;
//				if(in.available()!=0) {
//					pic = new byte[in.available()];
//				in.read(pic);
//				in.close();
//				}  else {
//					pic = order_tableSvc.getOneOrder_Table(order_id).getOrder_qrcode();
//				}
//				
//				
//				Integer rs_table_status = new Integer(request.getParameter("rs_table_status").trim());
//
//				
//				Order_TableVO order_tableVO = new Order_TableVO();
//				order_tableVO.setOrder_id(order_id);
//				order_tableVO.setRs_id(rs_id);
//				order_tableVO.setGs_email(gs_email);
//				order_tableVO.setOrder_status(order_status);
//				order_tableVO.setOrder_time(order_time);
//				order_tableVO.setOrder_cancel_time(order_cancel_time);
//				order_tableVO.setGs_order_remark(gs_order_remark);
//				order_tableVO.setGs_people(gs_people);
//				order_tableVO.setGs_select_time(gs_select_time);
//				order_tableVO.setOrder_deposit(order_deposit);
//				order_tableVO.setOrder_qrcode(pic);
//				order_tableVO.setRs_table_status(rs_table_status);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					request.setAttribute("order_tableVO", order_tableVO); 
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/back-end/order_table/back_addOrder_Table.jsp");
//					failureView.forward(request, response);
//					return;
//				}
//
//				/***************************2.開始新增資料***************************************/
////				Order_TableService order_tableSvc = new Order_TableService();
//
//				order_tableVO = order_tableSvc.addOrder_Table(order_id, rs_id, gs_email, order_status, order_time, order_cancel_time, gs_order_remark, gs_people, gs_select_time, order_deposit, pic, rs_table_status);
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/order_table/back_addOrder_Table.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);
//				successView.forward(request, response);				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無qrcode"+e.getMessage());
//				RequestDispatcher failureView = request
//						.getRequestDispatcher("/back-end/order_table/back_addOrder_Table.jsp");
//				failureView.forward(request, response);
//
//			}
//		}
		
		// 刪除訂單資訊
		if ("delete".equals(action)) { // 來自back_listAllOrder_Table.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************************/
				String order_id = new String(request.getParameter("order_id"));
				/***************************2.開始刪除資料***************************************/
				Order_TableService order_tableSvc = new Order_TableService();
				order_tableSvc.deleteOrder_Table(order_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/order_table/back_listAllOrder_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/order_table/back_listAllOrder_Table.jsp");
				failureView.forward(request, response);
			}
		}
	}
	private byte[] order_qrcode(Part part) {
		byte[] pic = null;
		try (InputStream in = part.getInputStream()) {
			pic = new byte[in.available()];
			in.read(pic);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return pic;
	}
}
