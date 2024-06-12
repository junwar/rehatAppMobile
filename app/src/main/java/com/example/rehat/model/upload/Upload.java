package com.example.rehat.model.upload;

import com.google.gson.annotations.SerializedName;

public class Upload {

	@SerializedName("data")
	private Data data;

	@SerializedName("meta")
	private Meta meta;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}

