package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import java.io.Serializable;
import java.lang.*;

/**
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