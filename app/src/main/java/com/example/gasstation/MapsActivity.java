package com.example.gasstation;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;


import android.view.View;

import android.location.Geocoder;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.Marker;

//import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener, OnMapLongClickListener{

    private GoogleMap mMap;
    private Hashtable<Marker,Bathroom> bathrooms = new Hashtable<>();

    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();

        try{
            String location = intent.getStringExtra("location");
            String review = intent.getStringExtra("review");
            String rating = intent.getStringExtra("rating");

            float lat = 0;
            float lon = 0;
            String str = "";
            for(int i = 0; i < location.length(); i ++){
                if(location.charAt(i) == ':'){
                    lat = Float.parseFloat(str);
                    str = "";
                }else if(i == location.length() -1){
                    lon = Float.parseFloat(str);
                }
                str = str + location.charAt(i);
            }

            LatLng place = new LatLng(lat,lon);

            //bathrooms.add(new Bathroom());

        }catch(Exception  e){

        }

        // Edit text
        mSearchText = (EditText) findViewById(R.id.editText);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    // Found online...
    // Should update map with search result after search button is pressed

    // JAY LOOK AT THIS
    // Is it searching for what we want? We want it to search a city and find bathrooms in that city
    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address>addressList = null;

        if (location != null && !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
        mMap.setOnMapLongClickListener((OnMapLongClickListener) this);

        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(35.9097, -79.0460);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Woollen Gymnasium"));    // I don't need the _
        float zoomIn = 17.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomIn));

        //mMap.setMyLocationEnabled(true);
        // Could possibly later make the

    }
    @Override
    public boolean onMarkerClick (Marker marker){

        //Search for selected gas station
        Bathroom selected = bathrooms.get(marker);

        Intent display = new Intent(MapsActivity.this, DisplayLocation.class);
        //pass info
        display.putExtra("bathroom",selected);
        startActivity(display);

        return true;
    }
    @Override
    public void onMapLongClick (final LatLng point){

        Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;
        String str = "";
        try {

            addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1);
            str = addresses.get(0).getFeatureName().toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        final String strOut = str;

        /*
        String knownName = addresses.get(0).getFeatureName();
        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        */
        //final String print = knownName + " : " + address;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add-Location");
        builder.setMessage("Do you want to add this location " + strOut + "as a bathroom?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bathroom bathroom = new Bathroom(strOut,point);
                mMap.addMarker(new MarkerOptions().position(point).title(strOut));
                dialog.dismiss();
            }
        });builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void onSearch(){
    
    }

}
