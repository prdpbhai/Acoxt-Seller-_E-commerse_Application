package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.acoxtseller.Fregment.Buyer_detail_fragment;
import com.example.acoxtseller.Fregment.Order_detail_fragment;

public class Order_Detail_Activity extends AppCompatActivity {
    CardView buyer_detail,order_detail;
    RelativeLayout back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        order_detail=findViewById(R.id.order_detail);
        buyer_detail=findViewById(R.id.buyer_detail);
        back_btn=findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent=getIntent();
        String AS=intent.getStringExtra("prameter");

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new Order_detail_fragment(AS))
                    .commit();
        }

        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Order_detail_fragment(AS))
                            .commit();
                }
            }
        });
        buyer_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Buyer_detail_fragment())
                            .commit();
                }
            }
        });
    }
}