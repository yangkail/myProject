<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%
String rs_id = (String)session.getAttribute("rs_id");
if(rs_id==null){
	session.setAttribute("rs_id", "RS10001");
	rs_id ="RS10001";
}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" -->
<!-- 	crossorigin="anonymous"> -->
<!-- Latest compiled and minified CSS -->

<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/css/front-use/bking_table/bootstrap.min.css" /> --%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front-use/bking_table/datetimepicker/jquery.datetimepicker.css" />
</head>

<body>
	<!-- <%-- 錯誤表列 --%> -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<h2>立即訂位</h2>
	<form method="POST"
		action="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_Seat_OrderServlet.do"
		name="form1">
		<div class="mb-3">
			<label for="f_date1">日期</label>
			<div class="form-label">
				<input name="gs_select_date" id="f_date1" type="text">
				
			</div>
		</div>
		<div class="form-group">
			<label for="control-label">時段</label>
			 <select name="gs_select_time" class="form-control"
				id="control-label" style="width: 200px;">
				<option value="1">上午8點-下午13點</option>
				<option value="2">下午13點-晚上18點</option>
				<option value="3">晚上18點-晚上23點</option>
			</select>
		</div>
		<div class="form-group">
			<label for="control-label1">人數</label> 
			<select name="rs_table_seat" multiple
				class="form-control" id="control-label1" style="width: 200px;">
				<option value="1" selected="selected">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
		</div>
		<input type="hidden" name="action" value="get_set">
		<button type="submit" class="btn btn-danger btn-lg">查看選位</button>
	</form>
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
	<script src="<%=request.getContextPath()%>/css/front-use/bking_table/datetimepicker/jquery.datetimepicker.full.js"></script>
<%-- <%-- 	<%  --%>
<!-- //  java.sql.Date rs_seat_xy_time = null;  -->
<!-- //    try {  -->
<!-- // 	   rs_seat_xy_time = rest_Seat_Coordinate_TableVO.getRs_seat_xy_time(); -->
<!-- //   } catch (Exception e) {  -->
<!-- // 	  rs_seat_xy_time = new java.sql.Date(System.currentTimeMillis()); -->
<!-- //   }  -->
<%-- <%-- %>  --%> 
	<style>
	
	
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
	/* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
	/* height:  151px; */
}
</style>

	<script>
		$.datetimepicker.setLocale('zh');
		$('#f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : false, //timepicker:true,
			// 	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d', //format:'Y-m-d H:i:s',
			value : new Date(), // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	            '2017/07/10',  // 起始日
			minDate : '-1970-01-01', // 去除今日(不含)之前
		//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>
</body>

</html>