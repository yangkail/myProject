<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Ad_Table: Home</title>

<style>
/* table#table-1 { */
/* 	background-color: orange; */
/* 	border: 2px solid black; */
/* 	text-align: center; */
/* } */
table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 111px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 500px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

ul, li {
	list-style: none;
}

.ull.li {
	margin: 0, -10 auto;
}

.font {
	font-weight: bold;
	color: red;
}
</style>



</head>
<body bgcolor=''>
	<div class="headerer">
		<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
	</div>
<div class="topBackplane" style="text-align:center;">
		<div class="displayTopPlane"></div>
			<h3><a
				href="<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableSelect_page.jsp">
				<img src="<%=request.getContextPath()%>/images/logo.jpg" style="width:25%; margin:auto;">
			</a></h3></div>
<div style="margin-top:2px;margin-bottom:0px; text-align:center;">
    <div class="title_word">
<!--     	<h3>廣告查詢</h3> -->
    </div></div>
<div style="text-align:center;">	
<ul>
<li><h3><a href='<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableList_All.jsp'>查詢所有餐廳廣告明細</a></h3><br><br></li>
  <%-- 錯誤表列 --%>
  
<div style="text-align:center;">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/controller/Ad_TableDAOServlet" >
        <b>輸入廣告編號:</b>
        <input type="text" name="ad_serial" size=6>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="ad_tableSvc" scope="page" class="com.ad_table.model.Ad_TableService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/controller/Ad_TableDAOServlet" >
       <b>選擇廣告編號:</b>
       <select size="1" name="ad_serial">
         <c:forEach var="ad_TableVO" items="${ad_tableSvc.all}" > 
          <option value="${ad_TableVO.ad_serial}">${ad_TableVO.ad_serial}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>

   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/controller/Ad_TableDAOServlet" >
       <b>選擇餐廳編號:</b>
       <select size="1" name="rs_id">
         <c:forEach var="ad_TableVO" items="${ad_tableSvc.all}" > 
          <option value="${ad_TableVO.rs_id}">${ad_TableVO.rs_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display1">
       <input type="submit" value="送出">
    </FORM>
   </li>
  

<%--   <li><a href='<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableSelectRsId.jsp'>查詢餐廳編號點這裡</a><br><br></li> --%>
</ul>  
</div>
 <div style="text-align:center;">
<br><br>
<ul>
   <li><h3><a href='<%=request.getContextPath()%>/back-end/Ad_Table/Ad_TableAdd.jsp'>後台新增餐廳廣告</a></h3></li>
</ul></div><br><br>

</body>
</html>