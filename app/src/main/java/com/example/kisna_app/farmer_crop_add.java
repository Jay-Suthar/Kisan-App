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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class farmer_crop_add extends AppCompatActivity {
//    DatabaseReference databaseReference;
//    StorageReference storageReference;
    public String id;
    public String role;
//    public ImageView img_view_0,img_view_1,img_view_2;
//    public ImageView cropImages[];
//    public Uri cropImagesUri[];
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
                    startActivity(new Intent(farmer_crop_add.this,DashboardSellActivity.class));
                    finish();
            }
        });



//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        storageReference = FirebaseStorage.getInstance().getReference();
//
//        cropImages = new ImageView[3];
//        cropImages[0] = (ImageView) findViewById(R.id.crp_img_0);
//        cropImages[1] = (ImageView) findViewById(R.id.crp_img_1);
//        cropImages[2] = (ImageView) findViewById(R.id.crp_img_2);
//        cropImagesUri = new Uri[cropImages.length];
//
//        for(int i=0 ; i<cropImages.length ; i++){
//            cropImagesUri[i] = Uri.parse("");
//            int req = 1001+i;
//
//            cropImages[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(openGalleryIntent,req);
//                }
//            });
//        }
    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(1001<=requestCode && requestCode<(1001+cropImages.length)){
//            if(resultCode == Activity.RESULT_OK){
//                int index = requestCode-1001;
//                String selectedImageviewID = "crp_img_"+Integer.toString(index);
//                int selectedImageviewResourceID = getResources().getIdentifier(selectedImageviewID, "id", getPackageName());
//
//                ImageView selectedCropImage = (ImageView) findViewById(selectedImageviewResourceID);
//
//                Uri uri = data.getData();
//                Log.d("tr", String.valueOf(index));
//                Log.d("sd", String.valueOf(uri));
//
//
//
//
//
//                // TODO
//                // This line of code was making mistake
//                // thejust below was maing it to crash
////                cropImagesUri[index] = uri;
//
//
//
//
//
//                selectedCropImage.setImageURI(uri);
//            }
//
//        }
//    }





    private void updatevalues(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        Map<String,Object> map=new HashMap<>();
        map.put("crop_name",name.getText().toString());
        map.put("sub_type",subtype.getText().toString());
        map.put("price",price.getText().toString());
        map.put("quantity",quantity.getText().toString());
        map.put("crop_parent",id);
        DatabaseReference df = FirebaseDatabase.getInstance().getReference().child("All_Crops_Veg_Fruits");
        String key = df.push().getKey();
        df.child(key).setValue(map);
        FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Crops").child(key)
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
//
//    public boolean allImagesFilled(){
//        for(int i=0 ; i<cropImagesUri.length ; i++){
//            if(cropImagesUri[i]==Uri.parse("")){
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public void uploadCropImagesToFirebaseStorage(){
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        id = user.getUid();
//
//
//        databaseReference.child("User").child(id).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                role = snapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(farmer_crop_add.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Handler scheduler = new Handler();
//        scheduler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                temp();
//            }
//        }, 2000);
//
//    }

//    public void temp()
//    {
//        String folderPath = "users/"+ role +"/"+id+"/";
//
//        for(int i=0 ; i<cropImages.length ; i++){
//            String imageNamePrefix = "crp_img_";
//            String imageNumber = Integer.toString(i);
//            String path = folderPath + imageNamePrefix + imageNumber + ".jpg";
//            Log.d("pth",path);
//
//            StorageReference cropImageReference = storageReference.child(path);
//            cropImageReference.putFile(cropImagesUri[i]);
//
//            for(int j=0 ; j<cropImagesUri.length ; j++){
//                Log.d("tt", String.valueOf(cropImagesUri[j]));
//            }
//        }
//    }
}