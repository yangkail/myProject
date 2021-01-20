<%@ page import="java.util.*, java.text.DateFormat,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order_table.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/order_table_use/order_page_use.css">
</head>
<body>
		
<button type="button" class="btn_modal">開啟 Modal 彈跳視窗</button>

<div class="overlay">
  <article>
    <h1>這是 Modal 裡的標題</h1>
    <p>這是 Modal 段落</p>
    <button type="button" class="btn_modal_close">關閉</button>
  </article>
</div>
	 <script src="<%=request.getContextPath() %>/js/jquery/jquery-3.5.1.min.js"></script>
     <script src="<%=request.getContextPath() %>/js/jquery/jquery-ui-1.10.3.custom.min.js"></script>
	 <script src="<%=request.getContextPath() %>/js/front-use/front_all_ordrer_page.js"></script>
	
</body>
</html>