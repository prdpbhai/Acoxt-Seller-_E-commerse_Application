package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity_Menu_Activity extends AppCompatActivity {
    LinearLayout add_cigrate,set_taeget,my_goal;
    TextView profile_text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        add_cigrate=findViewById(R.id.add_cigrate);
        profile_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        add_cigrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Activity_Menu_Activity.this,Activity_cigrate_counter_Activity.class);
                startActivity(intent);
            }
        });
        set_taeget=findViewById(R.id.set_taeget);
        set_taeget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_Menu_Activity.this,SetTarget_cigrate_Activity.class);
                startActivity(intent);
            }
        });
        my_goal=findViewById(R.id.my_goal);
        my_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Activity_Menu_Activity.this,My_Goals_Activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}