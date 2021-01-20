package com.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.websocketchat.jedis.JedisHandleMessage;
import com.websocketchat.model.ChatMessage;
import com.websocketchat.model.RemindUse;
import com.websocketchat.model.State;
import com.websocketchat.model.StateCompyUse;
import com.websocketchat.model.UserGetCompys;

@ServerEndpoint("/Websocketchat/{userName}")
public class Websocketchat {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		//將所有在線使用者放進MAP中(k為使用者名稱,v為使用者的連線session)
		sessionsMap.put(userName, userSession);
		
		// 取目前在線連線所有使用
		Set<String> userNames = sessionsMap.keySet();
		
		if("客服人員".equals(userName)) {
			// 將所有連線及自己、上限狀態放入一個VO
			State stateMessage = new State("open", userName, userNames);

			// 將VO資料轉成JSON
			String stateMessageJson = gson.toJson(stateMessage);

			// 取目前所有連線者的連線值
			Collection<Session> sessions = sessionsMap.values();
			
			// 在一個個發送給目前有在線的人發送其他人在線狀態(包含自己，前端濾掉)
			for (Session session : sessions) {
				if (session.isOpen()) {
					session.getAsyncRemote().sendText(stateMessageJson);
				}
			}
			if("客服人員".equals(userName)) {
				List<String> compys=JedisHandleMessage.getAllCompies(userName);
				UserGetCompys allCompys=new UserGetCompys("allCompy", compys);
				String allcompy=gson.toJson(allCompys);
				
				userSession.getAsyncRemote().sendText(allcompy);
			}
		}else {
			StateCompyUse stateCompyUse=new StateCompyUse("open", userName, "客服人員");
			String stateCompyUseJson=gson.toJson(stateCompyUse);
			userSession.getAsyncRemote().sendText(stateCompyUseJson);
		}
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		// 前端傳送過來的訊息,由JSON轉為VO
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);

		// 發送者
		String sender = chatMessage.getSender();

		// 接收者
		String receiver = chatMessage.getReceiver();

		if ("history".equals(chatMessage.getType())) {
			// 由JedisHandleMessage取得歷史訊息
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			// 將歷史訊息轉為JSON
			String historyMsg = gson.toJson(historyData);

			// 將歷史訊息存進VO
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			
			if (userSession != null && userSession.isOpen()) {
				// 如果當下使用者的連線存在,且是開啟狀態
				// 將歷史訊息傳出去
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("歷史訊息是~~ history = " + gson.toJson(cmHistory));
				return;
			}
		}
		
		if("getMeDiv".equals(chatMessage.getType())) {
			String count=JedisHandleMessage.getDiv(receiver);
			RemindUse remindUse=new RemindUse("count", receiver, count);
			String div=gson.toJson(remindUse);
			try {
				userSession.getBasicRemote().sendText(div);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if("deleteCount".equals(chatMessage.getType())) {
			new JedisHandleMessage().deleteCount(receiver);
			return;
		}
		

		// 取得接收者訊息
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			// 如果對方存在,且有連線
			// 將訊息傳送,並且存在redis
			receiverSession.getAsyncRemote().sendText(message);
		}
		
		JedisHandleMessage.saveChatMessage(sender, receiver, message);
		userSession.getAsyncRemote().sendText(message);
		System.out.println("訊息是~~ Message received: " + message);
	}
		

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("關起來了唷 ~~ session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}