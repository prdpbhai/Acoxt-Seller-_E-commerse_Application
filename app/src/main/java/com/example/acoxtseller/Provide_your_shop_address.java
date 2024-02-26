package com.example.acoxtseller;

import androidx.annotation.NonNull;
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
import android.window.OnBackInvokedDispatcher;

import com.example.acoxtseller.Api_Pojo.MyStore_POJO;
import com.example.acoxtseller.Api_Pojo.Store_name_address_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Provide_your_shop_address extends AppCompatActivity {
    RelativeLayout continue_shop_addr,main_layout_addr;
    TextView back;

    String Token;
    EditText edit_pincode,edit_city,edit_state,edit_addre;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_your_shop_address);
        continue_shop_addr=findViewById(R.id.continue_shop_addr);
        edit_pincode=findViewById(R.id.edit_pincode);
        edit_city=findViewById(R.id.edit_city);
        edit_state=findViewById(R.id.edit_state);
        edit_addre=findViewById(R.id.edit_addre);
        main_layout_addr=findViewById(R.id.main_layout_addr);


        main_layout_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        SharedPreferences pref=Provide_your_shop_address.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        Token = pref.getString("token","");

        continue_shop_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_pincode.getText().toString().isEmpty()){
                    Toast.makeText(Provide_your_shop_address.this, "Enter Pincode", Toast.LENGTH_SHORT).show();
                } else if (edit_city.getText().toString().isEmpty()) {
                    Toast.makeText(Provide_your_shop_address.this, "Enter City Name", Toast.LENGTH_SHORT).show();
                } else if (edit_state.getText().toString().isEmpty()) {
                    Toast.makeText(Provide_your_shop_address.this, "Enter State Name", Toast.LENGTH_SHORT).show();
                } else if (edit_addre.getText().toString().isEmpty()) {
                    Toast.makeText(Provide_your_shop_address.this, "Enter Building No.", Toast.LENGTH_SHORT).show();
                }else {
                    Web_Service_login.getClient().store_name_address_pojo("Bearer "+Token,edit_pincode.getText().toString(),
                            edit_city.getText().toString(),
                            edit_state.getText().toString(),
                            edit_addre.getText().toString()).enqueue(new Callback<Store_name_address_pojo>() {
                        @Override
                        public void onResponse(Call<Store_name_address_pojo> call, Response<Store_name_address_pojo> response) {
                            if(response.body()!=null){
//                            Toast.makeText(Provide_your_shop_address.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                if (response.body().getCode()==201){
                                    Intent intent =new Intent(Provide_your_shop_address.this,Select_your_preferred_shipping.class);
                                    startActivity(intent);
                                }
                            }else {
                                Toast.makeText(Provide_your_shop_address.this, "Enter Correct Address", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Store_name_address_pojo> call, Throwable t) {

                        }
                    });
                }

//                Intent intent =new Intent(Provide_your_shop_address.this,Select_your_preferred_shipping.class);
//                startActivity(intent);

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