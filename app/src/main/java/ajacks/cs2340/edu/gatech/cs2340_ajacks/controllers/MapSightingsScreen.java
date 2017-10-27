package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Location;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;


/**
 * Created by sarah on 10/21/17.
 */

public class MapSightingsScreen extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Model mFacade;
    private String[] datesArray;
    private Spinner startSpinner;
    private Spinner endSpinner;
    private boolean instantiateEndSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFacade = Model.getInstance();
        startSpinner = (Spinner) findViewById(R.id.start_spinner);
        endSpinner = (Spinner) findViewById(R.id.end_spinner);
        Object[] objArray = Model.getInstance().getDates().toArray();
        datesArray = Arrays.copyOf(objArray, objArray.length, String[].class);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, datesArray);
        startSpinner.setPrompt("Start Date");
        endSpinner.setPrompt("End Date");

        // Apply the adapter to the spinner
        startSpinner.setAdapter(adapter);
        endSpinner.setAdapter(adapter);
        instantiateEndSpinner = true;
        endSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!instantiateEndSpinner) {
                    filterByDateAndTime();
                } else {
                    instantiateEndSpinner = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<RatSighting> reportList = mFacade.getAllSightings();
        for (RatSighting r : reportList) {
            LatLng loc = new LatLng(r.getLocation().getCoordinates().getCoordX(), r.getLocation().getCoordinates().getCoordY());
            mMap.addMarker(new MarkerOptions().position(loc).title(Integer.toString(r.getId())).snippet(r.toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.activity_map_details, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    void filterByDateAndTime() {
        // Find start and end dates
        String startString = startSpinner.getSelectedItem().toString();
        DateFormat startDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = new Date();
        String endString = endSpinner.getSelectedItem().toString();
        DateFormat endDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date endDate = new Date();
        try {
            startDate = startDateFormat.parse(startString);
            endDate = endDateFormat.parse(endString);
            mMap.clear();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (endDate.compareTo(startDate) >= 0) {
            List<RatSighting> filteredList = new ArrayList<RatSighting>();
            for (RatSighting r : mFacade.getAllSightings()) {
                // Find rat sightings that fall within the bounds of start and end
                String currentString = r.getDateAndTime();
                DateFormat currentDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date currentDate = new Date();
                try {
                    currentDate = currentDateFormat.parse(currentString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0) {
                    filteredList.add(r);
                }
            }
            for (RatSighting r : filteredList) {
                LatLng loc = new LatLng(r.getLocation().getCoordinates().getCoordX(), r.getLocation().getCoordinates().getCoordY());
                mMap.addMarker(new MarkerOptions().position(loc).title(Integer.toString(r.getId())).snippet(r.toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            }

            mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        }
    }
}