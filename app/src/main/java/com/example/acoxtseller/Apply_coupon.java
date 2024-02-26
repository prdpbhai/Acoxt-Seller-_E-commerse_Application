package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.acoxtseller.Adapter.Adapter_Coupan_apply;
import com.example.acoxtseller.Model.Model_coupon_apply;

import java.util.ArrayList;
import java.util.List;

public class Apply_coupon extends AppCompatActivity {
    RecyclerView coupon_recy;
    Adapter_Coupan_apply adapter_coupan_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_coupon);

        coupon_recy=findViewById(R.id.coupon_recy);

        List<Model_coupon_apply> model_coupon_applies=new ArrayList<>();

        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        model_coupon_applies.add(new Model_coupon_apply("Stellar"));
        adapter_coupan_apply=new Adapter_Coupan_apply(model_coupon_applies,this);
        coupon_recy.setLayoutManager(new LinearLayoutManager(this));
        coupon_recy.setHasFixedSize(true);
        coupon_recy.setAdapter(adapter_coupan_apply);


    }
}