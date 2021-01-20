<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad_table.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad_table.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Ad_TableVO ad_TableVO = (Ad_TableVO) request.getAttribute("ad_TableVO");
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
		 <br> <br> <br> <br> <br> <br> <br><h3>餐廳訂購廣告明細(單筆查詢)</h3>
<table class="content"
	style="weight: 200px; height: 250px; border: 3px tomato dashed; margin-top: 50px; margin-bottom: 30px;">
	<tr style="text-align: center;">
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
		</tr>
		<tr>
			<td><%=ad_TableVO.getAd_serial()%></td>
			<td><%=ad_TableVO.getAd_top_yn()%></td>
			<td><%=ad_TableVO.getRs_id()%></td>
			<td><%=ad_TableVO.getAd_price()%></td>
			<td><%=ad_TableVO.getAd_top_time1()%></td>
			<td><%=ad_TableVO.getAd_top_time2()%></td>
			<td><%=ad_TableVO.getAd_top_time3()%></td>
			<td><%=ad_TableVO.getAd_time()%></td>
			<td><%=ad_TableVO.getAd_showtime()%></td>
			<td><%=ad_TableVO.getAd_cancel_time()%></td>
			<td><%=ad_TableVO.getAd_status()%></td>

			<%
				String photoString = null;
				try {
					Base64.Encoder encoder = Base64.getEncoder();
					photoString = encoder.encodeToString(ad_TableVO.getAd_pic_queue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>

			<td><img src="data:image;base64,<%=photoString%>"></td>
		</tr>
	</table>

</body>
</html>