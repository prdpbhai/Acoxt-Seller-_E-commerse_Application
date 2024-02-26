package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.acoxtseller.Adapter.Adapter_bankList;
import com.example.acoxtseller.Model.Model_bank_list;

import java.util.ArrayList;
import java.util.List;

public class Bank_nameList_Activity extends AppCompatActivity {
    RecyclerView bank_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_name_list);
        bank_recyclerview=findViewById(R.id.bank_recyclerview);
        List<Model_bank_list>model_bank_lists=new ArrayList<>();
        model_bank_lists.add(new Model_bank_list(R.drawable.sbi_icon,"SBI"));
        model_bank_lists.add(new Model_bank_list(R.drawable.axis_icon,"Axis"));
        model_bank_lists.add(new Model_bank_list(R.drawable.bank_of_india_icon,"BIO"));
        model_bank_lists.add(new Model_bank_list(R.drawable.hdfc_icon,"HDFC"));
        model_bank_lists.add(new Model_bank_list(R.drawable.bank_of_india_icon,"ICIC"));
        model_bank_lists.add(new Model_bank_list(R.drawable.sbi_icon,"SBI"));
        model_bank_lists.add(new Model_bank_list(R.drawable.axis_icon,"AXIS"));
        model_bank_lists.add(new Model_bank_list(R.drawable.axis_icon,"AXIS"));
        model_bank_lists.add(new Model_bank_list(R.drawable.hdfc_icon,"HDFC"));
        model_bank_lists.add(new Model_bank_list(R.drawable.bank_of_india_icon,"ICIC"));

        bank_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        bank_recyclerview.setAdapter(new Adapter_bankList(getApplicationContext(),model_bank_lists));

    }
}