package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;  /*manually imported*/
import java.util.Date;              /*manually imported*/

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /*constructor method*/
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super (context, 0, earthquakes);
    }

    /*helper method*/
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());
        /*location*/
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(currentEarthquake.getLocation());

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

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}