package Equipment;

import Blocks.Block;
import EnemyTanks.EnemyTank;
import Engine.GameFrame;
import Engine.GameState;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Tank implements Serializable{

    private int locX;
    private int locY;

    private int endLocX;
    private int endLocY;

    private int gunLocX;
    private int gunLocY;

    private final int xPixels = 128;
    private final int yPixels = 128;
    private final static int gunXPixels = 128;
    private final static int gunYPixels = 40;
    private int gunLevel = 0 ;

    private int bulletsNumber;
    private int rocketsNumber;
    private int health;
    private final int healthLimit;

    private GameState gameState ;


    private double gunAndBodyRadian; //this is tank body and gun radian .

    public Tank(GameState gameState) {
        bulletsNumber = 999;
        rocketsNumber = 999;

        locX = 100;
        locY = 100;
        healthLimit = 100;
        health = healthLimit;

        this.gameState = gameState ;
        initLocations();
    }

    /**
     * This method will initialize tank starting location .
     */
    private void initLocations() {
        setEndingLocation();
        setGunLocation();
    }

    /**
     * This method will set button-right location (tank is 128*128 pixels) .
     */
    private void setEndingLocation() {
        endLocX = locX + xPixels;
        endLocY = locY + yPixels;
    }

    private void setGunLocation() {
        gunLocX = locX + xPixels / 2;
        gunLocY = locY + yPixels / 2 - gunYPixels / 2;

    }

    public int getEndLocX() {
        return endLocX;
    }

    public int getEndLocY() {
        return endLocY;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocation(int locX, int locY) {
        ArrayList<EnemyTank> enemyTanks = gameState.getEnemyTanks();
        boolean canMove = true;

        for (EnemyTank enemyTank : enemyTanks) {

            Rectangle r = new Rectangle(locX, locY, xPixels, yPixels);
            Rectangle p = new Rectangle(enemyTank.getLocX(), enemyTank.getLocY(), enemyTank.getGunXPixels() - 20, enemyTank.getyPixels());

            // Assuming there is an intersect method
            if (r.intersects(p))
                canMove = false;
        }

        for (Block block : gameState.getBlocks()) {
            Rectangle p = new Rectangle(block.getLocX(), block.getLocY(), block.getxPixels(), block.getyPixels());
            Rectangle r = new Rectangle(locX, locY, xPixels, yPixels);

            if (r.intersects(p))
                canMove = false;

        }

        if ((locX > 0) && (locX + xPixels < GameFrame.GAME_FULL_WIDTH) && (locY > 0) && (locY + yPixels < GameFrame.GAME_FULL_WIDTH)) {
            if (canMove == true) {
                this.locX = locX;
                this.locY = locY;

                endLocX = locX + xPixels;
                endLocY = locY + yPixels;

                setGunLocation();
            }
        }

    }

    private boolean canMove() {
        ArrayList<EnemyTank> enemyTanks = gameState.getEnemyTanks();
        boolean canMove = true;

        for (EnemyTank tank : enemyTanks) {
            if ((locX < tank.getEndLocX()) && (endLocX > tank.getLocX()) && (locY < tank.getEndLocY()) && (endLocY > tank.getLocY())) {

            }
        }
        return canMove;
    }

    public void moveLocX(int moveX) {
        this.setLocation(locX + moveX, locY);
    }

    public void moveLocY(int moveY) {
        this.setLocation(locX, locY + moveY);
    }

    public int getGunLocX() {
        return gunLocX;
    }

    public int getGunLocY() {
        return gunLocY;
    }

    public int getTankCenterX() {
        return locX + xPixels / 2;
    }

    public int getTankCenterY() {
        return locY + yPixels / 2;
    }

    public void setGunAndBodyRadian(double gunAndBodyRadian) {
        this.gunAndBodyRadian = gunAndBodyRadian;
    }

    public double getGunAndBodyRadian() {
        return gunAndBodyRadian;
    }

    public static int getGunXPixels() {
        return gunXPixels;
    }

    public static int getGunYPixels() {
        return gunYPixels;
    }

    public int getyPixels() {
        return yPixels;
    }

    public int getxPixels() {
        return xPixels;
    }

    public int getBulletsNumber() {
        return bulletsNumber;
    }

    public int getRocketsNumber() {
        return rocketsNumber;
    }

    public void reduceBulletNumber() {
        if (bulletsNumber > 0)
            bulletsNumber--;
    }

    public void reduceRocketNumbers() {
        if (rocketsNumber > 0)
            rocketsNumber--;
    }

    public void addToBullets(int amount) {
        bulletsNumber += amount;
    }

    public void addToRockets(int amount) {
        rocketsNumber += amount;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health -= amount;
        if (health <= 0)
            GameState.gameOver = true ;
    }

    public void addToHealth(int amount) {
        health += amount;
        if (health > healthLimit) {
            health = healthLimit ;
        }
    }

    public int getHealthLimit() {
        return healthLimit;
    }

    public void updateWeapon () {
        if (gunLevel < 3) {
            gunLevel++ ;
        }
    }

    public int getGunLevel() {
        return gunLevel;
    }
}

