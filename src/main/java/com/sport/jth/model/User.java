package com.sport.jth.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	//PK, 자동증가
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//회원 아이디, not null, 최대 50자, 유니크
	@Column(nullable = false, unique = true)
	private String username;
	
	//회원 이메일
	private String email;
	
	//회원 비밀번호, not null, 최대 50자
	@Column(nullable = false, length = 100)
	private String password;
	
	private String role;
	
	private String oauth;
	
	//현재 시간 자동 삽입
	@CreationTimestamp
	private Timestamp dateCreated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(int id, String username, String email, String password, String role, String oauth,
			Timestamp dateCreated) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.oauth = oauth;
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + ", oauth=" + oauth + ", dateCreated=" + dateCreated + "]";
	}
	
}
