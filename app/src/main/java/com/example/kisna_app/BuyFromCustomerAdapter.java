package com.example.kisna_app;

import static androidx.core.content.ContextCompat.startActivity;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.security.AccessControlContext;
import java.util.HashMap;

public class BuyFromCustomerAdapter extends FirebaseRecyclerAdapter<CropModel, BuyFromCustomerAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.

     */
    private Context context;
    public BuyFromCustomerAdapter(@NonNull FirebaseRecyclerOptions<CropModel> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CropModel model) {
        holder.name.setText(model.getCrop_name());
        holder.sub_type.setText(model.getSub_type());
        holder.price.setText(model.getPrice());
        holder.quantity.setText(model.getQuantity());
        String cur_user1 = getRef(position).getKey();
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, farmer_crop_details.class);
                intent.putExtra("key",cur_user1);
                intent.putExtra("main_key","All_Crops_Veg_Fruits");
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_design_cutomer_farmer,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,sub_type,price,quantity;
        //taking buttons
        Button detail;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_buy);
            sub_type=itemView.findViewById(R.id.subtype_buy);
            price=itemView.findViewById(R.id.price_buy);
            quantity=itemView.findViewById(R.id.quantity_buy);
            detail=itemView.findViewById(R.id.view_detail);

        }
    }
}