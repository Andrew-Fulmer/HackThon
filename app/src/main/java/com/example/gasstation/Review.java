package com.example.gasstation;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    // Review Text
    private String rText = "";
    // Which tags (finite number, in array
    private boolean[] rTags ;

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

    protected Review(Parcel in) {
        rText = in.readString();
        rTags = in.createBooleanArray();
        rOverall = in.readDouble();
        rSafety = in.readDouble();
        rClean = in.readDouble();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rText);
        dest.writeBooleanArray(rTags);
        dest.writeDouble(rOverall);
        dest.writeDouble(rSafety);
        dest.writeDouble(rClean);
    }
}

