package com.example.acoxtseller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_horizental_item_pojo;
import com.example.acoxtseller.Munchies_Category_Activity;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_category  extends RecyclerView.Adapter<Adapter_category.ViewHolder>{

//

    List<Acoxt_fregment_horizental_item_pojo.Datum>acoxt_fregment_horizental_item_pojos;
    Context context;

    public Adapter_category(List<Acoxt_fregment_horizental_item_pojo.Datum> acoxt_fregment_horizental_item_pojos, Context context) {
        this.acoxt_fregment_horizental_item_pojos = acoxt_fregment_horizental_item_pojos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.category_name.setText(acoxt_fregment_horizental_item_pojos.get(position).getTitle());
        String input = acoxt_fregment_horizental_item_pojos.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");

        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.category_image);
        Intent intent=new Intent();
        intent.putExtra("categoryId",acoxt_fregment_horizental_item_pojos.get(position).getId());
        holder.itm_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Munchies_Category_Activity.class);
                intent.putExtra("name",acoxt_fregment_horizental_item_pojos.get(position).getId());
                context.startActivity(intent);

                SharedPreferences pref=context.getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String title=acoxt_fregment_horizental_item_pojos.get(position).getTitle();
                editor.putString("productname",title);
                Log.d("jhgtyhvfeaesdc", "onClick: "+title);
                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
        return acoxt_fregment_horizental_item_pojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_name;
        ImageView category_image;
        RelativeLayout relative_add,itm_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name=itemView.findViewById(R.id.category_name);
            category_image=itemView.findViewById(R.id.category_image);
            itm_card=itemView.findViewById(R.id.itm_card);
        }
    }
}
