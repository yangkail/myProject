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

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
   <li><a href='<%=request.getContextPath()%>/search?rs_type=�x��&action=getOne_For_Display'>�x��</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=�馡&action=getOne_For_Display'>�馡</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=����&action=getOne_For_Display'>����</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=�覡&action=getOne_For_Display'>�覡</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=����&action=getOne_For_Display'>����</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=����&action=getOne_For_Display'>����</a>
   <a href='<%=request.getContextPath()%>/search?rs_type=�L�צ�&action=getOne_For_Display'>�L�צ�</a>

   </li>
</ul>

<ul>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/search" >
        <b>��J�\�U����:</b>
        <input type="text" name="rs_type">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
<!--           <b>��J�a�}:</b> -->
<!--         <input type="text" name="rs_type"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
  </li>

  <jsp:useBean id="rest_tableSvc" scope="page" class="com.rest_table.model.Rest_TableService" />
   

</ul>  








</body>
</html>