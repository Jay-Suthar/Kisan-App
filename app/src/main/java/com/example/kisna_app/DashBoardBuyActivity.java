package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashBoardBuyActivity extends AppCompatActivity {

    private ConstraintLayout seed,pesticides,manure,fertilizers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_buy);
        seed = (ConstraintLayout) findViewById(R.id.farmer_seeds);
        pesticides = (ConstraintLayout) findViewById(R.id.farmer_pesticides);
        manure = (ConstraintLayout) findViewById(R.id.farmer_manure);
        fertilizers = (ConstraintLayout) findViewById(R.id.farmer_fertilizers);
        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardBuyActivity.this,BuySeedsActivity.class);
                intent.putExtra("Category","Seed");
                startActivity(intent);
            }
        });
        pesticides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardBuyActivity.this,BuySeedsActivity.class);
                intent.putExtra("Category","Pesticides");
                startActivity(intent);
            }
        });
        manure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardBuyActivity.this,BuySeedsActivity.class);
                intent.putExtra("Category","Manure");
                startActivity(intent);
            }
        });
        fertilizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardBuyActivity.this,BuySeedsActivity.class);
                intent.putExtra("Category","Fertilizers");
                startActivity(intent);
            }
        });
    }

}