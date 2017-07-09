package com.example.android.quakereport;

public class Earthquake {

    /*fields*/
    private String mMagnitude;
    private String mLocation;
    private String mDate;

    /*constructor method*/
    public Earthquake(String magnitude, String location, String date){
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    /*helper methods*/
    public String getMagnitude(){
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }
    public String getDate(){
        return mDate;
    }
}
