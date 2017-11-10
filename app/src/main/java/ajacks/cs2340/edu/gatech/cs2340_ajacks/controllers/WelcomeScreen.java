package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    /**
     * What happens when btn_login is clicked. Redirects to LoginScreen
     * @param view view to be passed in
     */
    protected void onClick_btn_login(View view) {
        Intent intent = new Intent(WelcomeScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    /**
     * What happens when btn_register is clicked. Redirects to RegisterScreen
     * @param view view to be passed in
     */
    protected void onClick_btn_register(View view) {
        Intent intent = new Intent(WelcomeScreen.this, RegisterScreen.class);
        startActivity(intent);
    }

}
