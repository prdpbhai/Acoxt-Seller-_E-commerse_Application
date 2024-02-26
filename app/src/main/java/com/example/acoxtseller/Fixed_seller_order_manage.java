package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Order_status_update_pojo;
import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.Api_Pojo.Update_order_status_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fixed_seller_order_manage extends AppCompatActivity {

    TextView email_addr,phone_number,block,state,name,order_id;
    Button packed;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_seller_order_manage);


        email_addr=findViewById(R.id.email_addr);
        phone_number=findViewById(R.id.phone_number);
        block=findViewById(R.id.block);
        name=findViewById(R.id.name);
        order_id=findViewById(R.id.order_id);
        packed=findViewById(R.id.packed);
        back_btn=findViewById(R.id.back_btn);

        Intent intent=getIntent();
        String orderid=intent.getStringExtra("orderrid");
        Log.d("hgdasdgywegdhdbcbgf", ""+orderid);

        SharedPreferences pref = Fixed_seller_order_manage.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
        Web_Service_login.getClient().show_order_detail_management_customer("Bearer " +Token,"getRequstDetail").enqueue(new Callback<Show_order_detail_management_customer_pojo>() {
            @Override
            public void onResponse(Call<Show_order_detail_management_customer_pojo> call, Response<Show_order_detail_management_customer_pojo> response) {
                if (response.body()!=null){
                    name.setText(response.body().getData().get(0).getName());
                    block.setText(response.body().getData().get(0).getAddress().toString());
                    phone_number.setText(response.body().getData().get(0).getPhone());
                    order_id.setText(response.body().getData().get(0).getOrderId());
                    email_addr.setText(response.body().getData().get(0).getEmail());
                    Log.d("gddharrgrgrfvr", ""+response.body().getData().get(0).getAddress());
                }
            }

            @Override
            public void onFailure(Call<Show_order_detail_management_customer_pojo> call, Throwable t) {

            }
        });

        packed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Web_Service_login.getClient().update_order_status_pojo("Bearer "+Token,"orderRequestUpdate/"+orderid,"Packed").enqueue(
                        new Callback<Update_order_status_pojo>() {
                            @Override
                            public void onResponse(Call<Update_order_status_pojo> call, Response<Update_order_status_pojo> response) {
                                if (response.body().getCode()==200){
                                    Toast.makeText(Fixed_seller_order_manage.this, "success", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Fixed_seller_order_manage.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Update_order_status_pojo> call, Throwable t) {

                            }
                        }
                );
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}