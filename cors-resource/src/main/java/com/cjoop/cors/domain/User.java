package com.cjoop.cors.domain;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 7134669708822934528L;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
