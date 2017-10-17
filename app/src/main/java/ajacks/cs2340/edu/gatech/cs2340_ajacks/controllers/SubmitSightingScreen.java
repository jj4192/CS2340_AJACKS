package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Borough;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Coordinates;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Location;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.LocationType;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;

public class SubmitSightingScreen extends AppCompatActivity {
    Model model = Model.getInstance();

    private Spinner boroughSpinner;
    private Spinner locationTypeSpinner;

    private EditText date;
    private EditText time;
    private EditText x;
    private EditText y;
    private EditText address;
    private EditText zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_sighting_screen);

        date = (EditText) findViewById(R.id.tb_date);
        time = (EditText) findViewById(R.id.tb_time);
        x = (EditText) findViewById(R.id.tb_x);
        y = (EditText) findViewById(R.id.tb_y);
        address = (EditText) findViewById(R.id.tb_address);
        zipCode = (EditText) findViewById(R.id.tb_zip);

        boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);
        locationTypeSpinner = (Spinner) findViewById(R.id.spinner_location_type);

        ArrayAdapter boroughAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Borough.values());
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapter);

        ArrayAdapter locationTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, LocationType.values());
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);
    }

    protected void onClick_btn_cancel(View view) {
        //changes screen if cancel is pressed
        Intent intent = new Intent(SubmitSightingScreen.this, FirstEntryScreen.class);
        startActivity(intent);
    }

    private boolean validateInput() {
        Log.d("HELLO", date.getText().toString());
        return !date.getText().toString().isEmpty() && !time.getText().toString().isEmpty()
                && !x.getText().toString().isEmpty() && !y.getText().toString().isEmpty()
                && !address.getText().toString().isEmpty() && !zipCode.getText().toString().isEmpty();
    }

    protected void onClick_btn_submit(View view) {
        if (validateInput()) {
            Coordinates coords = new Coordinates(Float.valueOf(x.getText().toString()), Float.valueOf(y.getText().toString()));
            Location location = new Location(coords,
                    LocationType.getEnumValueByFullName(locationTypeSpinner.getSelectedItem().toString()),
                    zipCode.getText().toString(), address.getText().toString(), boroughSpinner.getSelectedItem().toString(),
                    Borough.getEnumValueByFullName(boroughSpinner.getSelectedItem().toString()));
            String dateAndTime = date.getText().toString() + " " + time.getText().toString();
            RatSighting sighting = new RatSighting(1, location, dateAndTime);
            model.addItem(sighting);
            Intent intent = new Intent(SubmitSightingScreen.this, FirstEntryScreen.class);
            startActivity(intent);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(SubmitSightingScreen.this).create();
            alertDialog.setMessage("Missing required field(s).");
            alertDialog.show();
        }
    }
}
