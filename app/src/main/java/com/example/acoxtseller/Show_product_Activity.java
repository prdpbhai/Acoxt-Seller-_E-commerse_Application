package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_Search;
import com.example.acoxtseller.Adapter.Adapter_recy_Acoxt;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Search_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Show_product_Activity extends AppCompatActivity {
    RecyclerView show_product_recy;
    Adapter_recy_Acoxt adapter_recy_acoxt;
    Context context;
    RelativeLayout search_box;
    Adapter_Search adapter_search;
    EditText search;
    TextView text_head;
    ImageView back_btn,search_icon;
    DB_Helper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        show_product_recy=findViewById(R.id.show_product_recy);
        back_btn=findViewById(R.id.back_btn);
        search_icon=findViewById(R.id.search_icon);
        text_head=findViewById(R.id.text_head);
        search_box=findViewById(R.id.search_box);
        search=findViewById(R.id.search);

        SharedPreferences pref=Show_product_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token","");


        search_icon.setVisibility(View.GONE);
        text_head.setVisibility(View.GONE);
        search_box.setVisibility(View.VISIBLE);

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_icon.setVisibility(View.GONE);
                text_head.setVisibility(View.GONE);
                search_box.setVisibility(View.VISIBLE);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();
//                String searchText = search.getText().toString();

                Log.d("hdfjeshhbahf", ""+searchText);
                if (!TextUtils.isEmpty(searchText)) {
                    performSearch(searchText);
                } else {
//                    Toast.makeText(context, ""+searchText, Toast.LENGTH_SHORT).show();
                    Web_Service_login.getClient().acoxt_fregment_pojo("Bearer "+Token,"product").enqueue(new Callback<Acoxt_fregment_vertical_item_pojo>() {
                        @Override
                        public void onResponse(Call<Acoxt_fregment_vertical_item_pojo> call, Response<Acoxt_fregment_vertical_item_pojo> response) {
                            if (response.body().getCode()==200){
                                adapter_recy_acoxt=new Adapter_recy_Acoxt(response.body().getData(),Show_product_Activity.this, dbHelper);
                                show_product_recy.setHasFixedSize(true);
                                show_product_recy.setLayoutManager(new GridLayoutManager(context,2));
                                show_product_recy.setAdapter(adapter_recy_acoxt);

                            }
                        }

                        @Override
                        public void onFailure(Call<Acoxt_fregment_vertical_item_pojo> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        Web_Service_login.getClient().acoxt_fregment_pojo("Bearer "+Token,"product").enqueue(new Callback<Acoxt_fregment_vertical_item_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_fregment_vertical_item_pojo> call, Response<Acoxt_fregment_vertical_item_pojo> response) {
                if (response.body().getCode()==200){
                    adapter_recy_acoxt=new Adapter_recy_Acoxt(response.body().getData(),Show_product_Activity.this, dbHelper);
                    show_product_recy.setHasFixedSize(true);
                    show_product_recy.setLayoutManager(new GridLayoutManager(context,2));
                    show_product_recy.setAdapter(adapter_recy_acoxt);

                }
            }

            @Override
            public void onFailure(Call<Acoxt_fregment_vertical_item_pojo> call, Throwable t) {

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void performSearch(String searchText) {

        SharedPreferences pref=Show_product_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token","");
//        Toast.makeText(Show_product_Activity.this, ""+searchText, Toast.LENGTH_SHORT).show();
        Web_Service_login.getClient().search_pojo( "search", searchText).enqueue(new Callback<Search_pojo>() {
            @Override
            public void onResponse(Call<Search_pojo> call, Response<Search_pojo> response) {
                if (response.body()!=null) {
                    adapter_search = new Adapter_Search(response.body().getData(), Show_product_Activity.this, dbHelper);
                    Log.d("khdbysdgfgsbcs", ""+response.body().getData());
                    show_product_recy.setHasFixedSize(true);
                    show_product_recy.setLayoutManager(new GridLayoutManager(Show_product_Activity.this, 2));
                    show_product_recy.setAdapter(adapter_search);
                }
            }

            @Override
            public void onFailure(Call<Search_pojo> call, Throwable t) {
                // Handle failure
            }
        });
    }
}