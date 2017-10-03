package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.app.AlertDialog;

import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;




import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

/**
 * Activity for logging into the app.
 */
public class LoginScreen extends AppCompatActivity {

    Model model = new Model();
    List<User> allUsers =  model.getAllUsers();

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
            //notify incorrect
            AlertDialog alertDialog = new AlertDialog.Builder(LoginScreen.this).create();
            alertDialog.setMessage("login attempt failed");
            alertDialog.show();
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
        Log.d("Hello" + String.valueOf(model.getAllUsers().size()), "hello");
        for (User eachUser : model.getAllUsers()) {
            Log.d(eachUser.getUserName(), "hi");
            if (eachUser.getUserName().equals(username)) {
                //username.equals("user") && password.equals("pass")
                String pass = eachUser.getPassword();
                if (password.equals(pass)) {
                    return 1;
                }
            }
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
