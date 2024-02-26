package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Store_Name_Show_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration_complete extends AppCompatActivity {
    RelativeLayout complete_registration;
    TextView back,store_name,id_num,seller_type;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_complete);
        complete_registration=findViewById(R.id.complete_registration);
        store_name=findViewById(R.id.store_name);
        id_num=findViewById(R.id.id_num);
        seller_type=findViewById(R.id.seller_type);

        SharedPreferences pref = Registration_complete.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
        Web_Service_login.getClient().store_name_show_pojo("Bearer " + Token,"acoxtseller").enqueue(new Callback<Store_Name_Show_pojo>() {
            @Override
            public void onResponse(Call<Store_Name_Show_pojo> call, Response<Store_Name_Show_pojo> response) {
                if (response.body()!=null){
//                    Toast.makeText(Registration_complete.this, ""+response.body().getStoreName(), Toast.LENGTH_SHORT).show();
                    store_name.setText(response.body().getStoreName());
                    id_num.setText(response.body().getId() );
//                    seller_type.setText(response.body().getSellerType());


                    if (response.body().getSellerType().equals("moving_seller")){
                        seller_type.setText("Moving Seller");
                    }
                    else {
                        seller_type.setText("Fixed Seller");
                    }
                }
            }

            @Override
            public void onFailure(Call<Store_Name_Show_pojo> call, Throwable t) {

            }
        });

        complete_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration_complete.this,Home_Product_Page.class);
                startActivity(intent);
            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}