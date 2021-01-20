<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
Rest_TableService rest_TableService=new Rest_TableService();
Rest_TableVO rest_TableVO=new Rest_TableVO();
if(request.getParameter("cp_contact_email")!=null){
	String rs_id=rest_TableService.getRs_id(request.getParameter("cp_contact_email"));
	rest_TableVO=rest_TableService.getOneAll(rs_id);
}else{
	rest_TableVO=rest_TableService.getOneAll(request.getParameter("rs_id"));
}
pageContext.setAttribute("rest", rest_TableVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>餐廳細項</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/back-use/rest_table_use/back_deleteCompyOneRest_use.css">
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
<div style="margin-top:20px;margin-bottom:150px;text-align:center;">

	<div class="title_word">
    	刪除餐廳
    </div>
	<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back" method="POST">
        <div class="div_out">
            <div>
				<table>
					<tr>
						<td>餐廳編號:</td>
						<td><input type="text" name="rs_id"></td>
					</tr>
					<tr>
						<td>業者聯絡人EMAIL:</td>
						<td><input type="text" name="cp_contact_email"></td>
					</tr>
					<tr>
						<td>餐廳名稱:</td>
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

    <div class="search_div">
       	刪除餐廳
    	<hr>
    </div>
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
                		<li>審核狀態: <span style="color:red;">未審核</span></li>
            		</ul>
            	</div>
            	<div class="display_bigpic">
            		<img src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back?method=display_one_img&key=${rest.rs_id}&which=rs_bigpic">
            	</div>
            	<div class="just_a_word">
            		環視圖
            	</div>
            	<div class="display_view">
            		<div class="display_view_img">
            			<img src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back?method=display_one_img&key=${rest.rs_id}&which=rs_view1">
            		</div>
            		<div class="display_view_img">
            			<img src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back?method=display_one_img&key=${rest.rs_id}&which=rs_view2">
            		</div>
            		<div class="display_view_img">
            			<img src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back?method=display_one_img&key=${rest.rs_id}&which=rs_view3">
            		</div>
            		<div class="display_view_img">
            			<img src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back?method=display_one_img&key=${rest.rs_id}&which=rs_view4">
            		</div>
            		<div class="display_view_img">
            			<img src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back?method=display_one_img&key=${rest.rs_id}&which=rs_view5">
            		</div>
            	</div>
            	<div>
            	</div>
            	<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_back" method="POST">
            		<div class="btns">
            			<button class="audit_btn">確認下架</button>
            			<input type="hidden" name="rs_id" value="${rest.rs_id }">
            			<input type="hidden" name="method" value="delete_rs">
            			<button class="cancel_btn" type="button"><a href="<%=request.getContextPath() %>/back-end/rest_table/back_verifyCompyRest.jsp">取消</a></button>
            		</div>
            	</form>
        	</div>
        </div>
    
   <a href="<%=request.getContextPath() %>/front-end/compy_table/fakeIndex.jsp">回假首頁</a>
</body>
</html>