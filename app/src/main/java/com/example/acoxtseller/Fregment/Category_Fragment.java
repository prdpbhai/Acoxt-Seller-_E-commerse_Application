package com.example.acoxtseller.Fregment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_acoxt_freg_horzen;
import com.example.acoxtseller.Adapter.Adapter_category;
import com.example.acoxtseller.Adapter.Adapter_recy_Acoxt;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_horizental_item_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Help_and_support;
import com.example.acoxtseller.Home_Product_Page;
import com.example.acoxtseller.Model.Model_Category;
import com.example.acoxtseller.Model.Model_recy_Acoxt;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Fragment extends Fragment {
    RecyclerView category_recyclerview;

    View view;
    Adapter_category adapter_category;
//    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_category_, container, false);

        view = inflater.inflate(R.layout.fragment_category_, container, false);
        category_recyclerview = view.findViewById(R.id.category_recyclerview);

//        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
//        shimmerFrameLayout.startShimmer();

//        List<Model_Category> item_w1 = new ArrayList<>();
//        item_w1.add(new Model_Category("Cold Drinks",R.drawable.fanta));
//        item_w1.add(new Model_Category("Sweet Cravings",R.drawable.chocolate));
//        item_w1.add(new Model_Category("Biscuits",R.drawable.biscuit));
//        item_w1.add(new Model_Category("Cold Drinks",R.drawable.fanta));
//        int numberOfColumns = 2;
//        Adapter_category mAdapter = new Adapter_category(item_w1, view.getContext());
//        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
//        recyclerView1.setHasFixedSize(true);
//        recyclerView1.setAdapter(mAdapter);

        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");
        Web_Service_login.getClient().acoxt_horzental_pojo("Bearer " +Token,"products/category").enqueue(new Callback<Acoxt_fregment_horizental_item_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_fregment_horizental_item_pojo> call, Response<Acoxt_fregment_horizental_item_pojo> response) {
                if (response.body()!=null){
//                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    adapter_category=new Adapter_category(response.body().getData(),view.getContext());
                    category_recyclerview.setHasFixedSize(true);
                    category_recyclerview.setLayoutManager(new GridLayoutManager(view.getContext(),2));
                    category_recyclerview.setAdapter(adapter_category);
                    Log.d("sdfghjfghj", ""+response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Acoxt_fregment_horizental_item_pojo> call, Throwable t) {


            }
        });


//        mAdapter = new Adapter_category(item_w1, new Adapter_category.OnItemClickListener() {
//            @Override
//            public void onItemClick(Model_Category item) {
//                openNextActivity(item);
//
//            }
//        });


//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView1.setLayoutManager(layoutManager);
//
//        Adapter_category adapter = new Adapter_category(item_w1,view.getContext());
//        adapter.setOnItemClickListener(new Adapter_category.OnItemClickListener() {
//            @Override
//            public void onItemClick(Model_Category item) {
//                Intent intent = new Intent(getActivity(), Help_and_support.class);
//                startActivity(intent);
//            }
//        });
//        recyclerView1.setAdapter(adapter);

        return view;
    }

    private void openNextActivity(Model_Category item) {
        Intent intent = new Intent(requireContext(), Help_and_support.class);
        intent.putExtra("item_title", item.getCategory_name());
        startActivity(intent);
    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        shimmerFrameLayout.stopShimmer();
//    }


    @Override
    public void onResume() {
        super.onResume();

        // Handle the back press event
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate to Home_Product_Page activity
                startActivity(new Intent(requireContext(), Home_Product_Page.class));
                requireActivity().finish();
            }
        });
    }
}