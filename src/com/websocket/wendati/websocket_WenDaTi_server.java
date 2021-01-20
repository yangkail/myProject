package com.websocket.wendati;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wendati.jedis.JedisWenDaTi;
import com.wendati.model.question;

@ServerEndpoint("/wenDaTi")
public class websocket_WenDaTi_server {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	Gson gson = new Gson();

	@OnOpen
	public void myOnOpen(Session session) throws IOException {
		connectedSessions.add(session);
		System.out.println("WebSocket opened: " + session.getId());
		if (session != null && session.isOpen()) {
			String question = JedisWenDaTi.get_one_ask();
			JedisWenDaTi.one_ask();
			session.getAsyncRemote().sendText(question);
		}

	}

	@OnMessage
	public void wenDaTi(Session userSession,String message) {
		

//		String rs_id = rest_Tablejson.get("rs_id").toString();
//		System.out.println(rs_id);
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
		
		

	}

	@OnClose
	public void myOnClose(Session userSession, CloseReason reason) {
		
//		if (userSession != null && userSession.isOpen()) {
			connectedSessions.remove(userSession);

//		}
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase()+"user remove "+ userSession.getId());
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
