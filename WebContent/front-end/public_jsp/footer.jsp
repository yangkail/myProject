<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.compy_table.model.Compy_TableService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>業者會員管理平台-會員資訊</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/footer.css">

</head>
<body>
	<c:if test="${userName!=null}">
		<jsp:include page="/front-end/compy_user_chat/compy_user_chat.jsp"></jsp:include>
	</c:if>
</body>
</html>