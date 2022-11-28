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

public class farmer_crop_details extends AppCompatActivity {
    private String product_key,product_vendor,product_parent;
    private TextView pr_name,pr_price,pr_quantity,pr_sub_type;
    private Button order,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_crop_details);

        pr_name = findViewById(R.id.detail_crop_name);
        pr_price = findViewById(R.id.detail_crop_price);
        pr_quantity = findViewById(R.id.detail_crop_quantity);
        pr_sub_type = findViewById(R.id.detail_crop_sub_type);
        order  =findViewById(R.id.detail_crop_order);
        back = findViewById(R.id.detail_crop_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(farmer_crop_details.this,Navigation_Activity_2.class));
                finish();
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(farmer_crop_details.this,Navigation_Activity_2.class);
                finish();
            }
        });



        Intent intent = getIntent();
        product_key = intent.getStringExtra("key");
        product_parent = intent.getStringExtra("main_key");
        Toast.makeText(this, product_key + product_parent, Toast.LENGTH_SHORT).show();
        DatabaseReference df = FirebaseDatabase.getInstance().getReference();


        df.child(product_parent).child(product_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CropModel ud = snapshot.getValue(CropModel.class);
                if(ud!=null)
                {
                    pr_name.setText(ud.getCrop_name());
                    pr_price.setText(ud.getPrice());
                    pr_quantity.setText(ud.getQuantity());
                    pr_sub_type.setText(ud.getSub_type());
                    product_vendor = ud.getCrop_parent();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // TODO
            }
        });
    }
}