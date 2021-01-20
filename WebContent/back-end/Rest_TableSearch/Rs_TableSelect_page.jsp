<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Ad_Table: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='orange'>

<table id="table-1">
   <tr><td><h3>IBM Rs_Table: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Ad_Table: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
   <li><a href='<%=request.getContextPath()%>/search?rs_type=台式&action=getOne_For_Display'>台式</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=日式&action=getOne_For_Display'>日式</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=韓式&action=getOne_For_Display'>韓式</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=西式&action=getOne_For_Display'>西式</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=美式&action=getOne_For_Display'>美式</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=泰式&action=getOne_For_Display'>泰式</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=印度式&action=getOne_For_Display'>印度式</a>

   </li>
</ul>

<ul>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/search" >
        <b>輸入餐廳類型:</b>
        <input type="text" name="rs_type">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
<!--           <b>輸入地址:</b> -->
<!--         <input type="text" name="rs_type"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
  </li>

  <jsp:useBean id="rest_tableSvc" scope="page" class="com.rest_table.model.Rest_TableService" />
   

</ul>  








</body>
</html>