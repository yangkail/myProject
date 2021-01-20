<%@page import="com.point_table.model.Point_TableService"%>
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

<%
	int i = 1;
	Ad_TableService ad_Tablesvc = new Ad_TableService();
	List<Ad_TableVO> listad = ad_Tablesvc.getAll();
	pageContext.setAttribute("listad",listad);
	
	Compy_TableService compy_TableService = new Compy_TableService();
	List<Compy_TableVO> listComp = compy_TableService.getAll();
	pageContext.setAttribute("listComp",listComp);
	
	Point_TableService _pntableService = new Point_TableService();
	List<Ad_TableVO> listap = ad_Tablesvc.getAll();
	pageContext.setAttribute("listap",listap);
	
	
	Rest_TableService rest_Tablesvc = new Rest_TableService();
	//熱門餐廳
	List<Rest_TableVO> list = rest_Tablesvc.getAll();
	List<Rest_TableVO> list2 = new ArrayList<Rest_TableVO>();
	List<Rest_TableVO> list3 = new ArrayList<Rest_TableVO>();
	List<Rest_TableVO> list4 = new ArrayList<Rest_TableVO>();
	List<Rest_TableVO> list5 = new ArrayList<Rest_TableVO>();
	list2.add(list.get(0));
	list3.add(list.get(1));
	list4.add(list.get(2));
	list5.add(list.get(3));
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("list2", list2);
	pageContext.setAttribute("list3", list3);
	pageContext.setAttribute("list4", list4);
	pageContext.setAttribute("list5", list5);
	//新進餐廳
	List<Rest_TableVO> listN = rest_Tablesvc.getAllNew();
	List<Rest_TableVO> listN2 = new ArrayList<Rest_TableVO>();
	List<Rest_TableVO> listN3 = new ArrayList<Rest_TableVO>();
	List<Rest_TableVO> listN4 = new ArrayList<Rest_TableVO>();
	List<Rest_TableVO> listN5 = new ArrayList<Rest_TableVO>();
	listN2.add(listN.get(0));
	listN3.add(listN.get(1));
	listN4.add(listN.get(2));
	listN5.add(listN.get(3));
	pageContext.setAttribute("listN", listN);
	pageContext.setAttribute("listN2", listN2);
	pageContext.setAttribute("listN3", listN3);
	pageContext.setAttribute("listN4", listN4);
	pageContext.setAttribute("listN5", listN5);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>訂味</title>
<!-- FAVICON -->
<link href="img/favicon.png" rel="shortcut icon">
<!-- PLUGINS CSS STYLE -->
<!-- <link href="plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet"> -->
<!-- Bootstrap -->

<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap-slider.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<!-- CUSTOM CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/main.css">
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/reset.css"> --%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/style.css">
<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=request.getContextPath() %>/js/index_use/main.js"></script>


<body class="body-wrapper">

<header class="header">
		<div class="header_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="header_content d-flex flex-row align-items-center justify-content-start">
							<div ><a  href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp"><img alt="" src="<%=request.getContextPath() %>/images/logo2.png"></a></div>
							<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_search" method="POST">
							<input name="search_rest_name" id="inputtext4" type="text" class="search_input" placeholder="Search">
							<label>
							<img src="<%=request.getContextPath()%>/images/search.gif">
							<button type="submit" class="btn btn-primary" style="display:none;"></button>
								<input type="hidden" name="method" value="search_rest"></label>
							</form>
							<nav class="main_nav">
							
							<c:choose>
								<c:when test="${gs_email!=null }">
									<ul><a class="nav-item dropdown dropdown-slide">歡迎，${gs_name }</a></ul>
									<ul>
										<li class="hassubs active" >
											<a href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">會員專區</a>
											<ul>
												<li style="width: 56px;"><a href="<%=request.getContextPath() %>/front-end/Guest_Table/guest_profile1.jsp">會員資訊</a></li>																						
											</ul>
										</li>												
										<a class="nav-item dropdown dropdown-slide" href="<%=request.getContextPath()%>/Guest_table/IndexServlet">登出</a>
									</ul>
								</c:when>
								
								<c:when test="${authority_compy!=null }">
									<ul><a class="nav-item dropdown dropdown-slide">歡迎，${userName }</a></ul>
									<ul>
										<li class="hassubs active" >
											<a href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">業者專區</a>
											<ul>
												<li style="width: 56px;"><a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp?display=1">業者資訊</a></li>																						
											</ul>
										</li>														
										<a class="nav-item dropdown dropdown-slide" href="<%=request.getContextPath()%>/compy_table/IndexServlet">登出</a>	
									</ul>
								</c:when>		
									
							<c:when test="${gs_email==null }">									
									<ul>
										<li class="hassubs active" >
											<a href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">會員專區</a>
											<ul>
												<li style="width: 56px;"><a href="<%=request.getContextPath() %>/front-end/Guest_Table/register1.jsp">會員註冊</a></li>
												<li><a href="<%=request.getContextPath() %>/front-end/Guest_Table/login2.jsp">會員登入</a></li>											
											</ul>
										</li>
										<li class="hassubs active" >
											<a href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">業者專區</a>
											<ul>
												<li style="width: 56px;"><a href="<%=request.getContextPath()%>/front-end/compy_table/front_addCompyRegister.jsp">業者註冊</a></li>
												<li><a href="<%=request.getContextPath()%>/front-end/compy_table/front_CompyLogin.jsp">業者登入</a></li>
											</ul>
										</li>
									</ul>
							</c:when>
						</c:choose>	

	</header>


<%-- 							<c:choose> --%>
<%-- 								<c:when test="${gs_email!=null }"> --%>
							
<%-- 								<li><a class="nav-item dropdown dropdown-slide">歡迎，${gs_name }&nbsp&nbsp&nbsp&nbsp </a></li> --%>
								
<!-- 								<li class="nav-item dropdown dropdown-slide"> -->
<!-- 								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 									會員專區 <span><i class="fa fa-angle-down"></i></span> -->
<!-- 								</a> -->
<!-- 								<div class="dropdown-menu"> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/Guest_Table/guest_profile1.jsp">會員資料</a>								 --%>
<!-- 							</div>					 -->
<!-- 								</li>	 -->
												
<!-- 							<li > -->
<%-- 								<a class="nav-item dropdown dropdown-slide" href="<%=request.getContextPath()%>/Guest_table/IndexServlet">登出</a> --%>
<!-- 							</li>												 -->
<%-- 							</c:when> --%>
							
<%-- 							<c:when test="${rs_id!=null }"> --%>
							
<%-- 								<li><a class="nav-item dropdown dropdown-slide">歡迎，${rs_id }&nbsp&nbsp&nbsp&nbsp </a></li> --%>
						
<!-- 							<li class="nav-item dropdown dropdown-slide"> -->
<!-- 								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 									業者專區 <span><i class="fa fa-angle-down"></i></span> -->
<!-- 								</a> -->

<!-- 								<div class="dropdown-menu"> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp">業者資料</a> --%>
<!-- 								</div> -->
<!-- 							</li>								 -->
<!-- 							<li > -->
<%-- 								<a class="nav-item dropdown dropdown-slide" href="<%=request.getContextPath()%>/compy_table/IndexServlet">登出</a> --%>
<!-- 							</li>											 -->
<%-- 							</c:when> --%>
							
							
							
<%-- 							<c:when test="${gs_email==null }"> --%>
<!-- 								<li class="nav-item dropdown dropdown-slide"> -->
<!-- 								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 									會員專區 <span><i class="fa fa-angle-down"></i></span> -->
<!-- 								</a> -->
<!-- 								<div class="dropdown-menu"> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/Guest_Table/login.jsp">會員登入</a> --%>
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/Guest_Table/register1.jsp">會員註冊</a>								 --%>
<!-- 								</div> -->
<!-- 							<li class="nav-item dropdown dropdown-slide"> -->
<!-- 								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 									業者專區 <span><i class="fa fa-angle-down"></i></span> -->
<!-- 								</a> -->
<!-- 								<div class="dropdown-menu"> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyLogin.jsp">業者登入</a> --%>
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_addCompyRegister.jsp">業者註冊</a> --%>
<!-- 								</div> -->
<!-- 							</li> -->
<%-- 							</c:when>							 --%>
<%-- 							</c:choose>	 --%>
<!-- <!-- 	*****************************************************************************					 --> 

<!-- 					</div> -->
<!-- 				</nav> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </section> -->
	<!--===============================
=            Hero Area            =
================================-->

<!-- 					<div class="advance-search"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="row justify-content-center"> -->
<!-- 				<div class="col-lg-12 col-md-12 align-content-center"> -->
				
<%-- 					<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_search" method="POST"> --%>
<!-- 						<div class="form-row"> -->
<!-- 							<div class="form-group col-md-4" > -->
<!-- 								<input type="text" class="form-control my-2 my-lg-1" -->
<!-- 									name="search_rest_name" id="inputtext4" placeholder="餐廳名稱" > -->
<!-- 							</div> -->
<!-- 							<div class="form-group col-md-3"> -->
<!-- 								<input style="display:none;" type="text" class="form-control my-2 my-lg-1" id="inputLocation4" placeholder="地點"> -->
<!-- 							</div> -->
<!-- 							<div class="form-group col-md-3"> -->
<!-- 								<select name="search_rest_type" class="w-100 form-control mt-lg-1 mt-md-2" > -->
<!-- 									<option>分類</option> -->
<!-- 									<option value="日式">日式</option> -->
<!-- 									<option value="美式">美式</option> -->
<!-- 									<option value="台式">台式</option> -->
<!-- 									<option value="韓式">韓式</option> -->
<!-- 									<option value="西式">西式</option> -->
<!-- 									<option value="泰式">泰式</option> -->
<!-- 									<option value="印式">印式</option> -->
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 							<div class="form-group col-md-2 align-self-center"> -->
<!-- 								<button type="submit" class="btn btn-primary">搜尋餐廳</button> -->
<!-- 								<input type="hidden" name="method" value="search_rest"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</form> -->
					
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 				<br> -->




	<div class="slideshow" style="height:700px;">
	<%
	Ad_TableVO ad_tableVO = (Ad_TableVO)pageContext.getAttribute("ad_tableVO");
	%>
	<c:forEach var="ad_TableVO" items="${listad}" varStatus="i">
		<%
			String photoString = null;
			try{
				Ad_TableVO ad_TableVO =(Ad_TableVO)pageContext.getAttribute("ad_TableVO");
				Base64.Encoder encoder = Base64.getEncoder();
				photoString = encoder.encodeToString(ad_TableVO.getAd_pic_queue());
			}catch(Exception e){
				e.printStackTrace();
			}
			%>
		<div class="slideshow-slides">

			<a href="javascript: void(0)" class="slide" id="slide-${i.count}"><img src="data:image;base64,<%=photoString %>" width="1600" height="600" ></a>

		</div>
		</c:forEach>
		<div class="slideshow-nav">
			<a href="javascript: void(0)" class="prev" style="margin-top: 300px">Prev</a>
			<a href="javascript: void(0)" class="next" style="margin-top: 300px">Next</a>
		</div>
		<div class="slideshow-indicator"></div>
	</div>
<h3 > 
<marquee id="wendati_rs" style="color:white;"></marquee>
</h3>
	<!-- Container End -->

<!--===================================
=            Client Slider            =
====================================-->
<!--===========================================
=            Popular deals section            =
============================================-->

	<section class="popular-deals section bg-gray">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="section-title">
						<h2>精選熱門餐廳</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<!-- offer 01 -->
				<div class="col-lg-12">
					<div class="trending-ads-slide">
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${list2}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
									</c:forEach>
									<div class="card-body">
										<c:forEach var="infor" items="${list2}">
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${list3}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
									</c:forEach>
									<div class="card-body">
										<c:forEach var="infor" items="${list3}">
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${list4}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
									</c:forEach>
									<div class="card-body">
										<c:forEach var="infor" items="${list4}">
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${list5}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
									</c:forEach>
									<div class="card-body">
										<c:forEach var="infor" items="${list5}">
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
<section class="popular-deals section bg-gray">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="section-title">
						<h2>精選新進餐廳</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<!-- offer 01 -->
				<div class="col-lg-12">
					<div class="trending-ads-slide">
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${listN2}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
										<div class="card-body">

											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${listN3}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
										<div class="card-body">

											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>

										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${listN4}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
										<div class="card-body">
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-lg-4">
							<!-- product card -->
							<div class="product-item bg-light">
								<div class="card">
									<c:forEach var="infor" items="${listN5}">
										<div class="thumb-content">
											<a
												href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">
												<img class="card-img-top img-fluid"
												src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${infor.rs_id}&which=rs_view1" width="600" height="400"
												alt="Card image cap">
											</a>
										</div>
										<div class="card-body">
											<h4 class="card-title">
												<a href="<%=request.getContextPath()%>/front-end/booking_table/booking.jsp?name=action&value=${infor.rs_id}">${infor.rs_name},${infor.rs_type}料理</a>
											</h4>
											<li class="list-inline-item"><a><i class=""></i>連絡電話:${infor.rs_phone}</a>
											</li>
											<li class="list-inline-item"><a><i class=""></i>餐廳地址:${infor.rs_address}</a>
											</li>
											<p class="card-text">${infor.rs_intro}</p>
											<div class="product-ratings"></div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</section>
	<!--==========================================
=            All Category Section            =
===========================================-->
	<!--============================
=            Footer            =
=============================-->
<!-- 	<footer class="footer section section-sm"> -->
<!-- 		<!-- Container Start --> 
<!-- 		<div class="container"> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-lg-3 col-md-7 offset-md-1 offset-lg-0"> -->
<!-- 					About -->
<!-- 					<div class="block about"> -->
<%-- 						<img src="<%=request.getContextPath()%>/images/logo2.png" alt=""> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				Link list -->
<!-- 				<div class="col-lg-2 offset-lg-1 col-md-3"> -->
<!-- 					<div class="block"> -->
<!-- 						<h4>Q&A</h4> -->
<!-- 						<ul> -->
<!-- 							<li><a href="#">Boston</a></li> -->
<%-- 							<li><a href="<%=request.getContextPath()%>/front-end/ad_table/Q&A.jsp">Q&A</a></li> --%>
<!-- 							<li><a href="#">Deals & Coupons</a></li> -->
							
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				Link list -->
<!-- 				<div class="col-lg-2 col-md-3 offset-md-1 offset-lg-0"> -->
<!-- 					<div class="block"> -->
<!-- 						<h4>關於我們</h4> -->
<!-- 						<ul> -->
<%-- 							<li><a href="<%=request.getContextPath()%>/back-end/user/page/logIn.jsp">介紹</a></li> --%>
							
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="col-lg-4 col-md-7"></div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
		<div>
		<jsp:include page="/shake_404/wenDaTi.jsp"></jsp:include>		
		</div><br>
	<label style="margin-left:450px; font-color:blue;">
	<a href="<%=request.getContextPath()%>/front-end/ad_table/Q&A.jsp">Q&A</a></label>
	<label style="margin-left:250px; font-color:blue;">
	<a href="<%=request.getContextPath()%>/back-end/user/page/logIn.jsp">關於我們</a></label>
		
		<!-- Container End -->
<!-- 	</footer> -->
	<!-- JAVASCRIPTS -->
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/bootstrap-slider.js"></script>
	<!-- tether js -->
	<script src="<%=request.getContextPath()%>/plugins/tether/js/tether.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/raty/jquery.raty-fa.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/fancybox/jquery.fancybox.pack.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/smoothscroll/SmoothScroll.min.js"></script>
	<!-- google map -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCcABaamniA6OL5YvYSpB3pFMNrXwXnLwU&libraries=places"></script>
	<script src="<%=request.getContextPath()%>/plugins/google-map/gmap.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>

</body>
</html>



