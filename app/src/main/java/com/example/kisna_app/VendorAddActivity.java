package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class VendorAddActivity extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public VendorAddAdapter vendorAddAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add);
        recyclerView2 = findViewById(R.id.Rvxml2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<VendorModel> options =
                new FirebaseRecyclerOptions.Builder<VendorModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Item_details"), VendorModel.class)
                        .build();

        vendorAddAdapter=new VendorAddAdapter(options,VendorAddActivity.this);
        recyclerView2.setAdapter(vendorAddAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vendorAddAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vendorAddAdapter.stopListening();
    }
}


