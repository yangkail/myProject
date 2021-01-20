<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Q&A</title>
<style type="text/css">
#QandA .Question { /*標題的CSS定義*/
	width: 100%;
	border-bottom: 1px #aaa dotted;
}

#QandA .Answer { /*內文的CSS定義*/
	display: none;
	width: 100%;
	padding: 10px 0px;
	background: #efefef;
	border-bottom: 1px #aaa dotted;
}

#QandA:hover { /*滑鼠移至區塊變色*/
	background: #ebf6f7;
}
</style>
</head>
<body>

<h3>Q&A</h3>
	<div id="QandA">

		<p class="Question">Question - 1</p>

		<div class="Answer">

			<p>Answer - 1</p>

		</div>

	</div>

	<div id="QandA">

		<p class="Question">Question - 2</p>

		<div class="Answer">

			<p>Answer - 2</p>

		</div>

	</div>


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