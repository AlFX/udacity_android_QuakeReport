package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        Earthquake currentEarthquake = getItem(position);

        /*find custom object views that must be populated with info*/

        /*magnitude*/
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());
        /*location*/
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(currentEarthquake.getLocation());
        /*date*/
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentEarthquake.getDate());

        /*return the now populated object*/
        return listItemView;
    }

}
