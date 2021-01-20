<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.feedback_table.model.*"%>


<%
    Feedback_TableService feedback_TableSvc = new Feedback_TableService();
    List<Feedback_TableVO> list = feedback_TableSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有調查表資料 - </title>

<style>
ul, li {
	list-style: none;
}
  table#table-1 {
	background-color: #f5bc07;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h3{
  text-align: center;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  h3{
text-align: center;
}
table{
    width:700px;
    
/*    border-collapse: collapse;  */
   /*  格子各有一條線變成一條 */
}
td{
    border-bottom: 1px solid white;padding:20px;
}
tr:nth-child(1){
    background-color: orange;color:black;
}
tr:nth-child(3){
    background-color: wheat;color:black;
}
tr:nth-child(5){
    background-color: wheat;color:black;
}
tr:nth-child(even){
    background-color: burlywood;

}
tr:nth-child(6){
    background-color: orange;color:black;
}
.keyword{
color:brown
}

/* 確認 */
.btn {
  display: inline-block;
  border-radius: 4px;
  background-color: #f4511e;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 20px;
  width: 100px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 1px;
}

.btn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.btn span:after {
  content: '»';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.btn:hover span {
  padding-right: 25px;
}

.btn:hover span:after {
  opacity: 1;
  right: 0;
}
/* 取消 */
.btn1{
  display: inline-block;
  border-radius: 4px;
  background-color: gray;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 20px;
  width: 100px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 1px;
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
.content {
width: 1200px;
margin-left: auto; 
margin-right: auto;
}
.box{
 text-align: center;
}
  
</style>

</head>
<body bgcolor='white'>
	<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
	</div>
<br><br><br><br><br><br>
<%-- 錯誤表列 --%>
<div style="text-align:center;">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if></div>
<h3>單筆調查表查詢:</h3>
<div style="text-align:center;">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Feedback_Table/Feedback_Table.do" >
        <b>輸入訂單編號 :</b>
        <input type="text" name="order_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</div>
<table class="content" style="weight:200px;height:300px;border:3px tomato dashed; margin-top:20px;margin-bottom:50px;">
	<tr style="text-align:center;">
		<th>訂單編號</th>
		<th>餐廳編號</th>
		<th>調查表評論</th>
		<th>調查表評論時間</th>
		<th>是否推薦給朋友</th>
		<th>星星數</th>
		<th>評論刪除時間</th>
		<th>調查表刪除狀態</th>
		<th>修改</th>
		
	</tr>
	<div style="text-align:center; margin-top:50px;">
	<%@ include file="page1.file" %>
	</div>
	<c:forEach var="feedback_tableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${feedback_tableVO.order_id}</td>
			<td>${feedback_tableVO.rs_id}</td>
			<td>${feedback_tableVO.common}</td>
			<td>${feedback_tableVO.common_time}</td>
			<td>${feedback_tableVO.push_yn}</td>
			<td>${feedback_tableVO.order_star}</td>
			<td>${feedback_tableVO.common_status==0?"none":feedback_tableVO.common_cancel_time}</td> 
			<td>${feedback_tableVO.common_status==0?"未刪除":"已刪除"}</td> 
			 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Feedback_Table/Feedback_Table.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_id"  value="${feedback_tableVO.order_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="gs_email"  value="${guest_tableVO.gs_email}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
 	</c:forEach>
</table>
<div style="text-align:center;">
<%@ include file="page2.file" %> 
</div>
</body>
</html>