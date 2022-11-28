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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class vendor_add_seed extends AppCompatActivity {
    private EditText name,subtype,price,quantity;
    private Button update,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_seed);
        name=findViewById(R.id.add_seed_name);
        subtype=findViewById(R.id.add_seed_sub_type);
        price=findViewById(R.id.add_seed_price);
        quantity=findViewById(R.id.add_seed_quantity);
        update=findViewById(R.id.add_seed_add);
        back=findViewById(R.id.add_seed_cancel);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(vendor_add_seed.this,VendorAddActivity.class));
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        Map<String,Object> map=new HashMap<>();
        map.put("name_seed",name.getText().toString());
        map.put("sub_type_seed",subtype.getText().toString());
        map.put("price_seed",price.getText().toString());
        map.put("quantity_seed",quantity.getText().toString());
        map.put("parent_vendor",id);

        DatabaseReference df = FirebaseDatabase.getInstance().getReference().child("ALL_SEEDS_MANURE_FERTILIZERS_EQUIPMENT");
        String key = df.push().getKey();
        df.child(key).setValue(map);
        FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Item_details").child(key)
                .setValue(map)


                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(vendor_add_seed.this, "Added successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(vendor_add_seed.this, "error", Toast.LENGTH_SHORT).show();
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