package com.example.android.quakereport;

public class Earthquake {

    /*fields*/
    private Double mMagnitude;
    private String mLocation;
    private Long mTimeInMilliseconds;
    private String mUrl;

    /*constructor method*/
    public Earthquake(Double magnitude, String location, Long timeInMilliseconds, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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
    public String getWebsite() {
        return mUrl;
    }
}
