package com.example.kisna_app;

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

import java.util.HashMap;

public class VendorAddAdapter extends FirebaseRecyclerAdapter<VendorModel, VendorAddAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.

     */
    private Context context;
    public VendorAddAdapter(@NonNull FirebaseRecyclerOptions<VendorModel> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull VendorModel model) {
        holder.name.setText(model.getName_seed());
        holder.sub_type.setText(model.getSub_type_seed());
        holder.price.setText(model.getPrice_seed());
        holder.quantity.setText(model.getQuantity_seed());


        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_seed_popup))
                        .setExpanded(true,1500) //size
                        .create();

                // dialogPlus.show();
//                startActivity(new Intent(VendorAddActivity.this,))
                View view1=dialogPlus.getHolderView();
                EditText sub_type=view1.findViewById(R.id.update_seed_desc);
                EditText price=view1.findViewById(R.id.update_seed_price);
                EditText quantity=view1.findViewById(R.id.update_seed_quantity);
                Button btn_update=view1.findViewById(R.id.update_crop);


                sub_type.setText(model.getSub_type_seed());
                price.setText(model.getPrice_seed());
                quantity.setText(model.getQuantity_seed());
                dialogPlus.show();
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String,Object> map=new HashMap< >();
                        map.put("sub_type_seed",sub_type.getText().toString());
                        map.put("price_seed",price.getText().toString());
                        map.put("quantity_seed",quantity.getText().toString());



                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String id = user.getUid();
                        String cur_user = getRef(position).getKey();
                        FirebaseDatabase.getInstance().getReference().child("ALL_SEEDS_MANURE_FERTILIZERS_EQUIPMENT").child(getRef(position).getKey()).updateChildren(map);
                        FirebaseDatabase.getInstance().getReference().child("User")
                                .child(id).child("Item_details").child(cur_user).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Updated successfully", Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();//to remove popup
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });

            }
        });

        //for delete
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted items can't be restored");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("User")
                                .child(id).child("Item_details").child(getRef(position).getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("ALL_SEEDS_MANURE_FERTILIZERS_EQUIPMENT").child(getRef(position).getKey()).removeValue();
                        //deleting using s1,s2,s3,... as they are unique
                    }
                });
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();//to show dialogbox
            }
        });




        //


        //for delete

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_vendor_item_design,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,sub_type,price,quantity;
//taking buttons
        Button edit_btn,delete_btn;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_seed);
            sub_type=itemView.findViewById(R.id.subtype_seed);
            price=itemView.findViewById(R.id.price_seed);
            quantity=itemView.findViewById(R.id.quantity_buy);
            edit_btn=itemView.findViewById(R.id.edit_seed);
            delete_btn=itemView.findViewById(R.id.delete_seed);
        }
    }
}