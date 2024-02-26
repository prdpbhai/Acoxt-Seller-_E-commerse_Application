package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Model.Model_munchies;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_munchies extends RecyclerView.Adapter<Adapter_munchies.ViewHolder> {
    List<Model_munchies>model_munchies;
    Context context;

    public Adapter_munchies(List<Model_munchies> model_munchies, Context context) {
        this.model_munchies = model_munchies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_munchies.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.category_name.setText((CharSequence) model_munchies.get(position).getCategory_name());

    }

    @Override
    public int getItemCount() {
        return model_munchies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name=itemView.findViewById(R.id.category_name);
        }
    }
}
