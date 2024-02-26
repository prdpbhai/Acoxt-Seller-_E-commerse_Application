package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Delivery_confirm_otp_pojo;
import com.example.acoxtseller.Api_Pojo.Order_status_update_pojo;
import com.example.acoxtseller.Api_Pojo.Update_order_status_pojo;
import com.example.acoxtseller.Api_Pojo.Verify_otp_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update_order_Status extends AppCompatActivity {
    CardView order_status_card;
    TextView status_text,get_map;
    CardView pick_btn,track;
    String order_status;
    ImageView drop,deliver,out_for_delivery,transit;
    String trackorder;
    String id;

    String otp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order_status);

        order_status_card=findViewById(R.id.order_status_card);
        drop=findViewById(R.id.drop);
        pick_btn=findViewById(R.id.pick_btn);
        status_text=findViewById(R.id.status_text);
        deliver=findViewById(R.id.deliver);
        out_for_delivery=findViewById(R.id.out_for_delivery);
        transit=findViewById(R.id.transit);
        get_map=findViewById(R.id.get_map);
        track=findViewById(R.id.track);

        SharedPreferences pref=Update_order_Status.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
        id=pref.getString("user","");



        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       Intent intent=new Intent(Update_order_Status.this,Facek.class);
                       startActivity(intent);

            }
        });

        Log.d("hgfdrtyuioplknbgy", ""+id);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        pick_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (status_text.getText().toString().equals("Select order status")){
                    Toast.makeText(Update_order_Status.this, "Select Order status", Toast.LENGTH_SHORT).show();
                }else {
                    Web_Service_login.getClient().update_order_status_pojo("Bearer "+Token,"orderRequestUpdate/"+id,""+order_status).enqueue(
                            new Callback<Update_order_status_pojo>() {
                                @Override
                                public void onResponse(Call<Update_order_status_pojo> call, Response<Update_order_status_pojo> response) {
                                    if (response.body().getCode()==200){
                                        Toast.makeText(Update_order_Status.this, "success", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(Update_order_Status.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Update_order_status_pojo> call, Throwable t) {

                                }
                            }
                    );
                }
            }
        });
    }

    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(Update_order_Status.this, anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.order_manage_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                SharedPreferences pref = Update_order_Status.this.getSharedPreferences("login", Context.MODE_PRIVATE);
                String Token = pref.getString("token", "");
//                Toast.makeText(Update_order_Status.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                status_text.setText(menuItem.getTitle());
//                order_status="In Transit";
                trackorder=menuItem.getTitle().toString();
                order_status=menuItem.toString();
                if (menuItem.getTitle().equals("In Transit")){
                    transit.setImageResource(R.drawable.blue_tick);
                    out_for_delivery.setImageResource(R.drawable.gray_tick);
                    deliver.setImageResource(R.drawable.gray_tick);
                }
                if (menuItem.getTitle().equals("Out for Delivery")){
                    transit.setImageResource(R.drawable.blue_tick);
                    out_for_delivery.setImageResource(R.drawable.blue_tick);
                    deliver.setImageResource(R.drawable.gray_tick);
                }
                if (menuItem.getTitle().equals("Delivered")){
                    transit.setImageResource(R.drawable.blue_tick);
                    out_for_delivery.setImageResource(R.drawable.blue_tick);
                    deliver.setImageResource(R.drawable.blue_tick);

                    status();
                    runningupdate();
                }
                return true;
            }
        });

        popupMenu.show();
    }


    void runningupdate(){

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String Token = sharedPreferences.getString("token","");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_confirm_delivered_otp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.slide_in_bottom;
//        Button submit_otp=dialog.findViewById(R.id.submit_button);
        RelativeLayout submit_otp=dialog.findViewById(R.id.submit_otp);
        TextView otp_text=dialog.findViewById(R.id.otp_text);
        EditText otp1 =dialog.findViewById(R.id.edittext1);
        EditText otp2 =dialog.findViewById(R.id.edittext2);
        EditText otp3 =dialog.findViewById(R.id.edittext3);
        EditText otp4 =dialog.findViewById(R.id.edittext4);



        //here
        EditText[] otpBoxes = {otp1, otp2, otp3, otp4};
        for (int i = 0; i < otpBoxes.length - 1; i++) {
            final EditText currentBox = otpBoxes[i];
            final EditText nextBox = otpBoxes[i + 1];

            currentBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && s.length() == 1) {
                        nextBox.requestFocus();
                    }
                }
            });
        }

//        otp_text.setText(otp);

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String otp = otp1.getText().toString() +
                        otp2.getText().toString() +
                        otp3.getText().toString() +
                        otp4.getText().toString();
                Log.d("jfipurhjfnvsjdnvsdnjjds", ""+otp);
//
//                Toast.makeText(Update_order_Status.this, ""+otp, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                if (otp.length()==4){
                    Web_Service_login.getClient().delivery_confirm_otp_pojo(id,""+otp).enqueue(new Callback<Delivery_confirm_otp_pojo>() {
                        @Override
                        public void onResponse(Call<Delivery_confirm_otp_pojo> call, Response<Delivery_confirm_otp_pojo> response) {
                            Log.d("jdewhncjdnekadbd", ""+response.body().getMsg());
                            if (response.body().getMsg().equals("Order Delivered Successfully")){
                                Toast.makeText(Update_order_Status.this, "Otp Confirmed", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Update_order_Status.this, "Wrong otp", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Delivery_confirm_otp_pojo> call, Throwable t) {

                        }
                    });
                }

            }
        });
        dialog.show();


    }
    public void status(){
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String Token = sharedPreferences.getString("token","");
        Web_Service_login.getClient().update_order_status_pojo("Bearer "+Token,"orderRequestUpdate/"+id,"Delivered").enqueue(
                new Callback<Update_order_status_pojo>() {
                    @Override
                    public void onResponse(Call<Update_order_status_pojo> call, Response<Update_order_status_pojo> response) {
                        if (response.body().getCode()==200){
                            Toast.makeText(Update_order_Status.this, "sent Otp", Toast.LENGTH_SHORT).show();
                            Log.d("hbchjadbcnxqibckx ", ""+response.body().getMsg());
                        }
                        else {
                            Toast.makeText(Update_order_Status.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Update_order_status_pojo> call, Throwable t) {

                    }
                }
        );
    }

}