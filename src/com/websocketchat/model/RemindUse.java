package com.websocketchat.model;

import java.util.List;

public class RemindUse {
	private String type;
	private String receiver;
	private String count;
	
	public RemindUse(String type, String receiver, String count) {
		super();
		this.type = type;
		this.receiver = receiver;
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	
}
