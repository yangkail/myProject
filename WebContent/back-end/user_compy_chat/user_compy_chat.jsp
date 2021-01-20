<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>聊天室</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/back-use/user_compy_chat_use/user_compy_chat_use.css"/>
</head>
<body onload="connect();" onunload="disconnect();">
	<div class="info_chat_use">
        <div class="info_img">
            <img id="only_img" src="<%=request.getContextPath() %>/images/—Pngtree—vector chat icon_3762784.png" alt="" title="與客服聯絡">
        </div>

        <div class="all_div_chat onoe">
            <div class="chat_all_compy">
                <ul class="chat_all_compyss">
                    <!--好友列表-->
                </ul>
            </div>
            <div class="chat_in_here">
                <div class="noly_div">
                    <div class="just_word" id="just_word">
                        <!--點擊好友後顯示名稱位子-->
                    </div>
                    <button class="X_btn">X</button>
                </div>
                <div class="info_chat_div" id="info_chat_div">
						<!-- 訊息顯示 -->
                </div>
                <div class="message_div">
                    <input type="text" id="message" class="message_input"
                        onkeydown="if (event.keyCode == 13) sendMessage();">
                    <input type="submit" class="submit_btn" value="傳送" onclick="sendMessage();">
                    <input type="hidden" id="friend" value="">
                </div>
            </div>
        </div>
    </div>
  <script>
    $("#only_img").click(function () {
        $(".info_img").addClass("onoe");
        $(".all_div_chat").fadeIn(1000, function () {
            $(".all_div_chat").removeClass("onoe");
        })
    })
    $(".X_btn").click(function () {
        $(".all_div_chat").fadeOut(1000, function () {
            $(".all_div_chat").addClass("onoe");
            $(".info_img").removeClass("onoe");
        })

    })
    //以下為webSocket
    var MyPoint = "/Websocketchat/客服人員";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("just_word");
	var messagesArea = document.getElementById("info_chat_div");
	var self = '客服人員';
	var webSocket;
	
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			console.log(event);
			console.log("Connect Success!");
		};

		webSocket.onmessage = function(event) {
			//從webSocket中取到值
			var jsonObj = JSON.parse(event.data);
			//判斷如果取道的是open(連線)
			 if ("history" === jsonObj.type) {
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
				//如果發送過來的訊息發送者是自己
				if(jsonObj.sender==="客服人員"){
					jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
				}
				//如果發送過來的訊息並不是現在正在聊天的老友
				else if(jsonObj.sender===$("#friend").val()){
					jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
				}
				//都不是的狀況下(ex:有在好友列表,或沒在好友列表)
				else{
					let formSender=jsonObj.sender;
					let lis=document.getElementsByClassName("it_is_compy");
					for(let count=0;count<lis.length;count++){
						if($(lis[count])[0].firstChild.textContent===formSender){
							iWantCount(formSender);
							return;
						}
					}
					$(".chat_all_compyss").prepend(
							'<li class="it_is_compy" value=' + formSender + '>'
							+formSender
							+'<div class="reminder_div" style="display:none"></div>'
							+'</li>');
					addListener();
					iWantCount(formSender);
				}
			}else if("allCompy"===jsonObj.type){
				refreshFriendList(jsonObj)
			}else if("count"===jsonObj.type){
				let divs=jsonObj.div;
				let lis=document.getElementsByClassName("it_is_compy");
				for(let count=0;count<lis.length;count++){
					if($(lis[count])[0].firstChild.textContent===jsonObj.receiver){
						addRemind(lis[count],jsonObj.count);
						return;
					}
				}
			}
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = $("#friend").val();
		var message = inputMessage.value.trim();
		console.log(friend)
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
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.compies;
		var chat_all_compyss=document.getElementsByClassName("chat_all_compyss")[0];
		chat_all_compyss.innerHTML = '';
		for (let i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }
			chat_all_compyss.innerHTML +='<li class="it_is_compy" value="' + friends[i] + '">'
										+friends[i]
										+'<div class="reminder_div" style="display:none"></div>'
										+'</li>';
		}
		addListener();
		for(let i = 0; i < friends.length; i++){
			iWantCount(friends[i]);
		}
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		$(".it_is_compy").click(function(e){
            var friend=e.currentTarget.childNodes[0].nodeValue;
            updateFriendName(friend);
            $("#friend").val(friend);
            var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : ""
				};
            $(this).find(".reminder_div").attr("style","display:none");
            webSocket.send(JSON.stringify(jsonObj));
            removeCount(friend);
        })
	}
	// 我要計數器拉
	function iWantCount(formSender){
		var jsonObj={
				"type" : "getMeDiv",
				"sender" : "",
				"receiver" : formSender,
				"message" : ""
		};
		 webSocket.send(JSON.stringify(jsonObj));
	}
	
	//我要加計數器搂
	function addRemind(thisli,count){
		if(count==null){
			return;
		}else{
			$(thisli).find(".reminder_div").attr("style","display:inline-block");
			$(thisli).find(".reminder_div").text(count);
			$(thisli).closest("ul").prepend(thisli);
		}
	}
	
	//我要刪計數器拉
	function removeCount(friend){
		var jsonObj={
				"type" : "deleteCount",
				"sender" : "",
				"receiver" : friend,
				"message" : ""
		};
		 webSocket.send(JSON.stringify(jsonObj));
	}
	
	function disconnect() {
		webSocket.close();
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
  </script>
</body>
</html>