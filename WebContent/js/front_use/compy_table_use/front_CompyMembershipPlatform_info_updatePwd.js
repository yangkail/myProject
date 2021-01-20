$("#pwd_original").blur(function() {
	var pwd_original = $("#pwd_original").val();
	var obj = {
		"method" : "pwd_update",
		"pwd_original" : pwd_original
	}
	console.log("123");
	$.ajax({
		url : "/TEA102G1/compy_table/Compy_TableServlet_front",
		type : "POST",
		data : obj,
		dataType:"JSON",

		success : function(result) {
            if(result.final=="error"){
            	$("#pwd_original_span").attr("style","color:red")
                $("#pwd_original_span").text("舊密碼輸入錯誤")
            }else if(result.final=="success"){
            	$("#pwd_original_span").attr("style","color:green")
                $("#pwd_original_span").text("正確符合")
            }
		},
		error : function(err) {
		}
	})
})
$("#submit_btn").on("click", function () {
            var pwd_update_comfirm = $("#pwd_update_comfirm").val();

            var pwd_update = $("#pwd_update").val();

            if (pwd_update_comfirm == pwd_update) {
            	$("#pwd_update_span").attr("style","color:green")
                $("#pwd_update_span").text("相符");
                if ($("#pwd_original_span").text() == "正確符合") {
                	$("#all_span").attr("style","color:green")
                    $("#all_span").text("密碼更改成功")

                    let obj={
                        "method":"pwd_update_comfirm",
                        "pwd_update":pwd_update,
                    }

                    $.ajax({
                        url: "/TEA102G1/compy_table/Compy_TableServlet_front",
                        type: "POST",
                        data: obj,
                        dataType: "JSON",

                        success: function (result) {
                        	if(result.final=="success"){
                        		window.location.replace("/TEA102G1/front-end/compy_table/front_CompyLogin.jsp");
                        		alert("密碼更改成功,請重新登入");
                        	}
                        },
                        error: function (err) {
                        }
                    })

                } else {
                	$("#all_span").attr("style","color:red")
                    $("#all_span").text("請確認舊密碼是否正確")
                }
            } else {
            	$("#pwd_update_span").attr("style","color:red")
                $("#pwd_update_span").text("二次密碼輸入不符");
            }

        })