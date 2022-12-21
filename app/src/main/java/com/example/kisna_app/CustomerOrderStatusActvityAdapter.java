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

public class CustomerOrderStatusActvityAdapter extends FirebaseRecyclerAdapter<FarmerMyOrderModel, CustomerOrderStatusActvityAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.

     */
    private Context context;
    public CustomerOrderStatusActvityAdapter(@NonNull FirebaseRecyclerOptions<FarmerMyOrderModel> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull FarmerMyOrderModel model) {
        holder.name.setText(model.getName_seed());
        holder.sub_type.setText(model.getSub_type_seed());
        holder.price.setText(model.getPrice_seed());
        holder.quantity.setText(model.getQuantity_seed());
        holder.status.setText(model.getOrder_status());
        String cur_user1 = getRef(position).getKey();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_order_design,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,sub_type,price,quantity,status;
        //taking buttons



        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.order_buy);
            sub_type=itemView.findViewById(R.id.order_subtype);
            price=itemView.findViewById(R.id.order_price);
            quantity=itemView.findViewById(R.id.order_quantity);
            status=itemView.findViewById(R.id.order_status);

        }
    }
}