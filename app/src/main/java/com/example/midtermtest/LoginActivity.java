package com.example.midtermtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermtest.Database.AppDatabase;
import com.example.midtermtest.Models.Product;
import com.example.midtermtest.Models.User;
import com.example.midtermtest.Seeders.ProductSeeder;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView linkToRegister;
    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();
//        seeder();
        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    User user = AppDatabase.getInstance(getContext()).userDao().attemptCredential(
                            inputUsername.getText().toString().trim(),
                            inputPassword.getText().toString().trim()
                    );
                    if (user != null) {
                        Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user_id", user.getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("Login", e.getMessage());
                }
            }
        });
    }

    private void initialization() {
        btnLogin = findViewById(R.id.btn_login);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        linkToRegister = findViewById(R.id.link_to_register);
    }

    private Context getContext() {
        return LoginActivity.this.getApplicationContext();
    }

    public void seeder() {
        try {
            Log.i("Seeder", "Started");
            for (Product product: ProductSeeder.products) {
                if (AppDatabase.getInstance(getContext()).productDao().isExists(product.getName(), product.getImage(), product.getPrice()) == null) {
                    AppDatabase.getInstance(getContext()).productDao().insert(product);
                }
            }
            Log.i("Seeder", "Finished");
        } catch (Exception e) {
            Log.e("Seeder", e.getMessage());
        }
    }
}