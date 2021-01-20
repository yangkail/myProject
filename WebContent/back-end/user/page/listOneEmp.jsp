<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.user_table.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
User_TableVO user_tableVO = (User_TableVO) request.getAttribute("user_tableVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
<link href="<%=request.getContextPath()%>/css/user-css/user.css"
	rel="stylesheet" />


<style>
table#table-1 {
	background-color: #f5bc07;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h3 {
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
}
</style>


<style>
table {
	width: 1500px;
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

h3 {
	text-align: center;
}

table {
	width: 700px;

	/*    border-collapse: collapse;  */
	/*  格子各有一條線變成一條 */
}

td {
	border-bottom: 1px solid white;
	padding: 20px;
}

tr:nth-child(1) {
	background-color: orange;
	color: black;
}

tr:nth-child(3) {
	background-color: wheat;
	color: black;
}

tr:nth-child(5) {
	background-color: wheat;
	color: black;
}

tr:nth-child(even) {
	background-color: burlywood;
}

tr:nth-child(6) {
	background-color: orange;
	color: black;
}

.keyword {
	color: brown
}

/* 確認 */
.btn {
	display: inline-block;
	border-radius: 4px;
	background-color: #f4511e;
	border: none;
	color: #FFFFFF;
	text-align: center;
	font-size: 15px;
	padding: 20px;
	width: 100px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 1px;
}

.btn span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.btn span:after {
	content: '»';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.btn:hover span {
	padding-right: 25px;
}

.btn:hover span:after {
	opacity: 1;
	right: 0;
}
/* 取消 */
.btn1 {
	display: inline-block;
	border-radius: 4px;
	background-color: gray;
	border: none;
	color: #FFFFFF;
	text-align: center;
	font-size: 15px;
	padding: 20px;
	width: 100px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 1px;
}

.btn1 span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.btn1 span:after {
	content: '«';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.btn1:hover span {
	padding-right: 25px;
}

.btn1:hover span:after {
	opacity: 1;
	right: 0;
}

.content {
	width: 1200px;
	margin-left: auto;
	margin-right: auto;
}

.box {
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
	</div>
<div style="margin-top:150px;margin-bottom:150px;" class="showall">

<div>
	<tr><td>
		 <h3><%=user_tableVO.getUser_account()%>的員工資料</h3>	
	</td></tr>
</div><br>

	<table class="content"
		style="weight: 200px; height: 120px; border: 3px tomato dashed; margin-top: 20px; margin-bottom: 50px;">
		<tr style="text-align: center;">
		    <th>員工編號</th>
			<th>員工帳號</th>
			<c:choose>
				<c:when test="${authority==1}">
			<th>員工密碼</th>
				</c:when>
			</c:choose>
			<th>部門</th>
			<th>職稱</th>
			<th>代號</th>
			<c:choose>
				<c:when test="${authority==1}">
					<th>修改</th>
					<th>刪除</th>
				</c:when>
			</c:choose>
				
	</tr>
	<tr>
		<td><%=user_tableVO.getUser_id()%></td>
		<td><%=user_tableVO.getUser_account()%></td>
		<c:choose>
			<c:when test="${authority==1}">
		<td><%=user_tableVO.getUser_password()%></td>
			</c:when>
		</c:choose>
		<td><%=user_tableVO.getUser_depart()%></td>
		<td><%=user_tableVO.getUser_job()%></td>
		<td><%=user_tableVO.getAuthority()%></td>
		<c:choose>
					<c:when test="${authority==1}">
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/user/user.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="user_id" value="${user_tableVO.user_id}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/user/user.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden"
									name="user_id" value="${user_tableVO.user_id}"> <input
									type="hidden" name="action" value="delete"><br>
							</FORM>
						</td>
					</c:when>
				</c:choose>
	</tr>
</table>
</div>

</body>
</html>