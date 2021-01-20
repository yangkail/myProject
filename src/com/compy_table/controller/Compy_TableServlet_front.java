package com.compy_table.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.google.gson.Gson;
import com.rest_table.model.Rest_TableService;

@WebServlet("/compy_table/Compy_TableServlet_front")
@MultipartConfig
public class Compy_TableServlet_front extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String method = request.getParameter("method");

// 業者會員登入驗證==========================================================================================================
// ======================================================================================================================
		if ("front_compy_login".equals(method)) {
			String cp_account = request.getParameter("cp_account").trim();
			String cp_pwd = request.getParameter("cp_pwd").trim();
			Compy_TableService compy_TableService = new Compy_TableService();

			// 裝資料用
			Compy_TableVO compy_TableVO = new Compy_TableVO();

			// 獲取資料庫資料用
			Compy_TableVO compy_TableVO_Compare = compy_TableService.findCompyAccountPwd_LoginUse(cp_account);

			// 錯誤訊息用
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 第一關....先判斷帳號密碼輸入是否為空
				if (cp_account.trim() == null || cp_account.trim().length() == 0) {
					errorMsgs.put("account", "帳號不可為空,請輸入");
				}
				if (cp_pwd.trim() == null || cp_pwd.trim().length() == 0) {
					errorMsgs.put("pwd", "密碼不可為空,請輸入");
				}
				if (!errorMsgs.isEmpty()) {
					compy_TableVO.setCp_account(cp_account);
					request.setAttribute("compy_TableVO", compy_TableVO);
					String url = "/front-end/compy_table/front_CompyLogin.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					return;
				}

				// 第二關....判斷有沒有這帳號 以及帳號是否有被刪除
				if (compy_TableVO_Compare == null) {
					errorMsgs.put("account_error", "帳號或密碼錯誤,請重新輸入");

				} else if (compy_TableVO_Compare.getAuthority() == 10) {
					errorMsgs.put("account_error", "帳號不存在,請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					compy_TableVO.setCp_account(cp_account);
					request.setAttribute("compy_TableVO", compy_TableVO);
					String url = "/front-end/compy_table/front_CompyLogin.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					return;
				}

				// 第三關....判斷密碼有無錯誤
				if (!cp_pwd.equals(compy_TableVO_Compare.getCp_pwd())) {
					errorMsgs.put("account_error", "帳號或密碼錯誤,請重新輸入");

					// 有錯
					compy_TableVO.setCp_account(cp_account);
					request.setAttribute("compy_TableVO", compy_TableVO);
					String url = "/front-end/compy_table/front_CompyLogin.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					return;
				}

				// 裝業者的EMAIL及權限
				Map<String, String> compy_info = new LinkedHashMap<String, String>();
				compy_info.put("cp_contact_email", compy_TableVO_Compare.getCp_contact_email());// 存EMAIL
				compy_info.put("authority", compy_TableVO_Compare.getAuthority() + "");// 轉為字串
				compy_info.put("cp_name", compy_TableVO_Compare.getCp_name());

				Rest_TableService rest_TableService = new Rest_TableService();
				String rs_id = rest_TableService.getRs_id(compy_TableVO_Compare.getCp_contact_email());
				String rs_check_yn = rest_TableService.getRs_check_yn(compy_TableVO_Compare.getCp_contact_email());

				HttpSession session = request.getSession();
				session.setAttribute("authority_compy", compy_info);
				session.setAttribute("rs_id", rs_id);
				session.setAttribute("rs_check_yn", rs_check_yn);
				session.setAttribute("userName", compy_TableVO_Compare.getCp_name());
				

				String url = "/front-end/compy_table/front_CompyMembershipPlatform_info.jsp?display=1";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.put("errorMsgs", "錯誤" + e.getMessage());
			}

		}

// 新增公司資料==========================================================================================================
// ======================================================================================================================
		if ("insert_new_compy".equals(method)) {
			String cp_name = request.getParameter("cp_name").trim();// 公司名稱
			String cp_id = request.getParameter("cp_id").trim();// 公司統編
			String cp_address = request.getParameter("cp_address").trim();// 公司地址
			String cp_boss = request.getParameter("cp_boss").trim();// 公司負責人

			String cp_contact_man = request.getParameter("cp_contact_man").trim();// 公司聯絡人
			String cp_contact_email = request.getParameter("cp_contact_email").trim();// 聯絡人EMAIL
			String cp_phone = request.getParameter("cp_phone").trim();// 公司聯絡人電話

			String cp_account = request.getParameter("cp_account").trim();// 公司帳號
			String cp_pwd = request.getParameter("cp_pwd").trim();// 公司密碼
			String cp_pwd_again = request.getParameter("cp_pwd_again").trim();

			// 錯誤訊息
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			// 裝資料
			Compy_TableVO compy_TableVO = new Compy_TableVO();
			// 找全部資料
			Compy_TableService compy_TableService = new Compy_TableService();
			List<Compy_TableVO> compy_List = compy_TableService.getAll();

			try {
				// 公司名稱(cp_name)
				if (cp_name == null || (cp_name.trim()).length() == 0) {
					errorMsgs.put("cp_name", "公司名稱不可為空");
				} else {
					for (Compy_TableVO list : compy_List) {
						if (cp_name.trim().equals(list.getCp_name())) {
							errorMsgs.put("cp_name", "公司名稱已註冊,請重新輸入");
						}
					}
				}

				// 公司統編(cp_id)
				String checkId = "^[0-9]{8}$";
				if (cp_id == null || (cp_id.trim()).length() == 0) {
					errorMsgs.put("cp_id", "公司統編不可為空");
				} else if (!cp_id.trim().matches(checkId)) {
					errorMsgs.put("cp_id", "公司統編格式錯誤");
				} else {
					for (Compy_TableVO list : compy_List) {
						if (cp_id.trim().equals(list.getCp_id())) {
							errorMsgs.put("cp_id", "公司統編已註冊,請重新輸入");
						}
					}
				}

				// 判斷公司地址(cp_address)
				if (cp_address == null || (cp_address.trim()).length() == 0) {
					errorMsgs.put("cp_address", "公司地址不可為空");
				} else {
					for (Compy_TableVO list : compy_List) {
						if (cp_address.trim().equals(list.getCp_address())) {
							errorMsgs.put("cp_address", "公司地址已註冊,請重新輸入");
						}
					}
				}

				// 判斷負責人(cp_boss)
				if (cp_boss == null || (cp_boss.trim()).length() == 0) {
					errorMsgs.put("cp_boss", "負責人不可為空");
				}

				// 判斷聯絡人(cp_contact_man)
				if (cp_contact_man == null || (cp_contact_man.trim()).length() == 0) {
					errorMsgs.put("cp_contact_man", "聯絡人不可為空");
				}

				// 判斷聯絡人EMAIL(cp_contact_email)
				if (cp_contact_email == null || (cp_contact_email.trim()).length() == 0) {
					errorMsgs.put("cp_contact_email", "EMAIL不可為空");
				} else {
					for (Compy_TableVO list : compy_List) {
						if (cp_contact_email.trim().equals(list.getCp_contact_email())) {
							errorMsgs.put("cp_contact_email", "EMAIL已註冊,請重新輸入");
						}
					}
				}
				// 判斷公司電話(cp_phone)
				if (cp_phone == null || (cp_phone.trim()).length() == 0) {
					errorMsgs.put("cp_phone", "電話不可為空");
				} else {
					for (Compy_TableVO list : compy_List) {
						if (cp_phone.trim().equals(list.getCp_phone())) {
							errorMsgs.put("cp_phone", "電話已註冊,請重新輸入");
						}
					}
				}
				// 判斷帳號(cp_account)
				if (cp_account == null || (cp_account.trim().length()) == 0) {
					errorMsgs.put("cp_account", "帳號不可為空");
				} else {
					for (Compy_TableVO list : compy_List) {
						if (cp_account.trim().equals(list.getCp_account())) {
							errorMsgs.put("cp_account", "帳號已註冊,請重新輸入");
						}
					}
				}

				// 判斷密碼 以及二次密碼
				String enameReg = "^[a-zA-Z0-9]{2,15}$";// 正則表達式

				if (cp_pwd == null || (cp_pwd.trim()).length() == 0) {
					errorMsgs.put("cp_pwd", "密碼不可為空");
				} else if (!cp_pwd.trim().matches(enameReg)) {
					errorMsgs.put("cp_pwd", "密碼格式錯誤");
				} else if (cp_pwd_again == null || (cp_pwd_again.trim()).length() == 0) {
					errorMsgs.put("cp_pwd_again", "二次密碼不可為空");
				} else if (!(cp_pwd_again.trim()).equals(cp_pwd)) {
					errorMsgs.put("cp_pwd_again", "二次密碼輸入不相同");
				}
				compy_TableVO.setCp_name(cp_name);
				compy_TableVO.setCp_id(cp_id);
				compy_TableVO.setCp_address(cp_address);
				compy_TableVO.setCp_boss(cp_boss);
				compy_TableVO.setCp_contact_man(cp_contact_man);
				compy_TableVO.setCp_contact_email(cp_contact_email);
				compy_TableVO.setCp_phone(cp_phone);
				compy_TableVO.setCp_account(cp_account);
				compy_TableVO.setCp_pwd(cp_pwd);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("compy_TableVO", compy_TableVO);
					String url = "/front-end/compy_table/front_addCompyRegister.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					return;
				}
				HttpSession session=request.getSession();
				session.removeAttribute("email");
				compy_TableService.addCompy_Table(cp_contact_email, cp_phone, cp_name, cp_id, cp_address, cp_boss,
						cp_contact_man, cp_account, cp_pwd);
				String url = "/front-end/compy_table/front_addCompyRegisterSuccess.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.put("error", "資料錯誤" + e.getMessage());
			}
		}

// 前端 業者更新大頭照========================================================================================================
// ====================================================================================================================		
		if ("update_bigpic".equals(method)) {
			Part part = request.getPart("cp_bigpic");
			HttpSession session = request.getSession();
			Map<String, String> map = (Map) session.getAttribute("authority_compy");
			String cp_contact_email = map.get("cp_contact_email");
			byte[] cp_bigpic = compyPicToBytes(part);

			Compy_TableService compy_TableService = new Compy_TableService();
			compy_TableService.frontChangeBigPic(cp_contact_email, cp_bigpic);
		}

// 前端 業者更新LOGO========================================================================================================
// ====================================================================================================================		
		if ("update_logo".equals(method)) {
			Part part = request.getPart("cp_logo");
			HttpSession session = request.getSession();
			Map<String, String> map = (Map) session.getAttribute("authority_compy");
			String cp_contact_email = map.get("cp_contact_email");

			byte[] cp_logo = compyPicToBytes(part);

			Compy_TableService compy_TableService = new Compy_TableService();
			compy_TableService.frontChangeLogo(cp_contact_email, cp_logo);
		}

// 前端 業者更新營業登記證========================================================================================================
// ====================================================================================================================	
		if ("update_business".equals(method)) {
			Part part = request.getPart("cp_business");
			HttpSession session = request.getSession();
			Map<String, String> map = (Map) session.getAttribute("authority_compy");
			String cp_contact_email = map.get("cp_contact_email");

			byte[] cp_business = compyPicToBytes(part);

			Compy_TableService compy_TableService = new Compy_TableService();
			compy_TableService.frontChangeBusiness(cp_contact_email, cp_business);
		}

// 密碼更改-密碼驗證============================================================================================
// =======================================================================================================================
		if ("pwd_update".equals(method)) {
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			HttpSession session = request.getSession();
			Map<String, String> map = (Map) session.getAttribute("authority_compy");
			String cp_contact_email = map.get("cp_contact_email");
			String cp_pwd = request.getParameter("pwd_original");

			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByPrimaryKey(cp_contact_email);

			Map<String, String> msgs = new HashMap<String, String>();
			if (!compy_TableVO.getCp_pwd().equals(cp_pwd)) {
				msgs.put("final", "error");
				out.write(gson.toJson(msgs));
			} else {
				msgs.put("final", "success");
				out.write(gson.toJson(msgs));
			}

		}

// 密碼更改-密碼驗證完更改============================================================================================
// =======================================================================================================================
		if ("pwd_update_comfirm".equals(method)) {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			Gson gson = new Gson();
			Map<String, String> map = (Map) session.getAttribute("authority_compy");
			String cp_contact_email = map.get("cp_contact_email");
			String cp_pwd = request.getParameter("pwd_update");

			Compy_TableService compy_TableService = new Compy_TableService();
			compy_TableService.updatePwd(cp_contact_email, cp_pwd);
			Map<String, String> msgs = new HashMap<String, String>();
			msgs.put("final", "success");
			out.write(gson.toJson(msgs));

			session.invalidate();

		}

// 忘記密碼-查詢輸入帳號============================================================================================
// =======================================================================================================================
		if ("account_confirm".equals(method)) {
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			Map<String, String> msgs = new HashMap<String, String>();
			String cp_account = request.getParameter("cp_account");
			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByCpAccount(cp_account);
			if (compy_TableVO == null) {
				msgs.put("final", "no_account");
				out.write(gson.toJson(msgs));
				return;
			} else {
				msgs.put("final", "is_ok");
				out.write(gson.toJson(msgs));
			}
		}

// 忘記密碼-輸入正確帳號後傳送EMAIL============================================================================================
// =======================================================================================================================
		if ("account_confirm_send_email".equals(method)) {
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			Map<String, String> finalMsgs = new HashMap<String, String>();
			String cp_account = request.getParameter("cp_account");
			Compy_TableService compy_TableService = new Compy_TableService();
			Compy_TableVO compy_TableVO = compy_TableService.findByCpAccount(cp_account);
			String cp_contact_email = compy_TableVO.getCp_contact_email();
			String cp_name = compy_TableVO.getCp_name();

			String title = "忘記密碼重設";
			String link = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getRequestURI() + "?method=account_update_form_email_confirm" + "&email="
					+ cp_contact_email;

			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("親愛的" + cp_name + "<br>");
			stringBuffer.append("您好,謝謝您的使用" + "<br>");
			stringBuffer.append("您的帳號經驗證無誤" + "<br>");
			stringBuffer.append("請您點選以下連結,以便更改您的密碼" + "<br>");
			stringBuffer.append("**點選後會自動將您導向密碼更改頁面**" + "<br>");
			stringBuffer.append("<a href=\"" + link + "\">請您點擊我</a>" + "<br>");
			stringBuffer.append("訂味在此謝謝您的愛戴<br>");

			try {
				String msgs = stringBuffer.toString();
				new SendVerifyEmail().sendEmail(cp_contact_email, msgs, cp_name, title);

				finalMsgs.put("final", "is_ok");
				out.write(gson.toJson(finalMsgs));
			} catch (Exception e) {
				finalMsgs.put("final", e.getMessage());
				out.write(gson.toJson(finalMsgs));
			}

		}

// 忘記密碼-EMAIL點回後============================================================================================
// =======================================================================================================================
		if ("account_update_form_email_confirm".equals(method)) {
			String cp_contact_email = request.getParameter("email");
			HttpSession session = request.getSession();
			session.setAttribute("cp_contact_email", cp_contact_email);

			response.sendRedirect("/TEA102G1/front-end/compy_table/front_CompyUpdatePwd.jsp");
		}

// 忘記密碼-輸入正確帳號後傳送EMAIL============================================================================================
// =======================================================================================================================
		if ("update_pwd_final".equals(method)) {
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			Map<String, String> msgs = new HashMap<String, String>();
			HttpSession session = request.getSession();
			String cp_contact_email = (String) session.getAttribute("cp_contact_email");
			String cp_pwd = request.getParameter("cp_pwd");

			if (cp_contact_email == null) {
				msgs.put("final", "error");
				out.write(gson.toJson(msgs));
				return;
			}

			try {
				Compy_TableService compy_TableService = new Compy_TableService();
				compy_TableService.updatePwd(cp_contact_email, cp_pwd);
				msgs.put("final", "is_ok");
				out.write(gson.toJson(msgs));
				session.invalidate();

			} catch (Exception e) {
				msgs.put("final", e.getMessage());
				out.write(gson.toJson(msgs));
			}

		}
// GMAIL用=================================================================================================
// =======================================================================================================
		if ("google".equals(method)) {
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			String email = request.getParameter("email");

			HttpSession session = request.getSession();
			session.setAttribute("email", email);

			Map<String, String> msgs = new HashMap<String, String>();

			try {
				msgs.put("finals", "it_ok");
				out.write(gson.toJson(msgs));
			} catch (Exception e) {
				msgs.put("finals", e.getMessage());
				out.write(gson.toJson(msgs));
			}
		}

// 我是在最底層的!!大量圖片顯示============================================================================================
// =======================================================================================================================
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
// =======================================================================================================================
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
