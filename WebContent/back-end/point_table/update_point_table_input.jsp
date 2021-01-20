<%@ page import="java.util.*, java.text.DateFormat,java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.point_table.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
  Point_TableVO point_tableVO = (Point_TableVO) request.getAttribute("point_tableVO"); 
%>

<%--  <%System.out.println(order_tableVO);%> --%>

<%
   	Point_TableService point_tableSvc = new Point_TableService();
    List<Point_TableVO> list = point_tableSvc.getAll();
    pageContext.setAttribute("list",list);
    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>


<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員積分修改 </title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/select_page_use/style.css">
<style>

  table, th, td {
    border: 1px solid #ffa500;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  

  
div.c {
  position:fixed;
  top:120px;
  left:800px;
}
ul, li {
	list-style: none;
}
</style>
 




</head>
<body>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div><br><br><br><br><br><br>
<div style="text-align: center;">
<h3>資料修改</h3></div>
<div style="margin-top:2px;margin-bottom:0px; text-align:center;">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if></div>	
<div style="margin-top: 50px;">
<div style="text-align: center;">

</div></div>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/point_table/point_table.do" name="form1" enctype="multipart/form-data">
<div>
<table class="content"
	style="weight: 200px; height: 300px; border: 3px tomato dashed; margin-top: 20px; margin-bottom: 20px; margin: auto ; ">
	<tr>
		<td>積分流水號:</td>
		<td><input type="TEXT" name="point_sieral" size="45" 
			 value="<%= (point_tableVO==null)? "" : point_tableVO.getPoint_sieral()%>"/></td>
			 
	</tr>
	<tr>
		<td>訂單流水號:</td>
		<td><input type="TEXT" name="order_id" size="45"
			 value="<%= (point_tableVO==null)? "" : point_tableVO.getOrder_id()%>" /></td>
	</tr>	
	<tr>
		<td>消費者信箱:</td>
		<td><input type="TEXT" name="gs_email" size="45"
			 value="<%= (point_tableVO==null)? "" : point_tableVO.getGs_email()%>" /></td>
	</tr>

	<tr>
		<td>訂單新增積分:</td>
		<td><input type="TEXT" name="order_get_point" size="45"
			 value="<%= (point_tableVO==null)? "" : point_tableVO.getOrder_get_point()%>" /></td>
	</tr>
	<tr>
		<td>訂單使用積分:</td>
		<td><input type="TEXT" name="order_use_point" size="45"
			 value="<%= (point_tableVO==null)? "" : point_tableVO.getOrder_use_point()%>" /></td>
	</tr>
	
	<tr>
		<td>積分更新時間:</td>
		<td><input type="text" name="point_update_time" id="f_date1" size="45"
			 value="<%= (point_tableVO==null)? "" : point_tableVO.getPoint_update_time()%>" /></td>
	</tr>
	</table>
</div>
	
<div style="text-align: center;">
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="point_sieral" value="<%=point_tableVO.getPoint_sieral()%>">
<input type="submit" value="送出修改">  <input type="reset" value="重填">
</div></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
 		   value: '<%=point_tableVO.getPoint_update_time()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
     
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
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