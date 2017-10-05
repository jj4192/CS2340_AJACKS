package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import android.location.Location;

import java.util.Date;

/**
 * Created by Alonzo on 10/4/2017.
 */

public class RatSighting {
    private String location;
    private Date dateAndTime;
    private LocationType locationType;
    private String zipCode;
    private String address;
    private String city;
    private Borough borough;

    RatSighting(String location, Date dateAndTime, LocationType locationType,
                String zipCode, String address, String city, Borough borough) {
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.locationType = locationType;
        this.zipCode = zipCode;
        this.address = address;
        this.city = city;
        this.borough = borough;
    }

    public String getLocation() {
        return location;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public Borough getBorough() {
        return borough;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }
}
