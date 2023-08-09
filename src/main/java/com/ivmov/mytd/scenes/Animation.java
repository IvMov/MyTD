package com.ivmov.mytd.scenes;

public class Animation {

    protected final int ANIMATION_SPEED = 20; //more means slower!

    protected int animationIndex;
    protected int tick;

    protected void updateWaterTick() {
        updateTick(4);
    }

    protected void updateTick(int times) {
        tick++;
        if (tick >= ANIMATION_SPEED) { //used to do faster/slower animation
            tick = 0;
            animationIndex++;
            if (animationIndex >= times) {
                animationIndex = 0;
            }
        }
    }

}
