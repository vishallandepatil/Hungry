package com.hungry.customer.address;

import android.Manifest;
import android.app.Activity;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.hungry.customer.R;
import com.hungry.customer.address.model.AddressResult;
import com.hungry.customer.address.model.DeliveryAddresss;
import com.hungry.customer.address.viewmodel.DeliveryAddessModel;
import com.hungry.customer.databinding.ActivityAddressBinding;
import com.hungry.customer.mapview.MapsFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Address2 extends AppCompatActivity implements  View.OnClickListener {
    LocationManager locationManager;
    LocationListener listener;
    private List<Location> locations = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    ImageView place;
    TextView message, line2;
    boolean locationFound = true;
    MapsFragment   mapsFragment;
    ProgressBar progressBar1;
    double langitude;
    double latitude;
    DeliveryAddessModel deliveryAddessModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryAddessModel= ViewModelProviders.of(this).get(DeliveryAddessModel.class);

        ActivityAddressBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_address);
        mapsFragment = MapsFragment.newInstance(true, true, 0.0, 0.0);
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map, mapsFragment, MapsFragment.class.getName());
        fragmentTransaction.commit();

        binding.address.setOnClickListener(view -> {
            if (deliveryAddessModel.deliveryAddresss == null ) {
                mapsFragment.fetchLocation();
            }
        });
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        mapsFragment.setmListener(latLng -> {


            Geocoder geocoder;
            List<android.location.Address> addresses = null;
            geocoder = new Geocoder(Address2.this, Locale.getDefault());

            try {
                langitude=latLng.longitude ;
                latitude=latLng.latitude;
                //addresses = geocoder.getFromLocation(latitude, langitude, 5); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String line1 = "",lines2 = "";
                try {
                    addresses = geocoder.getFromLocation(latitude, langitude, 5);
                     line1 = addresses.get(0).getAddressLine(0);
                     lines2 = addresses.get(0).getFeatureName();
                }catch (Exception e ){
                    Toast.makeText(getBaseContext(),"Point6",Toast.LENGTH_LONG).show();
                }
                DeliveryAddresss deliveryAddresss =new DeliveryAddresss(""+langitude, ""+latitude,line1 , lines2 );

               try {
                   String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                   String city = addresses.get(0).getLocality();
                   deliveryAddessModel.deliveryAddresss = deliveryAddresss;
//                        String state = addresses.get(0).getAdminArea();
//                        String country = addresses.get(0).getCountryName();
//                        String postalCode = addresses.get(0).getPostalCode();
//                        String knownName = addresses.get(0).getFeatureName();


                   LatLng currentLocation = latLng;

                   message.setText(city);
                   line2.setText(address);
                   message.setEnabled(false);
               } catch (Exception e){
                   Toast.makeText(getBaseContext(),"POint 1",Toast.LENGTH_LONG).show();
               }
                progressBar1.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
                message.setEnabled(true);
                locationFound = true;
                progressBar1.setVisibility(View.GONE);
                Toast.makeText(getParent(),"POint 2",Toast.LENGTH_LONG).show();
            }


        });
        instantiateView();


        binding.setAddressModel(deliveryAddessModel);
        binding.setLifecycleOwner(this);
        deliveryAddessModel.addressResultMutableLiveData.observeForever(new Observer<AddressResult>() {
            @Override
            public void onChanged(AddressResult addressResult) {
                Gson gson = new Gson();

                String json = gson.toJson(addressResult);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",json);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        });




    }

    private void instantiateView() {
        place = findViewById(R.id.place);

        message = findViewById(R.id.message);
        line2 = findViewById(R.id.line2);
        progressBar1 = findViewById(R.id.progressBar1);
        message.setText(Html.fromHtml("<u>Add current location</u>"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mapsFragment.fetchLocation();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(Address2.this, "Permission denied to read your Geo location", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.place:

                break;


        }
    }





}
