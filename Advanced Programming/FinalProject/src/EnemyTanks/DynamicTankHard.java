package EnemyTanks;

import Engine.GameState;

public class DynamicTankHard extends DynamicEnemyClass {
    public DynamicTankHard(int locX, int locY, int toThisXLocation, int toThisYLocation  , GameState gameState ) {
        super(locX, locY, toThisXLocation, toThisYLocation , 20 + 3 * GameState.difficultyLevel, 2 , gameState);
    }
}

