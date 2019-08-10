package Equipment;

import Others.Geometry;

public class MovingSmile {
    private int locX;
    private int locY;

    private boolean isSmiling = false;

    public static final int xPixels = 60;
    public static final int yPixels = 73;
    public static final int SPEED = 2;
    public static final int DAMAGE = 10 ;

    public MovingSmile(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public void attackToThisLocation(int x, int y) {

        if ((Math.abs(x - locX) < 500) && (Math.abs(y - locY) < 500)) {
            double radian = Geometry.radian(locX, locY, x, y);

            locX += Math.cos(radian) * SPEED;
            locY += Math.sin(radian) * SPEED;
            isSmiling = true ;
        }

        else {
            isSmiling = false ;
        }
    }

    public boolean isSmiling() {
        return isSmiling;
    }
}
