package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by Alonzo on 10/4/2017.
 */

public class Location {

    private LocationType locationType;
    private String zipCode;
    private String address;
    private String city;
    private Borough borough;

    Location(LocationType locationType, String zipCode, String address, String city, Borough borough) {
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

    public Borough getBorough() {
        return borough;
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
