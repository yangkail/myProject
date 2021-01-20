<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order_table.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
    Order_TableVO order_tableVO = (Order_TableVO) request.getAttribute("order_tableVO"); // Order_TableServlet.java(Concroller), 存入req的order_tableVO物件 -->
%> 

<%
	String order_id=(String)session.getAttribute("order_id");
   	Order_TableService order_tableSvc = new Order_TableService();
    List<Order_TableVO> list = order_tableSvc.getAllOrder_id(request.getParameter("order_id"));
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>單筆訂單編號查詢</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/select_page_use/style.css">
<style>

  table, th, td {
    border: 1px solid #ffa500;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }

</style>

</head>
<body>	 
	
			<h3><a
				href="<%=request.getContextPath()%>/front-end/order_table/all_order_page.jsp">
				<img src="<%=request.getContextPath()%>/images/logo.jpg">
			</a></h3>
		
		 <h3>單筆訂單編號</h3>
<table>
	<tr>
		<th>訂單流水號</th>
		<th>餐廳編號</th>
		<th>消費者信箱</th>
		<th>訂單狀態</th>
		<th>訂單成立時間</th>
		<th>訂單取消時間</th>
		<th>訂單備註</th>
		<th>用餐人數</th>
		<th>消費者用餐時段</th>
		<th>使用訂金</th>
<!-- 		<th>訂單QR條碼</th> -->
		<th>餐廳座位狀態</th>
	</tr>
	<c:forEach var="order_tableVO" items="${list}">
		<tr>
			<td>${order_tableVO.order_id}</td>
			<td>${order_tableVO.rs_id}</td>
			<td>${order_tableVO.gs_email}</td>
			<td>${order_tableVO.order_status}</td>
			<td><fmt:formatDate value="${order_tableVO.order_time}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td><fmt:formatDate value="${order_tableVO.order_cancel_time}" pattern="yyyy-MM-dd HH:mm"/></td> 
			<td>${order_tableVO.gs_order_remark}</td>
			<td>${order_tableVO.gs_people}</td>
			<td>${order_tableVO.gs_select_time}</td>
			<td>${order_tableVO.order_deposit}</td>
<%-- 			<td><img src= "<%=request.getContextPath()%>/back-end/order_table/order_table.do?action1=display_pic&order_id=${order_tableVO.order_id}" --%>
<!-- 			height="90" width="90" ></td> -->
			<td>${order_tableVO.rs_table_status}</td>	
		</tr>
		</c:forEach>
</table>

</body>
</html>