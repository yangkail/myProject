<%@page import="com.order_table.model.Order_TableVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feedback_table.model.*"%>

<%
  Feedback_TableVO feedback_TableVO = (Feedback_TableVO) request.getAttribute("feedback_TableVO");
	String order_id = request.getParameter("order_id");
	String rs_id = request.getParameter("rs_id");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增調查表 - addFeedback.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/star/demo/styles.css">

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/jQuery/jquery.min.js"></script>	

<style>
.common ::-webkit-input-placeholder { 
color: red; }
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
  #submit{
    background-color:#F5BC07;
    }
</style>

</head>
<body bgcolor='white'>


<center>
<a href="<%=request.getContextPath() %>/front-end/Guest_Table/home.jsp">
<img src="<%=request.getContextPath()%>/images/tomcat3.png" width="180" height="110" border="0"></a>
<h3>服務滿意度調查表:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/Feedback_Table/Feedback_Table.do" name="form1">
<table >

<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="order_id" size="25" readonly="readonly"
			 value="<%=order_id %>"/></td><td>${errorMsgs.order_id}</td>
	</tr>
	<tr>
<!-- 		<td>餐廳編號:</td> -->
		<td><input type="hidden" name="rs_id" size="25"
			 value="<%=rs_id %>"/></td><td>${errorMsgs.rs_id}</td>
	</tr>



			<label for="glsr-ltr">這次服務的滿意度:</label>
				<div class="form-field">
					<select id="glsr-ltr" name="order_star" class="star-rating" required="required">
						<option value="">請點選給分</option>
						<option value="5">非常滿意</option>
						<option value="4">滿意</option>
						<option value="3">普通</option>
						<option value="2">不滿意</option>
						<option value="1">非常不滿意</option>
					</select>
				</div>
<tr>
		<td>評論:</td>
		<td><textarea cols="50" rows="5" name="common"  class="common" required="required"
		value="${param.common}" placeholder="請留下你寶貴的意見"></textarea></td><td>${errorMsgs.common}</td>
	</tr>
	<tr>
<!-- 		<td>評論時間:</td> -->
		<td><input name="common_time" id="f_date1" size="7" type="hidden"  readonly/></td><td>${errorMsgs.common_time}</td>
	</tr>
	<tr>
		<td>是否推薦給朋友:</td>
		
			<td><input type="radio" name="push_yn" required="required" 
			 value="1" aria-label="Radio button for following text input"/>是<input type="radio" name="push_yn" 
			 value="0" aria-label="Radio button for following text input"/>否</td><td>${errorMsgs.push_yn}</td></td><td>${errorMsgs.push_yn}</td>
	</tr>
	
	<tr>
<!-- 		<td>評論刪除時間:</td> -->
		<td><input name="common_cancel_time"
		id="f_date2" type="hidden"/></td><td>${errorMsgs.common_cancel_time}</td>
	</tr>
	
	<tr>
<!-- 		<td>調查表狀態:</td> -->
		<td><input type="hidden" name="common_status" size="25"
			 value="0"/></td><td>${errorMsgs.common_status}</td>
	</tr>
	
	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<button type="button" id="submit" onclick="gogo();")>送出調查表</button>
<input type="submit" id="real_submit" style="display: none;">
									
</FORM>
</center>
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
 <script src="<%=request.getContextPath() %>/star/src/star-rating.js?ver=3.4.0"></script>
	<script>
		var destroyed = false;
		var starratings = new StarRating( '.star-rating', {
			onClick: function( el ) {
				console.log( 'Selected: ' + el[el.selectedIndex].text );
			},
		});
		document.querySelector( '.toggle-star-rating' ).addEventListener( 'click', function() {
			if( !destroyed ) {
				starratings.destroy();
				destroyed = true;
			}
			else {
				starratings.rebuild();
				destroyed = false;
			}
		});
		
		function gogo(){
			console.log("按了")
			  swal("表單已送出", "積分增加20點!", "warning");
			  setTimeout(function() {
				  $('#real_submit').click()}, 2000); 
		}
		
		
	</script>
   
</html>