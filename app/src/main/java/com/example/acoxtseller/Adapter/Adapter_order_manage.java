package com.example.acoxtseller.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.Fixed_seller_order_manage;
import com.example.acoxtseller.Model.Model_order_manage;
import com.example.acoxtseller.Order_Detail_Activity;
import com.example.acoxtseller.Order_manage;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_order_manage extends RecyclerView.Adapter<Adapter_order_manage.ViewHolder> {


    List<Show_order_detail_management_customer_pojo.Datum>model_order_manages;
    Context context;

    public Adapter_order_manage(List<Show_order_detail_management_customer_pojo.Datum> model_order_manages, Context context) {
        this.model_order_manages = model_order_manages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_order_manage.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_oreder_manage,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.order_id.setText(model_order_manages.get(position).getOrder_id());
        holder.order_id.setText(model_order_manages.get(position).getOrderId());
        holder.date_time.setText(model_order_manages.get(position).getDateTime());
        holder.status.setText(model_order_manages.get(position).getStatus());
        String id= model_order_manages.get(position).getId();


        holder.carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (model_order_manages.get(position).getSellerRole().equals("fixed_seller")){
                    Intent intent=new Intent(context, Fixed_seller_order_manage.class);
                    intent.putExtra("orderrid",id);
                    context.startActivity(intent);
                    Log.d("jdhciuhudjnckldjoih", ""+id);
//                    Toast.makeText(context, "Order Packed", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(context,Order_Detail_Activity.class);
                    intent.putExtra("prameter",String.valueOf(position));
                    context.startActivity(intent);
                    SharedPreferences pref= context.getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    String idd=model_order_manages.get(position).getId();
                    editor.putString("user",idd);
//                    editor.apply();
                    Log.d("dfuiytredsssa", ""+idd);
                    String orderid=model_order_manages.get(position).getOrderId();
                    Double loca=model_order_manages.get(position).getLocation().get(0);
                    Double loca1=model_order_manages.get(position).getLocation().get(1);
                    float locaFloat = loca.floatValue();
                    float loca1Float = loca1.floatValue();
                    editor.putFloat("locationlat",locaFloat);
                    editor.putFloat("locationlon",loca1Float);
                    editor.putString("orderid",orderid);
                    editor.apply();
                    Log.d("locaghjkuy", ""+locaFloat);
                    Log.d("locadsghjkuy", ""+loca1Float);
                    Log.d("locagasdfehjkuy", ""+idd);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return model_order_manages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_id,date_time,status;
        RelativeLayout carditem;
        LinearLayout order_no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_id=itemView.findViewById(R.id.order_id);
            carditem=itemView.findViewById(R.id.carditem);
            order_no=itemView.findViewById(R.id.order_no);
            date_time=itemView.findViewById(R.id.date_time);
            status=itemView.findViewById(R.id.status);
        }
    }
}
