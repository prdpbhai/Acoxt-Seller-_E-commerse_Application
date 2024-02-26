package com.example.acoxtseller.Fregment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acoxtseller.Adapter.Adapter_Inventary;
import com.example.acoxtseller.Adapter.Adapter_low_stock;
import com.example.acoxtseller.Api_Pojo.Low_Stock_Pojo;
import com.example.acoxtseller.Model.Model_Inventary;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Low_Stock_Fragment extends Fragment {
    RecyclerView lowStock_recy;
    View view;
    Adapter_low_stock adapter_low_stock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_low__stock_, container, false);
        lowStock_recy=view.findViewById(R.id.lowStock_recy);

        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");

        Web_Service_login.getClient().low_stock_pojo("Bearer " +Token,"fixedSellerInventory","low-stock").enqueue(new Callback<Low_Stock_Pojo>() {
            @Override
            public void onResponse(Call<Low_Stock_Pojo> call, Response<Low_Stock_Pojo> response) {
                if (response.body().getCode()==200){
                    adapter_low_stock=new Adapter_low_stock(response.body().getData(),getContext());
                    lowStock_recy.setHasFixedSize(true);
                    lowStock_recy.setLayoutManager(new LinearLayoutManager(getContext()));
                    lowStock_recy.setAdapter(adapter_low_stock);
                }
            }

            @Override
            public void onFailure(Call<Low_Stock_Pojo> call, Throwable t) {

            }
        });

//        List<Model_Inventary> item_w1=new ArrayList<>();
//        item_w1.add(new Model_Inventary(" Define","corner","20"));
//        item_w1.add(new Model_Inventary("Iceburst","corner","50"));
//        item_w1.add(new Model_Inventary("Juice","Juices","20"));
//        item_w1.add(new Model_Inventary("fruit","Juicesr","70"));
//        item_w1.add(new Model_Inventary("chips","Munchies","10"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));
//        item_w1.add(new Model_Inventary(" Define","corner","20"));
//        item_w1.add(new Model_Inventary("Iceburst","corner","50"));
//        item_w1.add(new Model_Inventary("Juice","Juices","20"));
//        item_w1.add(new Model_Inventary("fruit","Juicesr","70"));
//        item_w1.add(new Model_Inventary("chips","Munchies","10"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));
//        item_w1.add(new Model_Inventary(" Define","corner","20"));
//        item_w1.add(new Model_Inventary("Iceburst","corner","50"));
//        item_w1.add(new Model_Inventary("Juice","Juices","20"));
//        item_w1.add(new Model_Inventary("fruit","Juicesr","70"));
//        item_w1.add(new Model_Inventary("chips","Munchies","10"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));
//
//        Adapter_low_stock mAdapter=new Adapter_low_stock(item_w1, view.getContext());
//        lowStock_recy.setLayoutManager(new LinearLayoutManager(requireContext()));
//        lowStock_recy.setHasFixedSize(true);
//        lowStock_recy.setAdapter(mAdapter);

        return view;
    }
}