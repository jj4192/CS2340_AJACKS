package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import java.io.Serializable;

/**
 * Enum of all of the location types.
 * Created by Alonzo on 10/4/2017.
 */
public enum LocationType implements Serializable {
    HOSPITAL("Hospital"),
    CONSTRUCTION_SITE("Construction Site"),
    VACANT_LOT("Vacant Lot"),
    SEWER("Catch Basin/Sewer"),
    COMMERCIAL("Commercial Building"),
    MIXED_USE("3+ Family Mixed Use Building"),
    APARTMENT("3+ Family Apt. Building"),
    FAMILY_DWELLING("1-2 Family Dwelling");

    /**The complete name of the LocationType as it would be stored in data */
    private final String name;

    LocationType(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    /**
     * Maps the full name of a LocationType to the enum type representing it
     * @param fullName The name of the enum type as stored in data
     * @return The enum type that shares a name with the input
     */
    public static LocationType getEnumValueByFullName(String fullName) {
        LocationType[] enumValues = LocationType.values();
        for (LocationType location: enumValues) {
            if (location.getName().equals(fullName)) {
                return location;
            }
        }
        return null;
    }


}
