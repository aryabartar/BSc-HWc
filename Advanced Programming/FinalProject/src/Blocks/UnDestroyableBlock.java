package Blocks;

public class UnDestroyableBlock extends Block {
    public UnDestroyableBlock(int locX, int locY) {
        super(locX, locY , 100 , 100);
    }

    @Override
    public void reduceHealth(int reduce) {
        //Nothing man :D :))
    }
}
