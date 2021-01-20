<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user_table.model.*"%>

<%
	User_TableVO user_tableVO = (User_TableVO) request.getAttribute("user_tableVO");
%>
<%-- <%=user_tableVO == null%> --%>

<!-- 確認是否為空值 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<style>

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
<body bgcolor='white'>

	<div class="loginheaderer">
		<jsp:include page="userloginheader.jsp"></jsp:include>
	</div>

	<div>
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/user/user.do" name="form1"><br>
			
			<div class="loglog">
				<table class="logininput">

					<c:if test="${not empty errorMsgs}">
						<font class="font">&emsp;!請修正以下錯誤 :</font>
						<ul class="ull">
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<tr>
						<td>員工帳號:</td>
						<td><input type="TEXT" name="user_account" size="25"
							placeholder="請輸入帳號"
							value="<%=(user_tableVO == null) ? "" : user_tableVO.getUser_account()%>" /></td>
					</tr>
					<tr>
						<td>員工密碼:</td>
						<td><input type="password" name="user_password" size="25"
							placeholder="請輸入密碼" value="" /></td>
					</tr>

				</table>

				<br> <input type="hidden" name="action" value="login">
				<input type="submit" value="登入"><br><br>
			</div>
		</FORM>
	</div>
	<div class="footer">
		<jsp:include page="/css/headfoot/footer.jsp"></jsp:include>
	</div>
</body>
</html>