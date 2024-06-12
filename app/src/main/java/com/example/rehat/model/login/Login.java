package com.example.rehat.model.login;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("data")
	private loginData loginData;

	public void setData(loginData loginData){
		this.loginData = loginData;
	}

	public loginData getData(){
		return loginData;
	}
}