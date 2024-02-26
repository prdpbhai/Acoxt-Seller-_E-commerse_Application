package com.example.acoxtseller.Fregment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acoxtseller.Adapter.Adapter_Inventary;
import com.example.acoxtseller.Adapter.Adapter_low_stock;
import com.example.acoxtseller.Adapter.Out_of_stock_Adapter;
import com.example.acoxtseller.Api_Pojo.Out_of_stock_pojo;
import com.example.acoxtseller.Model.Model_Inventary;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OutOfStock_Fragment extends Fragment {
    RecyclerView outStock_recycler;
    View view;
    Out_of_stock_Adapter out_of_stock_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_out_of_stock_, container, false);
        outStock_recycler=view.findViewById(R.id.outStock_recycler);

        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");

        Web_Service_login.getClient().out_of_stock_pojo("Bearer " +Token,"fixedSellerInventory","out-of-stock").enqueue(new Callback<Out_of_stock_pojo>() {
            @Override
            public void onResponse(Call<Out_of_stock_pojo> call, Response<Out_of_stock_pojo> response) {
                if (response.body().getCode()==200){
                    out_of_stock_adapter=new Out_of_stock_Adapter(response.body().getData(),view.getContext());
                    outStock_recycler.setHasFixedSize(true);
                    outStock_recycler.setAdapter(out_of_stock_adapter);
                    outStock_recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                }
            }

            @Override
            public void onFailure(Call<Out_of_stock_pojo> call, Throwable t) {

            }
        });

//        List<Model_Inventary> item_w1=new ArrayList<>();
//        item_w1.add(new Model_Inventary("Stellar","corner","30"));
//        item_w1.add(new Model_Inventary("Classic ","Paan corner","80"));
//        item_w1.add(new Model_Inventary("100% Juice","Juices","40"));
//        item_w1.add(new Model_Inventary("fruit","Juicesr","40"));
//        item_w1.add(new Model_Inventary("Lays","Munchies","50"));
//        item_w1.add(new Model_Inventary("Cigarette","Paan","30"));
//        item_w1.add(new Model_Inventary("Stellar","corner","20"));
//        item_w1.add(new Model_Inventary("Classic","Paan corner","40"));
//        item_w1.add(new Model_Inventary("Real active","Juices","70"));
//        item_w1.add(new Model_Inventary("Real fruit","Juicesr","30"));
//        item_w1.add(new Model_Inventary("Lays chips","Munchies","40"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));

//        Adapter_low_stock mAdapter=new Adapter_low_stock(item_w1, view.getContext());
//        outStock_recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
//        outStock_recycler.setHasFixedSize(true);
//        outStock_recycler.setAdapter(mAdapter);
        return view;
    }
}