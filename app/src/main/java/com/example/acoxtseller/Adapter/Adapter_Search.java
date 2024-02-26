package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Search_pojo;
import com.example.acoxtseller.DB_Helper;
import com.example.acoxtseller.R;

import java.util.List;

public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.ViewHolder>{

    List<Search_pojo.Datum> search_pojo;
    Context context;

    DB_Helper dbHelper;

    private int totalItemCount=0;

    public Adapter_Search(List<Search_pojo.Datum> search_pojo, Context context, DB_Helper dbHelper) {
        this.search_pojo = search_pojo;
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Search_pojo.Datum item = search_pojo.get(position);

        holder.home_prodname.setText(item.getTitle());
        holder.weight.setText(item.getUnit());
        holder.discount_price.setText(item.getFinalprice());
        holder.price.setText(item.getPrice());

    }

    @Override
    public int getItemCount() {
        return search_pojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView home_prodname,add_product,count,discount_price,price,weight;
        ImageView home_img;
        CardView carditem;
        LinearLayout linear_add;
        RelativeLayout increase,relative_decrease;
        int itemCount=0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            home_prodname=itemView.findViewById(R.id.home_prodname);
            add_product=itemView.findViewById(R.id.add_product);
            home_img=itemView.findViewById(R.id.home_img);
            carditem=itemView.findViewById(R.id.carditem);
            linear_add=itemView.findViewById(R.id.linear_add);
            count=itemView.findViewById(R.id.count);
            increase=itemView.findViewById(R.id.increase);
            relative_decrease=itemView.findViewById(R.id.relative_decrease);
            weight=itemView.findViewById(R.id.weight);
            price=itemView.findViewById(R.id.price);
            discount_price=itemView.findViewById(R.id.discount_price);
        }
    }
}
