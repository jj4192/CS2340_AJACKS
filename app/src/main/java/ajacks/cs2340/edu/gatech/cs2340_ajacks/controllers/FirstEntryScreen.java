package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Borough;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Coordinates;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Location;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.LocationType;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;

public class FirstEntryScreen extends AppCompatActivity {

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_entry_screen);
    }

    /**
     * What happens when btn_logout is clicked. Redirects to WelcomeScreen.
     * @param view
     */
    protected void onClick_btn_logout(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, WelcomeScreen.class);
        startActivity(intent);
    }

    /**
     * What happens when btn_ViewSightings is clicked. Redirects to WelcomeScreen.
     * @param view
     */
    protected void onClick_btn_viewSightings(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, ViewSightingsScreen.class);
        startActivity(intent);
    }

    /**
     * When the Load CSV button is click, the csv file gets parsed
     * @param view
     */
    protected void onClick_btn_csv(View view) {
        readCSV();
    }
    /**
     * Parses the CSV
     */
    private void readCSV() {
        try {
            InputStream is = getResources().openRawResource(R.raw.rat_sightings);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                Coordinates coord = new Coordinates(Float.parseFloat(tokens[49]), Float.parseFloat(tokens[50]));
                Location location = new Location(coord, LocationType.getEnumValueByFullName(tokens[7]), tokens[8], tokens[9], tokens[16], Borough.getEnumValueByFullName((tokens[23])));
                model.addItem(new RatSighting(id, location, tokens[1]));
            }
            //Prints out whats in the list
//            for (RatSighting rat : model.getAllSightings()) {
//                System.out.println(rat.toString());
//            }
            br.close();
        } catch (IOException e){
            e.getMessage();
        }
    }
}
