package com.example.hungry.address;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hungry.R;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Address extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    LocationManager locationManager;
    LocationListener listener;
    private List<Location> locations = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    ImageView place;
    TextView message, line2;
    boolean locationFound = true;
    GoogleMap addressMap;
    ProgressBar progressBar1;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        instantiateView();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        place.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);


    }

    private void instantiateView() {
        place = findViewById(R.id.place);
        floatingActionButton = findViewById(R.id.location);
        message = findViewById(R.id.message);
        line2 = findViewById(R.id.line2);
        progressBar1 = findViewById(R.id.progressBar1);
        message.setText(Html.fromHtml("<u>Add current location</u>"));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camer to the same location.
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        addressMap = googleMap;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.place:

                break;
            case R.id.location:
                addCustomerLocation();
                break;
        }
    }

    private void addCustomerLocation() {
        progressBar1.setVisibility(View.VISIBLE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("location_result", location.toString());
                Log.d("location_lat", String.valueOf(location.getLatitude()));
                Log.d("location_long", String.valueOf(location.getLongitude()));
                locations.add(0, location);
                if (locationFound) {
                    locationFound = false;
                    Geocoder geocoder;
                    List<android.location.Address> addresses;
                    geocoder = new Geocoder(Address.this, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                        Log.d("address_complete", addresses.get(0).toString());
                        Log.d("address_address", address);
                        Log.d("address_city", city);
                        Log.d("address_state", state);
                        Log.d("address_country", country);
                        Log.d("address_postalcode", postalCode);
                        Log.d("address_knownanme", knownName);
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        addressMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in customer location"));
                        addressMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                        addressMap.animateCamera(CameraUpdateFactory.newLatLng(currentLocation));
                        message.setText(city);
                        line2.setText(address);
                        message.setEnabled(false);
                        progressBar1.setVisibility(View.GONE);
                    } catch (IOException e) {
                        e.printStackTrace();
                        message.setEnabled(true);
                        progressBar1.setVisibility(View.GONE);
                    }


                } else
                {
                    message.setEnabled(true);
                    progressBar1.setVisibility(View.GONE);
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
                progressBar1.setVisibility(View.GONE);
            }
        };

        checkPermission();

    }

    private void checkPermission() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermission();
            }
        } else {
            // permission has been granted
            locationManager.requestLocationUpdates("gps", 5000, 0, listener);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        } else {
            // permission has not been granted yet. Request it directly.
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    onBackPressed();
                }
                return;
            }
            case 10:
                checkPermission();
                break;
            default:
                break;

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
