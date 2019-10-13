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
        //This sets the view to the submit passed xlm file (R.layout.file)
        setContentView(R.layout.submit);

        //this gets the intent that was passed in when created
        Intent intent = getIntent();
        //gets a value from the intent


        //This code dose display stuff
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //sets width and height of window
        getWindow().setLayout((int)(width),(int)(height));

        //this sets ratings and reviews to the ones that match the IDs
        RatingBar rating = (RatingBar)findViewById(R.id.ratingBar);
        RatingBar ratingCleanliness = (RatingBar)findViewById(R.id.safetyRatingBarAdjust);
        RatingBar ratingSafety = (RatingBar)findViewById(R.id.cleanlinessRatingBarAdjust);
        TextView review = (TextView)findViewById(R.id.review);

        //Getting ready to go back to maps activity
        Intent maps = new Intent(Submit.this, MapsActivity.class);
        //passing information through
        maps.putExtra("review", review.getText());
        maps.putExtra("rating", rating.getNumStars());
        maps.putExtra("cleanliness", ratingCleanliness.getNumStars());
        maps.putExtra("safety", ratingSafety.getNumStars());
        maps.putExtra("location", );
        //Starts map activity
        startActivity(maps);

        //startActivity(maps);
    }
}
