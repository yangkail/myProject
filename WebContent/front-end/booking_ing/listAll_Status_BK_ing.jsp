<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.booking_ing_table.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Booking_Ing_TableService booking_Ing_TableSvc = new Booking_Ing_TableService();
	String rs_status = request.getParameter("rs_status");

	List<Booking_Ing_TableVO> list = null;
	list = booking_Ing_TableSvc.getAll_Status(Integer.parseInt(rs_status));
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="booking_Fixed_TableSvc" scope="page"
	class="com.booking_fixed_table.model.Booking_Fixed_TableService" />
<jsp:useBean id="booking_Fixed_TableVO" scope="page"
	class="com.booking_fixed_table.model.Booking_Fixed_TableVO" />
	
<html>
<head>
<title>餐廳訂位資訊(Booking_Ing_Table)- listAllBK_ing.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index.css" />
</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有餐廳訂位資訊 - listAllBK_ing.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front_end/booking_ing/select_pageBK_ing.jsp">
						<img src="./images/back1.gif" width="100" height="32" border="0">回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>餐廳訂單流水號</th>
			<th>餐廳座位狀態</th>
			<th>餐廳座位流水號</th>
			<th>餐廳公版流水號</th>
			<th>餐廳桌號</th>
			<th>桌號人數</th>
			<th>消費者用餐時段</th>
			<th>修改</th>
			<th>修改訂位</th>

		</tr>
		<%@ include file="pages/page1_allstatus.file"%>
		<c:forEach var="booking_Ing_TableVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${booking_Ing_TableVO.order_id}</td>

				<td>${(booking_Ing_TableVO.rs_status=='0')? '未訂位':'已訂位'}(${booking_Ing_TableVO.rs_status})</td>
				<td>
					${booking_Fixed_TableSvc.getOneBooking_Fixed_Table(booking_Ing_TableVO.rs_sieral).rs_seat_sieral}
				</td>
				<td>${booking_Ing_TableVO.rs_sieral}</td>
				<td>
					${booking_Fixed_TableSvc.getOneBooking_Fixed_Table(booking_Ing_TableVO.rs_sieral).rs_table_number}
				</td>
				<td>
					${booking_Fixed_TableSvc.getOneBooking_Fixed_Table(booking_Ing_TableVO.rs_sieral).rs_table_seat}
				</td>
				<td>
					${booking_Ing_TableVO.gs_select_time}
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="order_id" value="${booking_Ing_TableVO.order_id}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do"
						style="margin-bottom: 0px;" class="form2">
						<input type="hidden"
						name="order_id" value="${booking_Ing_TableVO.order_id}">
						<input type="hidden"
						name="rs_status" value="1">
						<input type="hidden" name="action" value="update_status">
						<input type="button" class="btn_bk_ing_yes" value="訂位" >
					</FORM>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do"
						style="margin-bottom: 0px;" class="form3">
						<input type="button" value="取消訂位" class="btn_bk_ing_no"> 
						<input type="hidden"
						name="order_id" value="${booking_Ing_TableVO.order_id}">
						<input type="hidden"
						name="rs_status" value="0">
						<input type="hidden" name="action" value="update_status">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2_allstatus.file"%>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
</body>
</html>