<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>業者忘記密碼</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_table_use/front_CompyForget.css">
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
            <p>忘記密碼</p>
        </div>
        <div class="in_div_content">
			<p>請輸入您的帳號</p>
			<input type="text" id="cp_account" name="cp_account"><br>
			<div class="div_here1"></div>
			<div class="div_here2"></div>
			<p>帳號輸入正確後請按下下方"送出"按鈕會寄送密碼重置信件</p>
			<p>請點選郵件內連結以便重置密碼</p>
			<button class="send_btn" id="send_btn">送出</button>
        </div>
 
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
    <script src="<%=request.getContextPath() %>/js/front_use/compy_table_use/front_CompyForget.js"></script>
</body>
</html>