<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>標題</title>
</head>
<body>
	Hello:<br>
	<font color="green" size="22">
		<%
			out.print(request.getParameter("gs_email")+"<br>");
		%>
	</font>
	<a href="<%=request.getContextPath()%>/Guest_Table/login.jsp">重新登入</a>
</body>
</html>