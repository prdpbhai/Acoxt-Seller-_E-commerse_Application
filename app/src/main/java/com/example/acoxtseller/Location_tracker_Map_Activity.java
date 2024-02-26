//package com.example.acoxtseller;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.FragmentActivity;
//
//import com.directions.route.AbstractRouting;
//import com.directions.route.Route;
//import com.directions.route.RouteException;
//import com.directions.route.Routing;
//import com.directions.route.RoutingListener;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Location_tracker_Map_Activity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, RoutingListener {
//
//    private GoogleMap mMap;
//    private Location myLocation;
//    private boolean locationPermission = false;
//    private final static int LOCATION_REQUEST_CODE = 23;
//    private List<Polyline> polylines;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location_tracker_map);
//
//        requestPermission();
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    private void requestPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
//        } else {
//            locationPermission = true;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case LOCATION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    locationPermission = true;
//                    getMyLocation();
//                } else {
//                    // Permission denied
//                }
//                break;
//        }
//    }
//
//    private void getMyLocation() {
//        mMap.setMyLocationEnabled(true);
//        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//                myLocation = location;
//                LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());
//                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ltlng, 16f);
//                mMap.animateCamera(cameraUpdate);
////                FindRoutes(myLocation, myLocation); // Set myLocation as both start and end
//            }
//        });
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        if (locationPermission) {
//            getMyLocation();
//        }
//    }
//
//    public void FindRoutes(LatLng start, LatLng end) {
//        if (start == null || end == null) {
//            Toast.makeText(Location_tracker_Map_Activity.this, "Unable to get location", Toast.LENGTH_LONG).show();
//        } else {
//            Routing routing = new Routing.Builder()
//                    .travelMode(AbstractRouting.TravelMode.DRIVING)
//                    .withListener(this)
//                    .alternativeRoutes(true)
//                    .waypoints(start, end)
//                    .key("AIzaSyA1IDG4zNmQk9UUUL3x76p2NteDebOiwGQ")
//                    .build();
//            routing.execute();
//        }
//    }
//
//    @Override
//    public void onRoutingFailure(RouteException e) {
//        View parentLayout = findViewById(android.R.id.content);
//        Snackbar snackbar = Snackbar.make(parentLayout, e.toString(), Snackbar.LENGTH_LONG);
//        snackbar.show();
//    }
//
//    @Override
//    public void onRoutingStart() {
//        Toast.makeText(Location_tracker_Map_Activity.this, "Finding Route...", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
//        if (polylines != null) {
//            polylines.clear();
//        }
//
//        PolylineOptions polyOptions = new PolylineOptions();
//        LatLng polylineStartLatLng = new LatLng("");
//        LatLng polylineEndLatLng = new LatLng("29.5787","78.3174");
//
//        polylines = new ArrayList<>();
//
//        for (int i = 0; i < route.size(); i++) {
//            if (i == shortestRouteIndex) {
//                polyOptions.color(getResources().getColor(R.color.colorPrimary));
//                polyOptions.width(7);
//                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
//                Polyline polyline = mMap.addPolyline(polyOptions);
//                polylineStartLatLng = polyline.getPoints().get(0);
//                int k = polyline.getPoints().size();
//                polylineEndLatLng = polyline.getPoints().get(k - 1);
//                polylines.add(polyline);
//            }
//        }
//
//        MarkerOptions startMarker = new MarkerOptions();
//        startMarker.position(polylineStartLatLng);
//        startMarker.title("My Location");
//        mMap.addMarker(startMarker);
//
//        MarkerOptions endMarker = new MarkerOptions();
//        endMarker.position(polylineEndLatLng);
//        endMarker.title("Destination");
//        mMap.addMarker(endMarker);
//    }
//
//    @Override
//    public void onRoutingCancelled() {
//        FindRoutes(myLocation, myLocation); // Set myLocation as both start and end
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        FindRoutes(myLocation, myLocation); // Set myLocation as both start and end
//    }
//}
