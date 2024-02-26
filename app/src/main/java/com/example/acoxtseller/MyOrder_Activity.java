package com.example.acoxtseller;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.acoxtseller.Adapter.Adapter_Add_Product;
import com.example.acoxtseller.Adapter.Adapter_My_Order_list;
import com.example.acoxtseller.Adapter.Adapter_bankList;
import com.example.acoxtseller.Model.Model_My_Order_list;

import java.util.ArrayList;
import java.util.List;

public class MyOrder_Activity extends AppCompatActivity {
    RecyclerView recyclerview_order_list;
    LinearLayout back_btn;
    ImageView back_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        back_img=findViewById(R.id.back_img);
        recyclerview_order_list=findViewById(R.id.recyclerview_order_list);
        List<Model_My_Order_list>model_my_order_lists=new ArrayList<>();
        model_my_order_lists.add(new Model_My_Order_list("21-Dec-2023","3:10PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("22-Dec-2023","3:20PM","999013","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("23-Dec-2023","3:30PM","999014","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("24-Dec-2023","3:40PM","999015","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("24-Dec-2023","3:50PM","999016","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));
        model_my_order_lists.add(new Model_My_Order_list("20-Dec-2023","3:00PM","999012","50",R.drawable.my_order_chips_icon));

        recyclerview_order_list.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_order_list.setAdapter(new Adapter_My_Order_list(getApplicationContext(),model_my_order_lists));


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}