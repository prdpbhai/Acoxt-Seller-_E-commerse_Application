package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.GST_Verify_pojo;
import com.example.acoxtseller.Api_Pojo.Gst_Otp_Verify_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Enter_GST_Num extends AppCompatActivity {
    RelativeLayout back_gst;
    LinearLayout verify_btn;
    EditText gst_enter;
    RelativeLayout continue_btn,submit_otp,main_layout_gst;
    private EditText[] otpEditTexts = new EditText[6];
    private String correctOtp = "123456";

    String gstotp="";
    String gst_no="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_gst_num);
        continue_btn = findViewById(R.id.continue_btn);
        verify_btn=findViewById(R.id.verify_btn);
        gst_enter=findViewById(R.id.gst_enter);
        submit_otp=findViewById(R.id.submit_otp);
        back_gst=findViewById(R.id.back_gst);
        main_layout_gst=findViewById(R.id.main_layout_gst);


        main_layout_gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Enter_GST_Num.this,Name_your_ACOXT_Store.class);
                startActivity(intent);
//                Toast.makeText(Enter_GST_Num.this, "Storeotp", Toast.LENGTH_SHORT).show();
            }
        });
        back_gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences pref = Enter_GST_Num.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
//        Toast.makeText(Enter_GST_Num.this, ""+Token, Toast.LENGTH_SHORT).show();

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (gst_enter.getText().toString().length()==15){
                   if (gst_enter.getText().toString().isEmpty()||verify_btn.toString().isEmpty())
                   {
                       Toast.makeText(Enter_GST_Num.this, "Please Enter GST Number", Toast.LENGTH_SHORT).show();
                   }else
                   {
                       Web_Service_login.getClient().gst_verify("Bearer " +Token, gst_enter.getText().toString()).enqueue(new Callback<GST_Verify_pojo>() {
                           @Override
                           public void onResponse(Call<GST_Verify_pojo> call, Response<GST_Verify_pojo> response) {
                               if(response.body()!=null){
//                                        Toast.makeText(Enter_GST_Num.this, ""+response.body().getOtp(), Toast.LENGTH_SHORT).show();
//                                        Log.e("sdfghj",""+response.body().getOtp());
                                   gstotp= String.valueOf(response.body().getOtp());
                                   gst_no=gst_enter.getText().toString();
                                   verify_btn.setEnabled(false);
                                   new Handler().postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           runningupdate();
                                           verify_btn.setEnabled(true);
                                       }
                                   }, 2000);

                               }
                           }

                           @Override
                           public void onFailure(Call<GST_Verify_pojo> call, Throwable t) {

                           }
                       });}
               }else {
                   Toast.makeText(Enter_GST_Num.this, "Enter Correct GST No.", Toast.LENGTH_SHORT).show();
               }
            }
        });


    }
    void runningupdate(){
        SharedPreferences pref=Enter_GST_Num.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token","");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_enter_otp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.slide_in_bottom;
        RelativeLayout submit_otp=dialog.findViewById(R.id.submit_otp);
        TextView gstotpp=dialog.findViewById(R.id.set_gst_otp);
         EditText otp1 =dialog.findViewById(R.id.edittext1);
         EditText otp2 =dialog.findViewById(R.id.edittext2);
         EditText otp3 =dialog.findViewById(R.id.edittext3);
         EditText otp4 =dialog.findViewById(R.id.edittext4);
         EditText otp5 =dialog.findViewById(R.id.edittext5);
         EditText otp6 =dialog.findViewById(R.id.edittext6);


        EditText[] otpBoxes = {otp1, otp2, otp3, otp4, otp5, otp6};
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


        gstotpp.setText(gstotp);

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String otp = otp1.getText().toString() +
                        otp2.getText().toString() +
                        otp3.getText().toString() +
                        otp4.getText().toString() +
                        otp5.getText().toString() +
                        otp6.getText().toString();
//                Toast.makeText(Enter_GST_Num.this, ""+otp, Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                if(otp!=null){
                    Web_Service_login.getClient().gst_otp_verify("Bearer " +Token,gst_no,otp).enqueue(new Callback<Gst_Otp_Verify_pojo>() {
                        @Override
                        public void onResponse(Call<Gst_Otp_Verify_pojo> call, Response<Gst_Otp_Verify_pojo> response) {
                            if(response.body()!=null){
                                Intent intent=new Intent(Enter_GST_Num.this,Name_your_ACOXT_Store.class);
                                startActivity(intent);
//                                Toast.makeText(Enter_GST_Num.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Enter_GST_Num.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Gst_Otp_Verify_pojo> call, Throwable t) {

                        }
                    });
                }

            }
        });
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}




