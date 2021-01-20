<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後臺訂單功能資訊</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/back-use/order_table_use/style.css">
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-3.5.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=request.getContextPath()%>/js/select_page_use/main.js"></script>
</head>
<body>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div>
	
			<h3><a
				href="<%=request.getContextPath()%>/back-end/order_table/select_page.jsp">
				<center><img src="<%=request.getContextPath()%>/images/logo.jpg" style="width:25%; margin:auto;"></center>
			</a></h3>


<!-- 			<h3>新增消費者訂單明細(測試用)</h3> -->
<%-- 			<li><a href='<%=request.getContextPath()%>/back-end/order_table/back_addOrder_Table.jsp'>Add</a> a new Order_Table.</li> --%>
			
<!-- 			<h3>查詢所有消費者訂單明細</h3> -->
			<div style="text-align:center;">
			<b><a href='<%=request.getContextPath()%>/back-end/order_table/back_listAllOrder_Table.jsp'>查詢所有消費者訂單明細</a> 
				</b></div>
			<div style="text-align:center;">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/order_table/order_table.do">				
				<b>查詢單筆消費者訂單明細(輸入訂單流水號) :</b> <input type="text" name="order_id" size=6>
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
			
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/order_table/back_listAllRs_id.jsp" >
				<b>查詢餐廳全部的訂單資訊(輸入餐廳流水號) :</b> <input type="text" name="rs_id" size=6>
				<input type="hidden" name="action" value="getOne_For_Display1">
				<input type="submit" value="送出">
			</FORM>
			
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/order_table/back_listAllGs_email.jsp">
				<b>查詢消費者所有訂單資訊(輸入消費者信箱) :</b> <input type="text" name="gs_email" size=6>
				<input type="hidden" name="action" value="getOne_For_Display2">
				<input type="submit" value="送出">
			</FORM>
			</div>>
			<jsp:useBean id="order_tableSvc" scope="page"
				class="com.order_table.model.Order_TableService" />
</body>
</html>