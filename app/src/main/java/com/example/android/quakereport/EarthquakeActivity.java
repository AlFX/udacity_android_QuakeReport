/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        final ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("6.66", "San Francisco", "June, 6th 2006"));
        earthquakes.add(new Earthquake("6.66", "London", "June, 6th 2006"));
        earthquakes.add(new Earthquake("6.66", "Tokyo", "June, 6th 2006"));
        earthquakes.add(new Earthquake("6.66", "Mexico City", "June, 6th 2006"));
        earthquakes.add(new Earthquake("6.66", "Moscow", "June, 6th 2006"));
        earthquakes.add(new Earthquake("6.66", "Rio de Janeiro", "June, 6th 2006"));
        earthquakes.add(new Earthquake("6.66", "Paris", "June, 6th 2006"));

        // Find a reference to the ListView in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new ArrayAdapter of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the ListView, so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}