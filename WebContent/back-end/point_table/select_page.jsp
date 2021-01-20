<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後臺積分功能資訊</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-use/select_page_use/style.css">
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-3.5.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=request.getContextPath()%>/js/select_page_use/main.js"></script>
<style>
/* table#table-1 { */
/* 	background-color: orange; */
/* 	border: 2px solid black; */
/* 	text-align: center; */
/* } */
table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 111px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 500px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

ul, li {
	list-style: none;
}

.ull.li {
	margin: 0, -10 auto;
}

.font {
	font-weight: bold;
	color: red;
}
</style>
</head>
<body>
<body bgcolor=''>
<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div>

<div style="text-align:center;">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<h3><a
		href="<%=request.getContextPath()%>/back-end/point_table/select_page.jsp">
			<img src="<%=request.getContextPath()%>/images/logo.jpg" style="width:25%; margin:auto;">
</a></h3></div>
<div style="margin-top:20px;margin-bottom:0px; text-align:center;">
    <div class="title_word">
<!--     	<h3>廣告查詢</h3> -->
    </div></div>
    
    <div style="text-align:center;">
			<h3><a href='<%=request.getContextPath()%>/back-end/point_table/back_addPoint_Table.jsp'>新增會員單筆積分</a></h3>
<%-- 			<li><a href='<%=request.getContextPath()%>/back-end/point_table/back_addPoint_Table.jsp'>新增</a></li><br> --%>
			
			<h3><a href='<%=request.getContextPath()%>/back-end/point_table/back_listAllPoint_Table.jsp'>查詢所有積分表格</a></h3>
<%-- 			<li><a href='<%=request.getContextPath()%>/back-end/point_table/back_listAllPoint_Table.jsp'>查詢</a></li><br> --%>
			<br>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/point_table/point_table.do">
				<b>查詢單筆積分表(輸入積分流水號) :</b> <input type="text" name="point_sieral" size=6>
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
			
		</div>	
			<jsp:useBean id="point_tableSvc" scope="page"
				class="com.point_table.model.Point_TableService" />
</body>
</html>