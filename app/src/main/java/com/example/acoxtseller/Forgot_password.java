package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Forget_password_verify_pojo;
import com.example.acoxtseller.Api_Pojo.Text_forget_pass_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgot_password extends AppCompatActivity {
    RelativeLayout verify_forget;
    TextView back,sent_otp,otp,reset;
    EditText email_forget,fill_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        verify_forget=findViewById(R.id.verify_forget);
        back=findViewById(R.id.back);
        sent_otp=findViewById(R.id.sent_otp);
        email_forget=findViewById(R.id.email_forget);
        otp=findViewById(R.id.otp);
        fill_otp=findViewById(R.id.fill_otp);
        reset=findViewById(R.id.reset);

        sent_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject forgetPasswordJson = new JSONObject();
                try {
                    forgetPasswordJson.put("email", email_forget.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), forgetPasswordJson.toString());
                Web_Service_login.getClient().text_forget_pass_pojo(requestBody).enqueue(new Callback<Text_forget_pass_pojo>() {
                    @Override
                    public void onResponse(Call<Text_forget_pass_pojo> call, Response<Text_forget_pass_pojo> response) {


                        String aaa="dfghjk";
                       if (response.body()!=null){
                           int statusCode = response.code();
                           if (statusCode == 200) {
//                               otp=String.valueOf(response.body().getCode());
//                               email=editemail.getText().toString();
//                               Toast.makeText(Forgot_password.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                   fill_otp.setText(response.body().getOtp());
                           }
                       }else {
                           try {
                               JSONObject job = new JSONObject(response.errorBody().string());
                               Log.e("dassadasda",job.getString("msg").toString());
                               Toast.makeText(Forgot_password.this, job.getString("msg").toString(), Toast.LENGTH_SHORT).show();
                           } catch (JSONException e) {
                               throw new RuntimeException(e);
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           }
                       }

                    }

                    @Override
                    public void onFailure(Call<Text_forget_pass_pojo> call, Throwable t) {

                    }
                });
            }
        });
        verify_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Forgot_password.this,New_Password_Activity.class);
//                startActivity(intent);
//                if (fill_otp.getText().toString()==resp)
                JSONObject forgetPasswordJson = new JSONObject();
                try {
                    forgetPasswordJson.put("email", email_forget.getText().toString());
                    forgetPasswordJson.put("code",fill_otp.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), forgetPasswordJson.toString());
                Web_Service_login.getClient().forget_password_verify_pojo(requestBody).enqueue(new Callback<Forget_password_verify_pojo>() {
                    @Override
                    public void onResponse(Call<Forget_password_verify_pojo> call, Response<Forget_password_verify_pojo> response) {
                        if (response.body()!=null){
                            if (response.code()==200){
                                Intent intent =new Intent(Forgot_password.this,New_Password_Activity.class);
                                String mail=email_forget.getText().toString();
                                intent.putExtra("mail",mail);
                                startActivity(intent);
                            }
                        }else {
                            try {
                                JSONObject job = new JSONObject(response.errorBody().string());
                                Log.e("dassadasda",job.getString("msg").toString());
                                Toast.makeText(Forgot_password.this, job.getString("msg").toString(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Forget_password_verify_pojo> call, Throwable t) {

                    }
                });

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email_forget.getText().toString().isEmpty()){
                    Toast.makeText(Forgot_password.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }else {
                    JSONObject forgetPasswordJson = new JSONObject();
                    try {
                        forgetPasswordJson.put("email", email_forget.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), forgetPasswordJson.toString());
                    Web_Service_login.getClient().text_forget_pass_pojo(requestBody).enqueue(new Callback<Text_forget_pass_pojo>() {
                        @Override
                        public void onResponse(Call<Text_forget_pass_pojo> call, Response<Text_forget_pass_pojo> response) {

                            if (response.body()!=null){
                                int statusCode = response.code();
                                if (statusCode == 200) {
//                               otp=String.valueOf(response.body().getCode());
//                               email=editemail.getText().toString();
//                               Toast.makeText(Forgot_password.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                                    fill_otp.setText(response.body().getCode());
                                } else if (statusCode==404) {
                                    Toast.makeText(Forgot_password.this, "Enter correct Mail", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                try {
                                    JSONObject job = new JSONObject(response.errorBody().string());
                                    Log.e("dassadasda",job.getString("msg").toString());
                                Toast.makeText(Forgot_password.this, job.getString("msg").toString(), Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(Forgot_password.this, "Code Resend", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Text_forget_pass_pojo> call, Throwable t) {

                        }
                    });
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}