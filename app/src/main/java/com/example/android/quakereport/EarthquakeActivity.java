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

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /* URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=2&limit=10";

    /* constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you are using multiple loaders. */
    private static final int EARTHQUAKE_LOADER_ID = 1;


    /* when we get to the onPostExecute() method, we need to update the ListView.
     * The only way to update the contents of the list is to update the data set within
     * the EarthquakeAdapter. To access and modify the instance of the EarthquakeAdapter,
     * we need to make it a global variable in the EarthquakeActivity. */

    /* Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    /* TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        Log.i(LOG_TAG, "TEST: Earthquake Activity onCreate() called");

        // Find a reference to the ListView in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Find the empty TextView meant to display when no earthquakes are available
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());


        // Set the adapter on the ListView, so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);


        /* set an itemClickListener on the ListView which sends an intent to a web browser to open a
        * website with more information about the selected earthquake */
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        /* Get a reference to  the ConnectivityManager to check state of newtwork connectivity */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        /* Get details on the currently active default data network */
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        /* If there is a network connection, fetch data */
        if (networkInfo != null && networkInfo.isConnected()) {

            /* get a reference to the LoaderManager in order to interact with loaders */
            LoaderManager loaderManager = getLoaderManager();

            /*initialize the loader. Pass in the int ID constant defined above and pass in null for the bundle.
            Pass in this activity for the LoaderCallbacks parameter (which is valid because this activity
            implements the LoaderCallbacks interface) */
            Log.i(LOG_TAG, "TEST: calling initLoader() ...");
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        } else {
            /* Otherwise display error
            * First, hide loading indicator so error message will be visible */
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            /* Update empty state with no connection error message */
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    /** here are the 3 callback methods that come with a LoaderManager
     * <a href="https://developer.android.com/guide/components/loaders.html">Here</a>
     *
     * {@link #onCreateLoader(int, Bundle)} called when the system needs a new Loader
     * {@link #onLoadFinished(Loader, List)} called when the loader has finished loading data
     * {@link #onLoaderReset(Loader)} called when a loader is reset, activity is destroyed = data unavailable
     * */

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG, "TEST: onCreateLoader() called...");

        /* create a new loader for the given URL */
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        Log.i(LOG_TAG, "TEST: onLoadFinished() called...");

        /* Hide loading indicator because the data has been loaded */
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        /* Set empty state text to display "No earthquakes found." */
        mEmptyStateTextView.setText(R.string.no_earthquakes);

        /* clear the adapter of previous earthquake data */
        mAdapter.clear();

        /* if there is a valid list of Earthquakes then add them to the adapter's
        * data set. This will trigger the ListView to update */
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {

        Log.i(LOG_TAG, "TEST: onLoaderReset() called...");

        /* loader reset, so we can clear out our existing data */
        mAdapter.clear();
    }
}