package ajacks.cs2340.edu.gatech.cs2340_ajacks;

import org.junit.Test;

import static org.junit.Assert.*;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers.GraphScreen;

/**
 * Created by Alonzo on 11/9/2017.
 *
 */
public class GraphLabelTest {

    @Test
    public void sameDate() throws Exception {
        int value = GraphScreen.dateDistance(1, 2010, 1, 2010);
        assertEquals(0, value);

        String date = GraphScreen.getMonthYearByDistance(1, 2010, 0);
        assertEquals("1/2010", date);
    }

    @Test
    public void sameMonthDifferentYear() throws Exception {
        int value = GraphScreen.dateDistance(1, 2010, 1, 2011);
        assertEquals(12, value);

        String date = GraphScreen.getMonthYearByDistance(1, 2010, 12);
        assertEquals("1/2011", date);
    }

    @Test
    public void differentMonthSameYear() throws Exception {
        int value = GraphScreen.dateDistance(1, 2010, 9, 2010);
        assertEquals(8, value);

        String date = GraphScreen.getMonthYearByDistance(1, 2010, 8);
        assertEquals("9/2010", date);
    }

    @Test
    public void differentMonthDifferentYear() throws Exception {
        int value = GraphScreen.dateDistance(1, 2010, 9, 2011);
        assertEquals(20, value);

        String date = GraphScreen.getMonthYearByDistance(1, 2010, 20);
        assertEquals("9/2011", date);
    }

    @Test
    public void reverseStartAndEndDate() throws Exception {
        int value = GraphScreen.dateDistance(1, 2010, 10, 2009);
        assertEquals(-3, value);

        String date = GraphScreen.getMonthYearByDistance(1, 2010, -3);
        assertEquals("10/2009", date);
    }
}
