package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import java.io.Serializable;

/**
 * Created by jj419_000 on 10/24/2017.
 */

public class DBRatSighting implements Serializable{
    public String id;
    public String dateAndTime;
    public String zipCode;
    public String address;
    public String city;
    public String borough;
    public String locationType;
    public String xCoord;
    public String yCoord;

    public DBRatSighting (RatSighting rat) {
        this.id = "" + rat.getId();
        this.dateAndTime = rat.getDateAndTime();
        this.zipCode = rat.getLocation().getZipCode();
        this.address = rat.getLocation().getAddress();
        this.city = rat.getLocation().getCity();
        this.borough = rat.getLocation().getBorough().toString();
        this.locationType = rat.getLocation().getLocationType().toString();
        this.xCoord = "" + rat.getLocation().getCoordinates().getCoordX();
        this.yCoord = "" + rat.getLocation().getCoordinates().getCoordY();
    }

    /***
     * method that takes in string parameters from Firebase and creates a RatSighting object from it
     * @param id id string from the RatSighting database
     * @param dateAndTime dataAndTime string from the RatSighting database
     * @param zipCode zipCode string from the RatSighting database
     * @param address address string from the RatSighting database
     * @param city city string from the RatSighting database
     * @param borough borough string from the RatSighting database
     * @param locationType locationType string from the RatSighting database
     * @param xCoord xCoord string from the RatSighting database
     * @param yCoord yCoord string from the RatSighting database
     * @return a newly created RatSighting object to add to our list of RatSightings
     */
    public static RatSighting createDBRatSighting(String id, String dateAndTime, String zipCode,
                                                  String address, String city, String borough,
                                                  String locationType, String xCoord, String yCoord) {
        Coordinates coords = new Coordinates(Float.parseFloat(xCoord), Float.parseFloat(yCoord));
        LocationType loc = LocationType.getEnumValueByFullName(locationType);
        Borough bor = Borough.getEnumValueByFullName(borough);
        Location location = new Location(coords, loc, zipCode, address, city, bor);
        RatSighting rat = new RatSighting(Integer.parseInt(id), location, dateAndTime);
        return rat;
    }
}
