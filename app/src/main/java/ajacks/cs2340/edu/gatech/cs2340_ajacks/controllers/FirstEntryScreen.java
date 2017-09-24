package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

public class FirstEntryScreen extends AppCompatActivity {

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
}
