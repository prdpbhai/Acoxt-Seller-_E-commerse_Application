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
import com.example.acoxtseller.Api_Pojo.Munchies_Product_show_pojo;
import com.example.acoxtseller.Model.Model_acoxt_freg_horzen;
import com.example.acoxtseller.Munchies_Category_Activity;
import com.example.acoxtseller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Callback;

public class Adapter_acoxt_freg_horzen extends RecyclerView.Adapter<Adapter_acoxt_freg_horzen.ViewHolder> {
    List<Acoxt_fregment_horizental_item_pojo.Datum>acoxt_fregment_horizental_item_pojos;
    Context context;

    public Adapter_acoxt_freg_horzen(List<Acoxt_fregment_horizental_item_pojo.Datum> acoxt_fregment_horizental_item_pojos, Context context) {
        this.acoxt_fregment_horizental_item_pojos = acoxt_fregment_horizental_item_pojos;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_acoxt_freg_horzen.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_horizental_acoxt_freg,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_name_hor.setText(acoxt_fregment_horizental_item_pojos.get(position).getTitle());
        String input = acoxt_fregment_horizental_item_pojos.get(position).getImg().toString();
        String output = input.replace("[", "").replace("]", "");


        SharedPreferences pref=context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.apply();



        Log.d("sdgfdgd",""+output);
        Picasso.get()
                .load("https://api.acoxt.fourbrick.in/"+output).into(holder.category_img);

        holder.relative_item_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Munchies_Category_Activity.class);
                intent.putExtra("name",acoxt_fregment_horizental_item_pojos.get(position).getId());
                context.startActivity(intent);

                String title= String.valueOf(acoxt_fregment_horizental_item_pojos.get(position).getTitle());
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
        CircleImageView category_img;
        TextView item_name_hor;
        RelativeLayout relative_item_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name_hor=itemView.findViewById(R.id.item_name_hor);
            category_img=itemView.findViewById(R.id.category_img);
            relative_item_type=itemView.findViewById(R.id.relative_item_type);
        }
    }
}
