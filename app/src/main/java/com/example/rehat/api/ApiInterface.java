package com.example.rehat.api;

import com.example.rehat.model.login.Login;
import com.example.rehat.model.register.Register;
import com.example.rehat.model.upload.Upload;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/user/login.php")
    Call<Login> LoginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/user/register.php")
    Call<Register> RegisterResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @Multipart
    @POST("api/post/create.php")
    Call<Upload> uploadPost(
            @Part("judul") RequestBody judul,
            @Part("deskripsi") RequestBody deskripsi,
            @Part MultipartBody.Part gambar
    );


    // Tambahkan endpoint GET untuk mengambil daftar postingan
    @GET("api/post/list.php")
    Call<List<Upload>> getPosts();
}
