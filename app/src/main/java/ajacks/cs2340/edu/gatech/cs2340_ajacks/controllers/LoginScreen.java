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
        if (model.checkCredentials(username.getText().toString(), password.getText().toString()) == 1) {
            Intent intent = new Intent(LoginScreen.this, FirstEntryScreen.class);
            startActivity(intent);
        } else {
            //notify if incorrect
            AlertDialog alertDialog = new AlertDialog.Builder(LoginScreen.this).create();
            alertDialog.setMessage("Login attempt failed.");
            alertDialog.show();
            //clear entry information
            username.setText("");
            password.setText("");
        }
        //still need to implement lockout after 3 attempts
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
}
