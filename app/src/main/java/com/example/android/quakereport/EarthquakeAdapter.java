package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /* The part of the location string from the USGS service that we use to determine whether or
    * not there is a location offset present (5 km north of The Shithole) */
    private static final String LOCATION_SEPARATOR = "of";

    /**
     * constructor method
     *
     * @param context     of the app
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /*helper method
    * returns a list item view that displays information about the earthquake at the give position
    * in the list of earthquakes*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*convertView is what recycles list objects once they are out of sight*/
        View listItemView = convertView;

        /*if the view is not there, create it (like when app just started*/
        /*this is a memory-costly operation which is why we do once and then recycle stuff*/
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        /*find custom object views that must be populated with info*/
        Earthquake currentEarthquake = getItem(position);

        /*magnitude*/
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);      /*find magnitude textview*/
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());      /*format magnitude with the helper method below*/
        magnitudeView.setText(formattedMagnitude);

        /*set proper background color on the magnitude circle.
        Fetch the background from the TextView, which is a GradientDrawable*/
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        /*get the appropriate background color based on the current earthquake magnitude*/
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        /*set the color on the magnitude circle*/
        magnitudeCircle.setColor(magnitudeColor);

        /*location*/
        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        /*date*/
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());      /*Create a new Date object from time in milliseconds from JSON*/

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);        /*find the TextView that will display the date*/
        String formattedDate = formatDate(dateObject);                              /*convert from Date to String*/
        dateView.setText(formattedDate);                                            /*display into text view*/

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        /*return the now populated object*/
        return listItemView;
    }

    /**
     * Return the color for the magnitude circle based on the intensity of the earthquake.
     * @param magnitude of the earthquake
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


}