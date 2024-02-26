package com.example.acoxtseller.Fregment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_My_Order_list;
import com.example.acoxtseller.Adapter.Adapter_order_list_detail;
import com.example.acoxtseller.Adapter.Adapter_recy_Acoxt;
import com.example.acoxtseller.Api_Pojo.Accept_customer_order_pojo;
import com.example.acoxtseller.Api_Pojo.Order_status_update_pojo;
import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.Model.Model_order_list_detail;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Update_order_Status;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Order_detail_fragment extends Fragment {
    View view;
    Button click;
    String id;
    Integer setIndex;

    Adapter_order_list_detail adapter_order_list_detail;

    public Order_detail_fragment(String data) {
        Log.e("data","");
        setIndex= Integer.valueOf(data);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_order_detail_fragment, container, false);

//        onItemClick(id);

        click=view.findViewById(R.id.click);
        RecyclerView order_manage_recy = view.findViewById(R.id.order_detail_recy);
        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");
        Web_Service_login.getClient().show_order_detail_management_customer("Bearer " +Token,"getRequstDetail").enqueue(new Callback<Show_order_detail_management_customer_pojo>() {
            @Override
            public void onResponse(Call<Show_order_detail_management_customer_pojo> call, Response<Show_order_detail_management_customer_pojo> response) {
                if (response.body()!=null){
                    adapter_order_list_detail=new Adapter_order_list_detail(response.body().getData(),view.getContext(),setIndex);
                    order_manage_recy.setHasFixedSize(true);
                    order_manage_recy.setLayoutManager(new LinearLayoutManager(requireContext()));
                    order_manage_recy.setAdapter(adapter_order_list_detail);
                    id=response.body().getData().get(0).getId();
                    Log.d("sdfghhgfdsaasdf", ""+id);

//                    adapter_order_list_detail.setOnItemClickListener((Adapter_order_list_detail.OnItemClickListener) getContext());

                }
            }

            @Override
            public void onFailure(Call<Show_order_detail_management_customer_pojo> call, Throwable t) {

            }
        });


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(view.getContext(), Update_order_Status.class);
//                startActivity(intent);
                Web_Service_login.getClient().accept_customer_order_pojo("Bearer " +Token,"orderRequestUpdate/"+id,"Packed").enqueue(new Callback<Accept_customer_order_pojo>() {
                    @Override
                    public void onResponse(Call<Accept_customer_order_pojo> call, Response<Accept_customer_order_pojo> response) {
                        if (response.body()!=null){
                            Toast.makeText(getContext(), "Accept", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(view.getContext(), Update_order_Status.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Accept_customer_order_pojo> call, Throwable t) {

                    }
                });

//                Web_Service_login.getClient().order_status_update_pojo("Bearer " +Token,"orderRequestUpdate/"+id,"pack").enqueue(new Callback<Order_status_update_pojo>() {
//                    @Override
//                    public void onResponse(Call<Order_status_update_pojo> call, Response<Order_status_update_pojo> response) {
//                        if (response.body().getCode()==200){
//                            Toast.makeText(getContext(), "Accept", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(view.getContext(), Update_order_Status.class);
//                            startActivity(intent);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Order_status_update_pojo> call, Throwable t) {
//
//                    }
//                });
            }
        });


        return view;
    }

//    public void onItemClick(String itemId) {
//        Log.d("Item ID", "Clicked item ID: " + itemId);
//    }

}