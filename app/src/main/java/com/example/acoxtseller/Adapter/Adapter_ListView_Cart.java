package com.example.acoxtseller.Adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Cart3_POJO;
import com.example.acoxtseller.Api_Pojo.Cart_data_show_pojo;
import com.example.acoxtseller.Api_Pojo.Decrement_cart_item_pojo;
import com.example.acoxtseller.Api_Pojo.Increment_cart_item_pojo;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.Model.Model_ListView_Cart;
import com.example.acoxtseller.Model.Model_checkout_recyclerview;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_ListView_Cart extends RecyclerView.Adapter<Adapter_ListView_Cart.ViewHolder> {




     List<Cart_data_show_pojo.Datum>cart_data_show_pojo;
     Context context;

    public Adapter_ListView_Cart(List<Cart_data_show_pojo.Datum> cart_data_show_pojo, Context context) {
        this.cart_data_show_pojo = cart_data_show_pojo;
        this.context = context;
    }

    public Adapter_ListView_Cart(List<Cart3_POJO> itemList, Context context, DB_Helper dbHelper) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_listview_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.item_name.setText(model_checkout_recyclerviews.get(position).getItem_name());
//        holder.item_weight.setText(model_checkout_recyclerviews.get(position).getItem_weight());
//        holder.item_rate.setText(model_checkout_recyclerviews.get(position).getItem_rate());
//        holder.item_image.setImageResource(model_checkout_recyclerviews.get(position).getItem_image());

        SharedPreferences pref=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");

        holder.item_name.setText(cart_data_show_pojo.get(position).getTitle());
        holder.item_weight.setText(cart_data_show_pojo.get(position).getUnit());
        holder.item_rate.setText(cart_data_show_pojo.get(position).getFinalprice());
//        holder.count.setText(String.valueOf(cart_data_show_pojo.get(position).getQuantity()));
        String input = cart_data_show_pojo.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");
        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.item_image);
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemCount > 0) {
                    holder.itemCount--;
                    holder.count.setText(String.valueOf(holder.itemCount));
                    String id=cart_data_show_pojo.get(position).getId();
                    Web_Service_login.getClient().decrement_cart_item_pojo("Bearer " +Token,"decrement"+id).enqueue(new Callback<Decrement_cart_item_pojo>() {
                        @Override
                        public void onResponse(Call<Decrement_cart_item_pojo> call, Response<Decrement_cart_item_pojo> response) {
                            if (response.body()!=null){
//                                Toast.makeText(context, "Decrement", Toast.LENGTH_SHORT).show();
                                Log.d("fghiuytr", ""+response.body().getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Call<Decrement_cart_item_pojo> call, Throwable t) {

                        }
                    });
                }

            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemCount++;
                holder.count.setText(String.valueOf(holder.itemCount));

                String id=cart_data_show_pojo.get(position).getId();
                Web_Service_login.getClient().increment_cart_item_pojo("Bearer " +Token,"increment"+id).enqueue(new Callback<Increment_cart_item_pojo>() {
                    @Override
                    public void onResponse(Call<Increment_cart_item_pojo> call, Response<Increment_cart_item_pojo> response) {
                        if (response.body()!=null){
//                            Toast.makeText(context, "Increment", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Increment_cart_item_pojo> call, Throwable t) {

                    }
                });

            }
        });



    }

    @Override
    public int getItemCount() {
        return cart_data_show_pojo.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_weight,item_rate,item_name,count;
        ImageView item_image;
        RelativeLayout decrease,increase;
        private int itemCount=0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image=itemView.findViewById(R.id.item_image);
            item_weight=itemView.findViewById(R.id.item_weight);
            item_rate=itemView.findViewById(R.id.item_rate);
            item_name=itemView.findViewById(R.id.item_name);
            count=itemView.findViewById(R.id.count);
            increase=itemView.findViewById(R.id.increase);
            decrease=itemView.findViewById(R.id.decrease);
        }
    }


}
