package com.example.acoxtseller.Fregment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Profile_show_data_Pojo;
import com.example.acoxtseller.Api_Pojo.Profile_update_pojo;
import com.example.acoxtseller.Home_Product_Page;
import com.example.acoxtseller.Profile_Activity;
import com.example.acoxtseller.R;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Fragment extends Fragment {

    EditText edit_gst,edit_dob,edit_gender,edit_email,edit_phone,user_name,seller_type_txt,seller_id_txt;
    ImageView edit_btn;
    View view;
    String waypathPhoto_book1;
    MultipartBody.Part imagePartPhoto_book1 = null;
    RelativeLayout save_btn,sellar_type,sellar_id;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RESULT_OK = 1;
    CircleImageView profile_image;

    String USer_name1="";
    String Edit_email1="";
    String Edit_phone1="";
    String Edit_gst1="";
    String Edit_gender1="";
    String Edit_dob1="";
    Uri imageUri;
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile_, container, false);

        save_btn=view.findViewById(R.id.save_btn);
        edit_dob=view.findViewById(R.id.edit_dob);
        edit_gst=view.findViewById(R.id.edit_gst);
        edit_gender= view.findViewById(R.id.edit_gender);
        edit_email=view.findViewById(R.id.edit_email);
        edit_phone=view.findViewById(R.id.edit_phone);
        user_name=view.findViewById(R.id.user_name);
        edit_btn=view.findViewById(R.id.edit_btn);
        profile_image=view.findViewById(R.id.profile_image);
        sellar_type=view.findViewById(R.id.sellar_type);
        seller_type_txt=view.findViewById(R.id.seller_type_txt);
        sellar_id=view.findViewById(R.id.sellar_id);
        seller_id_txt=view.findViewById(R.id.seller_id_txt);

//        bottomNavigationView=view.findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.profile);

        USer_name1=user_name.getText().toString();
        SharedPreferences pref = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String token = pref.getString("token", "");
        Log.d("fghjkluytfgbnm", ""+token);

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (Character.isDigit(character) || !Character.isLetterOrDigit(character)) {
                        return ""; // Remove numeric and special characters
                    }
                }
                return null; // Accept other characters
            }
        };
//        user_name.setFilters(new InputFilter[]{filter});

        Web_Service_login.getClient().profile_show_data_pojo("Bearer " + token, "profile_get")
                .enqueue(new Callback<Profile_show_data_Pojo>() {
                    @Override
                    public void onResponse(Call<Profile_show_data_Pojo> call, Response<Profile_show_data_Pojo> response) {
                        if (response.body().getCode()==200){

                            Picasso.get()
                                            .load("https://api.acoxt.fourbrick.in/" + response.body().getUser().getImg())
                                                    .into(profile_image);

                            user_name.setText(response.body().getUser().getFullname());
                            edit_email.setText(response.body().getUser().getEmail());
                            edit_phone.setText(response.body().getUser().getPhone());
                            edit_gst.setText(response.body().getUser().getGstNo());
                            seller_type_txt.setText(response.body().getUser().getRole());
                            seller_id_txt.setText(response.body().getUser().getUniqueId());

                        }
                        else {
                            Toast.makeText(getContext(), "Server Problem", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile_show_data_Pojo> call, Throwable t) {
                        // Handle network or other errors here
                    }
                });


        seller_type_txt.setEnabled(false);
        seller_id_txt.setEnabled(false);
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_dob.setEnabled(true);
                edit_gst.setEnabled(true);
                edit_gender.setEnabled(true);
                edit_email.setEnabled(true);
                edit_phone.setEnabled(true);
                user_name.setEnabled(true);
                profile_image.setEnabled(true);
                save_btn.setVisibility(View.VISIBLE);
                sellar_type.setVisibility(View.GONE);
                sellar_id.setVisibility(View.GONE);
            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();

            }
        });
        SharedPreferences prefre=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = prefre.edit();
        String Token = prefre.getString("token","");


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestBody username = RequestBody.create(MediaType.parse("text/plain"), user_name.getText().toString());
                RequestBody email = RequestBody.create(MediaType.parse("text/plain"), edit_email.getText().toString());
                RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), edit_phone.getText().toString());
                RequestBody gst_no = RequestBody.create(MediaType.parse("text/plain"), edit_gst.getText().toString());
                RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), edit_gender.getText().toString());
                RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), edit_dob.getText().toString());

                File imageFile = new File(String.valueOf(profile_image));
                RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), imageRequestBody);

                Web_Service_login.getClient().edit_profile("Bearer " +Token,username,email,phone,gst_no,gender,dob).enqueue(new Callback<Profile_update_pojo>() {
                    @Override
                    public void onResponse(Call<Profile_update_pojo> call, Response<Profile_update_pojo> response) {
                        if (response.body()!=null) {
//                            Profile_update_pojo profileUpdateResponse = response.body();
                            Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "error"+response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile_update_pojo> call, Throwable t) {
                    }
                });

                save_btn.setVisibility(View.GONE);
                edit_dob.setEnabled(false);
                edit_gst.setEnabled(false);
                edit_gender.setEnabled(false);
                edit_email.setEnabled(false);
                edit_phone.setEnabled(false);
                user_name.setEnabled(false);
                profile_image.setEnabled(false);

            }
        });



        edit_dob.setEnabled(false);
        edit_gst.setEnabled(false);
        edit_gender.setEnabled(false);
        edit_email.setEnabled(false);
        edit_phone.setEnabled(false);
        user_name.setEnabled(false);
        profile_image.setEnabled(false);

        return view;
    }


    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
             imageUri = data.getData();
            profile_image.setImageURI(imageUri);
        }
    }

    private RequestBody createRequestBodyFromUri(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            byte[] bytes = IOUtils.toByteArray(inputStream); // You can use any method to convert InputStream to byte array

            return RequestBody.create(MediaType.parse("multipart/form-data"), bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        Cursor cursor = requireContext().getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            fileName = cursor.getString(nameIndex);
            cursor.close();
            Log.d("ghjhgffghjk", ""+fileName);
        }
        return fileName;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Handle the back press event
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate to Home_Product_Page activity
                startActivity(new Intent(requireContext(), Home_Product_Page.class));
                requireActivity().finish();
            }
        });
    }


}