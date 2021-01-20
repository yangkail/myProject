<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="com.rest_table.controller.Rest_TableServlet_search"%>

<%@ page import="java.util.*, java.text.DateFormat,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order_table.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
  Order_Rest_TableVO order_rest_tableVO = (Order_Rest_TableVO) request.getAttribute("Order_Rest_TableVO"); // Order_TableServlet.java(Concroller), 存入req的order_tableVO物件
%>

<%--  <%System.out.println(order_tableVO);%> --%>

<%
	String gs_email=(String)session.getAttribute("gs_email");
	Order_TableService order_tableSvc = new Order_TableService();
	List<Order_Rest_TableVO> list = order_tableSvc.get_ORDER_REST(gs_email); //正式時打開使用
	pageContext.setAttribute("list",list);
	
%>





<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>消費者訂單資訊</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/front-use/order_table_use/order_page_use.css">
<style>
</style>

</head>
<body>						
		<div class="btn_1">
			<a href="<%= request.getContextPath()%>/front-end/Guest_Table/guest_profileorder.jsp">	
			<input type="button"  value="全部訂單" name="按鈕名稱"></a>
		</div>
		
		<div class="btn_2">
			<a href="<%= request.getContextPath()%>/front-end/Guest_Table/guest_profileordercomplt.jsp">
			<input type="button" value="已成立訂單" name="按鈕名稱" ></a>
		</div>
		
		<div class="btn_3">
			<a href="<%= request.getContextPath()%>/front-end/Guest_Table/guest_profileordercan.jsp">
			<input type="button" value="已取消訂單" name="按鈕名稱" ></a>
<%-- 			?order_status=${order_tableVO.order_status} --%>	
		</div>	
		<br>

		<table id="type">
			<tr>
				<th>消費者信箱</th>
				<th>餐廳名稱</th>
				<th>訂單編號</th>
				<th>訂單狀態</th>
				<th>訂單成立時間</th>
<!-- 				<th>訂單取消時間</th> -->
				<th>訂單備註</th>
				<th>用餐人數</th>
<!-- 				<th>消費者用餐時段</th> -->
				<th>使用訂金</th>
<!-- 				<th>訂單QR條碼</th> -->
<!-- 				<th>餐廳座位狀態</th> -->
				<th>選取狀態</th>				
			</tr>	
		<%@ include file="pages/page1.file"%> 
		<c:forEach var="order_rest_tableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					
					<td>${order_rest_tableVO.gs_email}</td>
					<td>${order_rest_tableVO.rs_name}</td>
					<td>${order_rest_tableVO.order_id}</td>
					<td id="${order_rest_tableVO.order_id}" class="status_text">
						${order_rest_tableVO.order_status==0?"取消":""}
						${order_rest_tableVO.order_status==1?"已接單":""}
						${order_rest_tableVO.order_status==2?"已報到":""}
					</td>
					<td>
						<c:choose>
						<c:when test="${order_rest_tableVO.order_status==1}">
					<fmt:formatDate value="${order_rest_tableVO.order_time}" pattern="yyyy-MM-dd HH:mm"/>
					</c:when>
					<c:when test="${order_rest_tableVO.order_status==2}">
					<fmt:formatDate value="${order_rest_tableVO.order_time}" pattern="yyyy-MM-dd HH:mm"/>
						</c:when>
						<c:otherwise>
						無
						</c:otherwise>
						</c:choose>
						</td>
<%-- 					<td><fmt:formatDate value="${order_tableVO.order_cancel_time}" pattern="yyyy-MM-dd HH:mm"/></td>  --%>
					<td class="remark">${order_rest_tableVO.gs_order_remark}</td>
					<td>${order_rest_tableVO.gs_people}</td>
<%-- 					<td>${order_tableVO.gs_select_time}</td> --%>
					<td>${order_rest_tableVO.order_deposit}</td>
<%-- 					<td><img src= "<%=request.getContextPath()%>/back-end/order_table/order_table.do?action1=display_pic&order_id=${order_rest_tableVO.order_id}" --%>
<!-- 					height="90" width="90" ></td> -->
<%-- 					<td>${order_tableVO.rs_table_status}</td> --%>
					<td> 
					<c:choose>
						<c:when test="${order_rest_tableVO.order_status==2}">
							<a href="<%=request.getContextPath()%>/front-end/Feedback_Table/frontaddFeedback.jsp?order_id=${order_rest_tableVO.order_id}&rs_id=${order_rest_tableVO.rs_id}">
		   						<button type="button" class="button_THREE_TYPE" id="f">填寫意見調查表</button>
		   					</a>
						</c:when>
							<c:when test="${order_rest_tableVO.order_status==0}">
								<a href="#">
			   						<button type="button" class="button_THREE_TYPE" id="fff">訂單已取消</button>
			   					</a>
							</c:when>			
						<c:otherwise>
							<button type="button" class="btn_modal" id="${order_rest_tableVO.order_id}" >取消訂單</button>
							<div class="overlay" id="${order_rest_tableVO.order_id}"> 
 			  					<article> 
			    				<h1>訂單已取消</h1> 
			    				<button type="button" class="btn_modal_close">關閉</button> 
 			  					</article> 
			 				</div>
						</c:otherwise>
					</c:choose>
					</td> 
			  </tr>	
	</c:forEach>	
		</table>
		
		<div class ="p2" style= "position:relative; left:25px; top:130px;"> 
			<%@ include file="pages/page2.file"%>
		</div>
			

		


			
<!-- 	  <a href="#"> -->
<!-- 		   <button type="button" class="btn_modal">填寫消費者調查表</button>	 -->
<!-- 		   </a>   -->
<!-- 			<div class="overlay"> -->
<!-- 			  <article> -->
<!-- 			    <h1>111</h1> -->
<!-- 			    <button type="button" class="btn_modal_close">關閉</button> -->
<!-- 			  </article> -->
<!-- 			  </div> -->
 
	
	
	 <script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
     <script src="<%=request.getContextPath() %>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
	 <script src="<%=request.getContextPath() %>/js/front-use/front_all_ordrer_page.js"></script>
	 
	 <script type="text/javascript">
	 	$(document).on("click",".btn_modal",function(){
	 		let order_id = $(this).attr("id");
	 			//console.log($(this).attr("id"));
// 	 			console.log(order_id)
	 			
	 			//console.log($("div.overlay"));
	 			$("div.overlay").filter(function(index){
	 				return $(this).attr("id") == order_id;
	 			}).addClass("-on");
	 			
	 			
	 			  // 關閉 Modal
	 			  $("button.btn_modal_close").on("click", function(){
	 			    $("div.overlay").addClass("-opacity-zero");
	 			    
	 			    // 設定隔一秒後，移除相關 class
	 			    setTimeout(function(){
	 			      $("div.overlay").removeClass("-on -opacity-zero");
	 			    }, 500);
	 			  });
	 			  
// 	 			});
	 	
	 			 let uPair = {
	 		    		"method":"update0",
	 		    		"order_id":order_id
	 		    	}
	 		 		
	 		 		

	 	    		
	 	    		$.ajax({
	 	    			url : "/TEA102G1/order_table/Order_TableServlet_front",
	 	    			type : "GET",
	 	    			data : uPair,
	 	    			dataType:"JSON",
	 	    			success : function(result) {
	 	    				if(result.update_ok=="success"){
	 	    				$("td.status_text").filter(function(index){
	 	    	 				return $(this).attr("id") == order_id;
	 	    	 			}).text("已取消");
	 	    				}else if(result.update_fail=="failure"){
	 	    					
	 	    				}
	 	    			},
	 	    			error : function(err) {
	 	    			}
	 	    		})//Ajax 結束
	 		 		
	 		 	})
	 		 
	 		 </script>

</body>
</html>