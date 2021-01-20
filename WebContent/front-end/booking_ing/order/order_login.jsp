<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- !-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-use/bking_table/order_after.css">
	
<style type="text/css">
#thanks {
	border: 5px solid #666;
	width: 600px;
	height: 500px;
	border-radius: 50px;
	margin: 0px auto;
	text-align: center;
}

#h1_ {
	margin: 0px;
	font-size: 2cm;
}

#p_ {
	font-size: 140%;
	color: red;
}
</style>
</head>

<body>
	<nav class="navbar navbar-expand-lg shadow navbar-light bg-light"
		id="navbar_bs"
		style="padding-top: 0px; padding-bottom: 0px; padding-left: 0px;">
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp"><img
			style="width: 120px;" alt=""
			src="<%=request.getContextPath()%>/front-end/booking_fixed/images/g11.jpg"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
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
	<div id="thanks">

		<h1 id="h1_">請登入會員</h1>
		<p id="p_">
			5秒後到會員登入頁面
		</p>
		
		<form action="" method="post">
			<a class="button"
				href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">返回首頁</a>

		</form>
	</div>
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
		<script type="text/javascript">
		$(document).ready(function(){
			setInterval(go, 1000);
			
		});
		var x=5; //利用了全局變量來執行
		function go(){
		x--;
		if(x>0){
		document.getElementById("p_").innerHTML=x+"秒後到會員登入頁面"; //每次設置的x的值都不一樣了。
		}else{
		location.href='<%=request.getContextPath()%>/front-end/Guest_Table/login.jsp';
		}
		}
		
		</script>
		
	
</body>

</html>