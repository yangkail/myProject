package com.order_table.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
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

import com.order_table.model.Order_TableDAO;
import com.order_table.model.Order_TableJDBCDAO;
import com.order_table.model.Order_TableService;
import com.order_table.model.Order_TableVO;

import com.google.gson.Gson;

@WebServlet("/order_table/Order_TableServlet_front")
@MultipartConfig
public class Order_TableServlet_front extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if ("getOne_Order_id_Display".equals(method)) {
			Order_TableJDBCDAO dao = new Order_TableJDBCDAO();
			HttpSession session =request.getSession();
			
//			String order_id=(String) session.getAttribute("order_id");
			String order_id="OD00101";
			
			List<Order_TableVO> list = dao.getAllOrder_id(order_id);
			
			session.setAttribute("list", list);    
			// Send the Success view
			String url = "/front-end/order_table/listOneOrder_id.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}	
			
		
		if ("update2".equals(method)) { // 來自update_order_table_input.jsp的請求
				String order_id = new String(request.getParameter("order_id").trim());
				
				PrintWriter out = response.getWriter(); //打印圖片、送出JSON
				Gson gson = new Gson();
				Map<String, String> msgs = new HashMap();//傳訊息用
				
				Order_TableService order_tableSvc = new Order_TableService();
				
				
				if(order_tableSvc.update2(order_id)) {
					msgs.put("update_ok","success");
					out.write(gson.toJson(msgs));//MAP轉GSON
				}else {
					msgs.put("update_fail","failure");
					out.write(gson.toJson(msgs));
				}
				
		}
			
		
		if ("update0".equals(method)) { // 來自update_order_table_input.jsp的請求
			String order_id = new String(request.getParameter("order_id").trim());
			
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			Map<String, String> msgs = new HashMap();
			
			Order_TableService order_tableSvc = new Order_TableService();
			
			
			if(order_tableSvc.update0(order_id)) {
				msgs.put("update_ok","success");
				out.write(gson.toJson(msgs));
			}else {
				msgs.put("update_fail","failure");
				out.write(gson.toJson(msgs));
			}
			
	}
		
		
//			Order_TableDAO dao = new Order_TableDAO();
//			List<Order_TableVO> list = dao.getAll();
//
//			HttpSession session =request.getSession();
//			session.setAttribute("list", list);    
//			// Send the Success view
//			String url = "/front-end/order_table/order_page.jsp";
//			RequestDispatcher successView = request.getRequestDispatcher(url);
//			successView.forward(request, response);
//			return;
//		}
//		
//		
	}
}	

