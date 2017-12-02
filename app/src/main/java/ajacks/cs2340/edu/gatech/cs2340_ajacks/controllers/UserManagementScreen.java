package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;

public class UserManagementScreen extends AppCompatActivity {

    private final Model model = Model.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management_screen);
    }

    /**
     * Method for when submit button clicked.
     * @param view where the button is pressed
     */
    public void onClick_btn_submit(View view) {
        EditText username = (EditText) findViewById(R.id.tb_username);
        boolean res = model.unlockUser(username.getText().toString());
        AlertDialog resultDialog = new AlertDialog.Builder(UserManagementScreen.this).create();
        if (res) {
            resultDialog.setMessage("Account '" + username.getText().toString() + "' has been unlocked");
        }
        else {
            resultDialog.setMessage("Failure to unlock account or account already unlocked");
        }
        resultDialog.show();
        username.setText("");
    }
}
