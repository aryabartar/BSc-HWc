/**
 * This class is for making a rectangle with start point , length , width parameters .
 * @author AryaKhaligh
 * @version 1.0
 */
public class Rectangle {
    static Point startPoint ;
    private int length ;
    private int width ;

    /**
     * This constructor initializes rectangle length and width .
     * @param length
     * @param width
     */
    public Rectangle ( int length , int width) {
        this.length = length ;
        this.width = width ;
    }

    /**
     * Returns starting point .
     * @return startPoint .
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * This method is for getting length .
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * This method is for getting width .
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method is for setting length .
     * @param length
     */
    public void setLength (int length) {
        this.length = length ;
    }

    /**
     * This method is for getting width .
     * @param width
     */
    public void setWidth (int width) {
        this.width = width ;
    }

    /**
     * For calculating area .
     * @return area .
     */
    public double area () {
        double area ;
        area = length * width ;
        return area ;
    }
    /**
     * For calculating circumference .
     * @return circumference
     */
    public double circumference() {
        double circumference ;
        circumference = (length+width) *2 ;
        return circumference ;
    }

    /**
     * This method is for setting starting point .
     * @param wd
     */
    public void setStartPoint(Point wd) {
        startPoint = wd ;
    }
}
