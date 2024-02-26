package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.MyStore_POJO;
import com.example.acoxtseller.Api_Pojo.Select_fixed_seller_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Select_your_preferred_shipping extends AppCompatActivity {
    RelativeLayout continue_select_ship;
    private RadioGroup radio_group;
    RadioButton check_one,check_two;
    TextView back;
    boolean radiocheak=true;
    boolean radiocheak1=true;
    String Token;
    String type="";
    String seller_type;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_preferred_shipping);
        continue_select_ship=findViewById(R.id.continue_select_ship);

        SharedPreferences pref=Select_your_preferred_shipping.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        Token = pref.getString("token","");

        continue_select_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(Select_your_preferred_shipping.this, ""+type, Toast.LENGTH_SHORT).show();
                Web_Service_login.getClient().fixed_seller_pojo("Bearer "+Token,type).enqueue(new Callback<Select_fixed_seller_pojo>() {
                    @Override
                    public void onResponse(Call<Select_fixed_seller_pojo> call, Response<Select_fixed_seller_pojo> response) {

                        if(response.body()!=null){
                            Intent intent=new Intent(Select_your_preferred_shipping.this,Registration_complete.class);
                            startActivity(intent);
//                            Toast.makeText(Select_your_preferred_shipping.this, ""+type, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Select_fixed_seller_pojo> call, Throwable t) {

                    }
                });

            }
        });
        radio_group=findViewById(R.id.radio_group);
        check_one=findViewById(R.id.check_one);
        check_two=findViewById(R.id.check_two);
        boolean condition = true;

        check_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(radiocheak==true) {
                    check_two.setChecked(false);
                    type="fixed_seller";
                    radiocheak = false;


                }
                else {
                    check_two.setChecked(true);
                    type="moving_seller";
                    radiocheak=true;
                }
//                Toast.makeText(Select_your_preferred_shipping.this, ""+type, Toast.LENGTH_SHORT).show();





            }
        });
        check_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(radiocheak1==true){
                    check_one.setChecked(false);
                    type="moving_seller";
                    radiocheak1 = false;
                }
                else {
                    check_one.setChecked(true);
                    type="fixed_seller";
                    radiocheak1=true;
                }

                SharedPreferences pref=Select_your_preferred_shipping.this.getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user",type);
                editor.apply();
                Toast.makeText(Select_your_preferred_shipping.this, ""+type, Toast.LENGTH_SHORT).show();


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