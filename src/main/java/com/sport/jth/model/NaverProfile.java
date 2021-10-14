package com.sport.jth.model;

public class NaverProfile {

public String resultcode;
public String message;
public Response response;

	public class Response {
	
	public String id;
	public String email;
	public String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Response(String id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Response [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public NaverProfile(String resultcode, String message, Response response) {
		super();
		this.resultcode = resultcode;
		this.message = message;
		this.response = response;
	}

	@Override
	public String toString() {
		return "NaverProfile [resultcode=" + resultcode + ", message=" + message + ", response=" + response + "]";
	}

	public NaverProfile() {
		// TODO Auto-generated constructor stub
	}
}


