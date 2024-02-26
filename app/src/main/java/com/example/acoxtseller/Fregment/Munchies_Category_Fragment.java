package com.example.acoxtseller.Fregment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acoxtseller.Adapter.Adapter_category;
import com.example.acoxtseller.Adapter.Adapter_munchies;
import com.example.acoxtseller.Model.Model_Category;
import com.example.acoxtseller.Model.Model_munchies;
import com.example.acoxtseller.R;

import java.util.ArrayList;
import java.util.List;


public class Munchies_Category_Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_munchies__category_, container, false);

        view= inflater.inflate(R.layout.fragment_munchies__category_, container, false);
        recyclerView=view.findViewById(R.id.category_recyclerview);

        List<Model_munchies> item_w1=new ArrayList<>();
        item_w1.add(new Model_munchies("name"));


        int numberOfColumns=2;
        Adapter_munchies mAdapter=new Adapter_munchies(item_w1, view.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),numberOfColumns));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}