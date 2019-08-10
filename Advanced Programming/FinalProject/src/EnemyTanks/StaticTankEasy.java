package EnemyTanks;

import EnemyTanks.EnemyTank;
import Engine.GameState;

public class StaticTankEasy extends EnemyTank {


    public StaticTankEasy( int locX , int locY , GameState gameState) {
        super(8 + 3 * GameState.difficultyLevel, locX , locY , 4 , gameState);
    }

}
