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
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Add_Product;
import com.example.acoxtseller.Api_Pojo.Munchies_Product_show_pojo;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Cigrate_Name extends RecyclerView.Adapter<Adapter_Cigrate_Name.ViewHolder> {

    List<Munchies_Product_show_pojo.ProductDatum>munchies_product_show_pojos;
    Context context;
    DB_Helper dbHelper;
    private int totalItemCount=0;

    public Adapter_Cigrate_Name(List<Munchies_Product_show_pojo.ProductDatum> munchies_product_show_pojos, Context context) {
        this.munchies_product_show_pojos = munchies_product_show_pojos;
        this.context = context;
        dbHelper=new DB_Helper(context);
    }

    //    public Adapter_Cigrate_Name(List<Munchies_Product_show_pojo.ProductDatum> munchies_product_show_pojos, Callback<Munchies_Product_show_pojo> context) {
//        this.munchies_product_show_pojos = munchies_product_show_pojos;
//        this.context = context;
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_Cigrate_Name.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cigrate_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Munchies_Product_show_pojo.ProductDatum item = munchies_product_show_pojos.get(position);

        holder.cig_name.setText(munchies_product_show_pojos.get(position).getTitle());
        holder.weight.setText(munchies_product_show_pojos.get(position).getUnit());
        holder.price.setText(munchies_product_show_pojos.get(position).getFinalprice());
        holder.discount_price.setText(munchies_product_show_pojos.get(position).getPrice());
//        holder.product_image_munchies.setImageResource(munchies_product_show_pojos.get(position).getProductData());

        String input = munchies_product_show_pojos.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");
        String munchies_product_id=munchies_product_show_pojos.get(position).getId();



        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.product_image_munchies);

//        holder.cig_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, Add_Product.class);
//                context.startActivity(intent);
//            }
//        });
//        holder.relative_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,Add_Product.class);
//                intent.putExtra("category_id",munchies_product_show_pojos.get(position).getId());
//                context.startActivity(intent);
//            }
//        });

        holder.relative_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.itemCount > 0) {
                    holder.itemCount--;
                    holder.count.setText(String.valueOf(holder.itemCount));
                    if (holder.itemCount == 0){
                        holder.linear_add.setVisibility(View.GONE);
                        holder.relative_add.setVisibility(View.VISIBLE);
                        holder.count.setText("1");
                    }
                } else if (holder.itemCount == 0) {
                    holder.linear_add.setVisibility(View.GONE);
                    holder.relative_add.setVisibility(View.VISIBLE);
                    holder.count.setText("1");
                    holder.itemCount++;
                    holder.count.setText(String.valueOf(holder.itemCount));
                }
            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                holder.itemCount++;
                holder.count.setText(String.valueOf(holder.itemCount));
            }
        });


        SharedPreferences pref=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");

        holder.relative_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = munchies_product_show_pojos.get(position).getId();
                String product_name = munchies_product_show_pojos.get(position).getTitle();
                Double price;
                Double dis_price;
                String image = String.valueOf(item.getImg());
//                String description = munchies_product_show_pojos.get(position).getDescription();
                String priceString = munchies_product_show_pojos.get(position).getFinalprice();
                priceString=priceString.replaceAll("[^0-9]", "");
                price=Double.parseDouble(priceString);
                String dis_priceString=munchies_product_show_pojos.get(position).getPrice();
                dis_priceString=dis_priceString.replaceAll("[^0-9]", "");
                dis_price=Double.parseDouble(dis_priceString);
                holder.linear_add.setVisibility(View.VISIBLE);
                holder.relative_add.setVisibility(View.GONE);

                if (dbHelper.productExists(name))
                {
                    dbHelper.incrementQuantityAndPrice(name,1,price);
                    holder.count.setText("1");

                    totalItemCount++;

//                    Toast.makeText(context, "product added increment", Toast.LENGTH_SHORT).show();
                }else {
                    holder.count.setText("1");
                    totalItemCount++;
                    dbHelper.insertData(name,product_name,price,dis_price,image,name);
//                    Toast.makeText(context, "product added to the cart", Toast.LENGTH_SHORT).show();
                }


            }
        });

//        holder.relative_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("fghasasashh", ""+munchies_product_id);
//                Web_Service_login.getClient().add_product_cart_pojo("Bearer " +Token,""+munchies_product_id).enqueue(new Callback<Add_product_in_cart_pojo>() {
//                    @Override
//                    public void onResponse(Call<Add_product_in_cart_pojo> call, Response<Add_product_in_cart_pojo> response) {
//                        if (response.body()!=null){
//                            Log.d("ffghjhgffdghgds", ""+response.body().getMsg());
//                            Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Add_product_in_cart_pojo> call, Throwable t) {
//                        Log.e("","");
//                    }
//                });
//
//                holder.linear_add.setVisibility(View.VISIBLE);
//                holder.relative_add.setVisibility(View.GONE);
//            }
//        });
        holder.card_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category_Id=munchies_product_show_pojos.get(position).getId();
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
        return munchies_product_show_pojos.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView cig_name,relative_add,discount_price,weight,price,add_product,count;
        ImageView product_image_munchies;
        int itemCount=0;
        RelativeLayout increase,relative_decrease,card_item;
        LinearLayout linear_add;
//        RelativeLayout relative_add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cig_name=itemView.findViewById(R.id.cig_name);
            product_image_munchies=itemView.findViewById(R.id.product_image_munchies);
            relative_add=itemView.findViewById(R.id.relative_add);
            discount_price=itemView.findViewById(R.id.discount_price);
            weight=itemView.findViewById(R.id.weight);
            price=itemView.findViewById(R.id.price);
            linear_add=itemView.findViewById(R.id.linear_add);
            add_product=itemView.findViewById(R.id.add_product);
            count=itemView.findViewById(R.id.count);
            increase=itemView.findViewById(R.id.increase);
            relative_decrease=itemView.findViewById(R.id.relative_decrease);
            card_item=itemView.findViewById(R.id.card_item);
        }
    }
}
