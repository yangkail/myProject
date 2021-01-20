<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user_table.model.*"%>

<%
	User_TableVO user_tableVO = (User_TableVO) request.getAttribute("user_tableVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%=user_tableVO == null%>--${user_tableVO.user_id}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_user_input.jsp</title>
<link href="<%=request.getContextPath()%>/css/user-css/user.css"
	rel="stylesheet" />

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
	</div>

	<table id="table-1" style="margin-top: 50px; margin-bottom: 100px;"></table>
	
	<div style="text-align: center;">
		<h3>資料修改</h3><br>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
	<div style="text-align: center;">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/user/user.do" name="form1">

<table class="content"
	style="weight: 200px; height: 300px; border: 3px tomato dashed; margin-top: 20px; margin-bottom: 20px; margin: auto ; ">
	<tr>

					<td>員工編號:<font color=red><b>*</b></font></td>
					<td><%=user_tableVO.getUser_id()%></td>
					<%--沒有values代表沒有直可以選擇--%>
				</tr>
				<tr>
					<td>員工帳號:</td>
					<td><input type="TEXT" name="user_account" size="25"
						value="<%=user_tableVO.getUser_account()%>" /></td>
				</tr>
				<tr>
					<td>員工密碼:</td>
					<td><input type="TEXT" name="user_password" size="25"
						value="<%=user_tableVO.getUser_password()%>" /></td>
				</tr>
				<tr>
					<td>員工職位:</td>
					<td><input type="TEXT" name="user_job" size="25"
						value="<%=user_tableVO.getUser_job()%>" /></td>
				</tr>


				<jsp:useBean id="user_tableSvc" scope="page"
					class="com.user_table.model.User_TableService" />
				<tr>
					<td>部門:<font color=red><b>*</b></font></td>
					<td><select size="1" name="user_depart">
							<c:forEach var="user_tableVO" items="${user_tableSvc.depart}">
								<option value="${user_tableVO.user_depart}"
									${(user_tableVO.user_depart==user_tableVO.user_depart)? 'selected':'' }>${user_tableVO.user_depart}
							</c:forEach>
					</select></td>
				<tr>
					<td>權限:</td>
					<td><input type="TEXT" name="authority" size="25"
						value="<%=user_tableVO.getAuthority()%>" /></td>
				</tr>
			</table>



			<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="user_id"
				value="<%=user_tableVO.getUser_id()%>"> <input type="submit"
				value="送出修改"><br>
		</FORM>
		<br>
	</div>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>