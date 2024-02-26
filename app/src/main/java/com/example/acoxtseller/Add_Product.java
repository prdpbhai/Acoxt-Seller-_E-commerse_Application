package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_Add_Product;
import com.example.acoxtseller.Adapter.ViewPagerAdapter;
import com.example.acoxtseller.Api_Pojo.Add_product_in_cart_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_pojo;
import com.example.acoxtseller.Api_Pojo.Checkout_pojo;
import com.example.acoxtseller.Api_Pojo.Decrement_cart_item_pojo;
import com.example.acoxtseller.Api_Pojo.Increment_cart_item_pojo;
import com.example.acoxtseller.Model.Model_add_productList;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product extends AppCompatActivity {
    LinearLayout add_card,count_num;
    ImageView back;
    TextView count;
    TextView down;
    TextView up;
    TextView linear_add_card;
    TextView add_product;
    TextView product_name;
    TextView weight;
    TextView price;
    TextView rate;
    TextView descript;
    TextView quantity;
    TextView product_num;
    RelativeLayout linear_checkout,increase,relative_decrease,relative_hide,about_product;

    String paisa;
    String name,priceString,description,image,product_name2;
    int num=1;
    Double dis_price,pricee;
    private int totalItemCount=0;
    String id;
    RecyclerView add_product_recyclerview;
    Adapter_Add_Product adapter_add_product;

//    ViewPager mViewPager;
    ImageView viewPagerMain;
    Add_product_pojo add_product_pojo;
    DB_Helper dbHelper;

    int[] images = {R.drawable.chocolates, R.drawable.chipps_icon, R.drawable.chocolates, R.drawable.chipps_icon,
            R.drawable.chocolates, R.drawable.chipps_icon, R.drawable.chocolates, R.drawable.chipps_icon};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;
    int itemCount=0;
    Integer id_priduct;

//    public Add_Product(String data) {
//        Log.e("data","");
//        id_priduct= Integer.valueOf(data);
//    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        back=findViewById(R.id.back);
        count_num=findViewById(R.id.count_num);
        linear_checkout=findViewById(R.id.linear_checkout);
        increase=findViewById(R.id.increase);
        relative_decrease=findViewById(R.id.relative_decrease);
        count=findViewById(R.id.count);
        down=findViewById(R.id.down);
        up=findViewById(R.id.up);
        about_product=findViewById(R.id.about_product);
        relative_hide=findViewById(R.id.relative_hide);
        add_product_recyclerview=findViewById(R.id.add_product_recyclerview);
        add_product=findViewById(R.id.add_product);
        product_name=findViewById(R.id.product_name);
        weight=findViewById(R.id.weight);
        rate=findViewById(R.id.rate);
        descript=findViewById(R.id.descript);
        quantity=findViewById(R.id.quantity);
        product_num=findViewById(R.id.product_num);
        price=findViewById(R.id.price);



        dbHelper=new DB_Helper(Add_Product.this);
        SharedPreferences pref=Add_Product.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
        String category_Id=pref.getString("addproductid","");
//        String category_Id=getIntent().getStringExtra("addid");
        Log.d("sdfghjjhgfdsghjgha", ""+category_Id);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        linear_add_card=findViewById(R.id.linear_add_card);
        linear_add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_Service_login.getClient().add_product_cart_pojo("Bearer " +Token,""+id).enqueue(new Callback<Add_product_in_cart_pojo>() {
                    @Override
                    public void onResponse(Call<Add_product_in_cart_pojo> call, Response<Add_product_in_cart_pojo> response) {
                        if (response.body()!=null){
//                            Toast.makeText(Add_Product.this, "Add", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Add_Product.this,Home_Product_Page.class);
                            intent.putExtra("possition","1");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Add_product_in_cart_pojo> call, Throwable t) {

                    }
                });
            }
        });

//        linear_checkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Web_Service_login.getClient().checkout_pojo("Bearer " +Token).enqueue(new Callback<Checkout_pojo>() {
//                    @Override
//                    public void onResponse(Call<Checkout_pojo> call, Response<Checkout_pojo> response) {
//                        if (response.body()!=null){
//                            Toast.makeText(Add_Product.this, "Checkout", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Checkout_pojo> call, Throwable t) {
//
//                    }
//                });
//            }
//        });

        Web_Service_login.getClient().add_product_pojo("Bearer " +Token,"product/"+category_Id).enqueue(new Callback<Add_product_pojo>() {
            @Override
            public void onResponse(Call<Add_product_pojo> call, Response<Add_product_pojo> response) {
                if (response.body().getCode()==200){
                    String idd=response.body().getData().getId();
                    adapter_add_product=new Adapter_Add_Product(response.body().getRecommendedProduct(),Add_Product.this);
                    add_product_recyclerview.setLayoutManager(new LinearLayoutManager(Add_Product.this,LinearLayoutManager.HORIZONTAL,false));
                    add_product_recyclerview.setAdapter(adapter_add_product);
//                    add_product_recyclerview.setHasFixedSize(true);
                    Log.d("cghjklkjnbviuy", ""+response.body().getRecommendedProduct());
                }
                else {
                    Toast.makeText(Add_Product.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Add_product_pojo> call, Throwable t) {

            }
        });




//        List<Model_add_productList>model_add_productLists=new ArrayList<>();
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        model_add_productLists.add(new Model_add_productList("name"));
//        add_product_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        add_product_recyclerview.setAdapter(new Adapter_Add_Product(getApplicationContext(),model_add_productLists));




        viewPagerMain=findViewById(R.id.viewPagerMain);
        Web_Service_login.getClient().add_product_pojo("Bearer " +Token,"product/"+category_Id).enqueue(new Callback<Add_product_pojo>() {
            @Override
            public void onResponse(Call<Add_product_pojo> call, Response<Add_product_pojo> response) {
                if (response.body().getData().getImg()!=null){
                    Log.d("dfgoiuytresxcvbn", ""+response.body().getData().getImg());
                    product_name.setText(response.body().getData().getTitle());
                    weight.setText(response.body().getData().getUnit());
                    rate.setText(response.body().getData().getFinalprice());
                    price.setText(response.body().getData().getFinalprice());
                    descript.setText(response.body().getData().getDescription());
                    quantity.setText(String.valueOf(response.body().getData().getUnit()));
                    id=response.body().getData().getId();
                    String input = response.body().getData().getImg().toString();
                    Log.d("fghjkl;iuytr", ""+response.body().getData().getImg());
                    String output = input.replace("[", "").replace("]", "");


                    Log.d("sdgfdgd",""+output);
                    Picasso.get()
                            .load("https://api.acoxt.fourbrick.in/"+output).into(viewPagerMain);




                     name = response.body().getData().getId();
                     product_name2 = response.body().getData().getTitle();
                     image = String.valueOf(response.body().getData().getImg());
                     description = response.body().getData().getDescription();
                     priceString = response.body().getData().getFinalprice();
                    priceString=priceString.replaceAll("[^0-9]", "");
                    pricee= Double.valueOf(String.valueOf(Double.parseDouble(priceString)));
                    String dis_priceString=response.body().getData().getPrice();
                    dis_priceString=dis_priceString.replaceAll("[^0-9]", "");
                    dis_price= Double.valueOf(String.valueOf(Double.parseDouble(dis_priceString)));

                }
            }

            @Override
            public void onFailure(Call<Add_product_pojo> call, Throwable t) {

            }
        });
//        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
//
//        mViewPagerAdapter = new ViewPagerAdapter(Add_Product.this, images);
//
//        mViewPager.setAdapter(mViewPagerAdapter);

        about_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num==1){
                    relative_hide.setVisibility(View.VISIBLE);
                    down.setVisibility(View.GONE);
                    up.setVisibility(View.VISIBLE);
                    num=0;
                }else {
                    relative_hide.setVisibility(View.GONE);
                    up.setVisibility(View.GONE);
                    down.setVisibility(View.VISIBLE);
                    num=1;
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_hide.setVisibility(View.VISIBLE);
                down.setVisibility(View.GONE);
                up.setVisibility(View.VISIBLE);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_hide.setVisibility(View.GONE);
                up.setVisibility(View.GONE);
                down.setVisibility(View.VISIBLE);
            }
        });

        relative_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCount > 0) {
                    itemCount--;
                    count.setText(String.valueOf(itemCount));
                    product_num.setText(String.valueOf(itemCount));
                    Double pricee;
                    pricee=Double.parseDouble(priceString);
                    dbHelper.decrementQuantityAndPrice(name,1,pricee);
                    if (itemCount == 0){
                        count_num.setVisibility(View.GONE);
                        linear_checkout.setVisibility(View.GONE);
                        add_card.setVisibility(View.VISIBLE);
                        count.setText("1");
                    }

//                    Web_Service_login.getClient().decrement_cart_item_pojo("Bearer " +Token,id).enqueue(new Callback<Decrement_cart_item_pojo>() {
//                        @Override
//                        public void onResponse(Call<Decrement_cart_item_pojo> call, Response<Decrement_cart_item_pojo> response) {
//                            if (response.body()!=null){
//                                Toast.makeText(Add_Product.this, "Decrement", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Decrement_cart_item_pojo> call, Throwable t) {
//
//                        }
//                    });
                } else if (itemCount == 0) {
                    count_num.setVisibility(View.GONE);
                    linear_checkout.setVisibility(View.GONE);
                    add_card.setVisibility(View.VISIBLE);
                    count.setText("1");
                    itemCount++;
                    count.setText(String.valueOf(itemCount));
                }
            }
        });

//        String idd=add_product_pojo.getData().getId();
        Log.d("fghjjhghjkhg", ""+id);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCount++;
                count.setText(String.valueOf(itemCount));
                product_num.setText(String.valueOf(itemCount));
                Double pricee;
                pricee=Double.parseDouble(priceString);
                dbHelper.incrementQuantityAndPrice(name,1,pricee);
                totalItemCount++;

//                Toast.makeText(Add_Product.this, "", Toast.LENGTH_SHORT).show();
//                Web_Service_login.getClient().increment_cart_item_pojo("Bearer " +Token,""+id).enqueue(new Callback<Increment_cart_item_pojo>() {
//                    @Override
//                    public void onResponse(Call<Increment_cart_item_pojo> call, Response<Increment_cart_item_pojo> response) {
//                        if (response.body()!=null){
//                            Toast.makeText(Add_Product.this, "Increment", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Increment_cart_item_pojo> call, Throwable t) {
//
//                    }
//                });
            }
        });


//        relative_decrease.setOnClickListener(v -> {
//            if (itemCount > 0) {
//                itemCount--;
//                totalItemCount--;
////                String name = acoxt_fregment_vertical_item_pojos.get(position).getId();
//                Double price;
//
////                String priceString = acoxt_fregment_vertical_item_pojos.get(position).getPrice();
//                priceString = priceString.replaceAll("[^0-9]", "");
//                price=Double.parseDouble(priceString);
//                count_num.setVisibility(View.VISIBLE);
//                add_card.setVisibility(View.GONE);
//                dbHelper.decrementQuantityAndPrice(name,1,price);
//                count.setText(String.valueOf(itemCount));
//                if (itemCount == 0) {
//                    count_num.setVisibility(View.GONE);
//                    add_card.setVisibility(View.VISIBLE);
//                    linear_checkout.setVisibility(View.GONE);
//                    count.setText("1");
//                    Log.d("asdfghj",""+totalItemCount);
//
//
//
//                }
//            }
//        });
//
//
//        increase.setOnClickListener(v -> {
//            itemCount++;
//            count.setText(String.valueOf(itemCount));
////            String name = acoxt_fregment_vertical_item_pojos.get(position).getId();
//            Double price;
////            String priceString = acoxt_fregment_vertical_item_pojos.get(position).getPrice();
//            priceString = priceString.replaceAll("[^0-9]", "");
//            price=Double.parseDouble(priceString);
//            count_num.setVisibility(View.VISIBLE);
//            add_card.setVisibility(View.GONE);
//            dbHelper.incrementQuantityAndPrice(name,1,price);
//            totalItemCount++;
//
//            Log.d("asdfghj",""+totalItemCount);
//
//
//        });



        add_card=findViewById(R.id.add_card);
        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_card.setVisibility(View.GONE);
                count_num.setVisibility(View.VISIBLE);
                linear_checkout.setVisibility(View.VISIBLE);


                Log.d("jdkhewbcwdk", ""+name);
                
                    Log.d("jdkhewbcwdk", ""+dbHelper);
                    if (dbHelper.productExists(name))
                    {
                        dbHelper.incrementQuantityAndPrice(name,1,pricee);
                        count.setText("1");
                        totalItemCount++;
//                        Toast.makeText(Add_Product.this, "product added increment", Toast.LENGTH_SHORT).show();
                    }else {
                        count.setText("1");
                        totalItemCount++;
                        dbHelper.insertData(name,product_name2,pricee,dis_price,image,description);
//                        Toast.makeText(Add_Product.this, "product added to the cart", Toast.LENGTH_SHORT).show();
                    }







            }
        });



        count_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        return null;
    }
}