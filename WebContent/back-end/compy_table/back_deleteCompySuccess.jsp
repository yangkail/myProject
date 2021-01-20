<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	Compy_TableVO compy_TableVO=(Compy_TableVO)request.getAttribute("compy_TableVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料刪除完成</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/back-use/compy_table_use/back_deleteCompy_use.css">
<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>

</head>
<body>
<div style="margin-top:150px;margin-bottom:150px;">
	<div class="title_word">
    	刪除公司成功
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
            <input type="hidden" name="method" value="select_compy">
            <input type="reset" value="重設">
        </div>
    </form>
    </div>
    	<div class="div_content">
    		<h1>資料已刪除</h1>
        	<div class="div_content1">
            	<ul>
                	<li>公司電話: <%=compy_TableVO.getCp_phone() %></li>
                	<li>公司名稱: <%=compy_TableVO.getCp_name()%></li>
                	<li>公司地址: <%=compy_TableVO.getCp_address()%></li>
                	<li>公司統編: <%=compy_TableVO.getCp_id() %></li>
                	<li>公司負責人: <%=compy_TableVO.getCp_boss() %></li>
                	<li>公司聯絡人: <%=compy_TableVO.getCp_contact_man() %></li>
                	<li>公司聯絡人EMAIL : <%=compy_TableVO.getCp_contact_email() %></li>
                	<li>公司帳號: <%=compy_TableVO.getCp_account() %></li>
                	<li>公司密碼: <%=compy_TableVO.getCp_pwd() %></li>
                	<hr>
                	<li>公司信用卡: <%=compy_TableVO.getCp_credit() %></li>
                	<li>公司註冊時間: <%=compy_TableVO.getCp_reg_time() %></li>
                	<li>公司更新時間: <%=compy_TableVO.getCp_update_time() %></li>
                	<li>權限代號: <%=compy_TableVO.getAuthority() %></li>
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
        	<div class="div_content3">
            	<ul>
            		<li>公司LOGO: 
                        <div class="cp_logo_display">
                             <img class="pic_img_cycle" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back?method=many_pics_display&&mail=<%=compy_TableVO.getCp_contact_email() %>&&which_one=logo">                                   
                        </div>
                    </li>
                    <li>公司大頭照: 
                        <div class="cp_bigpic_display">
                             <img class="pic_img_cycle" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back?method=many_pics_display&&mail=<%=compy_TableVO.getCp_contact_email() %>&&which_one=bigpic">                                  
                        </div>
                    </li>
            	</ul>
            </div>
            <ul class="button_ul">
            	<li style="color:red">
            		公司刪除時間: <%=compy_TableVO.getCp_update_time() %>
            	</li>
                <li>
                    <form action="<%=request.getContextPath() %>/back-end/compy_table/back_selectCompy.jsp">
                        <input type="submit" class="compy_dont_delete_btn" value="返回搜尋頁面">
                    </form>
                </li>
            </ul>
    </div>
</body>
</html>