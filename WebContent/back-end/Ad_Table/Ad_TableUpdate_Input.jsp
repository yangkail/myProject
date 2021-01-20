<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad_table.model.*"%>

<%
Ad_TableVO ad_TableVO = (Ad_TableVO) request.getAttribute("ad_TableVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�s�i��ƭק� - Ad_TableUpdate_Input.jsp</title>

<style>

img{
width:200px;
}
  table#table-1 {
	background-color: #CCCCFF;
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
	width: 450px;
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


</div><br><br><br><br><br><br>
<div style="text-align: center;">
<h3>��ƭק�</h3></div>
<div style="margin-top:2px;margin-bottom:0px; text-align:center;">
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if></div>
<div style="margin-top: 30px;">
<div style="text-align: center;">
</div></div>
<FORM METHOD="post" ACTION="/TEA102G1/controller/Ad_TableDAOServlet" name="form1" enctype="multipart/form-data">
<table class="content"
	style="weight: 200px; height: 400px; border: 3px tomato dashed; margin-top: 20px; margin-bottom: 20px; margin: auto ; ">
	<tr>
		<td>�s�i�y���s�� :<font color=red><b>*</b></font></td>
		<td><%=ad_TableVO.getAd_serial()%></td>
	</tr>
	
	<tr>
		<td>�s�i�O�_�ܳ�:</td>
         <td>
		<select name="ad_top_yn">
		<option value="">�п��</option>
		<option value="0"${ad_TableVO.getAd_top_yn()=="0"?'selected':''}>0</option>
		<option value="1"${ad_TableVO.getAd_top_yn()=="1"?'selected':''}>1</option>
		</select></td>
	</tr>
	<tr>
		<td>�\�U�s��:</td>
		<td><input type="TEXT" name="rs_id" 
			 value="<%= (ad_TableVO==null)? "RS10001" : ad_TableVO.getRs_id()%>" /></td>
	</tr>
	<tr>
		<td>�s�i�ɬq�O��:</td>			
		 <td>
		<select name="ad_price">
		<option value="">�п��</option>
		<option value="1000"${ad_TableVO.getAd_price()=="1000"?'selected':''}>1000</option>
		<option value="2000"${ad_TableVO.getAd_price()=="2000"?'selected':''}>2000</option>
		<option value="3000"${ad_TableVO.getAd_price()=="3000"?'selected':''}>3000</option>	
		
		</select></td>
	</tr>
	<tr>
		<td>�ʶR�s�i�ɬq1:</td>
        <td>
		<select name="ad_top_time1">
		<option value="">�п��</option>
		<option value="0"${ad_TableVO.getAd_top_time1()=="0"?'selected':''}>0</option>
		<option value="1"${ad_TableVO.getAd_top_time1()=="1"?'selected':''}>1</option>
		</select></td>
	</tr>
	<tr>
		<td>�ʶR�s�i�ɬq2:</td>		 
        <td>
		<select name="ad_top_time2">
		<option value="">�п��</option>
		<option value="0"${ad_TableVO.getAd_top_time2()=="0"?'selected':''}>�O</option>
		<option value="1"${ad_TableVO.getAd_top_time2()=="1"?'selected':''}>�_</option>
		</select></td>
	</tr>
	<tr>
		<td>�ʶR�s�i�ɬq3:</td>	
		<td>
		<select name="ad_top_time3">
		<option value="">�п��</option>
		<option value="0"${ad_TableVO.getAd_top_time3()=="0"?'selected':''}>�O</option>
		<option value="1"${ad_TableVO.getAd_top_time3()=="1"?'selected':''}>�_</option>
		</select></td> 
	</tr>
	<tr>
		<td>�s�i���߮ɶ�:</td>
		<td><input name="ad_time" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>�s�i����ɶ�:</td>
		<td><input name="ad_showtime" id="f_date2" type="text"></td>
	</tr>
	<tr>
		<td>�s�i�����ɶ�:</td>
		<td><input name="ad_cancel_time" id="f_date3" type="text"></td>
	</tr>
	
	<tr>
		<td> �s�i���A:</td>
        <td>
		<select name="ad_status">
		<option value="">�п��</option>
		<option value="0"${ad_TableVO.getAd_status()=="0"?'selected':''}>�W�[</option>
		<option value="1"${ad_TableVO.getAd_status()=="1"?'selected':''}>�U�[</option>
		</select></td>

	</tr>
	<tr>
		<td> �����s�i����:</td>
		<td><input type="File" name="ad_pic_queue" id="ad_pic_queue" accept="image/*"/>
	   </td>
	</tr>
	<tr>
		<td> �Ϥ��w����:</td>
		<td><div id = "preview"><img></div></td>
	</tr>
	
	<jsp:useBean id="ad_tableSvc" scope="page" class="com.ad_table.model.Ad_TableService" />
</table>
<div style="text-align: center;"><br>
<br>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ad_serial" value="<%=ad_TableVO.getAd_serial()%>">
<input type="submit" value="�e�X�ק�"></div></FORM>
</body>


<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date ad_time = null;
  try {
	  ad_time = ad_TableVO.getAd_time();
   } catch (Exception e) {
	   ad_time = new java.sql.Date(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Date ad_showtime = null;
  try {
	  ad_showtime = ad_TableVO.getAd_showtime();
   } catch (Exception e) {
	   ad_showtime = new java.sql.Date(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Date ad_cancel_time = null;
  try {
	  ad_cancel_time = ad_TableVO.getAd_cancel_time();
   } catch (Exception e) {
	   ad_cancel_time = new java.sql.Date(System.currentTimeMillis());
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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=ad_time%>',  // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: 'dark',             
	       timepicker:false,       
	       step: 1,                
	       format:'Y-m-d',         
		   value: '<%=ad_showtime%>',  
       
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              
	       timepicker:false,       
	       step: 1,                
	       format:'Y-m-d',      
		   value: '<%=ad_cancel_time%>',  
          
        });
      
</script>
  <script>  
      $("input#ad_pic_queue").change(function(){
    	  if(this.files.length==1){
    		  let reader = new FileReader();
    		  reader.readAsDataURL(this.files[0]);
    		  reader.addEventListener("load",function(){
    			  $("div#preview img").attr("src",reader.result);
    		  })
    	  }else{
    		  $("div#preview").html("<img>");
    	  }
      });
    
</script>
</html>