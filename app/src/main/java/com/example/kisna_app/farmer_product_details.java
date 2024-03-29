package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class farmer_product_details extends AppCompatActivity {

    private String product_key,product_vendor,product_parent;
    private TextView pr_name,pr_price,pr_quantity,pr_sub_type;
    private Button order,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_product_details);

        pr_name = findViewById(R.id.product_name);
        pr_price = findViewById(R.id.view_prod_price);
        pr_quantity = findViewById(R.id.view_product_quantity);
        pr_sub_type = findViewById(R.id.view_product_sub_type);

        Intent intent = getIntent();
        product_key = intent.getStringExtra("key");
        product_parent = intent.getStringExtra("main_key");
//        Toast.makeText(this, product_key + product_parent, Toast.LENGTH_SHORT).show();
        order  =findViewById(R.id.farmer_product_order);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(farmer_product_details.this,Navigation_Activity.class);
                finish();
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(farmer_product_details.this,FarmerOrderActivity.class);
                intent1.putExtra("key",product_key);
                intent1.putExtra("main_key",product_parent);
                startActivity(intent1);
                finish();
            }
        });



        DatabaseReference df = FirebaseDatabase.getInstance().getReference();
        df.child(product_parent).child(product_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VendorModel ud = snapshot.getValue(VendorModel.class);
                if(ud!=null)
                {
                    pr_name.setText(ud.getName_seed());
                    pr_price.setText(ud.getPrice_seed());
                    pr_quantity.setText(ud.getQuantity_seed());
                    pr_sub_type.setText(ud.getSub_type_seed());
                    product_vendor = ud.getParent_vendor();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // TODO
            }
        });
    }
}