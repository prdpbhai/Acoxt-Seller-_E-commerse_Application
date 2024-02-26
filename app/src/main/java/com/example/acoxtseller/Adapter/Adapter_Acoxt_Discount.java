package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Add_Product;
import com.example.acoxtseller.Api_Pojo.Acoxt_freg_offer_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_in_cart_pojo;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.Model.Model_Acoxt_discount;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Acoxt_Discount extends RecyclerView.Adapter<Adapter_Acoxt_Discount.ViewHolder>{
    List<Acoxt_freg_offer_pojo.Datum>model_acoxt_discounts;
    Context context;
    DB_Helper dbHelper;
    private int totalItemCount=0;

    public Adapter_Acoxt_Discount(List<Acoxt_freg_offer_pojo.Datum> model_acoxt_discounts, Context context) {
        this.model_acoxt_discounts = model_acoxt_discounts;
        this.context = context;
        dbHelper=new DB_Helper(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_Acoxt_Discount.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_discount,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Acoxt_freg_offer_pojo.Datum item = model_acoxt_discounts.get(position);

//        holder.home_prodname.setText((CharSequence) model_acoxt_discounts.get(position).getHome_prodname());
        holder.home_prodname.setText(model_acoxt_discounts.get(position).getTitle());
        holder.weight.setText(model_acoxt_discounts.get(position).getUnit());
//        holder.home_img.setImageResource(model_acoxt_discounts.get(position).getImg());
        holder.discount_percent.setText(String.valueOf(model_acoxt_discounts.get(position).getDiscount()));
        holder.price_dis.setText(String.valueOf(model_acoxt_discounts.get(position).getPrice()));
        holder.price.setText(String.valueOf(model_acoxt_discounts.get(position).getFinalprice()));

        String input = model_acoxt_discounts.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");
        String idd=model_acoxt_discounts.get(position).getId();
        Log.d("sdgfdgd",""+idd);
        Picasso.get().load("https://api.acoxt.fourbrick.in/"+output).into(holder.home_img);


//        holder.relative_decrease.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (holder.itemCount > 0) {
//                    holder.itemCount--;
//                    holder.count.setText(String.valueOf(holder.itemCount));
//                    if (holder.itemCount == 0){
//                        holder.linear_add.setVisibility(View.GONE);
//                        holder.add_product.setVisibility(View.VISIBLE);
//                        holder.count.setText("1");
//                    }
//                } else if (holder.itemCount == 0) {
//                    holder.linear_add.setVisibility(View.GONE);
//                    holder.add_product.setVisibility(View.VISIBLE);
//                    holder.count.setText("1");
//                    holder.itemCount++;
//                    holder.count.setText(String.valueOf(holder.itemCount));
//                }
//            }
//        });
//        holder.increase.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//
//            public void onClick(View view) {
//                holder.itemCount++;
//                holder.count.setText(String.valueOf(holder.itemCount));
//            }
//        });

        holder.relative_decrease.setOnClickListener(v -> {
            if (holder.itemCount > 0) {
                holder.itemCount--;
                totalItemCount--;
                String name = model_acoxt_discounts.get(position).getId();
                Double price;

                String priceString = model_acoxt_discounts.get(position).getPrice();
                priceString = priceString.replaceAll("[^0-9]", "");
                price=Double.parseDouble(priceString);
                holder.linear_add.setVisibility(View.VISIBLE);
                holder.add_product.setVisibility(View.GONE);
                dbHelper.decrementQuantityAndPrice(name,1,price);
                holder.count.setText(String.valueOf(holder.itemCount));
                if (holder.itemCount == 0) {
                    holder.linear_add.setVisibility(View.GONE);
                    holder.add_product.setVisibility(View.VISIBLE);
                    holder.count.setText("1");
                    Log.d("asdfghj",""+totalItemCount);



                }
            }
        });


        holder.increase.setOnClickListener(v -> {
            holder.itemCount++;
            holder.count.setText(String.valueOf(holder.itemCount));
            String name = model_acoxt_discounts.get(position).getId();
            Double price;
            String priceString = model_acoxt_discounts.get(position).getPrice();
            priceString = priceString.replaceAll("[^0-9]", "");
            price=Double.parseDouble(priceString);
            holder.linear_add.setVisibility(View.VISIBLE);
            holder.add_product.setVisibility(View.GONE);
            dbHelper.incrementQuantityAndPrice(name,1,price);
            totalItemCount++;

            Log.d("asdfghj",""+totalItemCount);


        });


        SharedPreferences pref=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
//        holder.add_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Web_Service_login.getClient().add_product_cart_pojo("Bearer " +Token,""+idd).enqueue(new Callback<Add_product_in_cart_pojo>() {
//                    @Override
//                    public void onResponse(Call<Add_product_in_cart_pojo> call, Response<Add_product_in_cart_pojo> response) {
//                        if (response.body().getCode()==200){
//                            Log.d("ffghjhgffdghgds", ""+response.body().getMsg());
////                            Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Add_product_in_cart_pojo> call, Throwable t) {
//                        Log.e("","");
//                    }
//                });
//                holder.linear_add.setVisibility(View.VISIBLE);
//                holder.add_product.setVisibility(View.GONE);
//            }
//        });

        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = model_acoxt_discounts.get(position).getId();
                String product_name = model_acoxt_discounts.get(position).getTitle();
                Double price;
                Double dis_price;
                String image = String.valueOf(item.getImg());
                String description = model_acoxt_discounts.get(position).getDescription();
                String priceString = model_acoxt_discounts.get(position).getFinalprice();
                priceString=priceString.replaceAll("[^0-9]", "");
                price=Double.parseDouble(priceString);
                String dis_priceString=model_acoxt_discounts.get(position).getPrice();
                dis_priceString=dis_priceString.replaceAll("[^0-9]", "");
                dis_price=Double.parseDouble(dis_priceString);
                holder.linear_add.setVisibility(View.VISIBLE);
                holder.add_product.setVisibility(View.GONE);

                if (dbHelper.productExists(name))
                {
                    dbHelper.incrementQuantityAndPrice(name,1,price);
                    holder.count.setText("1");

                    totalItemCount++;

//                    Toast.makeText(context, "product added increment", Toast.LENGTH_SHORT).show();
                }else {
                    holder.count.setText("1");
                    totalItemCount++;
                    dbHelper.insertData(name,product_name,price,dis_price,image,description);
//                    Toast.makeText(context, "product added to the cart", Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category_Id=model_acoxt_discounts.get(position).getId();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("addproductid",category_Id);
                editor.apply();
                Intent intent=new Intent(context, Add_Product.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model_acoxt_discounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView home_prodname,discount_percent,weight,price_dis,price,add_product,count;
        ImageView home_img;
        int itemCount = 0;
        RelativeLayout increase,relative_decrease;
        LinearLayout linear_add;
        CardView carditem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_prodname=itemView.findViewById(R.id.home_prodname);
            discount_percent=itemView.findViewById(R.id.discount_percent);
            weight=itemView.findViewById(R.id.weight);
            price=itemView.findViewById(R.id.price);
            price_dis=itemView.findViewById(R.id.price_dis);
            home_img=itemView.findViewById(R.id.home_img);
            add_product=itemView.findViewById(R.id.add_product);
            linear_add=itemView.findViewById(R.id.linear_add);
            count=itemView.findViewById(R.id.count);
            increase=itemView.findViewById(R.id.increase);
            relative_decrease=itemView.findViewById(R.id.relative_decrease);
            carditem=itemView.findViewById(R.id.carditem);
        }
    }
}
