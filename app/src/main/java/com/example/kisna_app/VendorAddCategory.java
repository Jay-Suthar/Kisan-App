package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VendorAddCategory extends AppCompatActivity {
    private ConstraintLayout seed,pesticides,manure,fertilizers;
    private String mainCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_category);
        seed = (ConstraintLayout) findViewById(R.id.vendor_seeds);
        pesticides = (ConstraintLayout) findViewById(R.id.vendor_pesticides);
        manure = (ConstraintLayout) findViewById(R.id.vendor_manure);
        fertilizers = (ConstraintLayout) findViewById(R.id.vendor_fertilizers);

        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorAddCategory.this,VendorAddActivity.class);
                intent.putExtra("Category","Seed");
                startActivity(intent);
            }
        });
        pesticides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorAddCategory.this,VendorAddActivity.class);
                intent.putExtra("Category","Pesticides");
                startActivity(intent);
            }
        });
        manure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorAddCategory.this,VendorAddActivity.class);
                intent.putExtra("Category","Manure");
                startActivity(intent);
            }
        });
        fertilizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorAddCategory.this,VendorAddActivity.class);
                intent.putExtra("Category","Fertilizers");
                startActivity(intent);
            }
        });

    }
}