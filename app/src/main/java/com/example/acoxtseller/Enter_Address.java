package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Enter_Address extends AppCompatActivity {
    LinearLayout home,work,other;
    RelativeLayout enter_address_btn;
    TextView back_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);
        home=findViewById(R.id.home);
        work=findViewById(R.id.work);
        other=findViewById(R.id.other);
        enter_address_btn=findViewById(R.id.enter_address_btn);
        back_btn=findViewById(R.id.back_btn);
        enter_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Enter_Address.this,Home_Product_Page.class);
                startActivity(intent);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        return null;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}