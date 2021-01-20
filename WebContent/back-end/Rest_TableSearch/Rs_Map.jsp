<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest_table.model.*"%>
<%
    Rest_TableService rest_tableSvc = new Rest_TableService();
    List<Rest_TableVO> list = rest_tableSvc.getRsAddress("");
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
#map {
	height: 500px;
	width: 500px;
}
</style>
</head>

<body>

	<div id="map" class="embed-responsive embed-responsive-16by9"></div>
	</button>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=金鑰匙&callback=initMap">
        </script>
	<script>
        var map;
        function initMap() {
  var markers = [];
  var infoWindows = [];
  var loaction;
  var geocoder = new google.maps.Geocoder();
  var info_config = [
	<c:forEach var="rest_TableVO" items="${list}">
    +"${rest_TableVO.rs_address}"+
    '<span>'+"${rest_TableVO.rs_name}"+'</span><br/>'+
    '<img class="infoImg" src="<%=request.getContextPath()%>/search?action=getOne_For_Display1&rs_id=${rest_TableVO.rs_id}"><br/>'
    ,                                   
    </c:forEach>
    ];


  //建立地圖 marker 的集合
  var marker_config = [
	<c:forEach var="rest_TableVO" items="${list}">
	{
    address: "${rest_TableVO.rs_address}"
    },
  </c:forEach>];  

  //geocoder主程式
  function _geocoder(address, callback){
    geocoder.geocode({
      address: address
    }, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        loaction = results[0].geometry.location;
        callback(loaction); //用一個 callback 就不用每次多寫上面這段
      }
    });
  }

  //使用地址或名稱標出位置
  _geocoder('台灣民主紀念館',function(address){
    var map = new google.maps.Map(document.getElementById('map'), {
      center: address,
      zoom: 14
    });

    //設定資訊視窗內容
    info_config.forEach(function(e,i){
      infoWindows[i] = new google.maps.InfoWindow({
        content: e
      });
    });

    //標出 marker
    marker_config.forEach(function(e,i){
      _geocoder(e.address,function(address){
        var marker = {
          position:address,
          map:map
        }
        markers[i] = new google.maps.Marker(marker);
        markers[i].setMap(map);
        markers[i].addListener('click', function() {
          infoWindows[i].open(map, markers[i]);
        });
      });
    });
  });

}
    </script>
</body>

</html>