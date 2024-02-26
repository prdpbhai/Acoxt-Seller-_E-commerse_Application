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
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_Inventary;
import com.example.acoxtseller.Api_Pojo.Inventory_show_pojo;
import com.example.acoxtseller.Model.Model_Inventary;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class All_Stock_Fragment extends Fragment {
    View view;
    RecyclerView inventary_recyclerview;
    Adapter_Inventary adapter_inventary;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_all__stock_, container, false);
        inventary_recyclerview=view.findViewById(R.id.inventary_recyclerview);

        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");
        String category_id=pref.getString("inventorycatid","");
        Log.d("ohhbftycvhnoiim", ""+category_id);

//        List<Model_Inventary> item_w1=new ArrayList<>();
//        item_w1.add(new Model_Inventary("Stellar Define","Paan corner","40"));
//        item_w1.add(new Model_Inventary("Classic Iceburst","Paan corner","60"));
//        item_w1.add(new Model_Inventary("100% Juice","Juices","70"));
//        item_w1.add(new Model_Inventary("fruit Juice","Juicesr","30"));
//        item_w1.add(new Model_Inventary("Lays chips","Munchies","40"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));
//        item_w1.add(new Model_Inventary("Stellar Define","Paan corner","40"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));
//        item_w1.add(new Model_Inventary("Real active","Juices","70"));
//        item_w1.add(new Model_Inventary("Real fruit","Juicesr","30"));
//        item_w1.add(new Model_Inventary("Lays chips","Munchies","40"));
//        item_w1.add(new Model_Inventary("Classic Cigarette","Paan corner","60"));
//
//        Adapter_Inventary mAdapter=new Adapter_Inventary(item_w1, view.getContext());
//        inventary_recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
//        inventary_recyclerview.setHasFixedSize(true);
//        inventary_recyclerview.setAdapter(mAdapter);

        Web_Service_login.getClient().inventory_show_pojo("Bearer " +Token,"fixedSellerInventory").enqueue(new Callback<Inventory_show_pojo>() {
            @Override
            public void onResponse(Call<Inventory_show_pojo> call, Response<Inventory_show_pojo> response) {
                if (response.body().getCode()==200){
                    adapter_inventary= new Adapter_Inventary(response.body().getData(),view.getContext());
                    inventary_recyclerview.setHasFixedSize(true);
                    inventary_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                    inventary_recyclerview.setAdapter(adapter_inventary);
                }else {
                    Toast.makeText(getContext(), "Server Problem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inventory_show_pojo> call, Throwable t) {

            }
        });

        return view;
    }
}