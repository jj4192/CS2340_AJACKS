package ajacks.cs2340.edu.gatech.cs2340_ajacks;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.LocationType;


public class enumLocationTypeTest {

    String testName1, testName2, testName3;
    String badName1, badName2, badName3;

    @Before
    public void setUp() {
        testName1 = "Hospital";
        testName2 = "1-2 Family Dwelling";
        testName3 = "Commercial Building";

        badName1 = "The White House";
        badName2 = "My House";
        badName3 = "Georgia Tech";


    }
    @Test
    public void properLocations() {
        Assert.assertEquals(LocationType.HOSPITAL, LocationType.getEnumValueByFullName(testName1));
        Assert.assertEquals(LocationType.FAMILY_DWELLING, LocationType.getEnumValueByFullName(testName2));
        Assert.assertEquals(LocationType.COMMERCIAL, LocationType.getEnumValueByFullName(testName3));

    }

    @Test
    public void improperLocations() {
        Assert.assertEquals(null, LocationType.getEnumValueByFullName(badName1));
        Assert.assertEquals(null, LocationType.getEnumValueByFullName(badName2));
        Assert.assertEquals(null, LocationType.getEnumValueByFullName(badName3));
    }
}

