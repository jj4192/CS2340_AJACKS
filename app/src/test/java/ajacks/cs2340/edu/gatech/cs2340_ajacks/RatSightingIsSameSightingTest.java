package ajacks.cs2340.edu.gatech.cs2340_ajacks;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Borough;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Coordinates;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Location;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.LocationType;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;

/**
 * Tests isSameSighting method in RatSighting class.
 */

public class RatSightingIsSameSightingTest {
    private RatSighting sighting1;
    private RatSighting sighting2;
    private RatSighting sighting3;
    /**
     * This method is run before each test
     */
    @Before
    public void setUp() throws Exception {
        Coordinates coord1 = new Coordinates(1, 1);
        Location location1 = new Location(coord1, LocationType.APARTMENT, "12345", "123 Street", "NYC", Borough.BRONX);
        sighting1 = new RatSighting(1, location1, "10/13/2009");
        Coordinates coord2 = new Coordinates(1, 1);
        Location location2 = new Location(coord2, LocationType.APARTMENT, "12345", "123 Street", "NYC", Borough.BRONX);
        sighting2 = new RatSighting(1, location2, "10/13/2009");
        Coordinates coord3 = new Coordinates(1, 1);
        Location location3 = new Location(coord3, LocationType.APARTMENT, "12345", "123 Street", "NYC", Borough.BRONX);
        sighting3 = new RatSighting(2, location3, "10/13/2009");
    }

    /**
     * Test method for checking if Rat Sightings have same id
     */
    @Test
    public void testSameId() {
        Assert.assertTrue("Sighting already exists", sighting1.isSameSighting(sighting2));
    }

    /**
     * Test method for checking if Rat Sightings have different id
     */
    @Test
    public void testDiffId() {
        Assert.assertFalse("Sightings different", sighting1.isSameSighting(sighting3));
    }

}