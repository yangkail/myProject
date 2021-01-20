<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.guest_table.model.*"%>
<%
Guest_TableVO guest_TableVO = (Guest_TableVO) request.getAttribute("guest_TableVO");
String gs_email=(String)session.getAttribute("gs_email");
Integer point=new Guest_TableService().totalPoint(gs_email);
pageContext.setAttribute("point", point);
%>
<!DOCTYPE html>
<html>
<head>

  <!-- SITE TITTLE -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>會員資訊</title>
  
  <!-- FAVICON -->
  <link href="<%=request.getContextPath() %>/img/favicon.png" rel="shortcut icon">
  <!-- PLUGINS CSS STYLE -->
  <!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
  <!-- Bootstrap -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/bootstrap/css/bootstrap-slider.css">
  <!-- Font Awesome -->
  <link href="<%=request.getContextPath() %>/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- Owl Carousel -->
  <link href="<%=request.getContextPath() %>/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
  <!-- Fancy Box -->
  <link href="<%=request.getContextPath() %>/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
  <!-- CUSTOM CSS -->
  <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">


  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
   <style>
 	div.point_div{
 		width: 100px;
    	height: 20px;
    	margin-left: 50px;
 	}
 	img.just_point_img{
 		width: 20px;
 	}
 </style>

</head>


<body class="body-wrapper" >


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
							<ul class="navbar-nav ml-auto main-nav ">
<!-- 								<li class="nav-item active"><a class="nav-link" -->
<!-- 									href="home.jsp">首頁</a></li> -->
<!-- 								<li class="nav-item dropdown dropdown-slide"><a -->
<!-- 									class="nav-link dropdown-toggle" data-toggle="dropdown" href="">訂位<span><i -->
<!-- 											class="fa fa-angle-down"></i></span> -->
								</a> <!-- Dropdown list -->
									<div class="dropdown-menu">
										<a class="dropdown-item" href="dashboard.html">Dashboard</a> <a
											class="dropdown-item" href="dashboard-my-ads.html">Dashboard
											My Ads</a> <a class="dropdown-item"
											href="dashboard-favourite-ads.html">Dashboard Favourite
											Ads</a> <a class="dropdown-item"
											href="dashboard-archived-ads.html">Dashboard Archived Ads</a>
										<a class="dropdown-item" href="dashboard-pending-ads.html">Dashboard
											Pending Ads</a>
									</div></li>
<!-- 								<li class="nav-item dropdown dropdown-slide"><a -->
<!-- 									class="nav-link dropdown-toggle" href="#" -->
<!-- 									data-toggle="dropdown" aria-haspopup="true" -->
<!-- 									aria-expanded="false"> Pages <span><i -->
<!-- 											class="fa fa-angle-down"></i></span> -->
								</a>
							</ul>
							<ul class="navbar-nav ml-auto mt-10">
<!-- 								<li class="nav-item"><a class="nav-link login-button" -->
<!-- 									href="login.html">註冊</a></li> -->
								<li class="nav-item"><a class="nav-link login-button" href="<%=request.getContextPath()%>/Guest_table/IndexServlet">登出</a></li>
								
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
</section>
<!--==================================
=            User Profile            =
===================================-->

<section class="user-profile section">
	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">
				<div class="sidebar">
					<!-- User Widget -->
					<div class="widget user">
						<!-- User Image -->
						<div class="image d-flex justify-content-center">
							<img id="gs_big_pic_img" src="<%=request.getContextPath() %>/Guest_Table/Guest_Table.do?action=display_pic&gs_email=${gs_email}" alt="" class="">
						</div>
						<form action="<%=request.getContextPath() %>/Guest_table/Guest_Table.do" method="post" id="bigpic_form" enctype="multipart/form-data">
							<label style="margin-left: 160px;">
						<img src="<%=request.getContextPath() %>/images/up.png" title="更改圖片">
						<input type="file" style="display:none;" name="gs_big_pic" id="gs_big_pic" accept="image/*">
						</label>
						</form>
						<!-- User Name -->
						<h5 class="text-center"><font size="6">${gs_name}</font></h5>
						<div class="point_div">
							<img class="just_point_img" alt="積分數" title="積分數" src="<%=request.getContextPath() %>/images/money.png">
							積分:${point}
						</div>
					</div>
					<!-- Dashboard Links -->
					<div class="widget dashboard-links">
										<ul>
							<li><a class="my-1 d-inline-block" href="<%=request.getContextPath() %>/front-end/Guest_Table/update_gs_profile.jsp">修改會員資料</a></li>
							<li><a class="my-1 d-inline-block" href="<%=request.getContextPath() %>/front-end/Guest_Table/guest_profileorder.jsp">訂單管理 </a></li>
							<li><a class="my-1 d-inline-block" href="<%=request.getContextPath() %>/front-end/Guest_Table/guest_profilecredit.jsp">信用卡資訊 </a></li>
							<li><a class="my-1 d-inline-block" href="<%=request.getContextPath() %>/front-end/Guest_Table/guest_profilepoint.jsp">積分累積查詢</a></li>
							<li><a class="my-1 d-inline-block" href="<%=request.getContextPath() %>/front-end/Guest_Table/changePWD.jsp">修改密碼</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">
			<div class="row">
					<div class="col-lg-6 col-md-6">
					
						<div class="widget personal-info" style="width:1000px; height:800px;">
							<h3 class="widget-header user"><font size="6"><center>會員訂單資訊-已取消訂單</center></font></h3>
				
								<!-- Last Name -->
								<div>
									<jsp:include page="/front-end/order_table/Consumer_mail_cancel_page.jsp"></jsp:include>
								</div>
							
								
						</div>
						
					</div>
							
				</div>
			</div>
					
<!-- 					<div class="col-lg-6 col-md-6"> -->
<!-- 						Change Email Address -->
<!-- 					<div class="widget change-email mb-0"> -->
<!-- 						<h3 class="widget-header user">Edit Email Address</h3> -->
<!-- 						<form action="#"> -->
<!-- 							Current Password -->
<!-- 							<div class="form-group"> -->
<!-- 								<label for="current-email">Current Email</label> -->
<!-- 								<input type="email" class="form-control" id="current-email"> -->
<!-- 							</div> -->
<!-- 							New email -->
<!-- 							<div class="form-group"> -->
<!-- 								<label for="new-email">New email</label> -->
<!-- 								<input type="email" class="form-control" id="new-email"> -->
<!-- 							</div> -->
<!-- 							Submit Button -->
<!-- 							<button class="btn btn-transparent">Change email</button> -->
<!-- 						</form> -->
<!-- 					</div> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
</section>

<!--============================
=            Footer            =
=============================-->



   
  <!-- Container End -->
  <!-- To Top -->
  <div class="top-to">
    <a id="top" class="" href="#"><i class="fa fa-angle-up"></i></a>
  </div>
</footer>

<!-- JAVASCRIPTS -->
<script src="<%=request.getContextPath() %>/plugins/jQuery/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/bootstrap/js/popper.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/bootstrap/js/bootstrap-slider.js"></script>
  <!-- tether js -->
<script src="<%=request.getContextPath() %>/plugins/tether/js/tether.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/raty/jquery.raty-fa.js"></script>
<script src="<%=request.getContextPath() %>/plugins/slick-carousel/slick/slick.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/fancybox/jquery.fancybox.pack.js"></script>
<script src="<%=request.getContextPath() %>/plugins/smoothscroll/SmoothScroll.min.js"></script>
<!-- google map -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
<script src="<%=request.getContextPath() %>/plugins/google-map/gmap.js"></script>
<script src="<%=request.getContextPath() %>/js/script.js"></script>
<script src="<%=request.getContextPath() %>/js/pic_update.js"></script>
</body>

</html>