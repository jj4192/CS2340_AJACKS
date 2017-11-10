package ajacks.cs2340.edu.gatech.cs2340_ajacks;

import org.junit.Before;
import org.junit.Test;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSightingManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by sarah on 11/9/17. Tests the compare method in SortByDateAndTime.
 */

public class SortByDateAndTimeTest {
    private RatSighting sighting1;
    private RatSighting sighting2;
    private RatSighting sighting3;

    @Before
    public void setup() {
        String dateAndTime1 = "11/18/1997";
        String dateAndTime2 = "11/19/1997";
        sighting1 = new RatSighting(1, null, dateAndTime1);
        sighting2 = new RatSighting(2, null, dateAndTime2);
        sighting3 = new RatSighting(3, null, dateAndTime1);
    }

    @Test
    public void firstDateLarger() {
        RatSightingManager.SortByDateAndTime sorter = new RatSightingManager.SortByDateAndTime();
        assertEquals(sorter.compare(sighting2, sighting1), 1);
    }

    @Test
    public void firstDateSmaller() {
        RatSightingManager.SortByDateAndTime sorter = new RatSightingManager.SortByDateAndTime();
        assertEquals(sorter.compare(sighting1, sighting2), -1);
    }

    @Test
    public void equalDates() {
        RatSightingManager.SortByDateAndTime sorter = new RatSightingManager.SortByDateAndTime();
        assertEquals(sorter.compare(sighting3, sighting1), 0);
    }

}
