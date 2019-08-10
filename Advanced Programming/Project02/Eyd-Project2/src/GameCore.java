import java.util.Scanner;

/**
 * The GameCore class implements a core to play the game in player VS player
 * and CPU vs player mode.
 *
 * @author Arya Khaligh
 */
public class GameCore {

    /**TODO
     * This method is called when the game is in player V.S. player mode.
     */
    public void p2p() {

        System.out.println("     ##########################" +
                "\n     ##########PLAYER 1########" +
                "\n     ##########################");
        Board player1Board = new Board();
        player1Board.setShips();
        Shoot player1Shoot = new Shoot();

        //player 2 round
        System.out.println("\n\n\n\n     ##########################" +
                "\n     ##########PLAYER 2########" +
                "\n     ##########################");
        Board player2Board = new Board();
        player2Board.setShips();
        Shoot player2Shoot = new Shoot();

        //Game STARTS
        int[] point;
        for (int i = 0; i < 100; i++) {
            //Player 1 round
            System.out.println("\n\n\n\n\n\n\n\n\n     ################################" +
                    "\n     ##########PLAYER 1 ROUND########" +
                    "\n     ################################\n");


            player2Board.drawWarBoard(player1Board);
            point = player1Shoot.shoot();
            player2Board.shoot(point[0], point[1]);
            player2Board.drawWarBoard(player1Board);


            //Player 2 round
            System.out.println("\n\n\n\n\n\n\n\n\n     ################################" +
                    "\n     ##########PLAYER 2 ROUND########" +
                    "\n     ################################\n");


            player1Board.drawWarBoard(player2Board);
            point = player2Shoot.shoot();
            player1Board.shoot(point[0], point[1]);
            player1Board.drawWarBoard(player2Board);

            if (player1Board.isTheGameFinished() == true) {
                System.out.println("##########################################\n" +
                        "###############PLAYER 2 WINS##############\n" +
                        "##########################################");
                break;
            }

            if (player2Board.isTheGameFinished() == true) {
                System.out.println("##########################################\n" +
                        "###############PLAYER 1 WINS##############\n" +
                        "##########################################");
                break;
            }

        }
    }

    /**TODO
     * This method is called when the game is in player V.S. computer mode.
     */
    public void playerVsMachine (){
        System.out.println("     ##########################" +
                "\n     ##########PLAYER 1########" +
                "\n     ##########################");
        Board player1Board = new Board();
        player1Board.setShips();
        Shoot player1Shoot = new Shoot();

        Board computerBoard = new Board();
        computerBoard.setShips("computer");
        Shoot computerShoot = new Shoot("computer") ;

        //Game STARTS
        int[] point;
        boolean hit = false ;
        for (int i = 0; i < 100; i++) {
            //Player 1 round
            System.out.println("\n\n\n\n\n\n\n\n\n     ################################" +
                    "\n     ##########PLAYER 1 ROUND########" +
                    "\n     ################################\n");


            computerBoard.drawWarBoard(player1Board);
            point = player1Shoot.shoot();
            computerBoard.shoot(point[0], point[1]);
            computerBoard.drawWarBoard(player1Board);


            //Computer round
            System.out.println("\n\n\n\n\n\n\n\n\n     ################################" +
                    "\n     ##########COMPUTER ROUND########" +
                    "\n     ################################\n");


            player1Board.drawWarBoard(computerBoard);
            point = computerShoot.shoot("computer" , hit);
            hit = player1Board.shoot(point[0], point[1]);
            player1Board.drawWarBoard(computerBoard);

            if (player1Board.isTheGameFinished() == true) {
                System.out.println("##########################################\n" +
                        "###############COMPUTER WINS##############\n" +
                        "##########################################");
                break;
            }

            if (computerBoard.isTheGameFinished() == true) {
                System.out.println("##########################################\n" +
                        "###############PLAYER 1 WINS##############\n" +
                        "##########################################");
                break;
            }
        }
    }

    /**
     * TODO
     * This method determines game mode.
     */
    public void gameMode() {
        Scanner input = new Scanner(System.in);
        boolean gameMode;
        System.out.print("What kind of game do you want to do?" +
                "\n true ==> player VS player\n false ==> CPU VS player\n : ");

        gameMode = input.nextBoolean() ;
        if (gameMode == true)
            p2p() ;
        else playerVsMachine();


    }
}
