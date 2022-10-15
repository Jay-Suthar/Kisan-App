package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Navigation_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void onClick_goto_logout(View view)
    {
        Intent lgn3 = new Intent(Navigation_Activity.this,LoginActivity.class);
        startActivity(lgn3);
    }
    public void onClick_goto_buy_dashboard(View view)
    {
        Intent lgn = new Intent(Navigation_Activity.this,DashBoardBuyActivity.class);
        startActivity(lgn);
    }

    public void onClick_goto_sell_dashboard(View view)
    {
        Intent lgn1 = new Intent(Navigation_Activity.this,DashboardSellActivity.class);
        startActivity(lgn1);
    }

    public void onClick_goto_my_orders(View view)
    {
        Intent lgn2 = new Intent(Navigation_Activity.this,OrderStatusActivity.class);
        startActivity(lgn2);
    }
    public void onClick_goto_farmerprofile(View view)
    {
        Intent lgn2 = new Intent(Navigation_Activity.this,user_profile_farmer.class);
        startActivity(lgn2);
    }


}