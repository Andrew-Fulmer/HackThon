package com.example.gasstation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayLocation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaylocation);

        Intent intent = getIntent();
        final Bathroom bathroom = intent.getParcelableExtra("bathroom");

        TextView locationName = (TextView) findViewById(R.id.locationName);
        RatingBar overallRatingBar = (RatingBar) findViewById(R.id.overallRatingBar);
        RatingBar safetyRatingBar = (RatingBar) findViewById(R.id.safetyRatingBar);
        RatingBar cleanlinessRatingBar = (RatingBar) findViewById(R.id.cleanlinessRatingBar);
        TextView topThreeTags = (TextView) findViewById(R.id.topThreeTags);

        System.out.println(bathroom.reviews.size());

        locationName.setText(bathroom.name);
        overallRatingBar.setRating((int) (bathroom.overall()));
        safetyRatingBar.setRating((int) (bathroom.getSafety()));
        cleanlinessRatingBar.setRating((int) (bathroom.getCleanliness()));
        topThreeTags.setText("");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(width, height);

        Button button = (Button) findViewById(R.id.writeReview);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent submit = new Intent(DisplayLocation.this,Submit.class);
                submit.putExtra("bathroom",bathroom);
                startActivity(submit);
            }
        });
    }

}
