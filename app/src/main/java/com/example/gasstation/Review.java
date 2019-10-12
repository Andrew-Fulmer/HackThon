package com.example.gasstation;

public class Review {
    private String rText = "";
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
}

