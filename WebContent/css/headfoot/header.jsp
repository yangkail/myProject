<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user_table.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/user-css/user.css"
	rel="stylesheet" />
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" style="color:white;" href="<%=request.getContextPath()%>/back-end/user/page/select_page.jsp">管理者平台</a>



			<button
				class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
				
						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" style="color:black;">${user_account}, 您好</a></li>				
				
						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/Guest_Table/listAllGuest.jsp">消費者管理</a></li>
								
						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/order_table/select_page.jsp">訂單管理</a></li>					
							
						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/compy_table/compy_backall.jsp">公司業者管理</a></li>

						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/rest_table/rest_backall.jsp">餐廳業者管理</a></li>				
	
						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableSelect_page.jsp">廣告管理</a></li>

						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/Feedback_Table/listAllFeedback.jsp">評論管理</a></li>

						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/back-end/point_table/select_page.jsp">積分管理</a></li>

						<li class="nav-item mx-0 mx-lg-1"><a
							class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="<%=request.getContextPath()%>/UserServlet">登出</a></li>
		
				</ul>
			</div>
		</div>
	</nav>
	<jsp:include page="/back-end/user_compy_chat/user_compy_chat.jsp"></jsp:include>
</body>
</html>