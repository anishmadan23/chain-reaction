
/**
 * This class is used to easily manipulate coordinates.
 */

public class Coordinates {

    /**
     * X-Coordinate
     */
    private int x;
    /**
     * Y-Coordinate
     */
    private int y;

    public Coordinates(int x1, int y1){
        this.x = x1;
        this.y = y1;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
