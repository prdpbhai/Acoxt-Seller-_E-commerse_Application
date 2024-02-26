package com.example.acoxtseller.Fregment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acoxtseller.Adapter.Adapter_order_list_detail;
import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Buyer_detail_fragment extends Fragment {

    View view;
    TextView email_addr,phone_number,block,state,name,order_id;
    Show_order_detail_management_customer_pojo show_order_detail_management_customer_pojo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_buyer_detail_fragment, container, false);

        email_addr=view.findViewById(R.id.email_addr);
        phone_number=view.findViewById(R.id.phone_number);
        block=view.findViewById(R.id.block);
        name=view.findViewById(R.id.name);
        order_id=view.findViewById(R.id.order_id);

        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");
        Web_Service_login.getClient().show_order_detail_management_customer("Bearer " +Token,"getRequstDetail").enqueue(new Callback<Show_order_detail_management_customer_pojo>() {
            @Override
            public void onResponse(Call<Show_order_detail_management_customer_pojo> call, Response<Show_order_detail_management_customer_pojo> response) {
                if (response.body()!=null){
                    name.setText(response.body().getData().get(0).getName());
                    block.setText(response.body().getData().get(0).getAddress().toString());
                    phone_number.setText(response.body().getData().get(0).getPhone());
                    order_id.setText(response.body().getData().get(0).getOrderId());
                    email_addr.setText(response.body().getData().get(0).getEmail());
                    Log.d("gddharrgrgrfvr", ""+response.body().getData().get(0).getAddress());
                }
            }

            @Override
            public void onFailure(Call<Show_order_detail_management_customer_pojo> call, Throwable t) {

            }
        });

        return view;
    }
}