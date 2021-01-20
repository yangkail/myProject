package com.ad_table.controller.frontend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ad_table.model.Ad_TableService;
import com.ad_table.model.Ad_TableVO;

@WebServlet("/controller/Ad_TableDAOServletFront_End")
@MultipartConfig
public class Ad_TableServletFront_End extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

//			try {

			Integer ad_top_yn = null;
			try {
				ad_top_yn = new Integer(request.getParameter("ad_top_yn").trim());
			} catch (NumberFormatException e) {
//					ad_top_yn = 0;
				errorMsgs.add("廣告是否至頂請選擇");
			}

			Integer ad_price = null;
			try {
				ad_price = new Integer(request.getParameter("ad_price").trim());
			} catch (NumberFormatException e) {
//					ad_price = 0;
				errorMsgs.add("廣告時段費用請選擇");
			}

			Integer ad_top_time1 = null;
			try {
				ad_top_time1 = new Integer(request.getParameter("ad_top_time1").trim());
			} catch (NumberFormatException e) {
//					ad_top_time1 = 3;
				errorMsgs.add("是否購買廣告時段1請選擇");
			}

			Integer ad_top_time2 = null;
			try {
				// System.out.println(request.getParameter("ad_top_time2"));
				ad_top_time2 = new Integer(request.getParameter("ad_top_time2").trim());
			} catch (NumberFormatException e) {
//					ad_top_time2 = 3;
				errorMsgs.add("是否購買廣告時段2請選擇");
			}

			Integer ad_top_time3 = null;
			try {
				ad_top_time3 = new Integer(request.getParameter("ad_top_time3").trim());
			} catch (NumberFormatException e) {
//					ad_top_time3 =3;
				errorMsgs.add("是否購買廣告時段3請選擇");
			}

			java.sql.Date ad_time = null;
			try {
				ad_time = new java.sql.Date(System.currentTimeMillis());
//					ad_time = java.sql.Date.valueOf(request.getParameter("ad_time").trim());

			} catch (IllegalArgumentException e) {
				ad_time = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入廣告成立日期");
			}

			java.sql.Date ad_showtime = null;
			try {
				ad_showtime = new java.sql.Date(System.currentTimeMillis());
//					ad_showtime = java.sql.Date.valueOf(request.getParameter("ad_showtime").trim());
			} catch (IllegalArgumentException e) {
				ad_showtime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入廣告到期日期");
			}

			java.sql.Date ad_cancel_time = null;
//				try {
//					ad_cancel_time = java.sql.Date.valueOf(request.getParameter("ad_cancel_time").trim());
//				} catch (IllegalArgumentException e) {
//					ad_cancel_time = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入廣告取消日期");
//				}
			Integer ad_status = null;
			try {
				ad_status = new Integer(request.getParameter("ad_status").trim());
			} catch (NumberFormatException e) {
//					ad_status = 1;
				errorMsgs.add("請選擇廣告狀態");
			}
			Ad_TableService ad_tableSvc = new Ad_TableService();
			byte[] ad_pic_queue = null;
			Part part = request.getPart("ad_pic_queue");
			if (part == null || part.getSize() == 0) {
				errorMsgs.add("請上傳圖片");
			} else {
				ad_pic_queue = getAd_pic_queue(part);
			}

			String rs_id = new String(request.getParameter("rs_id").trim());

			Ad_TableVO ad_TableVO = new Ad_TableVO();
			ad_TableVO.setAd_top_yn(ad_top_yn);
			ad_TableVO.setRs_id(rs_id);
			ad_TableVO.setAd_price(ad_price);
			ad_TableVO.setAd_top_time1(ad_top_time1);
			ad_TableVO.setAd_top_time2(ad_top_time2);
			ad_TableVO.setAd_top_time3(ad_top_time3);
			ad_TableVO.setAd_time(ad_time);
			ad_TableVO.setAd_showtime(ad_showtime);
			ad_TableVO.setAd_cancel_time(ad_cancel_time);
			ad_TableVO.setAd_status(ad_status);
			ad_TableVO.setAd_pic_queue(ad_pic_queue);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("ad_TableVO", ad_TableVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Ad_TableSelectFront.jsp");
				failureView.forward(request, response);
				return;
			}
			// 新增資料
//				Ad_TableService ad_tableSvc = new Ad_TableService();
			ad_TableVO = ad_tableSvc.addAd_Table(ad_top_yn, rs_id, ad_price, ad_top_time1, ad_top_time2, ad_top_time3,
					ad_time, ad_showtime, ad_cancel_time, ad_status, ad_pic_queue);
			// 新增資料,轉交successView
			request.setAttribute("ad_TableVO", ad_TableVO);
			HttpSession session = request.getSession();
			session.setAttribute("ad_price", ad_price);
			String url = "/front-end/ad_table/Ad_TableAddFrontEndSuccess.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Ad_TableSelectFront.jsp");
//				failureView.forward(request, response);
//			}
		}
//		if ("insert1".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			request.setAttribute("errorMsgs", errorMsgs);
//	
//			String url = "/front-end/Ad_PaySucces.jsp";
//			RequestDispatcher successView = request.getRequestDispatcher(url);
//			successView.forward(request, response);
//		}
	}

	public byte[] getAd_pic_queue(Part part) {
		InputStream in;
		ByteArrayOutputStream bos = null;
		try {
			in = part.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024 * 1000];
			while (in.read(b) != -1) {
				bos.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

}
