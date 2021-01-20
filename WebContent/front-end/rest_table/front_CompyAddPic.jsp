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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/rest_table_use/front_CompyAddPic.css">
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
    		<h1 style="font-size:40px;">業者餐廳圖片上傳</h1>
                <hr>
        </div>
        
        
        <form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front" method="POST" enctype="multipart/form-data">
        <div class="update_view">
            <div class="view">
                <div class="view_info">環視圖1
                    <label>
                    	<div type="button" class="btn-info">讀取檔案</div>
                    	<input type="file" style="display:none;" name="rs_view1" id="rs_view1">
                    </label>
                </div>
                <div class="img">
                    <img class="view_pic_display_img1" alt="餐廳環視圖1"
                        src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view1">
                </div>
            </div>
            <div class="view">
                <div class="info">環視圖2
                    <label>
                    	<div type="button" class="btn-info">讀取檔案</div>
                    	<input type="file" style="display:none;" name="rs_view2" id="rs_view2">
                    </label>
                </div>
                <div class="img">
                    <img class="view_pic_display_img2" alt="餐廳環視圖2"
                        src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view2">
                </div>
            </div>
            <div class="view">
                <div class="info">環視圖3
                	<label>
                    	<div type="button" class="btn-info">讀取檔案</div>
                    	<input type="file" style="display:none;" name="rs_view3" id="rs_view3">
                    </label>
                </div>
                <div class="img">
                    <img class="view_pic_display_img3" alt="餐廳環視圖3"
                        src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view3">
                </div>
            </div>
            <div class="view">
                <div class="info">環視圖4
                	<label>
                    	<div type="button" class="btn-info">讀取檔案</div>
                    	<input type="file" style="display:none;" name="rs_view4" id="rs_view4">
                    </label>
                </div>
                <div class="img">
                    <img class="view_pic_display_img4" alt="餐廳環視圖4"
                        src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view4">
                </div>
            </div>
            <div class="view">
                <div class="info">環視圖5
                	<label>
                    	<div type="button" class="btn-info">讀取檔案</div>
                    	<input type="file" style="display:none;" name="rs_view5" id="rs_view5">
                    </label>
                </div>
                <div class="img">
                    <img class="view_pic_display_img5" alt="餐廳環視圖5"
                        src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view5">
                </div>
            </div>
        </div>

        
        <span class="error_span">${errorMsgs.error }</span>
        <div class="submit_btn">
        	<input type="submit" value="下一步-座位配置">
        	<input type="hidden" name="method" value="update_pic_and_view">
        </div>
    </form>
			
			
    	
    	
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