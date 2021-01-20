<%@page import="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@ page import="java.util.*"%>
<%     
String rs_id = (String)session.getAttribute("rs_id");
if(rs_id==null){
	session.setAttribute("rs_id", "RS10001");
	rs_id ="RS10001";
}
		int i = 1;
%>
<%-- <%String rs_id = session.getAttribute("rs_id",rs_id); %> --%>
<!-- 到時候合併需要處理-->
<!DOCTYPE html>
<html lang="zh-Hant">

<head>
<meta charset="utf-8">
<title></title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<!-- Latest compiled and minified CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.css'/>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css'/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/bking_table/css-loading-animations.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/bking_table/bk_table2.css">
</head>

<body>

	<div class="wrapper">
			<div class="inner">
				<span>讀</span> 
				<span>取</span> 
				<span>中</span>
			</div>
	</div>
	<nav class="navbar navbar-expand-lg shadow navbar-light bg-light" id="navbar_bs" style="
    padding-top: 0px;
    padding-bottom: 0px;
    padding-left: 0px;
    display: none;
    ">
  <a class="navbar-brand" href="<%=request.getContextPath() %>/front-end/Guest_Table/home.jsp"><img style="width: 120px;" alt="" src="<%=request.getContextPath() %>/front-end/booking_fixed/images/g11.jpg"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
<!--     <ul class="navbar-nav"> -->
<!--       <li class="nav-item active"> -->
<!--         <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" href="#">Features</a> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" href="#">Pricing</a> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
<!--       </li> -->
<!--     </ul> -->
  </div>
</nav>

	<div class="title_header_papa" style="display: none;"><h1 class="title_header">餐廳桌子平面圖</h1></div>
<!-- 	<div style="width: 200px;height: 300px"></div> -->
	<div class="drag_drop" style="display: none;">
		<div class="tab_panel drop_panel shadow" id="html-content-holder">
			
			<ul class="drop_ul"></ul>
		</div>
	</div>

	<jsp:useBean id="booking_Fixed_TableSvc" scope="page"
		class="com.booking_fixed_table.model.Booking_Fixed_TableService" />
	<jsp:useBean id="rest_Seat_Coordinate_TableSve" scope="page"
		class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />
	
	<article class="task_container" style="display: none;">

<!-- 		<button type="button" class="btn_empty"></button> -->
		<h1 class="title1">選擇桌號輸入可以訂位的人數並點選新增</h1>

		<div class="task_add_block">
			<b>選擇餐廳公版流水號:</b> 
			<select size="1" class="task_sel" name="rs_seat_sieral" id="selValue">
				<c:forEach var="rest_Seat_Coordinate_TableVO" items="${rest_Seat_Coordinate_TableSve.get_RS_ID_All(rs_id)}">
					<option value="<%=i%>-${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">桌號<%=i++%>
				</c:forEach>
			</select> <input type="text" class="task_name" placeholder="請輸入人數"> 
			<input type="hidden" name="rs_id" value="${rs_id}">
			<button type="button" class="task_add" id="submit">新增</button>
		</div>

		<div class="task_list_parent">
			<ul class="task_list">
			</ul>
		</div>



	</article>

	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
		integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<!-- //  Option 2: jQuery, Popper.js, and Bootstrap JS -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js'></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="<%=request.getContextPath()%>/js/front_use/bking_table/bk_table_index.js"></script>
</body>

</html>