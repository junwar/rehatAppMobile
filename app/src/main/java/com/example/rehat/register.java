package com.example.rehat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehat.api.ApiClient;
import com.example.rehat.api.ApiInterface;
import com.example.rehat.model.register.Register;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    TextInputEditText username, password;
    ImageButton daftar;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        daftar = findViewById(R.id.daftar);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        daftar.setOnClickListener(v -> {
            String uname = username.getText().toString();
            String pass = password.getText().toString();

            if (!uname.isEmpty() && !pass.isEmpty()) {
                registerUser(uname, pass);
            } else {
                Toast.makeText(register.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(String username, String password) {
        Call<Register> call = apiInterface.RegisterResponse(username, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(register.this, login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(register.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
