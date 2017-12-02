package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;


import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by KXC6120 on 9/29/2017.
 * Facade for the backend in MVC
 */

public final class Model {


    private static final Model _instance = new Model();

    /**
     * Singleton design model. Create one instance of the model
     * @return the singleton model
     */
    public static Model getInstance() {
        return _instance;
    }

    private final UserManager userManager;
    private final RatSightingManager ratSightingManager;

    private Model() {
        userManager = new UserManager();
        ratSightingManager = new RatSightingManager();
    }

    /**
     * Adds a new user to the list of all users (calls userManageR)
     * @param u the user to be added
     * @return true if successful, false otherwise
     */
    public boolean addNewUser(User u) {
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
     * Locks the account with the specified username
     * @param username account to lock
     */
    public void lockAccount(String username) {
        userManager.lockAccount(username);
    }

    public String getAppUser_username() {
        if (userManager.getAppUser() != null) {
            return userManager.getAppUser().getUserName();
        }
        return "";
    }

    public String getAppUser_userType() {
        if (userManager.getAppUser() != null) {
            return userManager.getAppUser().getUserType();
        }
        return "error";
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
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * For returning next available rat sighting id
     * @return returns id
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

    /**
     * Gets a list of unique Dates for use in the spinner
     * @return list of unique dates
     */
    public List<String> getDates() {
        return ratSightingManager.getDates();
    }

    /**
     * Filters rat sightings based on a date range. All dates should be formatted MM/dd/yyyy
     * @param startString The earliest bound of the date range
     * @param endString The latest bound of the date range
     * @return The list of rat sightings in the date range
     * @throws ParseException If dates are not in the correct format
     */
    public List<RatSighting> filterByDateAndTime(String startString, String endString) throws ParseException {
        // Find start and end dates
        DateFormat startDateFormat = new SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault());
        Date startDate;
        DateFormat endDateFormat = new SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault());
        Date endDate;
        try {
            startDate = startDateFormat.parse(startString);
            endDate = endDateFormat.parse(endString);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
        if (endDate.compareTo(startDate) >= 0) {
            List<RatSighting> filteredList = new ArrayList<>();
            for (RatSighting r : getAllSightings()) {
                // Find rat sightings that fall within the bounds of start and end
                String currentString = r.getDateAndTime();
                DateFormat currentDateFormat = new SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault());
                Date currentDate = new Date();
                try {
                    currentDate = currentDateFormat.parse(currentString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if ((currentDate.compareTo(startDate) >= 0) && (currentDate.compareTo(endDate) <= 0)) {
                    filteredList.add(r);
                }
            }
            return filteredList;
        }
        return new ArrayList<>();
    }

    /**
     * Method to unlock a user account based off of username
     * @param username the user to unlock
     * @return true if successful, false if not
     */
    public boolean unlockUser(String username) {
        return userManager.unlockUser(username);
    }
}
