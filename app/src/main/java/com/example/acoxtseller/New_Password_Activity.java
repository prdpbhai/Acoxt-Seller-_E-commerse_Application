package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.New_pass_genrate_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
New_Password_Activity extends AppCompatActivity {
    TextView back;

    EditText new_password,confirm_password;
    RelativeLayout set_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        back=findViewById(R.id.back);
        set_password=findViewById(R.id.set_password);
        new_password=findViewById(R.id.new_password);
        confirm_password=findViewById(R.id.confirm_password);

        Intent intent = getIntent();
        String receivedMail = intent.getStringExtra("mail");

        set_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject forgetPasswordJson = new JSONObject();
                try {
                    forgetPasswordJson.put("new_password", new_password.getText().toString());
                    forgetPasswordJson.put("confirm_password",confirm_password.getText().toString());
                    forgetPasswordJson.put("email",receivedMail.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), forgetPasswordJson.toString());

                if (PasswordValidator.isValidPassword(new_password.getText().toString())) {
                    if (PasswordValidator.isValidPassword(confirm_password.getText().toString())) {
                        if (new_password.getText().toString().equals(confirm_password.getText().toString())){
                            Web_Service_login.getClient().new_pass_genrate_pojo(requestBody).enqueue(new Callback<New_pass_genrate_pojo>() {
                                @Override
                                public void onResponse(Call<New_pass_genrate_pojo> call, Response<New_pass_genrate_pojo> response) {
                                    if (response.body()!=null){
                                        Toast.makeText(New_Password_Activity.this, "Password Change Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(New_Password_Activity.this,Login_with_email.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<New_pass_genrate_pojo> call, Throwable t) {

                                }
                            });
                        }else {
                            Toast.makeText(New_Password_Activity.this, "Enter Both same password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(New_Password_Activity.this, "Enter Both same password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(New_Password_Activity.this, "Must have a minimum of 8 characters containing (a-z) and (0-9) and special character", Toast.LENGTH_SHORT).show();

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