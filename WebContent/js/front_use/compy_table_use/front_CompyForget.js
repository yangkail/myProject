$("#cp_account").blur(function() {
	var cp_account = $("#cp_account").val();
	var obj = {
		"method" : "account_confirm",
		"cp_account" : cp_account
	}
	$.ajax({
		url : "/TEA102G1/compy_table/Compy_TableServlet_front",
		type : "POST",
		data : obj,
		dataType : "JSON",
		success : function(result) {
			 if(result.final=="no_account"){
                 $(".div_here1").attr("style","color:red;");
                 $(".div_here1").text("查無此帳號,請重新輸入");
             }
			 if(result.final=="is_ok"){
                 $(".div_here1").attr("style","color:green;");
                 $(".div_here1").text("帳號吻合");
			 }
		},
		error : function(err) {

		}
	})
})
$("#send_btn").click(function(){
    var comfirm = $(".div_here1").text();
    var cp_account=$("#cp_account").val();
    var obj={
        "method": "account_confirm_send_email",
        "cp_account": cp_account
    }
    if (comfirm == '帳號吻合') {
        $.ajax({
            url: "/TEA102G1/compy_table/Compy_TableServlet_front",
            type: "POST",
            data: obj,
            dataType: "JSON",
            success : function(result) {
            	if(result.final="is_ok"){
            		window.location.replace("/TEA102G1/front-end/compy_table/fakeIndex.jsp");
            	}else{
                    $(".div_here2").attr("style", "color:red;")
                    $(".div_here2").val("伺服器錯誤")
            	}
            },
            error : function(err) {

            }
        })
    } else {
        $(".div_here2").attr("style", "color:red;")
        $(".div_here2").val("帳號輸入不符,請確認")
    }
 })