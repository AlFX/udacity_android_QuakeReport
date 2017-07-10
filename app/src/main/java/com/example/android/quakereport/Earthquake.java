package com.example.android.quakereport;

public class Earthquake {

    /*fields*/
    private Double mMagnitude;
    private String mLocation;
    private Long mTimeInMilliseconds;

    /*constructor method*/
    public Earthquake(Double magnitude, String location, Long timeInMilliseconds){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    /*helper methods*/
    public Double getMagnitude(){
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }
    public Long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }
}
