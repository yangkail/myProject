<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Rest_TableVO rest_TableVO=(Rest_TableVO)request.getAttribute("rest_TableVO");
if(session.getAttribute("rs_id")!=null){
	Rest_TableService rest_TableService=new Rest_TableService();
	rest_TableVO=rest_TableService.getOneAll((String)session.getAttribute("rs_id"));
}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>業者會員管理平台-會員資訊</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/footer.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/members_side.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/rest_table_use/front_CompyAddRest_info.css">
	<script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
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
        
        <c:if test="${rs_id==null}">
			<div class="info_div">
            <div>
                <h1 style="font-size:40px;">業者登記餐廳</h1>
                <hr>
            </div>
			
			<div class="rs_info">
        		<form action="<%=request.getContextPath()%>/rest_table/Rest_TableServlet_front" method="POST" enctype="multipart/form-data">
            		<label> 餐廳名稱 : 
            			<input type="text" id="rs_name" name="rs_name" value="<%=(rest_TableVO==null?"":rest_TableVO.getRs_name()) %>">
            			<span style="color:red;">${errorMsgs.rs_name}</span></label><br><br>
            			 
            		<label> 餐廳地址 : 
            			<input id="address" name="rs_address" class="twaddress" value="<%=(rest_TableVO==null?"":rest_TableVO.getRs_address()) %>"/>
            			<span style="color:red;">${errorMsgs.rs_address}</span></label><br><br>
            			 
            		<label> 餐廳電話 : 
            			<input type="text" id="rs_phone" name="rs_phone" value="<%=(rest_TableVO==null?"":rest_TableVO.getRs_phone()) %>">
            			<span style="color:red;">${errorMsgs.rs_phone}</span></label><br><br>
            			 
            		餐廳類別 : 
            		<label>台式<input type="checkbox" id="rs_type" name="rs_type" value="台式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("台式")?"checked":""):"") %>> </label> 
            		<label>日式<input type="checkbox" name="rs_type" value="日式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("日式")?"checked":""):"") %>> </label> 
            		<label>韓式<input type="checkbox" name="rs_type" value="韓式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("韓式")?"checked":""):"") %>> </label> 
            		<label>西式<input type="checkbox" name="rs_type" value="西式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("西式")?"checked":""):"") %>> </label> 
            		<label>美式<input type="checkbox" name="rs_type" value="美式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("美式")?"checked":""):"") %>> </label> 
            		<label>泰式<input type="checkbox" name="rs_type" value="泰式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("泰式")?"checked":""):"") %>> </label> 
            		<label>印度式<input type="checkbox" name="rs_type" value="印式" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&rest_TableVO.getRs_type().contains("印式")?"checked":""):"") %>></label> 
            		<span style="color:red;">${errorMsgs.rs_type}</span></label><br><br>
            		
            		每日營業時間 : 
            		<label>8:00~13:00<input type="checkbox" id="rs_open_time1" name="rs_open_time" value="1" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&(rest_TableVO.getRs_open_time().contains("1")||rest_TableVO.getRs_open_time().contains("4"))?"checked":""):"") %>></label>
            		<label>13:00~18:00<input type="checkbox" id="rs_open_time2" name="rs_open_time" value="2" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&(rest_TableVO.getRs_open_time().contains("2")||rest_TableVO.getRs_open_time().contains("4"))?"checked":""):"") %>></label>
            		<label>18:00~23:00<input type="checkbox" id="rs_open_time3" name="rs_open_time" value="3" <%=(rest_TableVO!=null?(rest_TableVO.getRs_type()!=null&&(rest_TableVO.getRs_open_time().contains("3")||rest_TableVO.getRs_open_time().contains("4"))?"checked":""):"") %>></label>
            		<span style="color:red;">${errorMsgs.rs_open_time}</span><br><br><hr>
            		
            		<label> 餐廳介紹 : <textarea id="rs_intro" name="rs_intro" cols="50" placeholder="請輸入20~100中文字"><%=(rest_TableVO==null?"":rest_TableVO.getRs_intro()) %></textarea></label><br><br>
            		<span style="color:red;">${errorMsgs.rs_intro}</span><hr>
            		
            		<label style="vertical-align: top;"> 餐廳用戶大頭照 : 
            		<input type="file" name="rs_big_pic" id="rs_big_pic" accept="image/*">
            		<span style="color:red;">${errorMsgs.rs_big_pic}</span></label>
            		<div class="rs_big_pic_display">
            		</div>
            		<br><br><hr>
            		<div class="submit_btn">
            			<span>${errorMsgs.error}</span>
            			<input type="submit" class="submit_input" value="確認儲存"> <input type="reset" class="reset_input" value="重填">
            			<input type="hidden" name="method" value="insert_new_rest">
            		</div>
        		</form>
        		<button id="magic_little_button">神奇小按鈕</button>
    		</div>
    	</c:if>
    	
    	<c:if test="${rs_id!=null}">
    		<div class="info_div">
            	<div>
                	<h1 style="font-size:40px;">業者已登記餐廳</h1>
                	<hr>
            	</div>
			
				<div class="rs_info">
        			<div class="rs_info_display">
        				<table class="info_table">
							<tr>
								<td class="title">餐廳編號: </td>
								<td class="info"><%=rest_TableVO.getRs_id() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳名稱: </td>
								<td class="info"><%=rest_TableVO.getRs_name() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳地址: </td>
								<td class="info"><%=rest_TableVO.getRs_address() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳電話: </td>
								<td class="info"><%=rest_TableVO.getRs_phone() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳電話: </td>
								<td class="info"><%=rest_TableVO.getRs_phone() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳類型: </td>
								<td class="info"><%=rest_TableVO.getRs_type() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳營業時間: </td>
								<td class="info">
									<c:if test='<%=rest_TableVO.getRs_open_time().equals("4") %>'>
										<span>8:00~13:00,13:00~18:00,18:00~23:00</span>
									</c:if>
									<c:if test='<%=rest_TableVO.getRs_open_time().contains("1") %>'>
										<span>8:00~13:00</span> 
									</c:if>
									<c:if test='<%=rest_TableVO.getRs_open_time().contains("2") %>'>
										<span>13:00~18:00</span>
									</c:if>
									<c:if test='<%=rest_TableVO.getRs_open_time().contains("3") %>'>
										<span>18:00~23:00</span>
									</c:if>
								</td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳介紹: </td>
								<td class="info"><%=rest_TableVO.getRs_intro() %></td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
							<tr>
								<td class="title">餐廳審核狀態: </td>
								<td class="info">
									<c:choose>
										<c:when test='<%=(rest_TableVO.getRs_check_yn()==1) %>'>
											<span style="color:green">已審核通過</span>
										</c:when>
										<c:when test='<%=(rest_TableVO.getRs_check_yn()==2) %>'>
											<span style="color:red">尚未審核</span>
										</c:when>
									</c:choose>
								</td>
							</tr>
							<tr class="tr_not_do_some"><td></td><td></td></tr>
                            
        				</table>
        			</div>
        			<div class="big_pic">
        				<img class="big_pic_img" alt="餐廳大頭照" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_bigpic">
        			</div>
        			<div class="view_pic">
						<div class="view_pic_display">
							<img class="view_pic_display_img" alt="餐廳環視圖1" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view1">
						</div>
						<div class="view_pic_display">
							<img class="view_pic_display_img" alt="餐廳環視圖2" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view2">
						</div>
						<div class="view_pic_display">
							<img class="view_pic_display_img" alt="餐廳環視圖3" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view3">
						</div>
						<div class="view_pic_display">
							<img class="view_pic_display_img" alt="餐廳環視圖4" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view4">
						</div>
						<div class="view_pic_display">
							<img class="view_pic_display_img" alt="餐廳環視圖5" src="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front?method=get_the_pic&id=${rs_id}&which=rs_view5">
						</div>
        			</div>
        			<c:if test='<%=(rest_TableVO.getAuthority()==11) %>'>
        				<div class="btn_display">
        					<a class="update_pic_to" href="<%=request.getContextPath() %>/front-end/rest_table/front_CompyAddPic.jsp?display=6">
        						<div type="button" class="btn-info">
        						上傳餐廳圖片
        						</div>
        					</a>
        				</div>
        			</c:if>
        			<c:if test='<%=(rest_TableVO.getAuthority()==12) %>'>
        				<div class="btn_display">
        					<form action="<%=request.getContextPath() %>/rest_table/Rest_TableServlet_front">
        						<input class="submit_btn_to" id="final_btn" type="submit" value="審核申請">
        						<input type="hidden" name="method" value="review_application">
        					</form>
        				</div>
        			</c:if>
    			</div>	
        	</div>
        </c:if>
    </div>
    
    
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
	<script src="<%=request.getContextPath() %>/js/front_use/rest_table_use/address.js"></script>
	<script src="<%=request.getContextPath() %>/js/front_use/rest_table_use/front_CompyAddRest_info.js"></script>
	<script>
	 $("#magic_little_button").click(function(){
		 $("#rs_name").val("愛貓輕食餐廳");
         $("#address").val("新北市新店區德正街27巷29弄53號4樓");
         $("#rs_phone").val("89112925");
         $("#rs_type").attr("checked","true");
         $("#rs_open_time1").attr("checked","true");
         $("#rs_open_time2").attr("checked","true");
         $("#rs_open_time3").attr("checked","true");
         $("#rs_intro").text("這是一間愛貓的餐廳,可以帶您家的毛小孩來(僅限貓),狗派我們就不歡迎您,請您去吃別家")
     })
	</script>
</body>
</html>