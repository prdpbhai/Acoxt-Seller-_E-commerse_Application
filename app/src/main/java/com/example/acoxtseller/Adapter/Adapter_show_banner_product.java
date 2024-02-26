package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Show_banner_pojo;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_show_banner_product extends RecyclerView.Adapter<Adapter_show_banner_product.Viewholder>{
    List<Show_banner_pojo.Datum> show_banner_pojos;
    Context context;

    public Adapter_show_banner_product(List<Show_banner_pojo.Datum> show_banner_pojos, Context context) {
        this.show_banner_pojos = show_banner_pojos;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return show_banner_pojos.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
