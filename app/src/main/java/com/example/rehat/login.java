package com.example.rehat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehat.api.ApiClient;
import com.example.rehat.api.ApiInterface;
import com.example.rehat.model.login.Login;
import com.example.rehat.model.login.loginData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    TextInputEditText namelog, passlog;
    ImageButton loginbtn;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        namelog = findViewById(R.id.namelog);
        passlog = findViewById(R.id.paslog);
        loginbtn = findViewById(R.id.loginbtn);

        sessionManager = new SessionManager(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        loginbtn.setOnClickListener(v -> {
            String uname = namelog.getText().toString();
            String pass = passlog.getText().toString();

            if (!uname.isEmpty() && !pass.isEmpty()) {
                loginUser(uname, pass);
            } else {
                Toast.makeText(login.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String username, String password) {
        Call<Login> call = apiInterface.LoginResponse(username, password);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginData loginData = response.body().getData();
                    sessionManager.createLoginSession(loginData);
                    Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, beranda.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(login.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
