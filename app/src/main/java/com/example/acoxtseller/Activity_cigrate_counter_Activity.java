package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acoxtseller.Adapter.Adapter_Add_Product;
import com.example.acoxtseller.Adapter.Adapter_Calender;
import com.example.acoxtseller.Adapter.Adapter_cigrate_counter;
import com.example.acoxtseller.Model.Model_Calender;
import com.example.acoxtseller.Model.Model_cigrate_counter;

import java.util.ArrayList;
import java.util.List;

public class Activity_cigrate_counter_Activity extends AppCompatActivity {
    RecyclerView count_cig_recyclerview,calendar_recyclerview;
    TextView view_history,count;
    ImageView decrease,increase;
    private int itemCount = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigrate_counter);
        count_cig_recyclerview=findViewById(R.id.count_cig_recyclerview);
        List<Model_cigrate_counter>model_cigrate_counters=new ArrayList<>();
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        count_cig_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        count_cig_recyclerview.setAdapter(new Adapter_cigrate_counter(getApplicationContext(),model_cigrate_counters));

        view_history=findViewById(R.id.view_history);
        view_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_cigrate_counter_Activity.this,Cigrate_History_Activity.class);
                startActivity(intent);
            }
        });
        calendar_recyclerview=findViewById(R.id.calendar_recyclerview);
        List<Model_Calender>model_calenders=new ArrayList<>();
        model_calenders.add(new Model_Calender("Aug","23"));
        model_calenders.add(new Model_Calender("Aug","24"));
        model_calenders.add(new Model_Calender("Aug","25"));
        model_calenders.add(new Model_Calender("Aug","26"));
        model_calenders.add(new Model_Calender("Aug","27"));
        model_calenders.add(new Model_Calender("Aug","28"));
        model_calenders.add(new Model_Calender("Aug","29"));
        model_calenders.add(new Model_Calender("Aug","30"));
        model_calenders.add(new Model_Calender("Aug","31"));
        model_calenders.add(new Model_Calender("sep","01"));
        model_calenders.add(new Model_Calender("sep","02"));
        model_calenders.add(new Model_Calender("sep","03"));
        model_calenders.add(new Model_Calender("sep","04"));
        model_calenders.add(new Model_Calender("sep","05"));
        model_calenders.add(new Model_Calender("sep","06"));
        model_calenders.add(new Model_Calender("sep","07"));
        model_calenders.add(new Model_Calender("sep","08"));
        model_calenders.add(new Model_Calender("sep","09"));
        model_calenders.add(new Model_Calender("sep","10"));
        model_calenders.add(new Model_Calender("sep","11"));
        model_calenders.add(new Model_Calender("sep","12"));
        model_calenders.add(new Model_Calender("sep","13"));
        calendar_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        calendar_recyclerview.setAdapter(new Adapter_Calender(getApplicationContext(),model_calenders));

        increase=findViewById(R.id.increase);
        decrease=findViewById(R.id.decrease);
        count=findViewById(R.id.count);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemCount > 0) {
                    itemCount--;
                    count.setText(String.valueOf(itemCount));
                }
            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCount++;
                count.setText(String.valueOf(itemCount));

            }
        });

    }
}