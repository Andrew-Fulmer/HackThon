package com.example.gasstation;

public class Review {
    // Review Text
    private String rText = "";
    // Which tags (finite number, in array
    private boolean[] rTags;

    private double rOverall = 0;
    private double rSafety = 0;
    private double rClean = 0;

    Review( String text, boolean[] tags, double overall, double safety, double clean){
        rText = text;
        rTags = tags;
        rOverall = overall;
        rSafety = safety;
        rClean = clean;
    }

    public String comment(){
        return rText;
    }
    public boolean[] tags() {
        return rTags;
    }
    public double overall() {
        return rOverall;
    }
    public double safety() {
        return rSafety;
    }
    public double clean() {
        return rClean;
    }
}

