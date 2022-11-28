package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Navigation_Activity_2 extends AppCompatActivity {
    private ConstraintLayout logout,buy_crops;
    private TextView name;
    private ImageView prof_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation2);
        logout = findViewById(R.id.nav_logout);
        name = findViewById(R.id.nav_username);
        prof_img = findViewById(R.id.nav_profile_img);
        buy_crops=findViewById(R.id.nav_buy_crops);

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
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Navigation_Activity_2.this, "Logged Out Syccessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Navigation_Activity_2.this,User_LoginActivity.class));
                finish();
            }
        });
        buy_crops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation_Activity_2.this,CustomerDashboardActivity.class));
            }
        });
        prof_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation_Activity_2.this,user_profile_farmer.class));
            }
        });
    }


}