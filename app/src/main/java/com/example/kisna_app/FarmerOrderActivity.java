package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FarmerOrderActivity extends AppCompatActivity {

    private String product_key,product_vendor,product_parent;
    private TextView pr_name,pr_price,pr_quantity,pr_sub_type;
    private Button order,back;
    private String newQuant;
    private String tempQuant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_order);

        Intent intent = getIntent();
        product_key = intent.getStringExtra("key");
        product_parent = intent.getStringExtra("main_key");

        pr_name = findViewById(R.id.order_farmer_name);
        pr_price = findViewById(R.id.order_farmer_price);
        pr_quantity = findViewById(R.id.order_quantity_price);
        pr_sub_type = findViewById(R.id.order_farmer_sub_type);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        order  =findViewById(R.id.order_farmer_confirm);
        back = findViewById(R.id.order_farmer_cancel);


        DatabaseReference df = FirebaseDatabase.getInstance().getReference();
        df.child(product_parent).child(product_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VendorModel ud = snapshot.getValue(VendorModel.class);
                if(ud!=null)
                {
                    pr_name.setText(ud.getName_seed());
                    pr_price.setText(ud.getPrice_seed());
                    tempQuant = ud.getQuantity_seed();
                    pr_sub_type.setText(ud.getSub_type_seed());
                    product_vendor = ud.getParent_vendor();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // TODO
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newQuant = pr_quantity.getText().toString();
                int new1 = Integer.parseInt(newQuant);
                int old = Integer.parseInt(tempQuant);
                
                if(new1>old)
                {
                    Toast.makeText(FarmerOrderActivity.this, "not avaialable", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    String quant222 = Integer.toString(new1);
                    String quant = Integer.toString(old-new1);
                    HashMap<String,Object> map=new HashMap< >();
                    map.put("quantity_seed",quant);
                    df.child("User").child(product_vendor).child("Item_details").child(product_key).updateChildren(map);
                    String status = "Pending";
                    FarmerMyOrderModel order = new FarmerMyOrderModel(pr_name.getText().toString(),pr_sub_type.getText().toString(),pr_price.getText().toString(),quant222,product_vendor,status,id);
                    String order_key = df.child("User").child(id).child("My_Orders").push().getKey();
                    df.child("User").child(id).child("My_Orders").child(order_key).setValue(order);
                    df.child("User").child(product_vendor).child("Farmer_Orders").child(order_key).setValue(order);

                    df.child(product_parent).child(product_key).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(FarmerOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FarmerOrderActivity.this,OrderStatusActivity.class));
                            finish();
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FarmerOrderActivity.this,farmer_product_details.class));
                finish();
            }
        });
    }
}