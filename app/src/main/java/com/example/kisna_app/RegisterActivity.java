package com.example.kisna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,email,phone,adhar_no,state,district,address,pass,conf_pass;
    private Button register_btn;
    private FirebaseAuth auth;
    private DatabaseReference df;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        role = intent.getStringExtra("Role");
//        Toast.makeText(this, role, Toast.LENGTH_SHORT).show();
        name = findViewById(R.id.name_reg);
        email=findViewById(R.id.email_reg);
        phone=findViewById(R.id.mobile_reg);
        adhar_no=findViewById(R.id.adhar_reg);
        state=findViewById(R.id.state_reg);
        district=findViewById(R.id.district_reg);
        address=findViewById(R.id.address_reg);
        pass=findViewById(R.id.password_reg);
        conf_pass=findViewById(R.id.confirm_pass_reg);
        register_btn=findViewById(R.id.register_btn);

        df=FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_auth = name.getText().toString();
                String mail_auth = email.getText().toString();
                String phone_auth = phone.getText().toString();
                String adhar_no_auth = adhar_no.getText().toString();
                String state_auth = state.getText().toString();
                String district_auth = district.getText().toString();
                String address_auth = address.getText().toString();
                String pass_auth = pass.getText().toString();
                String conf_pass_auth = conf_pass.getText().toString();

                if(TextUtils.isEmpty(name_auth) || TextUtils.isEmpty(mail_auth) || TextUtils.isEmpty(phone_auth) ||TextUtils.isEmpty(adhar_no_auth) ||
                        TextUtils.isEmpty(state_auth) ||TextUtils.isEmpty(district_auth) ||TextUtils.isEmpty(address_auth) ||TextUtils.isEmpty(pass_auth) ||
                        TextUtils.isEmpty(conf_pass_auth))
                {
                    Toast.makeText(RegisterActivity.this, "Please write all details", Toast.LENGTH_SHORT).show();
                }
                else if(phone_auth.length()!=10)
                {
                    Toast.makeText(RegisterActivity.this, "Enter a 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(adhar_no_auth.length()!=12)
                {
                    Toast.makeText(RegisterActivity.this, "Enter a 12 digit valid aadhar number", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(mail_auth).matches())
                {
                    Toast.makeText(RegisterActivity.this, "Enter valid Email!!", Toast.LENGTH_SHORT).show();
                }
                else if(!pass_auth.equals(conf_pass_auth))
                {
                    Toast.makeText(RegisterActivity.this, "Password not matched!!", Toast.LENGTH_SHORT).show();
                }
                else{
                        register_fun(name_auth,mail_auth,phone_auth,adhar_no_auth,state_auth,district_auth,address_auth,pass_auth,conf_pass_auth,role);
                }

            }
        });

    }

//    public void onClick_register(View view)
//    {
//        Intent lgn = new Intent(RegisterActivity.this,RoleActivity.class);
//        startActivity(lgn);
//    }

    public void register_fun(String name,String email,String phone,String adhar_no,String state,String district,String address,String pass,String conf_pass,String role)
    {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null)
                    {
                        String id = user.getUid();
                        UserDetails ud = new UserDetails(name,email,phone,adhar_no,state,district,address);
                        if(role.equals("farmer"))
                        {
                            df.child("User").child(id).child("role").setValue(role);
                            df.child("User").child(id).child("User_Details").setValue(ud);
                            startActivity(new Intent(RegisterActivity.this,Navigation_Activity.class));
                            finish();
                        }

                        else if(role.equals("customer"))
                        {
                            df.child("User").child(id).child("role").setValue(role);
                            df.child("User").child(id).child("User_Details").setValue(ud);
                            startActivity(new Intent(RegisterActivity.this,Navigation_Activity_2.class));
                            finish();
                        }

                        else if(role.equals("vendor"))
                        {
                            df.child("User").child(id).child("role").setValue(role);
                            df.child("User").child(id).child("User_Details").setValue(ud);
                            startActivity(new Intent(RegisterActivity.this,Navigation_Activity_3.class));
                            finish();
                        }
                        Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                    }
                }

                else
                {
                    Toast.makeText(RegisterActivity.this, "Registration UnSuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onClick_goto_login(View view)
    {
        Intent lgn = new Intent(RegisterActivity.this,User_LoginActivity.class);
        startActivity(lgn);
    }
}