package com.point_table.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.order_table.model.Order_TableJDBCDAO;
import com.order_table.model.Order_TableVO;
import com.point_table.model.*;


@WebServlet("/point_table/Point_TableServlet")
@MultipartConfig
public class Point_TableServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
//		String action1 = request.getParameter("action1");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if ("getAll".equals(action)) {
			/***************************�}�l�d�߸�� ****************************************/
			Point_TableDAO dao = new Point_TableDAO();
			List<Point_TableVO> list = dao.getAll();

			/***************************�d�ߧ���,�ǳ����(Send the Success view)*************/
			HttpSession session = request.getSession();
			session.setAttribute("list", list);    
			// Send the Success view
			String url = "/back-end/point_table/back_listAllPoint_Table.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}
		
	
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("point_sieral");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入積分流水號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/point_table/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				String point_sieral = null;
				try {
					point_sieral = new String(str);
				} catch (Exception e) {
					errorMsgs.add("積分流水號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/point_table/select_page.jsp");
					failureView.forward(request,response);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				Point_TableService point_tableSvc = new Point_TableService();

				Point_TableVO point_tableVO = point_tableSvc.getOnePoint_Table(point_sieral);
				if (point_tableVO == null) {
					errorMsgs.add("查無資料");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/point_table/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("point_tableVO", point_tableVO);// 資料庫取出的point_tableVO物件,存入req
				String url = "/back-end/point_table/back_listOnePoint_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOnePoint_Table.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/point_table/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {	

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String point_sieral = new String(request.getParameter("point_sieral"));
				
				/***************************2.開始查詢資料****************************************/
				Point_TableService point_tableSvc = new Point_TableService();
				Point_TableVO point_tableVO =point_tableSvc.getOnePoint_Table(point_sieral);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("point_tableVO", point_tableVO);         // 資料庫取出的point_tableVO物件,存入req
				String url = "/back-end/point_table/update_point_table_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_point_table_input.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/point_table/back_listAllPoint_Table.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("update".equals(action)) { // 來自update_point_table_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			Point_TableVO point_tableVO = new Point_TableVO();

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String point_sieral = new String(request.getParameter("point_sieral").trim());
				if (point_sieral == null || point_sieral.trim().length() == 0) {
					errorMsgs.add("請輸入正確積分流水號格式");
				}	
				
				
				String order_id = request.getParameter("order_id");
				if (order_id == null || order_id.trim().length() == 0) {
					errorMsgs.add("請輸入正確訂單流水號格式");
				}	
				
				String order_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (order_id == null || order_id.trim().length() == 0) {
					errorMsgs.add("訂單流水號: 請勿空白");
				} else if(!order_id.trim().matches(order_idReg)) { 
					errorMsgs.add("訂單流水號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				String gs_email = request.getParameter("gs_email").trim();
				if (gs_email == null || gs_email.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}	
				
				Integer order_get_point = null;
				try {
					order_get_point = new Integer(request.getParameter("order_get_point").trim());
				} catch (NumberFormatException e) {
					order_get_point = 0;
					errorMsgs.add("請輸入正確新增積分格式");
				}
			
				Integer order_use_point = null;
				try {
					order_use_point = new Integer(request.getParameter("order_use_point").trim());
				} catch (NumberFormatException e) {
					order_use_point = 0;
					errorMsgs.add("請輸入正確使用積分格式");
				}
				
				
				java.sql.Timestamp point_update_time = null;
				try {
					if(request.getParameter("point_update_time") == null || request.getParameter("point_update_time").length() == 0) {		
					throw new IllegalArgumentException();
					} 
					point_update_time = new java.sql.Timestamp(sdf.parse(request.getParameter("point_update_time")).getTime());
				} catch (IllegalArgumentException e) {
					point_update_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確積分更新時間格式");
				}	
				
				point_tableVO.setPoint_sieral(point_sieral);
				point_tableVO.setOrder_id(order_id);
				point_tableVO.setGs_email(gs_email);
				point_tableVO.setOrder_get_point(order_get_point);
				point_tableVO.setOrder_use_point(order_use_point);
				point_tableVO.setPoint_update_time(point_update_time);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("point_tableVO", point_tableVO); // 含有輸入格式錯誤的point_tableVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/point_table/update_point_table_input.jsp");
					failureView.forward(request, response);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Point_TableService point_tableSvc = new Point_TableService();
				point_tableVO = point_tableSvc.updatePoint_Table(point_sieral , order_id, gs_email, order_get_point, order_use_point, point_update_time);
				
				System.out.println(point_tableVO.getPoint_sieral());
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("point_tableVO", point_tableVO); 
				String url = "/back-end/point_table/back_listOnePoint_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				request.setAttribute("point_tableVO", point_tableVO);
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/point_table/update_point_table_input.jsp");
				failureView.forward(request, response);
		}
	}
		
		if ("insert".equals(action)) { // 來自addPoint_Table.jsp的請求 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String point_sieral = request.getParameter("point_sieral");
				if (point_sieral == null || point_sieral.trim().length() == 0) {
					errorMsgs.add("請輸入正確積分流水號格式");
				}	
				
				
				String order_id = request.getParameter("order_id");
				if (order_id == null || order_id.trim().length() == 0) {
					errorMsgs.add("請輸入正確訂單流水號格式");
				}	
				
				String order_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (order_id == null || order_id.trim().length() == 0) {
					errorMsgs.add("訂單流水號: 請勿空白");
				} else if(!order_id.trim().matches(order_idReg)) { 
					errorMsgs.add("訂單流水號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				String gs_email = request.getParameter("gs_email").trim();
				if (gs_email == null || gs_email.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}	
				
				Integer order_get_point = null;
				try {
					order_get_point = new Integer(request.getParameter("order_get_point").trim());
				} catch (NumberFormatException e) {
					order_get_point = 0;
					errorMsgs.add("請輸入正確新增積分格式");
				}
			
				Integer order_use_point = null;
				try {
					order_use_point = new Integer(request.getParameter("order_use_point").trim());
				} catch (NumberFormatException e) {
					order_use_point = 0;
					errorMsgs.add("請輸入正確使用積分格式");
				}
				
				
				java.sql.Timestamp point_update_time = null;
				try {
					if(request.getParameter("point_update_time") == null || request.getParameter("point_update_time").length() == 0) {		
					throw new IllegalArgumentException();
					} 
					point_update_time = new java.sql.Timestamp(sdf.parse(request.getParameter("point_update_time")).getTime());
				} catch (IllegalArgumentException e) {
					point_update_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確積分更新時間格式");
				}
				
				Point_TableVO point_tableVO = new Point_TableVO();
				point_tableVO.setPoint_sieral(point_sieral);
				point_tableVO.setOrder_id(order_id);
				point_tableVO.setGs_email(gs_email);
				point_tableVO.setOrder_get_point(order_get_point);
				point_tableVO.setOrder_use_point(order_use_point);
				point_tableVO.setPoint_update_time(point_update_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("point_tableVO", point_tableVO); 
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/point_table/back_addPoint_Table.jsp");
					failureView.forward(request, response);
					return;
				}

				/***************************2.開始新增資料***************************************/
				Point_TableService point_tableSvc = new Point_TableService();

				point_tableVO = point_tableSvc.addPoint_Table(point_sieral , order_id, gs_email, order_get_point, order_use_point, point_update_time);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/point_table/back_addPoint_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/point_table/back_addPoint_Table.jsp");
				failureView.forward(request, response);

			}
		}
		
		if ("delete".equals(action)) { // 來自listAllPoint_Table.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************************/
				String point_sieral = new String(request.getParameter("point_sieral"));
				/***************************2.開始刪除資料***************************************/
				Point_TableService point_tableSvc = new Point_TableService();
				point_tableSvc.deletePoint_Table(point_sieral);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/point_table/back_listAllPoint_Table.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/order_table/back_listAllPoint_Table.jsp");
				failureView.forward(request, response);
			}
		}
	}
}
	
	
