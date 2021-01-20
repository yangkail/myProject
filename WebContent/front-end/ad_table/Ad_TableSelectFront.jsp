<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad_table.model.*"%>

<!DOCTYPE html>

<%
Ad_TableVO ad_TableVO = (Ad_TableVO) request.getAttribute("ad_TableVO");
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

/* 確認 */
.btn {
  display: inline-block;
  border-radius: 4px;
  background-color: #f4511e;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 20px;
  width: 100px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 1px;
}

.btn span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.btn span:after {
  content: '»';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.btn:hover span {
  padding-right: 25px;
}

.btn:hover span:after {
  opacity: 1;
  right: 0;
}
/* 取消 */
.btn1{
  display: inline-block;
  border-radius: 4px;
  background-color: gray;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 20px;
  width: 100px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 1px;
}

.btn1 span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.btn1 span:after {
  content: '«';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.btn1:hover span {
  padding-right: 25px;
}

.btn1:hover span:after {
  opacity: 1;
  right: 0;
}
.content {
width: 400px;
/*  margin-left: auto;   */
/*  margin-right: auto;  */
}
.box{
 position: absolute ;right:450px;top:950px; 
}
#type{
/*  height:500spx; */
 width:1000px; height:500px;
 font-size: 20px;
 border: 20px solid #FFFACD;
 border-collapse: collapse;
 
}
#type2:hover{
 background-color:#E6E6FA;
}
#word{
font-size: 40px;
}

    </style>
</head>


<body>
   <h2 id="word">廣告月費制<br>選取每日廣告時段</h2>
<%-- 錯誤表列 --%>

<c:if test="${not empty errorMsgs}">
 <font style="color:red">請修正以下錯誤:</font>
 <ul>
  <li>${errorMsgs}</li>
  <c:forEach var="message" items="${errorMsgs}">
   <li style="color:red">${message}</li>
  </c:forEach>
 </ul>
</c:if>

   <div class="content"  style=" width:1000px;height:500px;border:3px tomato dashed;">
  
        
<!--   <tr> -->
<!--       <td>首頁廣告輪播</td> -->
<!--       <td> -->
<!--    <input type="File" name="ad_pic_queue" id="ad_pic_queue" accept="image/*"/> -->
<!--         </td> -->
<!--      </tr> -->
 
<!--      <tr> -->
<!--   <td> -->
<!--    <div id = "preview"><img></div> -->
<!--   </td> -->
<!--      </tr> -->
    
  
        
  
<!--     <tr> -->
<!--       <td><input type="reset" value="清除"> -->
<!--             <input type="text" id="total">元 -->
<!--             <input type="submit" value="送出"></td> -->
  
<!--            </tr> -->
      

  
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/controller/Ad_TableDAOServletFront_End" name="form1" enctype="multipart/form-data">
        <input type="hidden" name="ad_top_time1" id="ad_top_time1" value="0">
        <input type="hidden" name="ad_top_time2" id="ad_top_time2" value="0">
        <input type="hidden" name="ad_top_time3" id="ad_top_time3" value="0">
        <input type="hidden" name="money" id="money" value="0">
          <table id="type">
        <tr id="type2">
           <td align="center">廣告是否至頂</td>
            <td align="center"><input type ="radio" name ="ad_top_yn" value="1">是
            <input type ="radio" name ="ad_top_yn" value="0" checked="checked">否</td>
       </tr>
     
        <tr id="type2">
            <td align="center" class="keyword">購買時段一08:00-16:00</td>
            <td align="center"><input type ="radio" name ="ad_top_time1" id="time1yes" value="888">是
            <input type ="radio" name ="ad_top_time1" id="time1no" value="888" checked="checked">否</td>
            
        </tr>
        <tr id="type2">
            <td align="center" class="keyword">購買時段二 16:00-24:00</td>
            <td align="center"><input type ="radio" name ="ad_top_time2"id="time2yes" value="888">是
            <input type ="radio" name ="ad_top_time2" id="time2no" value="888" checked="checked">否</td>
        </tr>

        <tr id="type2">
            <td align="center" class="keyword">購買時段三 24:00-08:00</td>
            <td align="center"><input type ="radio" name ="ad_top_time3" id="time3yes" value="888">是
            <input type ="radio" name ="ad_top_time3" id="time3no" value="888" checked="checked">否</td>
        </tr>
       
        
        <tr id="type2">
      <td align="center">首頁廣告輪播</td>
      <td align="center">
   <input type="File" name="ad_pic_queue" id="ad_pic_queue" accept="image/*"/>
        </td>
     </tr>
      
      <tr id="type2">
            <td align="center" class="keyword">購買時段費用</td>
      <td align="center">
      <input type="text" name="ad_price" id="span">
     </td>
  </tr>
   </table>

    
     </div>
       <div class="box">
       <button class="btn1" style="vertical-align:middle"><span>取消</span></button>
       <button class="btn"style="vertical-align:middle" name="action" value="insert"><span>確認</span></button>
       <input type="hidden" name="ad_time" id="f_date1" type="text">
  <input type="hidden" name="ad_showtime" id="f_date2" type="text">
  <input type="hidden" name="ad_cancel_time" id="f_date3" type="text">
  <input type="hidden" name="rs_id" value="${rs_id}">
  <input type="hidden" name="ad_status"value="1">
 
      </div>
   </FORM>
</body>
<% 
  java.sql.Date ad_time = null;
  try {
   ad_time = ad_TableVO.getAd_time();
   } catch (Exception e) {
    ad_time = new java.sql.Date(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Date ad_showtime = null;
  try {
   ad_showtime = ad_TableVO.getAd_showtime();
   } catch (Exception e) {
    ad_showtime = new java.sql.Date(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Date ad_cancel_time = null;
  try {
   ad_cancel_time = ad_TableVO.getAd_cancel_time();
   } catch (Exception e) {
    ad_cancel_time = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

 <script>  
      $("input#ad_pic_queue").change(function(){
       if(this.files.length==1){
        let reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener("load",function(){
         $("div#preview img").attr("src",reader.result);
        })
       }else{
        $("div#preview").html("<img>");
       }
      });


 


  
 </script>

 <script>
      var Money = 0;

      $("#time1yes").focus(function () {
          let time1 = $("#time1yes").val();
          let time1_money = parseInt(time1);
          Money += time1_money;
          $("#span").val(Money)
          $("#money").val(Money)
          $("#ad_top_time1").val("1");
      })

      $("#time2yes").focus(function () {
          let time1 = $("#time2yes").val();
          let time1_money = parseInt(time1);
          Money += time1_money;
          $("#span").val(Money)
          $("#money").val(Money)
          $("#ad_top_time2").val("1");
      })

      $("#time3yes").focus(function () {
          let time1 = $("#time3yes").val();
          let time1_money = parseInt(time1);
          Money += time1_money;
          $("#span").val(Money)
          $("#money").val(Money)
          $("#ad_top_time3").val("1");
      })

      $("#time1no").focus(function () {
          let time1 = $("#time1no").val();
          let time1_money = parseInt(time1);
          Money -= time1_money
          $("#span").val(Money)
          $("#money").val(Money)
          $("#ad_top_time1").val("0");
      })

      $("#time2no").focus(function () {
          let time1 = $("#time2no").val();
          let time1_money = parseInt(time1);
          Money -= time1_money
          $("#span").val(Money)
          $("#money").val(Money)
          $("#ad_top_time2").val("0");
      })
      $("#time3no").focus(function () {
          let time1 = $("#time3no").val();
          let time1_money = parseInt(time1);
          Money -= time1_money
          $("#span").val(Money)
          $("#money").val(Money)
          $("#ad_top_time3").val("0");
      })
       
        
   
     
</script>


</html>