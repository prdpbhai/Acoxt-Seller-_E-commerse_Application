package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Low_Stock_Pojo;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_low_stock  extends RecyclerView.Adapter<Adapter_low_stock.Viewholder> {
    List<Low_Stock_Pojo.Datum>model_inventaries;
    Context context;

    public Adapter_low_stock(List<Low_Stock_Pojo.Datum> model_inventaries, Context context) {
        this.model_inventaries = model_inventaries;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_low_stock.Viewholder(LayoutInflater.from(context).inflate(R.layout.item_inventary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.product_name.setText(model_inventaries.get(position).getTitle());
//        holder.category_name.setText(model_inventaries.get(position).getCategory());
        holder.stock_left.setText(String.valueOf(model_inventaries.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return model_inventaries.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView product_name,category_name,stock_left;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
//            category_name=itemView.findViewById(R.id.category_name);
            stock_left=itemView.findViewById(R.id.stock_left);
        }
    }
}
