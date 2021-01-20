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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-use/index_use/main.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-use/index_use/footer.css">

</head>
<body>

</body>
</html>