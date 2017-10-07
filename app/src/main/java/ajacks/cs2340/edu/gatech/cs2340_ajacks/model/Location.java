package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by Alonzo on 10/4/2017.
 */

public class Location {
    private Coordinates coordinates;
    private LocationType locationType;
    private String zipCode;
    private String address;
    private String city;
    private Borough borough;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return coordinates + " " + borough + " " + city + " " + zipCode + " " + locationType + " " + address;
    }
}
