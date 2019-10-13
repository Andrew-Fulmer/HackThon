package com.example.gasstation;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.Marker;
import java.util.Hashtable;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener, OnMapLongClickListener{

    private GoogleMap mMap;
    private Hashtable<Marker,Bathroom> bathrooms = new Hashtable<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
        mMap.setOnMapLongClickListener((OnMapLongClickListener) this);
        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(-34, 151);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    @Override
    public boolean onMarkerClick (Marker marker){

        //Search for selected gas station
        Bathroom selected = bathrooms.get(marker);
        //Display selected Gastation
        startActivity(new Intent(MapsActivity.this,DisplayLocation.class));

        return true;
    }
    @Override
    public void onMapLongClick (LatLng point){
        //Open up dialog window
            //Add a location?
            //if yes get information on location
        //Make location
        startActivity(new Intent(MapsActivity.this,DisplayLocation.class));
    }
    public void onSearch(){
    
    }

}
