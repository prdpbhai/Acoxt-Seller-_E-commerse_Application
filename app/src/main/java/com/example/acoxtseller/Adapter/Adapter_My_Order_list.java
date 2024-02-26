package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.MainActivity;
import com.example.acoxtseller.Model.Model_My_Order_list;
import com.example.acoxtseller.Pickup_location_Activity;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_My_Order_list extends RecyclerView.Adapter<Adapter_My_Order_list.ViewHolder>{
    Context context;
    List<Model_My_Order_list>model_my_order_lists;

    public Adapter_My_Order_list(Context context, List<Model_My_Order_list> model_my_order_lists) {
        this.context = context;
        this.model_my_order_lists = model_my_order_lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_My_Order_list.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myoreder_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_icon.setImageResource(model_my_order_lists.get(position).getItem_icon());
        holder.date.setText((CharSequence) model_my_order_lists.get(position).getDate());
        holder.time.setText((CharSequence) model_my_order_lists.get(position).getTime());
        holder.order_id.setText((CharSequence) model_my_order_lists.get(position).getOrder_id());
        holder.rate.setText((CharSequence) model_my_order_lists.get(position).getRate());

//        holder.date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,MainActivity.class);
//                Toast.makeText(context, "ergth", Toast.LENGTH_SHORT).show();
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return model_my_order_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,time,order_id,rate,inProcess;
        ImageView item_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            order_id=itemView.findViewById(R.id.order_id);
            rate=itemView.findViewById(R.id.rate);
            item_icon=itemView.findViewById(R.id.item_icon);
            inProcess=itemView.findViewById(R.id.inProcess);
        }
    }
}
