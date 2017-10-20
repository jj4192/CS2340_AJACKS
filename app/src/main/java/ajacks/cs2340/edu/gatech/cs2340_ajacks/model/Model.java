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

    private List<RatSighting> sightings;

    private int id;

    public Model() {
        allUsers = new ArrayList<User>();
        sightings = new ArrayList<RatSighting>();
        id = 0;
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

    /**
     * Checks whether the username is registered or not
     * @param username The username to check the database for
     * @return A boolean representing whether the username is registered
     */
    public boolean usernameTaken(String username) {
        for (User eachUser : allUsers) {
            if (eachUser.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public List<RatSighting> getAllSightings() {
        return sightings;
    }

    public RatSighting findItemById(int id) {
        for (RatSighting e : sightings) {
            if (e.getId() == id) return e;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    /**
     * Adds item at an index.
     */
    public void addItem(RatSighting sighting, int index) {
        sightings.add(index,sighting);
    }

    public void addItem(RatSighting sighting) {
        sightings.add(sighting);
    }

    /**
     * Increments ID for uniqueness
     * @return the latest unique ID
     */
    public int generateId() {
        return id++;
    }
}
