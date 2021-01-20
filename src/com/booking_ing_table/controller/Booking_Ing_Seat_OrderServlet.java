package com.booking_ing_table.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.booking_fixed_table.model.Booking_Fixed_TableService;
import com.booking_fixed_table.model.Booking_Fixed_TableVO;
import com.booking_ing_table.model.Booking_Ing_TableService;
import com.booking_ing_table.model.Booking_Ing_TableVO;
import com.booking_ing_table.model.Booking_Ing_Table_OrderVO;
import com.order_table.model.Order_TableJDBCDAO;
import com.order_table.model.Order_TableVO;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO;


public class Booking_Ing_Seat_OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String rs_select_id ;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 判定使用者要訂哪家餐廳
		if ("get_set".equals(action)) {
			get_set(req, res);
		}
		if("callback".equals(action)) {
			callback(req, res);
			System.out.println("回上一頁");
		}
		if("go_home".equals(action)) {
			go_home(req, res);
			System.out.println("取消訂位回首頁");
		}
		if("add_order".equals(action)) {
			add_order(req, res);
		}
	}
	
	
	public void get_set(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 產生錯誤訊息list
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			HttpSession session = req.getSession();
			String rs_id = (String) session.getAttribute("rs_id");
			// 先取得使用者的資料
			rs_select_id = rs_id;
			System.out.println(rs_select_id);
			java.sql.Date gs_select_date = null;
		
			try {
				gs_select_date = java.sql.Date.valueOf(req.getParameter("gs_select_date").trim());
			} catch (IllegalArgumentException e1) {
				gs_select_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			String gs_select_time = req.getParameter("gs_select_time");
		
			if (gs_select_time == null || (gs_select_time.trim()).length() == 0) {
				errorMsgs.add("請選擇訂位時段");
			}

			Integer rs_table_seat = null;

			try {
				rs_table_seat = new Integer(req.getParameter("rs_table_seat").trim());
				if (rs_table_seat <= 0) {
					errorMsgs.add("請選擇用餐人數");
				}
				
			} catch (NumberFormatException e1) {
				rs_table_seat = 1;
				errorMsgs.add("請選擇用餐人數");
			}
			// 選擇訂位狀態是已訂位
			Integer rs_status = 0;

//			String rs_id = req.getParameter("rs_id").trim();
//			System.out.println("rs_id="+rs_id);
//			System.out.println("gs_select_date="+gs_select_date);
//			System.out.println("gs_select_time="+gs_select_time);
//			System.out.println("rs_table_seat="+rs_table_seat);
//			System.out.println("rs_status="+rs_status);
			// 儲存資料
			Booking_Ing_Table_OrderVO booking_Ing_Table_OrderVO = new Booking_Ing_Table_OrderVO();
			booking_Ing_Table_OrderVO.setRs_id(rs_id);
			booking_Ing_Table_OrderVO.setRs_table_seat(rs_table_seat);
			booking_Ing_Table_OrderVO.setRs_status(rs_status);
			booking_Ing_Table_OrderVO.setGs_select_time(gs_select_time);
			booking_Ing_Table_OrderVO.setGs_select_date(gs_select_date);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/booking_table/booking.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			
			/*************************** 2.開始查詢資料 *****************************************/
			// 調出可以讓使用者訂位的位子
			Booking_Ing_TableService booking_Ing_TableSvc = new Booking_Ing_TableService();
			List<Booking_Ing_Table_OrderVO> booking_Ing_Table_OrderList = booking_Ing_TableSvc.get_all_table_seat
					(rs_id, rs_table_seat, rs_status, gs_select_time, gs_select_date);
			
			if (booking_Ing_Table_OrderList == null) {
				errorMsgs.add("查無資料");
			}
//			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/booking_table/booking.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("booking_Ing_Table_OrderVO", booking_Ing_Table_OrderVO);
			req.setAttribute("booking_Ing_Table_OrderList", booking_Ing_Table_OrderList); // 資料庫取出的empVO物件,存入req
//			System.out.println(((Booking_Ing_Table_OrderVO)(booking_Ing_Table_OrderList.get(0))).getRs_id());			
			System.out.println(req.getContextPath());
			String url = "/front-end/booking_ing/order/order_select.jsp";
			System.out.println("url="+url);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
//
//			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/booking_table/booking.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	public void add_order(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
				// 產生錯誤訊息list
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
//				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					HttpSession session = req.getSession();
					//取得使用者信箱
					String gs_email = (String) req.getParameter("gs_email");
					if(gs_email.isEmpty()) {
//						errorMsgs.add("請登入會員");
						gs_email="Echo@gmail.com";
					}
					//取得餐廳編號
					String rs_id = (String) req.getParameter("rs_id");
					if(rs_id.isEmpty()) {
//						errorMsgs.add("無選擇餐廳");
						rs_id="RS10001";
					}
					//取得座位流水號
					String rs_seat_sieral =(String) req.getParameter("rs_seat_sieral").trim();
					//利用座位流水號使用Svc尋找主鍵
					Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
					Booking_Fixed_TableVO booking_Fixed_TableVO = 
							booking_Fixed_TableSvc.findByRS_SEAT_SIERAL(rs_seat_sieral);
					String rs_sieral= booking_Fixed_TableVO.getRs_sieral();
					//利用公版流水號尋找BOOKING_ING_TABLE那個欄位的資訊
					Booking_Ing_TableService booking_Ing_TableSvc =new Booking_Ing_TableService();
					Booking_Ing_TableVO booking_Ing_TableVO =booking_Ing_TableSvc.findByOrderID(rs_sieral);
					//開始修改使用者選此位的狀態
					String update_order_id =  booking_Ing_TableVO.getOrder_id();
					Integer update_rs_status = 0;
					//取得使用者選位的資料
					//使用者選擇日期
					java.sql.Date gs_select_date = null;
					gs_select_date = java.sql.Date.valueOf(req.getParameter("gs_select_date").trim());
					//使用者選擇時段
					String gs_select_time = req.getParameter("gs_select_time");
					//使用者選擇人數
					Integer rs_table_seat = null;
					rs_table_seat = new Integer(req.getParameter("rs_table_seat").trim());
					// 把訂位狀態改成已訂位
					Integer rs_status = 1;
					
					//開始新增訂單資訊
					List<Order_TableVO> order_TableList1 = new ArrayList<Order_TableVO>();
					Order_TableVO order_TableVO =new Order_TableVO();
					order_TableVO.setRs_id(rs_id);
					order_TableVO.setGs_email(gs_email);
					order_TableVO.setOrder_status(1);
					order_TableVO.setOrder_time(new java.sql.Timestamp(System.currentTimeMillis()));
					order_TableVO.setOrder_cancel_time(null);
					order_TableVO.setGs_order_remark("無備註");
					order_TableVO.setGs_people(rs_table_seat);
					order_TableVO.setGs_select_time(gs_select_time);
					order_TableVO.setOrder_deposit(new Integer(0));
					order_TableVO.setOrder_qrcode(null);
					order_TableVO.setRs_table_status(1);
					order_TableList1.add(order_TableVO);
					
					
//					String rs_id = req.getParameter("rs_id").trim();
//					System.out.println("rs_id="+rs_id);
//					System.out.println("gs_select_date="+gs_select_date);
//					System.out.println("gs_select_time="+gs_select_time);
//					System.out.println("rs_table_seat="+rs_table_seat);
//					System.out.println("rs_status="+rs_status);
					// 儲存資料
//					Booking_Ing_Table_OrderVO booking_Ing_Table_OrderVO = new Booking_Ing_Table_OrderVO();
//					booking_Ing_Table_OrderVO.setRs_id(rs_id);
//					booking_Ing_Table_OrderVO.setRs_table_seat(rs_table_seat);
//					booking_Ing_Table_OrderVO.setRs_status(rs_status);
//					booking_Ing_Table_OrderVO.setGs_select_time(gs_select_time);
//					booking_Ing_Table_OrderVO.setGs_select_date(gs_select_date);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/booking_table/booking.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					
					/*************************** 2.開始查詢資料 *****************************************/
					// 調出可以讓使用者訂位的位子
					List<Order_TableVO> order_TableList = booking_Ing_TableSvc.insertWithOrder
							(rs_status, rs_sieral, gs_select_time, gs_select_date, order_TableList1, update_rs_status, update_order_id);
					
					if (order_TableList == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/booking_table/booking.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
	
				//*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					
					req.setAttribute("order_TableList", order_TableList); // 資料庫取出的empVO物件,存入req
//					System.out.println(((Booking_Ing_Table_OrderVO)(booking_Ing_Table_OrderList.get(0))).getRs_id());			
					String url = "/front-end/booking_ing/order/order_after.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
	
			//*************************** 其他可能的錯誤處理 *************************************/
//				} 
//				catch (Exception e) {
//					errorMsgs.add("無法取得資料:" + e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/Seat_select_order.jsp");
//					failureView.forward(req, res);
//				}
				
	}
	public void go_home(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//轉交到首頁
		String url = "/front-end/Guest_Table/home.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);
	}
	public void callback(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//轉交到上一頁並回傳rs_id
		req.setAttribute("booking_Ing_Table_OrderList", "xxx");
		String url = "/front-end/booking_table/booking.jsp?name=action&value="+rs_select_id;
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);
		
		
	}
}
