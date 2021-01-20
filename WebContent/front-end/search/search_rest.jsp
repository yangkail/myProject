<%@page import="com.compy_table.model.Compy_TableService"%>
<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.ad_table.model.Ad_TableVO"%>
<%@page import="com.ad_table.model.Ad_TableService"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>訂味</title>
<link href="img/favicon.png" rel="shortcut icon">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap-slider.css">
<link href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/search_use/search_use.css">
<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<%-- 	<script src="<%=request.getContextPath() %>/js/index_use/main.js"></script> --%>
<style>


</style>
<body class="body-wrapper">


	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<nav class="navbar navbar-expand-lg navbar-light navigation">
						<a class="navbar-brand"
							href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">
							<img src="<%=request.getContextPath()%>/images/logo2.png" alt="">
						</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto main-nav ">
								<c:choose>
									<c:when test="${gs_email!=null }">

										<li><a class="nav-item dropdown dropdown-slide">歡迎，${gs_name }&nbsp&nbsp&nbsp&nbsp
										</a></li>

										<li class="nav-item dropdown dropdown-slide"><a
											class="nav-link dropdown-toggle" href=""
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false"> 會員專區 <span><i
													class="fa fa-angle-down"></i></span>
										</a> <!-- 								Dropdown list -->
											<div class="dropdown-menu">
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/Guest_Table/guest_profile1.jsp">會員資料</a>
											</div></li>
										<li class="nav-item dropdown dropdown-slide"><a
											class="nav-link dropdown-toggle" href=""
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false"> 業者專區 <span><i
													class="fa fa-angle-down"></i></span>
										</a> <!-- 								Dropdown list -->
											<div class="dropdown-menu">
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyLogin.jsp">業者登入</a>
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/compy_table/front_addCompyRegister.jsp">業者註冊</a>

											</div></li>


										<li><a class="nav-item dropdown dropdown-slide"
											href="<%=request.getContextPath()%>/Guest_table/IndexServlet">登出</a>
										</li>


									</c:when>

									<c:when test="${gs_email==null }">
										<li class="nav-item dropdown dropdown-slide"><a
											class="nav-link dropdown-toggle" href=""
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false"> 會員專區 <span><i
													class="fa fa-angle-down"></i></span>
										</a> <!-- 								Dropdown list -->
											<div class="dropdown-menu">
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/Guest_Table/login.jsp">會員登入</a>
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/Guest_Table/register1.jsp">會員註冊</a>

											</div></li>



										<li class="nav-item dropdown dropdown-slide"><a
											class="nav-link dropdown-toggle" href=""
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false"> 業者專區 <span><i
													class="fa fa-angle-down"></i></span>
										</a> <!-- 								Dropdown list -->
											<div class="dropdown-menu">
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyLogin.jsp">業者登入</a>
												<a class="dropdown-item"
													href="<%=request.getContextPath()%>/front-end/compy_table/front_addCompyRegister.jsp">業者註冊</a>
											</div></li>

									</c:when>

								</c:choose>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</section>

						<div class="advance-search">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12 col-md-12 align-content-center">
				
					<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_search" method="POST">
						<div class="form-row">
							<div class="form-group col-md-4" style="margin-left:140px;">
								<input type="text" class="form-control my-2 my-lg-1"
									name="search_rest_name" id="inputtext4" placeholder="餐廳名稱" >
							</div>
<!-- 							<div class="form-group col-md-3"> -->
<!-- 								<input style="display:none;" type="text" class="form-control my-2 my-lg-1" id="inputLocation4" placeholder="地點"> -->
<!-- 							</div> -->
							<div class="form-group col-md-3">
								<select name="search_rest_type" class="w-100 form-control mt-lg-1 mt-md-2" >
									<option>分類</option>
									<option value="日式">日式</option>
									<option value="美式">美式</option>
									<option value="台式">台式</option>
									<option value="韓式">韓式</option>
									<option value="西式">西式</option>
									<option value="泰式">泰式</option>
									<option value="印式">印式</option>
								</select>
							</div>
							<div class="form-group col-md-2 align-self-center">
								<button type="submit" class="btn btn-primary">搜尋餐廳</button>
								<input type="hidden" name="method" value="search_rest">
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div></div>
<!-- 搜尋頁底部 -->
<br>
<c:forEach items="${list}" var="list">
<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${list.rs_id}">
	<div class="rest_display_in_here">
		<div class="rest_pic_display_here">
			<img class="rest_pic_real_dsiplay" alt="" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${list.rs_id}&which=rs_view1">
		</div>
		<div class="rest_info_in_here">
			<table class="info_table">
				<tr>
					<td></td>
					<td class="info" style="font-size:45px;">${list.rs_name }</td>
				</tr>
				<tr class="tr_not_do_some"><td></td><td></td></tr>
				<tr>
					<td>餐廳地址: </td>
					<td class="info">${list.rs_address}</td>
				</tr>
				<tr class="tr_not_do_some"><td></td><td></td></tr>
				<tr>
					<td>餐廳類型: </td>
					<td class="info">${list.rs_type }</td>
				</tr>
				<tr class="tr_not_do_some"><td></td><td></td></tr>
				<tr>
					<td>餐廳時間: </td>
					<td class="info">${list.rs_open_time }</td>
				</tr>
				<tr class="tr_not_do_some"><td></td><td></td></tr>
				<tr>
					<td>餐廳介紹: </td>
					<td class="info">${list.rs_intro }</td>
				</tr>
				<tr class="tr_not_do_some"><td></td><td></td></tr>
			</table>
		</div>
	</div>
</a>
</c:forEach><br><br>


<!-- Footer -->
	<footer class="footer section section-sm">
		<!-- Container Start -->
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-7 offset-md-1 offset-lg-0">
					<!-- About -->
					<div class="block about">
						<img src="<%=request.getContextPath()%>/images/logo2.png" alt="">
					</div>
				</div>
				
	
		<!-- Container End -->
	</footer>
	<!-- JAVASCRIPTS -->
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/bootstrap-slider.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/tether/js/tether.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/raty/jquery.raty-fa.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/fancybox/jquery.fancybox.pack.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/smoothscroll/SmoothScroll.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
	<script src="<%=request.getContextPath()%>/plugins/google-map/gmap.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>

</body>
</html>



