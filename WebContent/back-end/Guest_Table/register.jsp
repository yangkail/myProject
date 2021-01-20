<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.guest_table.model.*"%>

<%
  Guest_TableVO guest_TableVO = (Guest_TableVO) request.getAttribute("guest_TableVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料新增 - addguest_table.jsp</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #f5bc07;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員註冊 </h3></td><td style="text-align: center">
		 <h4><a href="<%=request.getContextPath() %>/back-end/Guest_Table/index.jsp"><img src="images/tomcat3.png" width="180" height="110" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>填寫會員基本資料:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/Guest_Table/Guest_Table.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>email:</td>
		<td><input type="TEXT" name="gs_email" size="25" 
			 value="${param.gs_email}" /></td><td>${errorMsgs.gs_email}</td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="gs_pwd" size="25"
			 value="${param.gs_pwd}"/></td><td>${errorMsgs.gs_pwd}</td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="gs_name" size="12"
			 value="${param.gs_name}"/></td><td>${errorMsgs.gs_name}</td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input name="gs_birth" id="f_date1" size="7" type="text"/></td><td>${errorMsgs.gs_birth}</td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="radio" name="gs_sex" 
			 value="1"/>男<input type="radio" name="gs_sex" 
			 value="0"/>女</td><td>${errorMsgs.gs_sex}</td>
	</tr>
<tr>
		<td>手機號碼:</td>
		<td><input type="TEXT" name="gs_phone" size="18"
			 value="${param.gs_phone}"/></td><td>${errorMsgs.gs_phone}</td>
	</tr>
	
	<tr>
		<td>住址:</td>
		<td><input type="TEXT" name="gs_address" size="25"
			 value="${param.gs_address}"/></td><td>${errorMsgs.gs_address}</td>
	</tr>
	
	<tr>
		<td>信用卡資訊:</td>
		<td><input type="TEXT" name="gs_credit" size="25"
			 value="${param.gs_credit}"/></td><td>${errorMsgs.gs_credit}</td>
	</tr>
	
<tr>
		<td>上傳照片:</td>
		<td><input type="FILE" name="gs_big_pic" size="25"
			 value=""/></td><td>${errorMsgs.gs_big_pic}</td>
	</tr>
	
	<tr>
		<td>註冊時間:</td>
		<td><input name="gs_reg_time" id="f_date2" size="7" type="text"/></td><td>${errorMsgs.gs_reg_time}</td>
	</tr>
	
	
	

</table>
<br>
<input type="hidden" name="action" value="back-insert">
<input type="submit" value="送出新增"></FORM>
${errorMsgs }
</body>

<% 
  java.sql.Date gs_birth = null;
  try {
	  gs_birth  = guest_TableVO.getGs_birth();
   } catch (Exception e) {
	   gs_birth = new java.sql.Date(System.currentTimeMillis());
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
		   value: '<%=gs_birth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
 </script>       
</html>