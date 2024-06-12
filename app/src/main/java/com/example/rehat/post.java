package com.example.rehat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rehat.api.ApiClient;
import com.example.rehat.api.ApiInterface;
import com.example.rehat.model.upload.Upload;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class post extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    TextInputEditText inputJudul, inputDeskripsi;
    ImageView gambarPreview;
    ImageButton kirimButton, unggahButton;
    ApiInterface apiInterface;
    Uri gambarUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        inputJudul = findViewById(R.id.username);
        inputDeskripsi = findViewById(R.id.isideskripsi);
        gambarPreview = findViewById(R.id.gambarPreview);
        kirimButton = findViewById(R.id.kirim);
        unggahButton = findViewById(R.id.unggah);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        unggahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        kirimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = inputJudul.getText().toString();
                String deskripsi = inputDeskripsi.getText().toString();

                if (!judul.isEmpty() && !deskripsi.isEmpty() && gambarUri != null) {
                    uploadPost(judul, deskripsi, gambarUri);
                } else {
                    Toast.makeText(post.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            gambarUri = data.getData();
            Glide.with(this).load(gambarUri).into(gambarPreview);
        }
    }

    private void uploadPost(String judul, String deskripsi, Uri gambarUri) {
        File file = new File(FileUtils.getPath(this, gambarUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(gambarUri)), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("gambar", file.getName(), requestFile);
        RequestBody judulBody = RequestBody.create(MultipartBody.FORM, judul);
        RequestBody deskripsiBody = RequestBody.create(MultipartBody.FORM, deskripsi);

        Call<Upload> call = apiInterface.uploadPost(judulBody, deskripsiBody, body);
        call.enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(post.this, "Post uploaded successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(post.this, beranda.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(post.this, "Upload failed", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                Toast.makeText(post.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("UploadError", "Error: " + t.getMessage()); // Tambahkan log untuk melihat pesan error
            }

        });
    }
}
