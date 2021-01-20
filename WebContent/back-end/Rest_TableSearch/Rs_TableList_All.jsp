<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest_table.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Rest_TableService rest_tableSvc = new Rest_TableService();
    List<Rest_TableVO> list = rest_tableSvc.getRsName("");
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="ad_tableSvc" scope="page" class="com.ad_table.model.Ad_TableService" /> --%>

<html>
<head>
<title>所有餐廳資料 - Rs_TableList_All.jsp</title>

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

</head>
<body bgcolor='orange'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有餐廳資料 - Rs_TableList_All.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/Rest_TableSearch/Rs_TableSelect_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>餐廳編號</th>
		<th>餐廳名字</th>
		<th>餐廳地址</th>
		<th>餐廳電話</th>
		<th>餐廳介紹</th>
		<th>餐廳類別</th>
		<th>餐廳大頭照</th>
		<th>營業時間</th>
	
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="rest_TableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rest_TableVO.rsid}</td>
			<td>${rest_TableVO.rs_name}</td>
			<td>${rest_TableVO.rs_address}</td>
			<td>${rest_TableVO.rs_phone}</td>
			<td>${rest_TableVO.rs_intro}</td>
			<td>${rest_TableVO.rs_type}</td>
			<td>${rest_TableVO.rs_big_pic}</td>
			<td>${rest_TableVO.rs_open_time}</td>
	


	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>