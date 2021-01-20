package com.user_table.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.user_table.model.User_TableService;
import com.user_table.model.User_TableVO;

@WebServlet("/user/user.do")
public class User_TableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		doPost(requset, response);
	}

	public void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {

		requset.setCharacterEncoding("UTF-8");
		String action = requset.getParameter("action");

//-----------------------------------------------------------------------------------//
		//顯示單筆資料
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			requset.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = requset.getParameter("user_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/select_page.jsp");
					failureView.forward(requset, response);
					return;// 程式中斷
				}

				String user_id = null;
				try {
					user_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/select_page.jsp");
					failureView.forward(requset, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				User_TableService user_tableSvc = new User_TableService();
				User_TableVO user_tableVO = user_tableSvc.getOneUser(user_id);
				if (user_tableVO == null) {
					errorMsgs.add("查無資料");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/select_page.jsp");
					failureView.forward(requset, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				requset.setAttribute("user_tableVO", user_tableVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/user/page/listOneEmp.jsp";
				RequestDispatcher successView = requset.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(requset, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/select_page.jsp");
				failureView.forward(requset, response);
			}
		}
//-----------------------------------------------------------------------------------//
		//修改單筆資料
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			requset.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String user_id = new String(requset.getParameter("user_id"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				User_TableService user_tableSvc = new User_TableService();
				User_TableVO user_tableVO = user_tableSvc.getOneUser(user_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				requset.setAttribute("user_tableVO", user_tableVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/user/page/update_user_input.jsp";
				RequestDispatcher successView = requset.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(requset, response);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/listAllEmp.jsp");
				failureView.forward(requset, response);
			}
		}
//-----------------------------------------------------------------------------------//
		//資料更改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			requset.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String user_id = new String(requset.getParameter("user_id").trim());

				String user_account = requset.getParameter("user_account");
				String user_accountReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (user_account == null || user_account.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!user_account.trim().matches(user_accountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 英文字母、數字和_ , 且長度必需在4到10之間");
				}

				String user_password = requset.getParameter("user_password");
				String user_passwordReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (user_password == null || user_password.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if (!user_password.trim().matches(user_passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 英文字母、數字和_ , 且長度必需在4到10之間");
				}

				String user_job = requset.getParameter("user_job").trim();
				if (user_job == null || user_job.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				String user_depart = requset.getParameter("user_depart").trim();
				if (user_depart == null || user_depart.trim().length() == 0) {
					errorMsgs.add("部門請勿空白");
				}

				Integer authority = new Integer(requset.getParameter("authority"));

				User_TableVO user_tableVO = new User_TableVO();
				user_tableVO.setUser_id(user_id);
				user_tableVO.setUser_account(user_account);
				user_tableVO.setUser_password(user_password);

				user_tableVO.setUser_job(user_job);
				user_tableVO.setUser_depart(user_depart);
				user_tableVO.setAuthority(authority);

				if (!errorMsgs.isEmpty()) {
					requset.setAttribute("user_tableVO", user_tableVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = requset
							.getRequestDispatcher("/back-end/user/page/update_user_input.jsp");
					failureView.forward(requset, response);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				User_TableService user_tableSvc = new User_TableService();
				user_tableVO = user_tableSvc.updateUser(user_id, user_account, user_password, user_job, user_depart,
						authority);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				requset.setAttribute("user_tableVO", user_tableVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/user/page/listOneEmp.jsp";
				RequestDispatcher successView = requset.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(requset, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = requset
						.getRequestDispatcher("/back-end/user/page/update_user_input.jsp");
				failureView.forward(requset, response);
			}
		}
//-----------------------------------------------------------------------------------//
		//新增單筆資料
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			requset.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String user_account = requset.getParameter("user_account"); // 取值
				String user_accountReg = "^[(a-zA-Z0-9_)]{4,10}$";
				User_TableService user_tableSvc = new User_TableService(); // 找資料				
				User_TableVO utVo = user_tableSvc.getLog(user_account);

				if (user_account.trim() == null || user_account.trim().length() == 0) {
					errorMsgs.add("員工帳號: 請勿空白");
				} else if (!user_account.trim().matches(user_accountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 只能英文字母、數字和_ , 且長度必需在4到10之間");
				}
				if (user_account.trim() != null && utVo != null) {
					errorMsgs.add("帳號重複");
				}	

				String user_password = requset.getParameter("user_password");
				String user_passwordReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (user_password.trim() == null || user_password.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if (!user_password.trim().matches(user_passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 英文字母、數字和_ , 且長度必需在4到10之間");
				}

				String user_job = requset.getParameter("user_job").trim();
				if (user_job == null || user_job.trim().length() == 0) {
					errorMsgs.add("員工職位: 請勿空白");
				}
		
				String user_depart = requset.getParameter("user_depart").trim();
				if (user_depart == null || user_depart.trim().length() == 0) {
					errorMsgs.add("部門請勿空白");
				}

				User_TableVO user_tableVO = new User_TableVO();

				user_tableVO.setUser_account(user_account);
				user_tableVO.setUser_password(user_password);
				user_tableVO.setUser_job(user_job);
				user_tableVO.setUser_depart(user_depart);

				if (!errorMsgs.isEmpty()) {
					requset.setAttribute("user_tableVO", user_tableVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/addUser.jsp");
					failureView.forward(requset, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				user_tableVO = user_tableSvc.addUser(user_account, user_password, user_job, user_depart);
				System.out.println("6新增成功");

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/user/page/listAllEmp.jsp";
				RequestDispatcher successView = requset.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(requset, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/addUser.jsp");
				failureView.forward(requset, response);
			}
		}

//-----------------------------------------------------------------------------------//
		//刪除單筆資料
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			requset.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String user_id = new String(requset.getParameter("user_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				User_TableService user_tableSvc = new User_TableService();
				user_tableSvc.deleteUser(user_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/user/page/listAllEmp.jsp";
				RequestDispatcher successView = requset.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(requset, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/listAllEmp.jsp");
				failureView.forward(requset, response);
			}
		}
//-----------------------------------------------------------------------------------//
		//平台使用者登入
		if ("login".equals(action)) { // logIn.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			requset.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
				String user_account = requset.getParameter("user_account"); // user_account為輸入的帳號
				String user_accountReg = "^[(a-zA-Z0-9_)]{4,20}$";
				if (user_account.trim() == null || (user_account.trim()).length() == 0 ) {
					errorMsgs.add("帳號不可為空,請重新輸入");
				} else if (!user_account.trim().matches(user_accountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 英文字母、數字和_ , 且長度必需在4到20之間");
				}
				// -------------------------------------------------------//

				String user_password = requset.getParameter("user_password"); // user_password為輸入的密碼
				String user_passwordReg = "^[(a-zA-Z0-9_)]{4,20}$";
				if (user_password.trim() == null || (user_password.trim()).length() == 0) {
					errorMsgs.add("密碼不可為空,請重新輸入");
				} else if (!user_password.trim().matches(user_passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 英文字母、數字和_ , 且長度必需在4到20之間");
				}

				/*************************** 2.開始查詢資料 ***************************************/
				User_TableService user_tableSvc = new User_TableService();
				User_TableVO user_tableVO = user_tableSvc.getLog(user_account);
				String correct_account = user_tableVO.getUser_account();
				String correct_password = user_tableVO.getUser_password();
				Integer authority = user_tableVO.getAuthority();
				
				System.out.println("2取得帳號" + user_account);
				System.out.println("3取得密碼" + user_password);
				System.out.println("正確帳號" + correct_account);
				System.out.println("正確密碼" + correct_password);
				System.out.println("4準備登入呼叫allowUser");
				System.out.println(authority);

				/*************************** 3.查詢完成,準備登入(Send the Success view) ***********/
				if (!(user_account.equals(correct_account) && user_password.equals(correct_password))) {
					errorMsgs.add("輸入的帳號,密碼不符合");// 【帳號 , 密碼有效時, 才做以下工作】
								
				requset.setAttribute("user_tableVO", user_tableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/logIn.jsp");
				failureView.forward(requset, response);
				return;
				}
				
			 
				HttpSession session = requset.getSession();
					session.setAttribute("user_account", user_tableVO.getUser_account());
					session.setAttribute("user_password", user_tableVO.getUser_password());
					session.setAttribute("authority", user_tableVO.getAuthority());
					session.setAttribute("userName", "客服人員");
					
					System.out.println(user_tableVO.getUser_account());
					System.out.println(user_tableVO.getUser_password());
					System.out.println(user_tableVO.getAuthority()+"");
					System.out.println("登入有成功");
					System.out.println("5");

				requset.setAttribute("user_tableVO", user_tableVO);
				String url = "/back-end/user/page/select_page.jsp";
				response.sendRedirect(requset.getContextPath() + url);

	
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("7");
				RequestDispatcher failureView = requset.getRequestDispatcher("/back-end/user/page/logIn.jsp");
				failureView.forward(requset, response);
			}
		}

	}

}
