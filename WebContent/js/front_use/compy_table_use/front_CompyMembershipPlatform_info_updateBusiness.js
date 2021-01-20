$("#cp_business").on("change", function() {
	var reader = new FileReader();
	reader.readAsDataURL(this.files[0]);
	reader.addEventListener("load", function(e) {
		$(".info_business_display_img").attr("src", "");
		let obj = new FormData($("#business_form")[0]);
		obj.append("method","update_business");
		$.ajax({
			url : "/TEA102G1/compy_table/Compy_TableServlet_front",
			type : "POST",
			data : obj,
			contentType : false,
			processData : false,
			cache : false,

			success : function(result) {
				$(".info_business_display_img").attr("src", e.target.result);
			},
			error : function(err) {
				alert("大頭照更新錯誤");
			}
		})
	})
})