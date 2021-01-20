<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.css' />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/shake_404/css/wenDaTi.css">
</head>


<body>

  
   <div class="ask">
        <img src="https://media.giphy.com/media/OwooMbzO2Zb9mYDeMw/giphy.gif" alt=""  id="wenDaTi" style="color:rgb(223, 114, 13);">
<!--         <img src="https://cdn.glitch.com/0e4d1ff3-5897-47c5-9711-d026c01539b8%2Fbddfd6e4434f42662b009295c9bab86e.gif?v=1573157191712" -->
<!--         alt=""   id="wenDaTi" /> -->
        <div id="div_qrcode" style="display: none;" ></div>
    </div>
    
</body>

<!-- <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script> -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js'></script>
<script src="<%=request.getContextPath()%>/shake_404/js/wenDaTi.js"></script>

</html>