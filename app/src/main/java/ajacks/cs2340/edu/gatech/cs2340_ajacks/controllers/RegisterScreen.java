package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;

/**
 * An activity to register an account to use the app.
 */
public class RegisterScreen extends AppCompatActivity {
    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        //set up user type spinner
        Spinner userTypeSpinner = (Spinner) findViewById(R.id.spinner_user_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, new String[]{"User", "Admin"});
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
     * Handles registration logic when the register button is clicked.
     * @param view The current view of the app.
     */
    protected void onClick_btn_register(View view) {
        EditText username = (EditText) findViewById(R.id.tb_username);
        EditText password = (EditText) findViewById(R.id.tb_password);
        EditText email = (EditText) findViewById(R.id.tb_email);
        Spinner userType = (Spinner) findViewById(R.id.spinner_user_type);
        //check if username is already taken
        if (!(model.usernameTaken(username.getText().toString()))) {
            //add new user if valid credentials
            User user = new User(null, username.getText().toString(), password.getText().toString(), email.getText().toString(), userType.getSelectedItem().toString(), "unlocked");
            model.addNewUser(user);
            //move to first entry screen
            Intent intent = new Intent(RegisterScreen.this, FirstEntryScreen.class);
            startActivity(intent);
        } else {
            //if username taken, clear text-boxes
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterScreen.this).create();
            alertDialog.setMessage("Sign-up failed, username is already in use.");
            alertDialog.show();
            username.setText("");
            email.setText("");
            password.setText("");
        }
    }
}

