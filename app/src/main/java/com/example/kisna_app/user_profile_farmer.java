package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class user_profile_farmer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_farmer);
    }
    public void onClick_goto_update_profile(View view)
    {
        Intent lgn2 = new Intent(user_profile_farmer.this,update_user_profile_farmer.class);
        startActivity(lgn2);
    }

    public void onClick_goto_update_password(View view)
    {
        Intent lgn2 = new Intent(user_profile_farmer.this,update_password_farmer.class);
        startActivity(lgn2);
    }
}