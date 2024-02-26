package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Model.Model_bank_list;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_bankList extends RecyclerView.Adapter<Adapter_bankList.ViewHolder>{
    Context context;
    List<Model_bank_list>model_bank_lists;

    public Adapter_bankList(Context context, List<Model_bank_list> model_bank_lists) {
        this.context = context;
        this.model_bank_lists = model_bank_lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_bankList.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bank,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bank_name.setText((CharSequence) model_bank_lists.get(position).getBank_name());
        holder.bank_icon.setImageResource(model_bank_lists.get(position).getBank_icon());

    }

    @Override
    public int getItemCount() {
        return model_bank_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bank_icon;
        TextView bank_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bank_icon=itemView.findViewById(R.id.bank_icon);
            bank_name=itemView.findViewById(R.id.bank_name);
        }
    }
}
