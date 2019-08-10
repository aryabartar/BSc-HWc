package Engine; /*** In The Name of Allah ***/

import Blocks.Block;
import Blocks.Chariot;
import Blocks.DestroyableBlock;
import Blocks.UnDestroyableBlock;
import EnemyTanks.*;
import Equipment.*;
import Network.GameClient;
import Network.GameServer;
import Others.Geometry;
import Others.Point;
import Serialization.Serialize;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author ... :D
 */
public class GameState {

    //	public int locX, locY, diam;
    public static boolean gameOver;
    public boolean firstLevel;
    public static int difficultyLevel = 2; // use this later
    public boolean fullyFinished;

    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mouseLeftClicked, mouseRightClicked; //mouseRightClicked : false => bullet / true => rocket
    private int mouseX, mouseY; // for clicking
    private int click = 0;
    private int mouseMotionX, mouseMotionY; //for mouse motion
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    private Tank mainTank;
    private GameClient gameClient;
    private GameServer gameServer;
    private boolean load;

    private static ArrayList<Bullet> bullets;
    private static ArrayList<Rocket> rockets;
    private ArrayList<EnemyTank> enemyTanks;
    private ArrayList<Block> blocks;
    private ArrayList<Equipment> equipments;
    private ArrayList<Point> destroyedTankTemporaryTrashPoints;
    private ArrayList<MovingSmile> movingSmiles;

    GameStateSuperInfo gameStateSuperInfo;

    public GameState(boolean load) {

        this.load = load;

        if (load == false) {
            gameStateSuperInfo = new GameStateSuperInfo();
        } else {
            gameStateSuperInfo = (GameStateSuperInfo) Serialize.deserializeObject("test.tanki");
        }

        firstLevel = true;
        fullyFinished = false;

        mainTank = new Tank(this);
        setArrayLists();


        addMapObjects();

        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mouseLeftClicked = false;
        mouseRightClicked = false;

        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        playBackgroundMusic();
    }

    private void initClientServer() {
        boolean optionPaneNumber;
        optionPaneNumber = JOptionPane.showConfirmDialog(null, "Do you want to run the server ?") == 0;


        GameServer gameServer;
        GameClient gameClient;

        if (optionPaneNumber == true) {
            gameServer = new GameServer(null);
            gameServer.start();
        }

        gameClient = new GameClient(null, "localhost");
        gameClient.start();

        gameClient.sendData("ping".getBytes());
    }


    private void addMapObjects() {
        if (load == false) {

            //add tanks here

            enemyTanks.add(new StaticTankEasy(600, 100, this));
            enemyTanks.add(new DynamicTankEasy(300, 500, 500, 500, this));
            enemyTanks.add(new DynamicTankEasy(1500, 1300, 1800, 1300, this));

            enemyTanks.add(new StaticTankHard(1500, 1100, this));
            enemyTanks.add(new StaticTankEasy(1300, 1200, this));
            enemyTanks.add(new StaticTankHard(1200, 200, this));
            enemyTanks.add(new StaticTankHard(700, 1200, this));
            enemyTanks.add(new StaticTankHard(2600, 1300, this));

            enemyTanks.add(new StaticTankHard(1850, 800, this));
            enemyTanks.add(new DynamicTankEasy(2600, 130, 2600, 250, this));


            // add blocks here
            blocks.add(new UnDestroyableBlock(400, 100));
            blocks.add(new UnDestroyableBlock(400, 200));
            blocks.add(new UnDestroyableBlock(400, 300));

            blocks.add(new DestroyableBlock(100, 300));
            blocks.add(new DestroyableBlock(200, 300));
            blocks.add(new DestroyableBlock(300, 300));
            blocks.add(new DestroyableBlock(500, 100));

            blocks.add(new UnDestroyableBlock(400, 900));
            blocks.add(new UnDestroyableBlock(500, 900));
            blocks.add(new UnDestroyableBlock(600, 900));
            blocks.add(new UnDestroyableBlock(400, 1000));
            blocks.add(new UnDestroyableBlock(400, 1100));
            blocks.add(new UnDestroyableBlock(500, 1100));
            blocks.add(new UnDestroyableBlock(600, 1100));
            blocks.add(new UnDestroyableBlock(600, 1000));

            blocks.add(new UnDestroyableBlock(100, 900));
            blocks.add(new UnDestroyableBlock(100, 1000));
            blocks.add(new DestroyableBlock(200, 900));
            blocks.add(new DestroyableBlock(300, 900));

            blocks.add(new UnDestroyableBlock(600, 1200));
            blocks.add(new UnDestroyableBlock(600, 1300));
            blocks.add(new UnDestroyableBlock(600, 1400));
            blocks.add(new UnDestroyableBlock(600, 1500));

            blocks.add(new DestroyableBlock(600, 1600));
            blocks.add(new DestroyableBlock(600, 1700));
            blocks.add(new DestroyableBlock(600, 1800));

            blocks.add(new UnDestroyableBlock(700, 1300));
            blocks.add(new UnDestroyableBlock(700, 1400));
            blocks.add(new UnDestroyableBlock(700, 1500));
            blocks.add(new UnDestroyableBlock(800, 1300));
            blocks.add(new UnDestroyableBlock(800, 1400));
            blocks.add(new UnDestroyableBlock(800, 1500));
            blocks.add(new UnDestroyableBlock(900, 1300));
            blocks.add(new UnDestroyableBlock(900, 1400));
            blocks.add(new UnDestroyableBlock(900, 1500));
            blocks.add(new UnDestroyableBlock(1000, 1300));
            blocks.add(new UnDestroyableBlock(1000, 1400));
            blocks.add(new UnDestroyableBlock(1000, 1500));
            blocks.add(new UnDestroyableBlock(1100, 1300));
            blocks.add(new UnDestroyableBlock(1100, 1400));
            blocks.add(new UnDestroyableBlock(1100, 1500));
            blocks.add(new UnDestroyableBlock(1200, 1500));
            blocks.add(new UnDestroyableBlock(1200, 1300));
            blocks.add(new UnDestroyableBlock(1200, 1400));
            blocks.add(new UnDestroyableBlock(1300, 1300));
            blocks.add(new UnDestroyableBlock(1300, 1400));
            blocks.add(new UnDestroyableBlock(1300, 1500));
            blocks.add(new UnDestroyableBlock(1400, 1400));

            blocks.add(new DestroyableBlock(1400, 1500));
            blocks.add(new DestroyableBlock(1400, 1300));

            blocks.add(new UnDestroyableBlock(1400, 1200));
            blocks.add(new UnDestroyableBlock(1400, 1100));
            blocks.add(new UnDestroyableBlock(1400, 1000));
            blocks.add(new UnDestroyableBlock(1500, 1000));
            blocks.add(new DestroyableBlock(1600, 1000));
            blocks.add(new DestroyableBlock(1700, 1000));
            blocks.add(new UnDestroyableBlock(1800, 1000));
            blocks.add(new UnDestroyableBlock(1900, 1000));
            blocks.add(new UnDestroyableBlock(2000, 1000));
            blocks.add(new UnDestroyableBlock(2100, 1000));

            blocks.add(new UnDestroyableBlock(1400, 900));
            blocks.add(new UnDestroyableBlock(1500, 900));
            blocks.add(new DestroyableBlock(1600, 900));
            blocks.add(new DestroyableBlock(1700, 900));
            blocks.add(new UnDestroyableBlock(1800, 900));
            blocks.add(new UnDestroyableBlock(1900, 900));
            blocks.add(new UnDestroyableBlock(2000, 900));
            blocks.add(new UnDestroyableBlock(2100, 900));

            blocks.add(new DestroyableBlock(1300, 1000));
            blocks.add(new DestroyableBlock(1200, 1000));
            blocks.add(new DestroyableBlock(1100, 1000));
            blocks.add(new DestroyableBlock(1000, 1000));
            blocks.add(new DestroyableBlock(900, 1000));
            blocks.add(new DestroyableBlock(800, 1000));
            blocks.add(new DestroyableBlock(700, 1000));
            blocks.add(new DestroyableBlock(700, 900));
            blocks.add(new DestroyableBlock(800, 900));
            blocks.add(new DestroyableBlock(900, 900));
            blocks.add(new DestroyableBlock(1000, 900));
            blocks.add(new DestroyableBlock(1100, 900));
            blocks.add(new DestroyableBlock(1200, 900));
            blocks.add(new DestroyableBlock(1300, 900));

            blocks.add(new Chariot(900, 100));
            blocks.add(new DestroyableBlock(900, 600));
            blocks.add(new DestroyableBlock(1000, 600));
            blocks.add(new DestroyableBlock(900, 700));
            blocks.add(new DestroyableBlock(1000, 700));
            blocks.add(new DestroyableBlock(900, 800));
            blocks.add(new DestroyableBlock(1000, 800));
            blocks.add(new DestroyableBlock(900, 900));
            blocks.add(new DestroyableBlock(1000, 900));
            blocks.add(new DestroyableBlock(900, 500));
            blocks.add(new DestroyableBlock(1000, 500));

            for (int i = 4; i < 10; i++) {
                blocks.add(new UnDestroyableBlock(1600, i * 100));
                blocks.add(new UnDestroyableBlock(1700, i * 100));
                blocks.add(new UnDestroyableBlock(2000, i * 100));
                blocks.add(new UnDestroyableBlock(2100, i * 100));
            }

            blocks.add(new DestroyableBlock(1800, 400));
            blocks.add(new DestroyableBlock(1900, 400));


            blocks.add(new UnDestroyableBlock(2400, 400));
            blocks.add(new UnDestroyableBlock(2500, 400));
            blocks.add(new UnDestroyableBlock(2600, 400));
            blocks.add(new UnDestroyableBlock(2600, 500));
            blocks.add(new UnDestroyableBlock(2600, 600));
            blocks.add(new UnDestroyableBlock(2400, 500));
            blocks.add(new UnDestroyableBlock(2400, 600));
            blocks.add(new UnDestroyableBlock(2500, 600));
            blocks.add(new UnDestroyableBlock(2600, 600));
            blocks.add(new DestroyableBlock(2500, 500));

            blocks.add(new UnDestroyableBlock(2600, 300));
            blocks.add(new UnDestroyableBlock(2600, 200));
            blocks.add(new UnDestroyableBlock(2600, 100));
            blocks.add(new DestroyableBlock(2400, 100));
            blocks.add(new DestroyableBlock(2400, 200));
            blocks.add(new DestroyableBlock(2400, 300));

            blocks.add(new UnDestroyableBlock(2200, 1000));
            blocks.add(new UnDestroyableBlock(2300, 1000));
            blocks.add(new UnDestroyableBlock(2400, 1000));
            blocks.add(new UnDestroyableBlock(2500, 1000));
            blocks.add(new UnDestroyableBlock(2900, 1000));
            blocks.add(new UnDestroyableBlock(2600, 1000));
            blocks.add(new UnDestroyableBlock(2700, 1000));
            blocks.add(new UnDestroyableBlock(2800, 1000));
            blocks.add(new UnDestroyableBlock(2500, 1200));
            blocks.add(new UnDestroyableBlock(2500, 1300));
            blocks.add(new UnDestroyableBlock(2500, 1100));

            blocks.add(new DestroyableBlock(2900, 900));
            blocks.add(new DestroyableBlock(2900, 600));
            blocks.add(new DestroyableBlock(2900, 500));
            blocks.add(new DestroyableBlock(2900, 400));
            blocks.add(new DestroyableBlock(2900, 300));
            blocks.add(new DestroyableBlock(2900, 200));
            blocks.add(new DestroyableBlock(2900, 100));

            blocks.add(new Chariot(3100, 200));
            blocks.add(new DestroyableBlock(3100, 700));
            blocks.add(new DestroyableBlock(3100, 800));
            blocks.add(new UnDestroyableBlock(3100, 900));
            blocks.add(new UnDestroyableBlock(3100, 1000));
            blocks.add(new UnDestroyableBlock(3100, 1100));
            blocks.add(new DestroyableBlock(3100, 1200));
            blocks.add(new DestroyableBlock(3100, 1300));
            blocks.add(new DestroyableBlock(3100, 1500));
            blocks.add(new DestroyableBlock(3100, 1600));
            blocks.add(new UnDestroyableBlock(3400, 200));
            blocks.add(new UnDestroyableBlock(3400, 300));
            blocks.add(new UnDestroyableBlock(3400, 400));
            blocks.add(new UnDestroyableBlock(3400, 500));
            blocks.add(new UnDestroyableBlock(3400, 600));
            blocks.add(new UnDestroyableBlock(3400, 700));
            blocks.add(new UnDestroyableBlock(3400, 800));
            blocks.add(new UnDestroyableBlock(3400, 900));
            blocks.add(new UnDestroyableBlock(3400, 1000));
            blocks.add(new UnDestroyableBlock(3400, 1100));
            blocks.add(new UnDestroyableBlock(3400, 1200));
            blocks.add(new UnDestroyableBlock(3400, 1300));
            blocks.add(new UnDestroyableBlock(3400, 1400));
            blocks.add(new UnDestroyableBlock(3300, 1400));
            blocks.add(new UnDestroyableBlock(3200, 1400));
            blocks.add(new UnDestroyableBlock(3100, 1400));
            blocks.add(new UnDestroyableBlock(3000, 1400));
            blocks.add(new UnDestroyableBlock(2900, 1400));
            blocks.add(new UnDestroyableBlock(2900, 1500));
            blocks.add(new UnDestroyableBlock(2900, 1600));
            blocks.add(new UnDestroyableBlock(2800, 1600));
            blocks.add(new UnDestroyableBlock(2700, 1600));
            blocks.add(new UnDestroyableBlock(2600, 1600));
            blocks.add(new UnDestroyableBlock(2500, 1600));
            blocks.add(new UnDestroyableBlock(2400, 1600));
            blocks.add(new UnDestroyableBlock(2300, 1600));
            blocks.add(new DestroyableBlock(2200, 1600));
            blocks.add(new UnDestroyableBlock(2100, 1600));
            blocks.add(new UnDestroyableBlock(2000, 1600));
            blocks.add(new UnDestroyableBlock(2000, 1500));
            blocks.add(new UnDestroyableBlock(2000, 1400));
            blocks.add(new UnDestroyableBlock(2000, 1300));
            blocks.add(new DestroyableBlock(3400, 1500));
            blocks.add(new DestroyableBlock(3400, 1600));
            blocks.add(new DestroyableBlock(3400, 1700));
            blocks.add(new DestroyableBlock(3400, 1800));
            blocks.add(new DestroyableBlock(3400, 1900));


            for (int i = 0; i < 10; i++) {
                blocks.add(new UnDestroyableBlock(3700, 1000 + i * 100));
            }
            enemyTanks.add(new DynamicTankEasy(3700, 500, 3700, 900, this));
            blocks.add(new DestroyableBlock(3700, 400));
            blocks.add(new DestroyableBlock(3700, 300));
            blocks.add(new DestroyableBlock(3700, 200));
            blocks.add(new DestroyableBlock(3700, 100));


            blocks.add(new DestroyableBlock(3800, 400));
            blocks.add(new DestroyableBlock(3900, 400));
            blocks.add(new DestroyableBlock(4000, 400));
            blocks.add(new DestroyableBlock(4100, 400));

            blocks.add(new UnDestroyableBlock(4100, 300));
            blocks.add(new UnDestroyableBlock(4100, 200));
            blocks.add(new UnDestroyableBlock(4100, 100));

            blocks.add(new UnDestroyableBlock(4100, 400));
            blocks.add(new UnDestroyableBlock(4100, 500));
            blocks.add(new UnDestroyableBlock(4100, 600));
            blocks.add(new UnDestroyableBlock(4100, 700));
            blocks.add(new UnDestroyableBlock(4100, 800));
            blocks.add(new UnDestroyableBlock(4100, 900));
            blocks.add(new UnDestroyableBlock(4100, 1000));
            blocks.add(new UnDestroyableBlock(4100, 1100));
            blocks.add(new UnDestroyableBlock(4100, 1200));
            blocks.add(new UnDestroyableBlock(4100, 1300));
            blocks.add(new UnDestroyableBlock(4100, 1400));
            blocks.add(new UnDestroyableBlock(4100, 1500));


            blocks.add(new UnDestroyableBlock(4200, 1500));
            blocks.add(new UnDestroyableBlock(4300, 1500));
            blocks.add(new UnDestroyableBlock(4400, 1500));

//        enemyTanks.add(new DynamicTankEasy(120, 1800, 350, 1800, this));

            enemyTanks.add(new StaticTankEasy(3000, 100, this));
            enemyTanks.add(new StaticTankEasy(4000, 100, this));
            enemyTanks.add(new StaticTankEasy(4400, 1100, this));

            equipments.add(new UpdateWeapon(300, 1000));
            equipments.add(new UpdateWeapon(900, 1200));
            equipments.add(new UpdateWeapon(2500, 100));
            equipments.add(new Repair(3800, 100));
            equipments.add(new Repair(2400, 1800));


            //Add moving smiles here :D
            movingSmiles.add(new MovingSmile(500, 1000));
            movingSmiles.add(new MovingSmile(1100, 800));
            movingSmiles.add(new MovingSmile(2400, 1700));
            movingSmiles.add(new MovingSmile(4400, 500));
            movingSmiles.add(new MovingSmile(4800, 500));

            setBorders();
        }
    }


    /**
     * The method which updates the game state.
     */
    public void update() {
        if (mouseLeftClicked) {

            if (mouseRightClicked == false) {//bullet

                if (mainTank.getBulletsNumber() > 0)
                    bullets.add(new Bullet(mainTank.getTankCenterX(), mainTank.getTankCenterY(), mainTank.getGunAndBodyRadian(), false));
                mainTank.reduceBulletNumber();
            } else {//rocket
                if (mainTank.getRocketsNumber() > 0)
                    rockets.add(new Rocket(mainTank.getTankCenterX(), mainTank.getTankCenterY(), mainTank.getGunAndBodyRadian(), false));
                mainTank.reduceRocketNumbers();
            }
            mouseLeftClicked = !mouseLeftClicked;
        }

        if (keyUP)
            mainTank.moveLocY(-11);
        if (keyDOWN)
            mainTank.moveLocY(11);
        if (keyLEFT)
            mainTank.moveLocX(-11);
        if (keyRIGHT)
            mainTank.moveLocX(11);

        try {

            //moves bullets
            for (Bullet bullet : bullets) {
                if (bullet != null)
                    bullet.move();
            }
        } catch (ConcurrentModificationException e) {

        }

        try {
            for (Rocket rocket : rockets) {
                if (rocket != null)
                    rocket.move();

            }
        } catch (ConcurrentModificationException e) {

        }

        setMainTankAndGunRadian();
        setEnemyTanksRadian();

        moveDynamicTanks();
        updateAlphaInEquipments();
        checkShootHits();
        removeDeadBullets();
        removeDeadTanks();
        removeDestroyedBlocks();
        checkHitToEquipments();
        renderDestroyedTankPoints();
        attackMovingSmiles();
        findSmileFacesIntersects();
        checkTheGameFinish();

    }

    private void checkTheGameFinish() {
        Rectangle mainTankRec = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());
        Rectangle door = new Rectangle(4800, 1800, 100, 100);

        //erfan kamel she
        if ((door.intersects(mainTankRec)) && (firstLevel == false)) {
            ImageIcon icon = new ImageIcon("./pictures/skull.png");
            JOptionPane.showMessageDialog(null,
                    "LEVEL 2 IS FINISHED !", "  FINISHED!",
                    JOptionPane.INFORMATION_MESSAGE, icon);

            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.die();
            }
        }

        if ((door.intersects(mainTankRec)) && (firstLevel == true)) {
            ImageIcon icon = new ImageIcon("./pictures/skull.png");
            JOptionPane.showMessageDialog(null,
                    "LEVEL 1 IS COMPLETED !", "  FINISHED!",
                    JOptionPane.INFORMATION_MESSAGE, icon);

            firstLevel = false;

            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.die();
            }

            equipments = gameStateSuperInfo.equipments;
            enemyTanks = new ArrayList<>();
            bullets = new ArrayList<>();
            rockets = new ArrayList<>();
            setLevelTwoMap();
        }

    }

    private void setLevelTwoMap() {
        setArrayLists();

        keyDOWN = false;
        keyUP = false;
        keyLEFT = false;
        keyRIGHT = false;

//map two
        enemyTanks.add(new StaticTankEasy(600, 100, this));
        enemyTanks.add(new DynamicTankEasy(300, 500, 500, 500, this));
        enemyTanks.add(new DynamicTankHard(1600, 1300, 2200, 1300, this));

        enemyTanks.add(new StaticTankHard(1500, 1100, this));

        enemyTanks.add(new StaticTankEasy(1300, 1200, this));
        enemyTanks.add(new StaticTankHard(1200, 200, this));

        enemyTanks.add(new StaticTankHard(800, 1300, this));
        enemyTanks.add(new StaticTankHard(2600, 1300, this));

        enemyTanks.add(new StaticTankHard(1850, 800, this));
        enemyTanks.add(new DynamicTankEasy(2600, 130, 2600, 250, this));


        // add blocks here
        blocks.add(new DestroyableBlock(400, 100));
        blocks.add(new DestroyableBlock(400, 200));
        blocks.add(new DestroyableBlock(400, 300));

        blocks.add(new UnDestroyableBlock(100, 300));
        blocks.add(new UnDestroyableBlock(200, 300));
        blocks.add(new UnDestroyableBlock(300, 300));
        blocks.add(new DestroyableBlock(500, 100));

        blocks.add(new UnDestroyableBlock(400, 900));
        blocks.add(new UnDestroyableBlock(500, 900));
        blocks.add(new UnDestroyableBlock(600, 900));
        blocks.add(new UnDestroyableBlock(400, 1000));
        blocks.add(new UnDestroyableBlock(400, 1100));
        blocks.add(new UnDestroyableBlock(500, 1100));
        blocks.add(new UnDestroyableBlock(600, 1100));
        blocks.add(new UnDestroyableBlock(600, 1000));

        blocks.add(new UnDestroyableBlock(100, 900));
        blocks.add(new UnDestroyableBlock(100, 1000));
        blocks.add(new DestroyableBlock(200, 900));
        blocks.add(new DestroyableBlock(300, 900));

        blocks.add(new UnDestroyableBlock(600, 1200));
        blocks.add(new UnDestroyableBlock(600, 1300));
        blocks.add(new UnDestroyableBlock(600, 1400));
        blocks.add(new UnDestroyableBlock(600, 1500));

        blocks.add(new DestroyableBlock(600, 1600));
        blocks.add(new DestroyableBlock(600, 1700));
        blocks.add(new DestroyableBlock(600, 1800));

        blocks.add(new UnDestroyableBlock(700, 1300));
        blocks.add(new UnDestroyableBlock(700, 1400));
        blocks.add(new UnDestroyableBlock(700, 1500));
        blocks.add(new UnDestroyableBlock(800, 1300));
        blocks.add(new UnDestroyableBlock(800, 1400));
        blocks.add(new UnDestroyableBlock(800, 1500));
        blocks.add(new UnDestroyableBlock(900, 1300));
        blocks.add(new UnDestroyableBlock(900, 1400));
        blocks.add(new UnDestroyableBlock(900, 1500));
        blocks.add(new UnDestroyableBlock(1000, 1300));
        blocks.add(new UnDestroyableBlock(1000, 1400));
        blocks.add(new UnDestroyableBlock(1000, 1500));
        blocks.add(new UnDestroyableBlock(1100, 1300));
        blocks.add(new UnDestroyableBlock(1100, 1400));
        blocks.add(new UnDestroyableBlock(1100, 1500));
        blocks.add(new UnDestroyableBlock(1200, 1500));
        blocks.add(new UnDestroyableBlock(1200, 1300));
        blocks.add(new UnDestroyableBlock(1200, 1400));
        blocks.add(new UnDestroyableBlock(1300, 1300));
        blocks.add(new UnDestroyableBlock(1300, 1400));
        blocks.add(new UnDestroyableBlock(1300, 1500));
        blocks.add(new UnDestroyableBlock(1400, 1400));

        blocks.add(new DestroyableBlock(1400, 1500));
        blocks.add(new DestroyableBlock(1400, 1300));

        blocks.add(new UnDestroyableBlock(1400, 1200));
        blocks.add(new UnDestroyableBlock(1400, 1100));
        blocks.add(new UnDestroyableBlock(1400, 1000));
        blocks.add(new UnDestroyableBlock(1500, 1000));
        blocks.add(new DestroyableBlock(1600, 1000));
        blocks.add(new DestroyableBlock(1700, 1000));
        blocks.add(new UnDestroyableBlock(1800, 1000));
        blocks.add(new UnDestroyableBlock(1900, 1000));
        blocks.add(new UnDestroyableBlock(2000, 1000));
        blocks.add(new UnDestroyableBlock(2100, 1000));

        blocks.add(new UnDestroyableBlock(1400, 900));
        blocks.add(new UnDestroyableBlock(1500, 900));
        blocks.add(new DestroyableBlock(1600, 900));
        blocks.add(new DestroyableBlock(1700, 900));
        blocks.add(new UnDestroyableBlock(1800, 900));
        blocks.add(new UnDestroyableBlock(1900, 900));
        blocks.add(new UnDestroyableBlock(2000, 900));
        blocks.add(new UnDestroyableBlock(2100, 900));

        blocks.add(new DestroyableBlock(1300, 1000));
        blocks.add(new DestroyableBlock(1200, 1000));
        blocks.add(new DestroyableBlock(1100, 1000));
        blocks.add(new DestroyableBlock(1000, 1000));
        blocks.add(new DestroyableBlock(900, 1000));
        blocks.add(new DestroyableBlock(800, 1000));
        blocks.add(new DestroyableBlock(700, 1000));
        blocks.add(new DestroyableBlock(700, 900));
        blocks.add(new DestroyableBlock(800, 900));
        blocks.add(new DestroyableBlock(900, 900));
        blocks.add(new DestroyableBlock(1000, 900));
        blocks.add(new DestroyableBlock(1100, 900));
        blocks.add(new DestroyableBlock(1200, 900));
        blocks.add(new DestroyableBlock(1300, 900));

        blocks.add(new Chariot(900, 100));
        blocks.add(new DestroyableBlock(900, 600));
        blocks.add(new DestroyableBlock(1000, 600));
        blocks.add(new DestroyableBlock(900, 700));
        blocks.add(new DestroyableBlock(1000, 700));
        blocks.add(new DestroyableBlock(900, 800));
        blocks.add(new DestroyableBlock(1000, 800));
        blocks.add(new DestroyableBlock(900, 900));
        blocks.add(new DestroyableBlock(1000, 900));
        blocks.add(new DestroyableBlock(900, 500));
        blocks.add(new DestroyableBlock(1000, 500));

        for (int i = 1; i < 10; i++) {
            blocks.add(new UnDestroyableBlock(1600, i * 100));
            blocks.add(new UnDestroyableBlock(1700, i * 100));
            blocks.add(new UnDestroyableBlock(2000, i * 100));
            blocks.add(new UnDestroyableBlock(2100, i * 100));
        }
        blocks.add(new DestroyableBlock(1800, 400));
        blocks.add(new DestroyableBlock(1900, 400));


        blocks.add(new UnDestroyableBlock(2400, 400));
        blocks.add(new UnDestroyableBlock(2500, 400));
        blocks.add(new UnDestroyableBlock(2600, 400));
        blocks.add(new UnDestroyableBlock(2600, 500));
        blocks.add(new UnDestroyableBlock(2600, 600));
        blocks.add(new UnDestroyableBlock(2400, 500));
        blocks.add(new UnDestroyableBlock(2400, 600));
        blocks.add(new UnDestroyableBlock(2500, 600));
        blocks.add(new UnDestroyableBlock(2600, 600));
        blocks.add(new DestroyableBlock(2500, 500));

        blocks.add(new UnDestroyableBlock(2600, 300));
        blocks.add(new UnDestroyableBlock(2600, 200));
        blocks.add(new UnDestroyableBlock(2600, 100));
        blocks.add(new DestroyableBlock(2400, 100));
        blocks.add(new DestroyableBlock(2400, 200));
        blocks.add(new DestroyableBlock(2400, 300));

        blocks.add(new UnDestroyableBlock(2200, 1000));
        blocks.add(new UnDestroyableBlock(2300, 1000));
        blocks.add(new UnDestroyableBlock(2400, 1000));
        blocks.add(new UnDestroyableBlock(2500, 1000));
        blocks.add(new UnDestroyableBlock(2900, 1000));
        blocks.add(new UnDestroyableBlock(2600, 1000));
        blocks.add(new UnDestroyableBlock(2700, 1000));
        blocks.add(new UnDestroyableBlock(2800, 1000));
        blocks.add(new DestroyableBlock(2500, 1200));
        blocks.add(new DestroyableBlock(2500, 1300));
        blocks.add(new DestroyableBlock(2500, 1100));

        blocks.add(new DestroyableBlock(2900, 900));
        blocks.add(new DestroyableBlock(2900, 600));
        blocks.add(new DestroyableBlock(2900, 500));
        blocks.add(new DestroyableBlock(2900, 400));
        blocks.add(new DestroyableBlock(2900, 300));
        blocks.add(new DestroyableBlock(2900, 200));
        blocks.add(new DestroyableBlock(2900, 100));

        blocks.add(new Chariot(3100, 200));
        blocks.add(new DestroyableBlock(3100, 700));
        blocks.add(new DestroyableBlock(3100, 800));
        blocks.add(new UnDestroyableBlock(3100, 900));
        blocks.add(new UnDestroyableBlock(3100, 1000));
        blocks.add(new UnDestroyableBlock(3100, 1100));
        blocks.add(new DestroyableBlock(3100, 1200));
        blocks.add(new DestroyableBlock(3100, 1300));
        blocks.add(new DestroyableBlock(3100, 1500));
        blocks.add(new DestroyableBlock(3100, 1600));

        blocks.add(new UnDestroyableBlock(3400, 900));
        blocks.add(new UnDestroyableBlock(3400, 1000));
        blocks.add(new UnDestroyableBlock(3400, 1100));
        blocks.add(new UnDestroyableBlock(3400, 1200));
        blocks.add(new UnDestroyableBlock(3400, 1300));
        blocks.add(new UnDestroyableBlock(3400, 1400));
        blocks.add(new UnDestroyableBlock(3300, 1400));
        blocks.add(new UnDestroyableBlock(3200, 1400));
        blocks.add(new UnDestroyableBlock(3100, 1400));
        blocks.add(new UnDestroyableBlock(3000, 1400));
        blocks.add(new UnDestroyableBlock(2900, 1400));
        blocks.add(new UnDestroyableBlock(2900, 1500));
        blocks.add(new UnDestroyableBlock(2900, 1600));
        blocks.add(new UnDestroyableBlock(2800, 1600));
        blocks.add(new UnDestroyableBlock(2700, 1600));
        blocks.add(new UnDestroyableBlock(2600, 1600));
        blocks.add(new UnDestroyableBlock(2500, 1600));
        blocks.add(new UnDestroyableBlock(2400, 1600));
        blocks.add(new UnDestroyableBlock(2300, 1600));
        blocks.add(new DestroyableBlock(2200, 1600));
        blocks.add(new UnDestroyableBlock(2100, 1600));
        blocks.add(new UnDestroyableBlock(2000, 1600));
        blocks.add(new UnDestroyableBlock(2000, 1500));
        blocks.add(new UnDestroyableBlock(2000, 1400));
        blocks.add(new UnDestroyableBlock(2000, 1300));


        blocks.add(new DestroyableBlock(3400, 1500));
        blocks.add(new DestroyableBlock(3400, 1600));
        blocks.add(new DestroyableBlock(3400, 1700));
        blocks.add(new DestroyableBlock(3400, 1800));
        blocks.add(new DestroyableBlock(3400, 1900));


        for (int i = 0; i < 10; i++) {
            blocks.add(new UnDestroyableBlock(3700, 1000 + i * 100));
        }
        enemyTanks.add(new DynamicTankEasy(3700, 500, 3700, 900, this));
        enemyTanks.add(new DynamicTankEasy(4900, 1500, 4900, 1900, this));
        blocks.add(new DestroyableBlock(3700, 400));
        blocks.add(new DestroyableBlock(3700, 300));
        blocks.add(new DestroyableBlock(3700, 200));
        blocks.add(new DestroyableBlock(3700, 100));


        blocks.add(new DestroyableBlock(3800, 400));
        blocks.add(new DestroyableBlock(3900, 400));
        blocks.add(new DestroyableBlock(4000, 400));
        blocks.add(new DestroyableBlock(4100, 400));

        blocks.add(new UnDestroyableBlock(4100, 300));
        blocks.add(new UnDestroyableBlock(4100, 200));
        blocks.add(new UnDestroyableBlock(4100, 100));

        blocks.add(new UnDestroyableBlock(4100, 400));
        blocks.add(new UnDestroyableBlock(4100, 500));
        blocks.add(new UnDestroyableBlock(4100, 600));
        blocks.add(new UnDestroyableBlock(4100, 700));
        blocks.add(new DestroyableBlock(4100, 800));
        blocks.add(new UnDestroyableBlock(4100, 900));
        blocks.add(new UnDestroyableBlock(4100, 1000));
        blocks.add(new UnDestroyableBlock(4100, 1100));
        blocks.add(new UnDestroyableBlock(4100, 1200));
        blocks.add(new UnDestroyableBlock(4100, 1300));
        blocks.add(new DestroyableBlock(4100, 1400));
        blocks.add(new UnDestroyableBlock(4100, 1500));


        blocks.add(new UnDestroyableBlock(4200, 1500));
        blocks.add(new UnDestroyableBlock(4300, 1500));
        blocks.add(new UnDestroyableBlock(4400, 1500));
        blocks.add(new UnDestroyableBlock(4400, 1600));
        blocks.add(new DestroyableBlock(4400, 1700));


        enemyTanks.add(new DynamicTankEasy(300, 1800, 800, 1800, this));


        enemyTanks.add(new StaticTankEasy(3000, 100, this));
        enemyTanks.add(new StaticTankEasy(4000, 100, this));
        enemyTanks.add(new StaticTankEasy(4400, 1100, this));
        enemyTanks.add(new StaticTankEasy(3400, 1100, this));
        enemyTanks.add(new StaticTankEasy(4400, 1100, this));
        enemyTanks.add(new StaticTankEasy(4400, 1100, this));
/**

 //equipments.add(new UpdateWeapon(300, 1000));
 equipments.add(new UpdateWeapon(700, 1200));
 equipments.add(new UpdateWeapon(2500, 100));


 // equipments.add(new Repair(3800, 100));
 equipments.add(new Repair(2400, 1800));
 **/

        //Add moving smiles here :D
        movingSmiles.add(new MovingSmile(500, 1000));
        movingSmiles.add(new MovingSmile(1100, 800));
        movingSmiles.add(new MovingSmile(2400, 1700));
        movingSmiles.add(new MovingSmile(4400, 500));
        movingSmiles.add(new MovingSmile(4800, 500));
        movingSmiles.add(new MovingSmile(4400, 500));
        movingSmiles.add(new MovingSmile(1800, 500));
        movingSmiles.add(new MovingSmile(3800, 500));
        movingSmiles.add(new MovingSmile(800, 500));
        movingSmiles.add(new MovingSmile(2000, 500));


        setBorders();

    }

    private void setBorders() {

        for (int i = 0; i <= 49; i++) {
            blocks.add(new UnDestroyableBlock(i * 100, 0));
            blocks.add(new UnDestroyableBlock(i * 100, 1900));

            if (i < 20) {
                blocks.add(new UnDestroyableBlock(0, i * 100));
                blocks.add(new UnDestroyableBlock(4900, i * 100));
            }
        }
    }

    private void setArrayLists() {
        mainTank = new Tank(this);
        bullets = new ArrayList<>();
        rockets = new ArrayList<>();
        enemyTanks = new ArrayList<>();
        blocks = gameStateSuperInfo.blocks;
        equipments = gameStateSuperInfo.equipments;
        destroyedTankTemporaryTrashPoints = new ArrayList<>();
        movingSmiles = new ArrayList<>();
    }


    private void findSmileFacesIntersects() {
        for (int i = 0; i < movingSmiles.size(); i++) {
            Rectangle smileFaceRec = new Rectangle(movingSmiles.get(i).getLocX(), movingSmiles.get(i).getLocY(), MovingSmile.xPixels, MovingSmile.yPixels);
            Rectangle mainTankRec = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());

            if (smileFaceRec.intersects(mainTankRec)) {
                Point tempPoint = new Point(movingSmiles.get(i).getLocX(), movingSmiles.get(i).getLocY());
                destroyedTankTemporaryTrashPoints.add(tempPoint);
                mainTank.reduceHealth(MovingSmile.DAMAGE);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/MineBoom.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(0);
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                movingSmiles.remove(i);
                i--;
            }
        }
    }

    private void attackMovingSmiles() {
        for (MovingSmile movingSmile : movingSmiles) {
            movingSmile.attackToThisLocation(mainTank.getTankCenterX(), mainTank.getTankCenterY());

        }
    }

    private void renderDestroyedTankPoints() {
        for (int i = 0; i < destroyedTankTemporaryTrashPoints.size(); i++) {
            destroyedTankTemporaryTrashPoints.get(i).reduceTimeToRemove(3);

            if (destroyedTankTemporaryTrashPoints.get(i).getTimeToRemove() < 1)
                destroyedTankTemporaryTrashPoints.remove(i);
        }
    }

    private void checkHitToEquipments() {

        for (int i = 0; i < equipments.size(); i++) {
            try {

                Rectangle mainTankRec = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());
                Rectangle equipmentRec = new Rectangle(equipments.get(i).getLocX(), equipments.get(i).getLocY(), equipments.get(i).getxPixels(), equipments.get(i).getyPixels());

                if (mainTankRec.intersects(equipmentRec)) {

                    if (equipments.get(i) instanceof Cartridge) {
                        mainTank.addToBullets(50);
                        mainTank.addToRockets(30);
                    }
                    if (equipments.get(i) instanceof UpdateWeapon) {
                        mainTank.updateWeapon();
                    }
                    if (equipments.get(i) instanceof Repair) {
                        mainTank.addToHealth(30);
                    }

                    equipments.remove(i);
                    i--;
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }

    private void updateAlphaInEquipments() {
        for (Equipment equipment : equipments) {
            equipment.updateAlpha();
        }
    }

    private void removeDestroyedBlocks() {

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).isAlive() == false) {
                blocks.remove(i);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/softwall.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(0);
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                i--;
            }
        }

    }

    private void moveDynamicTanks() {
        for (EnemyTank enemyTank : enemyTanks) {
            if (enemyTank instanceof DynamicTankEasy) {
                ((DynamicTankEasy) enemyTank).moveAutomatic(mainTank);
            }
            if (enemyTank instanceof DynamicTankHard) {
                ((DynamicTankHard) enemyTank).moveAutomatic(mainTank);
            }
        }
    }

    private void setEnemyTanksRadian() {
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.setGunAndBodyRadian(Geometry.radian(enemyTank.getTankCenterX(), enemyTank.getTankCenterY(),
                    mainTank.getTankCenterX(), mainTank.getTankCenterY()));
        }
    }

    private void setMainTankAndGunRadian() {
        int mainX = getMainTank().getTankCenterX() - GameFrame.TANK_IN_MAP_X;
        int mainY = getMainTank().getTankCenterY() - GameFrame.TANK_IN_MAP_Y;

        mainTank.setGunAndBodyRadian(Geometry.radian(getMainTank().getTankCenterX() - mainX, getMainTank().getTankCenterY() - mainY,
                getMouseMotionX(), getMouseMotionY()));

    }


    public KeyListener getKeyListener() {
        return keyHandler;
    }

    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/motor1.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();

                if (GameLoop.isPaused == false)
                    clip.open(audioInputStream);

                clip.start();
                clip.loop(0);
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }


            switch (e.getKeyCode()) {

                case KeyEvent.VK_W:
                    keyUP = true;
                    break;
                case KeyEvent.VK_S:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_A:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_D:
                    keyRIGHT = true;
                    break;
//                case KeyEvent.VK_ESCAPE:
//                    gameOver = true;
//                    break;
                case KeyEvent.VK_ESCAPE:
                    GameLoop.isPaused = !GameLoop.isPaused;
                    System.out.println(GameLoop.isPaused);
                    break;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_W:
                    keyUP = false;
                    break;
                case KeyEvent.VK_S:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_A:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_D:
                    keyRIGHT = false;
                    break;
            }
        }
    }

    /**
     * This method will check if the bullets/rockets hit the enemy .
     */
    private void checkShootHits() {
        for (EnemyTank enemyTank : enemyTanks) {
            for (int i = 0; i < rockets.size(); i++) {
                try {
                    if (rockets.get(i) != null) {
                        if ((rockets.get(i).getLocX() > enemyTank.getLocX()) && (rockets.get(i).getLocX() < enemyTank.getEndLocX()) &&
                                (rockets.get(i).getLocY() > enemyTank.getLocY()) && (rockets.get(i).getLocY() < enemyTank.getEndLocY())) {
                            if (rockets.get(i).isFromEnemy() == false) {
                                System.out.println("zamani ke tir sangin khodam mizanam va barkhord mikonad");
                                enemyTank.reduceHealth(Rocket.DAMAGE + mainTank.getGunLevel() * 2);
                            }
                            rockets.remove(i);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }

        for (int j = 0; j < movingSmiles.size(); j++) {
            for (int i = 0; i < rockets.size(); i++) {
                try {
                    if (rockets.get(i) != null) {
                        if ((rockets.get(i).getLocX() > movingSmiles.get(j).getLocX()) && (rockets.get(i).getLocX() < movingSmiles.get(j).getLocX() + MovingSmile.xPixels) &&
                                (rockets.get(i).getLocY() > movingSmiles.get(j).getLocY()) && (rockets.get(i).getLocY() < movingSmiles.get(j).getLocY() + MovingSmile.yPixels)) {
                            if (rockets.get(i).isFromEnemy() == false) {
                                System.out.printf("tir mizanam3");
                                Point tempPoint = new Point(movingSmiles.get(j).getLocX(), movingSmiles.get(j).getLocY());
                                destroyedTankTemporaryTrashPoints.add(tempPoint);
                                movingSmiles.remove(j);
                                rockets.remove(i);
                                j--;
                                break;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }

        }

        for (int j = 0; j < movingSmiles.size(); j++) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i) != null) {
                    if ((bullets.get(i).getLocX() > movingSmiles.get(j).getLocX()) && (bullets.get(i).getLocX() < movingSmiles.get(j).getLocX() + MovingSmile.xPixels) &&
                            (bullets.get(i).getLocY() > movingSmiles.get(j).getLocY()) && (bullets.get(i).getLocY() < movingSmiles.get(j).getLocY() + MovingSmile.yPixels)) {
                        if (bullets.get(i).isFromEnemy() == false) {
                            System.out.printf("tir mizanam2");
                            Point tempPoint = new Point(movingSmiles.get(j).getLocX(), movingSmiles.get(j).getLocY());
                            destroyedTankTemporaryTrashPoints.add(tempPoint);
                            movingSmiles.remove(j);
                            j--;
                            bullets.remove(i);
                            break;
                        }
                    }
                }
            }

        }


        for (EnemyTank enemyTank : enemyTanks) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i) != null) {
                    if ((bullets.get(i).getLocX() > enemyTank.getLocX()) && (bullets.get(i).getLocY() > enemyTank.getLocY()) &&
                            (bullets.get(i).getLocX() < enemyTank.getEndLocX()) && (bullets.get(i).getLocY() < enemyTank.getEndLocY())) {
                        if (bullets.get(i).isFromEnemy() == false) {
                            System.out.println("zamani ke tir sabok khodam mizanam va barkhord mikonad be tank");
                            enemyTank.reduceHealth(Bullet.DAMAGE + mainTank.getGunLevel() * 3);
                        }
                        bullets.remove(i);
                    }
                }
            }
        }


        for (Block block : blocks) {
            for (int i = 0; i < rockets.size(); i++) {
                try {


                    if (rockets.get(i) != null) {
                        if ((rockets.get(i).getLocX() > block.getLocX()) && (rockets.get(i).getLocX() < block.getEndX()) &&
                                (rockets.get(i).getLocY() > block.getLocY()) && (rockets.get(i).getLocY() < block.getEndY())) {

                            if (rockets.get(i).isFromEnemy() == false) {
                                System.out.println("ba tir sangin bezanam be divar");
                                block.reduceHealth(Rocket.DAMAGE);
                            }

                            rockets.remove(i);

                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }
        }

        for (Block block : blocks) {
            for (int i = 0; i < bullets.size(); i++) {

                if (bullets.get(i) != null) {
                    if ((bullets.get(i).getLocX() > block.getLocX()) && (bullets.get(i).getLocY() > block.getLocY()) &&
                            (bullets.get(i).getLocX() < block.getEndX()) && (bullets.get(i).getLocY() < block.getEndY())) {
                        if (bullets.get(i).isFromEnemy() == false) {
                            System.out.println("ba tir sabok bezanam be divar");
                            block.reduceHealth(Bullet.DAMAGE);
                        }
                        bullets.remove(i);
                    }
                }
            }
        }


        for (int i = 0; i < rockets.size(); i++) {
            if (rockets.get(i) != null) {
                if ((rockets.get(i).getLocX() > mainTank.getLocX()) && (rockets.get(i).getLocX() < mainTank.getEndLocX()) &&
                        (rockets.get(i).getLocY() > mainTank.getLocY()) && (rockets.get(i).getLocY() < mainTank.getEndLocY())) {

                    if (rockets.get(i).isFromEnemy() == true) {

                        System.out.println("zamani ke khodam tir mikhoram-rocket mikhoram");
                        mainTank.reduceHealth(Rocket.DAMAGE);
                    }
                    rockets.remove(i);
                }
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i) != null) {
                if ((bullets.get(i).getLocX() > mainTank.getLocX()) && (bullets.get(i).getLocY() > mainTank.getLocY()) &&
                        (bullets.get(i).getLocX() < mainTank.getEndLocX()) && (bullets.get(i).getLocY() < mainTank.getEndLocY())) {
                    if (bullets.get(i).isFromEnemy() == true) {
                        System.out.println("zamani ke khodam tir mikhoram-bullet mikhoram");
                        mainTank.reduceHealth(Bullet.DAMAGE);
                    }
                    bullets.remove(i);
                }
            }
        }
    }

    private void removeDeadBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i) != null)
                if (bullets.get(i).checkAlive() == false) {
                    bullets.remove(i);
                    i -= 1;
                }
        }
        for (int i = 0; i < rockets.size(); i++) {
            if (rockets.get(i) != null)
                if (rockets.get(i).checkAlive() == false) {
                    rockets.remove(i);
                    i--;
                }
        }
    }

    public void removeDeadTanks() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            if (enemyTanks.get(i).getHealth() < 1) {
                Point tempPoint = new Point(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY());
                destroyedTankTemporaryTrashPoints.add(tempPoint);

                //Random equipment
                SecureRandom random = new SecureRandom();
                int rand = random.nextInt(7);

                if (rand == 0) {
                    equipments.add(new Cartridge(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }
                if (rand == 1) {
                    equipments.add(new Repair(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }
                if (rand == 2) {
                    equipments.add(new UpdateWeapon(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }
                if (rand == 3) {
                    movingSmiles.add(new MovingSmile(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }


                enemyTanks.remove(i);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/enemydestroyed.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(0);
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                i--;
            }
        }
    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            if (SwingUtilities.isLeftMouseButton(e)) {
                if ((GameLoop.isPaused == true) && ((mouseX > 132) && (mouseX < 391) && (mouseY > 330) && (mouseY < 400))) {
                    GameLoop.isPaused = false;
                    playSelectMusic();
                }
                if ((GameLoop.isPaused == true) && ((mouseX > 129) && (mouseX < 314) && (mouseY > 470) && (mouseY < 539))) {
                    Serialize.serializeObject(gameStateSuperInfo);
                    saveEnemyTanks();
                    playSelectMusic();
                }
                if ((GameLoop.isPaused == true) && ((mouseX > 128) && (mouseX < 275) && (mouseY > 609) && (mouseY < 680))) {
                    playSelectMusic();
                    System.exit(0);
                }


                mouseLeftClicked = true;
                if (click % 2 == 1) {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/heavygun.wav").getAbsoluteFile());

                        if (GameLoop.isPaused == false) {
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }


                } else {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/EnemyBulletToMyTank.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        if (GameLoop.isPaused == false) {
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }

                }
            }
            if (SwingUtilities.isRightMouseButton(e)) {
                mouseRightClicked = !mouseRightClicked;
                click++;
            }
        }

        private void saveEnemyTanks() {
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {
                fw = new FileWriter("./saves/enemyTanks.txt");
                bw = new BufferedWriter(fw);

            } catch (IOException e) {
                e.printStackTrace();

            }

            for (EnemyTank enemyTank : enemyTanks) {
                if (enemyTank instanceof DynamicTankEasy) {
                    try {
                        bw.write("1" + "-" + enemyTank.getLocX() + "-" + enemyTank.getLocY()+"|");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (enemyTank instanceof DynamicTankHard) {
                    try {
                        bw.write("2" + "-" + enemyTank.getLocX() + "-" + enemyTank.getLocY()+"|");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (enemyTank instanceof StaticTankEasy) {
                    try {
                        bw.write("3" + "-" + enemyTank.getLocX() + "-" + enemyTank.getLocY()+"|");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (enemyTank instanceof StaticTankHard) {
                    try {
                        bw.write("4" + "-" + enemyTank.getLocX() + "-" + enemyTank.getLocY()+"|");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            mouseLeftClicked = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
//			mouseX = e.getX();
//			mouseY = e.getY();
        }

        // for moving mouse !
        @Override
        public void mouseMoved(MouseEvent e) {
            mouseMotionX = e.getX();
            mouseMotionY = e.getY();
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }

    public int getMouseMotionX() {
        return mouseMotionX;
    }

    public int getMouseMotionY() {
        return mouseMotionY;
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public ArrayList<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void addToBullets(Bullet bullet) {
        bullets.add(bullet);
    }

    public static void addToRockets(Rocket rocket) {
        try {
            rockets.add(rocket);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public ArrayList<Point> getDestroyedTankTemporaryTrashPoints() {
        return destroyedTankTemporaryTrashPoints;
    }

    public ArrayList<MovingSmile> getMovingSmiles() {
        return movingSmiles;
    }

    public static void setDifficultyLevel(int difficultyLevel) {
        GameState.difficultyLevel = difficultyLevel;
    }

    private void playBackgroundMusic() {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./sound/background.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
        clip.start();
    }


    private void playSelectMusic() {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./sound/tick.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//        gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
        clip.start();
    }
}
