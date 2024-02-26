package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_My_Order_list;
import com.example.acoxtseller.Adapter.Adapter_cigrate_list;
import com.example.acoxtseller.Adapter.Adapter_order_manage;
import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.Model.Model_My_Order_list;
import com.example.acoxtseller.Model.Model_order_manage;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_manage extends AppCompatActivity {

    TextView category_popup_down,order_id;
    RecyclerView order_manage_recy;
    LinearLayout back_btn;
    ImageView empty_cart;
    Adapter_order_manage adapter_order_manage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);
        order_manage_recy=findViewById(R.id.order_manage_recy);
        order_id=findViewById(R.id.order_id);
        back_btn=findViewById(R.id.back_btn);
        empty_cart=findViewById(R.id.empty_cart);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SharedPreferences pref=Order_manage.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
        Web_Service_login.getClient().show_order_detail_management_customer("Bearer " +Token,"getRequstDetail").enqueue(new Callback<Show_order_detail_management_customer_pojo>() {
            @Override
            public void onResponse(Call<Show_order_detail_management_customer_pojo> call, Response<Show_order_detail_management_customer_pojo> response) {
                if (response.body().getData().isEmpty()){
                    empty_cart.setVisibility(View.VISIBLE);
//                    Toast.makeText(Order_manage.this, "pdd", Toast.LENGTH_SHORT).show();
                }else {
                    adapter_order_manage=new Adapter_order_manage(response.body().getData(),Order_manage.this);
                    order_manage_recy.setLayoutManager(new LinearLayoutManager(Order_manage.this));
                    order_manage_recy.setHasFixedSize(true);
                    order_manage_recy.setAdapter(adapter_order_manage);


//                    Toast.makeText(Order_manage.this, "Show", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Show_order_detail_management_customer_pojo> call, Throwable t) {

            }
        });

//        List<Model_order_manage> model_order_manages=new ArrayList<>();
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//        model_order_manages.add(new Model_order_manage("21-Dec-2023"));
//
//        order_manage_recy.setLayoutManager(new LinearLayoutManager(this));
//        order_manage_recy.setAdapter(new Adapter_order_manage(this,model_order_manages));



//        category_popup_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupMenu(v);
//            }
//        });
//    }
//
//    public void showPopupMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(this, view); // Use 'this' for activity context
//        popupMenu.getMenuInflater().inflate(R.menu.order_manage_menu, popupMenu.getMenu());
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Toast.makeText(Order_manage.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//
//        popupMenu.show();
    }
}