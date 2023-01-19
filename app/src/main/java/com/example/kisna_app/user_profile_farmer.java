package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class user_profile_farmer extends AppCompatActivity {

    private TextView name,aadhar,role,state,district,address,phone,email;
    public DatabaseReference databaseReference;
    public StorageReference storageReference;
    public ImageView profilePicture;
    public TextView uploadImageWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_farmer);
        getSupportActionBar().hide();
        name = findViewById(R.id.user_profile_name);
        aadhar = findViewById(R.id.user_profile_aadhar_number);
        role = findViewById(R.id.user_profile_role);
        state = findViewById(R.id.user_profile_State);
        district = findViewById(R.id.user_profile_District);
        address = findViewById(R.id.user_profile_Address);
        phone = findViewById(R.id.user_profile_Mobile_Number);
        email = findViewById(R.id.user_profile_Email);
        uploadImageWarning = findViewById(R.id.uploadImageWarning);

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
//                Log.d("tum",role.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Nothing to do here
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
                // Nothing to do here
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        profilePicture = (ImageView) findViewById(R.id.user_profile_user_img);

        Handler scheduler = new Handler();
        scheduler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayProfilePhoto(role.getText().toString(),id);
            }
        }, 100);


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

    public void displayProfilePhoto(String user_role,String user_id)
    {
        String path = "users"+"/"+ user_role+"/"+user_id+"/"+"profile_picture.jpg";
        Log.d("pth",role.getText().toString());
        StorageReference profilePictureReference = storageReference.child(path);
        profilePictureReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
//                profilePicture.setImageURI(uri);
                Picasso.get().load(uri).into(profilePicture);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("pth",path);
                profilePicture.setImageResource(R.drawable.not_found);
                uploadImageWarning.setText("Please Upload Your Image");
            }
        });
    }
}



//package com.example.kisna_app;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;
//
//public class user_profile_farmer extends AppCompatActivity {
//
//    private TextView name,aadhar,role,state,district,address,phone,email;
//    public DatabaseReference databaseReference;
//    public StorageReference storageReference;
//    public ImageView profilePicture;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile_farmer);
//        name = findViewById(R.id.user_profile_name);
//        aadhar = findViewById(R.id.user_profile_aadhar_number);
//        role = findViewById(R.id.user_profile_role);
//        state = findViewById(R.id.user_profile_State);
//        district = findViewById(R.id.user_profile_District);
//        address = findViewById(R.id.user_profile_Address);
//        phone = findViewById(R.id.user_profile_Mobile_Number);
//        email = findViewById(R.id.user_profile_Email);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String id = user.getUid();
//        DatabaseReference df = FirebaseDatabase.getInstance().getReference();
//
//
//        df.child("User").child(id).child("User_Details").child("name").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String nm = snapshot.getValue(String.class);
//                name.setText(nm);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        df.child("User").child(id).child("role").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String rl = snapshot.getValue(String.class);
//                role.setText(rl);
////                Log.d("tum",role.getText().toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        df.child("User").child(id).child("User_Details").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                UserDetails ud = snapshot.getValue(UserDetails.class);
//                if(ud!=null)
//                {
//                    aadhar.setText(ud.getAdhar_no());
//                    state.setText(ud.getState());
//                    district.setText(ud.getDistrict());
//                    address.setText(ud.getAddress());
//                    phone.setText(ud.getPhone());
//                    email.setText(ud.getEmail());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // TODO
//            }
//        });
//
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        storageReference = FirebaseStorage.getInstance().getReference();
//        profilePicture = (ImageView) findViewById(R.id.user_profile_user_img);
//
//        Handler scheduler = new Handler();
//        scheduler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                displayProfilePhoto(role.getText().toString(),id);
//            }
//        }, 100);
//
//
//    }
//    public void onClick_goto_update_profile(View view)
//    {
//        Intent lgn2 = new Intent(user_profile_farmer.this,update_user_profile_farmer.class);
//        startActivity(lgn2);
//        finish();
//    }
//
//    public void onClick_goto_update_password(View view)
//    {
//        Intent lgn2 = new Intent(user_profile_farmer.this,update_password_farmer.class);
//        startActivity(lgn2);
//        finish();
//    }
//
//    public void displayProfilePhoto(String user_role,String user_id)
//    {
//        String path = "users"+"/"+ user_role+"/"+user_id+"/"+"profile_picture.jpg";
//        Log.d("pth",role.getText().toString());
//        StorageReference profilePictureReference = storageReference.child(path);
//        profilePictureReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
////                profilePicture.setImageURI(uri);
//                Picasso.get().load(uri).into(profilePicture);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("pth",path);
//                profilePicture.setImageResource(R.drawable.not_found);
//            }
//        });
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////package com.example.kisna_app;
////
////        import androidx.annotation.NonNull;
////        import androidx.appcompat.app.AppCompatActivity;
////
////        import android.content.Intent;
////        import android.net.Uri;
////        import android.os.Bundle;
////        import android.util.Log;
////        import android.view.View;
////        import android.widget.ImageView;
////        import android.widget.TextView;
////        import android.widget.Toast;
////
////        import com.google.android.gms.tasks.OnFailureListener;
////        import com.google.android.gms.tasks.OnSuccessListener;
////        import com.google.firebase.auth.FirebaseAuth;
////        import com.google.firebase.auth.FirebaseUser;
////        import com.google.firebase.database.DataSnapshot;
////        import com.google.firebase.database.DatabaseError;
////        import com.google.firebase.database.DatabaseReference;
////        import com.google.firebase.database.FirebaseDatabase;
////        import com.google.firebase.database.ValueEventListener;
////        import com.google.firebase.storage.FirebaseStorage;
////        import com.google.firebase.storage.StorageReference;
////        import com.squareup.picasso.Picasso;
////
////public class user_profile_farmer extends AppCompatActivity {
////
////    private TextView name,aadhar,role,state,district,address,phone,email;
////    public DatabaseReference databaseReference;
////    public StorageReference storageReference;
////    public ImageView profilePicture;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_user_profile_farmer);
////        name = findViewById(R.id.user_profile_name);
////        aadhar = findViewById(R.id.user_profile_aadhar_number);
////        role = findViewById(R.id.user_profile_role);
////        state = findViewById(R.id.user_profile_State);
////        district = findViewById(R.id.user_profile_District);
////        address = findViewById(R.id.user_profile_Address);
////        phone = findViewById(R.id.user_profile_Mobile_Number);
////        email = findViewById(R.id.user_profile_Email);
////
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        String id = user.getUid();
////        DatabaseReference df = FirebaseDatabase.getInstance().getReference();
////
////
////        df.child("User").child(id).child("User_Details").child("name").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String nm = snapshot.getValue(String.class);
////                name.setText(nm);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////        df.child("User").child(id).child("role").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String rl = snapshot.getValue(String.class);
////                role.setText(rl);
////                Log.d("tum",role.getText().toString());
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////        df.child("User").child(id).child("User_Details").addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                UserDetails ud = snapshot.getValue(UserDetails.class);
////                if(ud!=null)
////                {
////                    aadhar.setText(ud.getAdhar_no());
////                    state.setText(ud.getState());
////                    district.setText(ud.getDistrict());
////                    address.setText(ud.getAddress());
////                    phone.setText(ud.getPhone());
////                    email.setText(ud.getEmail());
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////
////        databaseReference = FirebaseDatabase.getInstance().getReference();
////        storageReference = FirebaseStorage.getInstance().getReference();
////        profilePicture = (ImageView) findViewById(R.id.user_profile_user_img);
////
////
////        String path = "users"+"/"+ role.getText().toString()+"/"+id+"/"+"profile_picture.jpg";
////        Log.d("pth",role.getText().toString());
////        StorageReference profilePictureReference = storageReference.child(path);
////        profilePictureReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////            @Override
////            public void onSuccess(Uri uri) {
//////                profilePicture.setImageURI(uri);
////                Picasso.get().load(uri).into(profilePicture);
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                Log.d("pth",path);
////                profilePicture.setImageResource(R.drawable.not_found);
////            }
////        });
////
////    }
////    public void onClick_goto_update_profile(View view)
////    {
////        Intent lgn2 = new Intent(user_profile_farmer.this,update_user_profile_farmer.class);
////        startActivity(lgn2);
////        finish();
////    }
////
////    public void onClick_goto_update_password(View view)
////    {
////        Intent lgn2 = new Intent(user_profile_farmer.this,update_password_farmer.class);
////        startActivity(lgn2);
////        finish();
////    }
////}