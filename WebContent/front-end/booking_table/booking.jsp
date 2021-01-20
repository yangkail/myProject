<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.servlet.http.Part"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.feedback_table.model.Feedback_TableVO"%>
<%@page import="com.feedback_table.model.Feedback_TableService"%>

<%	

	Rest_TableService rest_Tablesvc = new Rest_TableService();
	String rs_id = request.getParameter("value");
	Rest_TableVO rest_TableVO = (Rest_TableVO) rest_Tablesvc.getOneAll(rs_id);
	pageContext.setAttribute("rest_TableVO", rest_TableVO);
	String addressLine=rest_TableVO.getRs_address();
	String[] address=addressLine.split(",");
	String realyAddress=address[2];
	pageContext.setAttribute("address", realyAddress);
	Rest_TableVO rest_TablePhoto = (Rest_TableVO) rest_Tablesvc.getOneAllOnlyPic(rs_id);
	pageContext.setAttribute("rest_TablePhoto", rest_TablePhoto);
	
	

	
	
	Feedback_TableService fbtService = new Feedback_TableService();
	List<Feedback_TableVO> statList = fbtService.getRs_star(rs_id);
	pageContext.setAttribute("statList", statList);
	
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap-slider.css">
<!-- Font Awesome -->
<link
	href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Owl Carousel -->
<link
	href="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/plugins/slick-carousel/slick/slick-theme.css"
	rel="stylesheet">
<!-- Fancy Box -->
<link
	href="<%=request.getContextPath()%>/plugins/fancybox/jquery.fancybox.pack.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/plugins/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<!-- CUSTOM CSS -->
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet">
	<link rel="stylesheet" href="http://www.w3ii.com/lib/w3.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/main.css">
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/reset.css"> --%>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/style.css">
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<%-- 	<script src="<%=request.getContextPath() %>/js/index_use/main.js"></script> --%>


<style>


/*  img{ */
/*     display: block; */
/*     min-width: 100%; */
/*     min-height: 100%; */
/*     object-fit:cover  */
/* } */



#SEARCH {
	border: 2px red solid;
	float: top;
	padding: 5px 0 5px 0;
	margin-bottom: 0px;
	line-height: 35px;
	width: 100%;
}

 #DIV1 {
 	width: 700px; 
 	/* 	line-height: 400px;*/  
 	padding: 20px; 
 	border: 10px #yellow outset; 
 	margin-right: 10px; 
 	margin-left: 40px;
 	margin-top: 15px; 
 	margin-bottom: 30px; 
	float: left; 
 	border-radius:30px; 
 } 

 #DIV2 { 
 	width: 1300px; 
 /* 	line-height: 400px; */
 	padding: 20px; */
 	border: 10px #ff007b26 outset; 
 	float: RIGHT; 
	margin-right: 60px; 
 	margin-left: 60px; 
 	margin-top: 15px; 
 	margin-bottom: 30px; 
 	float: right; 
	border-radius:30px; 
} 

/* 訂位 */
#DIV3 {
	width: 270px;
	/* 	line-height: 400px; */
	padding: 5px;
	
	margin-right: 200px;
	margin-left: 1100px;
	margin-top: 300px;
	margin-bottom: 50px;
	bgcolor:white;
	position:absolute;
}

/* 調查表 */
#DIV4 {            
	width: 890px;
/*  	line-height: 25px;  */
	padding: 10px;
/*  	border: 5px white outset;  */
	margin-right: 60px;
	margin-left: 100px;
	margin-top: 650px;
	margin-bottom: 100px;
	display: block;
   
    object-fit:cover;
	position:absolute;
}

body {
	padding: 20px;
	background: #eee;
	user-select: none;
}

[type=radio] {
	display: none;
}

#slider {
	height: 35vw;
	position: relative;
	perspective: 1000px;
	transform-style: preserve-3d;
}

#slider label {
	margin: auto;
	width: 60%;
	height: 80%;
	border-radius: 4px;
	position: absolute;
	left: 0;
	right: 0;
	cursor: pointer;
	transition: transform 0.4s ease;
}

#s1:checked ~ #slide4, #s2:checked ~ #slide5, #s3:checked ~ #slide1, #s4:checked 
	~ #slide2, #s5:checked ~ #slide3 {
	box-shadow: 0 1px 4px 0 rgba(0, 0, 0, .37);
	transform: translate3d(-30%, 0, -200px);
}

#s1:checked ~ #slide5, #s2:checked ~ #slide1, #s3:checked ~ #slide2, #s4:checked 
	~ #slide3, #s5:checked ~ #slide4 {
	box-shadow: 0 6px 10px 0 rgba(0, 0, 0, .3), 0 2px 2px 0
		rgba(0, 0, 0, .2);
	transform: translate3d(-15%, 0, -100px);
}

#s1:checked ~ #slide1, #s2:checked ~ #slide2, #s3:checked ~ #slide3, #s4:checked 
	~ #slide4, #s5:checked ~ #slide5 {
	box-shadow: 0 13px 25px 0 rgba(0, 0, 0, .3), 0 11px 7px 0
		rgba(0, 0, 0, .19);
	transform: translate3d(0, 0, 0);
}

#s1:checked ~ #slide2, #s2:checked ~ #slide3, #s3:checked ~ #slide4, #s4:checked 
	~ #slide5, #s5:checked ~ #slide1 {
	box-shadow: 0 6px 10px 0 rgba(0, 0, 0, .3), 0 2px 2px 0
		rgba(0, 0, 0, .2);
	transform: translate3d(15%, 0, -100px);
}

#s1:checked ~ #slide3, #s2:checked ~ #slide4, #s3:checked ~ #slide5, #s4:checked 
	~ #slide1, #s5:checked ~ #slide2 {
	box-shadow: 0 1px 4px 0 rgba(0, 0, 0, .37);
	transform: translate3d(30%, 0, -200px);
}
p.i_just_p{
	font-size:20px; 
	color:black;
	font-weight: bold;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
</head>
<body>
<!--   style="bgcolor:orange;"> -->
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-expand-lg navbar-light navigation">
					<a class="navbar-brand" href="<%=request.getContextPath() %>/front-end/Guest_Table/home.jsp">
						<img src="<%=request.getContextPath() %>/images/logo2.png" alt="">
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav ml-auto main-nav ">
<!-- 							<LI CLASS="NAV-ITEM ACTIVE"> -->
<!-- 								<A CLASS="NAV-LINK" HREF="INDEX.HTML">首頁</A> -->
<!-- 							</LI> -->
<!-- 							<LI CLASS="NAV-ITEM DROPDOWN DROPDOWN-SLIDE"> -->
<!-- 								<A CLASS="NAV-LINK DROPDOWN-TOGGLE" DATA-TOGGLE="DROPDOWN" HREF="">訂位<SPAN><I CLASS="FA FA-ANGLE-DOWN"></I></SPAN> -->
<!-- 								</a> -->

<!-- 								Dropdown list -->
<!-- 								<div class="dropdown-menu"> -->
<!-- 									<a class="dropdown-item" href="dashboard.html">Dashboard</a> -->
<!-- 									<a class="dropdown-item" href="dashboard-my-ads.html">Dashboard My Ads</a> -->
<!-- 									<a class="dropdown-item" href="dashboard-favourite-ads.html">Dashboard Favourite Ads</a> -->
<!-- 									<a class="dropdown-item" href="dashboard-archived-ads.html">Dashboard Archived Ads</a> -->
<!-- 									<a class="dropdown-item" href="dashboard-pending-ads.html">Dashboard Pending Ads</a> -->
<!-- 								</div> -->
<!-- 							</li> -->
<!-- 							<li class="nav-item dropdown dropdown-slide"> -->
<!-- 								<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 									Pages <span><i class="fa fa-angle-down"></i></span> -->
<!--  								</a>   -->
<!-- 								Dropdown list -->
					
							</li>					
							<c:choose>
								<c:when test="${gs_email!=null }">
							
								<li><a class="nav-item dropdown dropdown-slide">歡迎，${gs_name }&nbsp&nbsp&nbsp&nbsp </a></li>
								
								<li class="nav-item dropdown dropdown-slide">
								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									會員專區 <span><i class="fa fa-angle-down"></i></span>
								</a>
<!-- 								Dropdown list -->
								<div class="dropdown-menu">
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/Guest_Table/guest_profile1.jsp">會員資料</a>								
							</div>					
								</li>	
							<li class="nav-item dropdown dropdown-slide">
								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									業者專區 <span><i class="fa fa-angle-down"></i></span>
								</a>
<!-- 								Dropdown list -->
								<div class="dropdown-menu">
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyLogin.jsp">業者登入</a>
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_addCompyRegister.jsp">業者註冊</a>
									
								</div>
							</li>
								
								
							<li >
								<a class="nav-item dropdown dropdown-slide" href="<%=request.getContextPath()%>/Guest_table/IndexServlet">登出</a>
							</li>
								
								
							</c:when>
							
							<c:when test="${gs_email==null }">
								<li class="nav-item dropdown dropdown-slide">
								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									會員專區 <span><i class="fa fa-angle-down"></i></span>
								</a>
<!-- 								Dropdown list -->
								<div class="dropdown-menu">
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/Guest_Table/login2.jsp">會員登入</a>
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/Guest_Table/register1.jsp">會員註冊</a>
									
								</div>
							</li>
							
							
									
							<li class="nav-item dropdown dropdown-slide">
								<a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									業者專區 <span><i class="fa fa-angle-down"></i></span>
								</a>
<!-- 								Dropdown list -->
								<div class="dropdown-menu">
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyLogin.jsp">業者登入</a>
									<a class="dropdown-item" href="<%=request.getContextPath() %>/front-end/compy_table/front_addCompyRegister.jsp">業者註冊</a>
								</div>
							</li>
						
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
		
<!-- ---------------------------------------------------------------------		 -->
	
<div class="w3-content" style="max-width:1100px; margin-left:250px;"><br>

<% String photoString = null;
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view1());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">


<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view2());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">

<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view3());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">

<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view4());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">

<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view5());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;"><br>


  

<div class="w3-col s4">
    <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view1());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; height:150px; float:left; display:inline;" onclick="currentDiv(1)">
</div>

    <div class="w3-col s4">
    <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view2());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; height:150px;float:left; display:inline;" onclick="currentDiv(2)">
</div>
    
    <div class="w3-col s4">
       <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view3());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; height:150px;float:left; display:inline;" onclick="currentDiv(3)">
</div>
    
 
    <div class="w3-col s4">
    <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view4());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; height:150px;float:left; display:inline;" onclick="currentDiv(4)">
</div>

  	<div class="w3-col s4">
        <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view5());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; height:150px;float:left; display:inline;" onclick="currentDiv(5)">
</div>
	
</div>
<!-- ---------------------------------------------------		 -->
		
	

	<div id="DIV2" >
	<a>
	<br><center><p style="font-size:60px; font-weight:bold; color:black;">${rest_TableVO.rs_name}</p></center><br>
    <p class="i_just_p" style="line-height: 0px;">地址: ${address}</p>
    <p class="i_just_p" style="line-height: 0px;">電話: ${rest_TableVO.rs_phone}</p>
    <p class="i_just_p" style="line-height: 0px;">餐廳類型: ${rest_TableVO.rs_type}</p>
    <p class="i_just_p" style="line-height: 0px;">餐廳時間: ${rest_TableVO.rs_open_time==4?"11:00~21:00":"全時段"}</p>
    <p class="i_just_p">餐廳介紹: ${rest_TableVO.rs_intro}</p>
	</a>
	<div style="width: 100%; height: 200px;">
		<jsp:include page="/front-end/map/map.jsp"></jsp:include>
	</div>
		</div><br><br><br><br><br><br><br><br>
	

	<div>

		<div id="DIV3">
			<jsp:include page="/front-end/booking_ing/order/Seat_select_order.jsp" /> 
		</div>
	
	</div>
<br><br><br><br>
	<div>
		<div id="DIV4"><jsp:include
				page="/front-end/Feedback_Table/frontlistFeedback.jsp"></jsp:include></div> 
	</div>

	
<script>
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function currentDiv(n) {
  showDivs(slideIndex = n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  if (n > x.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = x.length} ;
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
     dots[i].className = dots[i].className.replace(" w3-border-red", "");
  }
  x[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " w3-border-red";
}

</script>

</body>

</html>