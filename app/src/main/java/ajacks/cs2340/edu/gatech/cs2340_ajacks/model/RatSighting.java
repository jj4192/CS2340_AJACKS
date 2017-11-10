package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import java.io.Serializable;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.*;


/**
 * Stores information about submitted rat sightings.
 *
 * Created by Alonzo on 10/4/2017.
 */
public class RatSighting {
    /** The unique ID of the RatSighting used to identify it in the database */
    private int id;
    /** The information about the location of the rat sighting. */
    private Location location;
    /** The temporal information about the rat sighting. */
    private String dateAndTime;

    public RatSighting(int id, Location location, String dateAndTime) {
        this.id = id;
        this.location = location;
        this.dateAndTime = dateAndTime;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

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
