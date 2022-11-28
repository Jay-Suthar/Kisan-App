package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class BuySeedsActivity extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public BuyForCustomerFarmerAdapter buyForCustomerFarmerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_seeds);
        recyclerView2 = findViewById(R.id.rvxml9);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<VendorModel> options =
                new FirebaseRecyclerOptions.Builder<VendorModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ALL_SEEDS_MANURE_FERTILIZERS_EQUIPMENT"), VendorModel.class)
                        .build();

        buyForCustomerFarmerAdapter=new BuyForCustomerFarmerAdapter(options,BuySeedsActivity.this);
        recyclerView2.setAdapter(buyForCustomerFarmerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buyForCustomerFarmerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        buyForCustomerFarmerAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.seacrh);
        SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtsearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private  void txtsearch(String str){
        FirebaseRecyclerOptions<VendorModel> options =
                new FirebaseRecyclerOptions.Builder<VendorModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ALL_SEEDS_MANURE_FERTILIZERS_EQUIPMENT").orderByChild("name_seed").startAt(str).endAt(str+"~"), VendorModel.class)
                        .build();
        BuyForCustomerFarmerAdapter mainAdapter=new BuyForCustomerFarmerAdapter(options,BuySeedsActivity.this);
        mainAdapter.startListening();
        recyclerView2.setAdapter(mainAdapter);
    }
}