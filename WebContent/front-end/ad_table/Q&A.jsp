<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Q&A</title>
<style type="text/css">
#QandA .Question { /*標題的CSS定義*/
	width: 100%;
	border-bottom: 1px #aaa dotted;
/* 	padding: 10px 0px; */
	margin-left: auto;  
    margin-right: auto; 
}

#QandA .Answer { /*內文的CSS定義*/
	display: none;
	width: 100%;
	padding: 10px 0px;
	background: #efefef;
	border-bottom: 1px #aaa dotted;
/* 	height:100px; */
}

#QandA:hover { /*滑鼠移至區塊變色*/
	background: #ebf6f7;
}
.box{
     width:750px; 
    
}
.Question{
     background:wheat; 
     height:30px;
     font-size:20px;
     
/*      font-family: "微軟正黑體"; */

}
mark{
     background-color:burlywood;
}
.tittle{
     color:brown
}
 body{
      font-family: "微軟正黑體";
    }
</style>
</head>
<body>

<center><div><h1 class="tittle">訂味Q&A</h1></div></center>
<center><div class="box">
	<div id="QandA">

		<p class="Question">請問可以攜帶寵物嗎？</p>

		<div class="Answer">

			<p>您好~因每間餐廳的狀況不同，建議您電話訂位時先詢問該店的服務人員，以利為您安排喔！謝謝！</p>

		</div>

	</div>

	<div id="QandA">

		<p class="Question">是否能預先訂位？位子保留多久呢？</p>

		<div class="Answer">

			<p>您好~歡迎您至<mark>訂味</mark>線上預訂，座位為您保留10分鐘，謝謝！</p>

		</div>

	</div>
	
	<div id="QandA">

		<p class="Question">有無限上網服務嗎?</p>

		<div class="Answer">

			<p>餐廳內都有提供免費無線上網服務</p>

		</div>

	</div>
	
	<div id="QandA">

		<p class="Question">請問餐廳內有設立吸煙區嗎?</p>

		<div class="Answer">

			<p>餐廳皆為開放式空間，未提供吸煙區座位</p>

		</div>

	</div>
	
	<div id="QandA">

		<p class="Question">請問是否要戴口罩?</p>

		<div class="Answer">

			<p>配合政府，防疫期間進餐廳前請配戴口罩，會有人量體溫，體溫若超過37度無法入內用餐，感謝配合~</p>

		</div>

	</div>
	
	</div></center>


	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script type="text/javascript">
		jQuery(function($) {

			$("p.Question").css({
				cursor : "pointer"
			}).click(function() {

				$(this).next().toggle("normal");

			});

		});
	</script>
</body>
</html>