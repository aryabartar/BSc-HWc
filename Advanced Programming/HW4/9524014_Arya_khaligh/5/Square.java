/**
 * This class is for making a square with start point and length parameters .
 * @author AryaKhaligh
 * @version 1.0
 */
public class Square {

    private Point startPoint ; //for top right of square
    private int length ;

    /**
     * This constructor initializes square length and starting point .
     * @param startPoint
     * @param length
     */
    public Square (Point startPoint , int length) {
        this.length = length ;
        this.startPoint = startPoint ;
    }

    /**
     * For getting length .
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * For getting starting point .
     * @return startPoint
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * for setting starting point .
     * @param startPoint
     */
    public void setStartPoint (Point startPoint) {
        this.startPoint = startPoint ;
    }

    /**
     * For setting length .
     * @param length
     */
    public void setLength (int length) {
        this.length = length ;
    }

    /**
     * For calculating area .
     * @return area .
     */
    public double area () {
        double area ;
        area = (double)length*length ;
        return area ;
    }
    /**
     * For calculating circumference .
     * @return circumference
     */
    public double circumference () {
        double circumference = 4*length ;
        return circumference ;
    }
}
