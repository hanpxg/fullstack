package com.ibm.w3.model;

public class AuthResponse {

	private String jwtToken;
	
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	private String username;
	private String usertype;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	private String header;
	public String getHeader() {
		return header;
	}	
	public void setHeader(String header, String string) {
		this.header = header;		
	}

	private Long id;

	public void setHeader(String header) {
		this.header = header;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

