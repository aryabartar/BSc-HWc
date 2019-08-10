package EnemyTanks;

import Engine.GameState;
import Equipment.Tank;

import java.awt.*;

public class DynamicTankEasy extends DynamicEnemyClass {


    public DynamicTankEasy(int locX, int locY, int toThisXLocation, int toThisYLocation , GameState gameState) {
        super(locX, locY, toThisXLocation, toThisYLocation , 10 + 4 * GameState.difficultyLevel, 4 , gameState);
    }
}
