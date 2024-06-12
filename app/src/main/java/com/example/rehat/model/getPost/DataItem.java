package com.example.rehat.model.getPost;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("judul")
	private String judul;

	@SerializedName("gambar")
	private String gambar;

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}
}