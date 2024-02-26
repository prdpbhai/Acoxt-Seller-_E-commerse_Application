package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_Cigrate_Name;
import com.example.acoxtseller.Adapter.Adapter_cigrate_list;
import com.example.acoxtseller.Api_Pojo.Munchies_Product_show_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Munchies_Category_Activity extends AppCompatActivity implements Adapter_cigrate_list.OnImageClickListener {
    RecyclerView side_recyclerview,product_recyclerview;
    LinearLayout back;
    TextView product_name;
    Adapter_Cigrate_Name adapter_cigrate_name;
    Adapter_cigrate_list adapter_cigrate_list;
    String producnam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_munchies_category);
        product_name=findViewById(R.id.product_name);

        SharedPreferences pref=Munchies_Category_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
        String id=getIntent().getStringExtra("name");
        Log.d("sdfghjjhgfdsgha", ""+id);
        producnam=pref.getString("productname","");
        Log.d("ignoihjk", ""+producnam);

        side_recyclerview=findViewById(R.id.side_recyclerview);
        Web_Service_login.getClient().munchies_product_show_pojo("Bearer " +Token,"category/productList/"+id).enqueue(new Callback<Munchies_Product_show_pojo>() {
            @Override
            public void onResponse(Call<Munchies_Product_show_pojo> call, Response<Munchies_Product_show_pojo> response) {
                if (response.body().getSubcategoryData()!=null){
                    adapter_cigrate_list=new Adapter_cigrate_list(response.body().getSubcategoryData(),Munchies_Category_Activity.this,Munchies_Category_Activity.this);
                    side_recyclerview.setLayoutManager(new LinearLayoutManager(Munchies_Category_Activity.this));
                    side_recyclerview.setHasFixedSize(true);
                    side_recyclerview.setAdapter(adapter_cigrate_list);
                    product_name.setText(producnam);
                }
            }

            @Override
            public void onFailure(Call<Munchies_Product_show_pojo> call, Throwable t) {

            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        product_recyclerview=findViewById(R.id.product_recyclerview);
        Web_Service_login.getClient().munchies_product_show_pojo("Bearer " +Token,"category/productList/"+id).enqueue(new Callback<Munchies_Product_show_pojo>() {
            @Override
            public void onResponse(Call<Munchies_Product_show_pojo> call, Response<Munchies_Product_show_pojo> response) {
                if (response.body().getProductData()!=null){
                    adapter_cigrate_name=new Adapter_Cigrate_Name(response.body().getProductData(),Munchies_Category_Activity.this);
                    product_recyclerview.setLayoutManager(new GridLayoutManager(Munchies_Category_Activity.this, 2));
                    product_recyclerview.setHasFixedSize(true);
                    product_recyclerview.setAdapter(adapter_cigrate_name);
                    Log.d("ghjkjhghjhjm", ""+response.body().getProductData());

                }
            }

            @Override
            public void onFailure(Call<Munchies_Product_show_pojo> call, Throwable t) {

            }
        });

    }

    @Override
    public void onImageClick(String itemId) {
        SharedPreferences pref=Munchies_Category_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
        Log.d("kjfvsddgfbdg", ""+itemId);
        Web_Service_login.getClient().munchies_product_show_pojo("Bearer " +Token,"customer_banner/"+itemId).enqueue(new Callback<Munchies_Product_show_pojo>() {
            @Override
            public void onResponse(Call<Munchies_Product_show_pojo> call, Response<Munchies_Product_show_pojo> response) {
                if (response.body().getProductData()!=null){
                    adapter_cigrate_name=new Adapter_Cigrate_Name(response.body().getProductData(),Munchies_Category_Activity.this);
                    product_recyclerview.setLayoutManager(new GridLayoutManager(Munchies_Category_Activity.this, 2));
                    product_recyclerview.setHasFixedSize(true);
                    product_recyclerview.setAdapter(adapter_cigrate_name);
                    Log.d("dfjierugjfhv", ""+response.body().getProductData());
                    Log.d("nmvbudfyvbdfv", ""+itemId);

                }
                else {
                    Toast.makeText(Munchies_Category_Activity.this, "Empty Product", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Munchies_Product_show_pojo> call, Throwable t) {

            }
        });
    }
}