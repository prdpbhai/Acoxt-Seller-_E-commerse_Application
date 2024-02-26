package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.MyStore_POJO;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Name_your_ACOXT_Store extends AppCompatActivity {
    RelativeLayout continue_store,main_layout_store;
    TextView back;
    EditText store_name;
    String Token;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_your_acoxt_store);
        continue_store=findViewById(R.id.continue_store);
        main_layout_store=findViewById(R.id.main_layout_store);

        SharedPreferences pref=Name_your_ACOXT_Store.this.getSharedPreferences("login", Context.MODE_PRIVATE);
         Token = pref.getString("token","");

        main_layout_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        store_name=findViewById(R.id.store_name);
        continue_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(store_name.getText().toString().isEmpty()){
                    Toast.makeText(Name_your_ACOXT_Store.this, "Enter Store Name", Toast.LENGTH_SHORT).show();

                }else {
                    Web_Service_login.getClient().addstore("Bearer "+Token,store_name.getText().toString()).enqueue(new Callback<MyStore_POJO>() {
                        @Override
                        public void onResponse(Call<MyStore_POJO> call, Response<MyStore_POJO> response) {

                            if(response.body()!=null){
//                                Toast.makeText(Name_your_ACOXT_Store.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Name_your_ACOXT_Store.this,Provide_your_shop_address.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<MyStore_POJO> call, Throwable t) {

                        }
                    });
                }

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

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}