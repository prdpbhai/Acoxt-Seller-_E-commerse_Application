package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.Manifest;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.acoxtseller.Api_Pojo.Generate_QR_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumMap;
import java.util.Map;

public class Scanner_Activity extends AppCompatActivity {
    ImageView qr_code_scan,camera;
    Bitmap bitmap;
    RelativeLayout share_qr;
    TextView back_btn;
    private static final int REQUEST_STORAGE_PERMISSION = 1;
    private SharedPreferences sharedPreferences;
    private Web_Service_login webServiceLogin;
    private Bitmap qrCodeBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        qr_code_scan = findViewById(R.id.qr_code_scan);
        share_qr = findViewById(R.id.share_qr);
        camera=findViewById(R.id.camera);
        back_btn=findViewById(R.id.back_btn);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        } else {

        }

        SharedPreferences pref = Scanner_Activity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String Token = pref.getString("token", "");
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        Web_Service_login.getClient().generate_QR("Bearer " + Token, "generate_qr".toString()).enqueue(new Callback<Generate_QR_pojo>() {
            @Override
            public void onResponse(Call<Generate_QR_pojo> call, Response<Generate_QR_pojo> response) {
//                Toast.makeText(Scanner_Activity.this, "sdfghjk"+response.body(), Toast.LENGTH_SHORT).show();
                if (response.body() != null) {
//                    Toast.makeText(Scanner_Activity.this, "prdp", Toast.LENGTH_SHORT).show();
                    String imageUrl = "https://api.acoxt.fourbrick.in/" + response.body().getReferralCode(); // Replace with the URL of your image

                    Picasso.get()
                            .load(imageUrl)
                            .into(qr_code_scan);
                }
            }

            @Override
            public void onFailure(Call<Generate_QR_pojo> call, Throwable t) {

            }
        });

        Bitmap qrCodeBitmap = generateQRCode("https://api.acoxt.fourbrick.in/",400,400);
        qr_code_scan.setImageBitmap(qrCodeBitmap);
        String linkshare = "https://acoxtseller.page.link/H3Ed";

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scanner_Activity.this,Camera_Scanner.class);
                startActivity(intent);
            }
        });

        share_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQRCode(qrCodeBitmap,linkshare);
//                shareQRCode();
            }
        });
    }

    public Bitmap generateQRCode(String textToEncode, int width, int height) {
        try {
            // Create encoding hints for the QR code generator
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // Character encoding
            hints.put(EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.H);

            // Create a MultiFormatWriter to generate the QR code
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(textToEncode, BarcodeFormat.QR_CODE, width, height, hints);

            // Create a Bitmap to represent the QR code
            Bitmap qrCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            // Fill the Bitmap with the QR code data
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrCodeBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            return qrCodeBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





    private void shareQRCode(Bitmap qrCodeBitmap,String linkshare) {
        // Create a share intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("image/*");
        shareIntent.setType("text/plain");
        shareIntent.setType("image/text/plain/*");

        // Save the QR code bitmap to local storage
        String fileName = "qr_code_scan.png";
        File qrCodeFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);

        try {
            FileOutputStream out = new FileOutputStream(qrCodeFile);
            qrCodeBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Uri contentUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", qrCodeFile);
            shareIntent.putExtra(Intent.EXTRA_TEXT,linkshare);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        shareIntent.putExtra(Intent.EXTRA_TEXT,linkshare);

        // Start the share activity
        try {
            startActivity(Intent.createChooser(shareIntent, "Share QR Code"));
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }
}