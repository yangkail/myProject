package com.rest_table.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rest_table.model.Rest_TableService;
import com.rest_table.model.Rest_TableVO;

@WebServlet("/rest_table/Rest_TableServlet_search")
public class Rest_TableServlet_search extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		
		String method=request.getParameter("method");
		
		
		if("search_rest".equals(method)) {
			Rest_TableService rest_TableService=new Rest_TableService();
			Map<String , String[]> map=request.getParameterMap();
			List<Rest_TableVO> list=rest_TableService.getAllRestForSomeCondition(map);
			request.setAttribute("list", list);

			String url = "/front-end/search/search_rest.jsp";
			RequestDispatcher view = request.getRequestDispatcher(url);
			view.forward(request, response);
		}
	}
}
