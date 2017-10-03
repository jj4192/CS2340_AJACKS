package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXC6120 on 9/29/2017.
 */

public class Model {


    private static final Model _instance = new Model();

    /**
     * Singleton design model. Create one instance of the model
     * @return the singleton model
     */
    public static Model getInstance() {
        return _instance;
    }

    private List<User> allUsers;

    public Model() {
        allUsers = new ArrayList<User>();
    }

    /**
     * Creates a new user to add to the database
     * @param newUser the new user object being added to the database
     */
    public void addUser(User newUser) {
        allUsers.add(newUser);
    }

    /**
     * Getter method to return a list of all current users from the database
     * @return list of all current users of type User
     */
    public List<User> getAllUsers() {
        return allUsers;
    }
}
