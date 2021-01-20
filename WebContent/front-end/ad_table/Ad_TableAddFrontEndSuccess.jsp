<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad_table.model.*"%>
<!DOCTYPE html>
<%
String rs_id=(String)session.getAttribute("rs_id");
Ad_TableService ad_TableService=new Ad_TableService();
Ad_TableVO ad_TableVO=ad_TableService.getOneRs(rs_id);
pageContext.setAttribute("ad_TableVO", ad_TableVO);
%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ad_TableSelectFront</title>
    <style type="text/css">

/* h3{ */
/* text-align: center; */
/* } */
table{
    width:400px;
/*    border-collapse: collapse;  */
   /*  格子各有一條線變成一條 */
}
td{
    border-bottom: 1px solid white;padding:10px;
}
tr:nth-child(1){
    background-color: orange;color:black;
}
tr:nth-child(3){
    background-color: wheat;color:black;
}
tr:nth-child(5){
    background-color: wheat;color:black;
}
tr:nth-child(even){
    background-color: burlywood;

}
tr:nth-child(6){
    background-color: orange;color:black;
}
.keyword{
color:brown
}


.content {
width: 400px;
/*  margin-left: auto;   */
/*  margin-right: auto;  */
}
#type{
/*  height:500spx; */
 width:1000px; height:500px;
 font-size: 20px;
 border: 20px solid #FFFACD;
 border-collapse: collapse;
 }
 #word{
font-size: 40px;
}
 #card-btn {
  background-color: rgba(251, 251, 251, 0.8);
  color:blue;
/*   position: absolute; */
  bottom: -55px;
  right: 0;
  width: 150px;
  border-radius: 8px;
  height: 42px;
  font-size: 22px;
  font-family: lato, 'helvetica-light', 'sans-serif';
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: 400;
  cursor: pointer;

}

#card-btn:hover {
  background-color:lightgoldenrodyellow;
}
/* h1{ */
/* text-align: center; */
/* } */
/* .box{position: absolute ;right:0px;bottom:-100px;width:200px;height:200px; */
/*        } */

<meta charset="UTF-8">
<title>Ad_TableAddFrontEndSuccess</title>
   </style>
</head>
<body>
<h3 id="word"><b><購買明細></b></h3>
<div class="content"  style="weight:450px;height:538px;">
 <table id="type">
        <tr>
            <td> 廣告至頂</td>
           <td>${ad_TableVO.ad_top_yn==0?"是否購買至頂廣告:否":"是否購買至頂廣告:是"}</td>
       </tr>
     
        <tr>
            <td class="keyword">購買時段08:00-16:00</td>
            <td>${ad_TableVO.ad_top_time1==0?"購買時段一:否":"購買時段一:是"}</td>
            
        </tr>
        <tr>
            <td class="keyword">購買時段16:00-24:00</td>
            <td>${ad_TableVO.ad_top_time2==0?"購買時段二:否":"購買時段二:是"}</td>
        </tr>

        <tr>
            <td class="keyword">購買時段24:00-08:00</td>
            <td>${ad_TableVO.ad_top_time3==0?"購買時段三:否":"購買時段三:是"}</td>
        </tr>
       
        
  <tr>
      <td>首頁廣告輪播</td>
     <td id = "ad_pic_queue"   >
   <%
    String photoString = null;
    try {
     Base64.Encoder encoder = Base64.getEncoder();
     photoString = encoder.encodeToString(ad_TableVO.getAd_pic_queue());
    } catch (Exception e) {
     e.printStackTrace();
    }
   %>
   <img src="data:image;base64,<%=photoString %>" style="width: 300px;">
   </td>
     </tr>
 

      <tr>
            <td class="keyword">購買時段費用</td>
      <td>價錢:${ad_TableVO.ad_price}</td>
  </tr>
  
</table> 
<label><a href="<%=request.getContextPath()%>/front-end/ad_table/Ad_Pay.jsp"><button type="button"id="card-btn" name="action" value="insert1" style="font-size:20px; font-weight:bold;">結帳去</button></a>
</label><br>
</body>
</html>