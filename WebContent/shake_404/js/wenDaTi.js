function getRootPath_web() {
    //獲取當前網址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //獲取主機地址之後的目錄，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //獲取主機地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //獲取帶"/"的專案名，如：/uimcardprj
    projectName = pathName.split("/");

}
var div_qrcode;
var projectName
var websocket;
var qa1;
var qa2;
var rs_name;
var that_div;
var that;
var wendati_rs = $("#wendati_rs");
var qrcode_table;
var form_data = {
    "wendati": "start",

}
window.onload = function () {

    getRootPath_web();
    // websocket = new WebSocket(wsUri);
    // websocket.onopen = (e) => {
    //     // let jsonString = JSON.stringify(form_data);
    //     // websocket.send(jsonString);

    //     let jsonString = JSON.stringify(form_data);
    //     websocket.send(jsonString);
    //     console.log('open connection');
    // }
    // websocket.onmessage = (e) => {

    //     let jsonObj = JSON.parse(e.data);
    //     console.log("onmessage="+e.data)
    //     qa1 = jsonObj.Q1;
    //     qa2 = jsonObj.Q2;
    // }

};

//設定webWebSocket
var wsUri = getRootUri() + "/TEA102G1/wenDaTi";

function getRootUri() {
    return "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" + (document.location.port == "" ? "8081" : document.location.port);
}




//jq.confirm全域設定
jconfirm.defaults = {
    boxWidth: '30%',
    useBootstrap: false,
    type: 'orange',
    typeAnimated: true,

}


$("#wenDaTi").on("click", function () {
    ask_wenDaTi();
})




function ask_wenDaTi() {

    console.log("問題開始")

    websocket = new WebSocket(wsUri);
    websocket.onopen = (e) => {
        // let jsonString = JSON.stringify(form_data);
        // websocket.send(jsonString);

        // let jsonString = JSON.stringify(form_data);
        // websocket.send(jsonString);
        console.log('open connection');
    }
    websocket.onmessage = (e) => {
        let jsonObj = JSON.parse(e.data);
        console.log("onmessage=" + e.data)
        qa1 = jsonObj.Q1;
        qa2 = jsonObj.Q2;
        rs_name = jsonObj.rs_name;
        console.log(rs_name)
        if (rs_name != null) {
            $("#wendati_rs").append(" 有人使用驚喜抽抽樂 抽到:");
            $("#wendati_rs").append(rs_name);
        }
    }
    // websocket.onclose = () => {
    //     console.log('close connection')
    // }
    // websocket.onclose = () => {
    //     console.log('close connection')
    // }
    $.confirm({

        // icon: 'fa fa-gifts',
        title: '請回答下列問題',
        content: '準備好了嗎?',
        boxWidth: '30%',
        useBootstrap: false,
        buttons: {
            Yes: function () {

                $.confirm({
                    title: '請回答下列問題',
                    content: qa1,
                    buttons: {
                        Yes: function () {
                            $.confirm({
                                title: '請回答下列問題',
                                content: qa2,
                                buttons: {
                                    Yes: function () {
                                        // $("#wendati_rs").html("");
                                        rs();
                                        // $("#wendati_rs").append(" 有人使用驚喜抽抽樂 抽到:");
                                    },
                                    No: function () {
                                        // $("#wendati_rs").html("");
                                        rs();
                                        // $("#wendati_rs").append(" 有人使用驚喜抽抽樂 抽到:");
                                    }
                                }
                            });
                        },
                        NO: function () {
                            $.confirm({
                                title: '請回答下列問題',
                                content: qa2,
                                buttons: {
                                    Yes: function () {
                                        // $("#wendati_rs").html("");
                                        rs();
                                        // $("#wendati_rs").append(" 有人使用驚喜抽抽樂 抽到:");
                                    },
                                    No: function () {
                                        // $("#wendati_rs").html("");
                                        rs();
                                        // $("#wendati_rs").append(" 有人使用驚喜抽抽樂 抽到:");
                                    }
                                }
                            });
                        }
                    }
                })
            },
            No: function () {
                $.confirm({
                    closeIcon: true,
                    title: '肚子餓嗎?歡迎使用熱門餐廳挑選',
                    content: '本平台有眾多風格餐廳等著你探索 <img src="https://media.giphy.com/media/t1uL0HsyPZl7NF5fLv/giphy.gif" alt="" style="width: 300px;">',
                    autoClose: 'OK|3500',
                    buttons: {
                        OK: function () {

                        }
                    }
                });
            },
            somethingElse: {
                text: '熱門餐廳挑選',
                btnClass: 'btn-red',
                keys: ['enter', 'shift'],
                action: function () {
                    // $("#wendati_rs").html("");
                    rs();
                    // $("#wendati_rs").append(" 有人使用驚喜抽抽樂 抽到:");
                }
            }
        }
    });

}



function select_time(rs_open_time) {

    switch (rs_open_time) {
        case 1:
            let rs_open_time_1 = "上午8點 - 下午1點";
            return rs_open_time_1;
        case 2:
            let rs_open_time_2 = "下午1點 - 下午6點";
            return rs_open_time_2;
        case 3:
            let rs_open_time_3 = "下午6點 - 晚上23點";
            return rs_open_time_3;
        default:
            let rs_open_time_4 = "全天";
            return rs_open_time_4;

    }


}

function rs() {
    $.confirm({

        title: '這是為你推薦的餐廳',
        content: function () {
            
            that = this;
            console.log(that)
            let xxx=  $(that).closest("div.jconfirm-title-c").find("span.jconfirm-title").html();
            console.log(xxx)
            return $.ajax({
                url: '/TEA102G1/WenDaTi_Ajax.do',
                dataType: 'json',
                method: 'POST',
            }).done(function (data) {
                // console.log(data)
                let jsonString = JSON.stringify(data);
                websocket.send(jsonString);
                let rs_open_time = data.rs_open_time;
                that.setContent('<h2>餐廳名稱:' + data.rs_name + '</h2>');
                that.setContentAppend(
                    '<div class="card" style="width:18rem;">'
                    + '<div class="card-body">'
                    + ' <h3 class="card-title_1" >餐點風格:' + data.rs_type + '</h3>'
                    + '<a href="/' + projectName[1] + '/front-end/booking_table/booking.jsp?name=action&value='+data.rs_id+'">'
                    + '<img class="card-img-top img-fluid" src="/TEA102G1/WenDaTi_Servler.do?method=get_the_pic&id=' + data.rs_id + '&which=rs_pic" alt="Card image cap">'
                    + '</a>'
                    + ' <h4 class="card-title_1">營業時間:' + select_time(rs_open_time) + '</h4>'
                    + ' <h4 class="card-title_1">餐廳地址:' + data.rs_address + '</h4>'
                    + ' <h4 class="card-title_1">聯絡方式:' + data.rs_phone + '</h4>'
                    + ' <h3 class="card-title_1">餐廳介紹:</h3>'
                    + ' <p class="card-text_1" style="color:rgb(17, 49, 231);">' + data.rs_intro + '</p>'
                    + '</div>'
                    + '</div>'
                    
                );
            }).fail(function () {
                that.setContentAppend('<div>Fail!</div>');
            }).always(function(data){
                let rs_id  = data.rs_id;
                create_qrcode(rs_id);
                console.log("always"+data.rs_id)
            })

        }
    })


}

function get_rs_id_Uri() {
    return "http://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" + (document.location.port == "" ? "8081" : document.location.port);
}

function create_qrcode(rs_id){
    // div_qrcode.clear(); // clear the code.
    document.getElementById("div_qrcode").innerHTML = "";
    $("#div_qrcode").css("display","block")

    
    div_qrcode= new QRCode(document.getElementById("div_qrcode"), {
        text:get_rs_id_Uri()+'/'+projectName[1]+'/front-end/booking_table/booking.jsp?name=action&value='+rs_id,
        width: 100,
        height: 100,
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H
    });

};