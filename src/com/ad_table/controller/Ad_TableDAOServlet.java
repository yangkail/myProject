package com.ad_table.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ad_table.model.Ad_TableService;
import com.ad_table.model.Ad_TableVO;

@WebServlet("/controller/Ad_TableDAOServlet")
@MultipartConfig
public class Ad_TableDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		/*System.out.println("=====");
		Enumeration<String> requestAttributes = request.getAttributeNames();
        while (requestAttributes.hasMoreElements()) {
            String attributeName = (String) requestAttributes.nextElement();
            System.out.println("Request Attribute Name: " + attributeName 
                            + ", Value - " + (request.getAttribute(attributeName)).toString());
        }*/
		
		String action = request.getParameter("action");
		//廣告編號查詢
		if ("getOne_For_Display".equals(action))

		{

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 請求參數,輸入格式錯誤處理
				String str = request.getParameter("ad_serial");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelect_page.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}

				String ad_serial = null;
				try {
					ad_serial = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelect_page.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}
                  //查詢資料
				Ad_TableService ad_tableSvc = new Ad_TableService();
				Ad_TableVO ad_TableVO = ad_tableSvc.getOneAd(ad_serial);
				if (ad_TableVO == null) {
					errorMsgs.add("沒有此資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelect_page.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}
				request.setAttribute("ad_TableVO", ad_TableVO);
				String url ="/back-end/Ad_Table/Ad_TableList_One.jsp";
						//"/Ad_TableUpdate_Input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelect_page.jsp");
				failureView.forward(request, response);
			}

		}
		//餐廳編號查詢
		if ("getOne_For_Display1".equals(action))

		{

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 請求參數,輸入格式錯誤處理
				String str = request.getParameter("rs_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelectRsId.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}

				String rs_id = null;
				try {
					rs_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("餐廳編號錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelectRsId.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}
                  //查詢資料
				Ad_TableService ad_tableSvc = new Ad_TableService();
				Ad_TableVO ad_TableVO = ad_tableSvc.getOneRs(rs_id);
				if (ad_TableVO == null) {
					errorMsgs.add("沒有此資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelectRsId.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}
				request.setAttribute("ad_TableVO", ad_TableVO);
				String url ="/back-end/Ad_Table/Ad_TableList_One.jsp";
						//"/Ad_TableUpdate_Input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableSelectRsId.jsp");
				failureView.forward(request, response);
			}

		}
		
		
		if ("getOne_For_Update".equals(action)) {// 來自Ad_TableList_All.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接受請求參數
				String ad_serial = new String(request.getParameter("ad_serial"));
				System.out.println(ad_serial);
				// 開始查詢
				Ad_TableService ad_tableSvc = new Ad_TableService();
				Ad_TableVO ad_TableVO = ad_tableSvc.getOneAd(ad_serial);

//                if(ad_TableVO==null) {
//                	System.out.println("null");
//                }else {
//                	System.out.println("ok");
//                }
				// 查詢完成,準備轉交
				request.setAttribute("ad_TableVO", ad_TableVO);
				String url = "/back-end/Ad_Table/Ad_TableUpdate_Input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
//				return;

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableList_All.jsp");
				failureView.forward(request, response);
			}
		}
//修改
		if ("update".equals(action)) {// 來自Ad_TableUpdate_Input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {

				String ad_serial = new String(request.getParameter("ad_serial").trim());

				Integer ad_top_yn = null;
				try {
					ad_top_yn = new Integer(request.getParameter("ad_top_yn").trim());
				} catch (NumberFormatException e) {
//					ad_top_yn = 1;
					errorMsgs.add("廣告是否至頂請選擇");
				}

//
				String rs_id = new String(request.getParameter("rs_id").trim());

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
//					ad_top_time1 = 0;
					errorMsgs.add("是否購買廣告時段1");
				}

				Integer ad_top_time2 = null;
				try {
					System.out.println(request.getParameter("ad_top_time2"));
					ad_top_time2 = new Integer(request.getParameter("ad_top_time2").trim());
				} catch (NumberFormatException e) {
//					ad_top_time2 = 0;
					errorMsgs.add("是否購買廣告時段2");
				}

				Integer ad_top_time3 = null;
				try {
					ad_top_time3 = new Integer(request.getParameter("ad_top_time3").trim());
				} catch (NumberFormatException e) {
//					ad_top_time3 = 0;
					errorMsgs.add("是否購買廣告時段3");
				}

				java.sql.Date ad_time = null;
				try {
					ad_time = java.sql.Date.valueOf(request.getParameter("ad_time").trim());
				} catch (IllegalArgumentException e) {
					ad_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告成立日期");
				}

				java.sql.Date ad_showtime = null;
				try {
					ad_showtime = java.sql.Date.valueOf(request.getParameter("ad_showtime").trim());
				} catch (IllegalArgumentException e) {
					ad_showtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告到期日期");
				}

				java.sql.Date ad_cancel_time = null;
				try {
					ad_cancel_time = java.sql.Date.valueOf(request.getParameter("ad_cancel_time").trim());
				} catch (IllegalArgumentException e) {
					ad_cancel_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告取消日期");
				}
				Integer ad_status = null;
				try {
					ad_status = new Integer(request.getParameter("ad_status").trim());
				} catch (NumberFormatException e) {
//					ad_status = 0;
					errorMsgs.add("廣告狀態請選擇");
				}

				byte[] ad_pic_queue = null;
				Ad_TableService ad_tableSvc = new Ad_TableService();

				Part part = request.getPart("ad_pic_queue");
                if(part==null|| part.getSize()==0) {
                	errorMsgs.add("請上傳圖片");
                }else {
                	ad_pic_queue= getAd_pic_queue(part);
                }
//                	
//				InputStream ins = part.getInputStream();
//				if (ins.available() != 0) {
//					ad_pic_queue = new byte[ins.available()];
//					ins.read(ad_pic_queue);
//					ins.close();
//				}

//				String rs_id = new String(request.getParameter("rs_id").trim());

				Ad_TableVO ad_TableVO = new Ad_TableVO();

				ad_TableVO.setAd_serial(ad_serial);
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
					request.setAttribute("ad_TableVO", ad_TableVO);//輸入格式錯誤的VO,也存入 request
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableUpdate_Input.jsp");
					failureView.forward(request, response);
					return;// 中斷
				}
				// 修改資料
//				Ad_TableService ad_tableSvc = new Ad_TableService();
				ad_TableVO = ad_tableSvc.updateAd_Table(ad_serial, ad_top_yn, rs_id, ad_price, ad_top_time1,
						ad_top_time2, ad_top_time3, ad_time, ad_showtime, ad_cancel_time, ad_status, ad_pic_queue);
				// 資料庫update成功後,正確的ad_TableVO存入 request
				request.setAttribute("ad_TableVO", ad_TableVO);
				String url ="/back-end/Ad_Table/Ad_TableList_One.jsp";
						//"/Ad_TableList_All.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("修改失敗" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableUpdate_Input.jsp");
				failureView.forward(request, response);
			}
		}
//新增
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {

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
					//System.out.println(request.getParameter("ad_top_time2"));
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
					ad_time = java.sql.Date.valueOf(request.getParameter("ad_time").trim());
				} catch (IllegalArgumentException e) {
					ad_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告成立日期");
				}

				java.sql.Date ad_showtime = null;
				try {
					ad_showtime = java.sql.Date.valueOf(request.getParameter("ad_showtime").trim());
				} catch (IllegalArgumentException e) {
					ad_showtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告到期日期");
				}

				java.sql.Date ad_cancel_time = null;
				try {
					ad_cancel_time = java.sql.Date.valueOf(request.getParameter("ad_cancel_time").trim());
				} catch (IllegalArgumentException e) {
					ad_cancel_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告取消日期");
				}
				Integer ad_status = null;
				try {
					ad_status = new Integer(request.getParameter("ad_status").trim());
				} catch (NumberFormatException e) {
//					ad_status = 1;
					errorMsgs.add("請選擇廣告狀態");
				}
				Ad_TableService ad_tableSvc = new Ad_TableService();
				byte[]ad_pic_queue=null;
				Part part = request.getPart("ad_pic_queue");
                if(part==null|| part.getSize()==0) {
                	errorMsgs.add("請上傳圖片");
                }else {
                	ad_pic_queue= getAd_pic_queue(part);
                }
//				byte[] data = null;
//				Part part = request.getPart("ad_pic_queue");
//				InputStream ins = part.getInputStream();
//				if (ins.available() != 0) {
//					data = new byte[ins.available()];
//					ins.read(data);
//					ins.close();
//				}
//				 else {
//				 data = ad_tableSvc.getOneAd(ad_serial).ad_pic_queue();
//				 }

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
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableAdd.jsp");
					failureView.forward(request, response);
					return;
				}
				// 新增資料
//				Ad_TableService ad_tableSvc = new Ad_TableService();
				ad_TableVO = ad_tableSvc.addAd_Table(ad_top_yn, rs_id, ad_price, ad_top_time1, ad_top_time2,
						ad_top_time3, ad_time, ad_showtime, ad_cancel_time, ad_status, ad_pic_queue);
				// 新增資料,轉交successView
				String url = "/back-end/Ad_Table/Ad_TableList_All.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableAdd.jsp");
				failureView.forward(request, response);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {

				String ad_serial = new String(request.getParameter("ad_serial"));

				Ad_TableService ad_tableSvc = new Ad_TableService();
				ad_tableSvc.deleteAd_Table(ad_serial);

				String url = "/back-end/Ad_Table/Ad_TableList_All.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Ad_Table/Ad_TableList_All.jsp");
				failureView.forward(request, response);
			}
		}
	}

//	public static byte[] getAd_pic_queue(String string) throws IOException {
//		FileInputStream fis = new FileInputStream(string);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}
	public byte[]  getAd_pic_queue(Part part) {
		InputStream in;
		ByteArrayOutputStream bos =null;
		try {
			in = part.getInputStream();
			 bos = new ByteArrayOutputStream();
			 byte[] b = new byte[1024*1000];
			 while(in.read(b)!=-1) {
				 bos.write(b);
			 }
		}catch(IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	

}