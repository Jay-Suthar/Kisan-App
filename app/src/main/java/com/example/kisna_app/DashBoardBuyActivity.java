package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashBoardBuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_buy);
    }

    public void onClick_goto_buy_seeds(View view)
    {
        Intent lgn = new Intent(DashBoardBuyActivity.this,BuySeedsActivity.class);
        startActivity(lgn);
    }
}