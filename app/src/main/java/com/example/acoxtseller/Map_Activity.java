package com.example.acoxtseller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Api_Pojo.Map_pojo;
import com.example.acoxtseller.Web_Service.Web_Service_login;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Map_Activity extends FragmentActivity implements OnMapReadyCallback {
    private  static final int REQUEST_LOCATION_PERMISSION=1;
    private GoogleMap mMap;
    TextView txt_loc, txtloca, save,manual_location,back;
    String str_locality;
    String str_address;
    Double latitude,longitude;
    LocationManager locationManager;
    LocationListener locationListener;
    RelativeLayout allow_location;
    private Marker marker;
    private List<LatLng> pathPoints; // List to store the path points
    private Polyline pathLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//        setContentView(R.layout.activity_map);
        //        txtloca=findViewById(R.id.txtloca);
                txt_loc=findViewById(R.id.txt_loc);
                save=findViewById(R.id.save);
        allow_location=findViewById(R.id.allow_location);
                SharedPreferences pref=getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = pref.edit();
                String Token = pref.getString("token","");
        Log.d("dfghhgfd", "onCreate: "+Token);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


                manual_location=findViewById(R.id.manual_location);
                manual_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Map_Activity.this,Enter_Address.class);
                        startActivity(intent);
                    }
                });
        allow_location.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Log.d("dfghhgsdfgnfdlk", "onCreate: "+Token);
                        Web_Service_login.getClient().map_pojo("Bearer " +Token, latitude,longitude).enqueue(new Callback<Map_pojo>() {
                            @Override
                            public void onResponse(Call<Map_pojo> call, Response<Map_pojo> response) {
                                if (response.body()!=null)
                                {
//                                    Toast.makeText(Map_Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Map_Activity.this, Home_Product_Page.class);
                                    myEdit.putString("location",str_locality +" "+ str_address);
//                                    Toast.makeText(Map_Activity.this, ""+str_address, Toast.LENGTH_SHORT).show();
                                    myEdit.apply();
                                    Log.d("vnjkfvnuirwounf", ""+str_locality);
                                    Log.d("ygdfwedgedbyeg", ""+str_address);
                                    startActivity(intent);


                                }
                            }

                            @Override
                            public void onFailure(Call<Map_pojo> call, Throwable t) {

                            }
                        });
                    }
                });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Web_Service_login.getClient().map_pojo("Bearer " +Token, latitude,longitude).enqueue(new Callback<Map_pojo>() {
                    @Override
                    public void onResponse(Call<Map_pojo> call, Response<Map_pojo> response) {
                        if (response.body()!=null)
                        {

                            SharedPreferences pref=getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = pref.edit();
//                                    Toast.makeText(Map_Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Map_Activity.this, Home_Product_Page.class);
                            myEdit.putString("location",str_locality +" "+ str_address);
//                                    Toast.makeText(Map_Activity.this, ""+str_address, Toast.LENGTH_SHORT).show();
                            myEdit.apply();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Map_pojo> call, Throwable t) {

                    }
                });
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);
        // Initialize the location manager and request location updates
         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
         requestLocationUpdates();
         pathPoints = new ArrayList<>();


    }

    private void requestLocationUpdates(){
        // Check for location permissions
         if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                     REQUEST_LOCATION_PERMISSION);
             return;
         }
         locationListener = new LocationListener() {
             @Override
             public void onLocationChanged(Location location) {
                 // Handle location updates
                  updateLocationOnMap(location);
             }
             @Override
             public void onStatusChanged(String s, int i, Bundle bundle) {

             }
             @Override
             public void onProviderEnabled(String s) {

             }
             @Override
             public void onProviderDisabled(String s) {
             }

    };
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,0,0,locationListener);
    }
     private void updateLocationOnMap(Location location){
     latitude = location.getLatitude();
     longitude = location.getLongitude();
     Geocoder geocoder = new Geocoder(getApplicationContext());
     try {
         List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
         String address = addresses.get(0).getLocality() + ":";
         address += addresses.get(0).getCountryName();
         LatLng latLng = new LatLng(latitude, longitude);
         if (addresses == null || addresses.size() == 0) {

         } else {
             Address address1 = addresses.get(0);
             String str_postcode = address1.getPostalCode();
             String str_Country = address1.getCountryName();
             String str_state = address1.getAdminArea();
             String str_district = address1.getSubAdminArea();
             str_locality = address1.getLocality();
             str_address = address1.getFeatureName();
             txt_loc.setText(str_address+" "+str_locality);
             Log.d("uiewrhfwefi",""+str_address);
             Log.d("ndhcbsydubhc",""+str_Country);
             Log.d("hhbdsbs",""+str_state);
             Log.d("hfbuyfbs",""+str_district);
             Log.d("nxbusy",""+str_locality);
             Log.d("hcvctacsdk",""+str_postcode);
         }
         Log.d("tfghjb",""+latLng);
         if (marker != null) {
             marker.remove();
         }
         marker = mMap.addMarker(new MarkerOptions().position(latLng).title(""));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
     } catch (IOException e) {
         e.printStackTrace();
     }
     }
     @Override
      protected void onStop() {
    super.onStop();
    locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
    }
}