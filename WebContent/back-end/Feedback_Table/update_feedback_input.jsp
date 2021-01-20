<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feedback_table.model.*"%>

<%
  Feedback_TableVO feedback_TableVO = (Feedback_TableVO) request.getAttribute("feedback_TableVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>調查表資料新增</title>

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
  ul, li {
	list-style: none;
}
</style>

</head>
<body bgcolor='white'>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div>

<div style="margin-top: 150px;">
<div style="text-align: center;">
<h3>資料修改</h3><br>
</div></div>
<div style="text-align:center;">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if></div>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Feedback_Table/Feedback_Table.do" name="form1">
<table class="content"
	style="weight: 300px; height: 300px; border: 3px tomato dashed; margin-top: 20px; margin-bottom: 20px; margin: auto ; ">
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=feedback_TableVO.getOrder_id()%></td>
	</tr>
	<tr>
		<td>餐廳編號:</td>
		<td><input type="TEXT" name="rs_id" size="25"
			 value="<%=feedback_TableVO.getRs_id()%>"/></td>
	</tr>
	<tr>
		<td>餐廳評論:</td>
		<td><textarea cols="50" rows="5" name="common" 
		  placeholder="輸入你想說的話..."><%=feedback_TableVO.getCommon()%></textarea></td>
		
	</tr>
	<tr>
		<td>評論時間:</td>
		<td><input name="common_time" id="f_date1" size="7" type="text"/></td>
	</tr>
	<tr>
		<td>是否推薦給朋友:</td>
		<td><input type="TEXT" name="push_yn" size="5"
			 value="<%=feedback_TableVO.getPush_yn()%>"/></td>
	</tr>
<tr>
		<td>星星數:</td>
		<td><input type="TEXT" name="order_star" size="18"
			 value="<%=feedback_TableVO.getOrder_star()%>"/></td>
	</tr>
	
	<tr>
		<td>評論刪除時間:</td>
		<td><input name="common_cancel_time" id="f_date2" size="7" type="text"/></td>
	</tr>
	
	<tr>
		<td>刪除狀態:</td>
		<td><input type="TEXT" name="common_status" size="25"
			 value="<%=feedback_TableVO.getCommon_status()%>"/></td>
	</tr>
	
	
	
	

</table><div style="text-align: center;"><br>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="order_id" value="<%=feedback_TableVO.getOrder_id()%>">
<input type="submit" value="送出修改"></div></FORM>
</body>

<% 
  java.sql.Date common_time = null;
  try {
	  common_time  = feedback_TableVO.getCommon_time();
   } catch (Exception e) {
	   common_time = new java.sql.Date(System.currentTimeMillis());
   }
  
  
  java.sql.Date common_cancel_time = null;
  try {
	  common_cancel_time  = feedback_TableVO.getCommon_cancel_time();
   } catch (Exception e) {
	   common_cancel_time = new java.sql.Date(System.currentTimeMillis());
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
		   value: '<%=common_time%>', // value:   new Date(),
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
		   value: '<%=common_cancel_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
 </script>       
</html>