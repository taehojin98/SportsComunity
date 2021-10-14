package com.sport.jth.model;

public class FacebookProfile {

	public String name;
	public String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public FacebookProfile(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public FacebookProfile() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FacebookProfile [name=" + name + ", id=" + id + "]";
	}
	
	
}
