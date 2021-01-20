<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>假首頁-用於會員註冊</title>
    
    <style>
  table#table-1 {
    width: 450px;
	background-color: #f5bc07;
    border: 2px solid black;
    text-align: center;
  }
  </style>
</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>假首頁 </h3>
 		 <h4><img src="images/tomcat3.png" width="200" height="110" border="0"></a></h4> 
	</td></tr>
</table>

    <h1><a href="<%=request.getContextPath() %>/back-end/Guest_Table/register.jsp">會員-點擊我註冊</a></h1>
    <br>
    <br>
    <hr>
    <br>
    <br>
    <h1><a href="<%=request.getContextPath() %>/back-end/Guest_Table/listAllGuest.jsp">後台-點擊我看會員後臺資料</h1>
</body>
</html>
