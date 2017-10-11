package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import java.io.Serializable;
import java.lang.*;

/**
 * Enum of all of the boroughs.
 * Created by Alonzo on 10/4/2017.
 */

public enum Borough implements Serializable {
    QUEENS("Queens"),
    BRONX("Bronx"),
    MANHATTAN("Manhattan"),
    BROOKLYN("Brooklyn"),
    STATEN_ISLAND("Staten Island");

    private final String name;

    Borough(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    /**
     * Maps the full name of a Borough to the enum type representing it
     * @param fullName The name of the enum type as stored in data
     * @return The enum type that shares a name with the input
     */
    public static Borough getEnumValueByFullName(String fullName) {
        Borough[] enumValues = Borough.values();
        for (Borough borough: enumValues) {
            if (borough.getName().toLowerCase().equals(fullName.toLowerCase())) {
                return borough;
            }
        }
        return null;
    }

}
