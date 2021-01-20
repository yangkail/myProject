$("#send_btn").click(function () {
            var cp_pwd = $("#cp_pwd").val();
            var cp_pwd_comfirm = $("#cp_pwd_comfirm").val();

            if (cp_pwd != cp_pwd_comfirm) {
                $(".div_here1").attr("style", "color:red;")
                $(".div_here1").text("二次密碼輸入錯誤")
            } else {
                $(".div_here1").attr("style", "color:green;")
                $(".div_here1").text("符合")
                var obj = {
                    "method": "update_pwd_final",
                    "cp_pwd": cp_pwd
                }
                $.ajax({
                    url: "/TEA102G1/compy_table/Compy_TableServlet_front",
                    type: "POST",
                    data: obj,
                    dataType: "JSON",

                    success: function (result) {
                    	console.log(result.final);
                        if (result.final=="is_ok") {
                            window.location.replace("/TEA102G1/front-end/compy_table/front_CompyLogin.jsp");
                        	alert("密碼更改成功,請重新登入")
                        }
                    	if(result.final=="error"){
                        	window.location.replace("/TEA102G1/front-end/compy_table/front_CompyForget.jsp");
                        	alert("錯誤逾時,請您重新取得驗證信");
                        }else{
                        	$(".div_here2").attr("style", "color:red;")
                            $(".div_here2").val("伺服器錯誤")
                        }
                    },
                    error: function (err) {

                    }
                })
            }
        })