package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputFilter;

import com.example.acoxtseller.Api_Pojo.Email_verify_pojo;
import com.example.acoxtseller.Api_Pojo.Registration_pojo;
import com.example.acoxtseller.Api_Pojo.Registration_pojo_class;
import com.example.acoxtseller.Api_Pojo.Registration_register_number_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration_Activity extends AppCompatActivity {
    RelativeLayout submit,main_layout,validate,validate_email;
    EditText password,email,mobial_number,name,otp,otp_email;
    TextView login;
    ImageView back_btn;
    CheckBox checkbox;
    ImageButton toggleButton;
    private boolean isPasswordVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submit=findViewById(R.id.submit);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobial_number=findViewById(R.id.mobial_number);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        main_layout=findViewById(R.id.main_layout);
        back_btn=findViewById(R.id.back_btn);
        checkbox=findViewById(R.id.checkbox);
        validate=findViewById(R.id.validate);
        otp=findViewById(R.id.otp);
        toggleButton=findViewById(R.id.toggleButton);
        validate_email=findViewById(R.id.validate_email);
        otp_email=findViewById(R.id.otp_email);
        checkbox.setPaintFlags(checkbox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (Character.isDigit(character) || !Character.isLetterOrDigit(character)) {
                        return ""; // Remove numeric and special characters
                    }
                }
                return null;
            }
        };
//        name.setFilters(new InputFilter[]{filter});


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mobial_number.getText().toString().length()==10){
                   Web_Service_login.getClient().registration_register_number(mobial_number.getText().toString()).enqueue(new Callback<Registration_register_number_pojo>() {
                       @Override
                       public void onResponse(Call<Registration_register_number_pojo> call, Response<Registration_register_number_pojo> response) {
//                        Toast.makeText(Registration_Activity.this, ""+response.body().getOtp(), Toast.LENGTH_SHORT).show();
                           otp.setText(String.valueOf(response.body().getOtp()));
                       }

                       @Override
                       public void onFailure(Call<Registration_register_number_pojo> call, Throwable t) {

                       }
                   });

               }   else {
                   Toast.makeText(Registration_Activity.this, "Enter Correct Number", Toast.LENGTH_SHORT).show();
               }        }
        });
        validate_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()){
                    Toast.makeText(Registration_Activity.this, "Enter mail", Toast.LENGTH_SHORT).show();
                }   else {
                    Web_Service_login.getClient().email_verify_pojo(email.getText().toString()).enqueue(new Callback<Email_verify_pojo>() {
                        @Override
                        public void onResponse(Call<Email_verify_pojo> call, Response<Email_verify_pojo> response) {
                            if (response.body().getCode()==200){
                                Toast.makeText(Registration_Activity.this, "Otp Send", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Email_verify_pojo> call, Throwable t) {

                        }
                    });
                }
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

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

        SharedPreferences pref = Registration_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (name.getText().toString().isEmpty()){
                   Toast.makeText(Registration_Activity.this, "Enter Name", Toast.LENGTH_SHORT).show();
               }else {
                   if (email.getText().toString().isEmpty()){
                       Toast.makeText(Registration_Activity.this, "Enter Mail", Toast.LENGTH_SHORT).show();
                   }else {
                       if (password.getText().length()>=8){
                           if (PasswordValidator.isValidPassword(password.getText().toString())) {
                               if (checkbox.isChecked()){
                                   Web_Service_login.getClient().registration_pojo_class(name.getText().toString(),mobial_number.getText().toString(),email.getText().toString(),password.getText().toString(),"seller",otp.getText().toString(),otp_email.getText().toString()).enqueue(new Callback<Registration_pojo_class>() {
                                       @Override
                                       public void onResponse(Call<Registration_pojo_class> call, Response<Registration_pojo_class> response) {
                                           if (response.body()!=null) {

                                               if (response.body().getMsg().equals("User created successfully.")){

                                                   Toast.makeText(Registration_Activity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                                   Intent intent=new Intent(Registration_Activity.this,Login.class);
                                                   startActivity(intent);

                                               }else {
                                                   Toast.makeText(Registration_Activity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                               }

                                           }else {

                                               try {
                                                   JSONObject job = new JSONObject(response.errorBody().string());

                                                   Log.e("dassadasda",job.getString("msg").toString());
                                                   Toast.makeText(Registration_Activity.this, job.getString("msg").toString(), Toast.LENGTH_SHORT).show();


                                               } catch (JSONException e) {
                                                   throw new RuntimeException(e);
                                               } catch (IOException e) {
                                                   throw new RuntimeException(e);
                                               }

                                           }

                                       }

                                       @Override
                                       public void onFailure(Call<Registration_pojo_class> call, Throwable t) {

                                       }
                                   });
                               }else {
                                   Toast.makeText(Registration_Activity.this, "Please check the Checkbbox", Toast.LENGTH_SHORT).show();
                               }
                           } else {
                               // Password is invalid, display an error message to the user
                               // For example, you can show a Toast or set an error on an EditText
                               Toast.makeText(Registration_Activity.this, "Must have a minimum of 8 characters containing (a-z) and (0-9) and special character", Toast.LENGTH_SHORT).show();
                           }
                       }else {
                           Toast.makeText(Registration_Activity.this, "Enter minimum 8 character password", Toast.LENGTH_SHORT).show();
                       }
                   }
               }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration_Activity.this,Login.class);
                startActivity(intent);
//                Web_Service_login.getClient().registration_pojo_class(name.getText().toString(),mobial_number.getText().toString(),email.getText().toString(),password.getText().toString(),"seller");
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
