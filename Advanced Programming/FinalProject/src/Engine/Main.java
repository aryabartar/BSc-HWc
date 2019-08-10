package Engine;


/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {
    public static void main(String[] args)  {
        GameStateSuperInfo gameStateSuperInfo = new GameStateSuperInfo() ;

        ThreadPool.init();
        new Start(gameStateSuperInfo);

    }
}
