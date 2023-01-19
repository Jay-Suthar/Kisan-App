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

        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class User_LoginActivity extends AppCompatActivity {

    private EditText email,pass;
    private Button login;
    private FirebaseAuth auth;
    private DatabaseReference df;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getSupportActionBar().hide();
        df= FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        login = findViewById(R.id.register_btn);
        email = findViewById(R.id.Email_Login);
        pass = findViewById(R.id.pass_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_login = email.getText().toString();
                String pass_login = pass.getText().toString();

                if(TextUtils.isEmpty(email_login) || TextUtils.isEmpty(pass_login))
                {
                    Toast.makeText(User_LoginActivity.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(email_login).matches())
                {
                    Toast.makeText(User_LoginActivity.this, "Write valid email address", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    login_user(email_login,pass_login);
                }
            }
        });
    }

    public void login_user(String email_login, String pass_login) {
        auth.signInWithEmailAndPassword(email_login,pass_login).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();
                df.child("User").child(id).child("role").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        role = snapshot.getValue(String.class);
                        if(role.equals("farmer"))
                        {
                            startActivity(new Intent(User_LoginActivity.this,Navigation_Activity.class));
                            finish();
                        }
                        else if(role.equals("customer"))
                        {
                            startActivity(new Intent(User_LoginActivity.this,Navigation_Activity_2.class));
                            finish();
                        }
                        else if(role.equals("vendor"))
                        {
                            startActivity(new Intent(User_LoginActivity.this,Navigation_Activity_3.class));
                            finish();
                        }
                        Toast.makeText(User_LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void onClick_goto_register(View view)
    {
        Intent lgn = new Intent(User_LoginActivity.this,RoleActivity.class);
        startActivity(lgn);
    }
}