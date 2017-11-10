package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Borough;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Coordinates;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.LocationType;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;

/**
 * Created by sarah on 10/6/17.
 */

public class DetailedRatSightingScreen extends AppCompatActivity {
    protected static RatSighting sighting;
    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_sighting_list_format);

        int sightingID = getIntent().getIntExtra("Sighting ID", 0);
        sighting = model.findItemById(sightingID);

        int id = sighting.getId();
        String dateAndTime = sighting.getDateAndTime();
        Coordinates coordinates = sighting.getLocation().getCoordinates();
        LocationType locationType = sighting.getLocation().getLocationType();
        String borough = sighting.getLocation().getBorough().toString();
        String address = sighting.getLocation().getAddress();
        String zipCode = sighting.getLocation().getZipCode();
        String city = sighting.getLocation().getCity();

        TextView idField = (TextView) findViewById(R.id.tv_sightingId);
        TextView dateAndTimeField = (TextView) findViewById(R.id.tv_date);
        TextView coordinatesField = (TextView) findViewById(R.id.tv_coordinates);
        TextView locationTypeField = (TextView) findViewById(R.id.tv_locationType);
        TextView boroughField = (TextView) findViewById(R.id.tv_borough);
        TextView addressField = (TextView) findViewById(R.id.tv_address);
        TextView zipCodeField = (TextView) findViewById(R.id.tv_zipCode);
        TextView cityField = (TextView) findViewById(R.id.tv_city);

        idField.setText("Rat Sighting #: " + id);
        dateAndTimeField.setText("Date: " + dateAndTime);
        coordinatesField.setText("Coordinates: " + coordinates);
        locationTypeField.setText("Location Type: " + locationType);
        boroughField.setText("Borough: " + borough);
        addressField.setText("Address: " + address);
        zipCodeField.setText("Zip Code: " + zipCode);
        cityField.setText("City: " + city);
    }
}
