package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.app.AlertDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Coordinates;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Location;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Borough;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.LocationType;




import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

/**
 * Activity for logging into the app.
 */
public class LoginScreen extends AppCompatActivity {

    Model model = Model.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    /**
     * What happens when btn_login is clicked
     * @param view
     */
    protected void onClick_btn_login(View view) {
        //pull from text boxes
        EditText username = (EditText) findViewById(R.id.tb_username);
        EditText password = (EditText) findViewById(R.id.tb_password);
        //check credentials
        if (checkCredentials(username.getText().toString(), password.getText().toString()) == 1) {
            Intent intent = new Intent(LoginScreen.this, FirstEntryScreen.class);
            startActivity(intent);
        } else {
            //notify incorrect
            AlertDialog alertDialog = new AlertDialog.Builder(LoginScreen.this).create();
            alertDialog.setMessage("login attempt failed");
            alertDialog.show();
            //clear entry information
            username.setText("");
            password.setText("");
        }
    }

    /**
     * FOR NOW, returns 1 (success) if credentials are user and pass
     * @param username the username of the user
     * @param password the password of the user
     * @return will return 1 if success and 0 otherwise
     */
    private int checkCredentials(String username, String password) {
        for (User eachUser : model.getAllUsers()) {
            Log.d(eachUser.getUserName(), "hi");
            if (eachUser.getUserName().equals(username)) {
                //username.equals("user") && password.equals("pass")
                Log.d("Usernames are equal: ", username);
                String pass = eachUser.getPassword();
                if (password.equals(pass)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * What happens when btn_cancel is clicked
     * @param view
     */
    protected void onClick_btn_cancel(View view) {
        //changes screen if cancel is pressed
        Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
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
