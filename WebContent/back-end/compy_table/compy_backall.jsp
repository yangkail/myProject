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
		<div>
		<a
			href="<%=request.getContextPath()%>/back-end/rest_table/rest_backall.jsp">
			<center><img src="<%=request.getContextPath()%>/images/logo.jpg" style="width: 25%; margin: auto;"></center>
		</a></div>


	<div style="text-align: center;">
		<b><h3>
				<a
					href='<%=request.getContextPath()%>/back-end/compy_table/back_selectCompy.jsp'>已驗證業者查詢</a>
			</h3> </b>
	</div>

	<br>

	<div style="text-align: center;">
		<b><h3>
				<a
					href='<%=request.getContextPath()%>/back-end/compy_table/back_verifyCompy.jsp'>未驗證業者查詢</a>
			</h3> </b>
	</div>

	<br>

	