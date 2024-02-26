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

import com.example.acoxtseller.Api_Pojo.Cart3_POJO;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.Fregment.Cart_Fragment;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {

    List<Cart3_POJO>cart_List;
    Context context;
    DB_Helper dbHelper;

    private boolean isButtonsEnabled = true; // Flag to control button clicks

    // Callback interface for item click events
    public interface OnItemClickListener {
        void onPlusClick(String productId, String price);
        void onMinusClick(String productId, String price);
    }

    private OnItemClickListener listener;


    public CartAdapter(List<Cart3_POJO> cart_List, Context context, DB_Helper dbHelper, OnItemClickListener listener) {
        this.cart_List = cart_List;
        this.context = context;
        this.dbHelper = dbHelper;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_listview_cart,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.ProductName.setText(cart_List.get(position).getProductid());
//        holder.ProductDisc.setText(cart_List.get(position).getDiscription());
        holder.ProductPrice.setText(String.valueOf(cart_List.get(position).getPrice()));
//        holder.dic_price.setText(String.valueOf(cart_List.get(position).getDisprice()));
        holder.Num.setText(String.valueOf(cart_List.get(position).getQunitity()));
        String input = cart_List.get(position).getImg();



        String output = input.replace("[", "").replace("]", "");
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/" + output)
                .into(holder.ProductImage);

        int currentNumber = Integer.parseInt(cart_List.get(position).getQunitity());

        holder.Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlusClick(cart_List.get(position).getTitle(),String.valueOf(cart_List.get(position).getPrice()));
            }
        });


        holder.Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Cart_Fragment.replaceCartFragment();

                if (currentNumber == 0) {

                    Toast.makeText(context, "Please add first", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        listener.onMinusClick(cart_List.get(position).getTitle(),String.valueOf(cart_List.get(position).getPrice()));
                    }
                    catch (Exception e){
                        Log.d("hbdcyunduNEJZDS", ""+e);
                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return cart_List.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView ProductName,ProductDisc,ProductPrice,Num,dic_price;
        ImageView ProductImage;
        RelativeLayout Minus,Plus;

        int itemCount=0;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ProductName=itemView.findViewById(R.id.item_name);
//            ProductDisc=itemView.findViewById(R.id.cart3_disc);
            ProductPrice=itemView.findViewById(R.id.item_rate);
            ProductImage=itemView.findViewById(R.id.item_image);
            Num=itemView.findViewById(R.id.count);
            Minus=itemView.findViewById(R.id.decrease);
            Plus=itemView.findViewById(R.id.increase);
//            dic_price=itemView.findViewById(R.id.dic_price);
        }
    }


}
