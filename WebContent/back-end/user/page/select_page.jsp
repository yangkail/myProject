<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user_table.model.*"%>
<%@ page import="java.util.*"%>

<%-- <% --%>
<%--	User_TableService user_tableSvcc = new User_TableService();--%>
<%-- 	List<User_TableVO> list = user_tableSvcc.getAll();--%>
<%--	User_TableVO user_tableVO = (User_TableVO) request.getAttribute("user_tableVO");--%>
<%-- %> --%>


<html>
<head>
<title>IBM Emp: Home</title>
<%-- <link href="<%=request.getContextPath()%>/css/user-css/user.css" --%>
<!-- 	rel="stylesheet" /> -->


<style>
ul, li {
	list-style: none;
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

div.showall {
	text-align: center;
	/* border: 5px solid yellow; */
	margin: 0px 20px 0px 20px;
	height: 1000px auto;
}
</style>

</head>
<body bgcolor='white'>
	<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
	</div>
	<div class="topBackplane" style="text-align: center;">
		<div class="displayTopPlane"></div>
		<h3>
			<a
				href="<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableSelect_page.jsp">
				<img src="<%=request.getContextPath()%>/images/logo.jpg"
				style="width: 25%; margin: auto;">
			</a>
		</h3>
	</div>
	<div style="margin-top: 0px; margin-bottom: 0px; text-align: center;">
		<div class="title_word">
		<div style="text-align: center;">
				<h3>資料查詢</h3>
				<a
					href='<%=request.getContextPath()%>/back-end/user/page/listAllEmp.jsp'>查詢所有員工</a>
					 <br><br>
				
			</div>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/user/user.do">
						<b>輸入員工編號 (UR00101):</b> <input type="text" name="user_id" size=6>
						<input type="hidden" name="action" value="getOne_For_Display" >
						<input type="submit" value="送出">
					</FORM>
				</div>
				<jsp:useBean id="user_tableSvc" scope="page"
					class="com.user_table.model.User_TableService" />
				<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/user/user.do">
						<b>選擇員工編號:</b> <select size="1" name="user_id">
							<c:forEach var="user_tableVO" items="${user_tableSvc.all}">
								<option value="${user_tableVO.user_id}">${user_tableVO.user_id}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM>
				
				<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/user/user.do">
						<b>選擇員工姓名:</b> <select size="1" name="user_id">
							<c:forEach var="user_tableVO" items="${user_tableSvc.all}">
								<option value="${user_tableVO.user_id}">${user_tableVO.user_account}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM>
				</div>
			
			<br> <br>
			
			<c:choose>
				<c:when test="${authority==1}">
					<div style="text-align: center;">
					<h3>員工管理</h3></div>
						<a
							href='<%=request.getContextPath()%>/back-end/user/page/addUser.jsp'>新增平台管理者</a>					
					</c:when>
				</c:choose>		
		</div>
	</div></div>
	<br>
	<div class="footer">
		<jsp:include page="/css/headfoot/footer.jsp"></jsp:include>
	</div>

</body>
</html>