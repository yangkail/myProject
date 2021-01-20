<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.compy_table.model.Compy_TableService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Compy_TableVO compy_TableVO=new Compy_TableVO();
Compy_TableService compy_TableService=new Compy_TableService();
Map<String,String> map=(Map)session.getAttribute("authority_compy");
compy_TableVO=compy_TableService.findByPrimaryKey(map.get("cp_contact_email"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>業者會員管理平台</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/footer.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/members_side.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_table_use/front_CompyMembershipPlatform.css">
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
	
</head>
<body>
	<!-- HEADER -->
	<div class="topBackplane">
		<jsp:include page="/front-end/public_jsp/header.jsp"></jsp:include>
	</div>
	<!-- HEADER -->
	
	<!-- SIDE DIV -->
	<div class="all_div">
    	<jsp:include page="/front-end/public_jsp/member_side.jsp"></jsp:include>
    </div>
    <!-- SIDE DIV -->
        
        
        <div class="info_div">
            <div>
                <h1 style="font-size:40px;">業者會員資訊</h1>
                <hr>
                </div>

                    <div class="info_div_in_info">
                        <table class="info_table">
                            <tr>
                                <td>公司名稱:</td>
                                <td><%=compy_TableVO.getCp_name() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>公司統編: </td>
                                <td><%=compy_TableVO.getCp_id() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>公司地址: </td>
                                <td><%=compy_TableVO.getCp_address() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>公司負責人: </td>
                                <td><%=compy_TableVO.getCp_boss() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>公司聯絡人: </td>
                                <td><%=compy_TableVO.getCp_contact_man() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>公司聯絡電話: </td>
                                <td><%=compy_TableVO.getCp_phone() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>公司聯絡人EMAIL: </td>
                                <td><%=compy_TableVO.getCp_contact_email() %></td>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                            <tr>
                                <td>驗證狀態: </td>
                                <c:choose>
                                	<c:when test="${authority_compy.authority==2}">
                                		<td style="color:green;">已驗證</td>
                                	</c:when>
                                	<c:when test="${authority_compy.authority==11}">
                                		<td style="color:red;">未驗證</td>
                                	</c:when>
                                	<c:when test="${authority_compy.authority==12}">
                                		<td style="color:red;">未驗證</td>
                                	</c:when>
                                </c:choose>
                            </tr>
                            <tr class="tr_not_do_some"><td></td><td></td></tr>
                        </table>
                    </div>
				
				<div class="info_logo_info_out">
                    <div class="info_div_in_logo">
                        <div class="logo_info">公司LOGO
                        </div>
                        <div class="logo_display">
                            <img class="logo_display_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front?method=many_pics_display&&mail=${authority_compy.cp_contact_email}&&which_one=logo" alt="公司LOGO">
                    	</div>
                    </div>
                </div>
                


                <div class="info_business">
                    <div class="info_business_word">
                        	公司營業登記證
                    </div>
                    <div class="info_business_display">
                        <img class="info_business_display_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front?method=many_pics_display&mail=${authority_compy.cp_contact_email}&which_one=business" alt="公司營業登記證">
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
</body>
</html>