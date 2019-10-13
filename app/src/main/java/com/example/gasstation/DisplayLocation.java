package com.example.gasstation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayLocation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaylocation);

        Intent intent = getIntent();
        Bathroom bathroom = intent.getParcelableExtra("bathroom");

        TextView locationName = (TextView) findViewById(R.id.locationName);
        RatingBar overallRatingBar = (RatingBar) findViewById(R.id.overallRatingBar);
        RatingBar safetyRatingBar = (RatingBar) findViewById(R.id.safetyRatingBar);
        RatingBar cleanlinessRatingBar = (RatingBar) findViewById(R.id.cleanlinessRatingBar);
        TextView topThreeTags = (TextView) findViewById(R.id.topThreeTags);

        System.out.println(bathroom.reviews.size());

        locationName.setText(bathroom.name);
        overallRatingBar.setRating((int)(bathroom.overall()));
        safetyRatingBar.setRating((int)(bathroom.safety()));
        cleanlinessRatingBar.setRating((int)(bathroom.cleanliness()));
        topThreeTags.setText("");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(width,height);


    }
}
