<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.compy_table.model.Compy_TableService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Compy_TableVO compy_TableVO=new Compy_TableVO();
Compy_TableService compy_TableService=new Compy_TableService();
Map<String,String> map=(Map)session.getAttribute("authority_compy");
compy_TableVO=compy_TableService.findByPrimaryKey(map.get("cp_contact_email"));

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>業者會員管理平台</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/footer.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/members_side.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/rest_table_use/front_CompyAddRest_info.css">
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
	
</head>
<body>
	<!-- HEADER -->
	<div class="topBackplane">
		<jsp:include page="/front-end/public_jsp/header.jsp"></jsp:include>
	</div>
	<!-- HEADER -->
	
	<!-- SIDE DIV -->
	<div class="all_div">
    	<jsp:include page="/front-end/public_jsp/member_side.jsp"></jsp:include>
    </div>
    <!-- SIDE DIV -->

    <div class="info_div">
    	<div>
    		<h1 style="font-size:40px;">業者訂單資訊-已取消訂單</h1>
                <hr>
        </div>
        <div class="seat_div">
        <!-- 放這裡 -->
        	<jsp:include page="/front-end/order_table/RS_cancel_order_page.jsp"></jsp:include>
    	</div>
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
</body>
</html>