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
<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.2.0/anime.min.js"></script>
<title>標題</title>
<style > 
@import url('https://rsms.me/inter/inter-ui.css');
::selection {
  background: #2D2F36;
}
::-webkit-selection {
  background: #2D2F36;
}
::-moz-selection {
  background: #2D2F36;
}
 body { 
  background: white; 
   font-family: 'Inter UI', sans-serif; 
   margin: 0; 
   padding: 20px; 
 } 
.page {
  
  display: flex;
  flex-direction: column;
  height: calc(100% - 40px);
  position: absolute;
  place-content: center;
  width: calc(100% - 40px);
}
@media (max-width: 767px) {
  .page {
    height: auto;
    margin-bottom: 20px;
    padding-bottom: 20px;
  }
}
.container1 {
  display: flex;
  height: 320px;
  margin: 0 auto;
  width: 640px;
}
@media (max-width: 767px) {
  .container1 {
    flex-direction: column;
    height: 630px;
    width: 320px;
  }
}
.left {
  background: white;
  height: calc(100% - 40px);
  top: 20px;
  position: relative;
  width: 50%;
}
@media (max-width: 767px) {
  .left {
    height: 100%;
    left: 20px;
    width: calc(100% - 40px);
    max-height: 270px;
  }
}
.login {
  font-size: 50px;
  font-weight: 900;
  margin: 50px 40px 40px;
}
.eula {
  color: #999;
  font-size: 14px;
  line-height: 1.5;
  margin: 40px;
}
.right {
  
  box-shadow: 0px 0px 40px 16px rgba(0,0,0,0.22);
  color: #F1F1F2;
  position: relative;
  width: 50%;
}
@media (max-width: 767px) {
  .right {
    flex-shrink: 0;
    height: 100%;
    width: 100%;
    max-height: 350px;
  }
}
svg {
  position: absolute;
  width: 320px;
}
path {
  fill: none;
  stroke: url(#linearGradient);;
  stroke-width: 4;
  stroke-dasharray: 240 1386;
}
.form {
  margin: 40px;
  position: absolute;
}
label {
  color:  #c2c2c5;
  display: block;
  font-size: 14px;
  height: 16px;
  margin-top: 20px;
  margin-bottom: 5px;
}
input {
  background: transparent;
  border: 0;
  color: black;
  font-size: 20px;
  height: 30px;
  line-height: 30px;
  outline: none !important;
  width: 100%;
}
input::-moz-focus-inner { 
  border: 0; 
}
#submit {
  color: #707075;
  margin-top: 40px;
  transition: color 300ms;
}
#submit:focus {
  color: #f2f2f2;
}
#submit:active {
  color: #d0d0d2;
}

</style>
</head>
<body>
</head>

<body class="body-wrapper">


	
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
	
	
	
	
	<form action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do"
								method="post">
	<div class="page">
  <div class="container1">
    <div class="left">
      <div class="login">登入</div>
      <div class="eula"><a class="mt-3 d-block  text-primary" href="<%=request.getContextPath()%>/front-end/Guest_Table/forget.jsp">忘記密碼?</a>
      <a class="mt-3 d-inline-block text-primary"
										href="register1.jsp">沒有帳號? 馬上註冊</a>
      <p style="color:red">${message }</p>
      </div>
    </div>
    <div class="right">
      <svg viewBox="0 0 320 300">
        <defs>
          <linearGradient
                          inkscape:collect="always"
                          id="linearGradient"
                          x1="13"
                          y1="193.49992"
                          x2="307"
                          y2="193.49992"
                          gradientUnits="userSpaceOnUse">
            <stop
                  style="stop-color:#ff00ff;"
                  offset="0"
                  id="stop876" />
            <stop
                  style="stop-color:#ff0000;"
                  offset="1"
                  id="stop878" />
          </linearGradient>
        </defs>
        <path d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143" />
      </svg>
      <div class="form">
        <label for="email">電子信箱</label>
        <input type="email" id="email" name="gs_email" value="${param.gs_email}" required="required">
        <label for="password">密碼</label>
        <input type="password" id="password" name="gs_pwd" required="required" value="">
        <input type="submit" id="submit" value="登入">
        <input type="hidden" name="action" value="login">
      </div>
    </div>
  </div>
</div>
	</form>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
<!-- 		<section class="login py-5 border-top-1"> -->
<!-- 			<div class="container"> -->
<!-- 				<div class="row justify-content-center"> -->
<!-- 					<div class="col-lg-5 col-md-8 align-item-center"> -->
<!-- 						<div class="border"> -->
<!-- 							<h3 class="bg-gray p-4">會員登入</h3> -->
<%-- 							<form action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" --%>
<!-- 								method="post"> -->
<!-- 								<fieldset class="p-4"> -->
<!-- 									<input type="email" placeholder="電子信箱" name="gs_email" required="required"  -->
<%-- 										value="${param.gs_email}" class="border p-3 w-200 my-2"/> --%>
<!-- 									<input type="password" name="gs_pwd" placeholder="密碼" required="required" -->
<!-- 										value="" class="border p-3 w-200 my-2" /> -->
<%-- 									<p style="color:red">${message }</p> --%>




									<!--                    <div class="loggedin-forgot"> -->


									<!--                                     <input type="checkbox" -->
									<!-- 										id="keep-me-logged-in"> -->
									<!--                                     <label for="keep-me-logged-in" -->
									<!-- 										class="pt-3 pb-2">Keep me logged in</label> -->
									<!--                             </div> -->
<!-- 									<button type="submit" -->
<!-- 										class="d-block py-3 px-5 bg-primary text-white border-0 rounded font-weight-bold mt-3">登入</button> -->
<!-- 									<input type="hidden" name="action" value="login"> -->
<%-- 									<a class="mt-3 d-block  text-primary" href="<%=request.getContextPath()%>/front-end/Guest_Table/forget.jsp">忘記密碼?</a> <a class="mt-3 d-inline-block text-primary" --%>
<!-- 										href="register1.jsp">沒有帳號? 馬上註冊</a> -->
<!-- 								</fieldset> -->
<!-- 							</form> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</section> -->
	










	<!-- 	<div class="wrapper"> -->
	<!-- 		<form -->
	<%-- 			action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" --%>
	<!-- 			method="post"> -->
	<!-- 			<label>電子信箱:</label> <input type="text" name="gs_email" -->
	<%-- 				value="${param.gs_email}" /><br> --%>
	<!-- 			<br> <label>密碼：</label> <input type="password" name="gs_pwd" -->
<%-- 	value="${param.gs_pwd}" /> --%>
	<br>
<%-- 	<font color="red"> <% --%>
	
<%--  %> <!-- 			</font> --> <!-- 			<div id="submit"> --> <!-- 				<input type="submit" value="登入" /> --> --%>
		<!-- 			</div> --> <!-- 		</form> --> <!-- 	</div> -->
		
<script >
var current = null;
document.querySelector('#email').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: 0,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#password').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -336,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#submit').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -730,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '530 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
</script>	
</body>


</html>