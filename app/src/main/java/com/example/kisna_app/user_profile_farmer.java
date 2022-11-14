package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_profile_farmer extends AppCompatActivity {

    private TextView name,aadhar,role,state,district,address,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_farmer);
        name = findViewById(R.id.user_profile_name);
        aadhar = findViewById(R.id.user_profile_aadhar_number);
        role = findViewById(R.id.user_profile_role);
        state = findViewById(R.id.user_profile_State);
        district = findViewById(R.id.user_profile_District);
        address = findViewById(R.id.user_profile_Address);
        phone = findViewById(R.id.user_profile_Mobile_Number);
        email = findViewById(R.id.user_profile_Email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        DatabaseReference df = FirebaseDatabase.getInstance().getReference();


        df.child("User").child(id).child("User_Details").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nm = snapshot.getValue(String.class);
                name.setText(nm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        df.child("User").child(id).child("role").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rl = snapshot.getValue(String.class);
                role.setText(rl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        df.child("User").child(id).child("User_Details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserDetails ud = snapshot.getValue(UserDetails.class);
                    if(ud!=null)
                    {
                        aadhar.setText(ud.getAdhar_no());
                        state.setText(ud.getState());
                        district.setText(ud.getDistrict());
                        address.setText(ud.getAddress());
                        phone.setText(ud.getPhone());
                        email.setText(ud.getEmail());
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void onClick_goto_update_profile(View view)
    {
        Intent lgn2 = new Intent(user_profile_farmer.this,update_user_profile_farmer.class);
        startActivity(lgn2);
        finish();
    }

    public void onClick_goto_update_password(View view)
    {
        Intent lgn2 = new Intent(user_profile_farmer.this,update_password_farmer.class);
        startActivity(lgn2);
        finish();
    }
}