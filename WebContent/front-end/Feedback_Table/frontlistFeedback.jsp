<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="com.rest_table.model.Rest_TableService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.feedback_table.model.*"%>



<%
	Feedback_TableVO feedback_TableVO = (Feedback_TableVO) request.getAttribute("feedback_TableVO");
	String rs_id=(String)request.getParameter("value");
	Feedback_TableService feedback_TableSvc = new Feedback_TableService();
	List<Feedback_TableVO> listd =  feedback_TableSvc.getRs_star(rs_id);
	session.setAttribute("rs_id", rs_id);
	pageContext.setAttribute("listd", listd);
	
	Integer star = feedback_TableSvc.avg_star();
	Integer rs_star = feedback_TableSvc.rs_avg_star(rs_id);
	pageContext.setAttribute("rs_star", rs_star);
	pageContext.setAttribute("star", star);
	
	Rest_TableService rest_TableService=new Rest_TableService();
	Rest_TableVO rs_name=rest_TableService.getOneAll(rs_id);
	pageContext.setAttribute("rs_name", rs_name);
	

%>

<html>
<head>
<title>所有調查表資料 -</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/star/demo/styles.css">
<style>
table#table-1 {
	
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: black;
	display: block;
	margin-bottom: 1px;
}

h3 {
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 850px;
	
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
<body >





	<table>
	<td>${rs_name.rs_name}</td>
		<tr>
			<td>總分:</td>

			<td>
				<div class="form-field">
					<select id="glsr-ltr" name="order_star" class="star-rating" disabled="disabled">
						<option value="5" ${rs_star==5?"selected":""}>非常滿意</option>
						<option value="4" ${rs_star==4?"selected":""}>滿意</option>
						<option value="3" ${rs_star==3?"selected":""}>普通</option>
						<option value="2" ${rs_star==2?"selected":""}>不滿意</option>
						<option value="1" ${rs_star==1?"selected":""}>非常不滿意</option>
					</select>
				</div>
			</td>
		</tr>

		<tr>
			<th>調查表評論</th>
			<th>星星數</th>

		</tr>
		<c:forEach var="feedback_tableVO" items="${listd}">

			<tr>
				<td>${feedback_tableVO.common}</td>

				<%-- 			<td>${feedback_tableVO.order_star}</td> --%>
				<td><div class="form-field">
						<select id="glsr-ltr" name="order_star" class="star-rating" disabled="disabled">
							<option value="5" ${feedback_tableVO.order_star==5?"selected":""}>非常滿意</option>
							<option value="4" ${feedback_tableVO.order_star==4?"selected":""}>滿意</option>
							<option value="3" ${feedback_tableVO.order_star==3?"selected":""}>普通</option>
							<option value="2" ${feedback_tableVO.order_star==2?"selected":""}>不滿意</option>
							<option value="1" ${feedback_tableVO.order_star==1?"selected":""}>非常不滿意</option>
						</select>
					</div></td>
				<!-- 			<td> -->
				<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Guest_Table/Guest_Table.do" style="margin-bottom: 0px;"> --%>
				<!-- 			     <input type="submit" value="刪除"> -->
				<%-- 			     <input type="hidden" name="gs_email"  value="${guest_tableVO.gs_email}"> --%>
				<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
				<!-- 			</td> -->
		</c:forEach>
		</tr>

	</table>


</body>
<script
	src="<%=request.getContextPath()%>/star/src/star-rating.js?ver=3.4.0"></script>
<script>
	var destroyed = false;
	var starratings = new StarRating('.star-rating', {
		onClick : function(el) {
			console.log('Selected: ' + el[el.selectedIndex].text);
		},
	});
	document.querySelector('.toggle-star-rating').addEventListener('click',
			function() {
				if (!destroyed) {
					starratings.destroy();
					destroyed = true;
				} else {
					starratings.rebuild();
					destroyed = false;
				}
			});
</script>
</html>