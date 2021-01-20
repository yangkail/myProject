package com.rest_table.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rest_table.model.Rest_TableService;
import com.rest_table.model.Rest_TableVO;

@WebServlet("/rest_table/Rest_TableServlet_back")
@MultipartConfig
public class Rest_TableServlet_back extends HttpServlet {
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

//後臺查詢餐廳資料(未審核)============================================================================================================
//===========================================================================================================================
		if ("verify_and_select_rs".equals(method)) {

			Map<String, String[]> map = request.getParameterMap();

			Rest_TableService rest_TableService = new Rest_TableService();
			List<Rest_TableVO> list = rest_TableService.selectRest(map);
			request.setAttribute("list", list);

			String url = "/back-end/rest_table/back_verifyCompyRest.jsp";
			RequestDispatcher view = request.getRequestDispatcher(url);
			view.forward(request, response);

		}

// 後臺查詢餐廳資料(已審核)============================================================================================================
// ===========================================================================================================================
		if ("select_rs".equals(method)) {

			Map<String, String[]> map = request.getParameterMap();

			Rest_TableService rest_TableService = new Rest_TableService();
			List<Rest_TableVO> list = rest_TableService.selectRest(map);
			request.setAttribute("list", list);

			String url = "/back-end/rest_table/back_CompyRest.jsp";
			RequestDispatcher view = request.getRequestDispatcher(url);
			view.forward(request, response);

		}

//後臺顯示單一餐廳業者照片============================================================================================================
//===================================================================================================================================
		if ("display_one_img".equals(method)) {
			String which = request.getParameter("which");
			String rs_id = request.getParameter("key");
			OutputStream out = response.getOutputStream();

			Rest_TableService rest_TableService = new Rest_TableService();
			byte[] pic = rest_TableService.getOnePic(which, rs_id);
			out.write(pic);
		}

//後臺驗證成功============================================================================================================
//===================================================================================================================================
		if ("rest_review_application_sussess".equals(method)) {
			String rs_id = request.getParameter("rs_id");
			Rest_TableService rest_TableService = new Rest_TableService();
			rest_TableService.updateAuthorityIsOk(rs_id);

			response.sendRedirect(request.getContextPath() + "/back-end/rest_table/back_verifyCompyRest.jsp");
		}
		
		if("delete_rs".equals(method)) {
			String rs_id = request.getParameter("rs_id");
			Rest_TableService rest_TableService = new Rest_TableService();
			rest_TableService.deleteRest(rs_id);
			
			response.sendRedirect(request.getContextPath()+"/back-end/rest_table/back_CompyRest.jsp");
		}
	}
}
