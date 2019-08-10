/**
 *
 * This class is for making a circle with center and radius parameters .
 * @author AryaKhaligh
 * @version 1.0
 */
public class Circle {
    private Point center;
    private int r;

    /**
     * This constructor initializes circle .
     * @param center
     * @param r
     */
    public Circle(Point center, int r) {
        this.center = center;
        this.r = r;
    }

    /**
     * For getting radius of circle .
     * @return radius .
     */
    public int getR() {
        return r;
    }

    /**
     * For getting circle's center .
     * @return center .
     */
    public Point getCenter() {
        return center;
    }

    /**
     * For setting circle's center .
     * @param center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * For setting circle radius .
     * @param r
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * For calculating area .
     * @return area .
     */
    public double area () {
        double area ;
        area = Math.PI*r*r ;
        return area ;
    }

    /**
     * For calculating circumference .
     * @return circumference
     */
    public double circumference () {
        double circumference = 2*Math.PI*r;
        return circumference ;
    }
}
