<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Ad_TableRsId: Home</title>

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
<body bgcolor=''>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div><br><br><br><br><br><br>		
		 <h3>���O�̭q�����(�浧�d��)</h3>
<table class="content"
	style="weight: 200px; height: 250px; border: 3px tomato dashed; margin-top: 40px; margin-bottom: 30px;">
	<tr style="text-align: center;">


	
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

   <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/controller/Ad_TableDAOServlet" >
        <b>��J�\�U�s�� :</b>
        <input type="text" name="rs_id">
        <input type="hidden" name="action" value="getOne_For_Display1">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
 </ul> 
 <ul>
 
	<jsp:useBean id="ad_tableSvc" scope="page" class="com.ad_table.model.Ad_TableService" />
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/controller/Ad_TableDAOServlet" >
       <b>����\�U�s��:</b>
       <select size="1" name="rs_id">
         <c:forEach var="ad_TableVO" items="${ad_tableSvc.all}" > 
          <option value="${ad_TableVO.rs_id}">${ad_TableVO.rs_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display1">
       <input type="submit" value="�e�X">
    </FORM>
   </li>
</ul>  



<!-- <h3>�s�i�޲z</h3> -->

<!-- <ul> -->
<%--    <li><a href='<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableAdd.jsp'>Add</a> a new Ad_Table.</li> --%>
<!-- </ul> -->

</body>
</html>