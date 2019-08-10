package Engine;

import Engine.GameFrame;
import Network.GameClient;
import Network.GameServer;
import Serialization.Serialize;

import javax.swing.*;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 * <p>
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 * http://gameprogrammingpatterns.com/game-loop.html
 *
 * @author
 */

public class GameLoop implements Runnable {

    public static boolean isPaused = false;

    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 60;

    private boolean load ;
    private GameFrame canvas;
    private GameState state;
    GameStateSuperInfo gameStateSuperInfo ;


    public GameLoop(GameFrame frame , GameStateSuperInfo gameStateSuperInfo , boolean load ) {
        this.load = load ;
        this.gameStateSuperInfo = gameStateSuperInfo;
        canvas = frame;

    }

    /**
     * This must be called before the game loop starts.
     */
    public void init() {
        state = new GameState(load);
        canvas.addKeyListener(state.getKeyListener());
        canvas.addMouseListener(state.getMouseListener());
        canvas.addMouseMotionListener(state.getMouseMotionListener());
    }

    @Override
    public void run() {
        boolean gameOver = false;
        int tmp = 0; //For a smooth pause !
        while (!gameOver) {
            try {

                long start = System.currentTimeMillis();
                //
                state.update();
                canvas.render(state);
                gameOver = state.gameOver;
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            }
            if (isPaused == true)
                tmp++;
            else
                tmp = 0;
            while ((isPaused == true) && (tmp > 5)) {
                System.out.print("");
            }
        }
        canvas.render(state);
    }
}
