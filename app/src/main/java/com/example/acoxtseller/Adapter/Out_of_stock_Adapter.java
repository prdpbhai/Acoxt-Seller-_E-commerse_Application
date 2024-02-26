package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Inventory_show_pojo;
import com.example.acoxtseller.Api_Pojo.Low_Stock_Pojo;
import com.example.acoxtseller.Api_Pojo.Out_of_stock_pojo;
import com.example.acoxtseller.R;

import java.util.List;

public class Out_of_stock_Adapter extends RecyclerView.Adapter<Out_of_stock_Adapter.ViewHolder>{

    List<Out_of_stock_pojo.Datum> model_inventaries;
    Context context;

//    public Out_of_stock_Adapter(List<Out_of_stock_pojo.Datum> model_inventaries, Context context) {
//        this.model_inventaries = model_inventaries;
//        this.context = context;
//    }

    public Out_of_stock_Adapter(List<Low_Stock_Pojo.Datum> data, Context context) {
        this.model_inventaries = model_inventaries;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Out_of_stock_Adapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_inventary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.product_name.setText(model_inventaries.get(position).getTitle());
//        holder.category_name.setText(model_inventaries.get(position).getCategory());
        holder.stock_left.setText(String.valueOf(model_inventaries.get(position).getQuantity()));


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView product_name,category_name,stock_left;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
//            category_name=itemView.findViewById(R.id.category_name);
            stock_left=itemView.findViewById(R.id.stock_left);
        }
    }
}
