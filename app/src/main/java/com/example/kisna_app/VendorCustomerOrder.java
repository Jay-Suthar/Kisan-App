package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class VendorCustomerOrder extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public VendorCustomerOrderAdapter vendorCustomerOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_customer_order);
        recyclerView2 = findViewById(R.id.cut_order_rv);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<FarmerMyOrderModel> options =
                new FirebaseRecyclerOptions.Builder<FarmerMyOrderModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Farmer_Orders"), FarmerMyOrderModel.class)
                        .build();

        vendorCustomerOrderAdapter=new VendorCustomerOrderAdapter(options,VendorCustomerOrder.this);
        recyclerView2.setAdapter(vendorCustomerOrderAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        vendorCustomerOrderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vendorCustomerOrderAdapter.stopListening();
    }
}