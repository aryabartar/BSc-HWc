import java.util.Random;
import java.util.Scanner;

/**
 * The Board class implements a board for the player and can display the user's board
 * in different ways and place the ships in the board and when the user shoots to ships
 * can perform appropriate task.
 *
 * @author Arya Khaligh
 */
public class Board {

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    private final int numberOfShips = 5;
    private Ship[] ships;
    private boolean[][] shipsPlace;
    private char[][] warBoard; //indicates hit and miss .
    private char[][] myWarBoard; //indicates my WarBoard .

    /**
     * TODO
     * This constructor initializes class fields .
     * Number of ships can vary in other programs .
     */
    public Board() {
        ships = new Ship[numberOfShips];
        shipsPlace = new boolean[10][10];
        warBoard = new char[10][10];
        myWarBoard = new char[10][10];
    }

    /**
     * TODO
     * This class makes 5 ships and put them in the shipsPlace and myWarBoard .
     * This class invokes appropriate methods for making instance of ships .
     */
    public void setShips() {
        boolean isHorizontal;
        Scanner input = new Scanner(System.in);
        boolean isShipValid;

        for (int i = 0; i < numberOfShips; i++) {
            System.out.println("\n\n");
            System.out.println("\n**** You are placing ship " + (i + 1) + "  (" + (numberOfShips - i - 1) + " ships remained) ****");
            drawShipsPlace();

            do {
                System.out.print("\nIs the ship horizontal ? (true if horizontal | false if vertical) : ");
                isHorizontal = input.nextBoolean();

                //This if/else invokes appropriate method for making instance of ship and placing in shipsPlace.
                if (isHorizontal == true)
                    isShipValid = setHorizontalShip(i);
                else
                    isShipValid = setVerticalShip(i);

                if (isShipValid == false)
                    System.out.println("Error : You hit the other ships, input valid range for the ship. ");
            } while (isShipValid == false);

        }

        //These two nested loops iterate through all cells and marks ships with '@' symbol.
        for (int row = 0; row < 10; row++)
            for (int column = 0; column < 10; column++)
                if (shipsPlace[row][column] == true)
                    myWarBoard[row][column] = '@';

        System.out.println("     ***********************\nYour ships are ready !");
        drawShipsPlace(); //draws shipsPlace for last time.

    }

    /**
     * This class makes 5 ships and put them in the shipsPlace and myWarBoard .
     * This class invokes appropriate methods for making instance of ships .
     * This class is special for CPU and computer .
     *
     * @param computer
     */
    public void setShips(String computer) {
        boolean isHorizontal;
        Random rand = new Random();
        boolean isShipValid;

        for (int i = 0; i < numberOfShips; i++) {
            System.out.println("");
            drawShipsPlace();

            do {
                isHorizontal = rand.nextBoolean();

                //This if/else invokes appropriate method for making instance of ship and placing in shipsPlace.
                if (isHorizontal == true)
                    isShipValid = setHorizontalShip(i, computer);
                else
                    isShipValid = setVerticalShip(i, computer);

            } while (isShipValid == false);

        }

        //These two nested loops iterate through all cells and marks ships with '@' symbol.
        for (int row = 0; row < 10; row++)
            for (int column = 0; column < 10; column++)
                if (shipsPlace[row][column] == true)
                    myWarBoard[row][column] = '@';
        System.out.println("");
        drawShipsPlace(); //draws shipsPlace for last time.

        System.out.println("     ##################################\n" +
                "     #####COMPUTER SHIPS ARE READY!####\n" +
                "     ##################################\n");

    }

    /**
     * TODO
     * When setShips method calls this private method , setVerticalShip gets valid ship place
     * from user and instantiate a Ship object.
     *
     * @param shipNumber indicates which ship to instantiate
     */
    private boolean setVerticalShip(int shipNumber) {

        int columnNumber; //Indicates ship column.
        int startingRow;//Ships starts from startingRow to endingRow.
        int endingRow;
        int length;//This variable is equal to ship length.
        boolean isShipValid = true;

        do {
            System.out.print("\nInput column : ");
            columnNumber = takeIntBetween0and9();

            System.out.print("Input starting row : ");
            startingRow = takeIntBetween0and9();

            System.out.print("Input ending row : ");
            endingRow = takeIntBetween0and9();

            //This if statement swaps startingRow and endingRow if StartingRow is bigger than endingRow.
            if (startingRow > endingRow) {
                int tempSwap = startingRow;
                startingRow = endingRow;
                endingRow = tempSwap;
            }

            length = endingRow - startingRow; //After swapping length is always positive.

            //This if statement checks if length is invalid and prints appropriate string.
            if ((length != 1) && (length != 2) && (length != 3) && (length != 4))
                System.out.println("### Difference between starting value and ending value should be between " +
                        " 2 and 5. input a valid number . ###");

        } while ((length != 1) && (length != 2) && (length != 3) && (length != 4));

        boolean[][] place = new boolean[10][10];

        //Checks if the ship hits other ship or not
        for (int row = startingRow; row <= endingRow; row++)
            if (shipsPlace[row][columnNumber] == true)
                isShipValid = false;


        //This for statement initializes place array that we will pass it to Ship constructor.
        if (isShipValid == true)
            for (int row = startingRow; row <= endingRow; row++) {
                place[row][columnNumber] = true;
                shipsPlace[row][columnNumber] = true;
            }


        //Instantiates Ship object.
        ships[shipNumber] = new Ship(length, false, place);

        return isShipValid;
    }

    /**
     * TODO
     * When setShips method calls this private method , setVerticalShip gets valid ship place
     * from computer and instantiate a Ship object.
     * NOTE : This class is for CPU and computer.
     *
     * @param shipNumber indicates which ship to instantiate
     */
    private boolean setVerticalShip(int shipNumber, String computer) {

        int columnNumber; //Indicates ship column.
        int startingRow;//Ships starts from startingRow to endingRow.
        int endingRow;
        boolean isShipValid = true;
        int length;//This variable is equal to ship length.
        Random rand = new Random();

        do {
            columnNumber = rand.nextInt(10);
            startingRow = rand.nextInt(10);
            endingRow = rand.nextInt(10);

            //This if statement swaps startingRow and endingRow if StartingRow is bigger than endingRow.
            if (startingRow > endingRow) {
                int tempSwap = startingRow;
                startingRow = endingRow;
                endingRow = tempSwap;
            }

            length = endingRow - startingRow; //After swapping length is always positive.

        } while ((length != 1) && (length != 2) && (length != 3) && (length != 4));

        boolean[][] place = new boolean[10][10];

        //Checks if the ship hits other ship or not
        for (int row = startingRow; row <= endingRow; row++)
            if (shipsPlace[row][columnNumber] == true)
                isShipValid = false;


        //This for statement initializes place array that we will pass it to Ship constructor.
        if (isShipValid == true)
            for (int row = startingRow; row <= endingRow; row++) {
                place[row][columnNumber] = true;
                shipsPlace[row][columnNumber] = true;
            }


        //Instantiates Ship object.
        ships[shipNumber] = new Ship(length, false, place);

        return isShipValid;
    }

    /**
     * TODO
     * When setShips method calls this private method , setHorizontalShip gets valid ship place
     * from user and instantiate a Ship object.
     *
     * @param shipNumber indicates which ship to instantiate
     */
    private boolean setHorizontalShip(int shipNumber) {

        int rowNumber; //Indicates ship row.
        int startingColumn;//Ship starts from startingColumn to endingColumn.
        int endingColumn;
        int length;//This variable is equal to ship length.
        boolean isShipValid = true;

        do {
            System.out.print("\nInput row : ");
            rowNumber = takeIntBetween0and9();

            System.out.print("Input starting column : ");
            startingColumn = takeIntBetween0and9();

            System.out.print("Input ending column : ");
            endingColumn = takeIntBetween0and9();

            //This if statement swaps startingColumn and endingColumn if startingColumn is bigger than endingColumn.
            if (startingColumn > endingColumn) {
                int tempSwap = startingColumn;
                startingColumn = endingColumn;
                endingColumn = tempSwap;
            }

            length = endingColumn - startingColumn;//After swapping length is always positive.

            //This if statement checks if length is invalid and prints appropriate string.
            if ((length != 1) && (length != 2) && (length != 3) && (length != 4))
                System.out.println("### Difference between starting value and ending value should be between " +
                        " 2 and 5. input a valid number . ###");

        } while ((length != 1) && (length != 2) && (length != 3) && (length != 4));

        //This for statement initializes place array that we will pass it to Ship constructor.
        boolean[][] place = new boolean[10][10];

        //Checks if the ship hits other ship or not
        for (int column = startingColumn; column <= endingColumn; column++)
            if (shipsPlace[rowNumber][column] == true)
                isShipValid = false;

        if (isShipValid == true)
            for (int column = startingColumn; column <= endingColumn; column++) {
                place[rowNumber][column] = true;
                shipsPlace[rowNumber][column] = true;
            }

        //Instantiates Ship object.
        ships[shipNumber] = new Ship(length, true, place);

        return isShipValid;
    }

    /**
     * TODO
     * When setShips method calls this private method , setHorizontalShip gets valid ship place
     * from computer and instantiate a Ship object.
     * NOTE : This class is special for CPU and computer.
     *
     * @param shipNumber indicates which ship to instantiate
     */
    private boolean setHorizontalShip(int shipNumber, String computer) {

        int rowNumber; //Indicates ship row.
        int startingColumn;//Ship starts from startingColumn to endingColumn.
        int endingColumn;
        Random rand = new Random();
        int length;//This variable is equal to ship length.
        boolean isShipValid = true;

        do {
            startingColumn = rand.nextInt(10);
            endingColumn = rand.nextInt(10);

            //This if statement swaps startingColumn and endingColumn if startingColumn is bigger than endingColumn.
            if (startingColumn > endingColumn) {
                int tempSwap = startingColumn;
                startingColumn = endingColumn;
                endingColumn = tempSwap;
            }
            rowNumber = rand.nextInt(10);
            length = endingColumn - startingColumn;//After swapping length is always positive.


        } while ((length != 1) && (length != 2) && (length != 3) && (length != 4));

        //This for statement initializes place array that we will pass it to Ship constructor.
        boolean[][] place = new boolean[10][10];

        //Checks if the ship hits other ship or not
        for (int column = startingColumn; column <= endingColumn; column++)
            if (shipsPlace[rowNumber][column] == true)
                isShipValid = false;

        if (isShipValid == true)
            for (int column = startingColumn; column <= endingColumn; column++) {
                place[rowNumber][column] = true;
                shipsPlace[rowNumber][column] = true;
            }

        //Instantiates Ship object.
        ships[shipNumber] = new Ship(length, true, place);

        return isShipValid;
    }

    /**
     * TODO
     * This method prints ships on the board.
     * X ==> when there is a ship
     * blank space ==> when there is not a ship
     */
    public void drawShipsPlace() {

        System.out.print(ANSI_PURPLE + "X\\Y|0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |"+ANSI_RESET);

        for (int row = 0; row < 10; row++) {
            System.out.print(ANSI_PURPLE + "\n--|---+---+---+---+---+---+---+---+---+---+\n"+ANSI_RESET);
            System.out.print(row + " |");

            for (int column = 0; column < 10; column++)
                if (shipsPlace[row][column] == true)
                    System.out.print(ANSI_BLUE_BACKGROUND + " X " + ANSI_RESET + "|");
                else
                    System.out.print("   |");

        }

        System.out.println("");
    }

    /**
     * TODO
     * This method is used to shoot to a ship. If user inputs wrong point this method
     * will get another X and Y.
     */
    public boolean shoot(int x, int y) {
        boolean hit = false ;

        //Indicates that the bullet hits or not
        if (shipsPlace[x][y] == true) {
            warBoard[x][y] = '&';
            myWarBoard[x][y] = '#';
            hit = true ;
            System.out.println("Your bullet hits the ship. (&) ");
        } else {
            warBoard[x][y] = 'X';
            System.out.println("The bullet did not hit. (X) ");
        }
        return hit ;

    }

    /**
     * TODO
     * This method prints hits on the board.
     * X ==> missed shoots
     * & ==> destroyed parts of the ships.
     *
     * @ ==> intact parts of ships
     * # ==> destroyed parts of the ships
     */
    public void drawWarBoard(Board otherPlayer) {
        System.out.print("X\\Y|0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |          X\\Y|0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        for (int row = 0; row < 10; row++) {
            System.out.print("\n--|---+---+---+---+---+---+---+---+---+---+          --|---+---+---+---+---+---+---+---+---+---+\n");
            System.out.print(row + " |");
            for (int column = 0; column < 10; column++) {
                if (warBoard[row][column] == 'X')
                    System.out.print(ANSI_YELLOW_BACKGROUND + " X " + ANSI_RESET + "|");
                else if (warBoard[row][column] == '&')
                    System.out.print(ANSI_GREEN_BACKGROUND + " & " + ANSI_RESET + "|");
                else
                    System.out.print("   |");
            }

            System.out.print("          " + row + " |");
            for (int column = 0; column < 10; column++) {
                if (otherPlayer.myWarBoard[row][column] == '@')
                    System.out.print(ANSI_BLUE_BACKGROUND + " @ " + ANSI_RESET + "|");
                else if (otherPlayer.myWarBoard[row][column] == '#')
                    System.out.print(ANSI_RED_BACKGROUND + " # " + ANSI_RESET + "|");
                else
                    System.out.print("   |");
            }
        }
        System.out.println("");
    }

    /**
     * TODO
     * This method return true if the game is finished and the other player wins the game
     *
     * @return finished
     */
    public boolean isTheGameFinished() {
        boolean finished = true;

        for (int row = 0; row < 10; row++)
            for (int column = 0; column < 10; column++)
                if ((shipsPlace[row][column] == true) && (myWarBoard[row][column] == '@'))
                    finished = false;

        return finished;
    }

    /**
     * This method takes an integer value between 0 and 9 and if
     * the user inputs invalid number it takes the number again.
     *
     * @return value
     */
    private int takeIntBetween0and9() {
        int value;
        Scanner input = new Scanner(System.in);
        value = input.nextInt();
        while ((value < 0) || (value > 9)) {
            System.out.print("Error : Enter number between 0 and 9 : ");
            value = input.nextInt();
        }
        return value;
    }

}
