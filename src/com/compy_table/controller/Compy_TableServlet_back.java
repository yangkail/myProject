package com.compy_table.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.compy_table.model.Compy_TableService;
import com.compy_table.model.Compy_TableVO;
import com.rest_table.model.Rest_TableService;

@WebServlet("/compy_table/Compy_TableServlet_back")
@MultipartConfig
public class Compy_TableServlet_back extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String method = request.getParameter("method");

// 後臺公司查詢========================================================================================================
//====================================================================================================================
		if ("select_compy".equals(method)) {
			String cp_account = request.getParameter("cp_account");
			String cp_contact_email = request.getParameter("cp_contact_email");
			String cp_name = request.getParameter("cp_name");

			// 後台用聯絡人EMAIL查詢(cp_contact_email)
			if (cp_contact_email.trim().length() != 0) {
				Compy_TableService compy_TableService = new Compy_TableService();
				Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_selectCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				// 後台用業者帳號查詢(cp_account)
			} else if (cp_account.trim().length() != 0) {
				Compy_TableService compy_TableService = new Compy_TableService();
				Compy_TableVO compy_TableVO = compy_TableService.findByCpAccount(cp_account);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_selectCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				// 後台用業者公司名稱查詢(cp_name)
			} else if (cp_name.trim().length() != 0) {
				Compy_TableService compy_TableService = new Compy_TableService();
				Compy_TableVO compy_TableVO = compy_TableService.findByCpName(cp_name);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_selectCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				// 後台不輸入資料查詢全部
			} else {
				Compy_TableService compy_TableService = new Compy_TableService();
				List<Compy_TableVO> list = compy_TableService.getAll();
				request.setAttribute("list", list);
				String url = "/back-end/compy_table/back_selectCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			}
		}

// 後臺公司查詢========================================================================================================
// ====================================================================================================================
		if ("verify_and_select_compy".equals(method)) {
			String cp_account = request.getParameter("cp_account");
			String cp_contact_email = request.getParameter("cp_contact_email");
			String cp_name = request.getParameter("cp_name");

			// 後台用聯絡人EMAIL查詢(cp_contact_email)
			if (cp_contact_email.trim().length() != 0) {
				Compy_TableService compy_TableService = new Compy_TableService();
				Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_verifyCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				// 後台用業者帳號查詢(cp_account)
			} else if (cp_account.trim().length() != 0) {
				Compy_TableService compy_TableService = new Compy_TableService();
				Compy_TableVO compy_TableVO = compy_TableService.findByCpAccount(cp_account);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_verifyCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				// 後台用業者公司名稱查詢(cp_name)
			} else if (cp_name.trim().length() != 0) {
				Compy_TableService compy_TableService = new Compy_TableService();
				Compy_TableVO compy_TableVO = compy_TableService.findByCpName(cp_name);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_verifyCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				// 後台不輸入資料查詢全部
			} else {
				Compy_TableService compy_TableService = new Compy_TableService();
				List<Compy_TableVO> list = compy_TableService.getAll();
				request.setAttribute("list", list);
				String url = "/back-end/compy_table/back_verifyCompy.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			}
		}

//後臺刪除資料(進入刪除頁面)========================================================================================================
//====================================================================================================================
		if ("want_to_delete_compy".equals(method)) {
			String cp_contact_email = request.getParameter("method_key");

			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
			request.setAttribute("compy_TableVO", compy_TableVO);
			String url = "/back-end/compy_table/back_deleteCompy.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

//後臺確定刪除資料+刪除餐廳========================================================================================================
//====================================================================================================================
		if ("want_to_delete_compy_confirm".equals(method)) {
			String cp_contact_email = request.getParameter("method_key");
			Compy_TableService compy_TableService = new Compy_TableService();
			compy_TableService.delete(cp_contact_email);
			Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
			
			Rest_TableService rest_TableService=new Rest_TableService();
			String rs_id=rest_TableService.getRs_id(cp_contact_email);
			rest_TableService.deleteRest(rs_id);
			
			request.setAttribute("compy_TableVO", compy_TableVO);
			String url = "/back-end/compy_table/back_deleteCompySuccess.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

// 後臺公司更新(進入更新頁面)========================================================================================================
//====================================================================================================================
		if ("want_to_update_compy".equals(method)) {
			String cp_contact_email = request.getParameter("method_key");
			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
			request.setAttribute("compy_TableVO", compy_TableVO);
			String url = "/back-end/compy_table/back_updateCompy.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		}

// 後臺確認公司更新並判斷====================================================================================================
//====================================================================================================================
		if ("want_to_update_compy_confirm".equals(method)) {

			String cp_contact_email = request.getParameter("method_key");
			// 除了自己以外的表格資料
			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
			List<Compy_TableVO> list = new ArrayList<Compy_TableVO>();
			list = compy_TableService.findOtherCompanies(cp_contact_email);
			;
			// 裝錯誤訊息
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String cp_phone = request.getParameter("cp_phone").trim();// 要判斷
				String cp_name = request.getParameter("cp_name").trim();// 要判斷
				String cp_address = request.getParameter("cp_address").trim();// 要判斷
				String cp_id = request.getParameter("cp_id").trim();// 要判斷
				String cp_boss = request.getParameter("cp_boss").trim();
				String cp_contact_man = request.getParameter("cp_contact_man").trim();
				String cp_account = request.getParameter("cp_account").trim();
				String cp_pwd = request.getParameter("cp_pwd").trim();
				String cp_credit = request.getParameter("cp_credit").trim();
				String cp_reg_time = request.getParameter("cp_reg_time").trim();
				String cp_update_time = request.getParameter("cp_update_time").trim();
				String authority = request.getParameter("authority").trim();

				// 判斷公司電話(cp_phone)
				if (cp_phone == null || (cp_phone.trim()).length() == 0) {
					errorMsgs.put("cp_phone", "電話不可為空");
				} else {
					for (Compy_TableVO compylist : list) {
						if (compylist.getCp_phone().equals(cp_phone)) {
							errorMsgs.put("cp_phone", "電話有重複,請重新輸入");
						}
					}
				}

				// 判斷公司名稱(cp_name)
				if (cp_name == null || (cp_name.trim()).length() == 0) {
					errorMsgs.put("cp_name", "公司名稱不可為空");
				} else {
					for (Compy_TableVO compylist : list) {
						if (compylist.getCp_name().equals(cp_name)) {
							errorMsgs.put("cp_name", "公司名稱重複,請重新輸入");
						}
					}
				}

				// 判斷公司地址(cp_address)
				if (cp_address == null || (cp_address.trim()).length() == 0) {
					errorMsgs.put("cp_address", "公司地址不可為空");
				} else {
					for (Compy_TableVO compylist : list) {
						if (compylist.getCp_address().equals(cp_address)) {
							errorMsgs.put("cp_address", "公司地址重複,請重新輸入");
						}
					}
				}

				// 判斷公司統編(cp_id)
				String checkId = "^[0-9]{8}$";
				if (cp_id == null || (cp_id.trim()).length() == 0) {
					errorMsgs.put("cp_id", "公司統編不可為空");
				} else if (!cp_id.trim().matches(checkId)) {
					errorMsgs.put("cp_id", "公司統編格式錯誤");
				} else {
					for (Compy_TableVO compylist : list) {
						if (compylist.getCp_id().equals(cp_id)) {
							errorMsgs.put("cp_id", "公司統編重複,請重新輸入");
						}
					}
				}

				// 判斷負責人
				if (cp_boss == null || (cp_boss.trim()).length() == 0) {
					errorMsgs.put("cp_boss", "負責人不可為空");
				}

				// 判斷聯絡人
				if (cp_contact_man == null || (cp_contact_man.trim()).length() == 0) {
					errorMsgs.put("cp_contact_man", "聯絡人不可為空");
				}

				String enameReg = "^[a-zA-Z0-9]{2,15}$";// 正則表達式
				// 判斷密碼
				if (cp_pwd == null || (cp_pwd.trim()).length() == 0) {
					errorMsgs.put("cp_pwd", "密碼不可為空");
				} else if (!cp_pwd.trim().matches(enameReg)) {
					errorMsgs.put("cp_pwd", "密碼格式錯誤");
				}

				// 判斷信用卡
				if (cp_credit == null || (cp_credit.trim()).length() == 0) {
					errorMsgs.put("cp_credit", "信用卡不可為空");
				} else if (cp_credit.trim().length() != 16) {
					errorMsgs.put("cp_credit", "信用卡格式錯誤");
				}

				compy_TableVO.setCp_phone(cp_phone);
				compy_TableVO.setCp_name(cp_name);
				compy_TableVO.setCp_address(cp_address);
				compy_TableVO.setCp_id(cp_id);
				compy_TableVO.setCp_boss(cp_boss);
				compy_TableVO.setCp_contact_man(cp_contact_man);
				compy_TableVO.setCp_pwd(cp_pwd);
				compy_TableVO.setCp_credit(cp_credit);

				// 營業登記證
				byte[] cp_business = null;
				if (!request.getPart("cp_business").getContentType().equals("application/octet-stream")) {
					cp_business = compyPicToBytes(request.getPart("cp_business"));
					compy_TableVO.setCp_business(cp_business);
				} else {
					cp_business = compy_TableVO.getCp_business();
				}

				// LOGO
				byte[] cp_logo = null;
				if (!request.getPart("cp_logo").getContentType().equals("application/octet-stream")) {
					cp_logo = compyPicToBytes(request.getPart("cp_logo"));
					compy_TableVO.setCp_logo(cp_logo);
				} else {
					cp_logo = compy_TableVO.getCp_logo();
				}

				// 大頭貼
				byte[] cp_bigpic = null;
				if (!request.getPart("cp_bigpic").getContentType().equals("application/octet-stream")) {
					cp_bigpic = compyPicToBytes(request.getPart("cp_bigpic"));
					compy_TableVO.setCp_bigpic(cp_bigpic);
				} else {
					cp_bigpic = compy_TableVO.getCp_bigpic();
				}

				compy_TableVO.setCp_contact_email(cp_contact_email);
				compy_TableVO.setCp_account(cp_account);
				compy_TableVO.setCp_reg_time(java.sql.Date.valueOf(cp_reg_time));
				compy_TableVO.setCp_update_time(java.sql.Date.valueOf(cp_update_time));
				compy_TableVO.setAuthority(Integer.parseInt(authority));

				// 看錯誤訊息中是否為空
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("compy_TableVO", compy_TableVO);
					String url = "/back-end/compy_table/back_updateCompy.jsp";
					RequestDispatcher error = request.getRequestDispatcher(url);
					error.forward(request, response);
					return;
				}

				compy_TableVO = compy_TableService.updateCompyInfo(cp_phone, cp_name, cp_address, cp_id, cp_boss,
						cp_contact_man, cp_pwd, cp_credit, cp_business, cp_logo, cp_bigpic, cp_contact_email);

				compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
				request.setAttribute("compy_TableVO", compy_TableVO);
				String url = "/back-end/compy_table/back_updateCompySuccess.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.put("haveError", "錯誤" + e.getMessage());
				e.printStackTrace();
			}

		}

// 後臺驗證公司(進入驗證頁面)========================================================================================================
// =================================================================================================================================
		if ("want_to_verify_compy".equals(method)) {
			String cp_contact_email = request.getParameter("method_key");

			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);
			request.setAttribute("compy_TableVO", compy_TableVO);
			String url = "/back-end/compy_table/back_verifyOneCompy.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
// 後臺確認驗證公司(寄送EMAIL)========================================================================================================
// =================================================================================================================================
		if("verify_and_sendemail".equals(method)) {
			String cp_contact_email = request.getParameter("method_key");
			String cp_name=request.getParameter("method_key_name");
			
			Compy_TableService compy_TableService=new Compy_TableService();
			compy_TableService.updateFromBack(cp_contact_email);
			
			String title="註冊驗證信";
			String link=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI()
			+"?method=verify_form_email_confirm"
			+"&email="+cp_contact_email;
			
			StringBuffer stringBuffer=new StringBuffer();
			stringBuffer.append("親愛的"+cp_name+"<br>");
			stringBuffer.append("您好,謝謝您的使用"+"<br>");
			stringBuffer.append("經驗證您的資料及營業登記證無誤"+"<br>");
			stringBuffer.append("請您點選以下連結,以便激活您的帳號"+"<br>");
			stringBuffer.append("**點選後會自動將您導向登入頁面**"+"<br>");
			stringBuffer.append("<a href=\""+link+"\">請您點擊我<a>"+"<br>");
			stringBuffer.append("訂味在此謝謝您的愛戴<br>");
			
			String msgs=stringBuffer.toString();
			
			new SendVerifyEmail().sendEmail(cp_contact_email, msgs, cp_name, title);
			
			String url = "/back-end/compy_table/back_verifyCompy.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
// 業者EMAIL點選驗證後========================================================================================================
// =================================================================================================================================
		if("verify_form_email_confirm".equals(method)) {
			String cp_contact_email = request.getParameter("email");
			
			Compy_TableService compy_TableService=new Compy_TableService();
			compy_TableService.updateFromEMAILBack(cp_contact_email);
			
			HttpSession session=request.getSession();
			session.invalidate();
			
			response.sendRedirect(request.getContextPath()+"/front-end/compy_table/front_CompyLogin.jsp");
		}


//我是在最底層的!!大量圖片顯示============================================================================================
//=======================================================================================================================
		if ("many_pics_display".equals(method)) {
			String cp_contact_email = request.getParameter("mail");
			String which_one = request.getParameter("which_one");
			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findCompyPic(cp_contact_email);
			OutputStream out = response.getOutputStream();

			switch (which_one) {
			case "business":
				response.setContentLength(compy_TableVO.getCp_business().length);
				out.write(compy_TableVO.getCp_business());
				break;
			case "logo":
				response.setContentLength(compy_TableVO.getCp_logo().length);
				out.write(compy_TableVO.getCp_logo());
				break;
			case "bigpic":
				response.setContentLength(compy_TableVO.getCp_bigpic().length);
				out.write(compy_TableVO.getCp_bigpic());
				break;
			}
			out.close();
		}
	}

// 照片資料處理==========================================================================================================
//=======================================================================================================================
	private byte[] compyPicToBytes(Part part) {
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
