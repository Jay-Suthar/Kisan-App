package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BuySeedsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_seeds);
    }

    public void onClick_goto_buy_tomato_seeds(View view)
    {
        Intent lgn = new Intent(BuySeedsActivity.this,BuySeedsTomatoActivity.class);
        startActivity(lgn);
    }
}