<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@page import="com.rest_table.model.Rest_TableService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<% 
	Rest_TableService rest_TableService=new Rest_TableService();
	Rest_TableVO rest_TableVO=rest_TableService.getOneAll(request.getParameter("value"));
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>

#map{
height:400px;
width:800px;
}
</style>
</head>

<body>

	<div id="map" class="embed-responsive embed-responsive-16by9"></div>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDiAJMhzMG9W1LwN3f8dMqW8KIdWsxwSIc&callback=initMap"></script>
	<script>
	var map;

	function initMap() {
	  var position = {};
	  var map = new google.maps.Map(document.getElementById('map'), {
	    zoom: 18,
	    center: position
	  });
	  var marker = new google.maps.Marker({
	    position: position,
	    map: map
	  });
	  var geocoder = new google.maps.Geocoder();
	  geocoder.geocode( { 'address': '<%=rest_TableVO.getRs_address()%>'}, function(results, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	      map.setCenter(results[0].geometry.location);
	      var marker = new google.maps.Marker({
	          map: map,
	          position: results[0].geometry.location
	      });
	      console.log(marker.position.lat)
	    } else {
	      alert("Geocode was not successful for the following reason: " + status);
	    }
	  });
	}
	
    </script>
</body>

</html>