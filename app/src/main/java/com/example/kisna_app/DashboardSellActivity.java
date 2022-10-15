package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardSellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_sell);
    }

    public void onClick_goto_update_crop(View view)
    {
        Intent lgn2 = new Intent(DashboardSellActivity.this,FarmerCropUpdateActivity.class);
        startActivity(lgn2);
    }

    public void onClick_goto_detail_crop(View view)
    {
        Intent lgn2 = new Intent(DashboardSellActivity.this,farmer_crop_details.class);
        startActivity(lgn2);
    }

    public void onClick_goto_add_crop(View view)
    {
        Intent lgn2 = new Intent(DashboardSellActivity.this,farmer_crop_add.class);
        startActivity(lgn2);
    }
}