package com.example.acoxtseller.Fregment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.acoxtseller.Adapter.Adapter_Acoxt_Discount;
import com.example.acoxtseller.Adapter.Adapter_Recomended_Product;
import com.example.acoxtseller.Adapter.Adapter_Search;
import com.example.acoxtseller.Adapter.Adapter_acoxt_freg_horzen;
import com.example.acoxtseller.Adapter.Adapter_recy_Acoxt;
import com.example.acoxtseller.Api_Pojo.Acoxt_banner_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_freg_offer_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_horizental_item_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Search_pojo;
import com.example.acoxtseller.Apply_coupon;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.Enter_Address;
import com.example.acoxtseller.Help_and_support;
import com.example.acoxtseller.Language_Activity;
import com.example.acoxtseller.Location_Access_Activity;
import com.example.acoxtseller.Login;
import com.example.acoxtseller.Map_Activity;
import com.example.acoxtseller.MyOrder_Activity;
import com.example.acoxtseller.Notification_Activity;
import com.example.acoxtseller.Order_manage;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Scanner_Activity;
import com.example.acoxtseller.Show_Banner_Activity;
import com.example.acoxtseller.Show_product_Activity;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Acoxt_Fragment extends Fragment  {
    RecyclerView recyclerView,recyclerview_horizental,offer_rec,recomrnded_recy;
    TextView current_location,using_gps,order_manage,show_all ;

    LinearLayout scannercheak,linear;
    View view;
    int val1=0;
    Adapter_Search adapter_search;
    DrawerLayout layoutdrawer;
    DB_Helper dbHelper;
    TextView logout,username;
    ImageView search;
    ImageView sidebar,search_icon;
    Adapter_recy_Acoxt adapter_recy_acoxt;
    Adapter_Acoxt_Discount adapter_acoxt_discount;
    Adapter_Recomended_Product adapter_recomended_product;
    Adapter_acoxt_freg_horzen adapter_acoxt_freg_horzen;
    ImageSlider image_slider,image_slider2;
    RelativeLayout relative_main,search_box;
    TextView myyorder,faq,user_profile,user_notification,coupon,user_activity,user_payment,user_address,user_Language,qr_code;


    private ShimmerFrameLayout shimmerFrameLayout;

//    public DrawerLayout drawerLayout;
//    public ActionBarDrawerToggle actionBarDrawerToggle;


    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_acoxt_, container, false);


        layoutdrawer=view.findViewById(R.id.drawer_layout);
        sidebar=view.findViewById(R.id.setting_menu);
        myyorder=view.findViewById(R.id.myyorder);
        faq=view.findViewById(R.id.faq);
        user_profile=view.findViewById(R.id.user_profile);
        user_notification=view.findViewById(R.id.user_notification);
//        user_activity=view.findViewById(R.id.user_activity);
        user_payment=view.findViewById(R.id.user_payment);
        user_address=view.findViewById(R.id.user_address);
        user_Language=view.findViewById(R.id.user_Language);
        qr_code=view.findViewById(R.id.qr_code);
        current_location=view.findViewById(R.id.current_location);
        logout=view.findViewById(R.id.logout);
        scannercheak=view.findViewById(R.id.scannercheak);
        TextView location_show = view.findViewById(R.id.location_show);
        image_slider=view.findViewById(R.id.image_slider);
        image_slider2=view.findViewById(R.id.image_slider2);
        using_gps=view.findViewById(R.id.using_gps);
        relative_main= view.findViewById(R.id.relative_main);
        search_box=view.findViewById(R.id.search_box);
        linear=view.findViewById(R.id.linear);
        order_manage=view.findViewById(R.id.order_manage);
        offer_rec=view.findViewById(R.id.offer_rec);
        recomrnded_recy=view.findViewById(R.id.recomrnded_recy);
        show_all=view.findViewById(R.id.show_all);
//        coupon=view.findViewById(R.id.coupon);

        username=view.findViewById(R.id.username);
        search=view.findViewById(R.id.search);


//        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
//        shimmerFrameLayout.startShimmer();


        silderimge1();
        silderimge2();



//        ArrayList<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel(R.drawable.slider_card,null));
//        slideModels.add(new SlideModel(R.drawable.slider_card, null));
//        slideModels.add(new SlideModel(R.drawable.slider_card, null));
//        image_slider.setImageList(slideModels, ScaleTypes.FIT);


//        ArrayList<SlideModel> slideModel1 = new ArrayList<>();
//        slideModel1.add(new SlideModel(R.drawable.slider_card,null));
//        slideModel1.add(new SlideModel(R.drawable.slider_card, null));
//        slideModel1.add(new SlideModel(R.drawable.slider_card, null));
//        image_slider2.setImageList(slideModel1, ScaleTypes.FIT);

        SharedPreferences pref=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = pref.edit();
        String Token = pref.getString("token","");
        String name=pref.getString("usernamee","");
        Log.d("hsbderrbfkdjn", ""+name);

        username.setText(name);

//        Toast.makeText(getActivity(), ""+pref.getString("user",""), Toast.LENGTH_SHORT).show();


        location_show.setText(pref.getString("location", ""));
//        Toast.makeText(getContext(), ""+location_show, Toast.LENGTH_SHORT).show();
        Log.d("dfghjkjhgfdfg", ""+location_show);

//        using_gps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Show_product_Activity.class);
                startActivity(intent);
            }
        });



//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String searchText = s.toString();
////                String searchText = search.getText().toString();
//                if (!TextUtils.isEmpty(searchText)) {
//                    performSearch(searchText);
//                } else {
//                    Web_Service_login.getClient().acoxt_fregment_pojo("Bearer " + Token, "product").enqueue(new Callback<Acoxt_fregment_vertical_item_pojo>() {
//                        @Override
//                        public void onResponse(Call<Acoxt_fregment_vertical_item_pojo> call, Response<Acoxt_fregment_vertical_item_pojo> response) {
//                            if (response.body() != null) {
//                                adapter_recy_acoxt = new Adapter_recy_Acoxt(response.body().getData(), view.getContext(), dbHelper);
//                                recyclerView.setHasFixedSize(true);
//                                recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
//                                recyclerView.setAdapter(adapter_recy_acoxt);
//
//                                recomrnded_recy.setHasFixedSize(true);
//                                recomrnded_recy.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
//                                recomrnded_recy.setAdapter(adapter_recomended_product);
////                                shimmer_view_container.hideShimmer();
////                                shimmer_view_container.setVisibility(View.GONE);
////                                main_ui.setVisibility(View.VISIBLE);
//                                Log.d("Sfsdgf", "" + response.body().getData());
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Acoxt_fregment_vertical_item_pojo> call, Throwable t) {
//
//                        }
//                    });
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


        show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), Show_product_Activity.class);
                startActivity(intent);
            }
        });

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Map_Activity.class);
                startActivity(intent);
            }
        });

        relative_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("token");
                editor.apply();
                Intent intent=new Intent(getContext(),Login.class);
                startActivity(intent);
            }
        });


        sidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)                                                                                                                      {
                layoutdrawer.openDrawer(GravityCompat.START);
            }
        });

        myyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(view.getContext(), MyOrder_Activity.class);
                startActivity(intent);
                layoutdrawer.closeDrawer(GravityCompat.START);

            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(view.getContext(), Help_and_support.class);
                startActivity(intent);
                layoutdrawer.closeDrawer(GravityCompat.START);
            }
        });

        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(view.getContext(), Home_Product_Page.class);
//                startActivity(intent);
//                layoutdrawer.closeDrawer(GravityCompat.START);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, new Profile_Fragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        user_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(view.getContext(), Notification_Activity.class);
                startActivity(intent);
                layoutdrawer.closeDrawer(GravityCompat.START);
            }
        });
        order_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(view.getContext(), Order_manage.class);
                startActivity(intent);
                layoutdrawer.closeDrawer(GravityCompat.START);
            }
        });
//        user_payment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(view.getContext(), Payment_Method_Activity.class);
//                startActivity(intent);
//                layoutdrawer.closeDrawer(GravityCompat.START);
//            }
//        });
//        coupon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext(), Apply_coupon.class);
//                startActivity(intent);
//                layoutdrawer.closeDrawer(GravityCompat.START);
//            }
//        });

        user_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), Enter_Address.class);
                startActivity(intent);
                layoutdrawer.closeDrawer(GravityCompat.START);
            }
        });

        user_Language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), Language_Activity.class);
                startActivity(intent);
                layoutdrawer.closeDrawer(GravityCompat.START);
            }
        });

        SharedPreferences prefw=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        if(prefw.getString("user","").equals("fixed_seller")){
            Log.d("gfdsdfghjhgfyuj", ""+prefw);
        }
        else {
            scannercheak.setVisibility(View.VISIBLE);
        }
        Log.d("fghjkgnm", ""+prefw.getString("user",""));
        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Scanner_Activity.class);
                startActivity(intent);
            }
        });


        recyclerView=view.findViewById(R.id.recyclerview);


        Web_Service_login.getClient().acoxt_fregment_pojo("Bearer " +Token,"product").enqueue(new Callback<Acoxt_fregment_vertical_item_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_fregment_vertical_item_pojo> call, Response<Acoxt_fregment_vertical_item_pojo> response) {
                Log.e("ddgfgdgdgl",""+response.raw().code());
                if (response.body()!=null){

                    adapter_recy_acoxt=new Adapter_recy_Acoxt(response.body().getData(),view.getContext(), dbHelper);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
                    recyclerView.setAdapter(adapter_recy_acoxt);

                    Log.e("dffrfj",""+response.body().getData());
                }else {
                    Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Acoxt_fregment_vertical_item_pojo> call, Throwable t) {

            }


        });


        Web_Service_login.getClient().acoxt_freg_offer_pojo("offeredProduct").enqueue(new Callback<Acoxt_freg_offer_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_freg_offer_pojo> call, Response<Acoxt_freg_offer_pojo> response) {
                if (response.body().getCode()==200){
                    Log.d("ksokdeinc", ""+response.body());
                    adapter_acoxt_discount=new Adapter_Acoxt_Discount(response.body().getData(),getContext());
                    offer_rec.setHasFixedSize(true);
                    offer_rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                    offer_rec.setAdapter(adapter_acoxt_discount);
                }else{
                    Toast.makeText(getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Acoxt_freg_offer_pojo> call, Throwable t) {

            }

        });


//        List<Model_acoxt_freg_horzen> item_w1=new ArrayList<>();
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//        item_w1.add(new Model_acoxt_freg_horzen("Stellar Define"));
//
//        Adapter_Recomended_Product adapter = new Adapter_Recomended_Product(item_w1,getContext());
//        recomrnded_recy.setHasFixedSize(true);
//        recomrnded_recy.setLayoutManager(new GridLayoutManager(view.getContext(), 1, RecyclerView.VERTICAL, false));
//        recomrnded_recy.setAdapter(adapter);
//
//        recomrnded_recy.setHasFixedSize(true);
//        recomrnded_recy.setLayoutManager(new GridLayoutManager(view.getContext(), 2, RecyclerView.HORIZONTAL, false));
//        recomrnded_recy.setAdapter(adapter);


        Web_Service_login.getClient().acoxt_fregment_pojo("Bearer " +Token,"product").enqueue(new Callback<Acoxt_fregment_vertical_item_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_fregment_vertical_item_pojo> call, Response<Acoxt_fregment_vertical_item_pojo> response) {
                Log.e("ddgfgdgdgl",""+response.raw().code());
                if (response.body()!=null){

//                    adapter_recomended_product=new Adapter_Recomended_Product(response.body().getData(),view.getContext());
//                    recomrnded_recy.setHasFixedSize(true);
//                    recomrnded_recy.setLayoutManager(new GridLayoutManager(view.getContext(),2));
//                    recomrnded_recy.setAdapter(adapter_recomended_product);

                    Adapter_Recomended_Product adapter = new Adapter_Recomended_Product(response.body().getData(),getContext());
                    recomrnded_recy.setHasFixedSize(true);
                    recomrnded_recy.setLayoutManager(new GridLayoutManager(view.getContext(), 1, RecyclerView.VERTICAL, false));
                    recomrnded_recy.setAdapter(adapter);

                    recomrnded_recy.setHasFixedSize(true);
                    recomrnded_recy.setLayoutManager(new GridLayoutManager(view.getContext(), 2, RecyclerView.HORIZONTAL, false));
                    recomrnded_recy.setAdapter(adapter);


                    Log.e("dffrfj",""+response.body().getData());
                }else {
                    Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Acoxt_fregment_vertical_item_pojo> call, Throwable t) {

            }


        });


        recyclerview_horizental=view.findViewById(R.id.recyclerview_horizental);
        Web_Service_login.getClient().acoxt_horzental_pojo("Bearer " +Token,"products/category").enqueue(new Callback<Acoxt_fregment_horizental_item_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_fregment_horizental_item_pojo> call, Response<Acoxt_fregment_horizental_item_pojo> response) {
                if (response.raw().code()==200){
                    adapter_acoxt_freg_horzen=new Adapter_acoxt_freg_horzen(response.body().getData(),view.getContext());
                    recyclerview_horizental.setHasFixedSize(true);
                    recyclerview_horizental.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerview_horizental.setAdapter(adapter_acoxt_freg_horzen);
                    Log.d("ghjkjhghjhjm", ""+response.body().getData());
                }else{
                    Toast.makeText(getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Acoxt_fregment_horizental_item_pojo> call, Throwable t) {

            }


        });



        location_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningupdate();

            }
        });

        return view;


    }


    void runningupdate(){

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_location_select);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.slide_in_bottom;
        LinearLayout current_location=dialog.findViewById(R.id.current_location);



        current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent=new Intent(getContext(),Location_Access_Activity.class);
                startActivity(intent);


            }
        });
        dialog.show();

    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        shimmerFrameLayout.stopShimmer();
//    }


    public void silderimge1() {
        ArrayList<SlideModel> slideModels1 = new ArrayList<>();
        List<ProductInfo> productInfos1 = new ArrayList<>(); // Using a list to store product info
        Web_Service_login.getClient().acoxt_banner_pojo("customer_banner").enqueue(new Callback<Acoxt_banner_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_banner_pojo> call, Response<Acoxt_banner_pojo> response) {
                if (response.isSuccessful()) {
                    List<Acoxt_banner_pojo.Root> sliderData = response.body().getData().get(0).getRoot();

                    for (int i = 0; i < sliderData.size(); i++) {
                        String imageUrl = "https://api.acoxt.fourbrick.in/" + sliderData.get(i).getImg().toString();
                        String productId = sliderData.get(i).getId();
                        String productDiscount = sliderData.get(i).getDiscount();
                        slideModels1.add(new SlideModel(imageUrl, null));
                        ProductInfo productInfo = new ProductInfo(productId, productDiscount);
                        productInfos1.add(productInfo);

                    }

                    image_slider.setImageList(slideModels1, ScaleTypes.FIT);


                    image_slider.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemSelected(int position) {

                            ProductInfo productInfo = productInfos1.get(position);
                            String productId = productInfo.getProductId();
                            Log.d("jnunufbhsbdfhsdf", ""+productId);
                            String productDiscount = productInfo.getProductDiscount();
                            Intent intent = new Intent(view.getContext(), Show_Banner_Activity.class);
                            intent.putExtra("product_type", "banner");
                            intent.putExtra("productId", productId);
                            Log.d("lljniernnsdnfnv", ""+productId);
                            intent.putExtra("product_dis", productDiscount);
//                            Toast.makeText(getContext(), "Szdxfcgvhbjnkm", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

//                            Toast.makeText(getContext(), "wdefrtdyuio"+productInfo.productDiscount, Toast.LENGTH_SHORT).show();

                        }
                    });



                }
            }

            @Override
            public void onFailure(Call<Acoxt_banner_pojo> call, Throwable t) {
                // Handle the failure case if necessary
            }
        });
    }
    public void silderimge2() {
        ArrayList<SlideModel> slideModels1 = new ArrayList<>();
        List<ProductInfo> productInfos1 = new ArrayList<>(); // Using a list to store product info
        Web_Service_login.getClient().acoxt_banner_pojo("customer_banner").enqueue(new Callback<Acoxt_banner_pojo>() {
            @Override
            public void onResponse(Call<Acoxt_banner_pojo> call, Response<Acoxt_banner_pojo> response) {
                if (response.isSuccessful()) {
                    List<Acoxt_banner_pojo.Root> sliderData = response.body().getData().get(0).getRoot();

                    for (int i = 0; i < sliderData.size(); i++) {
                        String imageUrl = "https://api.acoxt.fourbrick.in/" + sliderData.get(i).getImg().toString();
                        String productId = sliderData.get(i).getId();
                        String productDiscount = sliderData.get(i).getDiscount();
                        slideModels1.add(new SlideModel(imageUrl, null));
                        ProductInfo productInfo = new ProductInfo(productId, productDiscount);
                        productInfos1.add(productInfo);

                    }

                    image_slider2.setImageList(slideModels1, ScaleTypes.FIT);


                    image_slider2.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemSelected(int position) {

                            ProductInfo productInfo = productInfos1.get(position);
                            String productId = productInfo.getProductId();
                            Log.d("nxbcbsdchhskdj", ""+productId);
                            String productDiscount = productInfo.getProductDiscount();
                            Intent intent = new Intent(view.getContext(), Show_Banner_Activity.class);
                            intent.putExtra("product_type", "banner");
                            intent.putExtra("productId", productId);
                            intent.putExtra("product_dis", productDiscount);
//                            Toast.makeText(getContext(), "Szdxfcgvhbjnkm", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

//                            Toast.makeText(getContext(), "wdefrtdyuio"+productInfo.productDiscount, Toast.LENGTH_SHORT).show();

                        }
                    });



                }
            }

            @Override
            public void onFailure(Call<Acoxt_banner_pojo> call, Throwable t) {
                // Handle the failure case if necessary
            }
        });
    }

    private void performSearch(String searchText) {

        SharedPreferences pref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
        Web_Service_login.getClient().search_pojo( "search", searchText).enqueue(new Callback<Search_pojo>() {
            @Override
            public void onResponse(Call<Search_pojo> call, Response<Search_pojo> response) {
                if (response.body()!=null) {
                    adapter_search = new Adapter_Search(response.body().getData(), view.getContext(), dbHelper);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
                    recyclerView.setAdapter(adapter_search);
                }
            }

            @Override
            public void onFailure(Call<Search_pojo> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public class ProductInfo {
        private String productId;
        private String productDiscount;

        public ProductInfo(String productId, String productDiscount) {
            this.productId = productId;
            this.productDiscount = productDiscount;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductDiscount() {
            return productDiscount;
        }
    }

}
