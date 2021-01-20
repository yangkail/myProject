<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/index_use/footer.css">

<style>

#DIVFOOTER {
	padding: 5px 0 5px 0;
	border: 2px orange solid;
	margin-top: 10px;
	margin-bottom: 10px;
	float: left;
	line-height: 50px;;
	width: 100%;
}

#SEARCH {
	border: 2px red solid;
	float: top;
	padding: 5px 0 5px 0;
	margin-bottom: 0px;
	line-height: 35px;
	width: 100%;
}

#DIV1 {
	width: 600px;
/* 	line-height: 400px; */
	padding: 20px;
	border: 5px blue solid;
	margin-right: 30px;
	margin-left: 60px;
	margin-top: 15px;
	margin-bottom: 10px;
	float: left;
}

#DIV2 {
	width: 600px;
/* 	line-height: 400px; */
	padding: 20px;
	border: 5px green solid;
	float: RIGHT;
	margin-right: 60px;
	margin-left: 30px;
	margin-top: 15px;
	margin-bottom: 10px;

}

#DIV3 {
 	width: 100%; 
/* 	line-height: 400px; */
	padding: 2px;
	border: 5px pink solid;
	margin-top: 10px;
	margin-bottom: 5px;

	float: left;
	text-align: center;
}

#divpic1 {
	width: 280px;
	padding: 10px;
	border: 2px black solid;
	float: left;
	margin-right: 40px;
	margin-top: 15px;
	margin-left: 40px;
	margin-bottom: 15px;
}

#divpic {
	width: 280px;
	padding: 10px;
	border: 2px black solid;
	float: left;
	margin-right: 35px;
	margin-top: 15px;
	margin-left: 35px;
	margin-bottom: 15px;
}
</style>
</head>

<body>	
<!-- HEADER -->
	<div class="topBackplane">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<!-- HEADER -->

	<div class="fortwodiv" style="margin-top:200px;margin-bottom:150px;">
		<div id="DIV1"><img src="<%=request.getContextPath() %>/images/picc.jpg"  width="600" height="400"></div>
		<div id="DIV2"><img src="<%=request.getContextPath() %>/images/map.jpg"  width="600" height="400"></div>
	</div>

	<div class=pics>
		<div id="divpic1"><img src="<%=request.getContextPath() %>/images/superman.jpg"  width="280" height="280"></div>
		<div id="divpic"><img src="<%=request.getContextPath() %>/images/Wonder-Woman.jpg"  width="280" height="280"></div>
		<div id="divpic"><img src="<%=request.getContextPath() %>/images/shazam.jpg"  width="280" height="280"></div>
		<div id="divpic1"><img src="<%=request.getContextPath() %>/images/greenlantern.jpg"  width="280" height="280"></div>
	</div>

	<div class="booking">
		<div id="DIV3"><img src="<%=request.getContextPath() %>/images/seat.jpg"  width="" height="500"></div>
		
	</div>

	
	<!-- FOOTER -->
<!--     <div class="footer"> -->
<%-- 		<jsp:include page="footer.jsp"></jsp:include> --%>
<!-- 	</div> -->
	<!-- FOOTER -->
</body>

</html>