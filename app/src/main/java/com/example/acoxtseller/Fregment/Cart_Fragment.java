        package com.example.acoxtseller.Fregment;

        import android.annotation.SuppressLint;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.media.session.MediaSession;
        import android.os.Bundle;

        import androidx.activity.OnBackPressedCallback;
        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Handler;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.acoxtseller.Adapter.Adapter_Inventary;
        import com.example.acoxtseller.Adapter.Adapter_ListView_Cart;
        import com.example.acoxtseller.Adapter.CartAdapter;
        import com.example.acoxtseller.Api_Pojo.Cart3_POJO;
        import com.example.acoxtseller.Api_Pojo.Cart_data_show_pojo;
        import com.example.acoxtseller.Api_Pojo.Checkout_pojo;
        import com.example.acoxtseller.DB_Helper;
        import com.example.acoxtseller.Home_Product_Page;
        import com.example.acoxtseller.Model.Model_ListView_Cart;
        import com.example.acoxtseller.Model.Model_checkout_recyclerview;
        import com.example.acoxtseller.R;
        import com.example.acoxtseller.Web_Service.Web_Service_login;
        import com.google.android.material.badge.BadgeDrawable;
        import com.google.android.material.bottomnavigation.BottomNavigationView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

        import okhttp3.MediaType;
        import okhttp3.RequestBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        public class Cart_Fragment extends Fragment implements CartAdapter.OnItemClickListener {
            RecyclerView recycler_cartview;
            Adapter_ListView_Cart adapter_listView_cart;
            RelativeLayout checkout, relative_total_amount, cart_lay;
            TextView total_Amount, sub_total;
            private CartDataListener dataListener;
            CartAdapter cartAdapter;
            FrameLayout framlaout;

            int count_item;
            BottomNavigationView bottomNavigationView;
            TextView total;
            ImageView empty_cart;
            List<Cart3_POJO> itemList;
            Button done_btn;
            DB_Helper dbHelper;

            @SuppressLint("MissingInflatedId")
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View rootView = inflater.inflate(R.layout.fragment_cart_, container, false);

                dbHelper = new DB_Helper(getContext());
                itemList = new ArrayList<>();


                recycler_cartview = rootView.findViewById(R.id.recycler_cartview);
                checkout = rootView.findViewById(R.id.checkout);
                total = rootView.findViewById(R.id.total);
                total_Amount = rootView.findViewById(R.id.total_Amount);
                sub_total = rootView.findViewById(R.id.sub_total);
                relative_total_amount = rootView.findViewById(R.id.relative_total_amount);
                empty_cart = rootView.findViewById(R.id.empty_cart);
                framlaout = rootView.findViewById(R.id.framlaout);
                cart_lay = rootView.findViewById(R.id.cart_lay);
                bottomNavigationView = rootView.findViewById(R.id.bottomNavigationView);


                SharedPreferences pref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = pref.edit();
                String Token = pref.getString("token", "");

                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.dialog_order_confirm);


                //        checkout.setOnClickListener(new View.OnClickListener() {
                //            @Override
                //            public void onClick(View v) {
                //                DB_Helper dbHelper = new DB_Helper(getContext());
                //                String tableName = "products";
                //                dbHelper.deleteAllData(tableName);
                //                dbHelper.close();
                //                showCustomDialog();
                //
                //                Toast.makeText(getContext(), "Checkout", Toast.LENGTH_SHORT).show();
                //
                //
                ////                Web_Service_login.getClient().checkout_pojo("Bearer " +Token).enqueue(new Callback<Checkout_pojo>() {
                ////                    @Override
                ////                    public void onResponse(Call<Checkout_pojo> call, Response<Checkout_pojo> response) {
                ////                        if (response.body()!=null){
                ////                            Toast.makeText(getContext(), "Checkout", Toast.LENGTH_SHORT).show();
                ////                            showCustomDialog();
                ////                        }
                ////                    }
                ////
                ////                    @Override
                ////                    public void onFailure(Call<Checkout_pojo> call, Throwable t) {
                ////
                ////                    }
                ////                });
                //            }
                //        });

                checkout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            Cursor cursor = dbHelper.getAllData();
                            if (cursor.getCount() == 0) {
                                Toast.makeText(getActivity(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                            } else {
                                while (cursor.moveToNext()) {

                                }

                                JSONArray jsonArray = new JSONArray();

                                cursor.moveToFirst(); // Move the cursor back to the first position

                                do {
                                    String Title1 = cursor.getString(0);
                                    int quantity1 = Integer.parseInt(cursor.getString(6));

                                    try {
                                        JSONObject productObject = new JSONObject();
                                        productObject.put("title", Title1);
                                        productObject.put("quantity", quantity1);

                                        jsonArray.put(productObject);

                                        JSONObject requestData = new JSONObject();
                                        JSONArray dataArray = new JSONArray();
                                        JSONObject itemData = new JSONObject();
                                        itemData.put("title", Title1);
                                        itemData.put("quantity", quantity1);
                                        dataArray.put(itemData);
                                        requestData.put("data", dataArray);

                                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());

                                        Web_Service_login.getClient().checkout_pojo("Bearer " + Token, requestBody).enqueue(new Callback<Checkout_pojo>() {
                                            @Override
                                            public void onResponse(Call<Checkout_pojo> call, Response<Checkout_pojo> response) {
                                                if (response.isSuccessful()) {
//                                                    Toast.makeText(getContext(), "Add sucessfullu", Toast.LENGTH_SHORT).show();
                                                    DB_Helper dbHelper = new DB_Helper(getContext());
                                                    String tableName = "products";
                                                    dbHelper.deleteAllData(tableName);
                                                    dbHelper.close();
                                                    showCustomDialog();

                                                    //                                            get_location();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Checkout_pojo> call, Throwable t) {
                                                // Handle the failure
                                            }
                                        });


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } while (cursor.moveToNext());

                            }

                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


                //        dbHelper = new DB_Helper(getContext()); // Initialize your database helper
                //
                //        adapter_listView_cart = new Adapter_ListView_Cart(itemList, getContext(), dbHelper);
                //        recyclerView.setAdapter(adapter);

                cartAdapter = new CartAdapter(itemList, rootView.getContext(), dbHelper, itemClickListener);
                recycler_cartview.setHasFixedSize(true);
                recycler_cartview.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                recycler_cartview.setAdapter(cartAdapter);
                displaydata();
                count_item = cartAdapter.getItemCount();
                myEdit.putString("carditemcount", String.valueOf(count_item));
                myEdit.apply();
                Log.d("jshicusfnucn", "" + count_item);
                sendDataToTargetComponent();

                recycler_cartview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                       try {
                           Cart_Fragment cart_fragment = new Cart_Fragment();
//                        Toast.makeText(getContext(), "finish", Toast.LENGTH_SHORT).show();
                           FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                           transaction.replace(R.id.cart_lay, cart_fragment);
                           transaction.addToBackStack(null);
                           transaction.commit();

                       }catch (Exception e1){
                           e1.printStackTrace();
                           Log.d("jbhgcfxyvjhuy", ""+e1);
                       }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                        // Handle touch events if needed
                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                        // Handle disallow intercept events if needed
                    }
                });


//                Cart_Fragment cart_fragment = new Cart_Fragment();

//                framlaout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                        transaction.replace(R.id.cart_lay, cart_fragment);
//                        transaction.addToBackStack(null); // Optional: Add the transaction to the back stack
//                        transaction.commit();
//
//                    }
//                });


                //        Web_Service_login.getClient().cart_data_pojo("Bearer " +Token,"addtocard").enqueue(new Callback<Cart_data_show_pojo>() {
                //            @Override
                //            public void onResponse(Call<Cart_data_show_pojo> call, Response<Cart_data_show_pojo> response) {
                //                if (response.body().getData().isEmpty()){
                ////                    Toast.makeText(getContext(), "pass", Toast.LENGTH_SHORT).show();
                //                    relative_total_amount.setVisibility(View.GONE);
                //                    empty_cart.setVisibility(View.VISIBLE);
                //                }else {
                //                    adapter_listView_cart=new Adapter_ListView_Cart(response.body().getData(),getContext());
                //                    recycler_cartview.setLayoutManager(new LinearLayoutManager(requireContext()));
                //                    recycler_cartview.setHasFixedSize(true);
                //                    recycler_cartview.setAdapter(adapter_listView_cart);
                //                    total.setText(String.valueOf(response.body().getTotal()));
                //                    total_Amount.setText(String.valueOf(response.body().getTotal()));
                //                    sub_total.setText(String.valueOf(response.body().getTotal()));
                //                }
                //            }
                //
                //            @Override
                //            public void onFailure(Call<Cart_data_show_pojo> call, Throwable t) {
                //
                //            }
                //        });


                //        List<Model_checkout_recyclerview>model_checkout_recyclerviews=new ArrayList<>();
                //        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
                //        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
                //        model_checkout_recyclerviews.add(new Model_checkout_recyclerview("500g","50","chips",R.drawable.chips1));
                //        Adapter_ListView_Cart mAdapter=new Adapter_ListView_Cart(model_checkout_recyclerviews,rootView.getContext());
                //        recycler_cartview.setLayoutManager(new LinearLayoutManager(requireContext()));
                //        recycler_cartview.setHasFixedSize(true);
                //        recycler_cartview.setAdapter(mAdapter);


                return rootView;

            }

            private void showCustomDialog() {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.dialog_order_confirm);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.slide_in_bottom;
                Button done_button = dialog.findViewById(R.id.done_btn);

                done_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Home_Product_Page.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }

            private void setCheckout() {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.dialog_order_confirm);
                dialog.dismiss();
                Intent intent = new Intent(getContext(), Home_Product_Page.class);
                startActivity(intent);
            }


            private void displaydata() {
                List<Double> totalPricesList = new ArrayList<>(); // Create a list to store total prices
                Cursor cursor = dbHelper.getAllData();
                if (cursor.getCount() == 0) {
                    //            noitem_cart.setVisibility(View.VISIBLE);
                    //            all_data.setVisibility(View.GONE);


                } else {

                    double[] totalPricesArray = new double[cursor.getCount()]; // Create an array to store total prices

                    int index = 0; // Initialize the index for the array
                    double totalSum = 0.0; // Initialize the total sum

                    while (cursor.moveToNext()) {
                        String productid = cursor.getString(0);
                        String Title = cursor.getString(1);
                        double price = Double.parseDouble(cursor.getString(2));
                        double discountprice = Double.parseDouble(cursor.getString(3));
                        String img = cursor.getString(4);
                        String discription = cursor.getString(5);
                        int quantity = Integer.parseInt(cursor.getString(6));

                        double total_price = price * quantity;

                        totalPricesList.add(total_price);


                        Log.d("adsedfghj", "" + totalPricesList);
                        totalSum += total_price;


                        totalPricesArray[index] = total_price;
                        index++;

                        Log.d("Total Prices Array", Arrays.toString(totalPricesArray));
                        Log.d("Total Sum", String.valueOf(totalSum));


                        //                cartTotalPrice += total_price;
                        total.setText(String.valueOf(totalSum));
                        total_Amount.setText(String.valueOf(totalSum));
                        sub_total.setText(String.valueOf(totalSum));
                        total_Amount.setText(String.valueOf(totalSum));


                        Cart3_POJO item = new Cart3_POJO(productid, Title, price, discountprice, img, discription, String.valueOf(quantity));
                        itemList.add(item);
                        //                generateAndDisplayReceipt();
                    }

                    JSONArray jsonArray = new JSONArray();

                    cursor.moveToFirst();

                    do {
                        String Title1 = cursor.getString(0);
                        int quantity1 = Integer.parseInt(cursor.getString(6));

                        try {
                            JSONObject productObject = new JSONObject();
                            productObject.put("title", Title1);
                            productObject.put("quantity", quantity1);


                            // Add the current item to the JSON array
                            jsonArray.put(productObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());

                    Log.d("CartData", jsonArray.toString());

                    //            totalItemCount = itemList.size();
                    //            adapter_listView_cart.notifyDataSetChanged();
                }
                //        updateCartItemCount(totalItemCount);
            }

            private void updateTotalFinalPrice() {
                double totalFinalPrice = dbHelper.getTotalFinalPrice();
                String totalFinalPriceText = "Total Final Price: ₹" + String.format("%.2f", totalFinalPrice);
                sub_total.setText(totalFinalPriceText);
            }

            CartAdapter.OnItemClickListener itemClickListener = new CartAdapter.OnItemClickListener() {
                @Override
                public void onPlusClick(String productId, String price) {

                    dbHelper.incrementQuantityAndPrice(productId, 1, Double.parseDouble(price));
                    updateRecyclerView();


                }

                @Override
                public void onMinusClick(String productId, String price) {
                    dbHelper.decrementQuantityAndPrice(productId, 1, Double.parseDouble(price));
//                    Toast.makeText(getContext(), "Pluefsadf", Toast.LENGTH_SHORT).show();
                    updateRecyclerView(); // Call this method to update the RecyclerView

                }

            };


            private void updateRecyclerView() {
                itemList.clear();
                displaydata();
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPlusClick(String productId, String price) {
//                Toast.makeText(getContext(), "sdfghjkl", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMinusClick(String productId, String price) {

            }

            //    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
            //        this.bottomNavigationView = bottomNavigationView;
            //
            //        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.cart);
            ////        Toast.makeText(this, ""+String.valueOf(count_item), Toast.LENGTH_SHORT).show();
            //        Log.d("jdhywehjdnad", ""+count_item);
            //        badgeDrawable.setNumber(Integer.parseInt(String.valueOf(count_item)));
            //    }


            private void sendDataToTargetComponent() {
                if (dataListener != null) {
                    String dataToSend = "This is the data to send";
                    dataListener.onDataReceived(String.valueOf(count_item));
                }
            }

            @Override
            public void onAttach(Context context) {
                super.onAttach(context);

                try {
                    dataListener = (CartDataListener) context;
                } catch (ClassCastException e) {
                    throw new ClassCastException(context.toString() + " must implement CartDataListener");
                }
            }

            public interface CartDataListener {
                void onDataReceived(String data);
            }


            public void replaceCartFragment() {
                Cart_Fragment cartFragment = new Cart_Fragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.cart_lay, cartFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

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
