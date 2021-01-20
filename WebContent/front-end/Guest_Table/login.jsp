<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>會員登入</title>

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
						<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp"> <img
							src="<%=request.getContextPath() %>/images/logo2.png" alt="">
						</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto main-nav ">
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
							<ul class="navbar-nav ml-auto mt-10">
								<li class="nav-item"><a class="nav-link login-button"
									href="register1.jsp">註冊</a></li>
								
							</ul>
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
							<h3 class="bg-gray p-4">會員登入</h3>
							<form action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do"
								method="post">
								<fieldset class="p-4">
									<input type="email" placeholder="電子信箱" name="gs_email" required="required" 
										value="${param.gs_email}" class="border p-3 w-200 my-2"/>
									<input type="password" name="gs_pwd" placeholder="密碼" required="required"
										value="" class="border p-3 w-200 my-2" />
									<p style="color:red">${message }</p>




									<!--                    <div class="loggedin-forgot"> -->


									<!--                                     <input type="checkbox" -->
									<!-- 										id="keep-me-logged-in"> -->
									<!--                                     <label for="keep-me-logged-in" -->
									<!-- 										class="pt-3 pb-2">Keep me logged in</label> -->
									<!--                             </div> -->
									<button type="submit"
										class="d-block py-3 px-5 bg-primary text-white border-0 rounded font-weight-bold mt-3">登入</button>
									<input type="hidden" name="action" value="login">
									<a class="mt-3 d-block  text-primary" href="<%=request.getContextPath()%>/front-end/Guest_Table/forget.jsp">忘記密碼?</a> <a class="mt-3 d-inline-block text-primary"
										href="register1.jsp">沒有帳號? 馬上註冊</a>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
	










	<!-- 	<div class="wrapper"> -->
	<!-- 		<form -->
	<%-- 			action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" --%>
	<!-- 			method="post"> -->
	<!-- 			<label>電子信箱:</label> <input type="text" name="gs_email" -->
	<%-- 				value="${param.gs_email}" /><br> --%>
	<!-- 			<br> <label>密碼：</label> <input type="password" name="gs_pwd" -->
<%-- 	value="${param.gs_pwd}" /> --%>
	<br>
	<font color="red"> <%
 	if (request.getAttribute("message") != null) {
 		out.print(request.getAttribute("message"));
 	}
 %> <!-- 			</font> --> <!-- 			<div id="submit"> --> <!-- 				<input type="submit" value="登入" /> -->
		<!-- 			</div> --> <!-- 		</form> --> <!-- 	</div> -->
</body>


</html>