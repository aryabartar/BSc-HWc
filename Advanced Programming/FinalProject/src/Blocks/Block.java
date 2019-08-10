package Blocks;

import java.io.Serializable;

public class Block implements Serializable{
    private int locX;
    private int locY;

    private int centerX;
    private int centerY;

    private int endX;
    private int endY;

    private int health = 6;
    private boolean isAlive;

    private final int xPixels ;
    private final int yPixels ;


    public Block(int locX, int locY , int xPixels , int yPixels) {
        this.locX = locX;
        this.locY = locY;

        this.xPixels = xPixels ;
        this.yPixels = yPixels ;

        endX = locX + xPixels;
        endY = locY + yPixels;

        centerX = locX + xPixels / 2;
        centerY = locY + yPixels / 2;

        isAlive = true;
    }

    public void reduceHealth(int reduce) {
        health -= reduce;

        if (health < 1) {
            isAlive = false;
        }
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

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
