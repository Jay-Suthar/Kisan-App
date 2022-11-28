package com.example.kisna_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


public class CropAddAdapter extends FirebaseRecyclerAdapter<CropModel, CropAddAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.

     */
    public CropAddAdapter(@NonNull FirebaseRecyclerOptions<CropModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CropModel model) {
        holder.name.setText(model.getCrop_name());
        holder.sub_type.setText(model.getSub_type());
        holder.price.setText(model.getPrice());
        holder.quantity.setText(model.getQuantity());

        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_crop_popup))
                        .setExpanded(true,1500) //size
                        .create();

                // dialogPlus.show();

                View view1=dialogPlus.getHolderView();
                EditText sub_type=view1.findViewById(R.id.update_crop_sub_type);
                EditText price=view1.findViewById(R.id.update_crop_price);
                EditText quantity=view1.findViewById(R.id.update_crop_quantity);
                Button btn_update=view1.findViewById(R.id.update_crop);


                sub_type.setText(model.getSub_type());
                price.setText(model.getPrice());
                quantity.setText(model.getQuantity());
                dialogPlus.show();
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String,Object> map=new HashMap< >();
                        map.put("sub_type",sub_type.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("quantity",quantity.getText().toString());



                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String id = user.getUid();
                        FirebaseDatabase.getInstance().getReference().child("All_Crops_Veg_Fruits").child(getRef(position).getKey()).updateChildren(map);
                        FirebaseDatabase.getInstance().getReference().child("User")
                                .child(id).child("Crops").child(getRef(position).getKey()).updateChildren(map)
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
                                .child(id).child("Crops").child(getRef(position).getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("All_Crops_Veg_Fruits").child(getRef(position).getKey()).removeValue();
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



        //binding popup


        //for delete

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_design,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,sub_type,price,quantity;
        Button edit_btn,delete_btn;
//taking buttons


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_crop);
            sub_type=itemView.findViewById(R.id.subtype_crop);
            price=itemView.findViewById(R.id.price_crop);
            quantity=itemView.findViewById(R.id.quantity_crop);
            edit_btn=itemView.findViewById(R.id.edit_seed);
            delete_btn=itemView.findViewById(R.id.delete_crop);
        }
    }
}