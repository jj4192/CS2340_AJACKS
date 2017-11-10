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

    public LocationType getLocationType() {
        return locationType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Borough getBorough() {
        return borough;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return coordinates + " " + borough + " " + city + " " + zipCode + " " + locationType + " " + address;
    }
}
