<%@ page
	import="java.util.*, java.text.DateFormat,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.point_table.model.*, java.text.DateFormat,java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	//   Point_Order_TableVO point_order_tableVO = (Point_Order_TableVO) request.getAttribute("point_order_tableVO"); // Order_TableServlet.java(Concroller), 存入req的order_tableVO物件
%>

<%--  <%System.out.println(order_tableVO);%> --%>

<%
	String gs_email = (String) session.getAttribute("gs_email");
	Point_TableService point_tableSvc = new Point_TableService();
	List<Point_Order_TableVO> list = point_tableSvc.getAllPoint(gs_email); //正式時打開使用
//	List<Point_Order_TableVO> list = point_tableSvc.getAllPoint("Beta@gmail.com");//測試用
	pageContext.setAttribute("list", list);
%>





<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員積分表</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front-use/order_table_use/order_page_use.css">
<style>
</style>

</head>
<body>
	<!-- 		<div class="btn_1"> -->
	<%-- 			<a href="<%= request.getContextPath()%>/front-end/order_table/RS_all_order_page.jsp">	 --%>
	<!-- 			<input type="button"  value="全部訂單" name="按鈕名稱"></a> -->
	<!-- 		</div> -->

	<!-- 		<div class="btn_2"> -->
	<%-- 			<a href="<%= request.getContextPath()%>/front-end/order_table/RS_complete_order_page.jsp"> --%>
	<!-- 			<input type="button" value="已成立訂單" name="按鈕名稱" ></a> -->
	<!-- 		</div> -->

	<!-- 		<div class="btn_3"> -->
	<%-- 			<a href="<%= request.getContextPath()%>/front-end/order_table/RS_cancel_order_page.jsp"> --%>
	<!-- 			<input type="button" value="已取消訂單" name="按鈕名稱" ></a> -->
	<%-- <%-- 			?order_status=${order_tableVO.order_status} --%>
	<!-- 		</div>	 -->


	<table id="type">
		<tr>
			<!-- 			    <th>積分流水號</th> -->
			<!-- 				<th>消費者信箱</th> -->
			<th>餐廳編號</th>
			<th>訂單編號</th>
			<th>訂單新增積分</th>
			<th>訂單使用積分</th>
			<th>訂單成立時間</th>
			<th>積分更新時間</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="point_order_tableVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
			<tr>
				<td>${point_order_tableVO.rs_id}</td>
				<td>${point_order_tableVO.order_id}</td>
				<%-- 					<td>${point_order_tableVO.point_sieral}</td> --%>
				<%-- 					<td>${point_order_tableVO.gs_email}</td> --%>
				<td>${point_order_tableVO.order_get_point}</td>
				<td>${point_order_tableVO.order_use_point}</td>
				<td><fmt:formatDate value="${point_order_tableVO.order_time}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td><fmt:formatDate
						value="${point_order_tableVO.point_update_time}"
						pattern="yyyy-MM-dd HH:mm" /></td>
			</tr>
		</c:forEach>
	</table>
		
		<%@ include file="pages/page2.file"%>

	<script
		src="<%=request.getContextPath()%>/js/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/front-use/front_all_ordrer_page.js"></script>
</body>
</html>