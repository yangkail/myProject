<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.guest_table.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Guest_TableVO guest_TableVO = (Guest_TableVO) request.getAttribute("guest_TableVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改密碼</title>

<!-- FAVICON -->
<link href="<%=request.getContextPath() %>/img/favicon.png" rel="shortcut icon">
<!-- PLUGINS CSS STYLE -->
<!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
<!-- Bootstrap -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/bootstrap/css/bootstrap-slider.css">
<!-- Font Awesome -->
<link href="<%=request.getContextPath() %>/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Owl Carousel -->
<link href="<%=request.getContextPath() %>/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/plugins/slick-carousel/slick/slick-theme.css"
	rel="stylesheet">
<!-- Fancy Box -->
<link href="<%=request.getContextPath() %>/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/plugins/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<!-- CUSTOM CSS -->
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>標題</title>
<style > 

</style>
</head>
<body>
</head>

<body class="body-wrapper">


	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<nav class="navbar navbar-expand-lg navbar-light navigation">
						<a class="navbar-brand" href="<%=request.getContextPath() %>/front-end/Guest_Table/home.jsp"> <img
							src="<%=request.getContextPath() %>/images/logo2.png" alt="">
						</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
<!-- 							<ul class="navbar-nav ml-auto main-nav "> -->
<!-- 								<li class="nav-item active"><a class="nav-link" -->
<!-- 									href="index.html">首頁</a></li> -->
<!-- 								<li class="nav-item dropdown dropdown-slide"><a -->
<!-- 									class="nav-link dropdown-toggle" data-toggle="dropdown" href="">訂位<span><i -->
<!-- 											class="fa fa-angle-down"></i></span> -->
<!-- 								</a> Dropdown list -->
<!-- 									<div class="dropdown-menu"> -->
<!-- 										<a class="dropdown-item" href="dashboard.html">Dashboard</a> <a -->
<!-- 											class="dropdown-item" href="dashboard-my-ads.html">Dashboard -->
<!-- 											My Ads</a> <a class="dropdown-item" -->
<!-- 											href="dashboard-favourite-ads.html">Dashboard Favourite -->
<!-- 											Ads</a> <a class="dropdown-item" -->
<!-- 											href="dashboard-archived-ads.html">Dashboard Archived Ads</a> -->
<!-- 										<a class="dropdown-item" href="dashboard-pending-ads.html">Dashboard -->
<!-- 											Pending Ads</a> -->
<!-- 									</div></li> -->
<!-- 								<li class="nav-item dropdown dropdown-slide"><a -->
<!-- 									class="nav-link dropdown-toggle" href="#" -->
<!-- 									data-toggle="dropdown" aria-haspopup="true" -->
<!-- 									aria-expanded="false"> Pages <span><i -->
<!-- 											class="fa fa-angle-down"></i></span> -->
<!-- 								</a> -->
<!-- 							</ul> -->
<!-- 							<ul class="navbar-nav ml-auto mt-10"> -->
<!-- 								<li class="nav-item"><a class="nav-link login-button" -->
<!-- 									href="register1.jsp">註冊</a></li> -->
								
<!-- 							</ul> -->
						</div>
					</nav>
				</div>
			</div>
		</div>
	</section>
	
		<section class="login py-5 border-top-1">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-5 col-md-8 align-item-center">
						<div class="border">
							<h3 class="bg-gray p-4">修改密碼</h3>
							
							<form id="changePWD" action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do"
								method="post">
								<fieldset class="p-4">
								<input type="hidden" placeholder="請輸入舊密碼" name="new_pwd" id="password0" 
										value="${gs_pwd}" class="border p-3 w-200 my-2"/>
									<input type="password" placeholder="請輸入舊密碼" name="new_pwd" id="password" onkeyup="validate2()" 
										value=""  class="border p-3 w-200 my-2"/>
									<input type="password" name="gs_pwd" placeholder="請輸入新密碼  6~10碼" id="password1" required="required" pattern="^[(a-zA-Z0-9_)]{6,10}$"
										value="${param.gs_pwd}" class="border p-3 w-200 my-2" />
										<input type="password" name="re_pwd" placeholder="請再次輸入密碼" id="password2" onkeyup="validate()" required="required" 
										value="" class="border p-3 w-200 my-2" />

									<%
										if (request.getAttribute("message") != null) {
											out.print(request.getAttribute("message"));
										}
									%>




									<!--                    <div class="loggedin-forgot"> -->


									<!--                                     <input type="checkbox" -->
									<!-- 										id="keep-me-logged-in"> -->
									<!--                                     <label for="keep-me-logged-in" -->
									<!-- 										class="pt-3 pb-2">Keep me logged in</label> -->
									<!--                             </div> -->
									<span id="tishi"></span>
									<span id="tishi2"></span>
<!-- 									<button type="submit" id="submit" -->
<!-- 										class="d-block py-3 px-5 bg-primary text-white border-0 rounded font-weight-bold mt-3" disabled>修改密碼</button> -->
									<button type="button" id="submit" class="d-block py-3 px-5 bg-primary text-white border-0 rounded font-weight-bold mt-3" disabled>修改密碼</button>
									<input type="submit" id="real_submit" style="display:none;">
									<input type="hidden" name="action" value="updatePwd">
<%
 	if (request.getAttribute("message") != null) {
 		out.print(request.getAttribute("message"));
 	}
 %>
			
								</fieldset>
								
					
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
	

<!-- 输入密码： <input type="password" name="password1" id="password1"/> -->
<!-- 确认密码： <input type="password" name="password2" id="password2" onkeyup="validate()"/> -->
<!-- <span id="tishi"></span> -->
<!-- <input type="submit" value="注册" id="submit"/> -->

<script src="<%=request.getContextPath() %>/plugins/jQuery/jquery.min.js"></script>		
<script>

function validate() {
	var password1 = document.getElementById("password1").value;
	var password2 = document.getElementById("password2").value;
	if(password1 == password2) {
	document.getElementById("tishi").innerHTML="<br><font color='green'></font>";
	document.getElementById("submit").disabled = false;
	}
	else {
	document.getElementById("tishi").innerHTML="<br><font color='red'>請確認輸入相同密碼</font>";
	document.getElementById("submit").disabled = true;
	}
	
	}
	
	
function validate2() {
	var password3 = document.getElementById("password").value;
	var password4 = document.getElementById("password0").value;
	if(password3 == password4) {
	document.getElementById("tishi2").innerHTML="<br><font color='green'></font>";
	document.getElementById("submit").disabled = false;
	}
	else {
	document.getElementById("tishi2").innerHTML="<br><font color='red'>舊密碼輸入錯誤</font>";
	document.getElementById("submit").disabled = true;
	}
	}

	

// function functSubmit(){
	
// 	alert("密碼已修改,請重新登入");

// 	};


document.getElementById("submit").addEventListener("click",function(){
	  swal("密碼以重設", "請重新登入!", "success");
	  setTimeout(function() {
		  $('#real_submit').click()}, 2000); 
	});


	
	
</script>






	<!-- 	<div class="wrapper"> -->
	<!-- 		<form -->
	<%-- 			action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" --%>
	<!-- 			method="post"> -->
	<!-- 			<label>電子信箱:</label> <input type="text" name="gs_email" -->
	<%-- 				value="${param.gs_email}" /><br> --%>
	<!-- 			<br> <label>密碼：</label> <input type="password" name="gs_pwd" -->
<%-- 	value="${param.gs_pwd}" /> --%>
<!-- 	<br> -->
<!-- 	<font color="red">  -->
	<%
 	if (request.getAttribute("message") != null) {
 		out.print(request.getAttribute("message"));
 	}
 %> <!-- 			</font> --> <!-- 			<div id="submit"> --> <!-- 				<input type="submit" value="登入" /> -->
		<!-- 			</div> --> <!-- 		</form> --> <!-- 	</div> -->

<script src="<%=request.getContextPath() %>/plugins/jQuery/jquery.easyui.min.js"></script>			
</body>


</html>