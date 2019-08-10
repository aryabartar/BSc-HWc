package EnemyTanks;

import Engine.GameFrame;
import Engine.GameState;
import Equipment.Bullet;
import Equipment.Rocket;
import Others.Point;

import java.awt.*;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class EnemyTank implements Serializable {

    protected int health;
    protected boolean isAlive;

    protected int locX;
    protected int locY;

    protected int endLocX;
    protected int endLocY;

    protected int gunLocX;
    protected int gunLocY;

    protected final int xPixels = 100;
    protected final int yPixels = 100;
    protected final int gunXPixels = 110;
    protected final int gunYPixels = 33;
    private GameState gameState;

    protected double gunAndBodyRadian; //this is tank body and gun radian .
    private TimerTask task;

    private SecureRandom random = new SecureRandom();
    private final int wholeProbability;

    public EnemyTank(int health, int locX, int locY, int wholeProbability, GameState gameState) {
        this.health = health;
        this.locX = locX;
        this.locY = locY;
        this.wholeProbability = wholeProbability;
        this.gameState = gameState;

        isAlive = true;

        initLocations();
        initTask();

        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1 * 1000;

        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, intervalPeriod);

    }


    public void initTask() {
        task = new TimerTask() {
            @Override
            public void run() {
                if (isAlive == true) {
                    if (random.nextInt(wholeProbability) != 0) {
                        gameState.addToBullets(new Bullet(getTankCenterX(), getTankCenterY(), getGunAndBodyRadian(), true));
                    } else {
                        gameState.addToRockets(new Rocket(getTankCenterX(), getTankCenterY(), getGunAndBodyRadian(), true));
                    }
                }
            }
        };
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

            Rectangle p = new Rectangle(enemyTank.getLocX(), enemyTank.getLocY(), enemyTank.getGunXPixels() - 20, enemyTank.getyPixels());
            Rectangle r = new Rectangle(locX, locY, xPixels, yPixels);

            // Assuming there is an intersect method
            if (r.intersects(p)) {
                canMove = false;
            }
        }
        justMove(locX, locY, canMove);

    }

    public void setLocation(int locX, int locY, EnemyTank et) {
        ArrayList<EnemyTank> enemyTanks = gameState.getEnemyTanks();
        boolean canMove = true;

        for (EnemyTank enemyTank : enemyTanks) {

            Rectangle p = new Rectangle(enemyTank.getLocX(), enemyTank.getLocY(), enemyTank.getGunXPixels() - 20, enemyTank.getyPixels());
            Rectangle r = new Rectangle(locX, locY, xPixels, yPixels);

            // Assuming there is an intersect method
            if (r.intersects(p)) {
                if (enemyTank.equals(et) == false)
                    canMove = false;
            }
        }

        justMove(locX, locY, canMove);
    }

    private void justMove(int locX, int locY, boolean canMove) {

        if ((locX > 0) && (locX + xPixels < GameFrame.GAME_FULL_WIDTH) && (locY > 0) && (locY + yPixels < GameFrame.GAME_FULL_HEIGHT)) {
            if (canMove == true) {
                this.locX = locX;
                this.locY = locY;

                endLocX = locX + xPixels;
                endLocY = locY + yPixels;

                setGunLocation();
            }
        }
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

    public int getGunXPixels() {
        return gunXPixels;
    }

    public int getGunYPixels() {
        return gunYPixels;
    }

    public int getxPixels() {
        return xPixels;
    }

    public int getyPixels() {
        return yPixels;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int reduce) {
        health -= reduce;
        if (health < 1)
            isAlive = false;
    }

    public void die() {
        isAlive = false;
    }

}
