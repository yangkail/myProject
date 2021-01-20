$("#cp_bigpic").on("change", function() {
	console.log("怪")
	var reader = new FileReader();
	reader.readAsDataURL(this.files[0]);
	reader.addEventListener("load", function(e) {
		$("#cp_bigpic_img").attr("src", "");
		$("#bigpic_img").attr("src","");
		let obj = new FormData($("#bigpic_form")[0]);
		obj.append("method","update_bigpic");
		$.ajax({
			url : "/TEA102G1/compy_table/Compy_TableServlet_front",
			type : "POST",
			data : obj,
			contentType : false,
			processData : false,
			cache : false,

			success : function(result) {
				$("#cp_bigpic_img").attr("src", e.target.result);
				$("#bigpic_img").attr("src", e.target.result);
			},
			error : function(err) {
				alert("大頭照更新錯誤");
			}
		})
	})
})

$("#cp_logo").on("change", function() {
	var reader = new FileReader();
	reader.readAsDataURL(this.files[0]);
	reader.addEventListener("load", function(e) {
		$("#cp_logo_img").attr("src", "");
		let obj = new FormData($("#logo_form")[0]);
		obj.append("method","update_logo");
		$.ajax({
			url : "/TEA102G1/compy_table/Compy_TableServlet_front",
			type : "POST",
			data : obj,
			contentType : false,
			processData : false,
			cache : false,

			success : function(result) {
				$("#cp_logo_img").attr("src", e.target.result);
			},
			error : function(err) {
				console.log(err);
				alert("大頭照更新錯誤");
			}
		})
	})
})