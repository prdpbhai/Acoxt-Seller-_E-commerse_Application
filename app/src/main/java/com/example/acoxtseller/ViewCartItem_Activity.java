package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.acoxtseller.Adapter.Adapter_Add_Product;
import com.example.acoxtseller.Adapter.ViewPagerAdapter;

public class ViewCartItem_Activity extends AppCompatActivity {
    TextView linear_add_card,count;
    RelativeLayout increase,relative_decrease;
    private int itemCount=0;
    ImageView back;

    RecyclerView add_product_recyclerview;
    Adapter_Add_Product adapter_add_product;

    ViewPager mViewPager;
    int[] images = {R.drawable.chocolates, R.drawable.chipps_icon, R.drawable.chocolates, R.drawable.chipps_icon,
            R.drawable.chocolates, R.drawable.chipps_icon, R.drawable.chocolates, R.drawable.chipps_icon};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart_item);
        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        mViewPagerAdapter = new ViewPagerAdapter(ViewCartItem_Activity.this, images);

        mViewPager.setAdapter(mViewPagerAdapter);

        back=findViewById(R.id.back);
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
                Intent intent=new Intent(ViewCartItem_Activity.this,Home_Product_Page.class);
                intent.putExtra("possition","1");
                startActivity(intent);
            }
        });

        relative_decrease=findViewById(R.id.relative_decrease);
        increase=findViewById(R.id.increase);
        count=findViewById(R.id.count);
        relative_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemCount > 0) {
                    itemCount--;
                    count.setText(String.valueOf(itemCount));
                }
            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCount++;
                count.setText(String.valueOf(itemCount));

            }
        });
    }
}