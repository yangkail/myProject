<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="java.util.List"%>
<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.compy_table.model.Compy_TableService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	Compy_TableVO compy_TableVO=(Compy_TableVO)request.getAttribute("compy_TableVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺驗證業者</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/back-use/compy_table_use/back_verifyCompy_use.css">
<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>

</head>
<body><div class="header">
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
    	未驗證公司查詢
    </div>
	<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back" method="POST">
        <div class="div_out">
            <div>
				<table>
					<tr>
						<td>業者帳號:</td>
						<td><input type="text" name="cp_account"></td>
					</tr>
					<tr>
						<td>業者聯絡人EMAIL:</td>
						<td><input type="text" name="cp_contact_email"></td>
					</tr>
					<tr>
						<td>業者公司名稱:</td>
						<td><input type="text" name="cp_name"></td>
					</tr>
				</table>
            </div>
        </div>
        <div class="div_submit">
            <input type="submit" value="查詢">
            <input type="hidden" name="method" value="verify_and_select_compy">
            <input type="reset" value="重設">
        </div>
    </form>
    </div>
    
    <!-- 無輸入任何資料時 全部查詢 會顯示"尚未審核完成"及"審核完成但未點選郵件(可重複寄信)" ------------------------------------------------------------------------------------------------------------------>
    <c:if test="${not empty list}">
    	<c:forEach items="${list}" var="compylist">
    		<c:if test="${compylist.authority==11}">
    			<div class="div_content">
        			<div class="div_content1">
            			<ul>
                			<li>公司電話: ${compylist.cp_phone}</li><br>
                			<li>公司名稱: ${compylist.cp_name}</li><br>
                			<li>公司地址: ${compylist.cp_address}</li><br>
                			<li>公司統編: ${compylist.cp_id}</li><br>
                			<li>公司負責人: ${compylist.cp_boss}</li><br>
                			<li>公司聯絡人: ${compylist.cp_contact_man}</li><br>
                			<li>公司聯絡人EMAIL : ${compylist.cp_contact_email}</li><br>
                			<li>公司帳號: ${compylist.cp_account}</li><br>
                			<li>公司密碼: ${compylist.cp_pwd}</li><br>
                			<hr>
                			<li>公司信用卡: ${compylist.cp_credit}</li><br>
                			<li>公司註冊時間: ${compylist.cp_reg_time}</li><br>
                			<li>公司更新時間: ${compylist.cp_update_time}</li><br>
                			<li>權限代號: ${compylist.authority}</li><br>
            			</ul>
        			</div>
        			<div class="div_content2">
            			<ul>
            				<li>公司營業登記證: 
                                <div class="cp_business_diaplay">
                                    <img class="pic_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back?method=many_pics_display&mail=${compylist.cp_contact_email}&which_one=business">
                                </div>
                            </li>        		
            			</ul>
        			</div>

        			<ul class="button_ul">

            			<li>
                			<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back" method="POST">
                    			<input type="submit" class="compy_verifyCompy_btn" value="驗   證">
                    			<input type="hidden" name="method" value="want_to_verify_compy">
                    			<input type="hidden" name="method_key" value="${compylist.cp_contact_email}">
                			</form>
            			</li>
        			</ul>
    			</div>
    			<hr>
			</c:if>
			<c:if test="${compylist.authority==12}">
    			<div class="div_content">
        			<div class="div_content1">
            			<ul>
                			<li>公司電話: ${compylist.cp_phone}</li><br>
                			<li>公司名稱: ${compylist.cp_name}</li><br>
                			<li>公司地址: ${compylist.cp_address}</li><br>
                			<li>公司統編: ${compylist.cp_id}</li><br>
                			<li>公司負責人: ${compylist.cp_boss}</li><br>
                			<li>公司聯絡人: ${compylist.cp_contact_man}</li><br>
                			<li>公司聯絡人EMAIL : ${compylist.cp_contact_email}</li><br>
                			<li>公司帳號: ${compylist.cp_account}</li><br>
                			<li>公司密碼: ${compylist.cp_pwd}</li><br>
                			<hr>
                			<li>公司信用卡: ${compylist.cp_credit}</li><br>
                			<li>公司註冊時間: ${compylist.cp_reg_time}</li><br>
                			<li>公司更新時間: ${compylist.cp_update_time}</li><br>
                			<li>權限代號: ${compylist.authority}</li><br>
            			</ul>
        			</div>
        			<div class="div_content2">
            			<ul>
            				<li>公司營業登記證: 
                                <div class="cp_business_diaplay">
                                    <img class="pic_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back?method=many_pics_display&mail=${compylist.cp_contact_email}&which_one=business">
                                </div>
                            </li>        		
            			</ul>
        			</div>

        			<ul class="button_ul">

            			<li>
                			<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back" method="POST">
                    			<input type="submit" class="compy_verifyCompy_btn" value="再次傳送驗證信">
                    			<input type="hidden" name="method" value="verify_and_sendemail">
                    			<input type="hidden" name="method_key" value="${compylist.cp_contact_email}">
                    			<input type="hidden" name="method_key_name" value="${compylist.cp_name}">
                			</form>
            			</li>
        			</ul>
    			</div>
    			<hr>
			</c:if>
    	</c:forEach>
    </c:if>
    
    
    <!-- 單筆查詢(EMAIL、帳號、公司名稱) ----------------------------------------------------------------------------------------------------------------------------->
    <c:if test="${compy_TableVO.getAuthority()==11}">
    	<div class="div_content">
        	<div class="div_content1">
            	<ul>
                	<li>公司電話: <%=compy_TableVO.getCp_phone() %></li><br>
                	<li>公司名稱: <%=compy_TableVO.getCp_name()%></li><br>
                	<li>公司地址: <%=compy_TableVO.getCp_address()%></li><br>
                	<li>公司統編: <%=compy_TableVO.getCp_id() %></li><br>
                	<li>公司負責人: <%=compy_TableVO.getCp_boss() %></li><br>
                	<li>公司聯絡人: <%=compy_TableVO.getCp_contact_man() %></li><br>
                	<li>公司聯絡人EMAIL : <%=compy_TableVO.getCp_contact_email() %></li><br>
                	<li>公司帳號: <%=compy_TableVO.getCp_account() %></li><br>
                	<li>公司密碼: <%=compy_TableVO.getCp_pwd() %></li><br>
                	<hr>
                	<li>公司信用卡: <%=compy_TableVO.getCp_credit() %></li><br>
                	<li>公司註冊時間: <%=compy_TableVO.getCp_reg_time() %></li><br>
                	<li>公司更新時間: <%=compy_TableVO.getCp_update_time() %></li><br>
                	<li>權限代號: <%=compy_TableVO.getAuthority() %></li><br>
            	</ul>
        	</div>
        	<div class="div_content2">
            	<ul>
            		<li>公司營業登記證: 
                         <div class="cp_business_diaplay">
                               <img class="pic_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back?method=many_pics_display&&mail=<%=compy_TableVO.getCp_contact_email() %>&&which_one=business">
                         </div>
                    </li>               	
            	</ul>
        	</div>
        	
        	<ul class="button_ul">
            	<li>
                	<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back" method="POST">
                    	<input type="submit" class="compy_verifyCompy_btn" value="驗   證">
                    	<input type="hidden" name="method" value="want_to_verify_compy">
                    	<input type="hidden" name="method_key" value="<%=compy_TableVO.getCp_contact_email() %>">
                	</form>
            	</li>
        	</ul>
    	</div>
   </c:if>
       <c:if test="${compy_TableVO.getAuthority()==12}">
    	<div class="div_content">
        	<div class="div_content1">
            	<ul>
                	<li>公司電話: <%=compy_TableVO.getCp_phone() %></li><br>
                	<li>公司名稱: <%=compy_TableVO.getCp_name()%></li><br>
                	<li>公司地址: <%=compy_TableVO.getCp_address()%></li><br>
                	<li>公司統編: <%=compy_TableVO.getCp_id() %></li><br>
                	<li>公司負責人: <%=compy_TableVO.getCp_boss() %></li><br>
                	<li>公司聯絡人: <%=compy_TableVO.getCp_contact_man() %></li><br>
                	<li>公司聯絡人EMAIL : <%=compy_TableVO.getCp_contact_email() %></li><br>
                	<li>公司帳號: <%=compy_TableVO.getCp_account() %></li><br>
                	<li>公司密碼: <%=compy_TableVO.getCp_pwd() %></li><br>
                	<hr>
                	<li>公司信用卡: <%=compy_TableVO.getCp_credit() %></li><br>
                	<li>公司註冊時間: <%=compy_TableVO.getCp_reg_time() %></li><br>
                	<li>公司更新時間: <%=compy_TableVO.getCp_update_time() %></li><br>
                	<li>權限代號: <%=compy_TableVO.getAuthority() %></li><br>
            	</ul>
        	</div>
        	<div class="div_content2">
            	<ul>
            		<li>公司營業登記證: 
                         <div class="cp_business_diaplay">
                               <img class="pic_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back?method=many_pics_display&&mail=<%=compy_TableVO.getCp_contact_email() %>&&which_one=business">
                         </div>
                    </li>               	
            	</ul>
        	</div>
        	
        	<ul class="button_ul">
            	<li>
                	<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back" method="POST">
                    	<input type="submit" class="compy_verifyCompy_btn" value=再次傳送驗證信>
                    	<input type="hidden" name="method" value="verify_and_sendemail">
                    	<input type="hidden" name="method_key" value="<%=compy_TableVO.getCp_contact_email() %>">
                    	<input type="hidden" name="method_key_name" value="<%=compy_TableVO.getCp_name()%>">
                	</form>
            	</li>
        	</ul>
    	</div>
   </c:if>
<%--    <a href="<%=request.getContextPath() %>/front-end/compy_table/fakeIndex.jsp">回假首頁</a> --%>
</body>
</html>