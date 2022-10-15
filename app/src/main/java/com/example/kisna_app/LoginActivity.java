package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick_login(View view)
    {
        Intent lgn = new Intent(LoginActivity.this,RoleActivity.class);
        startActivity(lgn);
    }

    public void onClick_goto_register(View view)
    {
        Intent lgn = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(lgn);
    }
}