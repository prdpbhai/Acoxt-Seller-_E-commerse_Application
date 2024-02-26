package com.example.acoxtseller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_ListView_Cart;
import com.example.acoxtseller.Fregment.Acoxt_Fragment;
import com.example.acoxtseller.Fregment.Cart_Fragment;
import com.example.acoxtseller.Fregment.Category_Fragment;
import com.example.acoxtseller.Fregment.Inventory_Fragment;
import com.example.acoxtseller.Fregment.Profile_Fragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_Product_Page extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener, Cart_Fragment.CartDataListener {
    BottomNavigationView bottomNavigationView;

    String receivedString,interitemcoun;

    String carditemcount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_product_page);


        SharedPreferences pref=getSharedPreferences("login", Context.MODE_PRIVATE);

        carditemcount = pref.getString("carditemcount", "");
        Log.d("MainAcdhdvativity", ""+carditemcount);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
        setBadgeCount(R.id.cart, 5);
//

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.acoxt);

        Intent intent = getIntent();
        if (intent != null) {
            receivedString = intent.getStringExtra("possition");
            if (receivedString != null) {
                bottomNavigationView.setSelectedItemId(R.id.cart);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, cart_fragment)
                        .commit();
            }
        }

    }
    Acoxt_Fragment acoxt_fragment = new Acoxt_Fragment();
    Category_Fragment category_fragment = new Category_Fragment();
    Inventory_Fragment inventory_fragment = new Inventory_Fragment();
    Cart_Fragment cart_fragment= new Cart_Fragment();
    Profile_Fragment profile_fragment=new Profile_Fragment();





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        setBadgeCount(R.id.cart, 5);
        switch (item.getItemId()) {

            case R.id.acoxt:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, acoxt_fragment)
                        .commit();

                SharedPreferences pref=getSharedPreferences("login", Context.MODE_PRIVATE);
                carditemcount = pref.getString("carditemcount", "");
                Log.d("bfhvngfuyvguycc", ""+carditemcount);
                setBadgeCount(R.id.cart, 5);
                return true;

            case R.id.categories:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, category_fragment)
                        .commit();

                SharedPreferences pref1=getSharedPreferences("login", Context.MODE_PRIVATE);
                carditemcount = pref1.getString("carditemcount", "");
                Log.d("bfhvngfuyvguycc", ""+carditemcount);
                setBadgeCount(R.id.cart, 5);
                return true;

            case R.id.inventory:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, inventory_fragment)
                        .commit();

                SharedPreferences pref2=getSharedPreferences("login", Context.MODE_PRIVATE);
                carditemcount = pref2.getString("carditemcount", "");
                Log.d("bfhvngfuyvguycc", ""+carditemcount);
                setBadgeCount(R.id.cart, 5);
                return true;

            case R.id.cart:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, cart_fragment)
                        .commit();

                SharedPreferences pref3=getSharedPreferences("login", Context.MODE_PRIVATE);
                carditemcount = pref3.getString("carditemcount", "");
                Log.d("bfhvngfuyvguycc", ""+carditemcount);
                setBadgeCount(R.id.cart, 5);
                return true;

            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, profile_fragment)
                        .commit();

                SharedPreferences pref4=getSharedPreferences("login", Context.MODE_PRIVATE);
                carditemcount = pref4.getString("carditemcount", "");
                Log.d("bfhvngfuyvguycc", ""+carditemcount);
                setBadgeCount(R.id.cart, 5);
                return true;
        }
        return false;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
    private void setBadgeCount(int itemId, int count) {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.cart);
//        Toast.makeText(this, ""+carditemcount, Toast.LENGTH_SHORT).show();
        badgeDrawable.setNumber(Integer.parseInt(carditemcount));

    }


    @Override
    public void onDataReceived(String data) {
        interitemcoun= data;
        Log.d("dwhjhsbqndjwnxsf", ""+interitemcoun);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.cart);
        badgeDrawable.setNumber(Integer.parseInt(interitemcoun));

    }
}