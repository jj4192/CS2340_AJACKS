package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;

/**
 * An activity to register an account to use the app.
 */
public class RegisterScreen extends AppCompatActivity {
    Model model = new Model();
    List<User> allUsers = new ArrayList<User>();
    int numUsers = 0;

    //Spinner that encodes user type for a registering user.
    private Spinner userTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        userTypeSpinner = (Spinner) findViewById(R.id.spinner_user_type);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, new String[]{"User", "Admin"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
    }

    /**
     * When the cancel button is clicked, the app returns to the welcome screen.
     * @param view The current view of the app.
     */
    protected void onClick_btn_cancel(View view) {
        //changes screen if cancel is pressed
        Intent intent = new Intent(RegisterScreen.this, WelcomeScreen.class);
        startActivity(intent);
    }

    /**
     * Checks whether the username is registered or not
     * @param username The username to check the database for
     * @return A boolean representing whether the username is registered
     */
    public boolean usernameTaken(String username) {
        for (User eachUser : model.getAllUsers()) {
            if (eachUser.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles registration logic when the register button is clicked.
     * @param view The current view of the app.
     */
    protected void onClick_btn_register(View view) {
        EditText username = (EditText) findViewById(R.id.tb_username);
        EditText password = (EditText) findViewById(R.id.tb_password);
        EditText email = (EditText) findViewById(R.id.tb_email);
        Spinner userType = (Spinner) findViewById(R.id.spinner_user_type);
        if (!usernameTaken(username.getText().toString())) {
            Intent intent = new Intent(RegisterScreen.this, FirstEntryScreen.class);
            startActivity(intent);
            User user = new User(username.getText().toString(), password.getText().toString(), email.getText().toString(), "User");
            allUsers.add(user);
            model.addUser(user);
            //Do we want a successful signup make people login or should it log them in?
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterScreen.this).create();
            alertDialog.setMessage("Signup failed.");
            alertDialog.show();
            username.setText("");
            password.setText("");
        }
    }
}

