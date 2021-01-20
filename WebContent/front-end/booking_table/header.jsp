<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.compy_table.model.Compy_TableService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Compy_TableVO compy_TableVO = new Compy_TableVO();
	if (session.getAttribute("authority") == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/compy_table/front_CompyLogin.jsp");
	} else {
		Map<String, String> map = (Map) session.getAttribute("authority");
		if (!"2".equals(map.get("authority"))) {
			response.sendRedirect(request.getContextPath() + "/front-end/compy_table/front_CompyLogin.jsp");
		} else {
			Compy_TableService compy_TableService = new Compy_TableService();
			compy_TableVO = compy_TableService.findByPrimaryKey(map.get("cp_contact_email"));
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>業者會員管理平台-會員資訊</title>
<link rel="stylesheet" ref="<%=request.getContextPath()%>/css/front-use/index_use/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/header.css">

</head>
<body>
	<div class="displayTopPlane">
		<a href="<%=request.getContextPath()%>/front-end/compy_table/fakeIndex.jsp">
			<img src="<%=request.getContextPath()%>/images/logo.jpg" height="90px" title="訂食你首頁" alt="訂食你首頁">
		</a>
		<ul>
			<c:choose>
				<c:when test="${authority.authority==2}">
					<li>您好,${authority.cp_name}</li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp">業者會員平台</a></li>
					<li><a
						href="<%=request.getContextPath()%>/compy_table/IndexServlet">登出</a></li>
				</c:when>
				<c:when test="${authority.authority==null}">
					<li><a
						href="<%=request.getContextPath()%>/front-end/compy_table/front_addCompyRegister.jsp">業者-點擊我註冊</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyLogin.jsp">業者-點我登入</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>
	<div class="search">
			<input type="text" class="search_input">
			<button type="submit">查詢</button>
			<br>
			<ul>
				<li>
					關鍵字 :
				</li>
				<li>
					<a href="#">日式/</a>
				</li>
				<li>
					<a href="#">台式/</a>
				</li>
				<li>
					<a href="#">西式/</a>
				</li>
			</ul>
	</div>
</body>
</html>