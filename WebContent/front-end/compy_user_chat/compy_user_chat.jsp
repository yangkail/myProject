<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>聊天室</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-use/compy_user_chat_use/compy_user_chat_use.css"/>
</head>
<body onload="connect();" onunload="disconnect();">
	<div class="info_chat_use">
        <div class="info_img">
            <img id="only_img" src="<%=request.getContextPath() %>/images/—Pngtree—vector chat icon_3762784.png" alt="" title="與客服聯絡">
        </div>
        <div class="all_div_chat onoe">
        	<div class="noly_div">
        		<div class="just_word">客服人員</div>
            	<button class="X_btn">X</button>
        	</div>
        	<div class="info_chat_div" id="info_chat_div">
            
        	</div>
        	<div class="message_div">
            	<input type="text" id="message" class="message_input" onkeydown="if (event.keyCode == 13) sendMessage();">
            	<input type="submit" class="submit_btn" value="傳送" onclick="sendMessage();">
            	<input type="hidden" id="friend" value="客服人員">
        	</div>
        </div>
    </div>
    <script>
        $("#only_img").click(function () {
            $(".info_img").addClass("onoe");
            $(".all_div_chat").fadeIn(1000,function(){
                $(".all_div_chat").removeClass("onoe");
            })
        })
        $(".X_btn").click(function () {
            $(".all_div_chat").fadeOut(1000,function(){
                $(".all_div_chat").addClass("onoe");
                $(".info_img").removeClass("onoe");
            })
            
        })
        
        //以下為連線開始
        var MyPoint = "/Websocketchat/${userName}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		
		var messagesArea= document.getElementById("info_chat_div");
		var self = '${userName}';
		var webSocket;
		
		//連線(1)
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
			webSocket.onopen = function(event) {
				console.log(event)
				console.log("Connect Success!");
			};
			
			
			webSocket.onmessage = function(event) {
				//從webSocket中取到值
				var jsonObj = JSON.parse(event.data);
				//判斷如果取道的是open(連線)
				if ("open" === jsonObj.type) {
					var friend = $("#friend").val();
					var jsonObj = {
							"type" : "history",
							"sender" : self,
							"receiver" : friend,
							"message" : ""
						};
					webSocket.send(JSON.stringify(jsonObj));
				} else if ("history" === jsonObj.type) {
					messagesArea.innerHTML = '';
					var ul = document.createElement('ul');
					ul.id = "area";
					messagesArea.appendChild(ul);
					// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
					var messages = JSON.parse(jsonObj.message);
					for (var i = 0; i < messages.length; i++) {
						var historyData = JSON.parse(messages[i]);
						var showMsg = historyData.message;
						var li = document.createElement('li');
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						historyData.sender === self ? li.className += 'me' : li.className += 'friend';
						li.innerHTML = showMsg;
						ul.appendChild(li);
					}
					messagesArea.scrollTop = messagesArea.scrollHeight;
				} else if ("chat" === jsonObj.type) {
					var li = document.createElement('li');
					jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
				} else if ("close" === jsonObj.type) {
					
				}
				
			};
			
			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}
		//訊息傳送
		function sendMessage() {
			var inputMessage = document.getElementById("message");
			var friend = $("#friend").val();
			var message = inputMessage.value.trim();

			if (message === "") {
				alert("Input a message");
				inputMessage.focus();
			} else if (friend === "") {
				alert("Choose a friend");
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : friend,
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}

		
		function disconnect() {
			webSocket.close();
		}
		
    </script>
</body>
</html>