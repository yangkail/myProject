<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_table.model.*"%>
<%@ page import="java.util.*, java.text.DateFormat,java.text.SimpleDateFormat"%>

<%
	Order_TableVO order_tableVO = (Order_TableVO) request.getAttribute("order_tableVO");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<head>
    <title>新增消費者訂單頁面</title>
</head>
</head>
<body>	
	<h1>新增消費者訂單頁面</h1>
	<form action="<%=request.getContextPath() %>/order_table/Order_TableServlet"  method="POST" enctype="multipart/form-data">
		<label> 訂單流水號 : <input type="text" name="order_id" size="50"></label><br><br> 
		<label> 餐廳編號 : 	<input type="text" name="rs_id" size="50"></label><br> <br>
		<label> 消費者信箱: 	<input type="text" name="gs_email" size="50"></label><br> <br> 
		<hr> 
		<label> 訂單狀態:
        <select name="order_status">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>     
        </select> 
   		</label><br> <br>
		<label>	訂單成立時間:<input type="text" name="order_time" id="f_date1" size="50"></label><br> <br>
		
		<label>	訂單取消時間:<input type="text" name="order_cancel_time" id="f_date2" size="50"></label>
		
		<br><br>
		<hr> 
		<label>	訂單備註:	<textarea name="gs_order_remark" cols="30" rows="10"></textarea></label><br> <br>
		<hr>
		<label> 用餐人數 : 	<input type="text" name="gs_people" size="50"></label><br> <br> 	
		<label> 消費者用餐時段:
        <select name="gs_select_time" >
          <option value="1">時段1(11:00-13:00)</option>
          <option value="2">時段2(13:00-15:00)</option>
          <option value="3">時段3(17:00-19:00)</option>
          <option value="4">時段4(17:00-19:00))</option>      
        </select> 
   		</label>
   		<br><br>
		<label> 使用訂金 : <input type="text" name="order_deposit" size="50"></label><br><br>
		<br>
		<hr>
		<label> 訂單QR條碼 : <input type="file" name="order_qrcode" id="order_qrcode" accept="image/*"></label>
		<div class="order_qrcode_diaplay"></div>
		<br>
		<label> 餐廳座位狀態 : <input type="text" name="rs_table_status" size="50"></label><br>
		
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="order_id" value="<%=(String)session.getAttribute("order_id")%>">
		<input type="submit" value="確認提交"> <input type="reset" value="重填">
	</form>
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
    
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp order_time = null;
  try {
	  order_time = order_tableVO.getOrder_time();
   } catch (Exception e) {
	   order_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Timestamp order_cancel_time = null;
  try {
	  order_cancel_time = order_tableVO.getOrder_cancel_time();
   } catch (Exception e) {
	   order_cancel_time = new java.sql.Timestamp(System.currentTimeMillis());
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
			    timepicker:true,       //timepicker:true,
			    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
			    format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
				   value: '<%=order_time%>', // value:   new Date(),
			   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			   //startDate:	            '2017/07/10',  // 起始日
			   //minDate:               '-1970-01-01', // 去除今日(不含)之前
			   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
			});
			
			
			$.datetimepicker.setLocale('zh');
			$('#f_date2').datetimepicker({
			   theme: '',              //theme: 'dark',
			    timepicker:true,       //timepicker:true,
			    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
			    format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
				 value: '<%=order_cancel_time%>', // value:   new Date(),
			   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			   //startDate:	            '2017/07/10',  // 起始日
			   //minDate:               '-1970-01-01', // 去除今日(不含)之前
			   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
			});
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>
