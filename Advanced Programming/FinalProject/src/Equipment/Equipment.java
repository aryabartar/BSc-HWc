package Equipment;

import Serialization.Serialize;

import java.io.Serializable;
import java.security.SecureRandom;

public class Equipment implements Serializable{

    private int locX ;
    private int locY ;

    private int centerX ;
    private int centerY ;

    private int endX ;
    private int endY ;

    private boolean isTaken ;

    private final int xPixels  ;
    private final int yPixels  ;

    private float alpha  ;
    private boolean isAlphaUp ;

    public Equipment (int locX , int locY , int xPixels , int yPixels ) {
        this.locX = locX ;
        this.locY = locY ;

        endX = locX + xPixels ;
        endY = locY + yPixels ;

        centerX = locX + xPixels/2 ;
        centerY = locY + yPixels/2 ;

        this.xPixels = xPixels;
        this.yPixels = yPixels ;

        isAlphaUp = true ;

        SecureRandom random = new SecureRandom() ;
        int tempValue = random.nextInt(50) + 40 ;
        alpha = (float) tempValue/ 100 ;

        isTaken = false ;
    }

    public void updateAlpha () {

        if (alpha < 0.2)
            isAlphaUp = true ;
        if (alpha > 0.95)
            isAlphaUp = false ;

        if (isAlphaUp == true) {
            alpha += 0.02 ;
        }
        else {
            alpha -= 0.02;
        }



    }

    public int getEndY() {
        return endY;
    }

    public int getEndX() {
        return endX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }

    public int getxPixels() {
        return xPixels;
    }

    public int getyPixels() {
        return yPixels;
    }

    public boolean getIsTaken () {
        return isTaken ;
    }

    public void takeEquipment () {
        isTaken = true ;
    }

    public float getAlpha() {
        return alpha;
    }
}
