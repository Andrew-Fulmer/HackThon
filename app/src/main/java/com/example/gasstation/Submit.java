package com.example.gasstation;
import android.content.Intent;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class Submit extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        TextView header = (TextView)findViewById(R.id.Header);
        header.setText(location);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        RatingBar rating = (RatingBar)findViewById(R.id.ratingBar);
        TextView review = (TextView)findViewById(R.id.review);

        
    }
}
