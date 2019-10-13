package com.example.gasstation;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Bathroom{
    String name = "";
    int rating = 0;
    LatLng location = new LatLng(0,0);
    ArrayList<Review> reviews;
    String[] stringTags = new String[] {"Free Parking", "Clean", "Multiple Stalls","Feminine Products Available", "Well-Lit", "Busy", "Vending Machines", "Broken Locks", "Smelly", "Dirty", "Clogged"};
    String[] comments;

    Bathroom(String inName, LatLng inLocation, int inRating, ArrayList<Review> inReviews, String[] inStringTags, String[] inComments) {
        name = inName;
        rating = inRating;
        location = inLocation;
        reviews = inReviews;
        stringTags = inStringTags;
        comments = inComments;
    }

    Bathroom( String inName, LatLng inLocation) {
        name = inName;
        location = inLocation;
        rating = 0;
        reviews = new ArrayList<Review>();
    }
        //Needs ratings, tags, location, name, reviews

    public String[] getComments(){
        for (int i = 0; i < reviews.size(); i++){
            comments[comments.length] = reviews.get(i).comment();
        }
        return comments;
    }

    public double overall(){
        //take an array of reviews and calc the average rating
        double sum = 0;
        int numRes = 0;
        for (int i = 0; i < reviews.size(); i++){
            Review r = reviews.get(i);
            if(r.overall() > 0) {
                sum += r.overall();
                numRes ++;
            }
        }
        return sum / numRes;
    }
    public double cleanliness(){
        //calculate cleanlines ratings from all reviews
        double sum = 0;
        int numRes = 0;
        for (int i = 0; i < reviews.size(); i++){
            if(reviews.get(i).clean() > 0) {
                sum += reviews.get(i).clean();
                numRes ++;
            }
        }
        return sum / numRes;
    }

    public double safety() {
        //calculate safety rating
        double sum = 0;
        int numRes = 0;
        for (int i = 0; i < reviews.size(); i++){
            if(reviews.get(i).safety() > 0) {
                sum += reviews.get(i).safety();
                numRes ++;
            }
        }
        return sum / numRes;
    }

    public String[] topTags(){
        //finds top three tags
        int[] counts = new int[] {0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0; i < reviews.size(); i ++){
            Review r = reviews.get(i);
            for(int j = 0; j< (r.tags()).length; j++){
                if(r.tags()[j]){
                    counts[j] ++;
                }
            }
        }

        int i0= 0;
        int i1 = 0;
        int i2 = 0;

        int high0 = 0;
        int high1 = 0;
        int high2 = 0;

        for(int k = 0; k < counts.length; k++){
            if(counts[k] > high0){
                high0 = counts[k];
                i0 = k;
            } else if(counts[k] > high1){
                high1 = counts[k];
                i1 = k;
            }else if(counts[k] > high2){
                high2 = counts[k];
                i2 = k;
            }

        }

        String[] topThree = new String[] {stringTags[i0], stringTags[i1], stringTags[i2]};
        return topThree;
    }

    public void addReview(Review newReview){
        reviews.add(newReview);
    }
}
