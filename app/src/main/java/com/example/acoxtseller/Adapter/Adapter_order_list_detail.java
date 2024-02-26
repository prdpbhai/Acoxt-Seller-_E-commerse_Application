package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_order_list_detail extends RecyclerView.Adapter<Adapter_order_list_detail.Viewholder>{

    List<Show_order_detail_management_customer_pojo.Datum>show_order_detail_management_customer_pojo;
    Context context;
    int setIndex;

    public Adapter_order_list_detail(List<Show_order_detail_management_customer_pojo.Datum> show_order_detail_management_customer_pojo, Context context, Integer setIndex) {
        this.show_order_detail_management_customer_pojo = show_order_detail_management_customer_pojo;
        this.setIndex=setIndex;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_order_list_detail.Viewholder(LayoutInflater.from(context).inflate(R.layout.item_listview_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
//        holder.item_name.setText((CharSequence) show_order_detail_management_customer_pojo.get(position).getTitle());

        Show_order_detail_management_customer_pojo.Datum datee = show_order_detail_management_customer_pojo.get(setIndex);
        holder.item_name.setText(show_order_detail_management_customer_pojo.get(setIndex).getProduct().get(position).getTitle());

        holder.item_rate.setText(show_order_detail_management_customer_pojo.get(setIndex).getProduct().get(position).getFinalprice());
        holder.item_weight.setText(show_order_detail_management_customer_pojo.get(setIndex).getProduct().get(position).getUnit());

        String input = show_order_detail_management_customer_pojo.get(setIndex).getProduct().get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");
        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return show_order_detail_management_customer_pojo.get(setIndex).getProduct().size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView item_name,item_rate,item_weight;
        ImageView item_image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_rate=itemView.findViewById(R.id.item_rate);
            item_weight=itemView.findViewById(R.id.item_weight);
            item_image=itemView.findViewById(R.id.item_image);
        }
    }


}
