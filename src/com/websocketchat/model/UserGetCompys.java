package com.websocketchat.model;

import java.util.List;

public class UserGetCompys {
	private String type;
	private List<String> compies;
	
	public UserGetCompys(String type, List<String> compies) {
		super();
		this.type = type;
		this.compies = compies;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getCompies() {
		return compies;
	}

	public void setCompies(List<String> compies) {
		this.compies = compies;
	}
	
	
}
