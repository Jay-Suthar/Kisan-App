package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerDashboardActivity extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public BuyFromCustomerAdapter buyFromCustomerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        recyclerView2 = findViewById(R.id.rvxml10);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<CropModel> options =
                new FirebaseRecyclerOptions.Builder<CropModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Crops_Veg_Fruits"), CropModel.class)
                        .build();

        buyFromCustomerAdapter=new BuyFromCustomerAdapter(options,CustomerDashboardActivity.this);
        recyclerView2.setAdapter(buyFromCustomerAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        buyFromCustomerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        buyFromCustomerAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.seacrh);
        SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtsearch1(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtsearch1(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private  void txtsearch1(String str){
        FirebaseRecyclerOptions<CropModel> options =
                new FirebaseRecyclerOptions.Builder<CropModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Crops_Veg_Fruits").orderByChild("crop_name").startAt(str).endAt(str+"~"), CropModel.class)
                        .build();
        BuyFromCustomerAdapter mainAdapter=new BuyFromCustomerAdapter(options,CustomerDashboardActivity.this);
        mainAdapter.startListening();
        recyclerView2.setAdapter(mainAdapter);
    }
}