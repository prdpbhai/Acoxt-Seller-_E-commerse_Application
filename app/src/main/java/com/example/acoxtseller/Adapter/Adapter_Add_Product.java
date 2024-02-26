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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Add_Product;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_in_cart_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_pojo;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.Model.Model_add_productList;
import com.example.acoxtseller.R;
import com.example.acoxtseller.ViewCartItem_Activity;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Add_Product extends RecyclerView.Adapter<Adapter_Add_Product.ViewHolder> {

    List<Add_product_pojo.RecommendedProduct>addProductPojos;
    Context context;
    DB_Helper dbHelper;
    private int totalItemCount=0;

//    public Adapter_Add_Product(List<Add_product_pojo.RecommendedProduct> addProductPojos, Context context) {
//        this.addProductPojos = addProductPojos;
//        this.context = context;
//    }


    public Adapter_Add_Product(List<Add_product_pojo.RecommendedProduct> addProductPojos, Context context) {
        this.addProductPojos = addProductPojos;
        this.context = context;
        dbHelper=new DB_Helper(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_Add_Product.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_add_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Add_product_pojo.RecommendedProduct item = addProductPojos.get(position);

        holder.item_Name.setText(addProductPojos.get(position).getTitle());
        holder.discount_price.setText(addProductPojos.get(position).getFinalprice());
        holder.price.setText(addProductPojos.get(position).getPrice());
        holder.weight.setText(addProductPojos.get(position).getUnit());
        String input = addProductPojos.get(position).getImg().toString();
        String data=addProductPojos.get(position).getId();
        String output = input.replace("[", "").replace("]", "");
        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.image);
        Intent inten=new Intent();
        inten.putExtra("prdtid",addProductPojos.get(position).getId());

        holder.item_Name.setOnClickListener(v -> {
            Intent intent = new Intent(context,ViewCartItem_Activity.class);
            context.startActivity(intent);
        });
//        holder.all_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,ViewCartItem_Activity.class);
//                context.startActivity(intent);
//            }
//        });
//        holder.item_Name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(context,ViewCartItem_Activity.class);
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
                        holder.add_product.setVisibility(View.VISIBLE);
                        holder.count.setText("1");
                    }
                } else if (holder.itemCount == 0) {
                    holder.linear_add.setVisibility(View.GONE);
                    holder.add_product.setVisibility(View.VISIBLE);
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

        holder.all_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category_Id=addProductPojos.get(position).getId();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("addproductid",category_Id);
                editor.apply();
                Web_Service_login.getClient().add_product_pojo("Bearer " +Token,"product/"+category_Id).enqueue(new Callback<Add_product_pojo>() {
                    @Override
                    public void onResponse(Call<Add_product_pojo> call, Response<Add_product_pojo> response) {
                        if (response.body().getRecommendedProduct()!=null){
                            String idd=response.body().getData().getId();
//                    add_product_recyclerview.setHasFixedSize(true);
                            Log.d("cghjklkjnbviuy", ""+response.body().getData().getTitle());
                            Intent intent=new Intent(context,Add_Product.class);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Add_product_pojo> call, Throwable t) {

                    }
                });

            }
        });



        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addProductPojos.get(position).getId();
                String product_name = addProductPojos.get(position).getTitle();
                Double price;
                Double dis_price;
                String image = String.valueOf(item.getImg());
                String description = addProductPojos.get(position).getDescription();
                String priceString = addProductPojos.get(position).getFinalprice();
                priceString=priceString.replaceAll("[^0-9]", "");
                price=Double.parseDouble(priceString);
                String dis_priceString=addProductPojos.get(position).getPrice();
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


//        holder.add_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Web_Service_login.getClient().add_product_cart_pojo("Bearer " +Token,""+data).enqueue(new Callback<Add_product_in_cart_pojo>() {
////                    @Override
////                    public void onResponse(Call<Add_product_in_cart_pojo> call, Response<Add_product_in_cart_pojo> response) {
////                        if (response.body()!=null){
////                            Log.d("ffghjhgffdghgds", ""+response.body().getMsg());
////                            Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<Add_product_in_cart_pojo> call, Throwable t) {
////                        Log.e("","");
////                    }
////                });
//                holder.linear_add.setVisibility(View.VISIBLE);
//                holder.add_product.setVisibility(View.GONE);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return addProductPojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_Name,discount_price,weight,price,add_product,home_prodname,count;
        ImageView image;
        CardView all_card;
        int itemCount=0;
        LinearLayout linear_add;
        RelativeLayout increase,relative_decrease;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_Name=itemView.findViewById(R.id.item_Name);
            all_card=itemView.findViewById(R.id.all_card);
            discount_price=itemView.findViewById(R.id.discount_price);
            weight=itemView.findViewById(R.id.weight);
            price=itemView.findViewById(R.id.price);
            image=itemView.findViewById(R.id.image);
            add_product=itemView.findViewById(R.id.add_product);
            count=itemView.findViewById(R.id.count);
            increase=itemView.findViewById(R.id.increase);
            linear_add=itemView.findViewById(R.id.linear_add);
            relative_decrease=itemView.findViewById(R.id.relative_decrease);
        }
    }
}
