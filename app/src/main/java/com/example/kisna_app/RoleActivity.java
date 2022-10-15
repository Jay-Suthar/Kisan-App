package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);
    }
    public void onClick_goto_Nav(View view)
    {
        Intent lgn = new Intent(RoleActivity.this,Navigation_Activity.class);
        startActivity(lgn);
    }
    public void onClick_goto_customer(View view)
    {
        Intent lgn = new Intent(RoleActivity.this,CustomerDashboardActivity.class);
        startActivity(lgn);
    }
}