package com.example.rehat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehat.api.ApiClient;
import com.example.rehat.api.ApiInterface;
import com.example.rehat.model.upload.Data;
import com.example.rehat.model.upload.Upload;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class beranda extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private ApiInterface apiInterface;
    private static final String TAG = "beranda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        ImageButton btnpost = findViewById(R.id.post);
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beranda.this, post.class);
                startActivity(intent);
            }
        });

        ImageButton btnprofile = findViewById(R.id.profil);
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beranda.this, profile.class);
                startActivity(intent);
            }
        });

        fetchPosts();
    }

    private void fetchPosts() {
        Call<List<Upload>> call = apiInterface.getPosts();
        call.enqueue(new Callback<List<Upload>>() {
            @Override
            public void onResponse(Call<List<Upload>> call, Response<List<Upload>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Upload> uploads = response.body();
                    List<Data> dataList = new ArrayList<>();
                    for (Upload upload : uploads) {
                        dataList.add(upload.getData());
                    }
                    postAdapter = new PostAdapter(beranda.this, dataList);
                    recyclerView.setAdapter(postAdapter);
                } else {
                    Log.e(TAG, "Response not successful: " + response.code());
                    Toast.makeText(beranda.this, "Failed to fetch posts", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Upload>> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
                Toast.makeText(beranda.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
