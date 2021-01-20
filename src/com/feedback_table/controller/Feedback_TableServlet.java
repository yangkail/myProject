package com.feedback_table.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedback_table.model.Feedback_TableService;
import com.feedback_table.model.Feedback_TableVO;
import com.guest_table.model.Guest_TableService;
import com.guest_table.model.Guest_TableVO;
import com.point_table.model.Point_TableService;
import com.point_table.model.Point_TableVO;


public class Feedback_TableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action);
		
		
		if ("insert".equals(action)) { 
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String order_id = request.getParameter("order_id");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (order_id == null || order_id.trim().length() == 0) {
					errorMsgs.put("order_id", "訂單編號請勿空白");
				}

				String rs_id = request.getParameter("rs_id").trim();
				if (rs_id == null || rs_id.trim().length() == 0) {
					errorMsgs.put("rs_id", "餐廳編號請勿空白");
				}
//				

				String common = request.getParameter("common").trim();
				if (rs_id == null || rs_id.trim().length() == 0) {
					errorMsgs.put("common", "請留下你的評論");
				}

				java.sql.Date common_time = null;
				try {
					common_time = java.sql.Date.valueOf(request.getParameter("common_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("common_time", "請輸入日期");
				}

				Integer push_yn = null;
				try {
					push_yn = new Integer(request.getParameter("push_yn").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("push_yn", "是否推薦朋友?");
				}

				Integer order_star = null;
				try {
					order_star = new Integer(request.getParameter("order_star").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("order_star", "請輸入星星數");
				}

				java.sql.Date common_cancel_time = null;
				try {
					common_cancel_time = java.sql.Date.valueOf(request.getParameter("common_cancel_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("common_cancel_time", "請輸入日期");
				}

				Integer common_status = null;
				try {
					common_status = new Integer(request.getParameter("common_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("common_status", "輸入評論狀態");
				}

//				

//			

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Feedback_Table/frontaddFeedback.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Feedback_TableService feedback_TableSvc = new Feedback_TableService();

				feedback_TableSvc.addFeedback(order_id, rs_id, common, common_time, push_yn, order_star,
						common_cancel_time, common_status);
//     ===========================================增加積分====================================================
				
				
				HttpSession session = request.getSession();
				
					String gs_email = (String)session.getAttribute("gs_email");
					
					
					Integer order_get_point = 20;
					
				
					Integer order_use_point = 0;
					
					
					java.sql.Timestamp point_update_time = new java.sql.Timestamp(System.currentTimeMillis());
						
				

					
					/***************************2.開始修改資料*****************************************/
					Point_TableService point_tableSvc = new Point_TableService();
					Point_TableVO point_TableVO=point_tableSvc.addPoint_Table("1",order_id, gs_email, order_get_point, order_use_point, point_update_time);
				
				
				
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/Guest_Table/guest_profilepoint.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(request, response);

//				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Feedback_Table/frontaddFeedback.jsp");
				failureView.forward(request, response);
			}
		}
		if ("getOne_For_Update".equals(action)) { //來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String order_id = new String(request.getParameter("order_id"));
				System.out.println("1123213213");
				/*************************** 2.開始查詢資料 ****************************************/
				Feedback_TableService feedback_TableService = new Feedback_TableService();
				Feedback_TableVO feedback_TableVO = feedback_TableService.getOneFeedback(order_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("feedback_TableVO", feedback_TableVO); //資料庫取出的empVO物件,存入req
				String url = "/back-end/Feedback_Table/update_feedback_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);
				System.out.println("1232132136");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Feedback_Table/listAllFeedback.jsp");
				failureView.forward(request, response);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String order_id = new String(request.getParameter("order_id").trim());

//				
					String rs_id = request.getParameter("rs_id").trim();
					if (rs_id == null || rs_id.trim().length() == 0) {
						errorMsgs.add("號請勿空白");
					}
//				

					String common = request.getParameter("common").trim();
					if (common == null || common.trim().length() == 0) {
						errorMsgs.add("評論請勿空白");
					}

					java.sql.Date common_time = null;
					try {
						common_time = java.sql.Date.valueOf(request.getParameter("common_time").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.add("請輸入時間");
					}

					Integer push_yn = null;
					try {
						push_yn = new Integer(request.getParameter("push_yn").trim());
					} catch (Exception e) {
						errorMsgs.add("請選擇");
					}

					
					Integer order_star = null;
					try {
						order_star = new Integer(request.getParameter("order_star").trim());
					} catch (Exception e) {
						errorMsgs.add("請輸入星星數");
					}
					
					
					java.sql.Date common_cancel_time = java.sql.Date.valueOf(request.getParameter("common_time").trim());
//					try {
//						common_cancel_time = java.sql.Date.valueOf(request.getParameter("common_time").trim());
//					} catch (IllegalArgumentException e) {
//						errorMsgs.add("請輸入時間");
//					}
//					
					Integer common_status = null;
					try {
						common_status = new Integer(request.getParameter("common_status").trim());
					} catch (Exception e) {
						errorMsgs.add("請輸入狀態");
					}
					

					Feedback_TableVO feedback_TableVO = new Feedback_TableVO();
					feedback_TableVO.setOrder_id(order_id);;
					feedback_TableVO.setRs_id(rs_id);
					feedback_TableVO.setCommon(common);
					feedback_TableVO.setCommon_time(common_time);
					feedback_TableVO.setPush_yn(push_yn);
					feedback_TableVO.setOrder_star(order_star);
					feedback_TableVO.setCommon_cancel_time(common_cancel_time);
					feedback_TableVO.setCommon_status(common_status);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("feedback_TableVO", feedback_TableVO); // �t����J�榡���~��empVO����,�]�s�Jreq
						RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Feedback_Table/update_feedback_input.jsp");
						failureView.forward(request, response);
						return; // 程式中斷
					}

					/*************************** 2開始修改資料 *****************************************/
					System.err.println("1");
					Feedback_TableService feedback_TableService = new Feedback_TableService();
					System.err.println("2");
					feedback_TableService.updateFeedback(order_id, rs_id, common, common_time, push_yn, order_star, common_cancel_time, common_status);

					/*************************** 3修改完成,準備轉交(Send the Success view) *************/
					request.setAttribute("feedback_TableVO", feedback_TableVO); // 資料庫update成功後,正確的的empVO物件,存入req
					System.out.println("3");
					String url = "/back-end/Feedback_Table/listAllFeedback.jsp";
					
					RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(request, response);

					/*************************** ��L�i�઺���~�B�z *************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:" + e.getMessage());
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Feedback_Table/update_feedback_input.jsp");
					failureView.forward(request, response);
				}
			}
			
		
		
		//=====================================================================================================
				if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					request.setAttribute("errorMsgs", errorMsgs);

					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						String str = request.getParameter("order_id");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入訂單編號");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = request
									.getRequestDispatcher("/back-end/Feedback_Table/listAllFeedback.jsp");
							failureView.forward(request, response);
							return;//程式中斷
						}
						System.out.println(str);
						String order_id = null;
						try {
							order_id = str;
						} catch (Exception e) {
							errorMsgs.add("訂單編號格式不正確");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = request
									.getRequestDispatcher("/back-end/Feedback_Table/listAllFeedback.jsp");
							failureView.forward(request, response);
							return;//程式中斷
						}
						
						/***************************2.開始查詢資料*****************************************/
						Feedback_TableService feedback_TableService = new Feedback_TableService();
						Feedback_TableVO feedback_TableVO = feedback_TableService.getOneFeedback(order_id);
						if (feedback_TableVO == null) {
							errorMsgs.add("查無資料");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = request
									.getRequestDispatcher("/back-end/Feedback_Table/listAllFeedback.jsp");
							failureView.forward(request, response);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						request.setAttribute("feedback_TableVO", feedback_TableVO); // 資料庫取出的empVO物件,存入req
						String url = "/back-end/Feedback_Table/listOneFeedback.jsp";
						RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(request, response);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = request
								.getRequestDispatcher("/back-end/Feedback_Table/listAllFeedback.jsp");
						failureView.forward(request, response);
					}
				}
		
		
		
		
		
	}
}
