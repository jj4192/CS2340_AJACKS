package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import android.os.Debug;
import android.util.Log;

import java.io.InputStream;
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

    private UserManager userManager;
    private RatSightingManager ratSightingManager;
    private int id;

    private Model() {
        userManager = new UserManager();
        ratSightingManager = new RatSightingManager();
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

    /***
     * Method that takes in a newly created rat sighting and passes it to the rat manager to be
     * added to the database
     * @param sighting sighting created
     * @return true when added
     */
    public boolean addNewSighting(RatSighting sighting) {

        return ratSightingManager.addNewSighting(sighting);
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

    /**
     * Gives list of all rat sightings in DB
     * @return allSightings from RatSightingManager
     */
    public List<RatSighting> getAllSightings() {
        return ratSightingManager.getAllSightings();
    }

    /**
     * Finds a specific rat sighting by its ID
     * @param id the id of the rat sighting to find
     * @return null if nonexistent, otherwise the RatSighting
     */
    public RatSighting findItemById(int id) {
        for (RatSighting e : ratSightingManager.getAllSightings()) {
            if (e.getId() == id) return e;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    /**
     * For returning next available rat sighting id
     * @return
     */
    public int useUniqueRatSightingID() {
        return ratSightingManager.useUniqueRatSightingID();
    }

    /**
     * Load CSV data into rat list
     * @param is the input stream that can only be accessed in an activity class
     */
    public void loadCSVData(InputStream is) {
        ratSightingManager.loadCSVData(is);
    }

}
