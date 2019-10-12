package com.example.gasstation;
import com.google.android.gms.maps.model.LatLng;

public class Bathroom{
    String name = "";
    int rating = 0;
    LatLng location = new LatLng(0,0);
    //Review[] reviews = [];
    String[] stringTags = new String[11]{"Free Parking", "Clean", "Multiple Stalls","Feminine Products Available", "Well-Lit", "Busy", "Vending Machines", "Broken Locks", "Smelly", "Dirty", "Clogged"};


    public double rating(){
        //take an array of reviews and calc the average rating
        double sum = 0;
        int numRes = 0;
        for (int i = 0; i < reviews.length; i++){
            if(reviews[i] > 0) {
                sum += reviews[i].rating;
                numRes ++;
            }
        }
        return sum / numRes;
    }
    public double cleanliness(){
        //calculate cleanlines ratings from all reviews
        double sum = 0;
        int numRes = 0;
        for (int i = 0; i < reviews.length; i++){
            if(reviews[i] > 0) {
                sum += reviews[i].rCleanliness;
                numRes ++;
            }
        }
        return sum / numRes;
    }

    public double safety() {
        //calculate safety rating
        double sum = 0;
        int numRes = 0;
        for (int i = 0; i < reviews.length; i++){
            if(reviews[i] > 0) {
                sum += reviews[i].rSafety;
                numRes ++;
            }
        }
        return sum / numRes;
    }

    public String[] topTags(){
        //finds top three tags
        int[] counts = new int[0,0,0,0,0,0,0,0,0,0,0];
        for(int i = 0; i < reviews.length; i ++){

            for(int j = 0; j< reviews.tags.length; j++){
                if(r.tags[j]){
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
            if(counts[k] > firstHigh){
                high0 = counts[k];
                i0 = k;
            } else if(counts[k] > secHigh){
                high1 = counts[k];
                i1 = k;
            }else if(counts[k] > thirdHigh){
                high2 = counts[k];
                i2 = k;
            }

        }

        String[] topThree = [stringTags[i0], stringTags[i1], stringTags[i2]];
        return topThree;
    }








}
