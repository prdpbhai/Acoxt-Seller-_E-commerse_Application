package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.acoxtseller.Adapter.Adapter_Calender;
import com.example.acoxtseller.Adapter.Adapter_cigrate_add;
import com.example.acoxtseller.Model.Model_Calender;
import com.example.acoxtseller.Model.Model_cigrate_add;

import java.util.ArrayList;
import java.util.List;

public class SetTarget_cigrate_Activity extends AppCompatActivity {
    RecyclerView recyclerview_add_cig;
    RelativeLayout relative_add_cigrate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target_cigrate);

        recyclerview_add_cig=findViewById(R.id.recyclerview_add_cig);
        List<Model_cigrate_add> model_cigrate_adds=new ArrayList<>();
        model_cigrate_adds.add(new Model_cigrate_add("1 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("2 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("3 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("4 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("5 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("6 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("7 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("8 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("9 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("10 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("11 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("12 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("13 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("14 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("15 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("16 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("17 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("18 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("19 Cigarette"));
        model_cigrate_adds.add(new Model_cigrate_add("20 Cigarette"));
        recyclerview_add_cig.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerview_add_cig.setAdapter(new Adapter_cigrate_add(getApplicationContext(),model_cigrate_adds));

        relative_add_cigrate=findViewById(R.id.relative_add_cigrate);
        relative_add_cigrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetTarget_cigrate_Activity.this,Activity_cigrate_counter_Activity.class);
                startActivity(intent);
            }
        });
    }
}