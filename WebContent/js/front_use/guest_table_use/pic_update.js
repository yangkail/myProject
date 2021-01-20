$("#gs_big_pic").on("change", function() {
	console.log("有近")
	var reader = new FileReader();
	reader.readAsDataURL(this.files[0]);
	reader.addEventListener("load", function(e) {
		$("#gs_big_pic_img").attr("src", "");
		
		let obj = new FormData($("#bigpic_form")[0]);
		obj.append("action","update_bigpic");
		$.ajax({
			url : "/TEA102G1/Guest_Table/Guest_Table.do",
			type : "POST",
			data : obj,
			contentType : false,
			processData : false,
			cache : false,

			success : function(result) {
				$("#gs_big_pic_img").attr("src", e.target.result);
				
			},
			error : function(err) {
				alert("大頭照更新錯誤");
			}
		})
	})
})