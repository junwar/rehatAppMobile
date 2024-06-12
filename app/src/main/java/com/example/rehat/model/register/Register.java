package com.example.rehat.model.register;

import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("data")
	private registerData registerData;

	public registerData getData(){

		return registerData;
	}
}