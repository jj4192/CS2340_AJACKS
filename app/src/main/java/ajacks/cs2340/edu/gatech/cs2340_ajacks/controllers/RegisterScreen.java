package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

public class RegisterScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
    }

    /**
     * What happens when btn_cancel is clicked
     * @param view
     */
    protected void onClick_btn_cancel(View view) {
        //changes screen if cancel is pressed
        Intent intent = new Intent(RegisterScreen.this, WelcomeScreen.class);
        startActivity(intent);
    }

    protected void onClick_btn_register(View view) {
        //register logic
    }
}

