package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BuySeedsTomatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_seeds_tomato);
    }

    public void onClick_goto_buy_tomato_seeds_details(View view)
    {
        Intent lgn = new Intent(BuySeedsTomatoActivity.this,farmer_product_details.class);
        startActivity(lgn);
    }
}