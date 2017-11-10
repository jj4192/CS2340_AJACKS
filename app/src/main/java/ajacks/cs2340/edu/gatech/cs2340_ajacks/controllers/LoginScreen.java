package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.app.AlertDialog;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

/**
 * Activity for logging into the app.
 */
public class LoginScreen extends AppCompatActivity {

    private final Model model = Model.getInstance();
    private int numAttempts;
    private String attemptingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        //for login attempt counting
        numAttempts = 0;
        attemptingUser = "none";
    }

    /**
     * What happens when btn_login is clicked
     * @param view view to be passed in
     */
    public void onClick_btn_login(View view) {
        //pull from text boxes
        EditText username = (EditText) findViewById(R.id.tb_username);
        EditText password = (EditText) findViewById(R.id.tb_password);
        //tracking number of attempts
        if (attemptingUser.equals(username.getText().toString())) {
            //if already tried before, increment
            numAttempts++;
        } else if ("none".equals(attemptingUser)) {
            //if first time trying, increment
            attemptingUser = username.getText().toString();
            numAttempts++;
        } else {
            //if different username, start at 1
            attemptingUser = username.getText().toString();
            numAttempts = 1;
        }
        //check credentials
        if (model.checkCredentials(username.getText().toString(), password.getText().toString()) == 1) {
            Intent intent = new Intent(LoginScreen.this, FirstEntryScreen.class);
            startActivity(intent);
        } else if (model.checkCredentials(username.getText().toString(), password.getText().toString()) == -1) {
            //if a locked account
            AlertDialog loginLockoutDialog = new AlertDialog.Builder(LoginScreen.this).create();
            loginLockoutDialog.setMessage("This account is locked. Contact admin to be unlocked");
            loginLockoutDialog.show();
            //clear entry information
            username.setText("");
            password.setText("");
        } else {
            //notify if incorrect
            AlertDialog loginFailDialog = new AlertDialog.Builder(LoginScreen.this).create();
            loginFailDialog.setMessage("Login attempt failed.");
            loginFailDialog.show();
            //implement lockout after 3 attempts
            if (numAttempts == 3) {
                model.lockAccount(attemptingUser);
                AlertDialog lockoutDialog = new AlertDialog.Builder(LoginScreen.this).create();
                lockoutDialog.setMessage("You have been locked out of your account.");
                lockoutDialog.show();
            }
            //clear entry information
            username.setText("");
            password.setText("");
        }

    }

    /**
     * What happens when btn_cancel is clicked
     * @param view view to be passed in
     */
    public void onClick_btn_cancel(View view) {
        //changes screen if cancel is pressed
        Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
        startActivity(intent);
    }
}
