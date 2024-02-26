package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Google_login_pojo;
import com.example.acoxtseller.Api_Pojo.Login_pojo;
import com.example.acoxtseller.Api_Pojo.Verify_otp_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    RelativeLayout submit_btn,main_layout,email_signup;
    EditText mobial_number,fill_otp;

    TextView otp_code,resend_otp,signup;
    ImageView google_login;
    RelativeLayout sent_otp;
    String otp="";

    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private Context activityContext;
    private static final int GOOGLE_SIGN_IN = 101;
    private static final int REQUEST_LOCATION_PERMISSION = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sent_otp=findViewById(R.id.sent_otp);
        mobial_number=findViewById(R.id.mobial_number);
        resend_otp=findViewById(R.id.resend_otp);
        fill_otp=findViewById(R.id.fill_otp);
        signup=findViewById(R.id.signup);
        email_signup=findViewById(R.id.email_signup);
        main_layout=findViewById(R.id.main_layout);
        google_login=findViewById(R.id.google_login);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);

        } else {

//            requestLocationUpdates();
        }

        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGmailLogin();
                init();
            }
        });



        main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        email_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Login_with_email.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration_Activity.class);
                startActivity(intent);
            }
        });

        sent_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobial_number.getText().toString().length()>=10)
                {
                    Web_Service_login.getClient().pojo(mobial_number.getText().toString(),"seller").enqueue(new Callback<Login_pojo>() {
                        @Override
                        public void onResponse(Call<Login_pojo> call, Response<Login_pojo> response) {

                            if (response.code()==200) {
                                if (response.body().getMsg() != null) {
                                    if (response.body().getOtp() != null) {
                                        otp = String.valueOf(response.body().getOtp());
                                        sent_otp.setEnabled(false);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                runningupdate();
                                                sent_otp.setEnabled(true);
                                            }
                                        }, 1000);

                                    } else {
                                        Toast.makeText(Login.this, "Enter Correct Number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                try {
                                     JSONObject job = new JSONObject(response.errorBody().string());
                                    Log.e("dassadasda",job.getString("msg").toString());
                                    Toast.makeText(Login.this, job.getString("msg").toString(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        @Override

                                                public void onFailure(Call<Login_pojo> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(Login.this, "Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

////        FirebaseAuth mAuth = FirebaseAuth.getInstance();
////        mAuth = FirebaseAuth.getInstance();
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        findViewById(R.id.google_login).setOnClickListener(v -> signInWithGoogle(mGoogleSignInClient));
//
//

//        google_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });


//        ProgressDialog progressDialog = new ProgressDialog(Login.this);
//        progressDialog.setTitle("Creating Account");
//        progressDialog.setMessage("we are creating account");
//
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("YOUR_WEB_CLIENT_ID")
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//                        // Handle connection failure
//                    }
//                })
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();



    }



    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signInWithGoogle(GoogleSignInClient client) {
        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == RC_SIGN_IN) {
//            Log.d("fghjklghj", ""+data);
//            GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();
//            Log.d("fghjklghj", ""+account);
//            if (account != null) {
//                firebaseAuthWithGoogle(account.getIdToken());
//            }
//        }

//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        // User signed in successfully, handle the user object
//                    } else {
//                        // Sign-in failed, handle the error
//                    }
//                });}



    void runningupdate(){

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String Token = sharedPreferences.getString("token","");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.otp_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.slide_in_bottom;
        Button submit_otp=dialog.findViewById(R.id.submit_button);
        TextView otp_text=dialog.findViewById(R.id.otp_text);
        EditText otp1 =dialog.findViewById(R.id.otp1);
        EditText otp2 =dialog.findViewById(R.id.otp2);
        EditText otp3 =dialog.findViewById(R.id.otp3);
        EditText otp4 =dialog.findViewById(R.id.otp4);
        EditText otp5 =dialog.findViewById(R.id.otp5);
        EditText otp6 =dialog.findViewById(R.id.otp6);

        //here
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

        otp_text.setText(otp);

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String otp = otp1.getText().toString() +
                        otp2.getText().toString() +
                        otp3.getText().toString() +
                        otp4.getText().toString() +
                        otp5.getText().toString() +
                        otp6.getText().toString();
//                Toast.makeText(Login.this, ""+otp, Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                if(otp!=null){
                    if (mobial_number.getText().toString().isEmpty())
                    {
                        Toast.makeText(Login.this, "Please Enter Both Fields", Toast.LENGTH_SHORT).show();
                    }else {
                        Web_Service_login.getClient().verify_otp(mobial_number.getText().toString(),otp,"fixed_seller,moving_seller").enqueue(new Callback<Verify_otp_pojo>() {
                            @Override
                            public void onResponse(Call<Verify_otp_pojo> call, Response<Verify_otp_pojo> response) {
                                if (response.body()!=null){

                                    Log.d("dfghjkllkjhgfd", ""+response.body().getCode());

                                    prefsEditor.putString("token",response.body().getToken());
                                    prefsEditor.putString("status","loginn");
                                    prefsEditor.putString("usernamee",response.body().getUser().getName());
//                                    Intent intent=new Intent(Login.this,Enter_GST_Num.class);
//                                    startActivity(intent);
//                                    String gst = sharedPreferences.getString("gst","");
                                    prefsEditor.apply();
//                                    Toast.makeText(Login.this, "Login Succesful", Toast.LENGTH_SHORT).show();


                                    if (response.body().getResponse().getGst().equals("gst is available")){
                                        Log.d("hjkjhhk", "hii"+response.body().getResponse().getStorename());
                                        if (response.body().getResponse().getStorename().equals("storeName is available")){
                                            Log.d("ghkjhjklkj", response.body().getResponse().getShopAddress());
                                            if (response.body().getResponse().getShopAddress().equals("shopAddress is available")){
                                                Log.d("ghkjhjkhgfdlkj", "ki");
                                                if (response.body().getResponse().getShippingMethod().equals("ShippingMethod is  available")){

                                                    Intent intent=new Intent(Login.this,Home_Product_Page.class);
                                                    startActivity(intent);
                                                }else {
                                                    Intent intent=new Intent(Login.this,Select_your_preferred_shipping.class);
                                                    startActivity(intent);
                                                }
                                            }else {
                                                Intent intent=new Intent(Login.this,Provide_your_shop_address.class);
                                                startActivity(intent);
                                            }
                                        }else {
                                            Intent intent=new Intent(Login.this,Name_your_ACOXT_Store.class);
                                            startActivity(intent);
                                        }
                                    }else {
                                        Intent intent=new Intent(Login.this,Enter_GST_Num.class);
                                        startActivity(intent);
                                    }
                                }else {
                                    Toast.makeText(Login.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Verify_otp_pojo> call, Throwable t) {

                            }
                        });
                    }
                }

            }
        });
        dialog.show();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void init() {
        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id_google))
                .requestEmail()
                .build();
        Log.d("fghjkllkjhgf", ""+gso);
    }

    private void setupGmailLogin() {
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(Login.this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.e("TAG", e.getMessage(), e);
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Web_Service_login.getClient().ssologin(user.getDisplayName(),"",user.getEmail(),user.getUid(),"customer").enqueue(new Callback<Google_login_pojo>() {
                            @Override
                            public void onResponse(Call<Google_login_pojo> call, Response<Google_login_pojo> response) {
                                if (response.code()==200){
                                    Intent intent = new Intent(Login.this, Map_Activity.class);
                                    prefsEditor.putString("token", response.body().getToken());
                                    prefsEditor.putString("status", "Success");
                                    prefsEditor.putString("username",response.body().getUserdata().getName());
                                    prefsEditor.apply();
                                    intent.putExtra("number", "cheak");
                                    startActivity(intent);
                                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(activityContext, "something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Google_login_pojo> call, Throwable t) {

                            }
                        });

                    } else {
                        // Handle sign-in failure
                        Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}