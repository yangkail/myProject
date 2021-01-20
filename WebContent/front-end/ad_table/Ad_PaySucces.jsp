<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad_table.model.*"%>
<!DOCTYPE html>

<%
 Ad_TableVO ad_TableVO = (Ad_TableVO) request.getAttribute("ad_TableVO");
%>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>付款完成</title>
<style type="text/css">

/* 取消 */
.btn1 {
 display: inline-block;
 border-radius: 10px;
 background-color:dodgerblue;
 border: none;
 color: #FFFFFF;
 text-align: center;
 font-size: 15px;
 padding: 20px;
 width: 100px;
 transition: all 0.5s;
 cursor: pointer;
 margin: 250px;
}

.btn1 span {
 cursor: pointer;
 display: inline-block;
 position: relative;
 transition: 0.5s;
}

.btn1 span:after {
 content: '«';
 position: absolute;
 opacity: 0;
 top: 0;
 right: -20px;
 transition: 0.5s;
}

.btn1:hover span {
 padding-right: 25px;
}

.btn1:hover span:after {
 opacity: 1;
 right: 0;
}
.box1{position: absolute ;right:450px;top:300px;}
</style>
</head>


<body>
<center>
 <h1>Thank you!</h1>
 <h3>已付款完成,後台已幫你處理,請耐心等候,期待您下次光臨</h3>
 <h3>The payment has been completed, the background has processed
  it for you, please wait patiently and look forward to your next visit</h3>
  <div id="qrcode-size" class="box1"><h3>按讚支持我們的團隊</h3></div>

 



 <div class="box">
  <button class="btn1" style="vertical-align: middle">
  <a href="<%=request.getContextPath()%>/front-end/Guest_Table/home.jsp">
   <span>首頁</span></a>
  </button>


 </div>
<!--  <div id="qrcode-size" class="box1">歡迎按讚我們的粉絲團隊</div> -->

</body>
</center>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.qrcode/1.0/jquery.qrcode.min.js'></script>



<script>
$('#qrcode-size').qrcode({
 width: 100,
  height: 100,
  text: 'https://www.facebook.com/Tea102g1%E5%9C%98%E9%9A%8A-102411155167832'
});
 
</script>


</html>