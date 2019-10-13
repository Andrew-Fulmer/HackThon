package com.example.gasstation;
import android.content.Intent;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Hashtable;

public class Submit extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //This sets the view to the submit passed xlm file (R.layout.file)
        setContentView(R.layout.submit);

        //this gets the intent that was passed in when created
        Intent intent = getIntent();
        //gets a value from the intent

        final Bathroom bathroom = intent.getParcelableExtra("bathroom");
        //final Hashtable<String,Bathroom> bathrooms = intent.getParcelableExtra("hash");
        //This code dose display stuff
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //sets width and height of window
        getWindow().setLayout((int)(width),(int)(height));


        //this sets ratings and reviews to the ones that match the IDs
        final RatingBar rating = (RatingBar)findViewById(R.id.ratingBar);
        final RatingBar ratingCleanliness = (RatingBar)findViewById(R.id.safetyRatingBarAdjust);
        final RatingBar ratingSafety = (RatingBar)findViewById(R.id.cleanlinessRatingBarAdjust);
        final TextView review = (TextView)findViewById(R.id.review);
        final CheckBox tag0 = (CheckBox)findViewById(R.id.checkBox);
        final CheckBox tag1 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox tag2 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox tag3 = (CheckBox)findViewById(R.id.checkBox4);
        final CheckBox tag4 = (CheckBox)findViewById(R.id.checkBox5);
        final CheckBox tag5 = (CheckBox)findViewById(R.id.checkBox6);
        final CheckBox tag6 = (CheckBox)findViewById(R.id.checkBox7);
        final CheckBox tag7 = (CheckBox)findViewById(R.id.checkBox8);
        final CheckBox tag8 = (CheckBox)findViewById(R.id.checkBox9);
        final CheckBox tag9 = (CheckBox)findViewById(R.id.checkBox10);
        final CheckBox[] tags = new CheckBox[]{tag0, tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9};






        Button button = (Button) findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Review r = new Review(review.toString(),new boolean[]{},(double)rating.getRating(),(double)ratingSafety.getRating(),(double)ratingCleanliness.getRating());

                bathroom.reviews.add(r);
                final Bathroom b = bathroom;
                Intent maps = new Intent(Submit.this, MapsActivity.class);

                maps.putExtra("submit", (Parcelable) r);
                maps.putExtra("bathroom", b);
                //submit.putExtra("hash", (Parcelable) bathrooms);
                //maps.putExtra("rating", rating.getNumStars());
                //maps.putExtra("cleanliness", ratingCleanliness.getNumStars());
                //maps.putExtra("safety", ratingSafety.getNumStars());
                System.out.println("Running Submit!=========");

                for(int i = 0; i < tags.length; i++){
                    maps.putExtra("tag " + i, tags[i].isChecked());
                }
                startActivity(maps);
            }
        });
    }
}
