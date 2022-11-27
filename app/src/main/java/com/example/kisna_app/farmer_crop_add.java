package com.example.kisna_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class farmer_crop_add extends AppCompatActivity {
    private EditText name,subtype,price,quantity;
    private Button update,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_crop_add);
        name=findViewById(R.id.add_crop_name);
        subtype=findViewById(R.id.add_crop_sub_type);
        price=findViewById(R.id.add_crop_price);
        quantity=findViewById(R.id.add_crop_quantity);
        update=findViewById(R.id.add_crop_add);
        back=findViewById(R.id.add_crop_cancel);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(farmer_crop_add.this,DashboardSellActivity.class));
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatevalues();
                clear();
            }
        });


    }

    private void updatevalues(){
        Map<String,Object> map=new HashMap<>();
        map.put("crop_name",name.getText().toString());
        map.put("sub_type",subtype.getText().toString());
        map.put("price",price.getText().toString());
        map.put("quantity",quantity.getText().toString());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Crops").push()
                .setValue(map)


                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(farmer_crop_add.this, "added successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(farmer_crop_add.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clear(){
        name.setText("");
        subtype.setText("");
        price.setText("");
        quantity.setText("");
    }
}