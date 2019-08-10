/**
 * The Ship class has length, place, and information about the ship.
 *
 * @author Arya Khaligh
 */
public class Ship {

    private int length;
    private boolean[][] place; //This 2D array saves ship place on map. true if ==> the ship is in that place
    private final boolean isHorizontal;

    /**
     * This constructor initializes length, place, isHorizontal field .
     *
     * @param length       for length of ship
     * @param isHorizontal is true if the ship is horizontal
     * @param place
     */
    public Ship(int length, boolean isHorizontal, boolean[][] place) {
        this.length = length;
        this.isHorizontal = isHorizontal;
        this.place = place;
    }

    // NOTE : Use this class for debugging.
    public void printShipPlace () {
        for (int row = 0 ; row < 10 ;row++) {
            System.out.println("");
            for (int column = 0 ; column < 10 ; column++) {
                System.out.print(" " + place[row][column] + " ");
            }
        }
    }

    /**
     * This method is used to access to the shipPlace.
     * @return place as shipPlace on map
     */
    public boolean[][] getPlace() {
        return place;
    }
}
