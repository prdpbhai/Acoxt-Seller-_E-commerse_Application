package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.acoxtseller.Adapter.Adapter_show_banner_product;
import com.example.acoxtseller.Api_Pojo.Show_banner_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Show_Banner_Activity extends AppCompatActivity {
    RecyclerView show_recy_banner;
    Adapter_show_banner_product adapter_show_banner_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_banner);
        show_recy_banner=findViewById(R.id.show_recy_banner);

        Intent intent=getIntent();
        String productid=intent.getStringExtra("productId");
        Log.d("gfrdeydftgygty", ""+productid);

        Web_Service_login.getClient().show_banner_pojo("customer_banner/"+productid).enqueue(new Callback<Show_banner_pojo>() {
            @Override
            public void onResponse(Call<Show_banner_pojo> call, Response<Show_banner_pojo> response) {
                 if (response.body().getCode()==200){
                     adapter_show_banner_product=new Adapter_show_banner_product(response.body().getData(),Show_Banner_Activity.this);
                     show_recy_banner.setHasFixedSize(true);
                     show_recy_banner.setLayoutManager(new GridLayoutManager(Show_Banner_Activity.this,2));
                     show_recy_banner.setAdapter(adapter_show_banner_product);
                 }
            }

            @Override
            public void onFailure(Call<Show_banner_pojo> call, Throwable t) {

            }
        });
    }
}