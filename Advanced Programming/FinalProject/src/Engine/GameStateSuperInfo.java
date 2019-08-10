package Engine;

import Blocks.Block;
import EnemyTanks.EnemyTank;
import Equipment.Bullet;
import Equipment.*;
import Equipment.Rocket;
import Others.Point;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStateSuperInfo implements Serializable {

    public ArrayList<Equipment> equipments;
    public ArrayList <Block> blocks ;
    public ArrayList<EnemyTank>enemyTanks ;

    public GameStateSuperInfo() {
        blocks = new ArrayList<>() ;
        equipments = new ArrayList<>();
        enemyTanks = new ArrayList<>();

    }
}
