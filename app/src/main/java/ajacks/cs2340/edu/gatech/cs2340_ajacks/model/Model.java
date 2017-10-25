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

    private List<RatSighting> sightings; //eventually remove
    private UserManager userManager;
    private int id;

    private Model() {
        sightings = new ArrayList<RatSighting>();
        userManager = new UserManager();
        id = 0;
    }

    /**
     * Adds a new user to the list of all users (calls userManageR)
     * @param u the user to be added
     * @return true if successful, false otherwise
     */
    public boolean addNewUser(User u) {
        //DEBUG: Log.d("Firebase", "adding new user from model");
        return userManager.addNewUser(u);
    }

    /**
     * Method to take in the current user's credentials and verify for login
     * @param username provided at login
     * @param password provided at login
     * @return 1 if valid, 0 if not (will expand later to handle banned/lockout)
     */
    public int checkCredentials(String username, String password) {
        return userManager.checkCredentials(username, password);
    }

    /**
     * Checks to see if a username for registration already exists in system
     * @param username the username in question
     * @return true if taken, false otherwise
     */
    public boolean usernameTaken(String username) {
        return userManager.usernameTaken(username);
    }

    /**************************************************************************************
     * All methods beyond this point should be removed eventually
     *************************************************************************************/

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

    public RatSighting getLastRatSighting() {
        if (sightings.size() != 0) {
            return sightings.get(sightings.size() - 1);
        }
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
