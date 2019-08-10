package EnemyTanks;

import Engine.GameState;
import Equipment.Tank;

import java.awt.*;

public abstract class DynamicEnemyClass extends EnemyTank {

    private final int fromThisXLocation;
    private final int fromThisYLocation;

    private final int toThisXLocation;
    private final int toThisYLocation;

    private boolean movesDown;
    private boolean movesRight;

    public DynamicEnemyClass(int locX, int locY, int toThisXLocation, int toThisYLocation , int health , int wholeProbability , GameState gameState) {
        super(health, locX, locY, wholeProbability , gameState);

        this.fromThisXLocation = locX;
        this.fromThisYLocation = locY;

        this.toThisXLocation = toThisXLocation;
        this.toThisYLocation = toThisYLocation;

        movesDown = true;
        movesRight = true;

    }

    public void moveAutomatic(Tank mainTank) {
        if (((fromThisXLocation == toThisXLocation) || (fromThisYLocation == toThisYLocation)) &&
                (fromThisYLocation <= toThisYLocation) && (fromThisXLocation <= toThisXLocation)) {

            if (fromThisXLocation == toThisXLocation) {
                if (movesDown == true) {
                    setLocation(locX, locY + 2, this);
                } else {
                    setLocation(locX, locY - 2, this);
                }
                if (locY > toThisYLocation)
                    movesDown = false;

                else if (locY < fromThisYLocation)
                    movesDown = true;

                boolean canMove = true;

                Rectangle r = new Rectangle(locX, locY, xPixels, yPixels);
                Rectangle p = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());

                // Assuming there is an intersect method
                if (r.intersects(p)) {
                    canMove = false;
                }

                if ((canMove == false)) {
                    movesDown = !movesDown;
                }


            } else {

                if (movesRight == true) {
                    setLocation(locX + 2, locY, this);
                } else {
                    setLocation(locX - 2, locY, this);
                }
                if (locX > toThisXLocation)
                    movesRight = false;

                else if (locX < fromThisXLocation)
                    movesRight = true;


                Rectangle r = new Rectangle(locX, locY, xPixels, yPixels);
                Rectangle p = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());

                boolean canMove = true;

                // Assuming there is an intersect method
                if (r.intersects(p)) {
                    canMove = false;
                    if (movesRight == false) {
                        mainTank.setLocation(mainTank.getLocX() +6, mainTank.getLocY());
                    }
                }

                if ((canMove == false)) {
                    movesRight = !movesRight;
                }

            }
        } else {
            System.err.println("Your inputs should have at least one same position in either x or y . :D");
        }
    }

}

