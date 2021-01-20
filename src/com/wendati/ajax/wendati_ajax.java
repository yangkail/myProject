package com.wendati.ajax;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.websocket.wendati.websocket_WenDaTi_server;
import com.wendati.jedis.JedisWenDaTi;

@WebServlet(urlPatterns = { "/WenDaTi_Ajax.do" })
public class wendati_ajax extends HttpServlet {
	Gson gson = new Gson();
	websocket_WenDaTi_server  websocket_WenDaTi_server= new  websocket_WenDaTi_server();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
//		if ("path".equals(action)) {
//			getContextPath(req, res);
//		} else {

			System.out.println("WenDaTi_Ajax.do");
			try {
				JsonObject rest_Tablejson = JedisWenDaTi.random_get_rs_id();
//				Session session = null;
//				websocket_WenDaTi_server.wenDaTi(session,"xxx");
				out.write(rest_Tablejson.toString());
				out.flush();
				out.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
//		}
	}

//	public void getContextPath(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		try {
//			String path = req.getContextPath();
//			PrintWriter out = res.getWriter();
//			JSONObject obj = new JSONObject();
//			obj.put("path", path);
//			System.out.println(obj.toString());
//			out.write(obj.toString());
//			out.flush();
//			out.close();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
