package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.acoxtseller.Adapter.Adapter_ListView_Cart;
import com.example.acoxtseller.Adapter.Adapter_bankList;
import com.example.acoxtseller.Model.Model_ListView_Cart;
import com.example.acoxtseller.Model.Model_checkout_recyclerview;

import java.util.ArrayList;
import java.util.List;

public class CheckOut_Activity extends AppCompatActivity {
    ListView list_view;
    RecyclerView recyclerview_chechout;
    RelativeLayout checkout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);


        recyclerview_chechout=findViewById(R.id.recyclerview_chechout);
        checkout=findViewById(R.id.checkout);
        List<Model_checkout_recyclerview>model_checkout_recyclerviews=new ArrayList<>();
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));


        recyclerview_chechout.setLayoutManager(new LinearLayoutManager(this));

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
