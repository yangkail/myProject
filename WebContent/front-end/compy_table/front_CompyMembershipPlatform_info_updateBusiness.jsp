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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_table_use/front_CompyMembershipPlatform_updateBusiness.css">
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
	<style>
    	div.btn-info{
    		margin-top: .25rem;
    		margin-bottom: .25rem;
        	color: #fff;
    		background-color: #17a2b8;
    		border-color: #17a2b8;
    		display: inline-block;
    		font-weight: 400;
    		text-align: center;
    		vertical-align: middle;
    		user-select: none;
    		border: 1px solid transparent;
    		padding: .375rem .75rem;
    		font-size: 1rem;
    		line-height: 1.5;
    		border-radius: .25rem;
    	}
    </style>
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
                <h1 style="font-size:40px;">上傳營業登記證</h1>
                <hr>
            </div>
            <div class="update_btn">
            	上傳您的營業登記證
            	<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front" method="post" id="business_form" enctype="multipart/form-data">
            		<label>
            			<div type="button" class="btn-info">點我上傳</div>
            			<input type="file" style="display:none;" name="cp_business" id="cp_business" accept="image/*">
            		</label>
            	</form>
            </div>
            <div class="display_div">
            	<img class="info_business_display_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front?method=many_pics_display&mail=${authority_compy.cp_contact_email}&which_one=business" alt="公司營業登記證">
            </div>
        </div>
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
	
    
    <script src="<%=request.getContextPath() %>/js/front_use/compy_table_use/front_CompyMembershipPlatform_info_updateBusiness.js"></script>
</body>
</html>