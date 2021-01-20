<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<a href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">
			<img src="<%=request.getContextPath()%>/images/logo2.png" style="height: 65px;" title="訂食你首頁" alt="訂食你首頁">
		</a>
		<ul>
			<c:choose>
				<c:when test="${authority_compy==null}">
					<li><a
						href="<%=request.getContextPath()%>/front-end/compy_table/front_addCompyRegister.jsp">業者-點擊我註冊</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyLogin.jsp">業者-點我登入</a></li>
				</c:when>
				<c:when test="${authority_compy!=null}">
					<c:if test="${authority_compy.authority==2}">
						<li>您好,${authority_compy.cp_name}</li>
						<li><a
							href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp?display=1">業者會員平台</a></li>
						<li><a
							href="<%=request.getContextPath()%>/compy_table/IndexServlet">登出</a></li>
					</c:if>
					<c:if test="${authority_compy.authority==11}">
						<li>您好,${authority_compy.cp_name}</li>
						<li><a
							href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp?display=1">業者會員平台</a></li>
						<li><a
							href="<%=request.getContextPath()%>/compy_table/IndexServlet">登出</a></li>
					</c:if>
					<c:if test="${authority_compy.authority==12}">
						<li>您好,${authority_compy.cp_name}</li>
						<li><a
							href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp?display=1">業者會員平台</a></li>
						<li><a
							href="<%=request.getContextPath()%>/compy_table/IndexServlet">登出</a></li>
					</c:if>
				</c:when>
			</c:choose>
		</ul>
	</div>
</body>
</html>