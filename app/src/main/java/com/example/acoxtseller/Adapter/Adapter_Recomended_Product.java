package com.example.acoxtseller.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
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
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_horizental_item_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_in_cart_pojo;
import com.example.acoxtseller.Api_Pojo.Datum_Acoxt_freg_vertical;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.R;
import com.example.acoxtseller.ViewCartItem_Activity;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Recomended_Product extends RecyclerView.Adapter<Adapter_Recomended_Product.ViewHolder> {


    List<Acoxt_fregment_vertical_item_pojo.Datum> acoxt_fregment_vertical_item_pojos;
    Context context;

    DB_Helper dbHelper;
    private int totalItemCount=0;

    public Adapter_Recomended_Product(List<Acoxt_fregment_vertical_item_pojo.Datum> acoxt_fregment_vertical_item_pojos, Context context) {
        this.acoxt_fregment_vertical_item_pojos = acoxt_fregment_vertical_item_pojos;
        this.context = context;
        dbHelper=new DB_Helper(context);
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recomended_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Acoxt_fregment_vertical_item_pojo.Datum product= acoxt_fregment_vertical_item_pojos.get(position);
        holder.home_prodname.setText(acoxt_fregment_vertical_item_pojos.get(position).getTitle());
        holder.weight.setText(String.valueOf(acoxt_fregment_vertical_item_pojos.get(position).getQuantity()));
        holder.discount_price.setText(String.valueOf(acoxt_fregment_vertical_item_pojos.get(position).getFinalprice()));
        holder.price.setText(String.valueOf(acoxt_fregment_vertical_item_pojos.get(position).getPrice()));
        holder.home_prodname.setText(acoxt_fregment_vertical_item_pojos.get(position).getTitle());
        String input = acoxt_fregment_vertical_item_pojos.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");
        String idd=acoxt_fregment_vertical_item_pojos.get(position).getId();

        Log.d("sdgfdgd",""+idd);
        Picasso.get().load("https://api.acoxt.fourbrick.in/"+output).into(holder.home_img);


        SharedPreferences pref=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");

//        holder.add_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id = product.getId();
//                String name = product.getTitle();
//
//                Double price;
//                Double dis_price;
//
//                String img = String.valueOf(product.getImg());
//                String description = product.getDescription();
//
//                String priceString = acoxt_fregment_vertical_item_pojos.get(position).getFinalprice();
//                priceString = priceString.replaceAll("[^0-9]", "");
//
//                price=Double.parseDouble(priceString);
//
//                String dis_price_string= String.valueOf(acoxt_fregment_vertical_item_pojos.get(position).getQuantity());
//                dis_price_string=dis_price_string.replaceAll("[^0-9]", "");
//
//                dis_price=Double.parseDouble(dis_price_string);
//
//
//                if (dbHelper.ProductExist(name))
//                {
//                    dbHelper.incrementQuantityAndPrice(name,1,price);
//                }else {
//
//                    dbHelper.InsertData(id,name,price,dis_price,img,description);
//                }
//                Toast.makeText(context, "Product added to the cart", Toast.LENGTH_SHORT).show();
//            }
//        });

//        holder.add_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent=new Intent(context, ViewCartItem_Activity.class);
////                context.startActivity(intent);
//                holder.add_product.setVisibility(View.GONE);
//                holder.linear_add.setVisibility(View.VISIBLE);
//            }
//        });



        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = acoxt_fregment_vertical_item_pojos.get(position).getId();
                String product_name = acoxt_fregment_vertical_item_pojos.get(position).getTitle();
                Double price;
                Double dis_price;
                String image = String.valueOf(product.getImg());
                String description = acoxt_fregment_vertical_item_pojos.get(position).getDescription();
                String priceString = acoxt_fregment_vertical_item_pojos.get(position).getFinalprice();
                priceString=priceString.replaceAll("[^0-9]", "");
                price=Double.parseDouble(priceString);
                String dis_priceString=acoxt_fregment_vertical_item_pojos.get(position).getPrice();
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

        holder.relative_decrease.setOnClickListener(v -> {
            if (holder.itemCount > 0) {
                holder.itemCount--;
                totalItemCount--;
                String name = acoxt_fregment_vertical_item_pojos.get(position).getId();
                Double price;

                String priceString = acoxt_fregment_vertical_item_pojos.get(position).getPrice();
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
            String name = acoxt_fregment_vertical_item_pojos.get(position).getId();
            Double price;
            String priceString = acoxt_fregment_vertical_item_pojos.get(position).getPrice();
            priceString = priceString.replaceAll("[^0-9]", "");
            price=Double.parseDouble(priceString);
            holder.linear_add.setVisibility(View.VISIBLE);
            holder.add_product.setVisibility(View.GONE);
            dbHelper.incrementQuantityAndPrice(name,1,price);
            totalItemCount++;

            Log.d("asdfghj",""+totalItemCount);


        });


        holder.carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Add_Product.class);
//                intent.putExtra("addid",acoxt_fregment_vertical_item_pojos.get(position).getId());
                String category_Id=acoxt_fregment_vertical_item_pojos.get(position).getId();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("addproductid",category_Id);
                editor.apply();
                context.startActivity(intent);
            }
        });
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

//        holder.add_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Acoxt_fregment_vertical_item_pojo itemList=new Acoxt_fregment_vertical_item_pojo();
////                String id = itemList.getData().get(position).getId();
////                String name=itemList.getData().get(position).getTitle();
////                Double price;
////                Double dis_price;
////                String image = String.valueOf(itemList.getData().get(position).getImg());
////                String description = itemList.getData().get(position).getDescription();
////
////                String priceString = itemList.getData().get(position).getFinalprice();
////                priceString = priceString.replaceAll("[^0-9]", "");
////
////                price=Double.parseDouble(priceString);
////
////                String dis_price_string= String.valueOf(itemList.getData().get(position).getQuantity());
////                dis_price_string=dis_price_string.replaceAll("[^0-9]", "");
////
////                dis_price=Double.parseDouble(dis_price_string);
////
////
////                if (dbHelper.ProductExist(name))
////                {
////                    dbHelper.incrementQuantityAndPrice(name,1,price);
////                }else {
////
////                    dbHelper.InsertData(id,name,price,dis_price,image,description);
////                }
////                Toast.makeText(context, "Product added to the cart", Toast.LENGTH_SHORT).show();
//
//
////                String id = acoxt_fregment_vertical_item_pojos.get(position).getId();
////                String name=acoxt_fregment_vertical_item_pojos.get(position).getTitle();
////                String weight1=acoxt_fregment_vertical_item_pojos.get(position).getUnit();
////                Double price1= Double.valueOf(acoxt_fregment_vertical_item_pojos.get(position).getPrice());
//////        Double dis_price= Double.valueOf(acoxt_fregment_vertical_item_pojos.get(position).getFinalprice());
////                String img= acoxt_fregment_vertical_item_pojos.get(position).getImg().toString();
////                String dis_price = acoxt_fregment_vertical_item_pojos.get(position).getFinalprice();
////                dis_price = dis_price.replaceAll("[^0-9]", "");
////                price1 = price1.replaceAll("[^0-9]", "");
//
////                if (dbHelper.ProductExist(name))
////                {
////                    dbHelper.incrementQuantityAndPrice(name,1, price1);
////                }else {
////
////                    dbHelper.InsertData(id,name, price1, Double.parseDouble(dis_price),img,weight1);
////                }
////                Toast.makeText(context, "Product added", Toast.LENGTH_SHORT).show();
//
//
//
//
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



    }

    @Override
    public int getItemCount() {
//        return Math.min(acoxt_fregment_vertical_item_pojos.size(), 6);
        return acoxt_fregment_vertical_item_pojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView home_prodname,add_product,count,discount_price,price,weight;
        ImageView home_img;
        CardView carditem;
        LinearLayout linear_add;
        RelativeLayout increase,relative_decrease;
        int itemCount=0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_prodname=itemView.findViewById(R.id.home_prodname);
            add_product=itemView.findViewById(R.id.add_product);
            home_img=itemView.findViewById(R.id.home_img);
            carditem=itemView.findViewById(R.id.carditem);
            linear_add=itemView.findViewById(R.id.linear_add);
            count=itemView.findViewById(R.id.count);
            increase=itemView.findViewById(R.id.increase);
            relative_decrease=itemView.findViewById(R.id.relative_decrease);
            weight=itemView.findViewById(R.id.weight);
            price=itemView.findViewById(R.id.price);
            discount_price=itemView.findViewById(R.id.discount_price);
        }
    }
}
