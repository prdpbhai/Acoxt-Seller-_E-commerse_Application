package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Model.Model_coupon_apply;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_Coupan_apply extends RecyclerView.Adapter<Adapter_Coupan_apply.ViewHolder>{
    List<Model_coupon_apply>model_coupon_applies;
    Context context;

    public Adapter_Coupan_apply(List<Model_coupon_apply> model_coupon_applies, Context context) {
        this.model_coupon_applies = model_coupon_applies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cupan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.coupon_code.setText(model_coupon_applies.get(position).getCoupon_code());

    }

    @Override
    public int getItemCount() {
        return model_coupon_applies.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        TextView coupon_code;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coupon_code=itemView.findViewById(R.id.coupon_code);
        }
    }
}
