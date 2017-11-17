package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Contains all of the location data relevant to the app.
 * Created by Alonzo on 10/4/2017.
 */

public class Location {
    private final Coordinates coordinates;
    private final LocationType locationType;
    private final String zipCode;
    private final String address;
    private final String city;
    private final Borough borough;

    public Location(Coordinates coordinates, LocationType locationType, String zipCode, String address, String city, Borough borough) {
        this.coordinates = coordinates;
        this.locationType = locationType;
        this.zipCode = zipCode;
        this.address = address;
        this.city = city;
        this.borough = borough;
    }

    /**
     * getter for locationType
     * @return locationType enum for rat sighting
     */
    public LocationType getLocationType() {
        return locationType;
    }

    /**
     * getter for zipcode
     * @return zipcode of rat sighting
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * getter for city
     * @return city of rat sighting
     */
    public String getCity() {
        return city;
    }

    /**
     * getter for address
     * @return address of rat sighting
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for borough enum
     * @return borough of rat sighting
     */
    public Borough getBorough() {
        return borough;
    }

    /**
     * getter for coordinates which have x coord and y coord
     * @return coordinates of rat sighting
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * toString of location
     * @return string of location
     */
    @Override
    public String toString() {
        return coordinates + " " + borough + " " + city + " " + zipCode + " " + locationType + " " + address;
    }
}
