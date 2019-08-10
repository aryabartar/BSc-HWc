/**
 * This class is for making a point in 2D .
 * @author AryaKhaligh
 * @version 1.0
 */
public class Point {
    private int x;
    private int y;

    /**
     * This constructor initializes point values .
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * For setting x .
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * for setting y .
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * For getting x value .
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     * For getting y value .
     * @return Y
     */
    public int getY() {
        return y;
    }
}

