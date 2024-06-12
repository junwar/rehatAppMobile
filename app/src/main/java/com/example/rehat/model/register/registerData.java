package com.example.rehat.model.register;

import com.google.gson.annotations.SerializedName;

public class registerData {

	@SerializedName("password")
	private String password;

	@SerializedName("username")
	private String username;

	public String getPassword(){
		return password;
	}

	public String getUsername(){
		return username;
	}
}