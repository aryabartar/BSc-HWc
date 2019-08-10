package Others;

public class Geometry {
    public static double radian (int x1 , int y1 , int x2 , int y2) {
        return (float)Math.atan2(y2 - y1, x2 - x1) ;
    }
}
