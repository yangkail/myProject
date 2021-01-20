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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_table_use/front_CompyMembershipPlatform_updatePic.css">
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/front_use/compy_table_use/front_CompyMembershipPlatform_info_update.js"></script>
    <style>
    	div.btn-info{
    		margin-top: .25rem;
    		margin-bottom: .25rem;
        	color: #fff;
    		background-color: #17a2b8;
    		border-color: #17a2b8;
    		display: inline-block;
    		font-weight: 400;
    		text-align: center;
    		vertical-align: middle;
    		user-select: none;
    		border: 1px solid transparent;
    		padding: .375rem .75rem;
    		font-size: 1rem;
    		line-height: 1.5;
    		border-radius: .25rem;
    	}
    </style>

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
                <h1 style="font-size:40px;">業者照片更改</h1>
                <hr>
            </div>
                <div class="info_logo_info_out">
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
                        </table>
                    </div>
                </div>
                
                <div class="info_update_pic_bigpic">
                     <div class="update_bigpic_info">公司大頭貼</div>
                        <form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front" method="post" id="bigpic_form" enctype="multipart/form-data">
                        	<label>
                        		<div type="button" class="btn-info">上傳公司大頭貼</div>
                        		<input type="file" style="display:none;" name="cp_bigpic" id="cp_bigpic" accept="image/*">
                        	</label>
                        </form>
                        <div class="logo_display">
                            <img class="logo_display_img" id="cp_bigpic_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front?method=many_pics_display&&mail=${authority_compy.cp_contact_email}&&which_one=bigpic" alt="大頭照">
                     	</div>
                </div>
                <div class="info_update_pic_logo">
                     <div class="update_logo_info">公司LOGO</div>
                        <form action="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front" method="post" id="logo_form" enctype="multipart/form-data">
                        	<label>
                        		<div type="button" class="btn-info">上傳公司LOGO</div>
                        		<input type="file" style="display:none;" name="cp_logo" id="cp_logo" accept="image/*">
                        	</label>
                        </form>
                     <div class="logo_display">
                            <img class="logo_display_img" id="cp_logo_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front?method=many_pics_display&&mail=${authority_compy.cp_contact_email}&&which_one=logo" alt="公司LOGO">
                     </div>
               </div>

            </div>
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
	<script >
    $("#cp_bigpic").on("change", function() {
    	var reader = new FileReader();
    	reader.readAsDataURL(this.files[0]);
    	reader.addEventListener("load", function(e) {
    		$("#cp_bigpic_img").attr("src", "");
    		$("#bigpic_img").attr("src","");
    		let obj = new FormData($("#bigpic_form")[0]);
    		obj.append("method","update_bigpic");
    		$.ajax({
    			url : "/TEA102G1/compy_table/Compy_TableServlet_front",
    			type : "POST",
    			data : obj,
    			contentType : false,
    			processData : false,
    			cache : false,

    			success : function(result) {
    				$("#cp_bigpic_img").attr("src", e.target.result);
    				$("#bigpic_img").attr("src", e.target.result);
    			},
    			error : function(err) {
    				alert("大頭照更新錯誤");
    			}
    		})
    	})
    })

    $("#cp_logo").on("change", function() {
    	var reader = new FileReader();
    	reader.readAsDataURL(this.files[0]);
    	reader.addEventListener("load", function(e) {
    		$("#cp_logo_img").attr("src", "");
    		let obj = new FormData($("#logo_form")[0]);
    		obj.append("method","update_logo");
    		$.ajax({
    			url : "/TEA102G1/compy_table/Compy_TableServlet_front",
    			type : "POST",
    			data : obj,
    			contentType : false,
    			processData : false,
    			cache : false,

    			success : function(result) {
    				$("#cp_logo_img").attr("src", e.target.result);
    			},
    			error : function(err) {
    				console.log(err);
    				alert("大頭照更新錯誤");
    			}
    		})
    	})
    })
    </script>
</body>
</html>