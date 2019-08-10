/**
 * This program implements an application that makes circle , rectangle and square .
 * @author Arya Khaligh
 * @version 1.0
 * @since 3/9/2018
 */
public class Main {
    /**
     * This is the main method which makes use of made classes .
     * @param args Unused
     */
    public static void main(String[] args) {
        Point p1 = new Point(2,3) ;
        Circle circle1 = new Circle (p1 , 2) ;

        System.out.print("Point in circle1 before changing point : ");
        System.out.print(circle1.getCenter().getX() +" "+circle1.getCenter().getY()) ;

        p1.setX(5) ;
        p1.setY(6) ;
        System.out.print("\nPoint in circle1 after changing : ") ;
        System.out.print(circle1.getCenter().getX() +" "+ circle1.getCenter().getY());


        Point p2 = new Point(0,0) ;
        Square square1 = new Square(p2 , 4) ;
        System.out.print("\n\nSquare start point before changing : ") ;
        System.out.print(square1.getStartPoint().getX()+ " " +square1.getStartPoint().getY());
        square1.setStartPoint(p1);
        System.out.print("\nSquare start point after changing : ") ;
        System.out.println(square1.getStartPoint().getX()+ " " +square1.getStartPoint().getY());


        Point p3 = new Point(1,1) ;
        Rectangle.startPoint = p3 ;
        Rectangle rectangle1 = new Rectangle(5 , 3) ;
        Rectangle rectangle2 = new Rectangle(7,6) ;

        System.out.print("\n\nRectangle1 start point : ");
        System.out.println(rectangle1.getStartPoint().getX() + " " + rectangle1.getStartPoint().getY()) ;
        System.out.print("Rectangle2 start point : ");
        System.out.println(rectangle2.getStartPoint().getX() + " " + rectangle2.getStartPoint().getY()) ;

        rectangle2.setStartPoint(p1) ;

        System.out.print("\nRectangle1 start point : ");
        System.out.println(rectangle1.getStartPoint().getX() + " " + rectangle1.getStartPoint().getY()) ;
        System.out.print("Rectangle2 start after changing point : ");
        System.out.println(rectangle2.getStartPoint().getX() + " " + rectangle2.getStartPoint().getY()) ;

        System.out.println("\nCircle1 area : " + circle1.area());
        System.out.println("Circle1 circumference : " + circle1.circumference()) ;

        System.out.println("\nSquare1 area : " + square1.area());
        System.out.println("Square1 circumference : " + square1.circumference()) ;

        System.out.println("\nRectangle1 area : " + rectangle1.area());
        System.out.println("Rectangle1 circumference : " + rectangle1.circumference()) ;

        System.out.println("\nRectangle2 area : " + rectangle2.area());
        System.out.println("Rectangle2 circumference : " + rectangle2.circumference()) ;


    }
}
