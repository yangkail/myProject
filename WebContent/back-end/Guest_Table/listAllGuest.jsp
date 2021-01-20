<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.guest_table.model.*"%>


<%
    Guest_TableService guest_TableSvc = new Guest_TableService();
    List<Guest_TableVO> list = guest_TableSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - </title>

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
ul, li {
	list-style: none;
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
<h3>會員單筆查詢:</h3><div style="text-align:center;">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" >
        <b>輸入電子信箱 :</b>
        <input type="text" name="gs_email">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
<table class="content" style="weight:200px;height:300px;border:3px tomato dashed; margin-top:20px;margin-bottom:50px;">
	<tr style="text-align:center;">
		<th>電子信箱</th>
		<th>密碼</th>
		<th>會員姓名</th>
		<th>會員生日</th>
		<th>性別</th>
		<th>手機號碼</th>
		<th>住址</th>
		<th>信用卡</th>
		<th>註冊時間</th>
		<th>大頭貼</th>
<!-- 		<th>權限</th> -->
 		<th>修改</th>	
	</tr>
	<div style="text-align:center; margin-top:50px;">
	<%@ include file="page1.file" %>
	</div> 
	<c:forEach var="guest_tableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${guest_tableVO.gs_email}</td>
			<td>${guest_tableVO.gs_pwd}</td>
			<td>${guest_tableVO.gs_name}</td>
			<td>${guest_tableVO.gs_birth}</td>
			<td>${guest_tableVO.gs_sex}</td>
			<td>${guest_tableVO.gs_phone}</td>
			<td>${guest_tableVO.gs_address}</td> 
			<td>${guest_tableVO.gs_credit}</td> 
			<td>${guest_tableVO.gs_reg_time}</td> 
			<td><img src="<%=request.getContextPath() %>/Guest_Table/Guest_Table.do?action=display_pic&gs_email=${guest_tableVO.gs_email}"
			 width="160" height="160"></td>
<%-- 			<td>${guest_tableVO.authority}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="gs_email"  value="${guest_tableVO.gs_email}">
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