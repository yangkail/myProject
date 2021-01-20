$("#rs_big_pic").on('change',function() {
			$(".rs_big_pic_display").append("");
			var reader = new FileReader();
			reader.readAsDataURL(this.files[0]);
			reader.addEventListener("load", function(e) {
				let base64 = e.target.result;
				$(".rs_big_pic_display").html(
						'<img class="rs_big_pic_display_img" src="' + base64
								+ '">');
			})
		})
$("#final_btn").click(function() {
	alert("已送出審核申請");
})