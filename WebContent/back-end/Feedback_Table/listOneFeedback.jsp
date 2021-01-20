<%@page import="com.feedback_table.model.Feedback_TableVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.guest_table.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的Guest_TableVO物件--%>
 <%Feedback_TableVO feedback_TableVO = (Feedback_TableVO) request.getAttribute("order_id");%> 

<%-- 取出 對應的Guest_TableVO物件--%>


<html>
<head>
<title>調查表資料</title>

<style>


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

h3 {
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
}
</style>


<style>
table {
	width: 2000px;
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

h3 {
	text-align: center;
}

table {
	width: 700px;

	/*    border-collapse: collapse;  */
	/*  格子各有一條線變成一條 */
}

td {
	border-bottom: 1px solid white;
	padding: 20px;
}

tr:nth-child(1) {
	background-color: orange;
	color: black;
}

tr:nth-child(3) {
	background-color: wheat;
	color: black;
}

tr:nth-child(5) {
	background-color: wheat;
	color: black;
}

tr:nth-child(even) {
	background-color: burlywood;
}

tr:nth-child(6) {
	background-color: orange;
	color: black;
}

.keyword {
	color: brown
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
.btn1 {
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
ul, li {
	list-style: none;
}
.box {
	text-align: center;
}
</style>

</head>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div><br><br><br><br><br><br>

		 <h3>調查表查詢(單筆查詢)</h3>
<table class="content"
	style="weight: 200px; height: 140px; border: 3px tomato dashed; margin-top: 40px; margin-bottom: 30px;">
	<tr style="text-align: center;">
		<th>訂單編號</th>
		<th>餐廳編號</th>
		<th>調查表評論</th>
		<th>調查表評論時間</th>
		<th>是否推薦給朋友</th>
		<th>星星數</th>
		<th>評論刪除時間</th>
		<th>調查表刪除狀態</th>
		<th></th>
	</tr>
	<tr>
		<tr>
			<td>${feedback_TableVO.order_id}</td>
			<td>${feedback_TableVO.rs_id}</td>
			<td>${feedback_TableVO.common}</td>
			<td>${feedback_TableVO.common_time}</td>
			<td>${feedback_TableVO.push_yn}</td>
			<td>${feedback_TableVO.order_star}</td>
			<td>${feedback_TableVO.common_status==1?"none":feedback_tableVO.common_cancel_time}</td> 
			<td>${feedback_TableVO.common_status}</td> 
			 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Feedback_Table/Feedback_Table.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_id"  value="${feedback_TableVO.order_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
	</tr>
</table>

</body>
</html>