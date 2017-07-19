package com.example.android.quakereport;

/**
 * this is a JAVADOC
 * https://en.wikipedia.org/wiki/Javadoc
 * @author AlFX
 *
 * Earthquake.java is the definition of the basic custom object
 * declare Fields = member variables
 * declare constructor method {@link com.example.android.quakereport.Earthquake}
 *
 * the constructor is initialized with as many parameters as specified in its declaration
 * Initiliazation = the Fields are filled in with the above said parameters
 *
 * helper methods are meant to return some Member variables = fields from within the object
 * */

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
    public String getUrl() {
        return mUrl;
    }
}
