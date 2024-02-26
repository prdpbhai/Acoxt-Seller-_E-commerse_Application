package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Location_Access_Activity extends AppCompatActivity {
    RelativeLayout allow_location;
    TextView manual_location;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_access);
        allow_location=findViewById(R.id.allow_location);
        allow_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Location_Access_Activity.this,Map_Activity.class);
                startActivity(intent);
            }
        });
        manual_location=findViewById(R.id.manual_location);
        manual_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Location_Access_Activity.this,Enter_Address.class);
                startActivity(intent);
            }
        });
//        return null;
    }
}