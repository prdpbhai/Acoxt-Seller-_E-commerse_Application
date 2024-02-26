package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.acoxtseller.Api_Pojo.Email_login_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_with_email extends AppCompatActivity {
    EditText email_addr,password;
    TextView signup,forget_password;
    RelativeLayout submit,main_layout;
    ImageView back_btn;
    ImageButton toggleButton;
    private boolean isPasswordVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);
        signup=findViewById(R.id.signup);
        password=findViewById(R.id.password);
        email_addr=findViewById(R.id.email_addr);
        submit=findViewById(R.id.submit);
        main_layout=findViewById(R.id.main_layout);
//        back_btn=findViewById(R.id.back_btn);
        forget_password=findViewById(R.id.forget_password);
        toggleButton=findViewById(R.id.toggleButton);


        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;

                if (isPasswordVisible) {
                    password.setTransformationMethod(null); // Show password
                    toggleButton.setImageResource(R.drawable.show); // Change the icon to indicate hidden password
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance()); // Hide password
//                    toggleButton.setImageResource(R.drawable.hide); // Change the icon to indicate visible password
                }
            }
        });


        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_with_email.this, Forgot_password.class);
                startActivity(intent);

            }
        });
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (PasswordValidator.isValidPassword(password.getText().toString())) {
                    Web_Service_login.getClient().email_login_pojo(email_addr.getText().toString(),password.getText().toString(),"fixed_seller,moving_seller").enqueue(new Callback<Email_login_pojo>() {
                        @Override
                        public void onResponse(Call<Email_login_pojo> call, Response<Email_login_pojo> response) {
//                        Toast.makeText(Login_with_email.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                            if (response.body()!=null){
                                prefsEditor.putString("token",response.body().getToken());
                                prefsEditor.putString("status","loginn");
                                prefsEditor.apply();

//                            Toast.makeText(Login_with_email.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(Login_with_email.this,Enter_GST_Num.class);
//                            startActivity(intent);

                                if (response.body().getResponse().getGst().equals("gst is available")){
                                    Log.d("hjkjhhk", "hii"+response.body().getResponse().getStoreName());
                                    if (response.body().getResponse().getStoreName().equals("storeName is available")){
                                        Log.d("ghkjhjklkj", response.body().getResponse().getShopAddress());
                                        if (response.body().getResponse().getShopAddress().equals("shopAddress is available")){
                                            Log.d("ghkjhjkhgfdlkj", "ki");
                                            if (response.body().getResponse().getShippingMethod().equals("ShippingMethod is  available")){

                                                Intent intent=new Intent(Login_with_email.this,Home_Product_Page.class);
                                                startActivity(intent);
                                            }else {
                                                Intent intent=new Intent(Login_with_email.this,Select_your_preferred_shipping.class);
                                                startActivity(intent);
                                            }
                                        }else {
                                            Intent intent=new Intent(Login_with_email.this,Provide_your_shop_address.class);
                                            startActivity(intent);
                                        }
                                    }else {
                                        Intent intent=new Intent(Login_with_email.this,Name_your_ACOXT_Store.class);
                                        startActivity(intent);
                                    }
                                }else {
                                    Intent intent=new Intent(Login_with_email.this,Enter_GST_Num.class);
                                    startActivity(intent);
                                }

                            }else {

                                try {
                                    JSONObject job = new JSONObject(response.errorBody().string());
                                    Log.e("dassadasda",job.getString("msg").toString());
                                    Toast.makeText(Login_with_email.this, job.getString("msg").toString(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Email_login_pojo> call, Throwable t) {

                        }
                    });
//                }else {
//                    Toast.makeText(Login_with_email.this, "Must have a minimum of 8 characters containing (a-z) and (0-9) and special character", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_with_email.this,Registration_Activity.class);
                startActivity(intent);
            }
        });
    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class PasswordValidator {

        private static final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        public static boolean isValidPassword(String password) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
    }
}