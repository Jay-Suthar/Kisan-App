package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerOrderStatusActivity extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public CustomerOrderStatusActvityAdapter customerOrderStatusActvityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_status);

        recyclerView2 = findViewById(R.id.rvxml_3);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<FarmerMyOrderModel> options =
                new FirebaseRecyclerOptions.Builder<FarmerMyOrderModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(id).child("My_Orders"), FarmerMyOrderModel.class)
                        .build();

        customerOrderStatusActvityAdapter=new CustomerOrderStatusActvityAdapter(options,CustomerOrderStatusActivity.this);
        recyclerView2.setAdapter(customerOrderStatusActvityAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        customerOrderStatusActvityAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        customerOrderStatusActvityAdapter.stopListening();
    }
}