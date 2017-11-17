package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;


/**
 * Stores information about submitted rat sightings.
 *
 * Created by Alonzo on 10/4/2017.
 */
public class RatSighting {
    /** The unique ID of the RatSighting used to identify it in the database */
    private final int id;
    /** The information about the location of the rat sighting. */
    private final Location location;
    /** The temporal information about the rat sighting. */
    private final String dateAndTime;

    public RatSighting(int id, Location location, String dateAndTime) {
        this.id = id;
        this.location = location;
        this.dateAndTime = dateAndTime;
    }

    /**
     * getter of rat sighting id
     * @return rat sighting id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for location
     * @return location of rat sighting
     */
    public Location getLocation() {
        return location;
    }

    /**
     * getter for date and time
     * @return date and time of rat sighting
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * toString for rat sighting
     * @return string with id, location, and date and time
     */
    @Override
    public String toString() {
        return getId() + " " + getLocation().toString() + " " + getDateAndTime();
    }

    /***
     * checks if the rat sightings' ids are the same
     * @param rat sighting to be checked
     * @return true if ids are the same, false otherwise
     */
    public boolean isSameSighting(RatSighting rat) {
        return rat.getId() == this.id;
    }
}
