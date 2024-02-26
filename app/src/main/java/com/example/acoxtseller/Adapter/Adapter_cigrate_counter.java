package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Model.Model_cigrate_counter;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_cigrate_counter extends RecyclerView.Adapter<Adapter_cigrate_counter.Viewholder>{
    Context context;
    List<Model_cigrate_counter>model_cigrate_counters;

    public Adapter_cigrate_counter(Context context, List<Model_cigrate_counter> model_cigrate_counters) {
        this.context = context;
        this.model_cigrate_counters = model_cigrate_counters;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.item_cig_timing,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.time.setText(model_cigrate_counters.get(position).getTime());
        holder.am.setText(model_cigrate_counters.get(position).getAm());

    }

    @Override
    public int getItemCount() {
        return model_cigrate_counters.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView time,am;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time);
            am=itemView.findViewById(R.id.am);
        }
    }
}
