<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rest_table.model.*"%>
<%@ page import="java.util.*" %>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
List<Rest_TableVO> list = (List<Rest_TableVO>) request.getAttribute("list"); //Servlet.java(Concroller), �s�Jreq��VO����
%>

<html>
<head>
<title>�\�U�d�� - listOne rs_type.jsp</title>

<style>
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
	width: 600px;
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
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/Rest_TableSearch/Rs_TableSelect_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�\�U�W��</th>
		<th>�\�U�a�}</th>
		<th>�\�U�q��</th>
		<th>�\�U����</th>
		<th>�\�U���O</th>
		<th>�\�U�j�Y��</th>
		<th>�\�U�y���</th>
		<th>�\�U��~�ɶ�</th>
	
		
	</tr>
	<tr>
	  <c:forEach var="rest_TableVO" items="${list}">
	  	<tr>
		<td>${rest_TableVO.rs_name}</td>
		<td>${rest_TableVO.rs_address}</td>
		<td>${rest_TableVO.rs_phone}</td>
		<td>${rest_TableVO.rs_intro}</td>
		<td>${rest_TableVO.rs_type}</td>
		<td>${rest_TableVO.rs_big_pic}</td>
		<td>${rest_TableVO.rs_pic}</td>
		<td>${rest_TableVO.rs_open_time}</td>
	   </tr>
		</c:forEach>

</table>

</body>
</html>