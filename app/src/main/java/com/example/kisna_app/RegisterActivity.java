package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick_register(View view)
    {
        Intent lgn = new Intent(RegisterActivity.this,RoleActivity.class);
        startActivity(lgn);
    }
    public void onClick_goto_login(View view)
    {
        Intent lgn = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(lgn);
    }
}