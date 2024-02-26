package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Model.Model_Calender;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_Calender extends RecyclerView.Adapter<Adapter_Calender.ViewHolder>{
    Context context;
    List<Model_Calender>model_calenders;

    public Adapter_Calender(Context context, List<Model_Calender> model_calenders) {
        this.context = context;
        this.model_calenders = model_calenders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_Calender.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.month.setText(model_calenders.get(position).getMonth());
        holder.date.setText(model_calenders.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return model_calenders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView month,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            month=itemView.findViewById(R.id.month);
            date=itemView.findViewById(R.id.date);
        }
    }
}
