<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.guest_table.model.*"%>
	<%
  Guest_TableVO guest_TableVO = (Guest_TableVO) request.getAttribute("guest_TableVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- SITE TITTLE -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>會員註冊</title>

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
<style>

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
									href="login2.jsp">登入</a></li>

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
						<h3 class="bg-gray p-4">會員註冊</h3>
						<form
							action="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do"
							method="post" name="form1" enctype="multipart/form-data">
							<fieldset class="p-4">
								<input type="email" placeholder="請輸入電子信箱" name="gs_email" 
									value="${param.gs_email}" class="border p-3 w-200 my-2" /> 
									<input type="password" name="gs_pwd" placeholder="請輸入密碼至少6碼"
									value="${param.gs_pwd}" class="border p-3 w-200 my-2" pattern="^[(a-zA-Z0-9_)]{6,10}$";/> 
									<input type="text" name="gs_name" placeholder="請輸入姓名" required="required"
									value="${param.gs_name}" class="border p-3 w-200 my-2" /><br>
									請選擇生日<input name="gs_birth" id="f_date1" type=text placeholder="請選擇生日"
									class="border p-3 w-200 my-2"  required="required"/><br>
									
									<div style="display: inline-block;">
									<label class="container" style='font-size: 20px'>男
  										<input type="radio"  name="gs_sex" value="1" required="required">
										<span class="checkmark"></span>
									</label>
									</div>
									<div style="display: inline-block;">
									<label class="container" style='font-size: 20px'>女
										<input type="radio" name="gs_sex" value="0">
										<span class="checkmark"></span>
									</label>
									</div>
									
									<input type="" name="gs_address" placeholder="請輸入住址" required="required"
									value="${param.gs_address}" class="border p-3 w-200 my-2" />
																			
																			
									
									
									
									
									
									<input type="test" name="gs_phone" placeholder="請輸入電話"
									value="${param.gs_phone}" class="border p-3 w-200 my-2" pattern="^[(0-9_)]{10}$";/> 
									
									<input name="gs_reg_time" id="f_date2" type="hidden" 
									class="border p-3 w-200 my-2" /><br>
									
									
									
									
									
									
		<td><input type="hidden" name="gs_credit" size="25"
			 value="${param.gs_credit}"/></td><td>${errorMsgs.gs_credit}</td>
			 
			 
			 <input type="hidden" name="gs_big_pic" value="" class="border p-3 w-200 my-2" /> 

								<%
 	if (request.getAttribute("message") != null) {
 		out.print(request.getAttribute("message"));
 	}
 %>




								<!--                    <div class="loggedin-forgot"> -->


								<!--                                     <input type="checkbox" -->
								<!-- 										id="keep-me-logged-in"> -->
								<!--                                     <label for="keep-me-logged-in" -->
								<!-- 										class="pt-3 pb-2">Keep me logged in</label> -->
								<!--                             </div> -->
								
								<input type="hidden" name="action" value="insert">
							
								<input type="submit"
									class="d-block py-3 px-5 bg-primary text-white border-0 rounded font-weight-bold mt-3" value="註冊">
								<a class="mt-3 d-block  text-primary" href="#">
<!--  							Forget Password?</a> <a class="mt-3 d-inline-block text-primary" -->
<!-- 									href="register.html">Register Now</a> -->
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

<% 
  java.sql.Date gs_birth = null;
  try {
	  gs_birth  = guest_TableVO.getGs_birth();
   } catch (Exception e) {
	   gs_birth = new java.sql.Date(System.currentTimeMillis());
   }
  
  
  java.sql.Date gs_reg_time = null;
  try {
	  gs_reg_time  = guest_TableVO.getGs_reg_time();
   } catch (Exception e) {
	   gs_reg_time = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=gs_birth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=gs_reg_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
 </script>       

</html>