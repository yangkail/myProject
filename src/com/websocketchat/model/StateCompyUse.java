package com.websocketchat.model;

/*
 * 用戶(業者)對客服人員專用
 */
public class StateCompyUse {
	private String type;
	private String compy;
	private String user;
	
	public StateCompyUse(String type, String compy, String user) {
		super();
		this.type = type;
		this.compy = compy;
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompy() {
		return compy;
	}

	public void setCompy(String compy) {
		this.compy = compy;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
