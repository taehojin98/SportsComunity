package com.sport.jth.repository;

public class JoinReqDto {
	private String username;
	private String password;
	private String repassword;
	private String email;
	
	public JoinReqDto(String username, String password, String repassword, String email) {
		super();
		this.username = username;
		this.password = password;
		this.repassword = repassword;
		this.email = email;
	}

	public JoinReqDto() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	@Override
	public String toString() {
		return "JoinReqDto [username=" + username + ", password=" + password + ", repassword=" + repassword + ", email="
				+ email + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
