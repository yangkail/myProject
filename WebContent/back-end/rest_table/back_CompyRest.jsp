<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>已上架餐廳</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/back-use/rest_table_use/back_CompyRest_use.css">
<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>

</head>
<body>
<div class="header">
<jsp:include page="/css/headfoot/header.jsp"></jsp:include>
</div>
<div class="topBackplane" style="text-align:center;">
		<div class="displayTopPlane"></div>
			<h3><a
				href="<%=request.getContextPath()%>/back-end/rest_table/back_CompyRest.jsp">
				<img src="<%=request.getContextPath()%>/images/logo.jpg" style="width:25%; margin:auto;">
			</a></h3></div>


<div style="margin-top:20px;margin-bottom:0px; text-align:center;">
	<div class="title_word">
    	已驗證餐廳查詢
    </div><br>
	<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back" method="POST">
        <div class="div_out">
            <div>
				<table>
					<tr>
						<td>請輸入餐廳編號 &ensp;: &ensp;</td>
						<td><input type="text" name="rs_id"></td>
					</tr>
					<tr>
						<td>業者聯絡人EMAIL: &ensp;</td>
						<td><input type="text" name="cp_contact_email"></td>
					</tr>
					<tr>
						<td>請輸入餐廳名稱 &ensp;: &ensp;</td>
						<td><input type="text" name="rs_name"></td>
					</tr>
				</table>
            </div>
        </div>
        <div class="div_submit">
            <input type="submit" value="查詢">
            <input type="hidden" name="method" value="select_rs">
            <input type="reset" value="重設">
        </div>
    </form>
    </div>
    <br>

<!--     <div class="search_div"> -->
<!--        	查詢結果 -->
<!--     	<hr> -->
<!--     </div> -->
    <% int count=0; %>
    <!-- 無輸入任何資料時 全部查詢 會顯示目前待審核資料(RS權限13者) ------------------------------------------------------------------------------------------------------------------>
    <c:if test="${not empty list}">
    	<c:forEach items="${list}" var="rest">
    		<c:if test="${rest.authority==4}">
    			<div class="div_content">
    				<div class="div_content1">
    					<div class="info_display1">
            				<ul>
                				<li>餐廳編號: ${rest.rs_id}</li><br>
                				<li>餐廳名稱: ${rest.rs_name}</li><br>
                				<li>餐廳地址: ${rest.rs_address}</li><br>
                				<li>餐廳電話: ${rest.rs_phone}</li><br>
                				<li>餐廳類型: ${rest.rs_type}</li><br>
                				<li>營業時間:
									<c:if test="${fn:contains(rest.rs_open_time, '4')}">
										8:00~13:00,13:00~18:00,18:00~23:00
									</c:if>
									<c:if test="${fn:contains(rest.rs_open_time, '1')}">
										8:00~13:00
									</c:if>
									<c:if test="${fn:contains(rest.rs_open_time, '2')}">
										13:00~18:00
									</c:if>
									<c:if test="${fn:contains(rest.rs_open_time, '3')}">
										18:00~23:00
									</c:if>
                				</li>
                			</ul>
                			</div>
                		<div class="info_display2">
                			<ul>
                				<li>聯絡人EMAIL: ${rest.cp_contact_email}</li><br>
                				<li>註冊時間: ${rest.rs_reg_date}</li><br>
                				<li>審核狀態: <span style="color:green;">已審核</span></li>
            				</ul>
            			</div>
            			<form action="<%=request.getContextPath() %>/back-end/rest_table/back_CompyOneRest.jsp" method="POST">
            				<button class="audit_btn">細項</button>
            				<input type="hidden" name="rs_id" value="${rest.rs_id }">
            			</form>
        			</div>
        		</div>
        		<%count++; %>
    		</c:if>
    		<c:if test="${rest.authority==10}">
    			<div class="div_content_delete">
    				<div class="div_content1">
    					<div class="info_display1">
            				<ul>
                				<li>餐廳編號: ${rest.rs_id}</li><br>
                				<li>餐廳名稱: ${rest.rs_name}</li><br>
                				<li>餐廳地址: ${rest.rs_address}</li><br>
                				<li>餐廳電話: ${rest.rs_phone}</li><br>
                				<li>餐廳類型: ${rest.rs_type}</li><br>
                				<li>營業時間:
									<c:if test="${fn:contains(rest.rs_open_time, '4')}">
										8:00~13:00,13:00~18:00,18:00~23:00
									</c:if>
									<c:if test="${fn:contains(rest.rs_open_time, '1')}">
										8:00~13:00
									</c:if>
									<c:if test="${fn:contains(rest.rs_open_time, '2')}">
										13:00~18:00
									</c:if>
									<c:if test="${fn:contains(rest.rs_open_time, '3')}">
										18:00~23:00
									</c:if>
                				</li>
                			</ul>
                			</div>
                		<div class="info_display2">
                			<ul>
                				<li>聯絡人EMAIL: ${rest.cp_contact_email}</li><br>
                				<li>註冊時間: ${rest.rs_reg_date}</li><br>
                				<li>審核狀態: <span style="color:red;">已下架</span></li>
            				</ul>
            			</div>
            			<form action="<%=request.getContextPath() %>/back-end/rest_table/back_CompyOneRest.jsp" method="POST">
            				<button class="audit_btn">細項</button>
            				<input type="hidden" name="rs_id" value="${rest.rs_id }">
            			</form>
        			</div>
        		</div>
        		<%count++; %>
    		</c:if>
    	</c:forEach>
    	<%if(count==0){ %>
    		<div class="no_search_div">
    			<img src="<%=request.getContextPath() %>/images/noSearch.jpg">
    		</div>
    	<%}%>
    </c:if>
    <c:if test="${list.size()==0 }">
  		<%if(count==0){ %>
    		<div class="no_search_div">
    			<img src="<%=request.getContextPath() %>/images/noSearch.jpg">
    		</div>
    	<%} %>
    </c:if>
<%--    <a href="<%=request.getContextPath() %>/front-end/compy_table/fakeIndex.jsp">回假首頁</a> --%>
</body>
</html>