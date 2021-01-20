<%@page import="com.compy_table.model.Compy_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Compy_TableVO compy_TableVO = (Compy_TableVO) request.getAttribute("compy_TableVO");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>業者註冊頁面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/index_use/footer.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-use/compy_table_use/front_addCompyRegister.css">
<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.5.1.min.js"></script>
<style>
	div.top_div{
		width: 60%;
    	margin: 0 auto;
    	margin-top: 105px;
	}
	div.compy_info_div{
		width: 60%;
    	margin: 0 auto;
    	margin-top:20px
	}
	div.compy_contact_div{
		width: 60%;
    	margin: 0 auto;
    	margin-top:20px
	}
	div.compy_account_pwd_div{
		width: 60%;
    	margin: 0 auto;
    	margin-top:20px
	}
</style>
</head>
<body>
	<!-- HEADER -->
	<div class="topBackplane">
		<jsp:include page="/front-end/public_jsp/header.jsp"></jsp:include>
	</div>
	<!-- HEADER -->
	
	<div class="top_div">
		<h1>業者會員註冊</h1>
	</div>
	<form action="<%=request.getContextPath()%>/compy_table/Compy_TableServlet_front" method="POST">
		<!------------公司資訊------------>
		<div class="compy_info_div">
			<table>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司名稱 :</td>
					<td><input type="text" name="cp_name" id="cp_name" required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_name()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_name}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司統編 :</td>
					<td><input type="text" name="cp_id" id="cp_id"
						required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_id()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_id}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司地址 :</td>
					<td><input type="text" name="cp_address" id="cp_address"
						size="50" required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_address()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_address}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司負責人 :</td>
					<td><input type="text" name="cp_boss" id="cp_boss"
						required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_boss()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_boss}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
			</table>
			<button type="button" class="reset_compy_info_btn">重新填寫</button>
		</div>

		<!------------公司聯絡人資訊------------>
		<div class="compy_contact_div">
			<table>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司聯絡人 :</td>
					<td><input type="text" name="cp_contact_man"
						id="cp_contact_man" required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_contact_man()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_contact_man}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司聯絡人EMAIL :</td>
					<td>
						<c:if test="${email==null}">
							<input type="email" name="cp_contact_email" id="cp_contact_email" required="required"
								value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_contact_email()%>">
							<span style="color: red; font-size: 5px;">${errorMsgs.cp_contact_email}</span>
						</c:if>
						<c:if test="${email!=null}">
							<input type="email" name="cp_contact_email" id="cp_contact_email_gmail" 
								value="${email}" readonly="readonly">
							<span style="color: red; font-size: 5px;">${errorMsgs.cp_contact_email}</span>
						</c:if>
					</td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 公司聯絡人電話 :</td>
					<td><input type="text" name="cp_phone" id="cp_phone"
						required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_phone()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_phone}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
			</table>
			<button type="button" class="reset_compy_contact_btn">重新填寫</button>
		</div>

		<!------------公司帳號密碼設置------------>
		<div class="compy_account_pwd_div">
			<table>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 帳號設置 :</td>
					<td><input type="text" name="cp_account" id="cp_account"
						required="required"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_account()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_account}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 密碼設置 :</td>
					<td><input type="password" name="cp_pwd" id="cp_pwd"
						required="required" placeholder="請輸入6~15大小寫英文及數字(不可輸入標點符號)"
						value="<%=(compy_TableVO == null) ? "" : compy_TableVO.getCp_pwd()%>">
						<span style="color: red; font-size: 5px;">${errorMsgs.cp_pwd}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
				<tr>
					<td><span class="star_display">*</span> 密碼二次設置 :</td>
					<td><input type="password" name="cp_pwd_again"
						id="cp_pwd_again" required="required"> <span
						style="color: red; font-size: 5px;">${errorMsgs.cp_pwd_again}</span></td>
				</tr>
				<tr style="height: 20px;">
				</tr>
			</table>
			<button type="button" class="reset_account_pwd_btn">重新填寫</button>
		</div>
		<div class="submit_btn">
			<input type="submit" value="送 出 註 冊"> 
			<input type="hidden" name="method" value="insert_new_compy">
		</div>
	</form>
	<button id="magic_little_button">神奇小按鈕</button>
	
    <!-- FOOTER -->
    <div class="footer">
		<jsp:include page="/front-end/public_jsp/footer.jsp"></jsp:include>
	</div>
	<!-- FOOTER -->
	<script src="<%=request.getContextPath()%>/js/front_use/compy_table_use/front_addCompyRegister.js"></script>
	<script>
		$("#magic_little_button").click(function(){
			$("#cp_name").val("超級難吃有限公司");
			$("#cp_id").val("12312312");
			$("#cp_address").val("新北市新店區德正街27巷29弄53號4樓");
			$("#cp_boss").val("洪紹軒");
			$("#cp_contact_man").val("洪紹唐");
			$("#cp_contact_email").val("asd0000fg@gmail.com");
			$("#cp_phone").val("09130652922");
			$("#cp_account").val("sars521101");
			$("#cp_pwd").val("123456");
			$("#cp_pwd_again").val("123456");
		})
	</script>
</body>
</html>