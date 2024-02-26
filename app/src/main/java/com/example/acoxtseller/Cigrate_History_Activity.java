package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.acoxtseller.Adapter.Adapter_Calender;
import com.example.acoxtseller.Adapter.Adapter_cigrate_counter;
import com.example.acoxtseller.Model.Model_Calender;
import com.example.acoxtseller.Model.Model_cigrate_counter;

import java.util.ArrayList;
import java.util.List;

public class Cigrate_History_Activity extends AppCompatActivity {
    RecyclerView history_cig_recyclerview,calendar_recyclerview_his;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigrate_history);
        history_cig_recyclerview=findViewById(R.id.history_cig_recyclerview);
        List<Model_cigrate_counter> model_cigrate_counters=new ArrayList<>();
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("09:00","AM"));
        model_cigrate_counters.add(new Model_cigrate_counter("12:00","PM"));
        model_cigrate_counters.add(new Model_cigrate_counter("04:00","PM"));
        history_cig_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        history_cig_recyclerview.setAdapter(new Adapter_cigrate_counter(getApplicationContext(),model_cigrate_counters));



        calendar_recyclerview_his=findViewById(R.id.calendar_recyclerview_his);
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
        calendar_recyclerview_his.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        calendar_recyclerview_his.setAdapter(new Adapter_Calender(getApplicationContext(),model_calenders));
    }
}