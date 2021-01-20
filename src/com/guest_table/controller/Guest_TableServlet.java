package com.guest_table.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;


import com.guest_table.model.Guest_TableService;
import com.guest_table.model.Guest_TableVO;

@MultipartConfig
public class Guest_TableServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
//   顯示圖片
		if ("display_pic".equals(action)) {
			String gs_email=request.getParameter("gs_email");
			Guest_TableService guest_TableService = new Guest_TableService();
			Guest_TableVO guest_TableVO = guest_TableService.getOneGuest(gs_email);
			
			
			byte[] pic = guest_TableVO.getGs_big_pic();
			OutputStream opStream = response.getOutputStream();
			opStream.write(pic);
			opStream.close();
		}

// 上傳圖片		
		if ("update_bigpic".equals(action)) {
			System.out.println("123");
			Part part = request.getPart("gs_big_pic");
			HttpSession session = request.getSession();
			String gs_email = (String) session.getAttribute("gs_email");
			byte[] gs_big_pic = guestPicToBytes(part);

			Guest_TableService guest_TableService = new Guest_TableService();
			guest_TableService.updateGsBigPic(gs_email, gs_big_pic);
		
		}
//=====================================================================================================
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("gs_email");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電子信箱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/Guest_Table/listAllGuest.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				System.out.println(str);
				String gs_email = null;
				try {
					gs_email = str;
				} catch (Exception e) {
					errorMsgs.add("電子信箱格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/Guest_Table/listAllGuest.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Guest_TableService guest_TableService = new Guest_TableService();
				Guest_TableVO guest_TableVO = guest_TableService.getOneGuest(gs_email);
				if (guest_TableVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/Guest_Table/listAllGuest.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/Guest_Table/listOneGuest.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/Guest_Table/listAllGuest.jsp");
				failureView.forward(request, response);
			}
		}

//=====================================================================================================
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String gs_email = request.getParameter("gs_email");
				String emailRule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
//				
				if (gs_email == null || gs_email.trim().length() == 0) {
					errorMsgs.put("gs_email", "電子信箱請勿空白");
				} else if (!gs_email.trim().matches(emailRule)) {
					errorMsgs.put("gs_email", "電子信箱格式錯誤");
				}
//				

				String gs_pwd = request.getParameter("gs_pwd").trim();

				String pwd_Reg = "^[a-zA-Z0-9]{6,15}$";
				if (gs_pwd == null || gs_pwd.trim().length() == 0) {
					errorMsgs.put("gs_pwd", "密碼請勿空白");
				} else if (!gs_pwd.trim().matches(pwd_Reg)) {
					errorMsgs.put("gs_pwd", "密碼最少請輸入6碼");
				}

				String gs_name = request.getParameter("gs_name").trim();
				if (gs_name == null || gs_name.trim().length() == 0) {
					errorMsgs.put("gs_name", "姓名請勿空白");
				}

				java.sql.Date gs_birth = null;
				try {
					gs_birth = java.sql.Date.valueOf(request.getParameter("gs_birth").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("gs_birth", "請輸入日期");
				}

				Integer gs_sex = null;
				try {
					gs_sex = new Integer(request.getParameter("gs_sex").trim());
				} catch (Exception e) {
					errorMsgs.put("gs_sex", "請選擇性別");
				}

				String gs_phone = request.getParameter("gs_phone").trim();
				if (gs_phone == null || gs_phone.trim().length() == 0) {
					errorMsgs.put("gs_phone", "電話請勿空白");
				}

				String gs_address = request.getParameter("gs_address").trim();
				if (gs_address == null || gs_address.trim().length() == 0) {
					errorMsgs.put("gs_address", "地址請勿空白");
				}

				String gs_credit = request.getParameter("gs_credit");

				java.sql.Date gs_reg_time = null;
				try {
					gs_reg_time = java.sql.Date.valueOf(request.getParameter("gs_reg_time").trim());
				} catch (Exception e) {
					// IllegalArgumentException
					errorMsgs.put("gs_reg_time", "請輸入日期");
				}

				
				
				byte[] gs_big_pic = null;
				if(request.getPart("gs_big_pic").getContentType()==null) {
					File file = new File("C:/TEA102_WebApp/eclipse_WTP_workspace1/TEA102G1/WebContent/images/nopic2.jpg");
					gs_big_pic=guestPicToBytesFile(file);
				}else {
					Part part = request.getPart("gs_big_pic");
					InputStream ips = part.getInputStream();
					gs_big_pic = new byte[ips.available()];
					ips.read(gs_big_pic);
					ips.close();
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Guest_Table/register1.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Guest_TableService guest_TableSvc = new Guest_TableService();

				guest_TableSvc.addGuest_Table(gs_email, gs_pwd, gs_name, gs_birth, gs_sex, gs_phone, gs_address,
						gs_credit, gs_reg_time, gs_big_pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/Guest_Table/login2.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);

//				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
//				errorMsgs.put("Exception", e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Guest_Table/register1.jsp");
				failureView.forward(request, response);
			}
		}
//=====================================================================================================
//		後台新增
		
		if ("back-insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String gs_email = request.getParameter("gs_email");
				String emailRule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
//				
				if (gs_email == null || gs_email.trim().length() == 0) {
					errorMsgs.put("gs_email", "電子信箱請勿空白");
				} else if (!gs_email.trim().matches(emailRule)) {
					errorMsgs.put("gs_email", "電子信箱格式錯誤");
				}
//				

				String gs_pwd = request.getParameter("gs_pwd").trim();

				String pwd_Reg = "^[a-zA-Z0-9]{2,15}$";
				if (gs_pwd == null || gs_pwd.trim().length() == 0) {
					errorMsgs.put("gs_pwd", "密碼請勿空白");
				} else if (!gs_pwd.trim().matches(pwd_Reg)) {
					errorMsgs.put("gs_pwd", "密碼格式錯誤");
				}

				String gs_name = request.getParameter("gs_name").trim();
				if (gs_name == null || gs_name.trim().length() == 0) {
					errorMsgs.put("gs_name", "姓名請勿空白");
				}

				java.sql.Date gs_birth = null;
				try {
					gs_birth = java.sql.Date.valueOf(request.getParameter("gs_birth").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("gs_birth", "請輸入日期");
				}

				Integer gs_sex = null;
				try {
					gs_sex = new Integer(request.getParameter("gs_sex").trim());
				} catch (Exception e) {
					errorMsgs.put("gs_sex", "請選擇性別");
				}

				String gs_phone = request.getParameter("gs_phone").trim();
				if (gs_phone == null || gs_phone.trim().length() == 0) {
					errorMsgs.put("gs_phone", "電話請勿空白");
				}

				String gs_address = request.getParameter("gs_address").trim();
				if (gs_address == null || gs_address.trim().length() == 0) {
					errorMsgs.put("gs_address", "地址請勿空白");
				}

				String gs_credit = request.getParameter("gs_credit");

				java.sql.Date gs_reg_time = null;
				try {
					gs_reg_time = java.sql.Date.valueOf(request.getParameter("gs_reg_time").trim());
				} catch (Exception e) {
					// IllegalArgumentException
					errorMsgs.put("gs_reg_time", "請輸入日期");
				}

				
				
				byte[] gs_big_pic = null;
				if(request.getPart("gs_big_pic").getContentType()==null) {
					File file = new File("C:/TEA102_WebApp/eclipse_WTP_workspace1/TEA102G1/WebContent/images/nopic2.jpg");
					gs_big_pic=guestPicToBytesFile(file);
				}else {
					Part part = request.getPart("gs_big_pic");
					InputStream ips = part.getInputStream();
					gs_big_pic = new byte[ips.available()];
					ips.read(gs_big_pic);
					ips.close();
				}


				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Guest_Table/register.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Guest_TableService guest_TableSvc = new Guest_TableService();

				guest_TableSvc.addGuest_Table(gs_email, gs_pwd, gs_name, gs_birth, gs_sex, gs_phone, gs_address,
						gs_credit, gs_reg_time, gs_big_pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Guest_Table/listAllGuest.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);

//				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("Exception", e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Guest_Table/register.jsp");
				failureView.forward(request, response);
			}
		}
//=====================================================================================================
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String gs_email = new String(request.getParameter("gs_email"));
				System.out.println("1");
				/*************************** 2.開始查詢資料 ****************************************/
				Guest_TableService guest_TableSvc = new Guest_TableService();
				Guest_TableVO guest_TableVO = guest_TableSvc.getOneGuest(gs_email);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/Guest_Table/update_guest_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);
				System.out.println("6");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Guest_Table/listAllGuest.jsp");
				failureView.forward(request, response);
			}
		}
//==========================================================================================================
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String gs_email = new String(request.getParameter("gs_email").trim());


				String gs_pwd = request.getParameter("gs_pwd").trim();
				if (gs_pwd == null || gs_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
//			

				String gs_name = request.getParameter("gs_name").trim();
				if (gs_name == null || gs_name.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}

				java.sql.Date gs_birth = null;
				try {
					gs_birth = java.sql.Date.valueOf(request.getParameter("gs_birth").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日");
				}

				Integer gs_sex = null;
				try {
					gs_sex = new Integer(request.getParameter("gs_sex").trim());
				} catch (Exception e) {
					errorMsgs.add("請填性別");
				}

				String gs_phone = request.getParameter("gs_phone").trim();
				if (gs_phone == null || gs_phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				String gs_address = request.getParameter("gs_address").trim();
				if (gs_address == null || gs_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String gs_credit = request.getParameter("gs_credit");

				java.sql.Date gs_reg_time = null;
				try {
					gs_reg_time = java.sql.Date.valueOf(request.getParameter("gs_reg_time").trim());
				} catch (Exception e) {
					// IllegalArgumentException
					errorMsgs.add("請輸入註冊日期");
				}

//				byte[] gs_big_pic = null;

				Guest_TableVO guest_TableVO = new Guest_TableVO();
				guest_TableVO.setGs_email(gs_email);
				guest_TableVO.setGs_pwd(gs_pwd);
				guest_TableVO.setGs_name(gs_name);
				guest_TableVO.setGs_birth(gs_birth);
				guest_TableVO.setGs_sex(gs_sex);
				guest_TableVO.setGs_phone(gs_phone);
				guest_TableVO.setGs_address(gs_address);
				guest_TableVO.setGs_credit(gs_credit);
				guest_TableVO.setGs_reg_time(gs_reg_time);
//				guest_TableVO.setGs_big_pic(gs_big_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("guest_TableVO", guest_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/Guest_Table/update_gs_profile.jsp");
					failureView.forward(request, response);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				System.err.println("1");
				Guest_TableService guest_TableSvc = new Guest_TableService();
				System.err.println("2");
				guest_TableSvc.updateGuest(gs_email, gs_pwd, gs_name, gs_birth, gs_sex, gs_phone, gs_address, gs_credit,
						gs_reg_time);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫update成功後,正確的的empVO物件,存入req
				HttpSession session=request.getSession();
				session.setAttribute("gs_email", guest_TableVO.getGs_email());
				session.setAttribute("gs_pwd", guest_TableVO.getGs_pwd());
				session.setAttribute("gs_name", guest_TableVO.getGs_name());
				session.setAttribute("gs_birth", guest_TableVO.getGs_birth());
				session.setAttribute("gs_phone", guest_TableVO.getGs_phone());
				session.setAttribute("gs_sex", guest_TableVO.getGs_sex());
				session.setAttribute("gs_phone", guest_TableVO.getGs_phone());
				session.setAttribute("gs_address", guest_TableVO.getGs_address());
				session.setAttribute("gs_credit", guest_TableVO.getGs_credit());
				session.setAttribute("authority", guest_TableVO.getAuthority());
				session.setAttribute("gs_reg_time", guest_TableVO.getGs_reg_time());
				System.out.println("3");
				String url = "/front-end/Guest_Table/guest_profile1.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/Guest_Table/update_gs_profile.jsp");
				failureView.forward(request, response);
			}
		}
		//==========================================================================================================
				if ("updateCredit".equals(action)) { // 來自update_emp_input.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					request.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						String gs_email = new String(request.getParameter("gs_email").trim());
			

						String gs_credit = request.getParameter("gs_credit");
						if (gs_credit == null || gs_credit.trim().length() == 0) {
							errorMsgs.add("卡號請勿空白");
						}
					
						Guest_TableVO guest_TableVO = new Guest_TableVO();
					
						
						guest_TableVO.setGs_credit(gs_credit);
					

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							request.setAttribute("guest_TableVO", guest_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = request
									.getRequestDispatcher("/front-end/Guest_Table/credit.jsp");
							failureView.forward(request, response);
							return; // 程式中斷
						}

						/*************************** 2.開始修改資料 *****************************************/
						System.err.println("1");
						Guest_TableService guest_TableSvc = new Guest_TableService();
						System.err.println("2");
						guest_TableSvc.updateCredit(gs_email, gs_credit);

						/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//						request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫update成功後,正確的的empVO物件,存入req
						HttpSession session=request.getSession();
						
						session.setAttribute("gs_credit", guest_TableVO.getGs_credit());
						
						String url = "/front-end/Guest_Table/credit.jsp";
						RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
						successView.forward(request, response);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:" + e.getMessage());
						RequestDispatcher failureView = request
								.getRequestDispatcher("/front-end/Guest_Table/credit.jsp");
						failureView.forward(request, response);
					}
				}
				//==========================================================================================================
				if ("updatePwd".equals(action)) { // 來自update_emp_input.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					request.setAttribute("errorMsgs", errorMsgs);
System.out.println("1");
					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						HttpSession session = request.getSession();
						
						String gs_email = (String)session.getAttribute("gs_email");
			

						String gs_pwd = request.getParameter("gs_pwd");


						Guest_TableVO guest_TableVO = new Guest_TableVO();
					
						
						guest_TableVO.setGs_pwd(gs_pwd);
						System.out.println("2");

					
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							request.setAttribute("guest_TableVO", guest_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = request
									.getRequestDispatcher("/front-end/Guest_Table/changePWD.jsp");
							failureView.forward(request, response);
							return; // 程式中斷
						}

						/*************************** 2.開始修改資料 *****************************************/
						System.err.println(gs_pwd);
						Guest_TableService guest_TableSvc = new Guest_TableService();
						System.err.println(gs_email);
						guest_TableSvc.updatePwd(gs_email, gs_pwd);
						System.err.println(gs_pwd);
						/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
						request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫update成功後,正確的的empVO物件,存入req
						
						
//						session.setAttribute("gs_pwd", guest_TableVO.getGs_pwd());
						session.invalidate();
						String url = "/front-end/Guest_Table/login2.jsp";
						RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
						successView.forward(request, response);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:" + e.getMessage());
						RequestDispatcher failureView = request
								.getRequestDispatcher("/front-end/Guest_Table/changePWD.jsp");
						failureView.forward(request, response);
					}
				}
		//==========================================================================================================
//		後台修改
					if ("back_update".equals(action)) { // 來自update_emp_input.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					request.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						String gs_email = new String(request.getParameter("gs_email").trim());

						String gs_pwd = request.getParameter("gs_pwd").trim();
						if (gs_pwd == null || gs_pwd.trim().length() == 0) {
							errorMsgs.add("密碼請勿空白");
						}				

						String gs_name = request.getParameter("gs_name").trim();
						if (gs_name == null || gs_name.trim().length() == 0) {
							errorMsgs.add("姓名請勿空白");
						}

						java.sql.Date gs_birth = null;
						try {
							gs_birth = java.sql.Date.valueOf(request.getParameter("gs_birth").trim());
						} catch (IllegalArgumentException e) {
							errorMsgs.add("請輸入生日");
						}

						Integer gs_sex = null;
						try {
							gs_sex = new Integer(request.getParameter("gs_sex").trim());
						} catch (Exception e) {
							errorMsgs.add("請填性別");
						}

						String gs_phone = request.getParameter("gs_phone").trim();
						if (gs_phone == null || gs_phone.trim().length() == 0) {
							errorMsgs.add("電話請勿空白");
						}

						String gs_address = request.getParameter("gs_address").trim();
						if (gs_address == null || gs_address.trim().length() == 0) {
							errorMsgs.add("地址請勿空白");
						}

						String gs_credit = request.getParameter("gs_credit");

						java.sql.Date gs_reg_time = null;
						try {
							gs_reg_time = java.sql.Date.valueOf(request.getParameter("gs_reg_time").trim());
						} catch (Exception e) {
							// IllegalArgumentException
							errorMsgs.add("請輸入註冊日期");
						}


						Guest_TableVO guest_TableVO = new Guest_TableVO();
						guest_TableVO.setGs_email(gs_email);
						guest_TableVO.setGs_pwd(gs_pwd);
						guest_TableVO.setGs_name(gs_name);
						guest_TableVO.setGs_birth(gs_birth);
						guest_TableVO.setGs_sex(gs_sex);
						guest_TableVO.setGs_phone(gs_phone);
						guest_TableVO.setGs_address(gs_address);
						guest_TableVO.setGs_credit(gs_credit);
						guest_TableVO.setGs_reg_time(gs_reg_time);

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							request.setAttribute("guest_TableVO", guest_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = request
									.getRequestDispatcher("/back-end/Guest_Table/update_guest_input.jsp");
							failureView.forward(request, response);
							return; // 程式中斷
						}

						/*************************** 2.開始修改資料 *****************************************/
						System.err.println("1");
						Guest_TableService guest_TableSvc = new Guest_TableService();
						System.err.println("2");
						guest_TableSvc.updateGuest(gs_email, gs_pwd, gs_name, gs_birth, gs_sex, gs_phone, gs_address, gs_credit,
								gs_reg_time);

						/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//						request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫update成功後,正確的的empVO物件,存入req
						HttpSession session=request.getSession();
						session.setAttribute("gs_email", guest_TableVO.getGs_email());
						session.setAttribute("gs_pwd", guest_TableVO.getGs_pwd());
						session.setAttribute("gs_name", guest_TableVO.getGs_name());
						session.setAttribute("gs_birth", guest_TableVO.getGs_birth());
						session.setAttribute("gs_phone", guest_TableVO.getGs_phone());
						session.setAttribute("gs_sex", guest_TableVO.getGs_sex());
						session.setAttribute("gs_phone", guest_TableVO.getGs_phone());
						session.setAttribute("gs_address", guest_TableVO.getGs_address());
						session.setAttribute("gs_credit", guest_TableVO.getGs_credit());
						session.setAttribute("authority", guest_TableVO.getAuthority());
						session.setAttribute("gs_reg_time", guest_TableVO.getGs_reg_time());
						System.out.println("3");
						String url = "/back-end/Guest_Table/listAllGuest.jsp";
						RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
						successView.forward(request, response);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:" + e.getMessage());
						RequestDispatcher failureView = request
								.getRequestDispatcher("/back-end/Guest_Table/update_guest_input.jsp");
						failureView.forward(request, response);
					}
				}
//					//==========================================================================================================
					if ("mail".equals(action)) { // 來自update_emp_input.jsp的請求
//
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						request.setAttribute("errorMsgs", errorMsgs);
//
						try {
//							/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//							HttpSession session = request.getSession();
//							
//							String gs_email = (String)session.getAttribute("gs_email");
							String gs_email = new String(request.getParameter("gs_email").trim());				

							String gs_pwd = getRandomPassword();


							Guest_TableVO guest_TableVO = new Guest_TableVO();
						
							
							guest_TableVO.setGs_pwd(gs_pwd);
							System.out.println("2");

							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								request.setAttribute("guest_TableVO", guest_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = request
										.getRequestDispatcher("/front-end/Guest_Table/changePWD.jsp");
								failureView.forward(request, response);
								return; // 程式中斷
							}

							/*************************** 2.開始修改資料 *****************************************/
							System.err.println(gs_pwd);
							Guest_TableService guest_TableSvc = new Guest_TableService();
							System.err.println(gs_email);
							guest_TableSvc.updatePwd(gs_email, gs_pwd);
							System.err.println(gs_pwd);
							/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
							request.setAttribute("guest_TableVO", guest_TableVO); // 資料庫update成功後,正確的的empVO物件,存入req
							
							/*************************** 4.寄送MAIL *************/
							
							 String host = "smtp.gmail.com";  
						        int port = 587;  
						        final String username = "tea102g1@gmail.com";  
						        final String password = "tea79979";  
						  
						        Properties props = new Properties();  
						        props.put("mail.smtp.host", host);  
						        props.put("mail.smtp.auth", "true");  
						        props.put("mail.smtp.starttls.enable", "true");  
						        props.put("mail.smtp.port", port);  
						          
						        Session session = Session.getInstance(props,new Authenticator(){  
						              protected PasswordAuthentication getPasswordAuthentication() {  
						                  return new PasswordAuthentication(username, password);  
						              }} );  
						           
						        try {  
						  
						        Message message = new MimeMessage(session);  
						        message.setFrom(new InternetAddress("tea102g1@gmail.com"));  
						        message.setRecipients(Message.RecipientType.TO,   
						                        InternetAddress.parse(gs_email));  
						        message.setSubject("訂味    重新設定密碼");  
						        message.setText("親愛的會員您好,這是您的暫時密碼"+gs_pwd+ ",請用此密碼登入修改新的密碼,謝謝");  
						  
						        Transport transport = session.getTransport("smtp");  
						        transport.connect(host, port, username, password);  
						  
						        Transport.send(message);  
						  
						        System.out.println("Done");  
						  
						        } catch (MessagingException e) {  
						            throw new RuntimeException(e);  
						        }  
							
							
							String url = "/front-end/Guest_Table/login2.jsp";
							RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
							successView.forward(request, response);

							/*************************** 其他可能的錯誤處理 *************************************/
						} catch (Exception e) {
							errorMsgs.add("修改資料失敗:" + e.getMessage());
							RequestDispatcher failureView = request
									.getRequestDispatcher("/front-end/Guest_Table/changePWD.jsp.jsp");
							failureView.forward(request, response);
						}
					}
		
		
		// 登入
		if ("login".equals(action)) {
			String gs_email = request.getParameter("gs_email");
			String gs_pwd = request.getParameter("gs_pwd");
			String gs_name = request.getParameter("gs_name");
			
			Guest_TableService guest_TableSvc = new Guest_TableService();
			Guest_TableVO guest_TableVO = guest_TableSvc.login(gs_email);
			
			if (guest_TableSvc.getOneGuest(gs_email)==null){
				request.setAttribute("message", "帳號或密碼錯誤，請重新登入");
				request.getRequestDispatcher("/front-end/Guest_Table/login2.jsp").forward(request, response);
				return;
			}
			String userName = guest_TableVO.getGs_email();
			String password = guest_TableVO.getGs_pwd();
			 if (userName.equals(gs_email) && password.equals(gs_pwd)) {
				guest_TableVO = guest_TableSvc.getOneGuest(gs_email);
				request.setAttribute("guest_TableVO", guest_TableVO);

				System.out.println(guest_TableVO.getGs_name());
				HttpSession session = request.getSession();
				session.setAttribute("gs_email", guest_TableVO.getGs_email());
				session.setAttribute("gs_pwd", guest_TableVO.getGs_pwd());
				session.setAttribute("gs_name", guest_TableVO.getGs_name());
				session.setAttribute("gs_birth", guest_TableVO.getGs_birth());
				session.setAttribute("gs_phone", guest_TableVO.getGs_phone());
				session.setAttribute("gs_sex", guest_TableVO.getGs_sex());
				session.setAttribute("gs_phone", guest_TableVO.getGs_phone());
				session.setAttribute("gs_address", guest_TableVO.getGs_address());
				session.setAttribute("gs_credit", guest_TableVO.getGs_credit());
				session.setAttribute("authority", guest_TableVO.getAuthority());
				session.setAttribute("gs_reg_time", guest_TableVO.getGs_reg_time());
				String url = "/front-end/Guest_Table/guest_profile1.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

			} else {
				request.setAttribute("message", "帳號或密碼錯誤，請重新登入");
				request.getRequestDispatcher("/front-end/Guest_Table/login2.jsp").forward(request, response);
			}

		}
	}

	
//---------------------------------------------------------------------------------
	
	private byte[] guestPicToBytes(Part part) {
		byte[] pic = null;
		try (InputStream in = part.getInputStream()) {
			pic = new byte[in.available()];
			in.read(pic);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return pic;
	}
	
	
	
	private byte[] guestPicToBytesFile(File file) {
		byte[] pic = null;
		try (InputStream in = new FileInputStream(file)) {
			pic = new byte[in.available()];
			in.read(pic);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return pic;
	}
	
	 private String getRandomPassword() {
		    int z;
		    StringBuilder sb = new StringBuilder();
		    int i;
		    for (i = 0; i < 8; i++) {
		      z = (int) ((Math.random() * 7) % 3);
		 
		      if (z == 1) { // 放數字
		        sb.append((int) ((Math.random() * 10) + 48));
		      } else if (z == 2) { // 放大寫英文
		        sb.append((char) (((Math.random() * 26) + 65)));
		      } else {// 放小寫英文
		        sb.append(((char) ((Math.random() * 26) + 97)));
		      }
		    }
		    return sb.toString();
		  }
}