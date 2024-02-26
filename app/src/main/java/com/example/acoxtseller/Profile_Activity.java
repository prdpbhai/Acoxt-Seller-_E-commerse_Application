package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Profile_show_data_Pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Activity extends AppCompatActivity {
    EditText edit_gst,edit_dob,edit_gender,edit_email,edit_phone,user_name;
    ImageView edit_btn;
    RelativeLayout save_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        save_btn=findViewById(R.id.save_btn);
        edit_dob=findViewById(R.id.edit_dob);
        edit_gst=findViewById(R.id.edit_gst);
        edit_gender= findViewById(R.id.edit_gender);
        edit_email=findViewById(R.id.edit_email);
        edit_phone=findViewById(R.id.edit_phone);
        user_name=findViewById(R.id.user_name);
        edit_btn=findViewById(R.id.edit_btn);

        SharedPreferences pref = Profile_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
        Web_Service_login.getClient().profile_show_data_pojo("Bearer " + Token,"profile_get").enqueue(new Callback<Profile_show_data_Pojo>() {
            @Override
            public void onResponse(Call<Profile_show_data_Pojo> call, Response<Profile_show_data_Pojo> response) {
                if (response.body()!=null){
                    Toast.makeText(Profile_Activity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Profile_Activity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile_show_data_Pojo> call, Throwable t) {

            }
        });


        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_dob.setEnabled(true);
                edit_gst.setEnabled(true);
                edit_gender.setEnabled(true);
                edit_email.setEnabled(true);
                edit_phone.setEnabled(true);
                user_name.setEnabled(true);
                save_btn.setVisibility(View.VISIBLE);
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_dob.setEnabled(false);
                edit_gst.setEnabled(false);
                edit_gender.setEnabled(false);
                edit_email.setEnabled(false);
                edit_phone.setEnabled(false);
                user_name.setEnabled(false);
                save_btn.setVisibility(View.GONE);

            }
        });

        edit_dob.setEnabled(false);
        edit_gst.setEnabled(false);
        edit_gender.setEnabled(false);
        edit_email.setEnabled(false);
        edit_phone.setEnabled(false);
        user_name.setEnabled(false);
    }
}