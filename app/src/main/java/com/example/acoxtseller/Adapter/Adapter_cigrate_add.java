package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Model.Model_cigrate_add;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_cigrate_add extends RecyclerView.Adapter<Adapter_cigrate_add.ViewHolder> {
    Context context;
    List<Model_cigrate_add>model_cigrate_adds;

    public Adapter_cigrate_add(Context context, List<Model_cigrate_add> model_cigrate_adds) {
        this.context = context;
        this.model_cigrate_adds = model_cigrate_adds;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Model_cigrate_add> getModel_cigrate_adds() {
        return model_cigrate_adds;
    }

    public void setModel_cigrate_adds(List<Model_cigrate_add> model_cigrate_adds) {
        this.model_cigrate_adds = model_cigrate_adds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_cigrate_add.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cigrate_count,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cigrate_count.setText(model_cigrate_adds.get(position).getCigrate_count());

    }

    @Override
    public int getItemCount() {
        return model_cigrate_adds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cigrate_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cigrate_count=itemView.findViewById(R.id.cigrate_count);
        }
    }
}
