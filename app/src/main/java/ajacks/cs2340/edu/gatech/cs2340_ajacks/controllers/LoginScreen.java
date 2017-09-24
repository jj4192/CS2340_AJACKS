package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;


import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

public class LoginScreen extends AppCompatActivity {

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
            //clear entry information
            username.setText("");
            password.setText("");
        }
    }

    /**
     * FOR NOW, returns 1 (success) if credentials are user and pass
     * @param username
     * @param password
     * @return
     */
    private int checkCredentials(String username, String password) {
        if (username.equals("user") && password.equals("pass")) {
            return 1;
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
}
