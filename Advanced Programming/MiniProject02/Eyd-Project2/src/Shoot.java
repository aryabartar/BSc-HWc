import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

/**
 * This class implements shooting to points in normal and faulting mode for both computer and user.
 * Normal shooting in computer mode is intelligent and computer can use relative methods.
 */
public class Shoot {
    private boolean isBulletFaulting; //For faulting bullet ==> true / for normal bullet ==> false
    private boolean[][] shootedPlaces;
    private boolean hitLastTime = false;
    private int hitCounter = 7;
    private int lastX = 0;
    private int lastY = 0;
    private int tempLastX = 0;
    private int tempLastY = 0;

    /**
     * TODO
     * This constructor asks from user if the bullet is faulting.
     */
    public Shoot() {
        Scanner input = new Scanner(System.in);
        System.out.print("Is your bullet faulting ? (true ==> faulting / false ==> normal bullet) : ");
        isBulletFaulting = input.nextBoolean();
        shootedPlaces = new boolean[10][10];
    }

    /**
     * TODO
     * This constructor is for CPU and computer. In this constructor bullets are normal
     * because we computer uses AI for its shooting places.
     * NOTE : This constructor is only for computer and CPU.
     *
     * @param computer is used for overloading constructor
     */
    public Shoot(String computer) {
        isBulletFaulting = false; //Bullets are normal
        shootedPlaces = new boolean[10][10];
        hitLastTime = false;
    }

    /**
     * TODO
     * This method is for shooting to a certain point considering whether the bullet is normal
     * or faulting.
     *
     * @return point
     */
    public int[] shoot() {
        int[] point;
        if (isBulletFaulting == false)
            point = normalShoot();
        else
            point = faultingShoot();
        return point;
    }

    /**
     * TODO
     * This class is used for shooting .
     * NOTE : This method is only for CPU and computer
     *
     * @param computer is used for overloading
     */
    public int[] shoot(String computer, boolean hit) {
        int[] point;
        if (isBulletFaulting == false)
            point = normalShoot("computer", hit);
        else
            point = faultingShoot();
        return point;
    }

    /**
     * TODO
     * This class is used to faulting shoot.The user inputs X and Y and this method shoots to 9
     * around points.
     *
     * @return point
     */
    private int[] faultingShoot() {
        int x;
        int y;
        int[] point;

        System.out.println("**** You are shooting to other player board. ****");
        System.out.print("Input X : ");
        x = takeIntBetween0and9();
        System.out.print("\nInput Y : ");
        y = takeIntBetween0and9();

        point = faultingPointRandom(x, y);
        shootedPlaces[point[0]][point[1]] = true;

        return point;
    }

    /**
     * TODO
     * This method is used to generate random point around x and y.
     *
     * @param x for row
     * @param y for column
     * @return
     */
    private int[] faultingPointRandom(int x, int y) {
        int xRange;
        int yRange;
        int randomCounter = 0;
        do {
            int[] points = new int[9];
            SecureRandom rand = new SecureRandom();
            xRange = rand.nextInt(3) - 1 + x;
            yRange = rand.nextInt(3) - 1 + y;
            randomCounter++;
            if (randomCounter > 300)
                break;
        }
        while ((xRange < 0) || (xRange > 9) || (yRange < 0) || (yRange > 9) || (shootedPlaces[xRange][yRange] == true));

        if (randomCounter > 300) {
            System.out.println("Can not shoot to this point because there is no place around x and y .");
            int[] point = faultingShoot();
            return point;
        } else {
            int[] point = {xRange, yRange};
            return point;
        }

    }

    /**
     * TODO
     * This method is used to shoot to [x,y] point.
     *
     * @return point
     */
    private int[] normalShoot() {
        int x;
        int y;
        boolean isPointValid;

        do {

            isPointValid = true;
            System.out.println("**** You are shooting to other player board. ****");
            System.out.print("Input X : ");
            x = takeIntBetween0and9();
            System.out.print("\nInput Y : ");
            y = takeIntBetween0and9();

            if (shootedPlaces[x][y] == true) {
                System.out.println("Error : You've already shot it to this point.");
                isPointValid = false;
            } else
                shootedPlaces[x][y] = true;

        } while (isPointValid == false);

        int[] point = {x, y};
        return point;

    }

    /**
     * This method is used to shoot to [x,y] point.
     * NOTE : This method is only for CPU and computer.
     *
     * @param computer
     * @return
     */
    private int[] normalShoot(String computer, boolean hit) {
        int x;
        int y;
        Random rand = new Random();

        if (hit == true) {
            lastX = tempLastX;
            lastY = tempLastY;
            hitCounter = 0;
            hitLastTime = true ;
        }

        if (hitLastTime == true) {
            int whileCounter = 0 ;
            do {
                x = lastX + rand.nextInt(3) - 1;
                y = lastY + rand.nextInt(3) - 1;
                if (whileCounter == 200) {
                    do {
                        x = rand.nextInt(10);
                        y = rand.nextInt(10);
                    }while (shootedPlaces[x][y] ==true) ;
                    hitCounter = 0;
                    break;
                }
                whileCounter++ ;
            } while ((x < 0) || (x > 9) || (y < 0) || (y > 9)||(shootedPlaces[x][y] == true));
            hitCounter++;
        } else {
            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            }while (shootedPlaces[x][y] ==true) ;
            hitCounter = 0;
        }

        tempLastX = x;
        tempLastY = y;

        shootedPlaces[x][y] = true ;

        if (hitCounter == 7)
            hitLastTime = false;

        int[] point = {x, y};
        return point;
    }

    /**
     * TODO
     * This method takes an integer value between 0 and 9 and if
     * the user inputs invalid number it takes the number again.
     *
     * @return value
     */
    private int takeIntBetween0and9() {
        int value;
        Scanner input = new Scanner(System.in);
        value = input.nextInt();
        while ((value > 9) || (value < 0)) {
            System.out.print("Error : Enter number between 0 and 9 : ");
            value = input.nextInt();
        }
        return value;
    }

}
