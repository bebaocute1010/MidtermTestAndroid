package com.example.midtermtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermtest.Database.AppDatabase;
import com.example.midtermtest.Models.User;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private TextView linkToLogin;
    private EditText inputName;
    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialization();
        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (AppDatabase.getInstance(getContext()).userDao().findByUsername(inputUsername.getText().toString().trim()) == null) {
                        User user = new User(
                            inputName.getText().toString().trim(),
                            inputUsername.getText().toString().trim(),
                            inputPassword.getText().toString().trim()
                        );

                        AppDatabase.getInstance(getContext()).userDao().insert(user);
                        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(getContext(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Context getContext() {
        return RegisterActivity.this.getApplicationContext();
    }

    private void initialization() {
        btnRegister = findViewById(R.id.btn_register);
        linkToLogin = findViewById(R.id.link_to_login);
        inputName = findViewById(R.id.input_name);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
    }
}