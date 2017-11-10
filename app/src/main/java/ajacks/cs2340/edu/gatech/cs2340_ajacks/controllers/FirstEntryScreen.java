package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.InputStream;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;

public class FirstEntryScreen extends AppCompatActivity {

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_entry_screen);
        InputStream is = getResources().openRawResource(R.raw.rat_sightings_original);
        model.loadCSVData(is);
        //personalize first entry screen based on user
        TextView welcomeMessage = (TextView) findViewById(R.id.tv_welcome);
        welcomeMessage.setText("Welcome, " + model.getAppUser_username() + "!");
        Button userManagement = (Button) findViewById(R.id.btn_userManagement);
        Log.d("Visibility", model.getAppUser_userType());
        if ("Admin".equals(model.getAppUser_userType())) {
            userManagement.setVisibility(View.VISIBLE);
        } else {
            userManagement.setVisibility(View.INVISIBLE);
        }


    }

    /**
     * What happens when btn_logout is clicked. Redirects to WelcomeScreen.
     * @param view view to be passed in
     */
    protected void onClick_btn_logout(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, WelcomeScreen.class);
        startActivity(intent);
    }

    /**
     * What happens when btn_ViewSightings is clicked. Redirects to WelcomeScreen.
     * @param view view to be passed in
     */
    protected void onClick_btn_viewSightings(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, ViewSightingsScreen.class);
        startActivity(intent);
    }

    /**
     * What happens when btn_SubmitSighting is clicked. Redirects to SubmitSightingScreen.
     * @param view view to be passed in
     */
    protected void onClick_btn_submitSighting(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, SubmitSightingScreen.class);
        startActivity(intent);
    }

    /**
     * What happens when btn_SubmitSighting is clicked. Redirects to SubmitSightingScreen.
     * @param view view to be passed in
     */
    protected void onClick_btn_mapSightings(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, MapSightingsScreen.class);
        startActivity(intent);
    }

    /**
     * What happens when btn_viewGraph is clicked. Redirects to GraphScreen.
     * @param view view to be passed in
     */
    protected void onClick_btn_viewGraph(View view) {
        Intent intent = new Intent(FirstEntryScreen.this, GraphScreen.class);
        startActivity(intent);
    }
}
