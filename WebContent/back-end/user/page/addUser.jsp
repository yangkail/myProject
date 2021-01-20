<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user_table.model.*"%>

<%
	User_TableVO user_tableVO = (User_TableVO) request.getAttribute("user_tableVO");
%>
<%=user_tableVO == null%>
<!-- 確認是否為空值 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addEmp.jsp</title>
<link href="<%=request.getContextPath()%>/css/user-css/user.css"
	rel="stylesheet" />

<style>
table#table-1 {
	background-color: orange;
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

div.showall {
	text-align: center;
	/* border: 5px solid yellow; */
	margin: 0px 20px 0px 20px;
	height: 1000px auto;
}
ul, li {
	list-style: none;
}

</style>

</head>
<body bgcolor='white'>
<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="showall"><br>
		<br><h3>新增使用者</h3>
		<br>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">  &emsp;&emsp;&emsp;&emsp;請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/user/user.do" name="form1">

			<table class="logininput">

				<tr>
					<td>員工帳號:</td>
					<td><input type="TEXT" name="user_account" size="25"
						value="<%=(user_tableVO == null) ? "" : user_tableVO.getUser_account()%>" /></td>
				</tr>
				<tr>
					<td>員工密碼:</td>
					<td><input type="TEXT" name="user_password" size="25"
						value="<%=(user_tableVO == null) ? "" : user_tableVO.getUser_password()%>" /></td>
				</tr>
				<tr>
					<td>員工職位:</td>
					<td><input type="TEXT" name="user_job" size="25"
						value="<%=(user_tableVO == null) ? "" : user_tableVO.getUser_job()%>" /></td>
				</tr>

				<jsp:useBean id="user_tableSvc" scope="page"
					class="com.user_table.model.User_TableService" />
				<tr>
					<td>員工部門:<font color=red><b></b></font></td>
					<td><select size="1" style="width: 220px" name="user_depart" >
							<c:forEach var="user_tableVO" items="${user_tableSvc.depart}">
								<option value="${user_tableVO.user_depart}"
									${(user_tableVO.user_depart==user_tableVO.user_depart)? 'selected':'' }>${user_tableVO.user_depart}
							</c:forEach>
					</select></td>
				</tr>

				<!-- 			<tr> -->
				<!-- 				<td>權限:</td> -->
				<!-- 				<td><input type="TEXT" name="authority" size="25" -->
				<%-- 					value="<%=(user_TableVO == null) ? "1" : user_TableVO.getAuthority()%>" /></td> --%>
				<!-- 			</tr> -->
			</table>
			<br>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增"> <br><br>
			<br>

		</FORM>
	</div>
	
	<div class="footer">
		<jsp:include page="/css/headfoot/footer.jsp"></jsp:include>
	</div>
</body>
</html>