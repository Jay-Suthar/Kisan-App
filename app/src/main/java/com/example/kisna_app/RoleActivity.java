package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class RoleActivity extends AppCompatActivity {
    public String role = "garbage";
    public ConstraintLayout[] options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        options = new ConstraintLayout[3];
        options[0] = (ConstraintLayout) findViewById(R.id.cns_role_farmer);
        options[1] = (ConstraintLayout) findViewById(R.id.cns_role_customer);
        options[2] = (ConstraintLayout) findViewById(R.id.cns_role_vendor);

        for(int i=0 ; i<options.length ; i++){
            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetResponse();

                    ConstraintLayout cns = (ConstraintLayout) view;
                    cns.setBackgroundColor(Color.parseColor("#52B356"));

                    role = cns.getTag().toString();
                }
            });
        }
    }

    public void proceed(View v) {
        if(Objects.equals(role, "garbage")){
            String caution = "Choose your role to proceed";
            Toast toast = Toast.makeText(RoleActivity.this,caution, Toast.LENGTH_SHORT);
            toast.show();
            return ;
        }
//        Log.d("val","errorrrrrrrrrrr");
        Intent intent = new Intent(RoleActivity.this,RegisterActivity.class);
        intent.putExtra("Role",role);
        startActivity(intent);
    }

    private void resetResponse(){
        for(int i=0 ; i<options.length ; i++){
            ConstraintLayout cns = (ConstraintLayout) options[i];
            cns.setBackgroundColor(getResources().getColor(R.color.main_color));
        }
        role = "garbage";
    }

//    public void onClick_goto_Nav(View view)
//    {
//        Intent lgn = new Intent(RoleActivity.this,Navigation_Activity.class);
//        startActivity(lgn);
//    }
//
//    public void onClick_goto_customer(View view)
//    {
//        Intent lgn = new Intent(RoleActivity.this,CustomerDashboardActivity.class);
//        startActivity(lgn);
//    }
}