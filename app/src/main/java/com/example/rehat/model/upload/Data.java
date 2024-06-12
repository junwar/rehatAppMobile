package com.example.rehat.model.upload;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("judul")
	private String judul;

	@SerializedName("gambar")
	private String gambar;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

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