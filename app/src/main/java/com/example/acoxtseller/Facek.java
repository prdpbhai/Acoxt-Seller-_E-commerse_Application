package com.example.acoxtseller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Facek extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, RoutingListener {
    private GoogleMap mMap; // Initialize mMap here
    private List<Polyline> polylines = null;
    private LatLng start;
//    private LatLng end = new LatLng(29.5787, 78.3174); // End latitude and longitude
    Marker previousEndMarker;
    Double lat,lon;
    private LatLng end =null;

    MarkerOptions startMarker;
    MarkerOptions endMarker;

    private static final String BROKER_URL = "tcp://broker.mqttdashboard.com:1883";
    private static final String CLIENT_ID = "your_client_id";
    private MqttHandler mqttHandler;
    private List<List<Polyline>> allPolylines = new ArrayList<>();

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    String orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tracker_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SharedPreferences pref=Facek.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String  Token = pref.getString("token","");
        float latFloat = pref.getFloat("locationlat", 0.0f);
        float lonFloat = pref.getFloat("locationlon", 0.0f);
         orderid=pref.getString("orderid","");
         lat = (double) latFloat;
         lon = (double) lonFloat;
        Log.d("rtyokjw", ""+lat);
        Log.d(" ", ""+lon);
        Log.d("dfghjoiuytewa", ""+orderid);
        end=new LatLng(lat,lon);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        createLocationRequest();
        createLocationCallback();

        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID);
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000); // Update location every 10 seconds
        locationRequest.setFastestInterval(2000); // The fastest update interval, in milliseconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    if (location != null) {

                        String topic = orderid;
                        JSONObject locationObject = new JSONObject();
                        try {
                            locationObject.put("latitude", location.getLatitude());
                            locationObject.put("longitude", location.getLongitude());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String message = locationObject.toString();

                        mqttHandler.publish(topic,message);

                        LatLng newLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        start = newLocation;
                        getCurrentLocation();
                    }
                }
            }
        };
    }


    private void startLocationUpdates() {
        try {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } catch (SecurityException e) {
            // Handle the exception
        }
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void getCurrentLocation() {

        if (start != null && end != null) {
            Findroutes(start, end);
        } else {
//            Toast.makeText(Facek.this, "Unable to get location", Toast.LENGTH_LONG).show();
        }
    }

    public void Findroutes(LatLng Start, LatLng End) {
        if (Start == null || End == null) {
//            Toast.makeText(Facek.this, "Unable to get location", Toast.LENGTH_LONG).show();
        } else {
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(Facek.this)
                    .alternativeRoutes(true)
                    .waypoints(Start, End)
                    .key("AIzaSyA1IDG4zNmQk9UUUL3x76p2NteDebOiwGQ")
                    .build();
            routing.execute();
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        // Handle routing failure
    }

    @Override
    public void onRoutingStart() {
//        Toast.makeText(Facek.this, "Finding Route...", Toast.LENGTH_LONG).show();
    }

    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        List<Polyline> currentPolylines = new ArrayList<>();
        currentPolylines.clear();
        for (int i = 0; i < route.size(); i++) {
            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(R.color.colorPrimary));
            polyOptions.width(10);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            if (i != shortestRouteIndex) {
                polyline.remove();
            } else {
                currentPolylines.add(polyline);
            }
        }
        clearAllPolylines();
        allPolylines.add(currentPolylines);
        startMarker = new MarkerOptions();
        startMarker.position(route.get(shortestRouteIndex).getPoints().get(0));
        startMarker.title("Start");
        BitmapDescriptor startIcon = BitmapDescriptorFactory.fromResource(R.drawable.delivery_bike);
        startMarker.icon(startIcon);
        if(previousEndMarker!=null){
            previousEndMarker.remove();
        }
        previousEndMarker=mMap.addMarker(startMarker);

        endMarker = new MarkerOptions();
        endMarker.position(end);
        endMarker.title("Destination");
        BitmapDescriptor endIcon = BitmapDescriptorFactory.fromResource(R.drawable.home_icon);
        endMarker.icon(endIcon);
    }

    private void clearAllPolylines() {
        for (List<Polyline> polylineList : allPolylines) {
            for (Polyline polyline : polylineList) {
                polyline.remove();
            }
        }
        allPolylines.clear();
    }

    public void onRoutingCancelled() {
        // Handle routing cancellation
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap; // Initialize the mMap object here
        getCurrentLocation();
        startLocationUpdates();

        double radiusInMeters = 50000;

        mMap.addCircle(new CircleOptions()
                .center(end) // Set the center of the circle
                .radius(radiusInMeters) // Set the radius in meters
                .strokeColor(Color.RED) // Set the circle's outline color
                .fillColor(Color.TRANSPARENT) // Set the circle's fill color
        );

//        mMap.addMarker(new MarkerOptions()
//                .position(start)
//                .title("Starting Location")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); // Set the marker's icon color

        // Move the camera to the starting location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(end, 14)); // Adjust the zoom level as needed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
