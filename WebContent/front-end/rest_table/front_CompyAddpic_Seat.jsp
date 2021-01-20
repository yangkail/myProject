<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Rest_TableVO rest_TableVO=(Rest_TableVO)request.getAttribute("rest_TableVO");
if(session.getAttribute("rs_id")!=null){
	Rest_TableService rest_TableService=new Rest_TableService();
	rest_TableVO=rest_TableService.getOneAll((String)session.getAttribute("rs_id"));
}
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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/rest_table_use/front_CompyAddpic_Seat.css">
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
    		<h1 style="font-size:40px;">餐廳資訊-桌位配置</h1>
                <hr>
        </div>
        <div class="seat_div">
        	<jsp:include page="/front-end/booking_fixed/user/bk_Table_select.jsp"></jsp:include>
    	</div>
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
	<script src="<%=request.getContextPath() %>/js/front_use/rest_table_use/address.js"></script>
	<script src="<%=request.getContextPath() %>/js/front_use/rest_table_use/front_CompyAddpic.js"></script>
</body>
</html>