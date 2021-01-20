<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Compy_TableVO compy_TableVO=(Compy_TableVO)request.getAttribute("compy_TableVO");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>業者登入</title>
    <meta name="google-signin-scope" content="profile email">
	<meta name="google-signin-client_id" content='1053268458509-vf3it6ejtpdq8plcuqco0il8cirhnejb.apps.googleusercontent.com'>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_table_use/front_CompyLogin.css">
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
            <p>業者會員登入</p>
        </div>
        <form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front" method="POST">
            <div class="in_div_account">
                <input type="text" name="cp_account" size="30px" placeholder="請輸入帳號" value="<%=(compy_TableVO==null)?"":compy_TableVO.getCp_account() %>">
                <div class="in_div_errorMsgs" style="color:red">${errorMsgs.account}</div>
            </div>
            <div class="in_div_pwd">
                <input type="password" name="cp_pwd" size="30px" placeholder="請輸入密碼">
                <div class="in_div_errorMsgs" style="color:red">${errorMsgs.pwd}</div>
                <div class="in_div_errorMsgs" style="color:red">${errorMsgs.account_error}</div>
            </div>
            <div class="in_div_submit_btn">
                <input class="submit_btn" type="submit" value="登入">
                <input type="hidden" name="method" value="front_compy_login">
            </div>
        </form><br>
        <div class="in_div_forget_pwd">
            <a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyForget.jsp" style="text-decoration:none">忘記密碼</a>
        </div>
        <div class="in_div_register_new">
            <a href="<%=request.getContextPath() %>/front-end/compy_table/front_addCompyRegister.jsp" style="text-decoration:none">沒有帳號?立即註冊</a>
        </div>
        <div class="in_div_fb_google_btn">
            <ul>
                <li class="in_li_google_btn">
                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                </li>
            </ul>
        </div>
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
	<script>
	function onSignIn(googleUser) {
	    // Useful data for your client-side scripts:
	    var profile = googleUser.getBasicProfile();
	    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
	    console.log("Email: " + profile.getEmail());

	    // The ID token you need to pass to your backend:
	    var id_token = googleUser.getAuthResponse().id_token;
	    console.log("ID Token: " + id_token);
	    let data ={
	            "method":"google",
	            "email" : profile.getEmail(),
	           };
	    $.ajax({
            type: "POST",
            url: "/TEA102G1/compy_table/Compy_TableServlet_front",
            data:data,
            success: function (result) {
            	if(result.finals="it_ok"){
            		signOut();
            		window.location.replace("/TEA102G1/front-end/compy_table/front_addCompyRegister.jsp");
            	}
            },
            error: function (err) {
                alert("系統錯誤");
            }
        });
	  }
	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');
		});
	  }
	</script>
</body>
</html>