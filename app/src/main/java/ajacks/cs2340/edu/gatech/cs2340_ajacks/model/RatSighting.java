package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import android.location.Location;

import java.util.Date;

/**
 * Created by Alonzo on 10/4/2017.
 */

public class RatSighting {
    private Location location;
    private Date dateAndTime;

    RatSighting(Location location, Date dateAndTime) {
        this.location = location;
        this.dateAndTime = dateAndTime;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
