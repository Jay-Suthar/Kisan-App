package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FarmerCustomerOrder extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public FarmerCustomerOrderAdapter farmerCustomerOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_customer_order);

        recyclerView2 = findViewById(R.id.rxml_12);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<FarmerMyOrderModel> options =
                new FirebaseRecyclerOptions.Builder<FarmerMyOrderModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Customer_Orders"), FarmerMyOrderModel.class)
                        .build();

        farmerCustomerOrderAdapter=new FarmerCustomerOrderAdapter(options,FarmerCustomerOrder.this);
        recyclerView2.setAdapter(farmerCustomerOrderAdapter);
    }
    protected void onStart() {
        super.onStart();
        farmerCustomerOrderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        farmerCustomerOrderAdapter.stopListening();
    }
}