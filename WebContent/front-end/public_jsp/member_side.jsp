<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@page import="com.compy_table.model.Compy_TableService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String display=request.getParameter("display");
pageContext.setAttribute("display", display);
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
	<style>
		div.div_is_btn_in_side{
    		width: 80%;
    		margin: 0 auto;
    		margin-top: 5px;
		}
		.open_color{
			background-color: #ffb700;
			border-bottom-left-radius: 10px;
    		border-bottom-right-radius: 10px;
    		border-top-left-radius: 10px;
    		border-top-right-radius: 10px;
		}
		div.currently_div_is_btn_in_side{
		    margin-top: 5px;
			width: 80%;
    		margin: 0 auto;
			background-color: #ffb700;
			border-bottom-left-radius: 10px;
    		border-bottom-right-radius: 10px;
    		border-top-left-radius: 10px;
    		border-top-right-radius: 10px;
    		color:white;
    		margin-top: 5px;
    		display: inline-block;
		}
		div.currently_div_is_btn{
			vertical-align: sub;
		    border-top: 15px solid #e9ff0000;
    		border-bottom: 15px solid #e9ff0000;
    		border-right: 34px solid white;
    		display: inline-block;
		}
	</style>
</head>
<body>
        <div class="side_div_in">
            <div class="side_div_in_bigpic">
                <img class="side_div_in_bigpic_img" src="<%=request.getContextPath() %>/compy_table/Compy_TableServlet_front?method=many_pics_display&&mail=${authority_compy.cp_contact_email}&&which_one=bigpic" alt="大頭照">
            </div>
            <ul>
                <li>
                	<a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info.jsp?display=1">
                		<c:if test="${display==1 }">
                			<div class="currently_div_is_btn_in_side">
                			業者會員資訊
                    		</div>
                    		<div class="currently_div_is_btn"></div>
                    	</c:if>
                    	<c:if test="${display!=1 }">
                			<div class="div_is_btn_in_side">
                			業者會員資訊
                    		</div>
                    	</c:if>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info_updatePic.jsp?display=2">
                    	<c:if test="${display==2 }">
                    		<div class="currently_div_is_btn_in_side">
                    		業者照片更改
                    		</div>
                    		<div class="currently_div_is_btn"></div>
                    	</c:if>
                    	<c:if test="${display!=2 }">
                    		<div class="div_is_btn_in_side">
                    		業者照片更改
                    		</div>
                    	</c:if>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info_updatePwd.jsp?display=3">
                    	<c:if test="${display==3 }">
                    		<div class="currently_div_is_btn_in_side">
                    		業者密碼更改
                    		</div>
                    		<div class="currently_div_is_btn"></div>
                    	</c:if>
                    	<c:if test="${display!=3 }">
                    		<div class="div_is_btn_in_side">
                    		業者密碼更改
                    		</div>
                    	</c:if>
                    </a>
                </li>   
                <c:if test="${authority_compy.authority==11}">
                	<li>
                    	<a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info_updateBusiness.jsp?display=4">
                    		<c:if test="${display==4 }">
                    			<div class="currently_div_is_btn_in_side">
                    			傳營業登記證
                    			</div>
                    			<div class="currently_div_is_btn"></div>
                    		</c:if>
                    		<c:if test="${display!=4 }">
                    			<div class="div_is_btn_in_side">
                    			傳營業登記證
                    			</div>
                    		</c:if>
                    	</a>
                	</li>
                </c:if>
                <c:if test="${authority_compy.authority==12}">
                	<li>
                    	<a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_info_updateBusiness.jsp?display=4">
                    		<c:if test="${display==4 }">
                    			<div class="currently_div_is_btn_in_side">
                    			營業登記證上傳
                    			</div>
                    			<div class="currently_div_is_btn"></div>
                    		</c:if>
                    		<c:if test="${display!=4 }">
                    			<div class="div_is_btn_in_side">
                    			營業登記證上傳
                    			</div>
                    		</c:if>
                    	</a>
                	</li>
                </c:if>
                <c:if test="${authority_compy.authority==2 }">
                	<c:choose>
                		<c:when test="${rs_check_yn==null}">
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/rest_table/front_CompyAddRest_info.jsp?display=5">
                    				<c:if test="${display==5 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳資訊
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=5 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳資訊
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/rest_table/front_CompyAddPic.jsp?display=6">
                    				<c:if test="${display==6 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳圖片
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=6 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳圖片
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/booking_fixed/user/bk_Table_select.jsp?display=7">
                    				<c:if test="${display==7 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳桌位
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=7 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳桌位
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/booking_fixed/user/bk_Table_index.jsp?display=8">
                    				<c:if test="${display==8 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者座位人數
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=8 }">
                    					<div class="div_is_btn_in_side">
                    						業者座位人數
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                		</c:when>
                		
                		
                		
                		
                		<c:when test="${fn:contains(rs_check_yn, '2')}">
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/rest_table/front_CompyAddRest_info.jsp?display=5">
                    				<c:if test="${display==5 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳資訊
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=5 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳資訊
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/rest_table/front_CompyAddPic.jsp?display=6">
                    				<c:if test="${display==6 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳圖片
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=6 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳圖片
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/booking_fixed/user/bk_Table_select.jsp?display=7">
                    				<c:if test="${display==7 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳桌位
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=7 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳桌位
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/booking_fixed/user/bk_Table_index.jsp?display=8">
                    				<c:if test="${display==8 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者座位人數
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=8 }">
                    					<div class="div_is_btn_in_side">
                    						業者座位人數
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                		</c:when>
                		
                		
                		
                		
                		<c:when test="${fn:contains(rs_check_yn, '1')}">
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/rest_table/front_CompyAddRest_info.jsp?display=5">
                    				<c:if test="${display==5 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者餐廳資訊
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=5 }">
                    					<div class="div_is_btn_in_side">
                    						業者餐廳資訊
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_order.jsp?display=9">
                    				<c:if test="${display==9 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者訂單資訊
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=9 }">
                    					<div class="div_is_btn_in_side">
                    						業者訂單資訊
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                			<li>
                    			<a href="<%=request.getContextPath() %>/front-end/compy_table/front_CompyMembershipPlatform_ad.jsp?display=10">
                    				<c:if test="${display==10 }">
                    					<div class="currently_div_is_btn_in_side">
                    					業者購買廣告
                    					</div>
                    					<div class="currently_div_is_btn"></div>
                    				</c:if>
                    				<c:if test="${display!=10 }">
                    					<div class="div_is_btn_in_side">
                    						業者購買廣告
                    					</div>
                    				</c:if>
                    			</a>
                			</li>
                		</c:when>
                	</c:choose>
                </c:if>
            </ul>
     <script>
     	$(".div_is_btn_in_side").mouseover(function(){
     		let that=$(this)
     		$(that).fadeIn(5000,function(){
     			$(that).addClass("open_color");
     		});
     	})
     	$(".div_is_btn_in_side").mouseout(function(){
     		let that=$(this)
     		$(that).removeClass("open_color");
     	})
     </script>
</body>
</html>