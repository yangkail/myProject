<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad_table.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
   Ad_TableService ad_TableService = new Ad_TableService();
    List<Ad_TableVO> list = ad_TableService.getAll();
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="ad_tableSvc" scope="page" class="com.ad_table.model.Ad_TableService" /> --%>

<html>
<head>
<title>所有廣告資料 - Ad_TableList_All.jsp</title>

<style>

img{
width:300px;
}
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
.box{
 text-align: center;
}
</style>

</head>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div>

<body bgcolor=''>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<br>
	<br>
	<br>
	
	<div style="text-align: center; margin-top: 80px;" >
	<%@ include file="page1.file"%>
	</div>

	<table class="content"
		style="weight: 200px; height: 300px; border: 3px tomato dashed; margin-top: 20px; margin-bottom: 50px;">
		<tr style="text-align: center;">
	<tr>
		<th>廣告流水編號</th>
		<th>廣告是否至頂</th>
		<th>餐廳編號</th>
		<th>廣告時段費用</th>
		<th>購買廣告時段1</th>
		<th>購買廣告時段2</th>
		<th>購買廣告時段3</th>
		<th>廣告成立時間</th>
		<th>廣告到期時間</th>
		<th>廣告取消時間</th>
		<th>廣告狀態</th>
		<th>首頁廣告輪播</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	<c:forEach var="ad_TableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ad_TableVO.ad_serial}</td>
			<td>${ad_TableVO.ad_top_yn}</td>
			<td>${ad_TableVO.rs_id}</td>
			<td>${ad_TableVO.ad_price}</td>
			<td>${ad_TableVO.ad_top_time1}</td>
			<td>${ad_TableVO.ad_top_time2}</td>
			<td>${ad_TableVO.ad_top_time3}</td>
			<td>${ad_TableVO.ad_time}</td>
			<td>${ad_TableVO.ad_showtime}</td>
			<td>${ad_TableVO.ad_cancel_time}</td>
			<td>${ad_TableVO.ad_status}</td>
			<td id = "ad_pic_queue">
			<%
			String photoString = null;
			try{
				Ad_TableVO ad_TableVO =(Ad_TableVO)pageContext.getAttribute("ad_TableVO");
				Base64.Encoder encoder = Base64.getEncoder();
				photoString = encoder.encodeToString(ad_TableVO.getAd_pic_queue());
			}catch(Exception e){
				e.printStackTrace();
			}
			%>
			<img src="data:image;base64,<%=photoString %>">
			</td>
			
<%-- 		<td>${ad_TableVO.ad_pic_queue}</td> --%>

		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/controller/Ad_TableDAOServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ad_serial"  value="${ad_TableVO.ad_serial}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/controller/Ad_TableDAOServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ad_serial"  value="${ad_TableVO.ad_serial}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<div style="text-align: center;">
<%@ include file="page2.file"%>
</div>


</body>
</html>