package com.example.gasstation;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.KeyEvent;
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



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener, OnMapLongClickListener{

    private GoogleMap mMap;
    private Hashtable<Marker,Bathroom> bathrooms = new Hashtable<>();
//<<<<<<< HEAD
    //private Hashtable<Marker,GasStation> gasStations = new Hashtable<>();

    //Widgets
    //private EditText mSearchText;


//=======

//ff0216b6ede406796cfbe910a506cbcfccfcd593
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
        mSearchText = (EditText) findViewById(R.id.input_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

//    private void init(){
//        Log.d(TAG, "init: initializing");
//        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
//                if(actionID == EditorInfo.IME_ACTION_SEARCH
//                        || actionId == EditorInfo.IME_ACTION_DONE
//                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
//                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
//                    //Execute A Search Method
//                    geoLocate();
//                }
//            }
//                                              }
//        );
//    }
   /* private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            geocoder.
            list = geocoder.getFromLocationName(searchString, 1);
        }
        catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }
        if(list.size() > 0) {
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    */

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
        mMap.setOnMapLongClickListener((OnMapLongClickListener) this);

        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(35.9097, -79.0460);

        //mMap.addMarker(new MarkerOptions().position(sydney).title("Woollen_Gymnasium"));    // I don't need the _
        float zoomIn = 17.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomIn));



        //mMap.setMyLocationEnabled(true);
    }
    @Override
    public boolean onMarkerClick (Marker marker){

        //Search for selected gas station
        Bathroom selected = bathrooms.get(marker);
        //Display selected Gastation
        //startActivity(new Intent(MapsActivity.this,DisplayLocation.class));

        return true;
    }
    @Override
    public void onMapLongClick (LatLng point){
        //Before switching to new activity need to make Intent
        // constructor takes(ThisClass.this,ClassYouWantToGoTo.class)
        Intent submit = new Intent(MapsActivity.this, Submit.class);
        //if you want to pass values to the new activity use theintent.putExtra("your id","the value")
        submit.putExtra("location",Math.round(point.latitude) + ":" + Math.round(point.longitude));
        //Starts the new activity
        startActivity(submit);

            //Add a location?
            //if yes get information on location
        //Make location
       //startActivity(new Intent(MapsActivity.this,DisplayLocation.class));
    }
    public void onSearch(){
    
    }

}
