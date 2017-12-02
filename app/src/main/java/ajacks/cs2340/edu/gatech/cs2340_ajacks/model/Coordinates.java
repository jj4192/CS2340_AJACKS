package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Stores coordinate information in a nice wrapper.
 * Created by Alonzo on 10/4/2017.
 */
public class Coordinates {
    private final float coordX;
    private final float coordY;

    public Coordinates(float x, float y) {
        coordX = x;
        coordY = y;
    }

    /**
     * getter for x coordinate
     * @return x coordinate of long lat
     */
    public float getCoordX() {
        return coordX;
    }

    /**
     * getter for y coordinate
     * @return y coordinate of long lat
     */
    public float getCoordY() {
        return coordY;
    }

    /**
     * toString method that returns string version of lat and long
     * @return string of lat and long
     */
    @Override
    public String toString() {
        return coordX + ", " + coordY;
    }
}
