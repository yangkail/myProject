<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="com.rest_table.model.Rest_TableService"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.servlet.http.Part"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.feedback_table.model.Feedback_TableVO"%>
<%@page import="com.feedback_table.model.Feedback_TableService"%>

<%	

	Rest_TableService rest_Tablesvc = new Rest_TableService();
	String rs_id = request.getParameter("value");
	Rest_TableVO rest_TableVO = (Rest_TableVO) rest_Tablesvc.getOneAll(rs_id);
	pageContext.setAttribute("rest_TableVO", rest_TableVO);
	String addressLine=rest_TableVO.getRs_address();
	String[] address=addressLine.split(",");
	String realyAddress=address[2];
	pageContext.setAttribute("address", realyAddress);
	Rest_TableVO rest_TablePhoto = (Rest_TableVO) rest_Tablesvc.getOneAllOnlyPic(rs_id);
	pageContext.setAttribute("rest_TablePhoto", rest_TablePhoto);
	
	

	
	
	Feedback_TableService fbtService = new Feedback_TableService();
	List<Feedback_TableVO> statList = fbtService.getRs_star(rs_id);
	pageContext.setAttribute("statList", statList);
	
%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3ii.com/lib/w3.css">
<style>
.mySlides {display:none}
</style>
<body>

<div class="w3-content" style="max-width:1100px; margin-left:260px;"><br>

<% String photoString = null;
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view1());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">


<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view2());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">

<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view3());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">

<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view4());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;">

<% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view5());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="mySlides" src="data:image;base64,<%=photoString%>" style="width:90%; height:500px;"><br>


  

<div class="w3-col s4">
    <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view1());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; float:left; display:inline;" onclick="currentDiv(1)">
</div>

    <div class="w3-col s4">
    <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view2());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; float:left; display:inline;" onclick="currentDiv(2)">
</div>
    
    <div class="w3-col s4">
       <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view3());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; float:left; display:inline;" onclick="currentDiv(3)">
</div>
    
 
    <div class="w3-col s4">
    <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view4());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; float:left; display:inline;" onclick="currentDiv(4)">
</div>

  	<div class="w3-col s4">
        <% 
  	try {
  		
  		Base64.Encoder encoder = Base64.getEncoder();
  		photoString = encoder.encodeToString(rest_TablePhoto.getRs_view5());
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
 
%><img class="w3-row-padding w3-section" src="data:image;base64,<%=photoString%>" style="width:18%; float:left; display:inline;" onclick="currentDiv(5)">
</div>
	
</div>

<script>
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function currentDiv(n) {
  showDivs(slideIndex = n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  if (n > x.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = x.length} ;
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
     dots[i].className = dots[i].className.replace(" w3-border-red", "");
  }
  x[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " w3-border-red";
}
</script>

</body>
</html> 
