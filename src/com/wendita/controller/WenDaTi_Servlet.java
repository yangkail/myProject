package com.wendita.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rest_table.model.Rest_TableService;
import com.rest_table.model.Rest_TableVO;

@WebServlet(urlPatterns = { "/WenDaTi_Servler.do" })
public class WenDaTi_Servlet  extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String method = req.getParameter("method");
	    if("get_the_pic".equals(method)) {
			String rs_id=req.getParameter("id");
			System.out.println("get_the_pic");
			Rest_TableService rest_TableService=new Rest_TableService();
			Rest_TableVO rest_TableVO=rest_TableService.getOneAllOnlyPic(rs_id);
			OutputStream out=res.getOutputStream();
			String which=req.getParameter("which");
			
			switch (which) {
				case "rs_bigpic":
					res.setContentLength(rest_TableVO.getRs_big_pic().length);
					out.write(rest_TableVO.getRs_big_pic());
					break;
				case "rs_pic":
					res.setContentLength(rest_TableVO.getRs_pic().length);
					out.write(rest_TableVO.getRs_pic());
					break;
				case "rs_view1":
					res.setContentLength(rest_TableVO.getRs_view1().length);
					out.write(rest_TableVO.getRs_view1());
					break;
				case "rs_view2":
					res.setContentLength(rest_TableVO.getRs_view2().length);
					out.write(rest_TableVO.getRs_view2());
					break;
				case "rs_view3":
					res.setContentLength(rest_TableVO.getRs_view3().length);
					out.write(rest_TableVO.getRs_view3());
					break;
				case "rs_view4":
					res.setContentLength(rest_TableVO.getRs_view4().length);
					out.write(rest_TableVO.getRs_view4());
					break;
				case "rs_view5":
					res.setContentLength(rest_TableVO.getRs_view5().length);
					out.write(rest_TableVO.getRs_view5());
					break;
			}
		}
		
	}
}
