package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Munchies_Product_show_pojo;
import com.example.acoxtseller.Model.Model_cigrate_list;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_cigrate_list extends RecyclerView.Adapter<Adapter_cigrate_list.ViewHolder>{

    List<Munchies_Product_show_pojo.SubcategoryDatum>munchies_product_show_pojos;
    Context context;
    private int selectedPosition = -1;

    private OnImageClickListener onImageClickListener;
    public Adapter_cigrate_list(List<Munchies_Product_show_pojo.SubcategoryDatum> munchies_product_show_pojos, Context context, OnImageClickListener onImageClickListener) {
        this.munchies_product_show_pojos = munchies_product_show_pojos;
        this.context = context;
        this.onImageClickListener = onImageClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_cigrate_list.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cigrate_left_side,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.item_name_cig.setText(munchies_product_show_pojos.get(position).getSubcategory());
        String input = munchies_product_show_pojos.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");
        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.item_type);

        holder.item_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = munchies_product_show_pojos.get(position).getId();
                onImageClickListener.onImageClick(itemId);
            }
        });


    }

    @Override
    public int getItemCount() {
        return munchies_product_show_pojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_name_cig;
        ImageView item_type;
        RelativeLayout relative_item_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name_cig=itemView.findViewById(R.id.item_name_cig);
            item_type=itemView.findViewById(R.id.item_type);
            relative_item_type=itemView.findViewById(R.id.relative_item_type);
        }
    }
    public interface OnImageClickListener {
         void onImageClick(String itemId);
    }
}
