var rs_id = $("input[name='rs_id']").val();

var projectName;
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


function setTable() {

  let gs_select_time = $("#gs_select_time").val();
  // 產生出table的function
  let form_date = {
    "rs_id": rs_id,
    
  }

  let json_data = JSON.stringify(form_date);
  $.ajax({
    url: "/"+projectName[1]+"/Rest_Seat_Coordinate_Table_Getlist_Ajax.do",           // 資料請求的網址
    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    data: json_data,                  // 傳送資料到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    beforeSend: function () {       // 在 request 發送之前執行

    },
    success: function (data) {      // request 成功取得回應後執行
      // border - radius: 50 %;
      // position: fixed;
      // width: 100px;
      // height: 100px;
      // /* border: 1px solid #000000; */
      // background: linear - gradient(to right, #c02425, #f0cb35);
      // style="position: relative; top: 166.667px; left: 127px;"
      let list_html = '';
      var num = 0;
      $.each(data, function (index, item) {
        item.rs_seat_xy = item.rs_seat_xy.split(",");
        let rs_top= item.rs_seat_xy[0]-90;
        let rs_left= item.rs_seat_xy[1]-40;
        list_html += '<div data-id="' + item.rs_id + '" data-sieral="' + item.rs_seat_sieral + '" data-xy="' + item.rs_seat_xy + '">';
        list_html += '<div class="Myclass" style=" width: 100px; height:100px; position:absolute; top : ' + rs_top + 'px ; left : ' + rs_left + 'px; border-radius: 50%; background:rgb(255, 255, 255);border: 1px solid #000000; ">' + (++num) + '';
        list_html += '</div>';
        list_html += '</div>';

      });
      //創造出來放入div
      $("#html-content-holder").html(list_html);

    },
    error: function (xhr) {// request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    }
  });
}
function init() {
  let form_date = {
    "rs_id": rs_id,
  }
  let jsonString = JSON.stringify(form_date);

  $.ajax({
    url: "/"+projectName[1]+"/Booking_Fixed_Table_List_Ajax.do",           // 資料請求的網址
    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    data: jsonString,                  // 傳送資料到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    beforeSend: function () {       // 在 request 發送之前執行
      $("ul.task_list").html('<li style="text-align: center;"><i class="fas fa-spinner fa-spin fa-3x"></i></li>');
    },
    success: function (data) {      // request 成功取得回應後執行

      // console.log(data);

      let list_html = '';
      $.each(data, function (index, item) {

        list_html += '<li data-id="' + item.rs_sieral + '" data-sort="' + item.sort + '">';
        list_html += '<div class="item_flex">';
        list_html += '<div class="middle_block">';
        list_html += '<p class="para">' + '<span>人數:</span>' + item.rs_table_seat + '</p>';
        list_html += '<input type="hidden" name="rs_id" value="' + item.rs_id + '">';
        list_html += '<input type="hidden" class="rs_seat_sieral_class" value="' + item.rs_seat_sieral + '">';
        list_html += '<p class="pare">' + '<span>桌號:</span>' + item.rs_seat_sieral + '</p>';
        list_html += '<input type="text" class="task_name_update -none" placeholder="更新待辦事項…" value="' + item.rs_table_seat + '">';
        list_html += '</div>';
        list_html += '<div class="right_block">';
        list_html += '<div class="btn_flex">';
        list_html += '<button type="button" class="btn_update">更新</button>';
        list_html += '<button type="button" class="btn_delete">移除</button>';
        list_html += '</div>';
        list_html += '</div>';
        list_html += '</div>';
        list_html += '</li>';
      });
      $("ul.task_list").html(list_html);
    },
    error: function (xhr) {         // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    }
  });
}
$(document).ready(function(){

  $(".wrapper").fadeOut(3000);
  getRootPath_web()
  
});
function show (){
  
  $("#header_container").fadeIn(1000);
  $("#drop").fadeIn(1000);
  $("#body_container").fadeIn(1000);
  $("#header_container").css("display", "block");
  $("#drop").css("display", "block");
  $("#body_container").css("display", "block");
  $("#navbar_bs").fadeIn(1000);
  $("#navbar_bs").css("display", "block");
  init();
  setTable();


 
};
setTimeout(show,3000);







