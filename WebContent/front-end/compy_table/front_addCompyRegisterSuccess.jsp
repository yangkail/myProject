<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>業者登入</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_table_use/front_addCompyRegisterSuccess.css">
    <script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
    <style>
    	div.out_div{
    	    margin: 150px auto;
    	}
    </style>
</head>
<body>
	<!-- HEADER -->
	<div class="topBackplane">
		<jsp:include page="/front-end/public_jsp/header.jsp"></jsp:include>
	</div>
	<!-- HEADER -->
	
   <div class="out_div">
        <div class="in_div_title">
            <p>恭喜您-已註冊完成</p>
        </div>
        <div class="in_div_content">
        	<p>感謝您註冊並使用本平台</p>
        	<p>請您點擊下方<span style="color:red;">轉至登入頁面</span>按鈕</p>
        	<p>登入後請點擊頁面上方<span style="color:red;">會員平台</span></p>
        	<p><span style="color:red;">會員平台</span>-><span style="color:red;">營業登記證</span></p>
        	<p>上傳貴公司的<span style="color:red;">營業登記證</span></p>
        	<p>上傳後於24小時內會有專人為您審核</p>
        	<p>審核後將會傳送郵件給您</p>
        	<p>審核通過將可使用更多功能</p>
        	<p>訂味公司感謝您</p>
        	<a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyLogin.jsp">
        		<button class="only_btn">轉至登入頁面</button>
        	</a>
        </div>
 
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
</body>
</html>