package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardSellActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public CropAddAdapter cropAddAdapter;
    public FloatingActionButton add_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_sell);
        recyclerView = findViewById(R.id.RV);
        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardSellActivity.this,farmer_crop_add.class));
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<CropModel> options =
                new FirebaseRecyclerOptions.Builder<CropModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Crops"), CropModel.class)
                        .build();

        cropAddAdapter=new CropAddAdapter(options);
        recyclerView.setAdapter(cropAddAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cropAddAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cropAddAdapter.stopListening();
    }

}


