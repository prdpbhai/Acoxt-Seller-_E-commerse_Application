package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Notification_pojo;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Adapter_notification extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Notification_pojo.Datum> model_notificatios;
    private Context context;
    private static final int VIEW_TYPE_TYPE1 = 1;
    private static final int VIEW_TYPE_TYPE2 = 2;

    public Adapter_notification(List<Notification_pojo.Datum> model_notificatios, Context context) {
        this.model_notificatios = model_notificatios;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        // Determine the view type for the item at the given position

        String diss = model_notificatios.get(position).getDiscount();
        diss=diss.replaceAll("[^0-9]", "");
        int pr= Integer.parseInt(diss);


        if (pr <= 50) {
            return VIEW_TYPE_TYPE1;
        } else {
            return VIEW_TYPE_TYPE2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TYPE1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
            return new ViewHolderType1(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_notification, parent, false);
            return new ViewHolderType2(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_TYPE1) {
            ViewHolderType1 type1Holder = (ViewHolderType1) holder;
            type1Holder.name.setText(model_notificatios.get(position).getTitle());
            type1Holder.discount.setText(String.valueOf(model_notificatios.get(position).getDiscount()));
            // Load image using Picasso for type1Holder
            List<String> imgList = Collections.singletonList(model_notificatios.get(position).getImg());

            if (imgList != null && !imgList.isEmpty()) {
                String imageUrl = imgList.get(0);
                Picasso.get()
                        .load("https://api.acoxt.fourbrick.in/" + imageUrl)
                        .into(((ViewHolderType1) holder).img);
            }
        } else {
            ViewHolderType2 type2Holder = (ViewHolderType2) holder;
            type2Holder.name.setText(model_notificatios.get(position).getTitle());
//            type2Holder.discount.setText(String.valueOf(model_notificatios.get(position).getDiscount()));
            // Load image using Picasso for type2Holder


            List<String> imgList = Collections.singletonList(model_notificatios.get(position).getImg());

            if (imgList != null && !imgList.isEmpty()) {
                String imageUrl = imgList.get(0);
                Picasso.get()
                        .load("https://api.acoxt.fourbrick.in/" + imageUrl)
                        .into(((ViewHolderType2) holder).img);
            }
        }
    }

    @Override
    public int getItemCount() {
        return model_notificatios.size();
    }

    public class ViewHolderType1 extends RecyclerView.ViewHolder {
        TextView name, discount;
        ImageView img;

        public ViewHolderType1(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            discount = itemView.findViewById(R.id.discount);
        }
    }

    public class ViewHolderType2 extends RecyclerView.ViewHolder {
        TextView name, discount;
        ImageView img;

        public ViewHolderType2(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            discount = itemView.findViewById(R.id.discount);
        }
    }
}
