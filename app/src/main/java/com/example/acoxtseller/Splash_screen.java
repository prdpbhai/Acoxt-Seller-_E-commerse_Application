package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_screen extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an intent to launch the main activity
//                Intent intent = new Intent(Splash_screen.this, Login.class);
//                startActivity(intent);

                // Close this activity
                finish();
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();


                String token = sharedPreferences.getString("token", null);
                if (token!=null){
//            Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Splash_screen.this,Home_Product_Page.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(Splash_screen.this,Login.class);
                    startActivity(intent);
                }
            }
        }, SPLASH_TIMEOUT);
    }
}