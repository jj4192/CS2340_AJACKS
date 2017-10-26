package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.Arrays;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFacade = Model.getInstance();

        Spinner startSpinner = (Spinner) findViewById(R.id.start_spinner);
        Spinner endSpinner = (Spinner) findViewById(R.id.end_spinner);
        Object[] objArray = Model.getInstance().getDates().toArray();
        String[] stringArray = Arrays.copyOf(objArray, objArray.length, String[].class);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray);
        // Apply the adapter to the spinner
        startSpinner.setAdapter(adapter);
        endSpinner.setAdapter(adapter);
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

}
