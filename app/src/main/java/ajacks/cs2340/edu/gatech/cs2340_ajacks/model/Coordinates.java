package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by Alonzo on 10/4/2017.
 */

public class Coordinates {
    private int coordX;
    private int coordY;

    public Coordinates(int x, int y) {
        coordX = x;
        coordY = y;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
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
