<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<style>

.rowno-gutters{
	border: 2px orange solid;
	max-width: 1000px;
	height:280px;
	margin:0px auto;
	margin-top:30px;
}
.col-md-4{
float: left;
margin-right:20px;
}
</style>
</head>

<body>
<div class="card mb-3" >
  <div class="rowno-gutters">
    <div class="col-md-4">
      <img src="<%=request.getContextPath() %>/images/superman.jpg" class="card-img" alt="..." width="280" height="280" >
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">餐廳名稱RS_NAME</h5>
        <p class="card-text">餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,
       						   餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,餐廳介紹RS_INTRO,</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
      </div>
    </div>
  </div>
</div>
</body>
</html>