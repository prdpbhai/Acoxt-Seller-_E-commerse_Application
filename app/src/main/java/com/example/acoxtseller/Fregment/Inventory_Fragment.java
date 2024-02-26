package com.example.acoxtseller.Fregment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_Inventary;
import com.example.acoxtseller.Adapter.Adapter_category;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_horizental_item_pojo;
import com.example.acoxtseller.Model.Model_Category;
import com.example.acoxtseller.Model.Model_Inventary;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory_Fragment extends Fragment {
    RecyclerView inventary_recyclerview;
    TextView category_popup_down;
    View view;

    RelativeLayout fragment_call;
    TextView outOfStock;
    TextView all_stock,low_stock;
    String category_id;

    Acoxt_fregment_horizental_item_pojo.Datum pedp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_inventory_, container, false);

        category_popup_down = view.findViewById(R.id.category_popup_down);
        inventary_recyclerview=view.findViewById(R.id.inventary_recyclerview);
        all_stock=view.findViewById(R.id.all_stock);
        outOfStock=view.findViewById(R.id.outOfStock);
        low_stock=view.findViewById(R.id.low_stock);
        fragment_call=view.findViewById(R.id.fragment_call);


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(20);
        gradientDrawable.setColor(R.color.sky_blue);
        all_stock.setBackground(gradientDrawable);


        FragmentManager fragmentManager = getFragmentManager();
        All_Stock_Fragment all_Stock_Fragment = new All_Stock_Fragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_call, all_Stock_Fragment)
                .addToBackStack(null).commit();

        category_popup_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });


        all_stock.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(20);
                gradientDrawable.setColor(R.color.sky_blue);
                all_stock.setBackground(gradientDrawable);
//                all_stock.setBackgroundResource(R.color.skyblue);
                outOfStock.setBackgroundColor(Color.TRANSPARENT);
                low_stock.setBackgroundColor(Color.TRANSPARENT);
//                outOfStock.setBackground(null);


                FragmentManager fragmentManager = getFragmentManager();

                All_Stock_Fragment all_Stock_Fragment = new All_Stock_Fragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_call, all_Stock_Fragment)
                        .addToBackStack(null).commit();
            }
        });
        outOfStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradientDrawable.setCornerRadius(20);
                gradientDrawable.setColor(R.color.sky_blue);
                outOfStock.setBackground(gradientDrawable);
                all_stock.setBackground(null);
                low_stock.setBackground(null);

                FragmentManager fragmentManager = getFragmentManager();

                OutOfStock_Fragment outOfstock = new OutOfStock_Fragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_call,outOfstock )
                        .addToBackStack(null).commit();
            }
        });
        low_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gradientDrawable.setCornerRadius(20);
                gradientDrawable.setColor(R.color.sky_blue);
                low_stock.setBackground(gradientDrawable);
                all_stock.setBackground(null);
                outOfStock.setBackground(null);

                FragmentManager fragmentManager = getFragmentManager();

                Low_Stock_Fragment lowStock = new Low_Stock_Fragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_call,lowStock )
                        .addToBackStack(null).commit();
            }
        });




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
//
//
//
//        Adapter_Inventary mAdapter=new Adapter_Inventary(item_w1, view.getContext());
//        inventary_recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
//        inventary_recyclerview.setHasFixedSize(true);
//        inventary_recyclerview.setAdapter(mAdapter);
        return view;
    }


//    private void showPopupMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
//        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Toast.makeText(requireContext(), "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//
//        popupMenu.show();
//    }


//    private void showPopupMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
//        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = pref.edit();
//        String Token = pref.getString("token","");
//
//        Web_Service_login.getClient().acoxt_horzental_pojo("Bearer " +Token,"products/category").enqueue(new Callback<Acoxt_fregment_horizental_item_pojo>() {
//            @Override
//            public void onResponse(Call<Acoxt_fregment_horizental_item_pojo> call, Response<Acoxt_fregment_horizental_item_pojo> response) {
//
//                if (response.isSuccessful() && response.body() != null) {
//                    List<String> data = (List<String>) response.body().getData().get(popupMenu).getTitle();
//
//                    // Clear the existing menu items
//                    popupMenu.getMenu().clear();
//
//                    // Add the API data to the popup menu
//                    for (String item : data) {
//                        popupMenu.getMenu().add(item);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Acoxt_fregment_horizental_item_pojo> call, Throwable t) {
//
//            }
//        });
//
//
//    }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        SharedPreferences pref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String token = pref.getString("token", "");

        Web_Service_login.getClient().acoxt_horzental_pojo("Bearer " + token, "products/category").enqueue(new Callback<Acoxt_fregment_horizental_item_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_fregment_horizental_item_pojo> call, Response<Acoxt_fregment_horizental_item_pojo> response) {

                List<Acoxt_fregment_horizental_item_pojo.Datum> items = null;
                if (response.isSuccessful() && response.body() != null) {
                    items = response.body().getData();
//                    category_id=response.body().getData().get(popupMenu.getGravity()).getId();
//                    Log.d("fjfewejfiwjjowmdkej", ""+category_id.toString());

                    popupMenu.getMenu().clear();
                    int index = 0;


                    for (Acoxt_fregment_horizental_item_pojo.Datum item : items) {
//                        popupMenu.getMenu().add(Menu.NONE,Menu.FIRST,item.getTitle(),item.getId());

                            popupMenu.getMenu().add(Menu.NONE, index, 1, item.getTitle());
                            index++;
//                        category_id= popupMenu.getMenu().add(item.getId()).toString();
//                        Log.d("hgfyegfuyegfjhjhv", ""+popupMenu.getMenu().add(item.getTitle()));
//                        Log.d("gdjhfgajfgdj", ""+popupMenu.getMenu().add(item.getId()).toString());
                    }
                }
                List<Acoxt_fregment_horizental_item_pojo.Datum> finalItems = items;
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(requireContext(), "You Clicked "+ finalItems.get(menuItem.getItemId()).getId() + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                        category_id=finalItems.get(menuItem.getItemId()).getId();
                        Log.d("dbhfkeiuedhgdakghjasuiehdja", ""+finalItems.get(menuItem.getItemId()).getId());
                        Log.d("kkdfeddskmskmadwe", ""+category_id);

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("inventorycatid",category_id);
                        editor.apply();


                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<Acoxt_fregment_horizental_item_pojo> call, Throwable t) {

            }
        });


        popupMenu.show();
    }

}