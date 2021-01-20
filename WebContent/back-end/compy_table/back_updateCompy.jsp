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
<title>資料修改</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/back-use/compy_table_use/back_updateCompy_use.css">
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
    	更改公司資訊
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
    	<form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_back" method="POST" enctype="multipart/form-data">
        	<div class="div_content1">
            	<ul>
                    <li>公司電話: 
                    	<input type="text" name="cp_phone" value="<%=compy_TableVO.getCp_phone() %>"/>
                    	<span style="color:red">${errorMsgs.cp_phone}</span>
                    </li>
                    <li>公司名稱: 
                    	<input type="text" name="cp_name" value="<%=compy_TableVO.getCp_name() %>"/>
                    	<span style="color:red">${errorMsgs.cp_name}</span>
                    </li>
                    <li>公司地址: 
                    	<input type="text" name="cp_address" value="<%=compy_TableVO.getCp_address()%>"/>
                    	<span style="color:red">${errorMsgs.cp_address}</span>
                    </li>
                    <li>公司統編: 
                    	<input type="text" name="cp_id" value="<%=compy_TableVO.getCp_id() %>"/>
                    	<span style="color:red">${errorMsgs.cp_id}</span>
                    </li>
                    <li>公司負責人: 
                    	<input type="text" name="cp_boss" value="<%=compy_TableVO.getCp_boss() %>"/>
                    	<span style="color:red">${errorMsgs.cp_boss}</span>
                    </li>
                    <li>公司聯絡人: 
                    	<input type="text" name="cp_contact_man" value="<%=compy_TableVO.getCp_contact_man() %>"/>
                    	<span style="color:red">${errorMsgs.cp_contact_man}</span>
                    </li>
                    <li>公司聯絡人EMAIL : <%=compy_TableVO.getCp_contact_email() %></li>
                    	<input type="hidden" name="cp_contact_mail" value="<%=compy_TableVO.getCp_contact_email() %>"/>
                	<li>公司帳號: <%=compy_TableVO.getCp_account() %></li>
                		<input type="hidden" name="cp_account" value="<%=compy_TableVO.getCp_account() %>"/>             	
                    <li>公司密碼: 
                    	<input type="text" name="cp_pwd" value="<%=compy_TableVO.getCp_pwd() %>"/>
                    	<span style="color:red">${errorMsgs.cp_pwd}</span>
                    </li>
                	<hr>
                    <li>公司信用卡: 
                    	<input type="text" name="cp_credit" value="<%=compy_TableVO.getCp_credit() %>"/>
                    	<span style="color:red">${errorMsgs.cp_credit}</span>
                    </li>
                    
                	<li>公司註冊時間: <%=compy_TableVO.getCp_reg_time() %></li>
                		<input type="hidden" name="cp_reg_time" value="<%=compy_TableVO.getCp_reg_time() %>"/>
                	<li>公司更新時間: <%=compy_TableVO.getCp_update_time() %></li>
                		<input type="hidden" name="cp_update_time" value="<%=compy_TableVO.getCp_update_time() %>"/>
                	<li>權限代號: <%=compy_TableVO.getAuthority() %></li>
                		<input type="hidden" name="authority" value="<%=compy_TableVO.getAuthority() %>"/>
            	</ul>
        	</div>
        	<div class="div_content2">
            	<ul>
            				<%
            				 	String a=null;
            				 	if((compy_TableVO.getCp_business())!=null){
            				 		a=new String(Base64.encodeBase64(compy_TableVO.getCp_business()));
            				 	}
            				 %>
            		<li>公司營業登記證: <input type="file" name="cp_business" id="cp_business" accept="image/*">
                         <div class="cp_business_diaplay">
                              <img class="pic_img" src="data:image/gif;base64,<%=a %>">                         
                         </div>       
                    </li>               	
            	</ul>
        	</div>
        	<div class="div_content3">
            	<ul>
            	            <%
            				 	String b=null;
            				 	if((compy_TableVO.getCp_logo())!=null){
            				 		b=new String(Base64.encodeBase64(compy_TableVO.getCp_logo()));
            				 	}
            				 	String c=null;
            				 	if((compy_TableVO.getCp_bigpic())!=null){
            				 		c=new String(Base64.encodeBase64(compy_TableVO.getCp_bigpic()));
            				 	}
            				 %>
            		 <li>公司LOGO: <input type="file" name="cp_logo" id="cp_logo" accept="image/*">
                        <div class="cp_logo_display">
                            <img class="pic_img_cycle" src="data:image/gif;base64,<%=b %>">
                        </div>
                     </li>
                     <li>公司大頭照: <input type="file" name="cp_bigpic" id="cp_bigpic" accept="image/*">
                        <div class="cp_bigpic_display">
                            <img class="pic_img_cycle" src="data:image/gif;base64,<%=c %>">
                        </div>
                     </li>
            	</ul>
            </div>
            <ul class="button_ul">
                <li>
                        <input type="submit" class="compy_delete_btn" value="確認更改" style="color: white;">
                        <input type="hidden" name="method" value="want_to_update_compy_confirm">
                    	<input type="hidden" name="method_key" value="<%=compy_TableVO.getCp_contact_email() %>">
                    
                </li>
        </form>
        		<li>
        			<form action="<%=request.getContextPath() %>/back-end/compy_table/back_selectCompy.jsp">
        				<input type="submit" class="compy_dont_delete_btn" value="取消">
        			</form>
        		</li>
         	</ul>
    </div>
    <script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/back_use/compy_table_use/back_updateCompy.js"></script>
</body>
</html>