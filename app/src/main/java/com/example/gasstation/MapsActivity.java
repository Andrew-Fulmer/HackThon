package com.example.gasstation;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;



import android.os.Parcelable;

import android.view.View;

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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener, OnMapLongClickListener{

    private GoogleMap mMap;
    private Hashtable<String,Bathroom> bathrooms = new Hashtable<>();

    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Running OnCreate!=========");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();

        /*try{
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

        }*/


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

        Intent intent = getIntent();

        try{
            //Hashtable<String,Bathroom> bathrooms = intent.getParcelableExtra("hash");
            Review r = intent.getParcelableExtra("submit");
            Bathroom b = intent.getParcelableExtra("bathroom");

            bathrooms.put(b.location.toString(),b);

            Set<String> keys = bathrooms.keySet();
            for(String key: keys){
                mMap.addMarker(new MarkerOptions().position(bathrooms.get(key).location).title(bathrooms.get(key).name));
            }

        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Running OnReady!=========");
        LatLng sydney = new LatLng(35.9097, -79.0460);
        float zoomIn = 17.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomIn));

        //mMap.setMyLocationEnabled(true);
        // Could possibly later make the

        //Bathroom bathroom = new Bathroom("First",new LatLng(35.9049,-79.0469));
        //Review review = new Review("This place sucks ", new boolean[]{}, 1, 2, 4);
       //Review review2 = new Review("This place sucks ", new boolean[]{}, 4, 1, 2);
       // bathroom.reviews.add(review);
       // bathroom.reviews.add(review2);
       // bathrooms.put(bathroom.location.toString(),bathroom);
       // mMap.addMarker(new MarkerOptions().position(bathroom.location).title(bathroom.name));

        boolean[] tags0 = new boolean[] {false,false,false,false,true,false,false,false,false,false};
        boolean[] tags1 = new boolean[] {false,false,true,false,false,false,false,false,true,false};
        boolean[] tags2 = new boolean[] {false,true,false,false,true,false,false,true,true,false};


        Bathroom bathroom = new Bathroom("Kenan Stadium",new LatLng(35.9049,-79.0469));
        Review review = new Review("The toilet is squeeky clean ", tags0, 4, 4, 5);
        Review review2 = new Review("It even has poo pourri! ", tags0, 5, 4, 5);
        Review review3 = new Review("I'm a lousy duke fan! ", new boolean[]{}, 2, 1, 4);
        bathroom.reviews.add(review);
        bathroom.reviews.add(review2);
        bathroom.reviews.add(review3);
        bathrooms.put(bathroom.location.toString(),bathroom);
        mMap.addMarker(new MarkerOptions().position(bathroom.location).title(bathroom.name));

        Bathroom bathroom1 = new Bathroom("Student Stores",new LatLng(35.909720,-79.048610));
        Review areview = new Review("The bathroom is great for hiding textbooks in my jacket", tags1, 5, 2, 2);
        Review areview2 = new Review("The floors are always slippery!!!", tags1, 4, 1, 2);
        bathroom1.reviews.add(areview);
        bathroom1.reviews.add(areview2);
        bathrooms.put(bathroom1..toString(),bathroom1);
        mMap.addMarker(new MarkerOptions().position(bathroom1.location).title(bathroom1.name));

        Bathroom bathroom2 = new Bathroom("Frank Porter Graham Student Union",new LatLng(35.910000,-79.047580));
        Review breview = new Review("The bathroom is great for hiding textbooks in my jacket", tags2, 5, 5, 3);
        Review breview2 = new Review("It's so big! so many stalls!", tags2, 4, 5, 4);
        bathroom2.reviews.add(breview);
        bathroom2.reviews.add(breview2);
        bathrooms.put(bathroom2.location.toString(),bathroom2);
        mMap.addMarker(new MarkerOptions().position(bathroom2.location).title(bathroom2.name));


    }
    @Override
    public boolean onMarkerClick (Marker marker){

        //Search for selected gas station
        Bathroom selected = bathrooms.get(marker.getPosition().toString());

        Intent display = new Intent(MapsActivity.this, DisplayLocation.class);
        //pass info

        //display.putExtra("hash", (Parcelable) bathrooms);
        display.putExtra("bathroom", (Parcelable) selected);
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
        builder.setMessage("Do you want to add this location " + strOut + " as a bathroom?");
            // Move this higher if I keep it
        //private string m_Text="";
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bathroom bathroom = new Bathroom(strOut,point);
                bathrooms.put(point.toString(),bathroom);
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