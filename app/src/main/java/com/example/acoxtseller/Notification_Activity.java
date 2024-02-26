package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.acoxtseller.Adapter.Adapter_notification;
import com.example.acoxtseller.Api_Pojo.Notification_pojo;
import com.example.acoxtseller.Model.Model_notificatio;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification_Activity extends AppCompatActivity {
    RelativeLayout linear_help_text;
    RecyclerView notification_recy;
    Adapter_notification adapter_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        linear_help_text=findViewById(R.id.linear_help_text);
        notification_recy=findViewById(R.id.notification_recy);
        linear_help_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        List<Model_notificatio>model_notificatios=new ArrayList<>();
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        model_notificatios.add(new Model_notificatio("Cold Drink 10%off"));
//        adapter_notification=new Adapter_notification(model_notificatios,this);
//        notification_recy.setHasFixedSize(true);
//        notification_recy.setLayoutManager(new LinearLayoutManager(this));
//        notification_recy.setAdapter(adapter_notification);

        Web_Service_login.getClient().notification_pojo("notification").enqueue(new Callback<Notification_pojo>() {
            @Override
            public void onResponse(Call<Notification_pojo> call, Response<Notification_pojo> response) {
                if (response.body()!=null){
                    adapter_notification=new Adapter_notification(response.body().getData(),Notification_Activity.this);
                    notification_recy.setHasFixedSize(true);
                    notification_recy.setLayoutManager(new LinearLayoutManager(Notification_Activity.this));
                    notification_recy.setAdapter(adapter_notification);
                }
            }

            @Override
            public void onFailure(Call<Notification_pojo> call, Throwable t) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}