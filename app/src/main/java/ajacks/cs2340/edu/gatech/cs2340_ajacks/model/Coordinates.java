package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Stores coordinate information in a nice wrapper.
 * Created by Alonzo on 10/4/2017.
 */
public class Coordinates {
    private float coordX;
    private float coordY;

    public Coordinates(float x, float y) {
        coordX = x;
        coordY = y;
    }

    public float getCoordX() {
        return coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    @Override
    public String toString() {
        return coordX + ", " + coordY;
    }
}
