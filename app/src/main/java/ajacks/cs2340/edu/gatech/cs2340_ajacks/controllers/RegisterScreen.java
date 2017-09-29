package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;

public class RegisterScreen extends AppCompatActivity {
    List<User> allUsers = new ArrayList<User>();
    int numUsers = 0;


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

    public boolean usernameTaken(String username) {
        for (User eachUser : allUsers) {
            if (eachUser.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    protected void onClick_btn_register(View view) {
        EditText username = (EditText) findViewById(R.id.tb_username);
        EditText password = (EditText) findViewById(R.id.tb_password);

        if (!usernameTaken(username.getText().toString())) {
            Intent intent = new Intent(RegisterScreen.this, FirstEntryScreen.class);
            startActivity(intent);
            String newUser = "user" + numUsers;
            User user = new User(username.getText().toString(), password.getText().toString());
            allUsers.add(user);
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

