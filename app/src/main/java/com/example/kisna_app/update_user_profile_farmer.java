package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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


public class update_user_profile_farmer extends AppCompatActivity {

    private TextView name;
    private EditText name1,aadhar,state,district,address,phone;
    private Button update,cancel;
    private DatabaseReference databaseReference;
    public StorageReference storageReference;
    public ImageView profileImage;
    public ImageView cameraIcon;
    private String Role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile_farmer);
        getSupportActionBar().hide();

        name = findViewById(R.id.update_profile_username);
        name1 = findViewById(R.id.update_profile_fullname);
        aadhar = findViewById(R.id.update_profile_adhar);
        state = findViewById(R.id.update_profile_state);
        district = findViewById(R.id.update_profile_district);
        address = findViewById(R.id.update_profile_address);
        phone = findViewById(R.id.update_profile_mobile);
        update = findViewById(R.id.update_profile_confirm_btn);
        cancel = findViewById(R.id.update_profile_cancel);

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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(update_user_profile_farmer.this,user_profile_farmer.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_name = name1.getText().toString();
                String new_aadhar = aadhar.getText().toString() ;
                String new_state = state.getText().toString();
                String new_district = district.getText().toString();
                String new_add = address.getText().toString();
                String new_phone = phone.getText().toString();
                if(TextUtils.isEmpty(new_name) || TextUtils.isEmpty(new_aadhar) || TextUtils.isEmpty(new_state) ||TextUtils.isEmpty(new_district) ||
                        TextUtils.isEmpty(new_phone) ||TextUtils.isEmpty(new_add))
                {
                    Toast.makeText(update_user_profile_farmer.this, "Please write all details", Toast.LENGTH_SHORT).show();
                }
                else if(new_phone.length()!=10)
                {
                    Toast.makeText(update_user_profile_farmer.this, "Enter a 10 digit valid mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(new_aadhar.length()!=12)
                {
                    Toast.makeText(update_user_profile_farmer.this, "Enter a 12 digit valid aadhar number", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    UserDetails user1 = new UserDetails(new_name,user.getEmail(),new_phone,new_aadhar,new_state,new_district,new_add);
                    if(user1!=null)
                    {
                        df.child("User").child(id).child("User_Details").setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(update_user_profile_farmer.this, "Details Updated Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(update_user_profile_farmer.this,user_profile_farmer.class));
                                    finish();
                                }

                                else
                                {
                                    Toast.makeText(update_user_profile_farmer.this, "Details Not Updated", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(update_user_profile_farmer.this,update_user_profile_farmer.class));
                                    finish();
                                }
                            }
                        });
                    }
                }

            };
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        profileImage = (ImageView) findViewById(R.id.profileImage);
        profileImage.setImageResource(R.drawable.ic_launcher_background);

        cameraIcon = (ImageView) findViewById(R.id.cameraIcon);
        cameraIcon.setImageResource(R.drawable.bg_main);
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,69);
            }
        });

        databaseReference.child("User").child(id).child("role").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Role = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_user_profile_farmer.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Handler scheduler = new Handler();
        scheduler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayProfilePhoto(Role,id);
            }
        }, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 69){
            if(resultCode == Activity.RESULT_OK){
                Uri imageURI = data.getData();
                profileImage.setImageURI(imageURI);

                uploadProfilePictureToFirebase(imageURI);
            }
        }
    }

    public void uploadProfilePictureToFirebase(Uri imageURI){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        databaseReference.child("User").child(id).child("role").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Role = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_user_profile_farmer.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        String path = "users/"+ Role +"/"+id+"/"+"profile_picture.jpg";
        StorageReference profilePictureReference = storageReference.child(path);
        profilePictureReference.putFile(imageURI);
    }

    public void displayProfilePhoto(String user_role,String user_id)
    {
        String path = "users"+"/"+ user_role+"/"+user_id+"/"+"profile_picture.jpg";
        StorageReference profilePictureReference = storageReference.child(path);
        profilePictureReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("pth",path);
                profileImage.setImageResource(R.drawable.not_found);
            }
        });
    }
}